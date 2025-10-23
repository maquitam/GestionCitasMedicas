package objetos;

public abstract class Usuario {
    private int id;
    private String nombres, apellidos, numeroDocumento, tipoDocumento, telefono, correo, direccion, perfil, contrasenna;
    
    public Usuario(int id, String nombres, String apellidos, String tipoDocumento,
            String numeroDocumento, String telefono, String correo, String direccion, String perfil, String contrasenna) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.contrasenna = contrasenna;
        this.perfil = perfil;
    }
    
    public void setId(int id) {this.id = id;}
    public void setNombres(String nuevosNombres) {nombres = nuevosNombres;}
    public void setApellidos(String nuevosApellidos) {apellidos = nuevosApellidos;}
    public void setTipoDocumento(String nuevoTipoDoc) {tipoDocumento = nuevoTipoDoc;}
    public void setCorreo(String nuevoCorreo) {correo = nuevoCorreo;}
    public void setDireccion(String nuevaDireccion) {direccion = nuevaDireccion;}
    public void setPerfil(String nuevoPerfil) {perfil = nuevoPerfil;}
    public void setNumeroDoc(String nuevoNumeroDoc) {numeroDocumento = nuevoNumeroDoc;}
    public void setTelefono(String nuevoTelefono) {telefono = nuevoTelefono;}
    public void setContrasenna(String contrasenna) {this.contrasenna = contrasenna;}

    public int getId() {return id;}
    public String getNombres() {return nombres;}
    public String getApellidos() {return apellidos;}
    public String getNombreCompleto() {return nombres + " " + apellidos;}
    public String getTipoDocumento() {return tipoDocumento;}
    public String getCorreo() {return correo;}
    public String getDireccion() {return direccion;}
    public String getPerfil() {return perfil;}
    public String getNumeroDoc() {return numeroDocumento;}
    public String getTelefono() {return telefono;}
    public String getContrassena() {return contrasenna;}

    public String getPrimerNombre() {
        var nombres = getNombres().split(" ");
        return nombres[0];
    }

    public String getSegundoNombre() {
        var nombres = getNombres().split(" ");
        if (nombres.length == 2) {
            return nombres[1];
        }
        return null;
    }

    public String getPrimerApellido() {
        var apellidos = getApellidos().split(" ");
        return apellidos[0];
    }

    public String getSegundoApellido() {
        var apellidos = getApellidos().split(" ");
        return apellidos[1];
    }
}
