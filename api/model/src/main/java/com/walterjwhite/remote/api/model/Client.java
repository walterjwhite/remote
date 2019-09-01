package com.walterjwhite.remote.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.model.Node;
import java.util.ArrayList;
import java.util.List;
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
public class Client extends AbstractEntity {

  // this is the UUID from the root device
  @EqualsAndHashCode.Exclude @Transient protected transient String uuid;

  @Column(nullable = false, updatable = false)
  protected String hashedIdentifier;

  @EqualsAndHashCode.Exclude @OneToOne /*(cascade = CascadeType.ALL)*/ @JoinColumn
  protected Node node;

  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
  protected List<ClientIPAddressHistory> clientIPAddressHistoryList = new ArrayList<>();

  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
  protected List<Message> sentMessages = new ArrayList<>();

  @EqualsAndHashCode.Exclude @Column protected byte[] initializationVector;

  public Client(Node node, String hashedIdentifier, byte[] initializationVector) {
    super();

    this.node = node;
    this.hashedIdentifier = hashedIdentifier;
    this.initializationVector = initializationVector;
  }

  public Client(Node node, String hashedIdentifier) {
    this(node, hashedIdentifier, null);
  }
}
