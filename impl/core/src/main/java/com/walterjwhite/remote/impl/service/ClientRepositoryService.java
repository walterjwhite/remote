package com.walterjwhite.remote.impl.service;

import com.walterjwhite.remote.api.model.Client;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Singleton;

// this stores state and only should have a single instance ...
@Singleton
public class ClientRepositoryService {
  protected Set<Client> clients = new HashSet<Client>();

  public Set<Client> getClients() {
    return clients;
  }

  public void setClients(Set<Client> clients) {
    this.clients = clients;
  }
}
