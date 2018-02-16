package com.walterjwhite.remote.modules.cli.handler;

import com.walterjwhite.google.guice.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.google.guice.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.google.guice.property.property.Property;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.remote.api.service.MessageRepository;
import com.walterjwhite.remote.impl.plugins.heartbeat.HeartbeatMessage;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * List all active nodes within the past 5 minutes. IE. checks for heartbeats less than 5 minutes
 * old.
 */
public class ListNodes extends AbstractCommandLineHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(ListNodes.class);
  protected final MessageRepository messageRepository;

  @Inject
  public ListNodes(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      MessageRepository messageRepository) {
    super(shutdownTimeoutInSeconds);
    this.messageRepository = messageRepository;
  }

  @Override
  public void run(final String... arguments) throws Exception {
    for (Message message : messageRepository.findWithinThePastHour()) {
      if (message instanceof HeartbeatMessage) {
        final HeartbeatMessage heartbeatMessage = ((HeartbeatMessage) message);

        //        final HeartbeatMessageHandlerService heartbeatMessageHandlerService = new
        // HeartbeatMessageHandlerService();
        //        heartbeatMessageHandlerService.setEntity(heartbeatMessage);
        //        heartbeatMessageHandlerService.call();

        System.out.println("heartbeat message:" + heartbeatMessage);
      }
    }
  }
}
