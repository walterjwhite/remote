package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.remote.api.model.Client;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
/** Avoid network activity for a fixed amount of time (both listening (receiving) and sending). */
// @PersistenceCapable
@Entity
public class BeQuietMessage extends Message {
  @Column(nullable = false)
  protected long duration;

  @Column(nullable = false)
  protected TimeUnit timeUnit;

  public BeQuietMessage(Set<Client> recipients, int timeToLive, long duration, TimeUnit timeUnit) {
    super(recipients, timeToLive);
    this.duration = duration;
    this.timeUnit = timeUnit;
  }

  public BeQuietMessage(Client recipient, int timeToLive, long duration, TimeUnit timeUnit) {
    super(recipient, timeToLive);
    this.duration = duration;
    this.timeUnit = timeUnit;
  }
}
