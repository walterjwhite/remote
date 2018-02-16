package com.walterjwhite.remote.impl.handler;

import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.remote.api.service.MessageWriterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: alternatively rather than use a handler, provide a super class that responds to events
 * listening is already asynchronous, so we can respond in that same thread rather than detach it
 * again
 */
public abstract class AbstractMessageHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMessageHandler.class);

  protected final MessageWriterService messageWriterService;

  protected AbstractMessageHandler(MessageWriterService messageWriterService) {
    super();
    this.messageWriterService = messageWriterService;
  }

  protected void reply(Message request, Message message) throws Exception {
    if (message != null) {
      message.getRecipients().add(request.getSender());
      messageWriterService.write(
          message, message.getRecipients().toArray(new Client[message.getRecipients().size()]));
    }
  }
}
