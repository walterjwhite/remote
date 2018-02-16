package com.walterjwhite.remote.impl.provider;

import com.walterjwhite.job.api.enumeration.QueueType;
import com.walterjwhite.job.api.model.Queue;
import com.walterjwhite.job.api.model.QueueMonitor;
import com.walterjwhite.remote.api.model.Client;
import javax.inject.Inject;
import javax.inject.Provider;

public class QueueMonitorProvider implements Provider<QueueMonitor> {
  protected final Client client;
  protected final QueueMonitor queueMonitor;

  @Inject
  public QueueMonitorProvider(Client client) {
    super();
    this.client = client;

    Queue allQueue = new Queue("all", QueueType.All);
    Queue selfQueue = new Queue(client.getId(), QueueType.Self);

    queueMonitor = new QueueMonitor(client.getId());
    queueMonitor.getQueues().add(allQueue);
    queueMonitor.getQueues().add(selfQueue);
  }

  @Override
  public QueueMonitor get() {
    return queueMonitor;
  }
}
