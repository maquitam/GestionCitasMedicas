package objetos;

public class Medico extends Usuario {

    private boolean estado;

     public Medico(String nombres, String apellidos, String tipoDocumento,
            double numeroDocumento, double telefono, String correo, String direccion, boolean estado) {
        
                super(nombres, apellidos, tipoDocumento, numeroDocumento, telefono,
                       correo, direccion, "Médico");
                
                this.estado = estado;
    }

    public void setEstado(boolean nuevoEstado) {
        estado = nuevoEstado;
    }

    public boolean getEstado(){
        return estado;
    }
}