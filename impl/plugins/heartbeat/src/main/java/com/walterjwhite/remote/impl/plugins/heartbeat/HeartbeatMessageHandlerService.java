package com.walterjwhite.remote.impl.plugins.heartbeat;

import com.walterjwhite.queue.api.job.AbstractCallableJob;
import com.walterjwhite.shell.api.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartbeatMessageHandlerService extends AbstractCallableJob<HeartbeatMessage, Void> {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(HeartbeatMessageHandlerService.class);

  protected HeartbeatMessage heartbeatMessage;

  protected void doDig(HeartbeatMessage heartbeatMessage) {
    if (heartbeatMessage.getDigRequest().getDigRequestIPAddresses() != null
        && heartbeatMessage.getDigRequest().getDigRequestIPAddresses().size() > 0) {
      LOGGER.info(
          "received heartbeat: (dig):"
              + heartbeatMessage.getDigRequest().getDigRequestIPAddresses().get(0));
    }
  }

  protected void doService(HeartbeatMessage heartbeatMessage) {
    for (ServiceStatus serviceStatus : heartbeatMessage.getServiceStatuses()) {
      LOGGER.info("service status:" + serviceStatus.getService().getName());

      if (serviceStatus.getBindAddressStates() != null
          && !serviceStatus.getBindAddressStates().isEmpty()) {
        for (BindAddressState addressState : serviceStatus.getBindAddressStates()) {
          LOGGER.info(
              "service status: (ipAddress):"
                  + addressState.getBindAddress().getProtocol()
                  + "@"
                  + addressState.getBindAddress().getIpAddress().getAddress());
        }
      }
    }
  }

  protected void doInterface(HeartbeatMessage heartbeatMessage) {
    for (NetworkInterfaceState networkInterfaceState :
        heartbeatMessage.getNetworkInterfaceStates()) {
      LOGGER.info(
          "received heartbeat: (networkInterfaceState):"
              + networkInterfaceState.getNetworkInterface().getInterfaceName());

      if (networkInterfaceState.getIpAddressStates() != null
          && !networkInterfaceState.getIpAddressStates().isEmpty()) {
        for (IPAddressState addressState : networkInterfaceState.getIpAddressStates()) {
          LOGGER.info(
              "received heartbeat: (networkInterfaceState/ipAddress):"
                  + addressState.getIpAddress());
        }
      }
    }
  }

  @Override
  protected boolean isRetryable(Throwable thrown) {
    return false;
  }

  @Override
  public Void call() throws Exception {
    LOGGER.warn("processing heartbeat:" + heartbeatMessage);

    LOGGER.info("received heartbeat: (identifier):" + heartbeatMessage.getSender().getId());
    LOGGER.info(
        "received heartbeat: (IP):"
            + heartbeatMessage
                .getSender()
                .getClientIPAddressHistoryList()
                .get(heartbeatMessage.getSender().getClientIPAddressHistoryList().size() - 1)
                .getIpAddress());
    LOGGER.info("received heartbeat: (date sent):" + heartbeatMessage.getDateSent());

    LOGGER.info("received heartbeat: (battery status):" + heartbeatMessage.getBatteryStatus());

    LOGGER.info(
        "received heartbeat: (dig fqdn):"
            + heartbeatMessage.getDigRequest().getNetworkDiagnosticTest().getFqdn());

    doDig(heartbeatMessage);
    doService(heartbeatMessage);
    doInterface(heartbeatMessage);

    return null;
  }
}
