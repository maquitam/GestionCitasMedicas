package objetos;

public abstract class Usuario {
    private String nombres, apellidos, tipoDocumento, correo, direccion, perfil;
    private int numeroDocumento;
    private double telefono;
    private boolean estado;
    
    // Constructor para usuarios tipo Paciente
    public Usuario(String nombres, String apellidos, String tipoDocumento,
            int numeroDocumento, double telefono, String correo, String direccion) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.perfil = "Paciente";
    }
    
    // Constructor para usuarios tipo Médico
    public Usuario(String nombres, String apellidos, 
            int numeroDocumento, String tipoDocumento, String correo, double telefono, String direccion,
            boolean estado) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.perfil = "Personal Médico";
        this.estado = estado;
    }
    
    public void setNombres(String nuevosNombres) {
        nombres = nuevosNombres;
    }
    
    public void setApellidos(String nuevosApellidos) {
        apellidos = nuevosApellidos;
    }
    
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
    
    public void setTipoDocumento(String nuevoTipoDoc) {
        tipoDocumento = nuevoTipoDoc;
    }
    
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    
    public void setCorreo(String nuevoCorreo) {
        correo = nuevoCorreo;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setDireccion(String nuevaDireccion) {
        direccion = nuevaDireccion;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setPerfil(String nuevoPerfil) {
        perfil = nuevoPerfil;
    }
    
    public String getPerfil() {
        return perfil;
    }
    
    public void setNumeroDoc(int nuevoNumeroDoc) {
        numeroDocumento = nuevoNumeroDoc;
    }
    
    public int getNumeroDoc() {
        return numeroDocumento;
    }
    
    public void setTelefono(int nuevoTelefono) {
        telefono = nuevoTelefono;
    }
    
    public double getTelefono() {
        return telefono;
    }
    
    public void setEstado(boolean nuevoEstado) {
        estado = nuevoEstado;
    }
    
    public boolean getEstado(){
        return estado;
    }
}
