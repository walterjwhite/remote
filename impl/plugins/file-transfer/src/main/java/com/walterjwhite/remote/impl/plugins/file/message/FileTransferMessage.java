package com.walterjwhite.remote.impl.plugins.file.message;

import com.walterjwhite.file.api.model.File;
import com.walterjwhite.remote.api.model.message.Message;
import javax.persistence.*;

/**
 * Used to send a file from machine A to machine B. machine A will upload the file to a 3rd party.
 * machine B will download the file from that 3rd party.
 */
@Entity
public class FileTransferMessage extends Message {
  @ManyToOne @JoinColumn protected File file;

  /** Absolute path where this file should be moved * */
  @Column protected String target;

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  @Override
  public String toString() {
    return "FileTransferMessage{"
        + "file="
        + file
        + ", target='"
        + target
        + '\''
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
