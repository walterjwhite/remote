package com.walterjwhite.remote.impl.plugins.ssh;

import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.ssh.api.model.command.SSHExpectCommand;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Execute a sshCommand on a remote server. @NOTE: this requires (assumes) that public key
 * authentication is setup for the user. @NOTE: this should be unused because we should be running
 * the remote control API on that box (unless it is embedded).
 */
@Entity
public class SSHExpectCommandMessage extends Message {
  @ManyToOne @JoinColumn protected SSHExpectCommand command;

  public SSHExpectCommand getCommand() {
    return command;
  }

  public void setCommand(SSHExpectCommand command) {
    this.command = command;
  }

  @Override
  public String toString() {
    return "SSHExpectCommandMessage{"
        + "command="
        + command
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
