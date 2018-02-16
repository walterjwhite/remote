package com.walterjwhite.remote.impl.plugins.printing;

import com.walterjwhite.print.model.PrintRequest;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PrintRequestMessage extends Message {
  @ManyToOne @JoinColumn protected PrintRequest printRequest;

  public PrintRequestMessage(Set<Client> recipients, int timeToLive, PrintRequest printRequest) {
    super(recipients, timeToLive);
    this.printRequest = printRequest;
  }

  public PrintRequestMessage(Client recipient, int timeToLive, PrintRequest printRequest) {
    super(recipient, timeToLive);
    this.printRequest = printRequest;
  }

  public PrintRequestMessage() {
    super();
  }

  public PrintRequest getPrintRequest() {
    return printRequest;
  }

  public void setPrintRequest(PrintRequest printRequest) {
    this.printRequest = printRequest;
  }

  @Override
  public String toString() {
    return "PrintRequestMessage{"
        + "printRequest="
        + printRequest
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
