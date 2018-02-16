package com.walterjwhite.remote.impl.service.configuration;

import java.util.HashMap;
import java.util.Map;

public class MonitorConfiguration {

  protected String name;
  protected String className;

  protected Map<String, String> properties = new HashMap<String, String>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, String> properties) {
    this.properties = properties;
  }
}
