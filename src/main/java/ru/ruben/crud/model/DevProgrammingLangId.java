package ru.ruben.crud.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DevProgrammingLangId implements Serializable {
    private static final long serialVersionUID = -4548802584120272096L;
    @Column(name = "developer_id", nullable = false)
    private Integer developerId;
    @Column(name = "prog_lang_id", nullable = false)
    private Integer progLangId;

    public Integer getProgLangId() {
        return progLangId;
    }

    public void setProgLangId(Integer progLangId) {
        this.progLangId = progLangId;
    }

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(developerId, progLangId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DevProgrammingLangId entity = (DevProgrammingLangId) o;
        return Objects.equals(this.developerId, entity.developerId) &&
                Objects.equals(this.progLangId, entity.progLangId);
    }
}