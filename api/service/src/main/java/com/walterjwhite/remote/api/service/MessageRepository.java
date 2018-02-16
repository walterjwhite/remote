package com.walterjwhite.remote.api.service;

import com.walterjwhite.datastore.criteria.AbstractRepository;
import com.walterjwhite.datastore.criteria.CriteriaQueryConfiguration;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.remote.api.model.message.Message_;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

/** Wrapper around external queue service */
public class MessageRepository extends AbstractRepository<Message> {
  @Inject
  protected MessageRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
    super(entityManager, criteriaBuilder, Message.class);
  }

  public List<Message> findWithinThePastHour() {
    final CriteriaQueryConfiguration<Message> messageCriteriaQueryConfiguration =
        getCriteriaQuery();

    Predicate ageCondition =
        criteriaBuilder.greaterThanOrEqualTo(
            messageCriteriaQueryConfiguration.getRoot().get(Message_.dateSent),
            LocalDateTime.now().minusHours(1));

    messageCriteriaQueryConfiguration.getCriteriaQuery().where(ageCondition);
    return (entityManager
        .createQuery(messageCriteriaQueryConfiguration.getCriteriaQuery())
        .getResultList());
  }
}
