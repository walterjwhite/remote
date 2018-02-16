package com.walterjwhite.remote.impl.service.configuration;

public class DatabaseConfiguration {

  protected String databaseUri;
  protected String databaseUsername;
  protected String databasePassword;
  protected String databaseDriver;

  public String getDatabaseUri() {
    return databaseUri;
  }

  public void setDatabaseUri(String databaseUri) {
    this.databaseUri = databaseUri;
  }

  public String getDatabaseUsername() {
    return databaseUsername;
  }

  public void setDatabaseUsername(String databaseUsername) {
    this.databaseUsername = databaseUsername;
  }

  public String getDatabasePassword() {
    return databasePassword;
  }

  public void setDatabasePassword(String databasePassword) {
    this.databasePassword = databasePassword;
  }

  public String getDatabaseDriver() {
    return databaseDriver;
  }

  public void setDatabaseDriver(String databaseDriver) {
    this.databaseDriver = databaseDriver;
  }
}
