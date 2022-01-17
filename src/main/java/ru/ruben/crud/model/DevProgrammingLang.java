package ru.ruben.crud.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dev_prog_lang")
public class DevProgrammingLang {
    @EmbeddedId
    private DevProgrammingLangId id;

    public DevProgrammingLangId getId() {
        return id;
    }

    public void setId(DevProgrammingLangId id) {
        this.id = id;
    }
}