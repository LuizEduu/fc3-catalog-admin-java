package com.luizeduu.admin.catalog.domain;

import com.luizeduu.admin.catalog.domain.validation.ValidationHandler;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

  protected AggregateRoot(final ID id) {
    super(id);
  }
}
