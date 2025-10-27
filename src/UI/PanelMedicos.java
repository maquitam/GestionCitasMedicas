package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import servicios.ServicioEspecialidad;
import servicios.ServicioMedico;
import servicios.UtilidadesForm;
import servicios.ControlMedico;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class PanelMedicos extends JPanel {

    private AdminView adminView;
    private JTable tablaMedicos;
    private DefaultTableModel modeloTabla;
    private ServicioMedico servicioMedico;
    private ControlMedico controlMedico;

    private CampoTexto primerNombreField, segundoNombreField, primerApellidoField,
                        segundoApellidoField, documentoField, telefonoField,
                        direccionField, correoField;
    private CampoContraseña campoContraseña;
    private ComboBox<String> tiposDocumentos, tipoEspecialidades;
    private boolean estado;

    private Boton btnCrearMedico, btnActualizar, btnCancelar;

    private int filaSeleccionada = -1;

    public PanelMedicos(AdminView adminView) {
        this.adminView = adminView;
        this.servicioMedico = new ServicioMedico();
        this.controlMedico = new ControlMedico();

        setPreferredSize(new Dimension(700,400));
        setLayout(new BorderLayout());

        JPanel medicoForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        var GAP_BETWEEN = new Insets(10, 20, 20, 20);
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

        // Primer Nombre
        JLabel primerNombreLabel = new JLabel("Primer Nombre");
        gbc.gridx = 0;
        medicoForm.add(primerNombreLabel, gbc);

        // Segundo Nombre
        JLabel segundoNombreLabel = new JLabel("Segundo Nombre");
        gbc.gridx = 1;
        medicoForm.add(segundoNombreLabel, gbc);

        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.insets = GAP_BETWEEN;

        primerNombreField = new CampoTexto(20);
        gbc.gridx = 0;
        medicoForm.add(primerNombreField, gbc);

        segundoNombreField = new CampoTexto(20);
        gbc.gridx = 1;
        medicoForm.add(segundoNombreField, gbc);

        gbc.insets = new Insets(10,20,1,20);

        // Apellidos
        JLabel primerApellidoLabel = new JLabel("Primer Apellido");
        gbc.gridx = 0;
        gbc.gridy = 3;
        medicoForm.add(primerApellidoLabel, gbc);

        JLabel segundoApellidoLabel = new JLabel("Segundo Apellido");
        gbc.gridx = 1;
        medicoForm.add(segundoApellidoLabel, gbc);
        
        gbc.gridy = 4;
        gbc.weightx = 0.5;
        gbc.insets = GAP_BETWEEN;
        
        primerApellidoField = new CampoTexto(20);
        gbc.gridx = 0;
        medicoForm.add(primerApellidoField, gbc);

        segundoApellidoField = new CampoTexto(20);
        gbc.gridx = 1;
        medicoForm.add(segundoApellidoField, gbc);

        // Documento y Tipo de Documento
        gbc.insets = new Insets(10,20,1,20);
        // Numero de Documento
        JLabel documentoLabel = new JLabel("Documento");
        gbc.gridx = 0;
        gbc.gridy = 5;
        medicoForm.add(documentoLabel, gbc);
        
        gbc.insets = GAP_BETWEEN;


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

        gbc.gridy = 6;
        medicoForm.add(panel, gbc);
        
        gbc.insets = new Insets(10,20,1,20);
        // Telefono
        JLabel telefonoLabel = new JLabel("Telefono");
        gbc.gridx = 1;
        gbc.gridy = 5;
        medicoForm.add(telefonoLabel,gbc);

        gbc.insets = GAP_BETWEEN;
        telefonoField = new CampoTexto(20);
        gbc.gridy = 6;
        medicoForm.add(telefonoField, gbc);
        
        gbc.insets = new Insets(10,20,1,20);
        // Direccion
        JLabel direccionLabel = new JLabel("Dirección");
        gbc.gridx = 1;
        gbc.gridy = 7;
        medicoForm.add(direccionLabel,gbc);

        gbc.insets = GAP_BETWEEN;
        direccionField = new CampoTexto(20);
        gbc.gridy = 8;
        medicoForm.add(direccionField, gbc);

        gbc.insets = new Insets(10,20,1,20);
        // Correo Electronico
        JLabel correoLabel = new JLabel("Correo Electrónico");
        gbc.gridx = 0;
        gbc.gridy = 7;
        medicoForm.add(correoLabel,gbc);

        gbc.insets = GAP_BETWEEN;
        correoField = new CampoTexto(20);
        gbc.gridy = 8;
        medicoForm.add(correoField, gbc);

        gbc.gridy = 9;
        gbc.insets = new Insets(10,20,1,20);
        JLabel especialidadLabel = new JLabel("Especialidad");
        medicoForm.add(especialidadLabel,gbc);
        gbc.gridy = 10;
        gbc.weightx = 1;
        String[] listaEspecialidades = {"Pediatría", "Consulta General"};
        tipoEspecialidades = new ComboBox<>(listaEspecialidades);
        gbc.anchor = GridBagConstraints.NORTH;
        medicoForm.add(tipoEspecialidades, gbc);

        gbc.gridx = 1;
        // Campo de Contraseña
        JLabel labelContraseña = new JLabel("Contraseña");
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        medicoForm.add(labelContraseña, gbc);
        
        JPanel panelContraseña = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanel = new GridBagConstraints();

        campoContraseña = new CampoContraseña(20);
        campoContraseña.setPreferredSize(primerNombreField.getPreferredSize());

        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;

        panelContraseña.add(campoContraseña, gbcPanel);

        JCheckBox mostrarContraseña = new JCheckBox("Mostrar Contraseña");

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

        gbcPanel.gridy = 1;
        gbcPanel.gridx = 0;
        gbcPanel.insets = new Insets(8, 0, 8, 0);
        gbcPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel.anchor = GridBagConstraints.WEST;
        panelContraseña.add(mostrarContraseña, gbcPanel);
        gbc.gridy = 10;
        gbc.insets = new Insets(8, 0, 0, 0);
        medicoForm.add(panelContraseña, gbc);


        // Boton Crear Medico
        btnCrearMedico = new Boton("Crear Médico");
        gbc.gridx = 0;
        gbc.gridy = 11;
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
}
