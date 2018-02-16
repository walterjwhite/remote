package com.walterjwhite.remote.impl.plugins.email;

import com.walterjwhite.email.api.service.EmailSendService;
import com.walterjwhite.queue.api.job.AbstractCallableJob;
import java.net.ConnectException;
import javax.inject.Inject;

public class EmailMessageCallable extends AbstractCallableJob<EmailMessage, Void> {

  protected final EmailSendService emailSendService;

  @Inject
  public EmailMessageCallable(EmailSendService emailSendService) {
    super();
    this.emailSendService = emailSendService;
  }

  @Override
  protected boolean isRetryable(Throwable thrown) {
    return thrown instanceof ConnectException;
  }

  @Override
  public Void call() throws Exception {
    emailSendService.send(entity.getEmail());
    return (null);
  }
}
