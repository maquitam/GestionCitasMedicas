package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import UI.Boton;

public class PanelCitas extends JPanel {
    private AdminView adminView;
    private Boton agendarCitaBoton, cancelarCitaBoton, listarCitaBoton, buscarCitaPorPacienteBoton;

    public PanelCitas (AdminView adminView) {
        this.adminView = adminView;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Aquí va la vista de citas: ", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(new Color(35,94,40));
        add(titulo, BorderLayout.NORTH);

        Boton botonPrueba1 = new Boton("Botón de prueba 1");
        botonPrueba1.setBackground(new Color(44,119,50));
        botonPrueba1.setRedondeado(20);
        
        Boton botonPrueba2 = new Boton("Botón de prueba 2");
        botonPrueba2.setBackground(new Color(44,119,50));
        botonPrueba2.setRedondeado(20);

        Boton botonPrueba3 = new Boton("Botón de prueba 3");
        botonPrueba3.setBackground(new Color(44,119,50));
        botonPrueba3.setRedondeado(20);

        JPanel panelBotonIzquierda = new JPanel();
        panelBotonIzquierda.setBackground(Color.WHITE);
        panelBotonIzquierda.add(botonPrueba1);


        JPanel panelBotonCentro = new JPanel();
        panelBotonCentro.setBackground(Color.WHITE);
        panelBotonCentro.add(botonPrueba2);


        JPanel panelBotonDerecha = new JPanel();
        panelBotonDerecha.setBackground(Color.WHITE);
        panelBotonDerecha.add(botonPrueba3);

        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(Color.WHITE);
        add(panelCentral, BorderLayout.CENTER);
        add(panelBotonIzquierda, BorderLayout.WEST);
        add(panelBotonCentro, BorderLayout.CENTER);
        add(panelBotonDerecha, BorderLayout.EAST);
    }
}
