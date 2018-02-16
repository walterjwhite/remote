package com.walterjwhite.remote.impl.plugins.ssh;

import com.google.inject.AbstractModule;

public class SSHMessageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(SSHCommandMessageHandlerService.class);
    // TODO: add SFTP && SCP handler services
  }
}
