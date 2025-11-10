package servicios;

import java.io.IOException;
import repositorio.LoginRepositorio;
import repositorio.PacienteRepositorio;
import objetos.*;

public class ServicioLogin {

    private LoginRepositorio loginRepositorio;

    public ServicioLogin() {
        try {
            loginRepositorio = new LoginRepositorio();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validarUsuarioyContraseña(String usuario, String contraseña) {
        var logins = loginRepositorio.getLogins();

        for (var login : logins) {
            if (login.getUsuario().equals(usuario) && login.getContraseña().equals(contraseña))
                return true;
        }

        return false;
    }

    public boolean registrarUsuario(Usuario usuario) throws IOException, Exception {
        var logins = loginRepositorio.getLogins();

        for (var temporal : logins) {
            if (temporal.getUsuario().equals(usuario.getNumeroDoc())) {
                return false;
            }

        }

        return loginRepositorio.registrarUsuario(usuario);

    }

    public boolean verificarUsuarioenBasedeDatos(String documento) {
        var logins = loginRepositorio.getLogins();

        for (var temporal : logins) {
            if (temporal.getUsuario().equals(documento)) {
                return true;
            }
        }

        return false;
    }

    public String obtenerPerfil(String documento) {
        return loginRepositorio.obtenerPerfilUsuario(documento);
    }

    // valida las credenciales
    // si si devuelve un OBJETO usuario con los datos del usuario
    // si no, devuelve null
    //public Usuario ...
    public String iniciarSesion(String usuario, String contrasenna) {
        var valid = validarUsuarioyContraseña(usuario, contrasenna);
        if (valid) {
            return obtenerPerfil(usuario);
        }

        return "";
        /*var valid = validarUsuarioyContraseña(usuario, contrasenna);
        if (valid) {
            String perfil = obtenerPerfil(usuario);

            if (perfil.equalsIgnoreCase("Paciente")) {
                try {
                    // buscamos paciente por documento
                    PacienteRepositorio repo = new PacienteRepositorio();
                    for (Paciente p : repo.getPacientes()) {
                        if (p.getNumeroDoc().equals(usuario)) {
                            return p;
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error leyendo Pacientes " + e);
                }

            }

        }
        return null;*/
    }
}
