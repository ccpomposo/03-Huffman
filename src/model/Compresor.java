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
public class Compresor {

    private ArrayList caracteres;
    private GestorDeArchivos gestor;
    private Arbol arbol;

    public Compresor() {
        this.gestor = new GestorDeArchivos();
        this.caracteres = new ArrayList();
    }

    public void comprimir(File input, File output) throws FileNotFoundException, IOException {
        Cola cola = this.leerArchivo(input);
        while (cola.getSize() > 1) {
            Arbol izquierdo = (Arbol) cola.quitar();
            Arbol derecho = (Arbol) cola.quitar();
            izquierdo.insertarH(derecho);
            cola.insertarOrdenado(izquierdo);
        }
        this.arbol = (Arbol) cola.quitar();
        String diccionario = this.arbol.getDiccionario();
        this.escribirArchivo(output, diccionario);
    }
    
    public void descomprimir(File input, File output) throws FileNotFoundException, IOException {
        FileInputStream stream = new FileInputStream(input);
        String diccionario = new String();
        Scanner sc = new Scanner(stream);
        ArrayList bin = new ArrayList();
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
        while (sc.hasNext()) {
            String s = sc.next();
            Integer i = (int) s.charAt(0);
            String binario = Integer.toBinaryString(i);
            binario = llenarCeros(binario);
            for (int j = 0; j < 8; j++) {
                bin.add(binario.charAt(j));
            }
        }
        for (int i = 0; i < faltante; i++) {
            bin.remove(bin.size() - 1);
        }
        sc.close();
        stream.close();
        descomprimir(output, diccionario, bin);
    }
    
    private Cola leerArchivo(File file) throws FileNotFoundException, IOException {
        FileInputStream stream = new FileInputStream(file);
        Cola cola = new Cola();
        ArrayList<Character> caracteres = new ArrayList();
        ArrayList<Integer> numeros = new ArrayList();
        int aux = stream.read();
        while (aux != -1) {
            Character c = (char) aux;
            this.caracteres.add(c);
            int indice = caracteres.indexOf(c);
            if (indice != -1) {
                numeros.set(indice, (numeros.get(indice)) + 1);
            } else {
                caracteres.add(c);
                numeros.add(1);
            }
            aux = stream.read();
        }
        for (int i = 0; i < caracteres.size(); i++) {
            Campo d = new Campo(caracteres.get(i), numeros.get(i));
            Arbol<Campo> tree = new Arbol();
            tree.insertar(d);
            cola.insertarOrdenado(tree);
        }
        stream.close();
        return cola;
    }

    private ArrayList convertirBinario(String diccionario) throws FileNotFoundException, IOException {
        ArrayList bin = new ArrayList();
        for (Object car : this.caracteres) {
            Character c = (Character) car;
            int i;
            switch (c) {
                case '1':
                    i = diccionario.indexOf("xy1") + 3;
                    break;
                case '0':
                    i = diccionario.indexOf("xy0") + 3;
                    break;
                case 'x':
                    int j = 0;
                    boolean f = true;
                    while (f) {
                        while (diccionario.charAt(j) != 'x') {
                            j++;
                        }
                        if (diccionario.charAt(j + 1) == 'y') {
                            j++;
                        } else {
                            f = false;
                        }
                    }
                    i = j + 1;
                    break;
                case 'y':
                    if (diccionario.charAt(0) == 'y') {
                        i = 1;
                    } else {
                        int k = 1;
                        boolean g = true;
                        while (g) {
                            while (diccionario.charAt(k) != 'y') {
                                k++;
                            }
                            if (diccionario.charAt(k - 1) == 'x') {
                                k++;
                            } else {
                                g = false;
                            }
                        }
                        i = k + 1;
                    }
                    break;
                default:
                    i = diccionario.indexOf(c) + 1;
                    break;
            }

            while (i < diccionario.length() && (diccionario.charAt(i) == '0' || diccionario.charAt(i) == '1')) {
                char b = diccionario.charAt(i);
                if (b == 'y') {
                    System.out.println(diccionario.charAt(i - 1) + " " + diccionario.charAt(i) + " " + diccionario.charAt(i + 1));
                }
                bin.add(b);
                i++;
            }
        }
        return bin;
    }

    private void escribirArchivo(File output, String diccionario) throws FileNotFoundException, IOException {
        ArrayList lista = convertirBinario(diccionario);
        FileWriter writer = new FileWriter(output);
        writer.write(diccionario);
        int residuo = lista.size() % 8;
        int faltante = 0;
        if (residuo != 0) {
            faltante = 8 - residuo;
        }
        writer.write("fpr");
        writer.write(String.valueOf(faltante));
        writer.write("tdr");
        String s;
        for (int i = 0; i < lista.size() / 8; i++) {
            s = new String();
            for (int j = 0; j < 8; j++) {
                s += lista.get((i * 8) + j);
            }
            int parseInt = 0;
            parseInt = Integer.parseInt(s, 2);
            char c = (char) parseInt;
            writer.write(c);
        }
        if (residuo != 0) {
            s = new String();
            int fin = lista.size() / 8;
            fin *= 8;
            for (int i = fin; i < lista.size(); i++) {
                s += lista.get(i);
            }
            for (int i = 0; i < faltante; i++) {
                s += "0";
            }
            int parseInt = Integer.parseInt(s, 2);
            char c = (char) parseInt;
            writer.write(c);
        }
        writer.close();
    }

    

    private String llenarCeros(String s) {
        String string = new String();
        if (s.length() < 8) {
            for (int i = 0; i < 8 - s.length(); i++) {
                string += "0";
            }
            string += s;
            return string;
        }
        return s;
    }

    private void descomprimir(File file, String dicc, ArrayList bin) throws IOException {
        ArrayList carac = new ArrayList();
        ArrayList binario = new ArrayList();
        int i = 0;
        while (i < dicc.length()) {
            if (dicc.charAt(i) == 'x' && dicc.charAt(i + 1) == 'y') {
                i += 2;
                carac.add(dicc.charAt(i));
            } else {
                carac.add(dicc.charAt(i));
            }
            i++;
            String aux = new String();
            while (i < dicc.length() && (dicc.charAt(i) == '0' || dicc.charAt(i) == '1')) {
                aux += dicc.charAt(i);
                i++;
            }
            binario.add(aux);
        }
        FileWriter writer = new FileWriter(file);
        String s = new String();
        for (i = 0; i < bin.size(); i++) {
            s += bin.get(i);
            int aux = -1;
            aux = binario.indexOf(s);
            if (aux != -1) {
                writer.write((char) carac.get(aux));
                s = new String();
            }
        }
        writer.close();
    }

}
