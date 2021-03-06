package com.walterjwhite.remote.modules.cli.enumeration;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.remote.modules.cli.handler.ListNodes;
import com.walterjwhite.remote.modules.cli.handler.MessageReader;
import com.walterjwhite.remote.modules.cli.handler.MessageWriter;
import com.walterjwhite.remote.modules.cli.handler.RemoteDaemon;

// TODO: while this works, this is a bad practice.
// I would like to automatically start any services that are needed
public enum RemoteOperatingMode implements OperatingMode {
  @DefaultValue
  Daemon("Run in the background as a service (default)", RemoteDaemon.class),
  Send("Send a message(s)", MessageWriter.class),
  Read("Read message(s)", MessageReader.class),
  List("List online nodes and statuses", ListNodes.class);

  private final String description;
  private final Class<? extends AbstractCommandLineHandler> initiatorClass;

  RemoteOperatingMode(
      String description, Class<? extends AbstractCommandLineHandler> initiatorClass) {
    this.description = description;
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends AbstractCommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
