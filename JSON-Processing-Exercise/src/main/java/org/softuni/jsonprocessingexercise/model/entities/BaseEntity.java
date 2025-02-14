package org.softuni.jsonprocessingexercise.model.entities;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BaseEntity() {}

    protected Long getId() {
        return this.id;
    }

}
