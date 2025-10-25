package objetos;

public class Paciente extends Usuario {
    private String sexo, grupoSanguineo;
    private String fechaDeNacimiento;
    
    public Paciente(int id, String nombres, String apellidos, String tipoDocumento, String numeroDocumento, String fechaDeNacimiento, 
                    String sexo,String grupoSanguineo, String correo, String telefono, String direccion, String perfil, String contrasenna) {

            super(id,nombres,apellidos,tipoDocumento,numeroDocumento,telefono,correo,direccion,perfil,contrasenna);
            this.sexo = sexo;
            this.fechaDeNacimiento = fechaDeNacimiento;
            this.grupoSanguineo = grupoSanguineo;
    }

    public void setsexo(String sexo) {this.sexo = sexo;}
    public void setFechadeNacimiento(String fechaDeNacimiento) {this.fechaDeNacimiento = fechaDeNacimiento;}
    public void setGrupoSanguineo(String grupoSanguineo) {this.grupoSanguineo = grupoSanguineo;}

    public String getSexo() {return sexo;}
    public String getFechadeNacimiento() {
        return fechaDeNacimiento;}
    public String getGrupo() {
        return grupoSanguineo.substring(0, grupoSanguineo.length()-1);
    }
    public String getRh() {
        return grupoSanguineo.substring(grupoSanguineo.length()-1);
    }
    
    public String toTxtFormat() {
        return getId() + "|" + getNombres() + "|" + getApellidos() + "|" + getTipoDocumento() + "|" + getNumeroDoc() + "|" + getFechadeNacimiento() + "|" + 
                getSexo() + "|" + getGrupo() + getRh() + "|" + getCorreo() + "|" + getTelefono() + "|" + getDireccion() + "|" + getPerfil() + "|" + getContrassena();
    }

    public static Paciente fromTxtFormat(String lineaPaciente) {
        String[] parts = lineaPaciente.split("\\|");
        if (parts.length != 13) throw new IllegalArgumentException("Fórmato de linea no valido.");
        Paciente paciente = new Paciente(Integer.parseInt(parts[0]), parts[1], parts[2], (parts[3]), (parts[4]), (parts[5]), (parts[6]), (parts[7]), (parts[8]), (parts[9]), (parts[10]), (parts[11]), (parts[12]));
        return paciente;
        }

}
