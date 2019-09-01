package com.walterjwhite.remote.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import com.walterjwhite.remote.api.enumeration.ProviderType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
// @PersistenceCapable
@Entity
public class Provider extends AbstractNamedEntity {
  @Column
  @Enumerated(EnumType.STRING)
  protected ProviderType providerType;

  public Provider(String moduleClassName, ProviderType providerType) {
    super(moduleClassName);
    this.providerType = providerType;
  }
}
