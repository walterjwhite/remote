package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.remote.api.enumeration.AlertReason;
import com.walterjwhite.remote.api.model.Client;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;
import lombok.ToString;

/** Send an alert indicating this node may be compromised. */
@ToString(doNotUseGetters = true, callSuper = true)
@Data
@Entity
public class AlertMessage extends Message {
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  protected AlertReason alertReason;

  public AlertMessage() {
    super((Client) null, -1);
  }
}
