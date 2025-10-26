package UI;

import UI.Boton;
import objetos.Cita;
import objetos.Medico;
import objetos.Paciente;
import servicios.ControlCita;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelCitas extends JPanel{
    private AdminView adminView;
    private ControlCita controlCita;

    private JTable tablaCitas;
    private DefaultTableModel modeloTabla;
    private JTextField txtDocumento, txtPaciente, txtMedico, txtFecha, txtHora, txtMotivo;

    private Boton agendarCitaBoton, modificarCitaBoton, cancelarCitaBoton, listarCitaBoton, buscarCitaPorPacienteBoton;
    

    public PanelCitas (AdminView adminView) {
        this.adminView = adminView;
        this.controlCita = new ControlCita();

        setLayout(new BorderLayout(10,10));
        setBackground(Color.WHITE);
        
        //Titulo Principal
        JLabel tituloLabel = new JLabel("Gestión de Citas Médicas", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        tituloLabel.setForeground(new Color(35,94,40));
        add(tituloLabel, BorderLayout.NORTH);
        
        //Panel Superior - Formulario de Citas
        JPanel panelSuperior = crearPanelFormulario();
        add(panelSuperior, BorderLayout.NORTH);

        //Panel Central - Tabla de Citas
        JPanel panelCentral = crearPanelTabla();
        add(panelCentral, BorderLayout.CENTER);

        //Panel Inferior - Botones de Acción
        JPanel panelInferior = crearPanelBusqueda();
        add(panelInferior, BorderLayout.SOUTH);
    }

    //Formulario de datos
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Datos De La Cita"));

        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 5, 2));
        panelCampos.setBackground(Color.WHITE);

        txtDocumento = new JTextField();
        txtPaciente = new JTextField();
        txtMedico = new JTextField();
        txtMotivo = new JTextField();
        txtFecha = new JTextField();
        txtHora = new JTextField();

        //Etiquetas y campos de texto
        panelCampos.add(new JLabel("Documento Del Paciente:"));
        panelCampos.add(txtDocumento);
        panelCampos.add(new JLabel("Nombre Del Paciente:"));
        panelCampos.add(txtPaciente);
        panelCampos.add(new JLabel("Nombre Del Médico:"));
        panelCampos.add(txtMedico);
        panelCampos.add(new JLabel("Motivo:"));
        panelCampos.add(txtMotivo);
        panelCampos.add(new JLabel("DD/MM/YYYY:"));
        panelCampos.add(txtFecha);
        panelCampos.add(new JLabel("HH:MM:"));
        panelCampos.add(txtHora);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(Color.WHITE);

        //Botones
        agendarCitaBoton = new Boton("Agendar Cita");
        modificarCitaBoton = new Boton("Modificar Cita");
        cancelarCitaBoton = new Boton("Cancelar Cita");

        //Acciones de los botones
        agendarCitaBoton.addActionListener(e -> agendarCita());
        //modificarCitaBoton.addActionListener(e -> modificarCita());
        //cancelarCitaBoton.addActionListener(e -> cancelarCita());
        
        panelBotones.add(agendarCitaBoton);
        panelBotones.add(modificarCitaBoton);
        panelBotones.add(cancelarCitaBoton);

        panel.add(panelBotones, BorderLayout.SOUTH);
        panel.add(panelCampos, BorderLayout.CENTER);
        return panel;
    }

    //Tabla de citas
    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Lista De Citas"));
        modeloTabla = new DefaultTableModel(
            new Object[]{"Documento", "Paciente", "Médico", "Motivo", "Fecha", "Hora"}, 0
        );

        tablaCitas = new JTable(modeloTabla);
        tablaCitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaCitas.setRowHeight(25);
        tablaCitas.setBackground(new Color(200, 230, 200));

        tablaCitas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tablaCitas.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    txtDocumento.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
                    txtPaciente.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
                    txtMedico.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
                    txtMotivo.setText(modeloTabla.getValueAt(filaSeleccionada, 3).toString());
                    txtFecha.setText(modeloTabla.getValueAt(filaSeleccionada, 4).toString());
                    txtHora.setText(modeloTabla.getValueAt(filaSeleccionada, 5).toString());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaCitas);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    //Busqueda de citas
    private JPanel crearPanelBusqueda() {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            panel.setBackground(Color.WHITE);

            JTextField txtBuscarDocumento = new JTextField(10);
            buscarCitaPorPacienteBoton = new Boton("Buscar Paciente");
            listarCitaBoton = new Boton("Listar Las Citas");
            
            panel.add(new JLabel("Documento Del Paciente:"));
            panel.add(txtBuscarDocumento);
            panel.add(buscarCitaPorPacienteBoton);
            panel.add(listarCitaBoton);

            //Acciones de los botones
            //buscarCitaPorPacienteBoton.addActionListener(e -> buscarPorDocumento(txtBuscarDocumento.getText()));
            //listarCitaBoton.addActionListener(e -> listarCitas());
            return panel;
    }
    
    
}
