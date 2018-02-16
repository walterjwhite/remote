package com.walterjwhite.remote.plugins.shell;

import com.google.inject.AbstractModule;

public class ShellMessageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(CommandOutputMessageHandlerService.class);
    bind(ExecuteCommandMessageHandlerService.class);
    bind(ServiceMessageHandlerService.class);
    bind(SystemActionMessageHandlerService.class);
  }
}
