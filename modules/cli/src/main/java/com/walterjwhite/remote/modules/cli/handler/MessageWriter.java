package com.walterjwhite.remote.modules.cli.handler;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

/** Write messages out to Amazon SQS (or whatever other provider is configured). */
public class MessageWriter extends AbstractCommandLineHandler {

  @Inject
  public MessageWriter(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @Override
  protected void doRun(final String... arguments) throws Exception {
    // daemon should already be running
    // wait 60 seconds for new messages
    Thread.sleep(60000);
  }
}
