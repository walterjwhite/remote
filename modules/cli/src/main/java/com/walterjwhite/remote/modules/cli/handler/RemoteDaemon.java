package com.walterjwhite.remote.modules.cli.handler;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractDaemonCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

/** Run in client mode (default operation). */
public class RemoteDaemon extends AbstractDaemonCommandLineHandler {

  @Inject
  public RemoteDaemon(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }
}
