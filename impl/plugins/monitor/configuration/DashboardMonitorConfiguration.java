package com.walterjwhite.remote.impl.service.configuration;

import java.util.ArrayList;
import java.util.List;

public class DashboardMonitorConfiguration {

  protected List<MonitorGroupConfiguration> monitors = new ArrayList<MonitorGroupConfiguration>();

  public List<MonitorGroupConfiguration> getMonitors() {
    return monitors;
  }

  public void setMonitors(List<MonitorGroupConfiguration> monitors) {
    this.monitors = monitors;
  }
}
