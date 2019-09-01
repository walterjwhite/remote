package com.walterjwhite.remote.impl.plugins.browser;

import com.walterjwhite.browser.api.model.BrowserCallable;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import java.util.Set;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
// @PersistenceCapable
@Entity
public class BrowserCallableMessage extends Message {
  // browser manager class that remote controls the browser

  // or browser script, get, type, ...
  protected BrowserCallable browserCallable;

  public BrowserCallableMessage(
      Set<Client> recipients, int timeToLive, BrowserCallable browserCallable) {
    super(recipients, timeToLive);
    this.browserCallable = browserCallable;
  }

  public BrowserCallableMessage() {
    super();
  }
}
