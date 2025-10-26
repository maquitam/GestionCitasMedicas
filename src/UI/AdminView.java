package UI;

import javax.swing.*;

import java.awt.*;

public class AdminView extends BaseView {
    public static final String MODULO_MEDICOS = "medicoPanel";
    public static final String MODULO_PACIENTES = "pacientePanel";
    public static final String BIENVENIDA_PANEL = "bienvenidaPanel";
    public static final String CITA_PANEL = "citaPanel";

    public String USUARIO_AUTENTICADO;

    public AdminView(String usuario) {
        super(usuario, "Vista Administrador");
    }

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
        Boton medicos = new Boton("Médicos");
        menuLateral.add(medicos, gbc);
        gbc.gridy = 1;
        Boton pacientes = new Boton("Pacientes");
        menuLateral.add(pacientes, gbc);
        gbc.gridy = 2;
        Boton citas = new Boton("Citas");
        gbc.gridy = 3;
        menuLateral.add(citas, gbc);
        Boton especialidades = new Boton("Especialidades");
        gbc.gridy = 4;
        menuLateral.add(especialidades, gbc);
        Boton cerrarSesion = new Boton("Cerrar Sesion");
        gbc.gridy = 5;
        menuLateral.add(cerrarSesion, gbc);

        medicos.setRedondeado(0);
        pacientes.setRedondeado(0);
        citas.setRedondeado(0);
        especialidades.setRedondeado(0);
        cerrarSesion.setRedondeado(0);


        // ---- EVENTOS ----
        medicos.addActionListener(e->{
            mostrarPanelMedicos();
        });

        pacientes.addActionListener(e->{
            mostrarPanelPacientes();
        });

        cerrarSesion.addActionListener(e->{
            dispose();
            new LoginView();
        });


        

        citas.addActionListener(e->{
            mostrarPanelCitas();
        });
    }

    @Override
    protected void crearPanelDerecho() {
        panelDerecho = new JPanel(cardLayout);
        
        //Crear paneles    
        PanelMedicos medicoPanel = new PanelMedicos(this);
        PanelPacientes panelPacientes = new PanelPacientes(this);
        JPanel bienvenidaPanel = mostrarBienvenida("Bienvenid@ Admin");

        PanelCitas citaPanel = new PanelCitas(this);


        panelDerecho.add(bienvenidaPanel, BIENVENIDA_PANEL);
        panelDerecho.add(panelPacientes, MODULO_PACIENTES);
        panelDerecho.add(medicoPanel, MODULO_MEDICOS);
        panelDerecho.add(citaPanel, CITA_PANEL);

        cardLayout.show(panelDerecho, BIENVENIDA_PANEL);
    }

    public void mostrarPanelMedicos() {
        cardLayout.show(panelDerecho, MODULO_MEDICOS);
    }

    public void mostrarPanelCitas() {
        cardLayout.show(panelDerecho, CITA_PANEL);
    }

    public void mostrarPanelCitas() {
        cardLayout.show(panelDerecho, CITA_PANEL);
    }

    public void mostrarPanelPacientes() {
        cardLayout.show(panelDerecho, MODULO_PACIENTES);
    }
}

