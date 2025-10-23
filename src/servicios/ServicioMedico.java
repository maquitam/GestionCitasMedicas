package servicios;

import java.io.IOException;

import repositorio.LoginRepositorio;
import repositorio.MedicoRepositorio;
import objetos.Medico;

import java.util.ArrayList;
import java.util.List;

public class ServicioMedico {
    
    MedicoRepositorio medicoRepositorio;
    LoginRepositorio loginRepositorio;

    public ServicioMedico() {
        try {
            medicoRepositorio = new MedicoRepositorio();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean crearMedico(String datos) throws Exception {
            String[] partes = datos.split("\\|");

            var primerNombre = partes[0];
            var segundoNombre = partes[1];
            
            String nombres;
            if (segundoNombre.isBlank()) {
                nombres = primerNombre;
            } else {
                nombres = primerNombre + " " + segundoNombre;
            };
            
            var primerApellido = partes[2];
            var segundoApellido = partes[3];
            var apellidos = primerApellido + " " + segundoApellido;

            var medico = new Medico(generarId(), nombres, apellidos, partes[4], partes[5], partes[6], partes[7], partes[8], partes[9], partes[10],  Boolean.parseBoolean(partes[11]), partes[12]);

            return registrarMedico(medico);
    }

    public boolean registrarMedico(Medico medico) throws IOException, Exception {
        var medicos = medicoRepositorio.getMedicos();

        if (medicos != null) {
            for (var temporal : medicos) {
                if (temporal.getNumeroDoc().equals(medico.getNumeroDoc())) {
                    return false;
            }
        }
        }

        return medicoRepositorio.registrarMedico(medico);
    }

    public List<Medico> getMedicos() {
        return medicoRepositorio.getMedicos();
    }

    public int generarId() {
        var medicos = getMedicos();
        
        if (medicos == null) {
            return 0;
        }
        
        return medicos.size();
    }

    public int buscarPorDocumento(String documento) {
        var medicos = getMedicos();
        for (var temporal : medicos) {
            if (temporal.getNumeroDoc().equals(documento)) {
                return medicos.indexOf(temporal);
            }
        }

        return -1;
    }

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
    }

    public boolean eliminarMedico(String documento) throws Exception {
        var medicos = medicoRepositorio.getMedicos();
        var indice = buscarPorDocumento(documento);
        
        medicos.remove(indice);
        
        medicoRepositorio.actualizarBasedeDatos(medicos);
        
        loginRepositorio = new LoginRepositorio();

        loginRepositorio.eliminarUsuario(documento);

        return true;

    }

    public boolean cambiasEstado(String documento) throws Exception {
        var medicos = getMedicos();
        var indice = buscarPorDocumento(documento);
        var medico = medicos.get(indice);

        if (medico.getEstado()) {
            medico.setEstado(false);
        } else {
            medico.setEstado(true);
        }

        medicoRepositorio.actualizarBasedeDatos(medicos);

        return true;
    };

}
