/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class GestorDeArchivos {
    
    private Cola cola;
    private ArrayList lista;
    private ArrayList binario;
    
    public GestorDeArchivos () {
        this.cola = new Cola();
        this.lista = new ArrayList();
    }
    

    public void leerArchivoComprimir(File file) throws FileNotFoundException, IOException {
        FileInputStream iStream = new FileInputStream(file);
        ArrayList<Character> caracteres = new ArrayList();
        ArrayList<Integer> frecuencias = new ArrayList();
        int aux = iStream.read();
        while (aux != -1) {
            Character c = (char) aux;
            this.lista.add(c);
            int indice = caracteres.indexOf(c);
            if (indice != -1) {
                frecuencias.set(indice, (frecuencias.get(indice)) + 1);
            } else {
                caracteres.add(c);
                frecuencias.add(1);
            }
            aux = iStream.read();
        }
        for (int i = 0; i < caracteres.size(); i++) {
            Campo d = new Campo(caracteres.get(i), frecuencias.get(i));
            Arbol<Campo> arbol = new Arbol();
            arbol.insertar(d);
            this.cola.insertarOrdenado(arbol);
        }
        iStream.close();
    }

    public void escribirArchivoComprimir(ArrayList lista, File output, String diccionario) throws FileNotFoundException, IOException {     
        FileWriter writer = new FileWriter(output);
        writer.write(diccionario);
        int res = lista.size() % 8;
        int faltante = 0;
        if (res != 0) {
            faltante = 8 - res;
        }
        writer.write("fpr");
        writer.write(String.valueOf(faltante));
        writer.write("tdr");
        String s;
        int aux = lista.size() / 8;
        for (int i = 0; i < aux; i++) {
            s = new String();
            for (int j = 0; j < 8; j++) {
                s += lista.get((i * 8) + j);
            }
            int num = Integer.parseInt(s, 2);
            char c = (char) num;
            writer.write(c);
        }
        if (res != 0) {
            s = new String();
            int fin = lista.size() / 8;
            fin *= 8;
            for (int i = fin; i < lista.size(); i++) {
                s += lista.get(i);
            }
            for (int i = 0; i < faltante; i++) {
                s += "0";
            }
            int num = Integer.parseInt(s, 2);
            char c = (char) num;
            writer.write(c);
        }
        writer.close();
    }
    
    public String leerArchivoDescomprimir(File input) throws FileNotFoundException, IOException {
        FileInputStream stream = new FileInputStream(input);
        String diccionario = new String();
        Scanner sc = new Scanner(stream);
        this.binario = new ArrayList();
        sc.useDelimiter("fpr");
        diccionario = sc.next();
        sc.useDelimiter("");
        sc.next();
        sc.next();
        sc.next();
        sc.useDelimiter("tdr");
        Integer faltante = Integer.valueOf(sc.next());
        sc.useDelimiter("");
        sc.next();
        sc.next();
        sc.next();
        while(sc.hasNext()) {
            String s = sc.next();
            Integer i = (int)s.charAt(0);
            String binario = Integer.toBinaryString(i);
            binario = llenarCeros(binario);
            for (int j = 0; j < 8; j++) {
                this.binario.add(binario.charAt(j));
            }
        }        
        for (int i = 0; i < faltante; i++) {
            this.binario.remove(this.binario.size()-1);
        }
        sc.close();
        stream.close();
        return diccionario;
    }
    
    public void escribirArchivoDescomprimir(File output, ArrayList caracteres, ArrayList binario) throws IOException {
        FileWriter writer = new FileWriter(output);
        String s = new String();
        for (int i = 0; i < this.binario.size(); i++) {
            s += this.binario.get(i);
            int aux = -1;
            aux = binario.indexOf(s);
            if(aux != -1) {
//                System.out.println(caracteres.get(aux));
                writer.write((char)caracteres.get(aux));
                s = new String();
            }
        }
        writer.close();
    }
    
    private String llenarCeros(String bin) {
        String string = new String();
        if(bin.length()<8) {
            for (int i = 0; i < 8-bin.length(); i++) {
               string += "0";
            }
            string+=bin;
            return string;
        }
        return bin;
    }

    public Cola getCola() {
        return cola;
    }

    public ArrayList getLista() {
        return lista;
    }

    public ArrayList getBinario() {
        return binario;
    }
    
    
}
