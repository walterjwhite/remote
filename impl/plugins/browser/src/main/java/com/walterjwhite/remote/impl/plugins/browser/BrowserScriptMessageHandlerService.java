package com.walterjwhite.remote.impl.plugins.browser;

import com.walterjwhite.browser.api.service.BrowserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserScriptMessageHandlerService {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(BrowserScriptMessageHandlerService.class);

  protected final BrowserService browserService;

  public BrowserScriptMessageHandlerService(BrowserService browserService) {
    super();
    this.browserService = browserService;
  }
  //
  //  @Subscribe
  //  public void process(BrowserScriptMessage browserScriptMessage) {
  //    //browserService.get();
  //  }
}
