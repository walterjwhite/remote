package com.walterjwhite.remote.impl.plugins.browser;

import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.queue.api.job.AbstractRunnable;
import com.walterjwhite.queue.event.annotation.SubscribeTo;

@SubscribeTo(eventClass = BrowserScriptMessage.class)
public class BrowserScriptMessageHandlerService extends AbstractRunnable {
  protected final BrowserService browserService;

  public BrowserScriptMessageHandlerService(BrowserService browserService) {
    super();
    this.browserService = browserService;
  }

  @Override
  protected void doCall() throws Exception {
    //    //browserService.get();

  }
}
