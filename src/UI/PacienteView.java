package UI;

import javax.swing.*;
import java.awt.*;

public class PacienteView extends BaseView{
    public static final String PERFIL = "miPerfil";
    public static final String CITAS = "misCitas";
    public static final String HISTORIAL = "historial";
    public static final String MENSAJE_BIENVENIDA = "mensajeBienvenida";

    // ---- CONSTRUCTOR ----
    public PacienteView(String usuario) {
        super(usuario, "Perfil: Paciente");
    }

    // ---- MÉTODOS ----
    @Override
    protected void crearMenuLateral() {
        menuLateral = new JPanel(new GridBagLayout());
        menuLateral.setBackground(new Color(35,94,40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Botones Laterales
        gbc.gridy = 0;
        gbc.gridx = 0;
        Boton misCitas = new Boton("Mis Citas");
        menuLateral.add(misCitas, gbc);
        gbc.gridy = 1;
        Boton historial = new Boton("Historial");
        menuLateral.add(historial, gbc);
        gbc.gridy = 2;
        Boton miPerfil = new Boton("Mi Perfil");
        gbc.gridy = 3;
        menuLateral.add(miPerfil, gbc);
        Boton cerrarSesion = new Boton("Cerrar Sesion");
        gbc.gridy = 4;
        menuLateral.add(cerrarSesion, gbc);

        misCitas.setRedondeado(0);
        historial.setRedondeado(0);
        miPerfil.setRedondeado(0);
        cerrarSesion.setRedondeado(0);


        // *************** EVENTOS ***************

        misCitas.addActionListener(e->{
            mostrarMisCitas();
        });

        historial.addActionListener(e->{
            // Mostrar historial
        });

        miPerfil.addActionListener(e->{
            mostrarMiPerfil();
        });

        cerrarSesion.addActionListener(e->{
            dispose();
            new LoginView();
        });

        // *************** ------- ***************
    }
    
    @Override
    protected void crearPanelDerecho() {
        panelDerecho = new JPanel(cardLayout);
        
        //Crear paneles    
        PacienteCitas citasPaciente = new PacienteCitas(this);
        PacienteMiPerfil miPerfil = new PacienteMiPerfil(this);
        PacienteHistorial historial = new PacienteHistorial(this);

        JPanel bienvenidaPanel = mostrarBienvenida("¡Bienvenid@!");
            
        panelDerecho.add(bienvenidaPanel, MENSAJE_BIENVENIDA);
        panelDerecho.add(miPerfil, PERFIL);
        panelDerecho.add(historial, HISTORIAL);
        panelDerecho.add(citasPaciente, CITAS);

        cardLayout.show(panelDerecho, MENSAJE_BIENVENIDA);
    }

    public void mostrarMisCitas() {
        cardLayout.show(panelDerecho, CITAS);
    }

    public void mostrarMiPerfil() {
        cardLayout.show(panelDerecho, PERFIL);
    }
}