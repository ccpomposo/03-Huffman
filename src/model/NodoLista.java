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
 */
public class NodoLista<T> implements Serializable{
    private T dato;
    private NodoLista<T> siguiente;
    private NodoLista<T> anterior;
    
    public NodoLista(T dato){
        this.dato = dato;
        siguiente = null;
        anterior = null;
    }
    
    public void print(NodoLista head) { // Método HackerRank Print the elements of a linked list
    if(head != null){
         System.out.println(head.getDato());
         print(head.getSiguiente());
        }
    }
    public NodoLista insert(NodoLista head,int data) { //Método HackerRank Insert a node at the tail of a linked list
        NodoLista tail = new NodoLista(data);
        if (head == null) {
            head = tail;
        } else {
            NodoLista aux = head;
            while(aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(tail);
        }
        return head;
    }
    
    public NodoLista Insert(NodoLista head,int x) { //Método HackerRank Insert a node at the head of a linked list
        NodoLista nuevo = new NodoLista(x);
        if (head == null) {
            head = nuevo;
        } else {
            nuevo.setSiguiente(head);
            head = nuevo;
        }
        return head;
    }
    
    public NodoLista InsertNth(NodoLista head, int data, int position) { //Método HackerRank Insert a node at a specific position in a linked list
        NodoLista nuevo = new NodoLista(data);
        if (head == null && position == 0){
            head = nuevo;
        } else {
            NodoLista aux = head;
            NodoLista ant = null;
            int pos = 0;
            while (aux != null && pos != position) {
                ant = aux;
                aux = aux.getSiguiente();
                pos++;
            }
            if(ant == null) {
                nuevo.setSiguiente(head);
                head = nuevo;
            } else {
                nuevo.setSiguiente(aux);
                ant.setSiguiente(nuevo);
            }
        }
        return head;
    }
    
    public NodoLista Delete(NodoLista head, int position) { //Método HackerRank Delete a node
        NodoLista aux = head;
        NodoLista ant = null;
        int pos = 0;
        while(aux != null && pos != position) {
            ant = aux;
            aux = aux.getSiguiente();
            pos++;
        }
        if (aux != null) {
            if (ant == null) {
                head = aux.getSiguiente();
                aux.setSiguiente(null);
            } else {
                ant.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(null);
            }
        }
        return head;
    }
    
    public void ReversePrint(NodoLista head) { //Método HackerRank Print in reverse
        if (head != null){
            ReversePrint(head.getSiguiente());
            System.out.println(head.getDato());
        }
    }
    
    public NodoLista Reverse(NodoLista head) { //Método HackerRank Reverse a linked list
        if (head == null || head.getSiguiente() == null){
            return head;
        } else {
            NodoLista resto = Reverse(head.getSiguiente());
            head.getSiguiente().setSiguiente(head);
            head.setSiguiente(null);
            return resto;
        }
    }
    
    public int CompareLists(NodoLista headA, NodoLista headB) { 
        if(headA == null && headB == null){
            return 1;
        } else if (headA == null || headB == null){
            return 0;
        }
        if(headA.getDato()==headB.getDato()){
            return CompareLists(headA.getSiguiente(), headB.getSiguiente());
        } else {
            return 0;
        }
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoLista<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista<T> siguiente) {
        this.siguiente = siguiente;
    }

    public void setAnterior(NodoLista<T> anterior) {
        this.anterior = anterior;
    }

    public NodoLista<T> getAnterior() {
        return anterior;
    }
    
}
