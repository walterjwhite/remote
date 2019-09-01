package com.walterjwhite.remote.impl.plugins.heartbeat;

import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.model.ping.PingRequest;
import com.walterjwhite.shell.api.model.ping.PingResponse;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
// @PersistenceCapable
@Entity
public class PingMessageResponse extends Message {
  protected PingRequest pingRequest;

  protected PingResponse pingResponse;

  public PingMessageResponse(PingRequest pingRequest, PingResponse pingResponse) {
    super();
    this.pingRequest = pingRequest;
    this.pingResponse = pingResponse;
  }

  public PingMessageResponse() {
    super();
  }
}
