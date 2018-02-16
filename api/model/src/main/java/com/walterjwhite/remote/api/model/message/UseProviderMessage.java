package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.remote.api.model.Provider;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UseProviderMessage extends Message {
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected Provider provider;

  public UseProviderMessage(Provider provider) {
    super();
    this.provider = provider;
  }

  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }
}
