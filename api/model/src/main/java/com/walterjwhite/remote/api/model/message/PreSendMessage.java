package com.walterjwhite.remote.api.model.message;

/** Used for event handling. */
public class PreSendMessage {
  protected final Message message;

  public PreSendMessage(Message message) {
    super();
    this.message = message;
  }

  public Message getMessage() {
    return message;
  }
}
