package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.enumeration.SystemAction;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class SystemActionMessage extends Message {
  @Enumerated(EnumType.STRING)
  @Column
  protected SystemAction systemAction;

  public SystemActionMessage(Set<Client> recipients, int timeToLive, SystemAction systemAction) {
    super(recipients, timeToLive);

    this.systemAction = systemAction;
  }

  public SystemActionMessage(Client recipient, int timeToLive, SystemAction systemAction) {
    super(recipient, timeToLive);

    this.systemAction = systemAction;
  }

  /** Required for deserialization. */
  public SystemActionMessage() {
    super();
  }

  public SystemAction getSystemAction() {
    return systemAction;
  }

  public void setSystemAction(SystemAction systemAction) {
    this.systemAction = systemAction;
  }

  @Override
  public String toString() {
    return "SystemActionMessage{"
        + "systemAction="
        + systemAction
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
