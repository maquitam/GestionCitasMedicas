package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import servicios.ControlMedico;
import servicios.UtilidadesForm;
import objetos.Medico;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PanelControlMedico extends JPanel {

    private ControlMedico controlMedico;
    private JTable tablaMedicos;
    private DefaultTableModel modeloTabla;

    private CampoTexto primerNombreField, segundoNombreField, primerApellidoField,
            segundoApellidoField, documentoField, telefonoField,
            direccionField, correoField;
    private CampoContraseña campoContraseña;
    private ComboBox<String> tiposDocumentos, tipoEspecialidades;

    private Boton btnCrearMedico, btnActualizar, btnCancelar;

    private int filaSeleccionada = -1;

    public PanelControlMedico() {
        this.controlMedico = new ControlMedico();

        setPreferredSize(new Dimension(700, 400));
        setLayout(new BorderLayout());

        JPanel medicoForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        var GAP_BETWEEN = new Insets(10, 20, 20, 20);
        gbc.insets = GAP_BETWEEN;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel moduleTitle = new JLabel("Módulo Médicos");
        moduleTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        moduleTitle.setForeground(new Color(35, 94, 40));
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

        gbc.insets = new Insets(10, 20, 1, 20);

        // Campos de Nombre
        JLabel primerNombreLabel = new JLabel("Primer Nombre");
        gbc.gridx = 0;
        medicoForm.add(primerNombreLabel, gbc);

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

        // Campos de Apellido
        gbc.insets = new Insets(10, 20, 1, 20);
        JLabel primerApellidoLabel = new JLabel("Primer Apellido");
        gbc.gridx = 0;
        gbc.gridy = 3;
        medicoForm.add(primerApellidoLabel, gbc);

        JLabel segundoApellidoLabel = new JLabel("Segundo Apellido");
        gbc.gridx = 1;
        medicoForm.add(segundoApellidoLabel, gbc);

        gbc.gridy = 4;
        gbc.insets = GAP_BETWEEN;

        primerApellidoField = new CampoTexto(20);
        gbc.gridx = 0;
        medicoForm.add(primerApellidoField, gbc);

        segundoApellidoField = new CampoTexto(20);
        gbc.gridx = 1;
        medicoForm.add(segundoApellidoField, gbc);

        // Documento y tipo
        gbc.insets = new Insets(10, 20, 1, 20);
        JLabel documentoLabel = new JLabel("Documento");
        gbc.gridx = 0;
        gbc.gridy = 5;
        medicoForm.add(documentoLabel, gbc);

        documentoField = new CampoTexto(10);
        gbc.gridx = 0;
        gbc.gridy = 6;
        medicoForm.add(documentoField, gbc);

        String[] listaDocumentos = {"CC", "CE", "PA"};
        tiposDocumentos = new ComboBox<>(listaDocumentos);
        gbc.gridx = 1;
        medicoForm.add(tiposDocumentos, gbc);

        // Telefono
        JLabel telefonoLabel = new JLabel("Telefono");
        gbc.gridx = 0;
        gbc.gridy = 7;
        medicoForm.add(telefonoLabel, gbc);

        telefonoField = new CampoTexto(20);
        gbc.gridy = 8;
        medicoForm.add(telefonoField, gbc);

        // Direccion
        JLabel direccionLabel = new JLabel("Dirección");
        gbc.gridx = 1;
        gbc.gridy = 7;
        medicoForm.add(direccionLabel, gbc);

        direccionField = new CampoTexto(20);
        gbc.gridy = 8;
        medicoForm.add(direccionField, gbc);

        // Correo
        JLabel correoLabel = new JLabel("Correo Electrónico");
        gbc.gridx = 0;
        gbc.gridy = 9;
        medicoForm.add(correoLabel, gbc);

        correoField = new CampoTexto(20);
        gbc.gridy = 10;
        medicoForm.add(correoField, gbc);

        // Especialidad
        JLabel especialidadLabel = new JLabel("Especialidad");
        gbc.gridx = 0;
        gbc.gridy = 11;
        medicoForm.add(especialidadLabel, gbc);

        String[] listaEspecialidades = {"Pediatría", "Consulta General"};
        tipoEspecialidades = new ComboBox<>(listaEspecialidades);
        gbc.gridy = 12;
        medicoForm.add(tipoEspecialidades, gbc);

        // Contraseña
        JLabel labelContraseña = new JLabel("Contraseña");
        gbc.gridx = 1;
        gbc.gridy = 11;
        medicoForm.add(labelContraseña, gbc);

        campoContraseña = new CampoContraseña(20);
        gbc.gridy = 12;
        medicoForm.add(campoContraseña, gbc);

        // Botones
        btnCrearMedico = new Boton("Crear Médico");
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        medicoForm.add(btnCrearMedico, gbc);

        btnActualizar = new Boton("Actualizar");
        gbc.gridy = 14;
        medicoForm.add(btnActualizar, gbc);
        btnActualizar.setVisible(false);

        btnCancelar = new Boton("Cancelar");
        gbc.gridx = 1;
        medicoForm.add(btnCancelar, gbc);
        btnCancelar.setVisible(false);

        add(medicoForm, BorderLayout.WEST);

        // Tabla de Médicos
        JPanel tablaMedicosPanel = new JPanel(new GridBagLayout());
        tablaMedicosPanel.setBorder(new EmptyBorder(0, 0, 20, 20));

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Nombres", "Documento", "Especialidad", "Correo", "Teléfono", "Estado"}, 0
        );

        tablaMedicos = new JTable(modeloTabla) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane scrollTabla = new JScrollPane(tablaMedicos);
        tablaMedicosPanel.add(scrollTabla);
        add(tablaMedicosPanel, BorderLayout.CENTER);

        actualizarTabla();

        // Eventos
        btnCrearMedico.addActionListener(e -> {
            // Unir nombres y apellidos para el constructor existente
            String nombres = primerNombreField.getText() + " " + segundoNombreField.getText();
            String apellidos = primerApellidoField.getText() + " " + segundoApellidoField.getText();

            Medico medico = new Medico(
                    0,
                    nombres,
                    apellidos,
                    tiposDocumentos.getSelectedItem().toString(),
                    documentoField.getText(),
                    tipoEspecialidades.getSelectedItem().toString(),
                    correoField.getText(),
                    telefonoField.getText(),
                    direccionField.getText(),
                    "Médico",
                    true,
                    new String(campoContraseña.getPassword())
            );

            boolean agregado = controlMedico.agregarMedico(medico);

            if (agregado) {
                actualizarTabla();
                JOptionPane.showMessageDialog(null, "Médico registrado correctamente.", "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);
                UtilidadesForm.limpiarCampos(medicoForm);
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe un médico con este documento.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Eventos Actualizar y Cancelar (igual que antes, solo que no usamos setters inexistentes)
        btnCancelar.addActionListener(e -> {
            UtilidadesForm.limpiarCampos(medicoForm);
            btnActualizar.setVisible(false);
            btnCancelar.setVisible(false);
            btnCrearMedico.setVisible(true);
        });
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Medico m : controlMedico.listarMedicos()) {
            modeloTabla.addRow(new Object[]{
                    m.getId(),
                    m.getNombres(),
                    m.getNumeroDoc(),
                    m.getEspeciliadad(),
                    m.getCorreo(),
                    m.getTelefono(),
                    m.getEstadoFormated()
            });
        }
    }
}
