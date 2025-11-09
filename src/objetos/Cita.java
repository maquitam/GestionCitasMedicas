package objetos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Cita {
    // Atributos
    private int idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private String idMedico;
    private String idPaciente;
    private String motivo;

    // Constructores
    public Cita() {
    }

    public Cita(int idCita, LocalDate fecha, LocalTime hora, String idMedico, String idPaciente, String motivo) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.motivo = motivo;
    }

    public Cita(String idMedico, String idPaciente, LocalDate fecha, LocalTime hora, String motivo) {
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
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
        System.out.println("Cita eliminada: " + this);
    }

    public void consultarCita() {
        System.out.println("ℹConsultando cita: " + this);
    }

    // Validar disponibilidad
    public boolean validarDisponibilidad(Cita otraCita) {
        if (otraCita == null || this.idMedico == null || otraCita.idMedico == null) {
            return true; // no hay conflicto
        }
        boolean mismoMedico = this.idMedico.equals(otraCita.idMedico);
        boolean mismaFecha = this.fecha != null && this.fecha.equals(otraCita.fecha);
        boolean mismaHora = this.hora != null && this.hora.equals(otraCita.hora);
        return !(mismoMedico && mismaFecha && mismaHora);
    }

    // 🔹 Getters y Setters
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
    
    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
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
        Cita cita = (Cita) o;
        return idCita == cita.idCita;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCita);
    }

    
    // Representación en texto
    
    //public String toString() {
    //    String nombreMedico = (idMedico != null) ? idMedico : "Sin asignar";
    //    String nombrePaciente = (idPaciente != null) ? idPaciente : "Desconocido";
    //    return "Cita #" + idCita +
    //            " [" + fecha + " " + hora + "] " +
    //            "Médico: " + nombreMedico +
    //            ", Paciente: " + nombrePaciente +
    //            ", Motivo: " + motivo;
    //}

    public String toTxtFormat() {
            return getIdCita() + "|" + getFecha() + "|" + getHora() + "|" + getIdMedico() + "|" + getIdPaciente() + "|" + getMotivo();
        }
    
    public static Cita fromTxtFormat(String lineaCita) {
            String[] parts = lineaCita.split("\\|");
            if (parts.length != 6) throw new IllegalArgumentException("Fórmato de linea no valido.");
            Cita cita = new Cita(Integer.parseInt(parts[0]), LocalDate.parse(parts[1]), LocalTime.parse(parts[2]), parts[3], parts[4], parts[5]);
            return cita;
        }
    
    public static String listaEspecialidades(String lineaEspecialidad) {
     String[] parts = lineaEspecialidad.split("\\|");
        if (parts.length != 4) throw new IllegalArgumentException("Fórmato de linea no valido.");
        String especialidad = parts[0];
            return especialidad;
    }
}