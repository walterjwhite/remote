package com.walterjwhite.remote.impl.plugins.heartbeat;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.walterjwhite.datastore.criteria.Repository;
import com.walterjwhite.google.guice.property.property.Property;
import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.shell.api.enumeration.ServiceAction;
import com.walterjwhite.shell.api.model.*;
import com.walterjwhite.shell.api.model.dig.DigRequest;
import com.walterjwhite.shell.api.model.ping.PingRequest;
import com.walterjwhite.shell.api.model.traceroute.TracerouteRequest;
import com.walterjwhite.shell.api.repository.ServiceRepository;
import com.walterjwhite.shell.api.service.*;
import com.walterjwhite.shell.api.service.unused.NetworkInterfaceService;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: use executor service to register periodic heartbeat
@Singleton
public class ClientHeartbeatService extends AbstractScheduledService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ClientHeartbeatService.class);

  protected final MessageWriterService messageWriterService;

  protected final Provider<DigService> digServiceProvider;
  protected final Provider<TracerouteService> tracerouteServiceProvider;
  protected final Provider<PingService> pingServiceProvider;

  protected final Provider<NetworkInterfaceService> networkInterfaceServiceProvider;

  protected final Provider<SystemServiceService> systemServiceServiceProvider;

  protected final Provider<UpowerService> upowerServiceProvider;

  protected final Provider<ServiceRepository> serviceRepositoryProvider;

  protected final Provider<Repository> repositoryProvider;

  protected final int heartbeatMaximumInterval;

  protected LocalDateTime nextRuntime = LocalDateTime.now();

  @Inject
  public ClientHeartbeatService(
      MessageWriterService messageWriterService,
      Provider<DigService> digServiceProvider,
      Provider<TracerouteService> tracerouteServiceProvider,
      Provider<PingService> pingServiceProvider,
      Provider<NetworkInterfaceService> networkInterfaceServiceProvider,
      Provider<SystemServiceService> systemServiceServiceProvider,
      Provider<UpowerService> upowerServiceProvider,
      Provider<ServiceRepository> serviceRepositoryProvider,
      Provider<Repository> repositoryProvider,
      @Property(HeartbeatMaximumInterval.class) int heartbeatMaximumInterval) {
    super();
    this.digServiceProvider = digServiceProvider;
    this.messageWriterService = messageWriterService;
    this.tracerouteServiceProvider = tracerouteServiceProvider;
    this.pingServiceProvider = pingServiceProvider;
    this.networkInterfaceServiceProvider = networkInterfaceServiceProvider;
    this.systemServiceServiceProvider = systemServiceServiceProvider;
    this.upowerServiceProvider = upowerServiceProvider;
    this.serviceRepositoryProvider = serviceRepositoryProvider;
    this.repositoryProvider = repositoryProvider;

    this.heartbeatMaximumInterval = heartbeatMaximumInterval;
  }

  public void doSendHeartbeat() throws Exception {
    LOGGER.info("sending heartbeat");

    try {
      final List<Service> services = serviceRepositoryProvider.get().findAll();

      /** TTL 1hr* */
      final HeartbeatMessage heartbeatMessage;

      final Set<ServiceStatus> serviceStatuses = new HashSet<>();
      for (final Service service : services) {
        serviceStatuses.add(
            systemServiceServiceProvider
                .get()
                .execute(
                    new ServiceCommand()
                        .withService(service)
                        .withServiceAction(ServiceAction.Status))
                .getServiceStatus());
      }

      final NetworkDiagnosticTest networkDiagnosticTest =
          (NetworkDiagnosticTest) repositoryProvider.get().findRandom(NetworkDiagnosticTest.class);
      DigRequest digRequest =
          digServiceProvider.get().execute(new DigRequest(networkDiagnosticTest));

      TracerouteRequest tracerouteRequest =
          tracerouteServiceProvider.get().execute(new TracerouteRequest(networkDiagnosticTest));

      PingRequest pingRequest =
          pingServiceProvider.get().execute(new PingRequest(networkDiagnosticTest));

      try {
        // TODO: get service names
        heartbeatMessage =
            new HeartbeatMessage(
                3600000,
                upowerServiceProvider.get().execute(new BatteryRequest()).getBatteryStatus(),
                serviceStatuses,
                digRequest,
                tracerouteRequest,
                pingRequest,
                networkInterfaceServiceProvider.get().getNetworkInterfaceStatesForNode());

        doLog(heartbeatMessage);

      } catch (SocketException e) {
        LOGGER.error("Error getting network interface information", e);
        throw (new RuntimeException("Error getting network interface information", e));
      }

      // just deliver to "all" queue
      // heartbeatMessage.getRecipients().addAll(clientRepositoryService.getClients());

      LOGGER.info("heartbeat message:" + heartbeatMessage.getRecipients().size());
      try {
        messageWriterService.write(heartbeatMessage);

      } catch (Exception e) {
        LOGGER.error("Error writing message", e);
        throw (new RuntimeException("Error writing message", e));
      }
    } catch (Exception e) {
      LOGGER.error("Error sending heartbeat", e);
      throw (e);
    }
  }

  protected void doLog(HeartbeatMessage heartbeatMessage) {
    doLogMiscellaneous(heartbeatMessage);
    doLogServiceStatus(heartbeatMessage);
    doLogInterfaceState(heartbeatMessage);
  }

  protected void doLogMiscellaneous(HeartbeatMessage heartbeatMessage) {
    /*
    LOGGER.info("sending heartbeat: (identifier):" + heartbeatMessage.getSender().getIdentifier());
    LOGGER.info("sending heartbeat: (IP):" + heartbeatMessage.getSender().getPublicIPAddress());
    LOGGER.info("sending heartbeat: (date sent):" + heartbeatMessage.getDateSent());
    */

    LOGGER.info("sending heartbeat: (battery status):" + heartbeatMessage.getBatteryStatus());

    LOGGER.info(
        "sending heartbeat: (dig fqdn):"
            + heartbeatMessage.getDigRequest().getNetworkDiagnosticTest().getFqdn());
    if (heartbeatMessage.getDigRequest().getDigRequestIPAddresses() != null
        && heartbeatMessage.getDigRequest().getDigRequestIPAddresses().size() > 0) {
      LOGGER.info(
          "sending heartbeat: (dig):"
              + heartbeatMessage.getDigRequest().getDigRequestIPAddresses().get(0));
    }
  }

  protected void doLogServiceStatus(HeartbeatMessage heartbeatMessage) {
    for (ServiceStatus serviceStatus : heartbeatMessage.getServiceStatuses()) {
      LOGGER.info(
          "service status:"
              + serviceStatus.getService().getName()
              + ":"
              + serviceStatus.getState());

      if (serviceStatus.getBindAddressStates() != null
          && !serviceStatus.getBindAddressStates().isEmpty()) {
        for (BindAddressState addressState : serviceStatus.getBindAddressStates()) {
          LOGGER.info(
              "service status: (ipAddress):"
                  + addressState.getBindAddress().getProtocol()
                  + "@"
                  + addressState.getBindAddress().getIpAddress());
        }
      }
    }
  }

  protected void doLogInterfaceState(HeartbeatMessage heartbeatMessage) {
    for (NetworkInterfaceState networkInterfaceState :
        heartbeatMessage.getNetworkInterfaceStates()) {
      LOGGER.info(
          "sending heartbeat: (networkInterfaceState):"
              + networkInterfaceState.getNetworkInterface().getInterfaceName());

      if (networkInterfaceState.getIpAddressStates() != null
          && !networkInterfaceState.getIpAddressStates().isEmpty()) {
        for (IPAddressState addressState : networkInterfaceState.getIpAddressStates()) {
          LOGGER.info(
              "sending heartbeat: (networkInterfaceState/ipAddress):"
                  + addressState.getIpAddress());
        }
      }
    }
  }

  @Override
  protected void runOneIteration() throws Exception {
    if (isRun()) {
      doSendHeartbeat();
      scheduleNextRun();
    } else {
      LOGGER.info("not running");
    }
  }

  protected boolean isRun() {
    return (LocalDateTime.now().isAfter(nextRuntime));
  }

  protected void scheduleNextRun() {
    Random generator = new Random();
    int delayInMinutes = generator.nextInt(heartbeatMaximumInterval) + 1;
    LOGGER.info("delaying next run by:" + delayInMinutes + " minutes");
    nextRuntime = LocalDateTime.now().plusMinutes(delayInMinutes);

    LOGGER.info("next run is:" + nextRuntime);
  }

  /**
   * In order to reduce our bandwidth consumption, only send messages during typical business hours.
   */
  @Override
  protected Scheduler scheduler() {
    return AbstractScheduledService.Scheduler.newFixedDelaySchedule(0, 1, TimeUnit.MINUTES);
  }
}
