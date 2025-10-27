package servicios;

import java.io.IOException;
import java.util.Map;
import objetos.Paciente;

import java.util.HashMap;
import java.util.List;

import repositorio.LoginRepositorio;
import repositorio.PacienteRepositorio;

public class ServicioPaciente {
    
    PacienteRepositorio pacienteRepositorio;
    LoginRepositorio loginRepositorio;

    public ServicioPaciente() {
        try {
            pacienteRepositorio = new PacienteRepositorio();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Paciente> getPacientes() {
        return pacienteRepositorio.getPacientes();
    }

    public int generarId() {
        var pacientes = getPacientes();
        
        if (pacientes == null) {
            return 0;
        }
        
        return pacientes.size();
    }

    public boolean crearPaciente(Map<String, String> datos) throws Exception {
        try {
            if (buscarPorDocumento(datos.get("documento")) != -1) {
                return false;
            }
            
            var nombres = datos.get("primerNombre");
            var segundoNombre = datos.get("segundoNombre");

            if (segundoNombre != null) {
                nombres += " " + segundoNombre;
            }

            var paciente = new Paciente(generarId(), nombres, datos.get("primerApellido") + " " + datos.get("segundoApellido"), 
                            datos.get("tipoDocumento"), datos.get("documento"), 
                            datos.get("fechaNacimiento"), datos.get("sexo"), datos.get("grupo") + datos.get("rh"),
                            datos.get("correo"), datos.get("telefono"), datos.get("direccion"), datos.get("perfil"), 
                            datos.get("contrasenna"));
        
        return pacienteRepositorio.registrarPaciente(paciente);

        } catch (Exception e) {
            return false;
        }
        
        
    }

    public Map<String, String> cargarPaciente(String id) {
        Paciente paciente = obtenerPaciente(id);

        Map<String, String> datos = new HashMap<>();

        datos.put("primerNombre", paciente.getPrimerNombre());
        datos.put("segundoNombre", paciente.getSegundoNombre());
        datos.put("primerApellido", paciente.getPrimerApellido());
        datos.put("segundoApellido", paciente.getSegundoApellido());
        datos.put("documento", paciente.getNumeroDoc());
        datos.put("tipoDocumento", paciente.getTipoDocumento());
        datos.put("sexo", paciente.getSexo());
        datos.put("grupo", paciente.getGrupo());
        datos.put("rh", paciente.getRh());
        datos.put("telefono", paciente.getTelefono());
        datos.put("direccion", paciente.getDireccion());
        datos.put("correo", paciente.getCorreo());
        datos.put("contrasenna", paciente.getContrassena());
        datos.put("fechaNacimiento", paciente.getFechadeNacimiento());
        datos.put("perfil", "Paciente");

        return datos;
    }

    public Paciente obtenerPaciente(String documento) {
        var pacientes = getPacientes();
        var indice = buscarPorDocumento(documento);

        if (indice < 0 || pacientes == null || pacientes.isEmpty()) {
            System.out.println("Paciente no encontrado o lista vacía para documento: " + documento);
            return null;
        }
        return pacientes.get(indice);
    };

    public int buscarPorDocumento(String documento) {
        var pacientes = getPacientes();

        if (pacientes == null) {return -1;}

        for (var temporal : pacientes) {
            if (temporal.getNumeroDoc().equals(documento)) {
                return pacientes.indexOf(temporal);
            }
        }

        return -1;
    }

    public boolean actualizarPaciente(Map<String, String> datos) throws Exception {
        var pacientes = getPacientes();
        var indice = buscarPorDocumento(datos.get("documento"));
        var paciente = pacientes.get(indice);

        var nombres = datos.get("primerNombre");

        if (datos.get("segundoNombre") != null) {nombres += " " + datos.get("segundoNombre");}

        paciente.setNombres(nombres);
        paciente.setApellidos(datos.get("primerApellido") + " " + datos.get("segundoApellido"));
        paciente.setTipoDocumento(datos.get("tipoDocumento"));
        paciente.setNumeroDoc(datos.get("documento"));
        paciente.setsexo(datos.get("sexo"));
        paciente.setGrupoSanguineo(datos.get("grupo")+datos.get("rh"));
        paciente.setTelefono(datos.get("telefono"));
        paciente.setDireccion(datos.get("direccion"));
        paciente.setFechadeNacimiento(datos.get("fechaNacimiento"));
        paciente.setCorreo(datos.get("correo"));
        paciente.setContrasenna(datos.get("contrasenna"));

        return pacienteRepositorio.actualizarBasedeDatos(pacientes);
    }

    public boolean eliminarPaciente(String documento) throws Exception {
        var pacientes = pacienteRepositorio.getPacientes();
        var indice = buscarPorDocumento(documento);
        
        pacientes.remove(indice);
        
        pacienteRepositorio.actualizarBasedeDatos(pacientes);
        
        loginRepositorio = new LoginRepositorio();
        loginRepositorio.eliminarUsuario(documento);

        return true;

    }
}
