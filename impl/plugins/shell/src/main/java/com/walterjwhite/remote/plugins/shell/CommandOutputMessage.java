package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.serialization.api.annotation.PrivateField;
import java.util.Set;
import javax.persistence.*;

@Entity
public class CommandOutputMessage extends Message {
  @OneToOne @JoinColumn @PrivateField protected ExecuteCommandMessage executeCommandMessage;

  @Column protected String stdout;
  @Column protected String stderr;
  @Column protected int returnCode;

  public CommandOutputMessage(
      Set<Client> recipients,
      int timeToLive,
      ExecuteCommandMessage executeCommandMessage,
      String stdout,
      String stderr,
      int returnCode) {
    super(recipients, timeToLive);
    this.executeCommandMessage = executeCommandMessage;
    this.stdout = stdout;
    this.stderr = stderr;
    this.returnCode = returnCode;
  }

  public CommandOutputMessage(
      Client recipient,
      int timeToLive,
      ExecuteCommandMessage executeCommandMessage,
      String stdout,
      String stderr,
      int returnCode) {
    super(recipient, timeToLive);
    this.executeCommandMessage = executeCommandMessage;
    this.stdout = stdout;
    this.stderr = stderr;
    this.returnCode = returnCode;
  }

  /** Required for deserialization. */
  public CommandOutputMessage() {
    super();
  }

  public ExecuteCommandMessage getExecuteCommandMessage() {
    return executeCommandMessage;
  }

  public void setExecuteCommandMessage(ExecuteCommandMessage executeCommandMessage) {
    this.executeCommandMessage = executeCommandMessage;
  }

  public String getStdout() {
    return stdout;
  }

  public void setStdout(String stdout) {
    this.stdout = stdout;
  }

  public String getStderr() {
    return stderr;
  }

  public void setStderr(String stderr) {
    this.stderr = stderr;
  }

  public int getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(int returnCode) {
    this.returnCode = returnCode;
  }

  @Override
  public String toString() {
    return "CommandOutputMessage{"
        + "executeCommandMessage="
        + executeCommandMessage
        + ", stdout='"
        + stdout
        + '\''
        + ", stderr='"
        + stderr
        + '\''
        + ", returnCode="
        + returnCode
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
