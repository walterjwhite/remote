package com.walterjwhite.remote.impl.plugins.printing;

import com.google.inject.AbstractModule;

public class PrintModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(PrintRequestMessageHandlerService.class);
  }
}
