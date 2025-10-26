package servicios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import objetos.Especialidad;

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
            var especialidad = new Especialidad(partes[0], partes[1], Boolean.parseBoolean(partes[2]));


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

    public List<Especialidad> getMedicos() {
        return especialidadRepositorio.getEspecialidades();
    }
/* 
    public int generarId() {
        var medicos = getMedicos();
        
        if (medicos == null) {
            return 0;
        }
        
        return medicos.size();
    }*/

    public int buscarPornombre(String nombre) {
        var especialidad = especialidadRepositorio.getEspecialidades();
        for (var temporal : especialidad) {
            if (temporal.getNombreEspecialidad().equals(nombre)) {
                return especialidad.indexOf(temporal);
            }
        }

        return -1;
    }
/* 
    public boolean actualizarMedico(ArrayList<String> lista) throws Exception {
        var medicos = getMedicos();
        var indice = buscarPorDocumento(new String(lista.get(5)));
        var medico = medicos.get(indice);

        var nombre = lista.get(0);

        if (!lista.get(1).isBlank()) {
            nombre += " " + lista.get(1);
        }

        medico.setNombres(nombre);
        medico.setApellidos(lista.get(2) + " " + lista.get(3));
        medico.setTipoDocumento(lista.get(4));
        medico.setNumeroDoc(lista.get(5));
        medico.setEspecialidad(lista.get(6));
        medico.setCorreo(lista.get(7));
        medico.setTelefono(lista.get(8));
        medico.setDireccion(lista.get(9));
        medico.setContrasenna(lista.get(10));

        medicos.set(indice, medico);

        return medicoRepositorio.actualizarBasedeDatos(medicos);
    }*/

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
        var especialiadades = especialidadRepositorio.getEspecialidades();
        var indice = buscarPornombre(nombre);
        var especialidad = especialiadades.get(indice);

        if (especialidad.getEstado()) {
            especialidad.setEstado(false);
        } else {
            especialidad.setEstado(true);
        }

        especialidadRepositorio.actualizarBasedeDatos(especialiadades);

        return true;
    };
}
