package com.walterjwhite.remote.impl.plugins.file.presend;

import com.google.common.eventbus.Subscribe;
import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.remote.impl.plugins.file.message.DeleteFileMessage;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteFilePreSendMessageListener {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(DeleteFilePreSendMessageListener.class);

  protected final FileStorageService fileStorageService;

  @Inject
  public DeleteFilePreSendMessageListener(FileStorageService fileStorageService) {
    super();
    this.fileStorageService = fileStorageService;
  }

  @Subscribe
  protected void process(DeleteFileMessage deleteFileMessage) throws Exception {
    fileStorageService.delete(deleteFileMessage.getFile());
  }
}
