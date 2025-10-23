package objetos;

public class Medico extends Usuario {
        private boolean estado;
        private String especialidad;
        
        public Medico(int id,
            String nombres, String apellidos, String tipoDocumento,
            String numeroDocumento, String especialidad, String correo, String telefono, String direccion, String perfil, boolean estado, String contrasenna) {

            super(id, nombres, apellidos, tipoDocumento, numeroDocumento, telefono, correo, direccion, perfil, contrasenna);
            this.estado = estado;
            this.especialidad = especialidad;
        }


        // Getters
        public String getEspeciliadad() {return especialidad;}
        public boolean getEstado() {return estado;}

        public String getEstadoFormated() {
            var estado = getEstado();
            switch (estado) {
                case true:
                    return "Activo";
                default:
                    return "Inactivo";
            }
        };
        
        // Setters
        public void setEstado(boolean estado) {this.estado = estado;}
        public void setEspecialidad(String especialidad) {this.especialidad = especialidad;}
        
        public String toTxtFormat() {
            return getId() + "|" + getNombres() + "|" + getApellidos() + "|" + getTipoDocumento() + "|" + getNumeroDoc() + "|" + getEspeciliadad() + "|" + 
                   getCorreo() + "|" + getTelefono() + "|" + getDireccion() + "|" + getPerfil() + "|" + getEstado() + "|" + getContrassena();
        }

        public static Medico fromTxtFormat(String lineaMedico) {
            String[] parts = lineaMedico.split("\\|");
            if (parts.length != 12) throw new IllegalArgumentException("Fórmato de linea no valido.");
            Medico medico = new Medico(Integer.parseInt(parts[0]), parts[1], parts[2], (parts[3]), (parts[4]), parts[5], parts[6], (parts[7]), (parts[8]), (parts[9]), Boolean.parseBoolean(parts[10]), (parts[11]));
            return medico;
        }
}
