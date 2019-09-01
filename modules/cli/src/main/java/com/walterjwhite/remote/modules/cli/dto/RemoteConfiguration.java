package com.walterjwhite.remote.modules.cli.dto;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.modules.cli.enumeration.RemoteOperatingMode;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@Entity
public class RemoteConfiguration extends AbstractEntity {
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected Client client;

  @Column(nullable = false, updatable = false)
  protected LocalDateTime dateTime = LocalDateTime.now();

  @EqualsAndHashCode.Exclude
  @Column
  @Enumerated(EnumType.STRING)
  protected RemoteOperatingMode remoteOperatingMode;

  // protected boolean nop;

  public RemoteConfiguration(Client client, RemoteOperatingMode remoteOperatingMode) {
    super();
    this.client = client;
    this.remoteOperatingMode = remoteOperatingMode;
  }
}
