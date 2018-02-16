package com.walterjwhite.remote.impl.plugins.ssh;

import com.google.common.eventbus.Subscribe;
import com.walterjwhite.job.api.model.JobExecution;
import com.walterjwhite.queue.api.job.CallableJob;
import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.handler.AbstractMessageHandler;
import com.walterjwhite.ssh.api.SSHCommandService;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSHCommandMessageHandlerService extends AbstractMessageHandler
    implements CallableJob<SSHCommandMessage, Void> {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(SSHCommandMessageHandlerService.class);

  protected JobExecution jobExecution;
  protected SSHCommandMessage sshCommandMessage;
  protected final SSHCommandService sshCommandService;

  @Inject
  public SSHCommandMessageHandlerService(
      MessageWriterService messageWriterService, SSHCommandService sshCommandService) {
    super(messageWriterService);
    this.sshCommandService = sshCommandService;
  }

  @Subscribe
  public void process(SSHCommandMessage sshCommandMessage) throws Exception {}

  protected String getArguments(final SSHCommandMessage sshCommandMessage) {
    return (sshCommandMessage.getCommand().getShellCommand().getCommandLine());
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
  public void setEntity(SSHCommandMessage entity) {
    this.sshCommandMessage = entity;
  }

  @Override
  public SSHCommandMessage getEntity() {
    return sshCommandMessage;
  }

  @Override
  public Void call() throws Exception {
    LOGGER.debug(
        "running ssh command (host):" + sshCommandMessage.getCommand().getHost().getName());
    LOGGER.debug(
        "running ssh command (user):" + sshCommandMessage.getCommand().getUser().getName());
    LOGGER.debug(
        "running ssh command (command):"
            + sshCommandMessage.getCommand().getShellCommand().getCommandLine());
    sshCommandService.execute(sshCommandMessage.getCommand());
    return null;
  }
}
