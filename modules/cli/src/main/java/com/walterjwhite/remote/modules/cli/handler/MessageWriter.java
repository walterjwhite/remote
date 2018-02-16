package com.walterjwhite.remote.modules.cli.handler;

import com.walterjwhite.google.guice.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.google.guice.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.google.guice.property.property.Property;
import javax.inject.Inject;

/** Write messages out to Amazon SQS (or whatever other provider is configured). */
public class MessageWriter extends AbstractCommandLineHandler {

  @Inject
  public MessageWriter(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @Override
  public void run(final String... arguments) throws Exception {
    // daemon should already be running
    // wait 60 seconds for new messages
    Thread.sleep(60000);
  }
}
