package com.walterjwhite.remote.impl.plugins.file.handler;

import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.queue.api.job.AbstractCallableJob;
import com.walterjwhite.remote.impl.plugins.file.message.FileTransferMessage;
import javax.inject.Inject;

public class FileTransferMessageHandlerService
    extends AbstractCallableJob<FileTransferMessage, Void> {
  protected final FileStorageService fileStorageService;

  @Inject
  public FileTransferMessageHandlerService(FileStorageService fileStorageService) {
    super();
    this.fileStorageService = fileStorageService;
  }

  protected void moveFile(FileTransferMessage fileTransferMessage) {
    final java.io.File target = new java.io.File(fileTransferMessage.getTarget());
    if (!target.exists() || target.isDirectory()) {
      final java.io.File source = new java.io.File(fileTransferMessage.getFile().getSource());
      source.renameTo(target);
    } else {
      throw (new IllegalStateException(
          "Target (" + target.getAbsolutePath() + ") already exists."));
    }
  }

  @Override
  protected boolean isRetryable(Throwable thrown) {
    return false;
  }

  @Override
  public Void call() throws Exception {
    fileStorageService.get(entity.getFile());
    moveFile(entity);

    return null;
  }
}
