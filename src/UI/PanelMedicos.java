package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import repositorio.EspecialidadRepositorio;
import servicios.ServicioEspecialidad;
import servicios.ServicioMedico;
import servicios.UtilidadesForm;
import servicios.ControlMedico;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanelMedicos extends JPanel {

    private AdminView adminView;
    private JTable tablaMedicos;
    private DefaultTableModel modeloTabla;
    private ServicioMedico servicioMedico;
    private ControlMedico controlMedico;
    private EspecialidadRepositorio especialidadRepositorio;
    private ServicioEspecialidad servicioEspecialidad;
    private final List<Boolean> camposValidos = new ArrayList<>();

    private CampoTexto primerNombreField, segundoNombreField, primerApellidoField,
                        segundoApellidoField, documentoField, telefonoField,
                        direccionField, correoField;
    private CampoContraseña campoContraseña;
    private ComboBox<String> tiposDocumentos, tipoEspecialidades;
    private boolean estado;

    private Boton btnCrearMedico, btnActualizar, btnCancelar;

    private int filaSeleccionada = -1;


    public PanelMedicos(AdminView adminView) throws Exception {
        this.servicioEspecialidad = new ServicioEspecialidad();

        this.adminView = adminView;
        this.servicioMedico = new ServicioMedico();
        this.controlMedico = new ControlMedico();

        for (int i = 0; i < 8; i++) camposValidos.add(false);

        setPreferredSize(new Dimension(700,400));
        setLayout(new BorderLayout());

        JPanel medicoForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        var GAP_BETWEEN = new Insets(10, 20, 5, 20);
        gbc.insets = GAP_BETWEEN;
        gbc.fill = GridBagConstraints.HORIZONTAL;



        // Título - Módulo de Médicos
        JLabel moduleTitle = new JLabel("Módulo Médicos");
        moduleTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        moduleTitle.setForeground(new Color(35,94,40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.5;
        gbc.gridwidth = 2;
        medicoForm.add(moduleTitle, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.insets = new Insets(10,20,1,20);

        // JLabels - Nombres
        JLabel primerNombreLabel = new JLabel("Primer Nombre *");

        gbc.gridx = 0;
        medicoForm.add(primerNombreLabel, gbc);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel segundoNombreLabel = new JLabel("Segundo Nombre");
        gbc.gridx = 1;
        medicoForm.add(segundoNombreLabel, gbc);

        // Campos - Nombres
        gbc.gridy = 2;
        gbc.weightx = 1;

        primerNombreField = new CampoTexto(20);
        gbc.gridx = 0;
        medicoForm.add(primerNombreField, gbc);

        segundoNombreField = new CampoTexto(20);
        gbc.gridx = 1;
        medicoForm.add(segundoNombreField, gbc);

        // Mensaje de error - Primer Nombre
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.insets = GAP_BETWEEN;
        JLabel errorPrimerNombre = new JLabel(" ");
        errorPrimerNombre.setForeground(Color.RED);
        medicoForm.add(errorPrimerNombre, gbc);

        // JLabels - Apellidos
        gbc.insets = new Insets(10,20,1,20);
        JLabel primerApellidoLabel = new JLabel("Primer Apellido *");
        gbc.gridx = 0;
        gbc.gridy = 4;
        medicoForm.add(primerApellidoLabel, gbc);

        JLabel segundoApellidoLabel = new JLabel("Segundo Apellido *");
        gbc.gridx = 1;
        medicoForm.add(segundoApellidoLabel, gbc);

        // Campos - Apellidos
        gbc.gridy = 5;
        gbc.weightx = 0.5;
        primerApellidoField = new CampoTexto(20);
        gbc.gridx = 0;
        medicoForm.add(primerApellidoField, gbc);

        segundoApellidoField = new CampoTexto(20);
        gbc.gridx = 1;
        medicoForm.add(segundoApellidoField, gbc);

        // Mensajes de Error - Apellidos
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.insets = GAP_BETWEEN;
        JLabel errorPrimerApellido = new JLabel(" ");
        errorPrimerApellido.setForeground(Color.RED);
        medicoForm.add(errorPrimerApellido, gbc);

        gbc.gridx = 1;
        JLabel errorSegundoApellido = new JLabel(" ");
        errorSegundoApellido.setForeground(Color.RED);
        medicoForm.add(errorSegundoApellido, gbc);

        
        // JLabel - Documento y Telefono
        gbc.insets = new Insets(10,20,1,20);
        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel documentoLabel = new JLabel("Documento *");
        
        medicoForm.add(documentoLabel, gbc);

        JLabel telefonoLabel = new JLabel("Telefono *");
        gbc.gridx = 1;
        medicoForm.add(telefonoLabel,gbc);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints innerGbc = new GridBagConstraints();
        innerGbc.insets = new Insets(0, 0, 0, 0);
        innerGbc.fill = GridBagConstraints.HORIZONTAL;
        innerGbc.gridx = 0;
        innerGbc.weightx = 0.9;
        documentoField = new CampoTexto(10);
        panel.add(documentoField,innerGbc);
        
        innerGbc.weightx = 0.1;
        innerGbc.gridx = 1;
        String[] listaDocumentos = {"CC","CE","PA"};
        tiposDocumentos = new ComboBox<>(listaDocumentos);
        panel.add(tiposDocumentos, innerGbc);

        gbc.gridy = 8;
        gbc.gridx = 0;
        medicoForm.add(panel, gbc);
        
        telefonoField = new CampoTexto(20);
        gbc.gridx = 1;
        medicoForm.add(telefonoField, gbc);
        
        // Mensajes de Error - Documento y Telefono
        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.insets = GAP_BETWEEN;
        JLabel errorDocumento = new JLabel(" ");
        errorDocumento.setForeground(Color.RED);
        medicoForm.add(errorDocumento, gbc);

        JLabel errorTelefono = new JLabel(" ");
        errorTelefono.setForeground(Color.RED);
        gbc.gridx = 1;
        medicoForm.add(errorTelefono, gbc);


        // JLabels - Dirección y Correo
        gbc.insets = new Insets(10,20,1,20);
        gbc.gridy = 10;
        gbc.gridx = 0;
        JLabel correoLabel = new JLabel("Correo Electrónico *");
        medicoForm.add(correoLabel,gbc);

        JLabel direccionLabel = new JLabel("Dirección *");
        gbc.gridx = 1;
        medicoForm.add(direccionLabel,gbc);

        // Campos - Dirección y Correo
        gbc.gridy = 11;
        gbc.gridx = 0;
        correoField = new CampoTexto(20);
        medicoForm.add(correoField, gbc);

        direccionField = new CampoTexto(20);
        gbc.gridx = 1;
        medicoForm.add(direccionField, gbc);
        
        // Mensajes de Error - Dirección y Correo
        gbc.gridy = 12;
        gbc.gridx = 0;
        gbc.insets = GAP_BETWEEN;
        JLabel errorCorreo = new JLabel(" ");
        errorCorreo.setForeground(Color.RED);
        medicoForm.add(errorCorreo, gbc);

        JLabel errorDireccion = new JLabel(" ");
        errorDireccion.setForeground(Color.RED);
        gbc.gridx = 1;
        medicoForm.add(errorDireccion, gbc);

        // JLabels - Especialidad y Contraseña
        gbc.gridy = 13;
        gbc.gridx = 0;
        JLabel especialidadLabel = new JLabel("Especialidad *");
        medicoForm.add(especialidadLabel,gbc);

        JLabel labelContraseña = new JLabel("Contraseña *");
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        medicoForm.add(labelContraseña, gbc);


        // Campos - Especialidad y Contraseña
        gbc.gridy = 14;
        gbc.weightx = 1;
        gbc.gridx = 0;

        try{
            String[] listaEspecialidades = servicioEspecialidad.getEspecialidadesList();
            tipoEspecialidades = new ComboBox<>(listaEspecialidades);
            gbc.anchor = GridBagConstraints.NORTH;
            medicoForm.add(tipoEspecialidades, gbc);
        } catch(Exception e){
            e.printStackTrace();
            String[] listaEspecialidades = {"Error en Especialidades.txt"};
            tipoEspecialidades = new ComboBox<>(listaEspecialidades);
            gbc.anchor = GridBagConstraints.NORTH;
            medicoForm.add(tipoEspecialidades, gbc);
        }
    
        JPanel panelContraseña = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanel = new GridBagConstraints();

        campoContraseña = new CampoContraseña(20);
        campoContraseña.setPreferredSize(primerNombreField.getPreferredSize());

        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;

        panelContraseña.add(campoContraseña, gbcPanel);

        JCheckBox mostrarContraseña = new JCheckBox("Mostrar Contraseña");

        gbcPanel.gridy = 1;
        gbcPanel.gridx = 0;
        gbcPanel.insets = new Insets(8, 0, 8, 0);
        gbcPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel.anchor = GridBagConstraints.WEST;
        panelContraseña.add(mostrarContraseña, gbcPanel);
        gbc.gridx = 1;
        gbc.insets = new Insets(8, 0, 0, 0);
        medicoForm.add(panelContraseña, gbc);

        // Mensajes de Error - Contraseña
        gbc.gridy = 15;
        gbc.gridx = 1;
        gbc.insets = new Insets(0,20,1,20);
        JLabel errorContraseña = new JLabel("La contraseña debe tener 4 números.");
        errorContraseña.setForeground(Color.RED);
        medicoForm.add(errorContraseña, gbc);


        // JLabel - Subtitulo
        gbc.insets = new Insets(10,20,1,20);
        gbc.gridy = 15;
        gbc.gridx = 0;
        JLabel subtitulo = new JLabel("Los campos marcados con * son obligatorios.");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitulo.setForeground(Color.RED);

        medicoForm.add(subtitulo, gbc);

        gbc.insets = new Insets(8, 0, 0, 0);

        // Boton Crear Medico
        btnCrearMedico = new Boton("Crear Médico");
        
        actualizarBotones();

        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.gridwidth = 2;
        medicoForm.add(btnCrearMedico, gbc);

        gbc.weightx = 1;
        gbc.gridwidth = 1;

        // Boton Actualizar
        btnActualizar = new Boton("Actualizar");
        medicoForm.add(btnActualizar, gbc);
        btnActualizar.setVisible(false);
        
        // Boton Cancelar
        btnCancelar = new Boton("Cancelar");
        gbc.gridx = 1;
        medicoForm.add(btnCancelar, gbc);
        btnCancelar.setVisible(false);

        add(medicoForm, BorderLayout.WEST);

        JPanel tablaMedicosPanel = new JPanel(new GridBagLayout());
        tablaMedicosPanel.setBorder(new EmptyBorder(new Insets(0, 0, 20, 20)));

        GridBagConstraints gbcInterno = new GridBagConstraints();
        gbcInterno.gridx = 0;
        gbcInterno.gridy = 0;
        gbcInterno.gridwidth = 1;
        gbcInterno.gridheight = 1;
        gbcInterno.weightx = 1;
        gbcInterno.weighty = 1;
        gbcInterno.fill = GridBagConstraints.BOTH;

        modeloTabla = new DefaultTableModel(new Object[]{"ID","Nombres", "Documento", "Especialidad", "Correo", "Teléfono", "Estado"}, 0);
        
        tablaMedicos = new JTable(modeloTabla) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane scrollTabla = new JScrollPane(tablaMedicos);
        tablaMedicosPanel.add(scrollTabla, gbcInterno);

        actualizarTabla();

        JPopupMenu menu = new JPopupMenu();
        JMenuItem itemEditar = new JMenuItem("Editar");
        JMenuItem itemEliminar = new JMenuItem("Eliminar");
        JMenuItem itemCambiarEstado = new JMenuItem("Cambiar Estado");
        menu.add(itemEditar);
        menu.add(itemCambiarEstado);
        menu.add(itemEliminar);

        tablaMedicos.setComponentPopupMenu(menu);

        add(tablaMedicosPanel, BorderLayout.CENTER);

        // - - - - EVENTOS - - - - -

        btnCrearMedico.addActionListener(e->{
            var datos = toTxtFormat();
            try {
                boolean valid = servicioMedico.crearMedico(datos);
                if (valid) {
                    actualizarTabla();
                    JOptionPane.showMessageDialog(
                        null, 
                        "Médico registrado correctamente.",
                        "¡Registro Exitoso!",
                        JOptionPane.INFORMATION_MESSAGE);
                    UtilidadesForm.limpiarCampos(medicoForm);
                } else {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Ya existe un médico registrado con el mismo número de documento.",
                        "¡Error!",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        btnActualizar.addActionListener(e->{
            var lista = obtenerCampos();
            try {
                servicioMedico.actualizarMedico(lista);
                actualizarTabla();
                UtilidadesForm.limpiarCampos(medicoForm);
                modoCrear();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            
        });

        btnCancelar.addActionListener(e->{
            UtilidadesForm.limpiarCampos(medicoForm);
            btnActualizar.setVisible(false);
            btnCancelar.setVisible(false);
            btnCrearMedico.setVisible(true);
        });

        tablaMedicos.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                filaSeleccionada = tablaMedicos.rowAtPoint(e.getPoint());
            }
        });

        itemEditar.addActionListener(a->{
            UtilidadesForm.limpiarCampos(medicoForm);
            cargarMedico();
            btnCrearMedico.setVisible(false);
            btnActualizar.setVisible(true);
            btnCancelar.setVisible(true);
        });

        itemEliminar.addActionListener(e->{
            var documento = modeloTabla.getValueAt(filaSeleccionada, 2).toString();
            try {
                servicioMedico.eliminarMedico(documento);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            actualizarTabla();
        });

        itemCambiarEstado.addActionListener(e->{
            var documento = modeloTabla.getValueAt(filaSeleccionada, 2).toString();
            try {
                servicioMedico.cambiasEstado(documento);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            actualizarTabla();
        });

        mostrarContraseña.addActionListener(e->{
            boolean showing = campoContraseña.getEchoChar() != 0;
            if (showing) {
                campoContraseña.setEchoChar((char) 0); // Mostrar texto
                mostrarContraseña.setText("Ocultar Constraseña");
            } else {
                campoContraseña.setEchoChar('•'); // Ocultar texto
                mostrarContraseña.setText("Mostrar Constraseña");
            }
        });

        primerNombreField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarCampoTexto(primerNombreField, errorPrimerNombre);
                if (!valid) {
                    camposValidos.set(0, false);
                } else {
                    camposValidos.set(0,true);
                }
                actualizarBotones();
                } catch(Exception a) {
                }
            }
        });
        
        primerApellidoField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarCampoTexto(primerApellidoField, errorPrimerApellido);
                if (!valid) {
                    camposValidos.set(1, false);
                } else {
                    camposValidos.set(1,true);
                }
                actualizarBotones();
                } catch(Exception a) {
                }
            }
        });
        
        segundoApellidoField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarCampoTexto(segundoApellidoField, errorSegundoApellido);
                if (!valid) {
                    camposValidos.set(2, false);
                } else {
                    camposValidos.set(2,true);
                }
                actualizarBotones();
                } catch(Exception a) {
                }
            }
        });

        documentoField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarDocumento(documentoField, errorDocumento);
                if (!valid) {
                    camposValidos.set(3, false);
                } else {
                    camposValidos.set(3,true);
                }
                actualizarBotones();
                } catch(Exception a) {
                }
            }
        });

        telefonoField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarTelefono(telefonoField, errorTelefono);
                if (!valid) {
                    camposValidos.set(4, false);
                } else {
                    camposValidos.set(4,true);
                }
                actualizarBotones();
                } catch(Exception a) {
                }
            }
        });

        correoField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarCorreo(correoField, errorCorreo);
                if (!valid) {
                    camposValidos.set(5, false);
                } else {
                    camposValidos.set(5,true);
                }
                actualizarBotones();
                } catch(Exception a) {
                }
            }
        });
        
        direccionField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                var valid = UtilidadesForm.validarCampoTexto(direccionField, errorDireccion);
                if (!valid) {
                    camposValidos.set(6, false);
                } else {
                    camposValidos.set(6,true);
                }
                actualizarBotones();
                } catch(Exception a) {
                }
            }
        });

        campoContraseña.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    var valid = UtilidadesForm.validarContrasenna(campoContraseña, errorContraseña);
                    if (!valid) {
                        camposValidos.set(7, false);
                    } else {
                        camposValidos.set(7,true);
                    }
                    actualizarBotones();
                } catch(Exception a) {
                }
            }
        });
        // - - - - - - - - - - - - - -
    }

    private String toTxtFormat() {
        return primerNombreField.getText() + "|" + segundoNombreField.getText() + "|" + primerApellidoField.getText() + "|" + segundoApellidoField.getText() + "|" + 
        tiposDocumentos.getSelectedItem().toString() + "|" + documentoField.getText() + "|" + tipoEspecialidades.getSelectedItem().toString() + "|" + correoField.getText() + "|" + 
        telefonoField.getText() + "|" + direccionField.getText() + "|" + "Médico" + "|" + "true" + "|" + new String(campoContraseña.getPassword());
    }

    public void actualizarTabla() {
        var medicos = servicioMedico.getMedicos();
        
        if (medicos == null) {
            return;
        }
        modeloTabla.setRowCount(0);

        modeloTabla = (DefaultTableModel) tablaMedicos.getModel();

        for (var medico : medicos) {
            modeloTabla.addRow(new Object[]{medico.getId(), medico.getPrimerNombre() + " " + medico.getPrimerApellido(), medico.getNumeroDoc(), medico.getEspeciliadad(), medico.getCorreo(), medico.getTelefono(), medico.getEstadoFormated()});;
        }
    };

    public void cargarMedico() {
        var documento = modeloTabla.getValueAt(filaSeleccionada, 2).toString();
        var medicos = servicioMedico.getMedicos();
        var indice = servicioMedico.buscarPorDocumento(documento);
        var medico = medicos.get(indice);


        primerNombreField.setText(medico.getPrimerNombre());
        
        var segundoNombre = medico.getSegundoNombre();
        if (segundoNombre != null) {
            segundoNombreField.setText(segundoNombre);
        }

        primerApellidoField.setText(medico.getPrimerApellido());
        segundoApellidoField.setText(medico.getSegundoApellido());

        documentoField.setText(medico.getNumeroDoc());
        tiposDocumentos.setSelectedItem(medico.getTipoDocumento());

        tipoEspecialidades.setSelectedItem(medico.getEspeciliadad());
        
        correoField.setText(medico.getCorreo());

        telefonoField.setText(medico.getTelefono());

        direccionField.setText(medico.getDireccion());

        campoContraseña.setText(medico.getContrassena());
    }

    public ArrayList<String> obtenerCampos() {
        
        ArrayList<String> lista = new ArrayList<>();

        lista.add(primerNombreField.getText());
        lista.add(segundoNombreField.getText());
        lista.add(primerApellidoField.getText());
        lista.add(segundoApellidoField.getText());
        lista.add(tiposDocumentos.getSelectedItem().toString());
        lista.add(documentoField.getText());
        lista.add(tipoEspecialidades.getSelectedItem().toString());
        lista.add(correoField.getText());
        lista.add(telefonoField.getText());
        lista.add(direccionField.getText());
        lista.add(new String(campoContraseña.getPassword()));

        return lista;
        
    }

    public void modoEdicion() {
        btnActualizar.setVisible(true);

    };

    public void modoCrear() {
        btnActualizar.setVisible(false);
        btnCancelar.setVisible(false);
        btnCrearMedico.setVisible(true);
    }

    public void actualizarBotones() {
        boolean todosValidos = camposValidos.stream().allMatch(v -> v);
        btnCrearMedico.setEnabled(todosValidos);
        if (todosValidos) {
            btnCrearMedico.setBackground(new Color(35,94,40));
        } else {
            btnCrearMedico.setBackground(Color.GRAY);
        }
    }
}
