package UI;

import javax.swing.*;

import servicios.ServicioLogin;

import java.awt.*;

public class LoginView extends JFrame {
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private CardLayout cardLayout = new CardLayout();

    public static final String LOGIN_PANEL = "loginPanel";
    public static final String REGISTRO_PANEL = "registroPanel";
    public static final String CREAR_CUENTA = "crearCuenta";
    public static final String INICIAR_SESION = "iniciarSesion";

    private ServicioLogin servicioLogin;
    
    public LoginView() {
        setTitle("Gestor de Citas Médicas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {

        crearPanelIzquierdo();
        crearPanelDerecho();

        add(panelDerecho, BorderLayout.CENTER);
        add(panelIzquierdo, BorderLayout.WEST);

        setVisible(true);
    }

    private void crearPanelDerecho() {
        panelDerecho = new JPanel(cardLayout);

        // Crear los dos paneles
        PanelLogin loginPanel = new PanelLogin(this);
        PanelRegistro registroPanel = new PanelRegistro(this);

        // Agregar los paneles al CardLayout con identificadores
        panelDerecho.add(loginPanel, LOGIN_PANEL);
        panelDerecho.add(registroPanel, REGISTRO_PANEL);

        // Mostrar el panel de inicio de sesión por defecto
        cardLayout.show(panelDerecho, LOGIN_PANEL);
    };

    private void crearPanelIzquierdo() {
        panelIzquierdo = new JPanel(cardLayout);

        // Crear Panel Crear Cuenta
        PanelCrearCuenta crearCuenta = new PanelCrearCuenta(this);
        PanelIniciarSesion iniciarSesion = new PanelIniciarSesion(this);

        panelIzquierdo.add(crearCuenta, CREAR_CUENTA);
        panelIzquierdo.add(iniciarSesion, INICIAR_SESION);

        cardLayout.show(panelIzquierdo, CREAR_CUENTA);
    };



    public void mostrarRegistroPanel() {
        cardLayout.show(panelDerecho, REGISTRO_PANEL);
    }

    public void mostrarLoginPanel() {
        cardLayout.show(panelDerecho, LOGIN_PANEL);
    }

    public void mostrarCrearCuenta() {
        cardLayout.show(panelIzquierdo, CREAR_CUENTA);
    }

    public void mostrarIniciarSesion() {
        cardLayout.show(panelIzquierdo, INICIAR_SESION);
    }

    public void manejarInicioSesion(String usuario, String contrasenna) {
        
        servicioLogin = new ServicioLogin();

        var perfil = servicioLogin.iniciarSesion(usuario, contrasenna);

        switch (perfil) {
            case "admin":
                AdminView adminView = new AdminView(usuario);
                this.dispose();
            case "Médico":
                // MedicoView = new MedicoView(usuario);
                break;
            case "Paciente":
                // PacienteView = new PacienteView(usuario);
            default:
                JOptionPane.showMessageDialog(
                    null, "Hubo un error al iniciar sesión.", "Error", JOptionPane.INFORMATION_MESSAGE);;
        }



    }
}
