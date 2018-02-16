package com.walterjwhite.remote.impl.plugins.printing;

import com.walterjwhite.queue.api.job.AbstractCallableJob;
import javax.inject.Inject;

public class PrintRequestMessageHandlerService
    extends AbstractCallableJob<PrintRequestMessage, Void> {
  //  protected final PrinterService printerService;

  @Inject
  public PrintRequestMessageHandlerService(/*PrinterService printerService*/ ) {
    super();
    //    this.printerService = printerService;
  }

  @Override
  protected boolean isRetryable(Throwable thrown) {
    return false;
  }

  @Override
  public Void call() throws Exception {
    // TODO: is this automatically handle or do we simply need to take the entity.getPrintRequest()
    // and put that into the queue?
    //    printerService.print(entity.getPrintRequest());
    return null;
  }
}
