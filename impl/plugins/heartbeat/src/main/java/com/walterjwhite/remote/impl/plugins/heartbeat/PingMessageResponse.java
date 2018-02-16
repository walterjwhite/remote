package com.walterjwhite.remote.impl.plugins.heartbeat;

import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.model.ping.PingRequest;
import com.walterjwhite.shell.api.model.ping.PingResponse;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PingMessageResponse extends Message {
  @ManyToOne(optional = false)
  @JoinColumn
  protected PingRequest pingRequest;

  @ManyToOne(optional = false)
  @JoinColumn
  protected PingResponse pingResponse;

  public PingMessageResponse(PingRequest pingRequest, PingResponse pingResponse) {
    super();
    this.pingRequest = pingRequest;
    this.pingResponse = pingResponse;
  }

  public PingMessageResponse() {
    super();
  }

  public PingRequest getPingRequest() {
    return pingRequest;
  }

  public void setPingRequest(PingRequest pingRequest) {
    this.pingRequest = pingRequest;
  }

  public PingResponse getPingResponse() {
    return pingResponse;
  }

  public void setPingResponse(PingResponse pingResponse) {
    this.pingResponse = pingResponse;
  }
}
