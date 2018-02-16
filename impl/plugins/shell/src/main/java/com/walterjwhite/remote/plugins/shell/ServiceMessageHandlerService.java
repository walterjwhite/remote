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

public class ServiceMessageHandlerService extends AbstractMessageHandler
    implements CallableJob<ServiceMessage, Void> {
  private static final Logger LOGGER = LoggerFactory.getLogger(ServiceMessageHandlerService.class);

  protected final ShellExecutionService shellExecutionService;

  protected JobExecution jobExecution;
  protected ServiceMessage serviceMessage;

  @Inject
  public ServiceMessageHandlerService(
      MessageWriterService messageWriterService, ShellExecutionService shellExecutionService) {
    super(messageWriterService);
    this.shellExecutionService = shellExecutionService;
  }

  protected String getArguments(final ServiceMessage serviceMessage) {
    return ("sudo service "
        + serviceMessage.getService().getName()
        + " "
        + serviceMessage.getServiceAction().getCommand());
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
  public void setEntity(ServiceMessage entity) {
    this.serviceMessage = entity;
  }

  @Override
  public ServiceMessage getEntity() {
    return serviceMessage;
  }

  @Override
  public Void call() throws Exception {
    try {
      ShellCommand shellCommand =
          new ShellCommand().withCommandLine(getArguments(serviceMessage)).withTimeout(10);
      shellExecutionService.run(shellCommand);
      for (CommandOutput commandOutput : shellCommand.getOutputs()) {
        LOGGER.debug("response (output):" + commandOutput.getOutput());
      }
      for (CommandError commandOutput : shellCommand.getErrors()) {
        LOGGER.debug("response (error):" + commandOutput.getOutput());
      }
      LOGGER.debug("response (returnCode):" + shellCommand.getReturnCode());
    } catch (Exception e) {
      LOGGER.error("Error occurred during execution", e);
      throw (e);
    }

    return null;
  }
}
