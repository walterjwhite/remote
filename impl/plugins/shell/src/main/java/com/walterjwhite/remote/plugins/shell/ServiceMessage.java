package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.enumeration.ServiceAction;
import com.walterjwhite.shell.api.model.Service;
import java.util.Set;
import javax.persistence.*;

@Entity
public class ServiceMessage extends Message {
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  protected Service service;

  @Enumerated(EnumType.STRING)
  @Column
  protected ServiceAction serviceAction;

  public ServiceMessage(
      Set<Client> recipients, int timeToLive, Service service, ServiceAction serviceAction) {
    super(recipients, timeToLive);

    this.service = service;
    this.serviceAction = serviceAction;
  }

  public ServiceMessage(
      Client recipient, int timeToLive, Service service, ServiceAction serviceAction) {
    super(recipient, timeToLive);

    this.service = service;
    this.serviceAction = serviceAction;
  }

  /** Required for deserialization */
  public ServiceMessage() {
    super();
  }

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  public ServiceAction getServiceAction() {
    return serviceAction;
  }

  public void setServiceAction(ServiceAction serviceAction) {
    this.serviceAction = serviceAction;
  }

  @Override
  public String toString() {
    return "ServiceMessage{"
        + "service="
        + service
        + ", serviceAction="
        + serviceAction
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
