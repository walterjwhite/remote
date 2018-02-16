package com.walterjwhite.remote.impl.plugins.ssh;

import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.ssh.api.model.sftp.SFTPTransfer;
import javax.persistence.Entity;

/** Execute a sshCommand on a remote server. */
@Entity
public class SFTPMessage extends Message {
  /*@ManyToOne @JoinColumn*/ protected SFTPTransfer transfer;

  public SFTPTransfer getTransfer() {
    return transfer;
  }

  public void setTransfer(SFTPTransfer transfer) {
    this.transfer = transfer;
  }

  @Override
  public String toString() {
    return "SFTPMessage{"
        + "transfer="
        + transfer
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
