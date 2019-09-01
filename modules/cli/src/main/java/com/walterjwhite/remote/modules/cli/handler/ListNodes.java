package com.walterjwhite.remote.modules.cli.handler;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.remote.api.repository.FindMessagesByTime;
import com.walterjwhite.remote.impl.plugins.heartbeat.HeartbeatMessage;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

/**
 * List all active nodes within the past 5 minutes. IE. checks for heartbeats less than 5 minutes
 * old.
 */
public class ListNodes extends AbstractCommandLineHandler {
  protected final Repository repository;

  @Inject
  public ListNodes(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      Repository repository) {
    super(shutdownTimeoutInSeconds);

    this.repository = repository;
  }

  @Override
  protected void doRun(final String... arguments) throws Exception {
    final List<Message> messages =
        (List<Message>) repository.query(new FindMessagesByTime(0, -1, 1, TimeUnit.HOURS));
    for (Message message : messages) {
      if (message instanceof HeartbeatMessage) {
        final HeartbeatMessage heartbeatMessage = ((HeartbeatMessage) message);

        //        final HeartbeatMessageCallable heartbeatMessageHandlerService = new
        // HeartbeatMessageCallable();
        //        heartbeatMessageHandlerService.setEntity(heartbeatMessage);
        //        heartbeatMessageHandlerService.call();

        System.out.println("heartbeat message:" + heartbeatMessage);
      }
    }
  }
}
