package com.uniovi.sdi2122212spring.entities;

public class Teacher {


    private Long Id;
    private String Dni;
    private String Nombre;
    private String Apellidos;
    private String Categoría;

    public Teacher(Long Id, String dni, String nombre, String apellidos, String categoría) {
        this.Id = Id;
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
                "Id=" + Id +
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
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
