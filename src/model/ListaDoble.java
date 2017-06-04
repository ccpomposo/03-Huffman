/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author Usuario
 */
public class ListaDoble<T extends Comparable<T>> implements Serializable, Iterable {

    private NodoLista<T> inicio;
    private NodoLista<T> fin;
    private Integer lenght;

    public ListaDoble() {
        this.inicio = null;
        this.fin = null;
        this.lenght = 0;
    }

    public void insertarFinal(Comparable<T> e) {
        NodoLista<T> nuevo = new NodoLista(e);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
            this.lenght++;
        } else {
            NodoLista<T> aux = inicio;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
            nuevo.setAnterior(aux);
            fin = nuevo;
            this.lenght++;
        }
    }

    public void insertarInicio(Comparable<T> e) {
        NodoLista<T> nuevo = new NodoLista(e);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
            this.lenght++;
        } else {
            NodoLista<T> aux = inicio;
            while (aux.getAnterior() != null) {
                aux = aux.getAnterior();
            }
            aux.setAnterior(nuevo);
            nuevo.setSiguiente(aux);
            inicio = nuevo;
            this.lenght++;
        }
    }

    public void insertarOrdenado(Comparable<T> e) {
        NodoLista<T> nuevo = new NodoLista(e);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            NodoLista<T> aux = inicio;
            NodoLista<T> ant = null;
            while (aux != null && nuevo.getDato().compareTo(aux.getDato()) > 0) {
                ant = aux;
                aux = aux.getSiguiente();
            }
            if(ant == null) {
                insertarFinal(e);
            }else {
                if(aux != null) {
                    aux.setAnterior(nuevo);
                    nuevo.setSiguiente(aux);                    
                } else
                    fin = nuevo;
                ant.setSiguiente(nuevo);
                nuevo.setAnterior(ant);
            }
        }
    }

    public T obtenerFinal() {
        T r = fin.getDato();
        NodoLista<T> aux = fin.getAnterior();
        aux.setSiguiente(null);
        fin.setAnterior(null);
        fin = aux;
        return r;
    }

    public T obtenerInicio() {
        T r = inicio.getDato();
        NodoLista<T> aux = inicio.getSiguiente();
        if (aux != null) {
            aux.setAnterior(null);
            inicio.setSiguiente(null);
        }
        inicio = aux;
        return r;
    }

    public void recorrer() {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            System.out.print(aux.getDato());
            aux = aux.getSiguiente();
        }
        System.out.println("");
    }

//    public Integer comparar(ListaDoble<T> num) {
//        if(this.lenght > num.lenght) {
//            return 1;
//        } else if (num.lenght > this.lenght) {
//            return 2;
//        } else {
//            Nodo<T> aux1 = this.inicio;
//            Nodo<T> aux2 = num.inicio;
//            while(aux1 != null && aux1.getDato() == aux2.getDato()) {
//                aux1 = aux1.getSiguiente();
//                aux2 = aux2.getSiguiente();
//            }
//            if(aux1.getDato().compareTo(aux2.getDato())>0) {
//                return 1;
//            } else {
//                return 2;
//            }
//        }
//    }
    public NodoLista<T> getInicio() {
        return inicio;
    }

    public NodoLista<T> getFin() {
        return fin;
    }

    public Integer getLenght() {
        return lenght;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator() {
            NodoLista<T> siguiente = inicio;

            @Override
            public boolean hasNext() {
                return siguiente != null;
            }

            @Override
            public T next() {
                T dato = siguiente.getDato();
                siguiente = siguiente.getSiguiente();
                return dato;
            }
        };
    }
}
