package com.pfeffer.springcleanarchtemplate.domain.entity;

import java.time.temporal.TemporalAccessor;

public interface NonDeletable<T extends TemporalAccessor> {

    T getDeletedAt();

}
