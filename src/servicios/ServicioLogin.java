package servicios;

import java.io.IOException;
import repositorio.LoginRepositorio;
import objetos.*;

public class ServicioLogin {

    private LoginRepositorio loginRepositorio;
    private Medico Medico;
    private Paciente Paciente;

    public ServicioLogin() {
        try {
            loginRepositorio = new LoginRepositorio();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validarUsuarioyContraseña(String usuario, String contraseña) {
        var logins = loginRepositorio.getLogins();

        for (var login: logins)  {
            if (login.getUsuario().equals(usuario) && login.getContraseña().equals(contraseña))
                return true;
        }

        return false;
    }

    public boolean registrarUsuario(Usuario usuario) throws IOException, Exception {
        var logins = loginRepositorio.getLogins();

        for (var temporal : logins) {
            if(temporal.getUsuario().equals(usuario.getNumeroDoc())) {
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

    public String iniciarSesion(String usuario, String contrasenna) {
        var valid = validarUsuarioyContraseña(usuario, contrasenna);
        if (valid) {
            return obtenerPerfil(usuario);
        }

        return "";
    }
}
