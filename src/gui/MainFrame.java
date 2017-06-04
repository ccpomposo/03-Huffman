/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.Controlador;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author Usuario
 */
public class MainFrame extends JFrame{
    private JMenuBar mnbBarra;
    private JButton btnComprimir;
    private JButton btnDescomprimir;
    private JLabel lblArchivo;
    private JPanel pnlTrabajo;
    private Controlador controlador;
    private File file;
    
    public MainFrame() {
        super("Compresor v1.0");
        super.setLayout(new BorderLayout());
        super.setSize(400,150);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.controlador = new Controlador();
        this.crearMenu();
        this.crearPnlTrabajo();
        super.add(this.mnbBarra, BorderLayout.NORTH);
        super.add(this.pnlTrabajo, BorderLayout.CENTER);
        super.setVisible(true);
    }
    
    private void crearMenu() {
        this.mnbBarra = new JMenuBar();
        JFileChooser fchChooser = new JFileChooser();
        JMenu mnArchivo = new JMenu("Archivo");
        JMenuItem mniComprimir = new JMenuItem("Comprimir archivo");
        mniComprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fchChooser.showOpenDialog(MainFrame.this);
                file = fchChooser.getSelectedFile();
                pnlTrabajo.remove(lblArchivo);
                pnlTrabajo.remove(btnDescomprimir);
                lblArchivo.setText(file.getAbsolutePath()+" seleccionado");
                pnlTrabajo.add(lblArchivo);
                pnlTrabajo.add(btnComprimir);
                pnlTrabajo.revalidate();
            }
        });
        JMenuItem mniDescomprimir = new JMenuItem("Descomprimir archivo");
        mniDescomprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fchChooser.showOpenDialog(MainFrame.this);
                file = fchChooser.getSelectedFile();
                pnlTrabajo.remove(lblArchivo);
                pnlTrabajo.remove(btnComprimir);
                lblArchivo.setText(file.getAbsolutePath()+" seleccionado");
                pnlTrabajo.add(lblArchivo);
                pnlTrabajo.add(btnDescomprimir);
                pnlTrabajo.revalidate();
            }
        });
        JMenu mnAyuda = new JMenu("Ayuda");
        JMenuItem mniAbout = new JMenuItem("Acerca de...");
        mnArchivo.add(mniComprimir);
        mnArchivo.add(mniDescomprimir);
        mnAyuda.add(mniAbout);
        
        this.mnbBarra.add(mnArchivo);
        this.mnbBarra.add(mnAyuda);
    }
    
    private void crearPnlTrabajo() {
        this.pnlTrabajo = new JPanel();
        this.pnlTrabajo.setLayout(new BoxLayout(this.pnlTrabajo, BoxLayout.Y_AXIS));
        this.lblArchivo = new JLabel("No se ha cargado ningún archivo");
        this.lblArchivo.setFont(new Font("ARIAL BOLD", Font.BOLD, 16));
        this.btnComprimir = new JButton("Comprimir");
        this.btnComprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    controlador.comprimir(file, new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().length()-3)+".huf"));
                    JOptionPane.showMessageDialog(MainFrame.this, "Archivo comprimido");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ocurrió un error con el archivo");
                }
            }
        });
        this.btnDescomprimir = new JButton("Descomprimir");
        this.btnDescomprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    controlador.descomprimir(file, new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().length()-3)));
                    JOptionPane.showMessageDialog(MainFrame.this, "Archivo descomprimido");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ocurrió un error con el archivo");
                }
            }
        });
        this.pnlTrabajo.add(this.lblArchivo);
    }
    
}

class Test {
    public static void main(String[] args) {
        new MainFrame();
    }
}
