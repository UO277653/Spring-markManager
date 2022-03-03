package com.uniovi.sdi2122212spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Professor {
    @Id
    @GeneratedValue
    private Long id;
    private String dni;
    private String name;
    private String surname;
    private String category;

    public Professor(Long Id, String dni, String nombre, String apellidos, String categoría) {
        this.id = Id;
        this.dni = dni;
        name = nombre;
        surname = apellidos;
        category = categoría;
    }

    public Professor() { }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "Id=" + id +
                ", Dni='" + dni + '\'' +
                ", Nombre='" + name + '\'' +
                ", Apellidos='" + surname + '\'' +
                ", Categoría='" + category + '\'' +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
