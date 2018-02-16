package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import java.util.Map;
import java.util.Set;
import javax.persistence.*;

@Entity
public class ExecuteCommandMessage extends Message {
  // used to set environmental properties
  /*@ManyToMany @JoinTable*/ @Transient protected Map<String, String> environmentMap;

  // used to set the user to run a command as
  @Column protected String runAs;

  @Column protected String command;
  // -1 for long-running, time in seconds to let the command run before killing it
  @Column protected int timeout;

  // whether or not to log the return code and output / stderr
  @Column protected boolean captureResponse;

  @OneToOne protected CommandOutputMessage commandOutputMessage;

  public ExecuteCommandMessage(
      Set<Client> recipients,
      int timeToLive,
      String command,
      int timeout,
      boolean captureResponse) {
    super(recipients, timeToLive);
    this.command = command;
    this.timeout = timeout;
    this.captureResponse = captureResponse;
  }

  public ExecuteCommandMessage(
      Client recipient, int timeToLive, String command, int timeout, boolean captureResponse) {
    super(recipient, timeToLive);
    this.command = command;
    this.timeout = timeout;
    this.captureResponse = captureResponse;
  }

  /** Required for deserialization. */
  public ExecuteCommandMessage() {
    super();
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  public boolean isCaptureResponse() {
    return captureResponse;
  }

  public void setCaptureResponse(boolean captureResponse) {
    this.captureResponse = captureResponse;
  }

  public Map<String, String> getEnvironmentMap() {
    return environmentMap;
  }

  public void setEnvironmentMap(Map<String, String> environmentMap) {
    this.environmentMap = environmentMap;
  }

  public String getRunAs() {
    return runAs;
  }

  public void setRunAs(String runAs) {
    this.runAs = runAs;
  }

  public CommandOutputMessage getCommandOutputMessage() {
    return commandOutputMessage;
  }

  public void setCommandOutputMessage(CommandOutputMessage commandOutputMessage) {
    this.commandOutputMessage = commandOutputMessage;
  }

  @Override
  public String toString() {
    return "ExecuteCommandMessage{"
        + "environmentMap="
        + environmentMap
        + ", runAs='"
        + runAs
        + '\''
        + ", command='"
        + command
        + '\''
        + ", timeout="
        + timeout
        + ", captureResponse="
        + captureResponse
        + ", recipients="
        + recipients
        + ", sender="
        + sender
        + ", dateCreated="
        + dateCreated
        + ", dateSent="
        + dateSent
        + ", timeToLive="
        + timeToLive
        + ", token='"
        + token
        + '\''
        + '}';
  }
}
