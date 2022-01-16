package ru.ruben.crud.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "programminglanguage")
public class ProgrammingLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "language_name")
    private String languageName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dev_prog_lang",
        joinColumns = {@JoinColumn(name = "prog_lang_id")},
        inverseJoinColumns = {@JoinColumn(name = "developer_id")})
    private List<Developer> developers;

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}