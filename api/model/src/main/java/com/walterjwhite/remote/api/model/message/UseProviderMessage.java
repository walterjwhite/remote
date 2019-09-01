package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.remote.api.model.Provider;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
// @PersistenceCapable
@AllArgsConstructor
@Entity
public class UseProviderMessage extends Message {
  protected Provider provider;
}
