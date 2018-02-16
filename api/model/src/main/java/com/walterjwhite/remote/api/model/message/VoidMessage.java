package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.remote.api.model.Client;

/** Created by walterjwhite on 11/24/16. */
public class VoidMessage extends Message {
  private VoidMessage() {
    super((Client) null, -1);
  }
}
