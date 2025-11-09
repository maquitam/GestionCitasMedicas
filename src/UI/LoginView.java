package UI;

import javax.swing.*;

import objetos.Usuario;
import repositorio.PacienteRepositorio;
import servicios.ServicioLogin;
import servicios.ServicioPaciente;
import servicios.UsuarioSesion;

import java.awt.*;

public class LoginView extends JFrame {
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private CardLayout cardLayout = new CardLayout();

    public static final String LOGIN_PANEL = "loginPanel";
    public static final String REGISTRO_PANEL = "registroPanel";
    public static final String CREAR_CUENTA = "crearCuenta";
    public static final String INICIAR_SESION = "iniciarSesion";
    public static final Dimension DEFECTO = new Dimension(800, 500);

    private ServicioLogin servicioLogin;

    public LoginView() {
        setTitle("Gestor de Citas Médicas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        setLayout(new BorderLayout());

        this.servicioLogin = new ServicioLogin();

        initComponents();

        setSize(DEFECTO);

        setLocationRelativeTo(null);
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
        SwithToCreateAccount crearCuenta = new SwithToCreateAccount(this);
        SwitchToLogin iniciarSesion = new SwitchToLogin(this);

        panelIzquierdo.add(crearCuenta, CREAR_CUENTA);
        panelIzquierdo.add(iniciarSesion, INICIAR_SESION);

        cardLayout.show(panelIzquierdo, CREAR_CUENTA);
    };

    public void mostrarRegistroPanel() {
        cardLayout.show(panelDerecho, REGISTRO_PANEL);
    }

    public void mostrarLoginPanel() {
        cardLayout.show(panelDerecho, LOGIN_PANEL);
        mostrarCrearCuenta();
    }

    public void mostrarCrearCuenta() {
        cardLayout.show(panelIzquierdo, CREAR_CUENTA);
        setSize(DEFECTO);
        setLocationRelativeTo(null);
    }

    public void mostrarIniciarSesion() {
        cardLayout.show(panelIzquierdo, INICIAR_SESION);
        setSize(900, 650);
        setLocationRelativeTo(null);
    }

    public void manejarInicioSesion(String usuario, String contrasenna) throws Exception{
        
        /*Usuario usuarioOBJ = servicioLogin.iniciarSesion(usuario, contrasenna);

        //if (usuarioOBJ != null) {
            // Guardar en sesion.
            UsuarioSesion.setUsuarioActual(usuarioOBJ);

            switch (usuarioOBJ) {

                case "admin":
                    new AdminView(usuario);
                    this.dispose();
                    break;
                case "paciente":
                    new PacienteView(usuarioOBJ.getNumeroDoc());
                    this.dispose();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Rol no encontrado.");*/

        var perfil = servicioLogin.iniciarSesion(usuario, contrasenna);
        var pacienteRepositorio = new PacienteRepositorio();
        var servicioPaciente = new ServicioPaciente();
        switch (perfil) {
            case "admin":
                new AdminView(usuario);
                this.dispose();
                break;
            case "Paciente":
                new PacienteView(usuario);
                var usuarios = pacienteRepositorio.getPacientes();
                var indice = servicioPaciente.buscarPorDocumento(usuario);
                var usuarioOBJ = usuarios.get(indice);
                UsuarioSesion.setUsuarioActual(usuarioOBJ);
                this.dispose();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Hubo un error al iniciar sesión.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

