package UI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import servicios.ServicioPaciente;
import servicios.UtilidadesForm;

public class PanelPacientes extends JPanel {
    private AdminView adminView;
    private JTable tablaPacientes;
    private DefaultTableModel modeloTabla;
    private ServicioPaciente servicioPaciente;

    private FormPacientes formPacientes;
    private Boton botonCrear, botonActualizar, botonCancelar;

    public PanelPacientes(AdminView adminView) {
        this.adminView = adminView;
        this.servicioPaciente = new ServicioPaciente();

        setPreferredSize(new Dimension(700,400));
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

        var formulario = crearFormulario();
        var botones = crearBotones();
        var tabla = crearTabla();

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(formulario, gbc);

        gbc.gridy = 1;
        formPanel.add(botones, gbc);

        add(formPanel, BorderLayout.WEST);

        add(tabla, BorderLayout.CENTER);

        actualizarTabla();
        modoCrear();
    }

    public JPanel crearFormulario() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcForm = new GridBagConstraints();
        gbcForm.gridwidth = 1;
        gbcForm.gridheight = 1;
        gbcForm.weightx = 1;
        gbcForm.weighty = 1;
        formPanel.setBackground(Color.GRAY);
        
        formPacientes = new FormPacientes("Gestión de Pacientes");
        formPanel.add(formPacientes);
        
        return formPanel;
    };

    public JPanel crearBotones() {
        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(Color.GRAY);

        botonCrear = new Boton("Crear Paciente");
        botonActualizar = new Boton("Actualizar");
        botonCancelar = new Boton("Cancelar");
        
        botonesPanel.add(botonCrear);
        botonesPanel.add(botonActualizar);
        botonesPanel.add(botonCancelar);

        // --- EVENTOS BOTONES ---
        botonCrear.addActionListener(e->{
            formPacientes.guardarPaciente();
            actualizarTabla();
        });

        botonActualizar.addActionListener(e->{
            var datos = formPacientes.cargarDatos();
            try {
                servicioPaciente.actualizarPaciente(datos);
                actualizarTabla();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            UtilidadesForm.limpiarCampos(formPacientes);
        });

        botonCancelar.addActionListener(e->{
            UtilidadesForm.limpiarCampos(formPacientes);
            modoCrear();
        });
            
        return botonesPanel;
    }

    public JPanel crearTabla() {
        JPanel tablaPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcTabla = new GridBagConstraints();
        gbcTabla.gridx = 0;
        gbcTabla.gridy = 0;
        gbcTabla.gridwidth = 1;
        gbcTabla.gridheight = 1;
        gbcTabla.weightx = 1;
        gbcTabla.weighty = 1;
        gbcTabla.fill = GridBagConstraints.BOTH;

        tablaPanel.setBackground(Color.GRAY);
        modeloTabla = new DefaultTableModel(new Object[]{"ID","Nombres", "Documento", "Sexo", "Teléfono", "Correo"}, 0);
        
        tablaPacientes = new JTable(modeloTabla) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane scrollTabla = new JScrollPane(tablaPacientes);

        JPopupMenu menu = new JPopupMenu();
        JMenuItem itemEliminar = new JMenuItem("Eliminar");
        menu.add(itemEliminar);

        tablaPacientes.setComponentPopupMenu(menu);
        
        tablaPanel.add(scrollTabla, gbcTabla);

        // --- EVENTOS TABLA ---
        tablaPacientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = tablaPacientes.getSelectedRow();
                    if (fila != -1) {
                        var documento = tablaPacientes.getValueAt(fila, 2).toString();
                        var datos = servicioPaciente.cargarPaciente(documento);
                        formPacientes.cargarPaciente(datos);
                        modoEdicion();
                    }
                }
            }
        });

        itemEliminar.addActionListener(a->{
            var documento = modeloTabla.getValueAt(tablaPacientes.getSelectedRow(), 2).toString();
            try {
                servicioPaciente.eliminarPaciente(documento);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            actualizarTabla();
        });

        return tablaPanel;
    };

    public void actualizarTabla() {
        var pacientes = servicioPaciente.getPacientes();
        
        if (pacientes == null) {
            return;
        }
        modeloTabla.setRowCount(0);

        modeloTabla = (DefaultTableModel) tablaPacientes.getModel();

        for (var paciente : pacientes) {
            modeloTabla.addRow(new Object[]{paciente.getId(), paciente.getPrimerNombre() + " " + paciente.getPrimerApellido(), paciente.getNumeroDoc(), paciente.getSexo(), paciente.getTelefono(), paciente.getCorreo()});;
        }
    };

    public void modoCrear() {
        botonActualizar.setVisible(false);
        botonCancelar.setVisible(false);
        botonCrear.setVisible(true);
    }
    
    public void modoEdicion() {
        botonActualizar.setVisible(true);
        botonCancelar.setVisible(true);
        botonCrear.setVisible(false);
    }

}
