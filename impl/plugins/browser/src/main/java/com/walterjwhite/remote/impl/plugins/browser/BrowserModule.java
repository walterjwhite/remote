package com.walterjwhite.remote.impl.plugins.browser;

import com.google.inject.AbstractModule;

public class BrowserModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(BrowserScriptMessageHandlerService.class);
  }
}
