package com.uniovi.sdi2122212spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;
    private String Dni;
    private String Nombre;
    private String Apellidos;
    private String Categoría;

    public Teacher(Long Id, String dni, String nombre, String apellidos, String categoría) {
        this.id = Id;
        Dni = dni;
        Nombre = nombre;
        Apellidos = apellidos;
        Categoría = categoría;
    }

    public Teacher () { }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "Id=" + id +
                ", Dni='" + Dni + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Categoría='" + Categoría + '\'' +
                '}';
    }

    public String getCategoría() {
        return Categoría;
    }

    public void setCategoría(String categoría) {
        Categoría = categoría;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
