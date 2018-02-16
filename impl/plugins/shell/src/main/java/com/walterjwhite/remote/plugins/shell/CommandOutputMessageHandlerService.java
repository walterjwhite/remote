package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.job.api.model.JobExecution;
import com.walterjwhite.queue.api.job.CallableJob;
import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.handler.AbstractMessageHandler;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Simply logs the command output message to the configured logger. */
public class CommandOutputMessageHandlerService extends AbstractMessageHandler
    implements CallableJob<CommandOutputMessage, Void> {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(CommandOutputMessageHandlerService.class);

  protected CommandOutputMessage commandOutputMessage;
  protected JobExecution jobExecution;

  @Inject
  public CommandOutputMessageHandlerService(MessageWriterService messageWriterService) {
    super(messageWriterService);
  }

  @Override
  public void onSuccess() {}

  @Override
  public void onError(Throwable thrown) {}

  @Override
  public void setJobExecution(JobExecution jobExecution) {
    this.jobExecution = jobExecution;
  }

  @Override
  public void setEntity(CommandOutputMessage entity) {
    this.commandOutputMessage = entity;
  }

  @Override
  public CommandOutputMessage getEntity() {
    return commandOutputMessage;
  }

  @Override
  public Void call() throws Exception {
    LOGGER.warn("processing commandOutputMessage:" + commandOutputMessage);

    LOGGER.info("received command output (stdout):" + commandOutputMessage.getStdout());
    LOGGER.info("received command output (stderr):" + commandOutputMessage.getStderr());
    LOGGER.info("received command output (returnCode):" + commandOutputMessage.getReturnCode());

    return null;
  }
}
