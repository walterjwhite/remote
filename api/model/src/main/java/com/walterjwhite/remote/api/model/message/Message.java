package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.serialization.api.annotation.PrivateField;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;

// @MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Message extends AbstractEntity {
  /** populated when the message is sent * */
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected Client sender;

  @Column(nullable = false, updatable = false)
  protected LocalDateTime dateCreated = LocalDateTime.now();

  @PrivateField @OneToMany /*(cascade = CascadeType.ALL)*/
  protected Set<Client> recipients = new HashSet<>();

  @Column protected LocalDateTime dateSent;

  @Column protected int timeToLive;

  /** Compute a hash of the message: sender UUID, time, IP, etc. */
  @Column protected String token;

  protected Message(Set<Client> recipients, int timeToLive) {
    this(timeToLive);

    this.recipients.addAll(recipients);
  }

  protected Message(Client recipient, int timeToLive) {
    this(timeToLive);

    this.recipients.add(recipient);
  }

  protected Message(int timeToLive) {
    this();

    this.timeToLive = timeToLive;
  }

  protected Message() {
    super();
  }

  public Set<Client> getRecipients() {
    return recipients;
  }

  public void setRecipients(Set<Client> recipients) {
    this.recipients = recipients;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public LocalDateTime getDateSent() {
    return dateSent;
  }

  public void setDateSent(LocalDateTime dateSent) {
    this.dateSent = dateSent;
  }

  public int getTimeToLive() {
    return timeToLive;
  }

  public void setTimeToLive(int timeToLive) {
    this.timeToLive = timeToLive;
  }

  public Client getSender() {
    return sender;
  }

  public void setSender(Client sender) {
    this.sender = sender;
  }

  public LocalDateTime getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDateTime dateCreated) {
    this.dateCreated = dateCreated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Message that = (Message) o;

    if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
    return dateCreated != null ? dateCreated.equals(that.dateCreated) : that.dateCreated == null;
  }

  @Override
  public int hashCode() {
    int result = sender != null ? sender.hashCode() : 0;
    result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "AbstractMessage{"
        + "sender="
        + sender
        + ", dateCreated="
        + dateCreated
        + ", recipients="
        + recipients
        + ", dateSent="
        + dateSent
        + ", timeToLive="
        + timeToLive
        + ", token='"
        + token
        + '\''
        + '}';
  }

  @PrePersist
  public void onPrePersist() {
    super.onPrePersist();

    this.dateSent = LocalDateTime.now();
  }
}
