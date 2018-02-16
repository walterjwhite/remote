package com.walterjwhite.remote.modules.cli.module;

// import com.walterjwhite.remote.impl.plugins.email.EmailModule;

import com.walterjwhite.file.providers.amazon.service.AmazonS3FileStorageModule;
import com.walterjwhite.google.guice.cli.AbstractCommandLineModule;
import com.walterjwhite.ip.impl.PublicIPLookupModule;
import com.walterjwhite.queue.providers.amazon.sqs.service.AmazonSQSQueueModule;
import com.walterjwhite.remote.impl.module.RemoteModule;
import com.walterjwhite.remote.impl.plugins.file.FileTransferModule;
import com.walterjwhite.remote.impl.plugins.heartbeat.HeartbeatModule;
import com.walterjwhite.remote.impl.plugins.ssh.SSHMessageModule;
import com.walterjwhite.remote.modules.cli.enumeration.RemoteOperatingMode;
import com.walterjwhite.remote.plugins.shell.ShellMessageModule;
import com.walterjwhite.shell.impl.ShellModule;
import com.walterjwhite.ssh.impl.SSHModule;
import org.reflections.Reflections;

public class RemoteCommandLineModule extends AbstractCommandLineModule {

  public RemoteCommandLineModule(Reflections reflections) {
    super(reflections, RemoteOperatingMode.class);
  }

  /**
   * FYI, at this point, properties should be injected, so we can do a dynamic configuration here
   * ...
   */
  @Override
  protected void doConfigure() {
    // TODO: automatically install modules based on configuration
    // TODO: if operating mode is daemon, then install normal job queue
    // else, install no-op job queue

    // TODO: use command line module to set jpaUnit
    install(new RemoteModule());
    install(new PublicIPLookupModule());
    install(new ShellModule());

    // remote handlers / plugins
    install(new SSHModule());
    install(new SSHMessageModule());
    install(new ShellMessageModule());
    install(new HeartbeatModule());
    install(new FileTransferModule());

    //    install(new EmailModule());
    install(new AmazonS3FileStorageModule());
    install(new AmazonSQSQueueModule());
  }
}
