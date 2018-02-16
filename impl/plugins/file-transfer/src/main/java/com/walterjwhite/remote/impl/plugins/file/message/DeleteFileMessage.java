package com.walterjwhite.remote.impl.plugins.file.message;

import com.walterjwhite.file.api.model.File;
import com.walterjwhite.remote.api.model.message.Message;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/** Used to delete a specific file from the machine. */
@Entity
public class DeleteFileMessage extends Message {
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
    return "DeleteFileMessage{"
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
