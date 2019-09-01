package com.walterjwhite.remote.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.shell.api.model.IPAddress;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
// @PersistenceCapable
@Entity
public class ClientIPAddressHistory extends AbstractEntity {
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected Client client;

  @Column(nullable = false, updatable = false)
  protected LocalDateTime dateTime = LocalDateTime.now();

  @EqualsAndHashCode.Exclude
  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(nullable = false, updatable = false)
  protected IPAddress ipAddress;

  public ClientIPAddressHistory(Client client, IPAddress ipAddress) {
    super();
    this.client = client;
    this.ipAddress = ipAddress;
  }
}
