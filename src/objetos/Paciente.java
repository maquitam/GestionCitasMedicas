package objetos;

public class Paciente extends Usuario {

    public Paciente(String nombres, String apellidos, String tipoDocumento,
            double numeroDocumento, double telefono, String correo, String direccion) {
        
        super(nombres, apellidos, tipoDocumento, numeroDocumento, telefono,
               correo, direccion, "Paciente");
    
}
}
