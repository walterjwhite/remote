package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.job.api.model.JobExecution;
import com.walterjwhite.queue.api.job.CallableJob;
import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.handler.AbstractMessageHandler;
import com.walterjwhite.shell.api.model.CommandError;
import com.walterjwhite.shell.api.model.CommandOutput;
import com.walterjwhite.shell.api.model.ShellCommand;
import com.walterjwhite.shell.api.service.ShellExecutionService;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemActionMessageHandlerService extends AbstractMessageHandler
    implements CallableJob<SystemActionMessage, Void> {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(SystemActionMessageHandlerService.class);

  protected final ShellExecutionService shellExecutionService;

  protected SystemActionMessage systemActionMessage;

  @Inject
  public SystemActionMessageHandlerService(
      MessageWriterService messageWriterService, ShellExecutionService shellExecutionService) {
    super(messageWriterService);
    this.shellExecutionService = shellExecutionService;
  }

  @Override
  public void onSuccess() {}

  @Override
  public void onError(Throwable thrown) {}

  @Override
  public void setJobExecution(JobExecution jobExecution) {}

  @Override
  public void setEntity(SystemActionMessage entity) {
    this.systemActionMessage = entity;
  }

  @Override
  public SystemActionMessage getEntity() {
    return systemActionMessage;
  }

  @Override
  public Void call() throws Exception {
    ShellCommand shellCommand =
        new ShellCommand()
            .withCommandLine("sudo " + systemActionMessage.getSystemAction())
            .withTimeout(10);
    shellExecutionService.run(shellCommand);

    for (CommandOutput commandOutput : shellCommand.getOutputs()) {
      LOGGER.debug("response (output):" + commandOutput.getOutput());
    }
    for (CommandError commandOutput : shellCommand.getErrors()) {
      LOGGER.debug("response (error):" + commandOutput.getOutput());
    }
    LOGGER.debug("response (returnCode):" + shellCommand.getReturnCode());
    return (null);
  }
}
