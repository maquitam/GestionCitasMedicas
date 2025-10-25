package objetos;
import java.time.LocalDate;
import java.time.LocalTime;


public class Cita {
    // Atributos
    private int idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private Medico medico;
    private Paciente paciente;
    private String motivo;

    // Constructor
    public Cita(int idCita, LocalDate fecha, LocalTime hora, Medico medico, Paciente paciente, String motivo) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
        this.paciente = paciente;
        this.motivo = motivo;
    }

    public Cita(){}

    // Metodos CRUD
    public void crearCita() {
        // Lógica para crear una cita
        System.out.println("Cita creada: " + this);
    }

    public void modificarCita(LocalDate nuevaFecha, LocalTime nuevaHora, String nuevoMotivo) {
        this.fecha = nuevaFecha;
        this.hora = nuevaHora;
        this.motivo = nuevoMotivo;
        System.out.println("Cita modificada: " + this);
    }

    public void eliminarCita() {
        // Lógica para eliminar una cita
        // Terminar una vez que se tenga la logica de almacenamiento
        System.out.println("Cita eliminada: " + this);
    }

    public void consultarCita() {
        // detalles de una citas
        System.out.println("Consultando cita: " + this);
    }

    public boolean validarDisponibilidad(Cita otraCita) {
        return !(this.fecha.equals(otraCita.fecha) && this.hora.equals(otraCita.hora) && this.medico.getId() == otraCita.medico.getId());
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

    @Override
    public String toString(){
        return "Cita{" +
                "idCita=" + idCita +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", medico=" + medico +
                ", paciente=" + paciente +
                ", motivo='" + motivo + '\'' +
                '}';
    }
}
