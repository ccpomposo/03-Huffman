/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import model.Compresor;

/**
 *
 * @author Usuario
 */
public class Controlador {
    private Compresor compresor;
    
    public Controlador() {
        this.compresor = new Compresor();
    }
    
    public void comprimir(File input, File output) throws IOException {
        this.compresor.comprimir(input, output);
    }
    
    public void descomprimir(File input, File output) throws IOException {
        this.compresor.descomprimir(input, output);
    }
}
