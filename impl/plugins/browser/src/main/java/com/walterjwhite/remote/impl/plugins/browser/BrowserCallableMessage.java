package com.walterjwhite.remote.impl.plugins.browser;

import com.walterjwhite.browser.api.model.BrowserCallable;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BrowserCallableMessage extends Message {
  // browser manager class that remote controls the browser

  // or browser script, get, type, ...
  @ManyToOne @JoinColumn protected BrowserCallable browserCallable;

  public BrowserCallableMessage(
      Set<Client> recipients, int timeToLive, BrowserCallable browserCallable) {
    super(recipients, timeToLive);
    this.browserCallable = browserCallable;
  }

  public BrowserCallableMessage() {
    super();
  }

  public BrowserCallable getBrowserCallable() {
    return browserCallable;
  }

  public void setBrowserCallable(BrowserCallable browserCallable) {
    this.browserCallable = browserCallable;
  }

  @Override
  public String toString() {
    return "BrowserCallableMessage{"
        + "browserCallable="
        + browserCallable
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
