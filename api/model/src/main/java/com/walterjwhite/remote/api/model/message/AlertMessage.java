package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.remote.api.enumeration.AlertReason;
import com.walterjwhite.remote.api.model.Client;

/** Send an alert indicating this node may be compromised. */
public class AlertMessage extends Message {
  protected AlertReason alertReason;

  public AlertMessage() {
    super((Client) null, -1);
  }

  public AlertReason getAlertReason() {
    return alertReason;
  }

  public void setAlertReason(AlertReason alertReason) {
    this.alertReason = alertReason;
  }

  @Override
  public String toString() {
    return "AlertMessage{"
        + "alertReason="
        + alertReason
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
