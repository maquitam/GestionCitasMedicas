package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import objetos.*;
import servicios.ControlCita;
import servicios.ServicioMedico;

public class PacienteCitas extends JPanel {

    private JComboBox<Especialidad> comboEspecialidad;
    private JComboBox<Medico> comboMedico;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextArea txtMotivo;
    private JTable tablaCitas;
    private DefaultTableModel modeloTabla;
    private JButton btnAgendar, btnModificar, btnEliminar;

    private final ControlCita controlCita;
    private final ServicioMedico servicioMedico;
    private final String usuario; // nombre o correo del paciente autenticado

    public PacienteCitas(PacienteView parentView) {
        this.usuario = parentView.USUARIO_AUTENTICADO; // usa lo que ya maneja BaseView
        this.controlCita = new ControlCita();
        this.servicioMedico = new ServicioMedico();

        initComponents();
        cargarEspecialidades();
        cargarCitasPaciente();
    }

    
    // Inicialización visual
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Panel superior (formulario)
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));

        comboEspecialidad = new JComboBox<>();
        comboMedico = new JComboBox<>();
        txtFecha = new JTextField("2025-10-27");
        txtHora = new JTextField("09:00");
        txtMotivo = new JTextArea(3, 20);

        btnAgendar = new JButton("Agendar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");

        panelFormulario.add(new JLabel("Especialidad:"));
        panelFormulario.add(comboEspecialidad);
        panelFormulario.add(new JLabel("Médico:"));
        panelFormulario.add(comboMedico);
        panelFormulario.add(new JLabel("Fecha (AAAA-MM-DD):"));
        panelFormulario.add(txtFecha);
        panelFormulario.add(new JLabel("Hora (HH:MM):"));
        panelFormulario.add(txtHora);
        panelFormulario.add(new JLabel("Motivo:"));
        panelFormulario.add(new JScrollPane(txtMotivo));

        // Tabla
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Médico", "Fecha", "Hora", "Motivo"}, 0);
        tablaCitas = new JTable(modeloTabla);

        // Panel botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgendar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        add(panelFormulario, BorderLayout.NORTH);
        add(new JScrollPane(tablaCitas), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        comboEspecialidad.addActionListener(e -> filtrarMedicosPorEspecialidad());
        btnAgendar.addActionListener(e -> agendarCita());
        btnModificar.addActionListener(e -> modificarCita());
        btnEliminar.addActionListener(e -> eliminarCita());
    }

    
    // Cargar especialidades (sin tocar ServicioMedico)
    
    private void cargarEspecialidades() {
        comboEspecialidad.removeAllItems();

        // ⚙️ Creamos una lista manual para simular especialidades
       /*  List<Especialidad> lista = new ArrayList<>();
        lista.add(new Especialidad("Medicina General", "E001", true));
        lista.add(new Especialidad("Pediatría", "E002", true));
        lista.add(new Especialidad("Cardiología", "E003", true));
        lista.add(new Especialidad("Dermatología", "E004", true));

        for (Especialidad esp : lista) {
            comboEspecialidad.addItem(esp);
        }

        // mostrar nombres legibles en el combo
        comboEspecialidad.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Especialidad especialidad) {
                    // usa consultarEspecialidad() para mostrar solo el nombre
                    String nombre = especialidad.consultarEspecialidad().split("\n")[0].replace("Nombre: ", "");
                    setText(nombre);
                }
                return this;
            }
        });*/

        comboEspecialidad.setSelectedIndex(0);
        filtrarMedicosPorEspecialidad();
    }
    

    // Filtrar médicos según especialidad seleccionada
    private void filtrarMedicosPorEspecialidad() {
        Especialidad esp = (Especialidad) comboEspecialidad.getSelectedItem();
        if (esp == null)
            return;

        comboMedico.removeAllItems();

        /*String nombreEsp = esp.consultarEspecialidad()
            .split("\n")[0]
            .replace("Nombre: ", "")
            .trim();

        System.out.println("Especilidad seleccionada: "+ nombreEsp);

        if (nombreEsp.contains("Medicina General")) {
            comboMedico.addItem(new Medico(
                    1, "Juan", "Pérez", "CC", "123456", "3123456789", "juanperez@hospital.com",
                    "Cra 1 #23-45", "Bogotá", "Medicina General", true, "1234"));
            comboMedico.addItem(new Medico(
                    2, "Ana", "Gómez", "CC", "654321", "3009876543", "anagomez@hospital.com",
                    "Av 9 #45-10", "Bogotá", "Medicina General", true, "5678"));
        } else if (nombreEsp.contains("Pediatría")) {
            comboMedico.addItem(new Medico(
                    3, "Carlos", "Niño", "CC", "789123", "3141592653", "cniño@hospital.com",
                    "Calle 45 #67-89", "Bogotá", "Pediatría", true, "abcd"));
        } else if (nombreEsp.contains("Cardiología")) {
            comboMedico.addItem(new Medico(
                    4, "Sofía", "Corazón", "CC", "321987", "3015556666", "scorazon@hospital.com",
                    "Transv 20 #30-10", "Bogotá", "Cardiología", true, "qwer"));
        } else if (nombreEsp.contains("Dermatología")) {
            comboMedico.addItem(new Medico(
                    5, "Paula", "Piel", "CC", "654987", "3101112233", "ppiel@hospital.com",
                    "Cl 100 #15-30", "Bogotá", "Dermatología", true, "zxcv"));
        }*/

        comboMedico.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent((list), value, index, isSelected, cellHasFocus);
                    if (value instanceof Medico medico) {
                        setText(medico.getNombres()+""+medico.getApellidos());
                    }
                return this;
            }
        });
    }

    // Cargar citas del paciente actual
    private void cargarCitasPaciente() {
        modeloTabla.setRowCount(0);
        System.out.println("📋 Mostrando citas del paciente: " + usuario);
        List<Cita> citas = controlCita.listarCitas();
        for (Cita c : citas) {
            modeloTabla.addRow(new Object[]{
                    c.getIdCita(),
                    c.getMedico().getNombres(),
                    c.getFecha(),
                    c.getHora(),
                    c.getMotivo()
            });
        }
    }

    // Agendar una nueva cita
    private void agendarCita() {
        try {
            Medico m = (Medico) comboMedico.getSelectedItem();
            LocalDate fecha = LocalDate.parse(txtFecha.getText());
            LocalTime hora = LocalTime.parse(txtHora.getText());
            String motivo = txtMotivo.getText();

            Paciente p = new Paciente(
                1, usuario, "Apellido", "correo@correo.com",
                "123456789", "F", "O+", "Calle Falsa 123",
                "Ciudad", "Departamento", "EPS Ejemplo",
                "Contraseña", "Paciente"
            );

            Cita nueva = new Cita(p, m, fecha, hora, motivo);
            if (controlCita.agendarCita(nueva)) {
                JOptionPane.showMessageDialog(this, "Cita agendada correctamente.");
                cargarCitasPaciente();
            } else {
                JOptionPane.showMessageDialog(this, "El médico no está disponible.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agendar cita: " + ex.getMessage());
        }
    }

    // Modificar cita seleccionada
    private void modificarCita() {
        int fila = tablaCitas.getSelectedRow();
        if (fila < 0) return;

        try {
            int id = (int) modeloTabla.getValueAt(fila, 0);
            Medico m = (Medico) comboMedico.getSelectedItem();
            LocalDate fecha = LocalDate.parse(txtFecha.getText());
            LocalTime hora = LocalTime.parse(txtHora.getText());
            String motivo = txtMotivo.getText();

            controlCita.modificarCita(id, m, fecha, hora, motivo);
            JOptionPane.showMessageDialog(this, "Cita modificada correctamente.");
            cargarCitasPaciente();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + ex.getMessage());
        }
    }

    // Eliminar cita seleccionada
    private void eliminarCita() {
        int fila = tablaCitas.getSelectedRow();
        if (fila < 0) return;

        int id = (int) modeloTabla.getValueAt(fila, 0);
        controlCita.eliminarCita(id);
        JOptionPane.showMessageDialog(this, "Cita eliminada correctamente.");
        cargarCitasPaciente();
    }
}
