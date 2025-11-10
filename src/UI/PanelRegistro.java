package UI;

import javax.swing.*;

import servicios.ServicioPaciente;
import servicios.UtilidadesForm;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PanelRegistro extends JPanel {
    private LoginView loginFrame;
    private FormPacientes formPacientes;
    private ServicioPaciente servicioPaciente;
    private CampoTexto primerNombre, segundoNombre, primerApellido, segundoApellido, documento, telefono, direccion, fechaNacimiento, correo;
    private ComboBox<String> tiposDocumentos, sexo, grupo, rh;
    private JPasswordField contrasenna;

    public PanelRegistro(LoginView loginFrame) {
        this.loginFrame = loginFrame;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        
        formPacientes = new FormPacientes("Registro de Usuario");
        add(formPacientes, BorderLayout.CENTER);
        
        // Boton Enviar
        var boton = crearBtnEnviar();

        add(boton, BorderLayout.SOUTH);
    }

    private JPanel crearBtnEnviar() {
        JPanel btnPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        Boton enviarBoton = new Boton("Crear Cuenta");

        btnPanel.add(enviarBoton, gbc);

        // ---- EVENTO ----
        enviarBoton.addActionListener(e->{
            ServicioPaciente  servicioPaciente = new ServicioPaciente();
            var datos = formPacientes.cargarDatos();
            boolean valid;
            try {
                valid = servicioPaciente.crearPaciente(datos);
                if (valid) {
                    JOptionPane.showMessageDialog(null, "Usuario Creado Exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
                    UtilidadesForm.limpiarCampos(this);
                    loginFrame.mostrarLoginPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "¡Ups! Parece que este documento ya tiene una cuenta asociada", "", JOptionPane.INFORMATION_MESSAGE);
                    UtilidadesForm.limpiarCampos(this);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        return btnPanel;
    }

    public Map<String, String> obtenerDatosCampos() {
            Map<String, String> datos = new HashMap<>();

            datos.put("primerNombre", primerNombre.getText());
            datos.put("segundoNombre", segundoNombre.getText());
            datos.put("primerApellido", primerApellido.getText());
            datos.put("segundoApellido", segundoApellido.getText());
            datos.put("documento", documento.getText());
            datos.put("tipoDocumento", (String) tiposDocumentos.getSelectedItem());
            datos.put("sexo", (String) sexo.getSelectedItem());
            datos.put("grupo", (String) grupo.getSelectedItem());
            datos.put("rh", (String) rh.getSelectedItem());
            datos.put("telefono", telefono.getText());
            datos.put("direccion", direccion.getText());
            datos.put("correo", correo.getText());
            datos.put("contrasenna", new String(contrasenna.getPassword()));
            datos.put("fechaNacimiento", fechaNacimiento.getText());
            datos.put("perfil", "Paciente");

            return datos;
        }
}
