package UI;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class AdminView extends JFrame {
    private JPanel menuLateral;
    private JPanel panelDerecho;
    private CardLayout cardLayout = new CardLayout();

    public static final String MEDICO_PANEL = "medicoPanel";    
    public static final String BIENVENIDA_PANEL = "bienvenidaPanel";
    public static final String CITA_PANEL = "citaPanel";

    public String USUARIO_AUTENTICADO;

    public AdminView(String usuario) {
        this.USUARIO_AUTENTICADO = usuario;
        setTitle("Vista Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());
        
        initComponents();
        setVisible(true);
    
    }

    private void initComponents() {
        crearMenuLateral();
        crearPanelDerecho();

        add(menuLateral, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
    }

    private void crearMenuLateral() {
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

        cerrarSesion.addActionListener(e->{
            dispose();
            LoginView loginView = new LoginView();
        });

        medicos.addActionListener(e->{
            mostrarPanelMedicos();
        });

        citas.addActionListener(e->{
            mostrarPanelCitas();
        });
    }

    private void crearPanelDerecho() {
        panelDerecho = new JPanel(cardLayout);
        
        //Crear paneles    
        PanelMedicos medicoPanel = new PanelMedicos(this);
        JPanel bienvenidaPanel = mostrarBienvenidaPanel();
        PanelCitas citaPanel = new PanelCitas(this);


        panelDerecho.add(bienvenidaPanel, BIENVENIDA_PANEL);
        panelDerecho.add(medicoPanel, MEDICO_PANEL);
        panelDerecho.add(citaPanel, CITA_PANEL);

        cardLayout.show(panelDerecho, BIENVENIDA_PANEL);
    }

    public void mostrarPanelMedicos() {
        cardLayout.show(panelDerecho, MEDICO_PANEL);
    }

    public void mostrarPanelCitas() {
        cardLayout.show(panelDerecho, CITA_PANEL);
    }

    private JPanel mostrarBienvenidaPanel() {
        JPanel bienvenidaPanel = new JPanel();
        bienvenidaPanel.setBackground(Color.WHITE);
        bienvenidaPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,20,10,20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Título - Bienvenid@ Admin
        JLabel welcomeTitle = new JLabel("Bienvenid@ Admin");
        welcomeTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeTitle.setForeground(new Color(35,94,40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        bienvenidaPanel.add(welcomeTitle, gbc);

        return bienvenidaPanel;
    }
}
