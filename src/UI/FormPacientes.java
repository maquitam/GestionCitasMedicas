package UI;

import javax.swing.*;

import servicios.ServicioPaciente;
import servicios.UtilidadesForm;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class FormPacientes extends JPanel {
    protected CampoTexto primerNombre, segundoNombre, primerApellido, segundoApellido, documento, telefono, direccion, fechaNacimiento, correo;
    protected ComboBox<String> tiposDocumentos, sexo, grupo, rh;
    protected JPasswordField contrasenna;
    protected JCheckBox mostrarContrasenna;
    protected JLabel contrasennaLabel;
    protected JLabel errorPrimerNombre, errorPrimerApellido, errorSegundoApellido, errorDocumento, errorFechaNacimiento, errorSexo, errorGrupo, errorTelefono, errorDireccion, errorCorreo, errorContrasenna;
    protected List<Boolean> camposValidos = new ArrayList<>();

    public FormPacientes(String titulo) {
        setLayout(new GridBagLayout());
        crearFormularioBase(titulo);
    }

    protected void crearFormularioBase(String titulo) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,20,5,20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        for (int i = 0; i < 12; i++) camposValidos.add(false);

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        tituloLabel.setForeground(new Color(35,94,40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        add(tituloLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.insets = new Insets(10,20,1,20);

        // Primer Nombre
        JLabel primerNombreLabel = new JLabel("* Primer Nombre");
        gbc.gridx = 0;
        add(primerNombreLabel, gbc);

        // Segundo Nombre
        JLabel segundoNombreLabel = new JLabel("Segundo Nombre");
        gbc.gridx = 1;
        add(segundoNombreLabel, gbc);

        gbc.gridy = 2;
        gbc.weightx = 0.5;

        primerNombre = new CampoTexto(20);
        gbc.gridx = 0;
        add(primerNombre, gbc);

        segundoNombre = new CampoTexto(20);
        gbc.gridx = 1;
        add(segundoNombre, gbc);

        // Excepcion Primer Nombre
        gbc.insets = new Insets(10,20,5,20);
        gbc.gridy = 3;
        gbc.gridx = 0;
        JLabel errorPrimerNombre = new JLabel("  ");
        add(errorPrimerNombre, gbc); 

        gbc.insets = new Insets(10,20,1,20);

        // Apellidos
        JLabel primerApellidoLabel = new JLabel("* Primer Apellido");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(primerApellidoLabel, gbc);

        JLabel segundoApellidoLabel = new JLabel("* Segundo Apellido");
        gbc.gridx = 1;
        add(segundoApellidoLabel, gbc);
        
        gbc.gridy = 5;
        gbc.weightx = 0.5;
        
        primerApellido = new CampoTexto(20);
        gbc.gridx = 0;
        add(primerApellido, gbc);

        segundoApellido = new CampoTexto(20);
        gbc.gridx = 1;
        add(segundoApellido, gbc);

        //Excepciones Apellidos
        gbc.insets = new Insets(10,20,5,20);
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel errorPrimerApellido = new JLabel("  ");
        add(errorPrimerApellido, gbc);

        gbc.gridx = 1;
        JLabel errorSegundoApellido = new JLabel("  ");
        add(errorSegundoApellido, gbc);

        // Documento y Drop List
        gbc.insets = new Insets(10,20,1,20);

        // Numero de Documento
        JLabel documentoLabel = new JLabel("* Documento");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(documentoLabel, gbc);
        
        gbc.insets = new Insets(10,20,5,20);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(getBackground());
        GridBagConstraints inner = new GridBagConstraints();
        inner.insets = new Insets(0, 0, 0, 5);
        inner.fill = GridBagConstraints.HORIZONTAL;
        inner.gridx = 0;
        inner.weightx = 0.9;
        documento = new CampoTexto(10);
        panel.add(documento, inner);

        inner.weightx = 0.1;
        inner.gridx = 1;
        String[] items = {"CC", "TI", "RC", "CE", "PA"};
        tiposDocumentos = new ComboBox<>(items);
        panel.add(tiposDocumentos, inner);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(panel, gbc);

        gbc.insets = new Insets(10,20,1,20);

        // FECHA DE NACIMIENTO
        JLabel fechaNacimientoLabel = new JLabel("* Fecha Nacimiento");
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(fechaNacimientoLabel, gbc);

        gbc.insets = new Insets(10,20,5,20);

        fechaNacimiento = new CampoTexto(20);
        gbc.gridy = 8;
        add(fechaNacimiento, gbc);

        // Excepciones Numero de documento y Fecha de Nacimiento
        gbc.insets = new Insets(10,20,5,20);
        gbc.gridy = 9;
        gbc.gridx = 0;
        JLabel errorDocumento = new JLabel("  ");
        add(errorDocumento, gbc);

        gbc.gridx = 1;
        JLabel errorFechaNacimiento = new JLabel("  ");
        add(errorFechaNacimiento, gbc);

        
        gbc.insets = new Insets(10,20,1,20);

        // SEXO
        JLabel sexoLabel = new JLabel("* Sexo");
        gbc.gridx = 0;
        gbc.gridy = 10;
        add(sexoLabel, gbc);

        gbc.insets = new Insets(10,20,5,20);

        String[] sexos = {" ","FEMENINO", "MASCULINO"};
        sexo = new ComboBox<>(sexos);
        gbc.gridy = 11;
        add(sexo, gbc);

        gbc.insets = new Insets(10,20,1,20);

        // GRUPO SANGUINEO
        JLabel grupoSanguineoLabel = new JLabel("* Grupo Sanguíneo");
        gbc.gridx = 1;
        gbc.gridy = 10;
        add(grupoSanguineoLabel, gbc);

        JPanel grupoSanguineoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        grupoSanguineoPanel.setBackground(getBackground());

        gbc.insets = new Insets(10,20,5,20);

        String[] grupos = {" ","A", "B", "AB", "O"};
        grupo = new ComboBox<>(grupos);

        JLabel factorRhLabel = new JLabel("RH:");
        String[] factoresRh = {" ","+", "-"};
        rh = new ComboBox<>(factoresRh);

        grupoSanguineoPanel.add(grupo);
        grupoSanguineoPanel.add(factorRhLabel);
        grupoSanguineoPanel.add(rh);

        gbc.gridy = 11;
        gbc.insets = new Insets(10,20,5,20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(grupoSanguineoPanel, gbc);

        // Excepciones Sexo y Grupo Sanguineo
        gbc.insets = new Insets(10,20,5,20);
        gbc.gridy = 12;
        gbc.gridx = 0;
        JLabel errorSexo = new JLabel(" ");
        add(errorSexo, gbc);

        gbc.gridx = 1;
        JLabel errorGrupo = new JLabel(" ");
        add(errorGrupo, gbc);

        gbc.insets = new Insets(10,20,1,20);

        // TELEFONO
        JLabel telefonoLabel = new JLabel("* Telefono");
        gbc.gridx = 0;
        gbc.gridy = 13;
        add(telefonoLabel,gbc);

        gbc.insets = new Insets(10,20,5,20);
        telefono = new CampoTexto(20);
        gbc.gridy = 14;
        add(telefono, gbc);
        
        gbc.insets = new Insets(10,20,1,20);

        // Direccion
        JLabel direccionLabel = new JLabel("* Dirección");
        gbc.gridx = 1;
        gbc.gridy = 13;
        add(direccionLabel,gbc);

        gbc.insets = new Insets(10,20,5,20);
        direccion = new CampoTexto(20);
        gbc.gridy = 14;
        add(direccion, gbc);

        // Excepciones Telefono y Direccion
        gbc.insets = new Insets(10,20,5,20);
        gbc.gridy = 15;
        gbc.gridx = 0;
        JLabel errorTelefono = new JLabel("  ");
        add(errorTelefono, gbc);

        gbc.gridx = 1;
        JLabel errorDireccion = new JLabel(" ");
        add(errorDireccion, gbc);

        gbc.insets = new Insets(10,20,1,20);

        // Correo Electronico
        JLabel correoLabel = new JLabel("* Correo Electrónico");
        gbc.gridx = 0;
        gbc.gridy = 16;
        add(correoLabel,gbc);


        gbc.insets = new Insets(10,20,5,20);

        correo = new CampoTexto(20);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridy = 17;
        add(correo, gbc);

        gbc.insets = new Insets(10,20,1,20);

        // Campo de Contraseña
        contrasennaLabel = new JLabel("* Contraseña");
        gbc.gridy = 16;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(contrasennaLabel, gbc);

        gbc.insets = new Insets(10,20,5,20);

        contrasenna = new CampoContraseña(20);
        contrasenna.setPreferredSize(primerNombre.getPreferredSize());
        gbc.gridy = 17;

        add(contrasenna, gbc);

        gbc.insets = new Insets(0,20,5,20);

        mostrarContrasenna = new JCheckBox("Mostrar Contraseña");
        mostrarContrasenna.setBackground(getBackground());

        gbc.gridy = 18;
        add(mostrarContrasenna, gbc);

        // Excepciones correo electronico y Contraseña
        gbc.insets = new Insets(0,20,5,20);
        gbc.gridy = 18;
        gbc.gridx = 0;
        JLabel errorCorreo = new JLabel(" ");
        add(errorCorreo, gbc);

        gbc.gridy = 19;
        gbc.gridx = 1;
        JLabel errorContrasenna = new JLabel(" ");
        add(errorContrasenna,gbc);
        gbc.insets = new Insets(0,20,1,20);

        mostrarContrasenna.addActionListener(a->{
            boolean showing = contrasenna.getEchoChar() != 0;
            if (showing) {
                contrasenna.setEchoChar((char) 0); // Mostrar texto
                mostrarContrasenna.setText("Ocultar Constraseña");
            } else {
                contrasenna.setEchoChar('•'); // Ocultar texto
                mostrarContrasenna.setText("Mostrar Constraseña");
            }
        });

        primerNombre.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarCampoTexto(primerNombre, errorPrimerNombre);
                if (!valid) {
                    camposValidos.set(0, false);
                } else {
                    camposValidos.set(0,true);
                }
                UtilidadesForm.actualizarBoton(camposValidos);
                } catch(Exception a) {
                }
            }
        });

        primerApellido.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarCampoTexto(primerApellido, errorPrimerApellido);
                if (!valid) {
                    camposValidos.set(1, false);
                } else {
                    camposValidos.set(1,true);
                }
                UtilidadesForm.actualizarBoton(camposValidos);
                } catch(Exception a) {
                }
            }
        });
    
        segundoApellido.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarCampoTexto(segundoApellido, errorSegundoApellido);
                if (!valid) {
                    camposValidos.set(2, false);
                } else {
                    camposValidos.set(2,true);
                }
                UtilidadesForm.actualizarBoton(camposValidos);
                } catch(Exception a) {
                }
            }
        });

        documento.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarDocumento(documento, errorDocumento);
                if (!valid) {
                    camposValidos.set(3, false);
                } else {
                    camposValidos.set(3,true);
                }
                UtilidadesForm.actualizarBoton(camposValidos);
                } catch(Exception a) {
                }
            }
        }); 

        fechaNacimiento.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarFechaNacimiento(fechaNacimiento, errorFechaNacimiento);
                if (!valid) {
                    camposValidos.set(4, false);
                } else {
                    camposValidos.set(4,true);
                }
                UtilidadesForm.actualizarBoton(camposValidos);
                } catch(Exception a) {
                }
            }
        }); 
        
        sexo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarSexo(sexo, errorSexo);
                if (!valid) {
                    camposValidos.set(5, false);
                } else {
                    camposValidos.set(5,true);
                }
                UtilidadesForm.actualizarBoton(camposValidos);
                } catch(Exception a) {
                }
            }
        }); 

        grupo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarGrupoSanguineo(grupo, rh, errorGrupo);
                if (!valid) {
                    camposValidos.set(6, false);
                    camposValidos.set(7, false);
                } else {
                    camposValidos.set(6,true);
                    camposValidos.set(7,true);
                }
                UtilidadesForm.actualizarBoton(camposValidos);
                } catch(Exception a) {
                }
            }
        }); 

        rh.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarGrupoSanguineo(grupo, rh, errorGrupo);
                if (!valid) {
                    camposValidos.set(6,false);
                    camposValidos.set(7, false);
                } else {
                    camposValidos.set(6,true);
                    camposValidos.set(7,true);
                }
                UtilidadesForm.actualizarBoton(camposValidos);
                } catch(Exception a) {
                }
            }
        }); 

        telefono.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarTelefono(telefono, errorTelefono);
                if (!valid) {
                    camposValidos.set(8, false);
                } else {
                    camposValidos.set(8,true);
                }
                UtilidadesForm.actualizarBoton(camposValidos);
                } catch(Exception a) {
                }
            }
        }); 

        correo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarCorreo(correo, errorCorreo);
                if (!valid) {
                    camposValidos.set(9, false);
                } else {
                    camposValidos.set(9, true);
                }
                UtilidadesForm.actualizarBoton(camposValidos);
                } catch(Exception a) {
                }
            }
        }); 

        direccion.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarDireccion(direccion, errorDireccion);
                if (!valid) {
                    camposValidos.set(10, false);
                } else {
                    camposValidos.set(10,true);
                }
                UtilidadesForm.actualizarBoton(camposValidos);
                } catch(Exception a) {
                }
            }
        });

        contrasenna.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarContrasenna(contrasenna, errorContrasenna);
                if (!valid) {
                    camposValidos.set(11, false);
                } else {
                    camposValidos.set(11,true);
                }
                UtilidadesForm.actualizarBoton(camposValidos);
                } catch(Exception a) {
                }
            }
        });
    }

    protected void cargarPaciente(Map<String, String> datos) {
        primerNombre.setText(datos.get("primerNombre"));
        segundoNombre.setText(datos.get("segundoNombre"));
        primerApellido.setText(datos.get("primerApellido"));
        segundoApellido.setText(datos.get("segundoApellido"));
        tiposDocumentos.setSelectedItem(datos.get("tipoDocumento"));
        documento.setText(datos.get("documento"));
        sexo.setSelectedItem(datos.get("sexo"));
        grupo.setSelectedItem(datos.get("grupo"));
        rh.setSelectedItem(datos.get("rh"));
        telefono.setText(datos.get("telefono"));
        direccion.setText(datos.get("direccion"));
        correo.setText(datos.get("correo"));
        contrasenna.setText(datos.get("contrasenna"));
        fechaNacimiento.setText(datos.get("fechaNacimiento"));
    }

    protected Map<String, String> cargarDatos() {
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
    };

    protected void setCampos() {
        primerNombre.setText("María");
        segundoNombre.setText("Camila");
        primerApellido.setText("Parra");
        segundoApellido.setText("Morales");
        documento.setText("1090377346");
        tiposDocumentos.setSelectedItem("CC");
        sexo.setSelectedItem("FEMENINO");
        grupo.setSelectedItem("A");
        rh.setSelectedItem("+");
        telefono.setText("3022480598");
        direccion.setText("Sabaneta");
        correo.setText("mcparram1611@gmail.com");
        contrasenna.setText("123");
        fechaNacimiento.setText("16-11-2004");
    }

    protected boolean guardarPaciente() {
        ServicioPaciente  servicioPaciente = new ServicioPaciente();
            var datos = cargarDatos();
            boolean valid;
            try {
                valid = servicioPaciente.crearPaciente(datos);
                if (valid) {
                    JOptionPane.showMessageDialog(null, "Usuario Creado Exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
                    UtilidadesForm.limpiarCampos(this);
                    return true;
                }
            JOptionPane.showMessageDialog(null, "Parece que este documento ya tiene una cuenta asociada", "", JOptionPane.INFORMATION_MESSAGE);
            UtilidadesForm.limpiarCampos(this);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        return false;
    };
}
