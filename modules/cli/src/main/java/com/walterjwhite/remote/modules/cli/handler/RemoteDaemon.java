package com.walterjwhite.remote.modules.cli.handler;

import com.walterjwhite.google.guice.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.google.guice.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.google.guice.cli.service.CommandLineDaemon;
import com.walterjwhite.google.guice.property.property.Property;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

/** Run in client mode (default operation). */
public class RemoteDaemon extends AbstractCommandLineHandler implements CommandLineDaemon {

  @Inject
  public RemoteDaemon(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  /** Unused because this is a daemon */
  @Override
  public void run(final String... arguments) throws Exception {}

  /** Run indefinitely */
  @Override
  public long getTimeout() {
    return 0;
  }

  @Override
  public TimeUnit getTimeoutUnit() {
    return null;
  }
}
