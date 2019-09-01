package com.walterjwhite.remote.impl.plugins.email;

import com.walterjwhite.remote.api.model.message.Message;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
// @PersistenceCapable
@Entity
public class EmailMessage extends Message {}
