package com.pfeffer.springcleanarchtemplate.infra.database.postgres.model;

import com.pfeffer.springcleanarchtemplate.domain.entity.NonDeletable;
import jakarta.persistence.Column;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDateTime;

public abstract class PgAuditableNonDeletableEntity
        extends PgAuditableEntity
        implements NonDeletable<LocalDateTime> {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Override
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @PreRemove
    public void preRemove() {
        throw new UnsupportedOperationException("This entity cannot be deleted.");
    }

}
