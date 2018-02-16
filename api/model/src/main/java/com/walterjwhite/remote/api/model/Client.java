package com.walterjwhite.remote.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.model.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.*;

@Entity
public class Client extends AbstractEntity {
  // this is the UUID from the root device
  @Transient protected String uuid;

  @Column(nullable = false, updatable = false)
  protected String hashedIdentifier;

  @OneToOne /*(cascade = CascadeType.ALL)*/ @JoinColumn protected Node node;

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
  protected List<ClientIPAddressHistory> clientIPAddressHistoryList = new ArrayList<>();

  @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
  protected List<Message> sentMessages = new ArrayList<>();

  @Column protected byte[] initializationVector;

  public Client(Node node, String hashedIdentifier, byte[] initializationVector) {
    super();

    this.node = node;
    this.hashedIdentifier = hashedIdentifier;
    this.initializationVector = initializationVector;
  }

  public Client(Node node, String hashedIdentifier) {
    this(node, hashedIdentifier, null);
  }

  /** Required for deserialization */
  public Client() {
    super();
  }

  public Node getNode() {
    return node;
  }

  public void setNode(Node node) {
    this.node = node;
  }

  public List<ClientIPAddressHistory> getClientIPAddressHistoryList() {
    return clientIPAddressHistoryList;
  }

  public void setClientIPAddressHistoryList(
      List<ClientIPAddressHistory> clientIPAddressHistoryList) {
    this.clientIPAddressHistoryList = clientIPAddressHistoryList;
  }

  public String getHashedIdentifier() {
    return hashedIdentifier;
  }

  public void setHashedIdentifier(String hashedIdentifier) {
    this.hashedIdentifier = hashedIdentifier;
  }

  public byte[] getInitializationVector() {
    return initializationVector;
  }

  public void setInitializationVector(byte[] initializationVector) {
    this.initializationVector = initializationVector;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public List<Message> getSentMessages() {
    return sentMessages;
  }

  public void setSentMessages(List<Message> sentMessages) {
    this.sentMessages = sentMessages;
  }

  @Override
  public String toString() {
    return "Client{"
        + "uuid='"
        + uuid
        + '\''
        + ", hashedIdentifier='"
        + hashedIdentifier
        + '\''
        + ", initializationVector="
        + Arrays.toString(initializationVector)
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Client client = (Client) o;

    return hashedIdentifier != null
        ? hashedIdentifier.equals(client.hashedIdentifier)
        : client.hashedIdentifier == null;
  }

  @Override
  public int hashCode() {
    return hashedIdentifier != null ? hashedIdentifier.hashCode() : 0;
  }
}
