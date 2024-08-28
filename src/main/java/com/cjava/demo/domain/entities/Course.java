package com.cjava.demo.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty
    private String name;
    @Column(name = "credits")
    @Max(5)
    @Min(0)
    private int credits;

    public Course() {
    }

    public Course(int id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int creditos) {
        this.credits = creditos;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + name + '\'' +
                ", creditos=" + credits +
                '}';
    }
}
