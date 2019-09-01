package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.serialization.api.annotation.PrivateField;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
// @MappedSuperclass
// @PersistenceCapable
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Message extends AbstractEntity {
  /** populated when the message is sent * */
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected Client sender;

  @Column(nullable = false, updatable = false)
  protected LocalDateTime dateCreated = LocalDateTime.now();

  @EqualsAndHashCode.Exclude @PrivateField @OneToMany /*(cascade = CascadeType.ALL)*/
  protected Set<Client> recipients = new HashSet<>();

  @EqualsAndHashCode.Exclude @Column protected LocalDateTime dateSent;

  @EqualsAndHashCode.Exclude @Column protected int timeToLive;

  @EqualsAndHashCode.Exclude
  /** Compute a hash of the message: sender UUID, time, IP, etc. */
  @Column
  protected String token;

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

  @PrePersist
  public void onPrePersist() {
    super.onPreCreate();

    this.dateSent = LocalDateTime.now();
  }
}
