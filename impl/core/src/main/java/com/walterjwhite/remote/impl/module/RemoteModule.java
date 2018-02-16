package com.walterjwhite.remote.impl.module;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.walterjwhite.compression.modules.CompressionModule;
import com.walterjwhite.datastore.GoogleGuicePersistModule;
import com.walterjwhite.datastore.criteria.CriteriaBuilderModule;
import com.walterjwhite.encryption.impl.EncryptionModule;
import com.walterjwhite.google.guice.GuavaEventBusModule;
import com.walterjwhite.job.api.model.QueueMonitor;
import com.walterjwhite.job.impl.QueueModule;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.service.ClientIdentifierService;
import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.DefaultMessageWriterService;
import com.walterjwhite.remote.impl.provider.ClientProvider;
import com.walterjwhite.remote.impl.provider.QueueMonitorProvider;
import com.walterjwhite.remote.impl.service.DefaultClientIdentifierService;
import com.walterjwhite.serialization.modules.java.KryoSerializationServiceModule;

public class RemoteModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Client.class).toProvider(ClientProvider.class); // .in(Singleton.class);
    bind(ClientIdentifierService.class).to(DefaultClientIdentifierService.class);
    bind(MessageWriterService.class).to(DefaultMessageWriterService.class);
    bind(QueueMonitor.class).toProvider(QueueMonitorProvider.class);

    // TODO: automatically install modules based on configuration
    install(new CriteriaBuilderModule());
    install(new GuavaEventBusModule());
    install(new QueueModule());

    install(new JpaPersistModule("defaultJPAUnit"));
    install(new GoogleGuicePersistModule());

    // install(new JacksonSerializationServiceModule());
    //    install(new NativeSerializationServiceModule());
    install(new KryoSerializationServiceModule());
    install(new CompressionModule());
    install(new EncryptionModule());
  }
}
