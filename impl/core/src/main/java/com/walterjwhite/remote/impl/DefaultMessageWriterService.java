package com.walterjwhite.remote.impl;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;
import com.walterjwhite.job.api.enumeration.QueueType;
import com.walterjwhite.job.api.model.Queue;
import com.walterjwhite.job.external.queue.api.ExternalQueueService;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.remote.api.service.MessageRepository;
import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.provider.ClientProvider;
import javax.inject.Inject;
import javax.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMessageWriterService implements MessageWriterService {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageWriterService.class);

  protected final ExternalQueueService externalQueueService;
  protected final EventBus eventBus;
  protected final Provider<MessageRepository> messageRepositoryProvider;
  protected final ClientProvider clientProvider;

  @Inject
  public DefaultMessageWriterService(
      ExternalQueueService externalQueueService,
      EventBus eventBus,
      Provider<MessageRepository> messageRepositoryProvider,
      ClientProvider clientProvider) {
    super();
    this.externalQueueService = externalQueueService;
    this.eventBus = eventBus;
    this.messageRepositoryProvider = messageRepositoryProvider;
    this.clientProvider = clientProvider;
  }

  @Override
  public void write(Message message, Client... clients) throws Exception {
    prepareMessage(message, clients);
    message = doPersist(message);
    doPreprocess(message);
    doWriteToQueue(message, clients);
  }

  protected void prepareMessage(Message message, Client... clients) {
    message.setSender(clientProvider.get());
    // message.getRecipients().addAll(clients);
    for (Client client : clients) message.getRecipients().add(client);
  }

  protected void doPreprocess(Message message) {
    // push this to the event bus to do any necessary preprocessing
    eventBus.post(message);
  }

  protected void doWriteToQueue(Message message, Client... clients) throws Exception {
    if (clients == null || clients.length == 0) {
      Queue queue = new Queue("all", QueueType.All);
      externalQueueService.write(queue, message);
    }

    for (Client client : clients) {
      Queue queue = new Queue(client.getId(), QueueType.Self);
      externalQueueService.write(queue, message);
    }
  }

  @Transactional
  protected Message doPersist(Message message) {
    //    messageRepositoryProvider.get().persist(message);
    return (messageRepositoryProvider.get().merge(message));
  }
}
