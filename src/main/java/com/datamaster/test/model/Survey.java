package com.datamaster.test.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="survey")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "start_date")
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate startDate;

    @Column(name = "expiry_date")
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate expiryDate;

    @Column(name="enable")
    private boolean enable;

    @OneToOne(mappedBy = "survey", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Question question;

    public Survey() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
