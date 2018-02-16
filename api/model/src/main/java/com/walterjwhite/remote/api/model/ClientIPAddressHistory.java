package com.walterjwhite.remote.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.shell.api.model.IPAddress;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class ClientIPAddressHistory extends AbstractEntity {
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected Client client;

  @Column(nullable = false, updatable = false)
  protected LocalDateTime dateTime = LocalDateTime.now();

  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(nullable = false, updatable = false)
  protected IPAddress ipAddress;

  public ClientIPAddressHistory(Client client, IPAddress ipAddress) {
    super();
    this.client = client;
    this.ipAddress = ipAddress;
  }

  public ClientIPAddressHistory() {
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

  public IPAddress getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(IPAddress ipAddress) {
    this.ipAddress = ipAddress;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ClientIPAddressHistory that = (ClientIPAddressHistory) o;

    if (client != null ? !client.equals(that.client) : that.client != null) return false;
    return dateTime != null ? dateTime.equals(that.dateTime) : that.dateTime == null;
  }

  @Override
  public int hashCode() {
    int result = client != null ? client.hashCode() : 0;
    result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ClientIPAddressHistory{" + "dateTime=" + dateTime + ", ipAddress=" + ipAddress + '}';
  }
}
