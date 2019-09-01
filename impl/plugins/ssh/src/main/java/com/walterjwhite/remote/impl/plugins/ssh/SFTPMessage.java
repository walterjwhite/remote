package com.walterjwhite.remote.impl.plugins.ssh;

import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.ssh.api.model.sftp.SFTPTransfer;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Execute a sshCommand on a remote server. */
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
// @PersistenceCapable
@Entity
public class SFTPMessage extends Message {
  protected SFTPTransfer transfer;
}
