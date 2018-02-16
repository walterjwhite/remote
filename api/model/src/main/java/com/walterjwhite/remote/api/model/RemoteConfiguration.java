// package com.walterjwhite.remote.api.model;
//
// import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
// import javax.persistence.Column;
// import javax.persistence.EmbeddedId;
// import javax.persistence.Entity;
//
// @Entity
// public class RemoteConfiguration extends AbstractEntity<RemoteConfigurationKey> {
//  @EmbeddedId protected RemoteConfigurationKey id;
//
//  /** Do not perform any operations, just log. */
//  @Column protected boolean noOp;
//
//  public RemoteConfiguration(boolean noOp) {
//    super();
//    this.noOp = noOp;
//  }
//
//  public RemoteConfiguration() {
//    super();
//  }
//
//  @Override
//  public RemoteConfigurationKey getId() {
//    return id;
//  }
//
//  @Override
//  public void setId(RemoteConfigurationKey id) {
//    this.id = id;
//  }
//
//  public boolean isNoOp() {
//    return noOp;
//  }
//
//  public void setNoOp(boolean noOp) {
//    this.noOp = noOp;
//  }
// }
