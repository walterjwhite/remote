package com.walterjwhite.remote.impl.provider;

import com.google.inject.persist.Transactional;
import com.walterjwhite.encryption.api.service.DigestService;
import com.walterjwhite.encryption.impl.RuntimeEncryptionConfiguration;
import com.walterjwhite.ip.api.service.PublicIPLookupService;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.ClientIPAddressHistory;
import com.walterjwhite.remote.api.service.ClientIdentifierService;
import com.walterjwhite.remote.impl.repository.ClientRepository;
import com.walterjwhite.shell.api.model.IPAddress;
import com.walterjwhite.shell.api.model.Node;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** This is the local client (self) */
@Singleton
public class ClientProvider implements Provider<Client> {
  private static final Logger LOGGER = LoggerFactory.getLogger(ClientProvider.class);

  protected final Client client;
  protected final Node node;
  protected final Provider<ClientRepository> clientRepositoryProvider;
  protected final PublicIPLookupService publicIPLookupService;
  protected final DigestService digestService;
  protected final ClientIdentifierService clientIdentifierService;
  protected final RuntimeEncryptionConfiguration runtimeEncryptionConfiguration;

  @Inject
  public ClientProvider(
      final RuntimeEncryptionConfiguration runtimeEncryptionConfiguration,
      Node node,
      Provider<ClientRepository> clientRepositoryProvider,
      final PublicIPLookupService publicIPLookupService,
      DigestService digestService,
      ClientIdentifierService clientIdentifierService)
      throws Exception {
    super();
    this.node = node;

    this.clientRepositoryProvider = clientRepositoryProvider;
    this.digestService = digestService;
    this.clientIdentifierService = clientIdentifierService;

    this.publicIPLookupService = publicIPLookupService;
    this.runtimeEncryptionConfiguration = runtimeEncryptionConfiguration;

    this.client = getClient();
  }

  protected Client getClient() throws Exception {
    final String id = clientIdentifierService.get();
    try {
      return (clientRepositoryProvider.get().findById(id));
    } catch (NoResultException e) {
      return (createClient(id));
    }
  }

  @Transactional
  protected Client createClient(final String id) {
    Client client = new Client(node, id);

    try {
      client
          .getClientIPAddressHistoryList()
          .add(
              new ClientIPAddressHistory(
                  client, new IPAddress(publicIPLookupService.getPublicIPAddress())));

      LOGGER.info(
          "public IP:"
              + client
                  .getClientIPAddressHistoryList()
                  .get(client.getClientIPAddressHistoryList().size() - 1));
    } catch (Exception e) {
      throw (new RuntimeException("Unable to set IP Address history record", e));
    }

    if (client.getInitializationVector() == null) {
      client.setInitializationVector(runtimeEncryptionConfiguration.getIvData());
    }

    clientRepositoryProvider.get().persist(client);
    return (client);
  }

  @Override
  public Client get() {
    //    clientRepositoryProvider.get().refresh(client);

    return (client);
  }
}
