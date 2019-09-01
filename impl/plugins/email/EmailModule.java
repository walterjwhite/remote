package com.walterjwhite.remote.impl.plugins.email;

import com.google.inject.AbstractModule;
import com.walterjwhite.email.modules.javamail.JavaMailEmailModule;

public class EmailModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EmailMessageCallable.class);

    install(new JavaMailEmailModule());
  }
}
