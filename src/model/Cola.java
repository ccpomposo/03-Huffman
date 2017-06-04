/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Usuario
 * @param <T>
 */
public class Cola<T extends Comparable<T>> implements Serializable{
    private ListaDoble<T> lista;
    private int size;
    
    public Cola() {
        this.lista = new ListaDoble();
        this.size = 0;
    }
    
    public void insertar (T e) {
        this.lista.insertarFinal(e);
        this.size++;
    }
    
    public void insertarOrdenado(T e){
        this.lista.insertarOrdenado(e);
        this.size++;
    }
    
    public T quitar() {
        if (this.isVacia()){
            return null;
        } else {
            this.size--;
            return (T)lista.obtenerInicio();
        }
    }
    
    public boolean isVacia(){
        return this.lista.getInicio()==null;
    }

    public int getSize() {
        return size;
    }
    
    
}
