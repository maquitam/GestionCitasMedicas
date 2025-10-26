package servicios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objetos.Especialidad;
import objetos.Paciente;
import repositorio.EspecialidadRepositorio;
import repositorio.LoginRepositorio;


public class ServicioEspecialidad {
   
    EspecialidadRepositorio especialidadRepositorio;
    LoginRepositorio loginRepositorio;

    public ServicioEspecialidad() {
        try {
            especialidadRepositorio = new EspecialidadRepositorio();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean crearEspecialidad(String datos) throws Exception {
            String[] partes = datos.split("\\|");
            var especialidad = new Especialidad(partes[0], generarId(), Boolean.parseBoolean(partes[2]), partes[3]);


            return especialidadRepositorio.registrarEspecialidad(especialidad);
    }

    public boolean registrarEspecialidad(Especialidad especialidad) throws IOException, Exception {
        var especialidades = especialidadRepositorio.getEspecialidades();

        if (especialidades != null) {
            for (var temporal : especialidades) {
                if (temporal.getNombreEspecialidad().equals(especialidad.getNombreEspecialidad())) {
                    return false;
            }
        }
        }

        return especialidadRepositorio.registrarEspecialidad(especialidad);
    }


    public List<Especialidad> getEspecialidades() {
        return especialidadRepositorio.getEspecialidades();
    }
 
    public int generarId() {
        var especialidades = especialidadRepositorio.getEspecialidades();
        
        if (especialidades == null) {
            return 0;
        }
        
        return especialidades.size();
    }

    public int buscarPornombre(String nombre) {
        var especialidad = especialidadRepositorio.getEspecialidades();
        for (var temporal : especialidad) {
            if (temporal.getNombreEspecialidad().equals(nombre)) {
                return especialidad.indexOf(temporal);
            }
        }

        return -1;
    }

    public Map<String, String> cargarEspecialidad(String nombreEspecialidad) {
        Especialidad especialidad = obtenerEspecialidad(nombreEspecialidad);
        Map<String, String> datos = new HashMap<>();

        datos.put("nombreEspecialidad", especialidad.getNombreEspecialidad());
        datos.put("identificador", especialidad.getIdentificadorFormated());
        datos.put("estado", especialidad.getEstadoFormated());
        datos.put("descripcion", especialidad.getDescripcion());
        

        return datos;
    }

        public Especialidad obtenerEspecialidad(String nombreEspecialidad) {
        var especialiadades = getEspecialidades();
        var indice = buscarPornombre(nombreEspecialidad);
        return especialiadades.get(indice);
    };


 
    public boolean actualizarEspecialidad(Map<String, String> datos) throws Exception {
        var especialiadades = getEspecialidades();
        var indice = buscarPornombre(datos.get("nombreEspecialidad"));
        var especialidad = especialiadades.get(indice);

        if (especialidad.getEstado()) {
            especialidad.setEstado(false);
        } else {
            especialidad.setEstado(true);
        }

        especialidadRepositorio.actualizarBasedeDatos(especialiadades);

        return true;
    }

    /*public boolean eliminarMedico(String documento) throws Exception {
        var medicos = medicoRepositorio.getMedicos();
        var indice = buscarPorDocumento(documento);
        
        medicos.remove(indice);
        
        medicoRepositorio.actualizarBasedeDatos(medicos);
        
        loginRepositorio = new LoginRepositorio();

        loginRepositorio.eliminarUsuario(documento);

        return true;

    }*/

    public boolean cambiasEstado(String nombre) throws Exception {
        var especialiadades = getEspecialidades();
        var indice = buscarPornombre(nombre);
        var especialidad = especialiadades.get(indice);

        if (especialidad.getEstado()) {
            especialidad.setEstado(false);
        } else {
            especialidad.setEstado(true);
        }

        especialidadRepositorio.actualizarBasedeDatos(especialiadades);

        return true;
    }
}
