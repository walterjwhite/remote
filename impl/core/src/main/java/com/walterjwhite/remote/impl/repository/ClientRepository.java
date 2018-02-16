package com.walterjwhite.remote.impl.repository;

import com.walterjwhite.datastore.criteria.AbstractRepository;
import com.walterjwhite.datastore.criteria.CriteriaQueryConfiguration;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.Client_;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class ClientRepository extends AbstractRepository<Client> {
  @Inject
  public ClientRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
    super(entityManager, criteriaBuilder, Client.class);
  }

  public Client findById(final String id) {
    final CriteriaQueryConfiguration<Client> clientCriteriaQueryConfiguration = getCriteriaQuery();
    // find pending jobs
    Predicate condition =
        criteriaBuilder.equal(clientCriteriaQueryConfiguration.getRoot().get(Client_.id), id);
    clientCriteriaQueryConfiguration.getCriteriaQuery().where(condition);

    return (entityManager
        .createQuery(clientCriteriaQueryConfiguration.getCriteriaQuery())
        .getSingleResult());
  }

  //  public Client findByIdOrCreate(final String id) {
  //    final CriteriaQueryConfiguration<Client> clientCriteriaQueryConfiguration =
  // getCriteriaQuery();
  //    // find pending jobs
  //    Predicate condition =
  //
  // criteriaBuilder.equal(clientCriteriaQueryConfiguration.getRoot().get(Client_.hashedIdentifier),
  // id);
  //    clientCriteriaQueryConfiguration.getCriteriaQuery().where(condition);
  //
  //    try {
  //      return (entityManager
  //          .createQuery(clientCriteriaQueryConfiguration.getCriteriaQuery())
  //          .getSingleResult());
  //    } catch (NoResultException e) {
  //      return(persist(new Client(id)));
  //    }
  //  }
}
