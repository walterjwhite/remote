package com.walterjwhite.remote.impl.plugins.heartbeat;

import com.walterjwhite.google.guice.property.property.DefaultValue;
import com.walterjwhite.google.guice.property.property.GuiceProperty;

public interface HeartbeatMaximumInterval extends GuiceProperty {
  @DefaultValue int Default = 60;
}
