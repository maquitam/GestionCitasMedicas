package objetos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Cita {
    // Atributos
    private int idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private Medico medico;
    private Paciente paciente;
    private String motivo;

    // Constructores
    public Cita() {
    }

    public Cita(int idCita, LocalDate fecha, LocalTime hora, Medico medico, Paciente paciente, String motivo) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
        this.paciente = paciente;
        this.motivo = motivo;
    }

    public Cita(Paciente paciente, Medico medico, LocalDate fecha, LocalTime hora, String motivo) {
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
    }

    // 🔹 Métodos CRUD simulados
    public void crearCita() {
        System.out.println("Cita creada: " + this);
    }

    public void modificarCita(LocalDate nuevaFecha, LocalTime nuevaHora, String nuevoMotivo) {
        this.fecha = nuevaFecha;
        this.hora = nuevaHora;
        this.motivo = nuevoMotivo;
        System.out.println("Cita modificada: " + this);
    }

    public void eliminarCita() {
        System.out.println(" Cita eliminada: " + this);
    }

    public void consultarCita() {
        System.out.println("Consultando cita: " + this);
    }

    // Validar disponibilidad
    public boolean validarDisponibilidad(Cita otraCita) {
        if (otraCita == null || this.medico == null || otraCita.medico == null) {
            return true; // no hay conflicto
        }
        boolean mismoMedico = this.medico.getId() == otraCita.medico.getId();
        boolean mismaFecha = this.fecha != null && this.fecha.equals(otraCita.fecha);
        boolean mismaHora = this.hora != null && this.hora.equals(otraCita.hora);
        return !(mismoMedico && mismaFecha && mismaHora);
    }

    // Getters y Setters
    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    // Métodos de comparación

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Cita))
            return false;
        Cita c = (Cita) o;
        return this.idCita == c.idCita &&
                Objects.equals(this.fecha, c.fecha) &&
                Objects.equals(this.hora, c.hora) &&
                Objects.equals(this.motivo, c.motivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCita, fecha, hora, motivo);
    }

    // Representación en texto

    @Override
    public String toString() {
        String nombreMedico = (medico != null) ? medico.getNombres() : "Sin asignar";
        String nombrePaciente = (paciente != null) ? paciente.getNombres() : "Desconocido";
        return "Cita #" + idCita +
                " [" + fecha + " " + hora + "] " +
                "Médico: " + nombreMedico +
                ", Paciente: " + nombrePaciente +
                ", Motivo: " + motivo;
    }

    // exportamos cita, convertimos la cita en formato de texto
    public String exportarArchivoCitas() {

        String idMedico = (medico != null) ? String.valueOf(medico.getId()) : "0"; // operador ternario
        String idPaciente = (paciente != null) ? String.valueOf(paciente.getId()) : "0";
        String fechaStr = (fecha != null) ? fecha.toString() : "";
        String horaStr = (hora != null) ? hora.toString() : "";
        String motivoStr = (motivo != null) ? motivo.replace("|", "/") : "";

        String linea = idCita + "|" + idPaciente + "|" + idMedico + "|" + fechaStr + "|" + horaStr + "|" + motivoStr;
        return linea.endsWith("\n") ? linea : linea + "\n";
    }

    // crear una cita a partir de una linea del archivo citas

    public static Cita cargarDesdeArchivo(String linea) {

        String[] partes = linea.split("\\|");

        if (partes.length < 0)
            return null;

        Cita cita = new Cita();
        cita.setIdCita(Integer.parseInt(partes[0]));

        Paciente p = new Paciente();
        p.setId(Integer.parseInt(partes[1]));
        cita.setPaciente(p);

        Medico m = new Medico();
        m.setId(Integer.parseInt(partes[2]));
        cita.setMedico(m);

        cita.setFecha(LocalDate.parse(partes[3]));
        cita.setHora(LocalTime.parse(partes[4]));
        cita.setMotivo((partes[5]));

        return cita;
    }
}