package com.walterjwhite.remote.impl.plugins.browser;

import com.walterjwhite.browser.api.model.BrowserScript;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import java.util.Set;
import javax.persistence.*;

@Entity
public class BrowserScriptMessage extends Message {
  // browser manager class that remote controls the browser

  // or browser script, get, type, ...
  @ManyToOne @JoinColumn protected BrowserScript browserScript;

  public BrowserScriptMessage(Set<Client> recipients, int timeToLive, BrowserScript browserScript) {
    super(recipients, timeToLive);
    this.browserScript = browserScript;
  }

  public BrowserScriptMessage() {
    super();
  }

  public BrowserScript getBrowserScript() {
    return browserScript;
  }

  public void setBrowserScript(BrowserScript browserScript) {
    this.browserScript = browserScript;
  }

  @Override
  public String toString() {
    return "BrowserScriptMessage{"
        + "browserScript="
        + browserScript
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
