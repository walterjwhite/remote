package com.walterjwhite.remote.modules.cli.dto;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.modules.cli.enumeration.RemoteOperatingMode;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class RemoteConfiguration extends AbstractEntity {
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected Client client;

  @Column(nullable = false, updatable = false)
  protected LocalDateTime dateTime = LocalDateTime.now();

  @Column
  @Enumerated(EnumType.STRING)
  protected RemoteOperatingMode remoteOperatingMode;

  // protected boolean nop;

  public RemoteConfiguration(Client client, RemoteOperatingMode remoteOperatingMode) {
    super();
    this.client = client;
    this.remoteOperatingMode = remoteOperatingMode;
  }

  public RemoteConfiguration() {
    super();
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public void setRemoteOperatingMode(RemoteOperatingMode remoteOperatingMode) {
    this.remoteOperatingMode = remoteOperatingMode;
  }

  public RemoteOperatingMode getRemoteOperatingMode() {
    return remoteOperatingMode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RemoteConfiguration that = (RemoteConfiguration) o;

    if (client != null ? !client.equals(that.client) : that.client != null) return false;
    return dateTime != null ? dateTime.equals(that.dateTime) : that.dateTime == null;
  }

  @Override
  public int hashCode() {
    int result = client != null ? client.hashCode() : 0;
    result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
    return result;
  }
}
