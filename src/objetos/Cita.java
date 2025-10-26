package objetos;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {
    //Atributos
    private String Cita;
    private String Paciente;
    private String Medico;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private String estado; 

    public Cita(String Cita, String Paciente, String Medico,
                LocalDate fecha, LocalTime hora, String motivo, String estado) {
        this.Cita = Cita;
        this.Paciente = Paciente;
        this.Medico = Medico;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estado = estado;
    }

    public String getIdCita() {
        return Cita;
    }

    public void setIdCita(String idCita) {
        this.Cita = idCita;
    }

    public String getIdPaciente() {
        return Paciente;
    }

    public void setIdPaciente(String Paciente) {
        this.Paciente = Paciente;
    }

    public String getIdMedico() {
        return Medico;
    }

    public void setIdMedico(String Medico) {
        this.Medico = Medico;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // 🔹 Método toString para mostrar información legible
    @Override
    public String toString() {
        return "Cita{" +
                "idCita='" + Cita + '\'' +
                ", idPaciente='" + Paciente + '\'' +
                ", idMedico='" + Medico + '\'' +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", motivo='" + motivo + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
