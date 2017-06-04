/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Usuario
 */
public class Campo implements Comparable<Campo>{
    private Character caracter;
    private Integer peso;

    public Campo(Character caracter, Integer peso) {
        this.caracter = caracter;
        this.peso = peso;
    }

    public Campo(Character caracter) {
        this.caracter = caracter;
    }

    public Campo(Integer peso) {
        this.peso = peso;
    }
    
    public Campo() {
        this.caracter = null;
        this.peso = null;
    }

    public Character getCaracter() {
        return caracter;
    }

    public void setCaracter(Character caracter) {
        this.caracter = caracter;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    @Override
    public int compareTo(Campo t) {
        return this.peso-t.getPeso();
    }
    
    
}
