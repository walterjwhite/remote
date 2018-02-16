package com.walterjwhite.remote.impl.plugins.heartbeat;

import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.model.*;
import com.walterjwhite.shell.api.model.dig.DigRequest;
import com.walterjwhite.shell.api.model.ping.PingRequest;
import com.walterjwhite.shell.api.model.traceroute.TracerouteRequest;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Capture the same information that would be reported through sysstatus: upower -d (battery level /
 * state) services / status / ports listening on ARP DIG google.com
 */
@Entity
public class HeartbeatMessage extends Message {
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  protected BatteryStatus batteryStatus;

  //  @Column protected double downloadBandwidthInBytesPerSecond;
  //  @Column protected double uploadBandwidthInBytesPerSecond;
  //
  //  @Column protected double latencyInMilliSeconds;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable
  protected Set<ServiceStatus> serviceStatuses = new HashSet<>();

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable
  protected Set<NetworkInterfaceState> networkInterfaceStates = new HashSet<>();

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  protected DigRequest digRequest;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  protected TracerouteRequest tracerouteRequest;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  protected PingRequest pingRequest;

  // TODO: ARP
  // TODO: disk usage
  // TODO: interfaces

  public HeartbeatMessage(
      int timeToLive,
      final BatteryStatus batteryStatus,
      final Set<ServiceStatus> serviceStatuses,
      final DigRequest digRequest,
      final TracerouteRequest tracerouteRequest,
      final PingRequest pingRequest,
      final Set<NetworkInterfaceState> networkInterfaceStates) {
    super(timeToLive);

    this.batteryStatus = batteryStatus;

    if (serviceStatuses != null && !serviceStatuses.isEmpty())
      this.serviceStatuses.addAll(serviceStatuses);

    this.digRequest = digRequest;
    this.pingRequest = pingRequest;
    this.tracerouteRequest = tracerouteRequest;

    if (networkInterfaceStates != null && !networkInterfaceStates.isEmpty())
      this.networkInterfaceStates = networkInterfaceStates;
  }

  /** Required for de-serialization. */
  public HeartbeatMessage() {
    super();
  }

  public BatteryStatus getBatteryStatus() {
    return batteryStatus;
  }

  public void setBatteryStatus(BatteryStatus batteryStatus) {
    this.batteryStatus = batteryStatus;
  }

  public Set<ServiceStatus> getServiceStatuses() {
    return serviceStatuses;
  }

  public void setServiceStatuses(Set<ServiceStatus> serviceStatuses) {
    this.serviceStatuses = serviceStatuses;
  }

  public Set<NetworkInterfaceState> getNetworkInterfaceStates() {
    return networkInterfaceStates;
  }

  public void setNetworkInterfaceStates(Set<NetworkInterfaceState> networkInterfaceStates) {
    this.networkInterfaceStates = networkInterfaceStates;
  }

  public DigRequest getDigRequest() {
    return digRequest;
  }

  public void setDigRequest(DigRequest digRequest) {
    this.digRequest = digRequest;
  }

  public TracerouteRequest getTracerouteRequest() {
    return tracerouteRequest;
  }

  public void setTracerouteRequest(TracerouteRequest tracerouteRequest) {
    this.tracerouteRequest = tracerouteRequest;
  }

  public PingRequest getPingRequest() {
    return pingRequest;
  }

  public void setPingRequest(PingRequest pingRequest) {
    this.pingRequest = pingRequest;
  }

  @Override
  public String toString() {
    return "HeartbeatMessage{"
        + "batteryStatus="
        + batteryStatus
        + ", serviceStatuses="
        + serviceStatuses
        + ", networkInterfaceStates="
        + networkInterfaceStates
        + ", digRequest="
        + digRequest
        + ", recipients="
        + recipients
        + ", sender="
        + sender
        + ", dateCreated="
        + dateCreated
        + ", dateSent="
        + dateSent
        + ", timeToLive="
        + timeToLive
        + ", token='"
        + token
        + '\''
        + '}';
  }
}
