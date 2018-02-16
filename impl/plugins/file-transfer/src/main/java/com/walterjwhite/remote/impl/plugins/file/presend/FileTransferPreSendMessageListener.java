package com.walterjwhite.remote.impl.plugins.file.presend;

import com.google.common.eventbus.Subscribe;
import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.remote.impl.plugins.file.message.FileTransferMessage;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTransferPreSendMessageListener {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(FileTransferPreSendMessageListener.class);

  protected final FileStorageService fileStorageService;

  @Inject
  public FileTransferPreSendMessageListener(FileStorageService fileStorageService) {
    super();
    this.fileStorageService = fileStorageService;
  }

  /** Simply uploads (writes) the file to the file storage service. */
  @Subscribe
  public void process(FileTransferMessage fileTransferMessage)
      throws IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
          InvalidKeyException {
    LOGGER.debug("processing presend message:" + fileTransferMessage);
    LOGGER.debug("putting file:");
    fileStorageService.put(fileTransferMessage.getFile());

    LOGGER.debug("processed presend message:" + fileTransferMessage);
  }
}
