package com.roberto.CochesMicroService;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="coche")//Nombre de la Coleccion
public class Coche {

    @Id
    public String id;

    public String numeroMatricula;

    public String marca;

    public String modelo;

    public Coche() {
    }

    public Coche(String numeroMatricula, String marca, String modelo) {
        this.numeroMatricula = numeroMatricula;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
