package com.walterjwhite.remote.impl.service.configuration;

import com.walterjwhite.remote.impl.service.monitor.AbstractMonitor;
import java.util.ArrayList;
import java.util.List;

public class MonitorGroupConfiguration {

  protected String name;

  protected List<MonitorConfiguration> configurations = new ArrayList<>();

  protected List<AbstractMonitor> monitors = new ArrayList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<MonitorConfiguration> getConfigurations() {
    return configurations;
  }

  public void setConfigurations(List<MonitorConfiguration> configurations) {
    this.configurations = configurations;
  }

  public List<AbstractMonitor> getMonitors() {
    return monitors;
  }

  public void setMonitors(List<AbstractMonitor> monitors) {
    this.monitors = monitors;
  }
}
