package com.walterjwhite.remote.impl.plugins.email;

import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.remote.api.model.message.Message;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmailMessage extends Message {
  @ManyToOne @JoinColumn protected Email email;

  public Email getEmail() {
    return email;
  }

  public void setEmail(Email email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "EmailMessage{"
        + "email="
        + email
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
