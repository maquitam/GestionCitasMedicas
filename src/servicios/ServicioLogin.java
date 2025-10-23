package servicios;

import java.io.IOException;
import repositorio.LoginRepositorio;
import objetos.Login;

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

        for (var login: logins)  {
            if (login.getUsuario().equals(usuario) && login.getContraseña().equals(contraseña))
                return true;
        }

        return false;
    }

    public boolean registrarUsuario(Login login) throws IOException, Exception {
        var logins = loginRepositorio.getLogins();

        for (var temporal : logins) {
            if(temporal.getUsuario().equals(login.getUsuario())) {
                return false;
            }

        }

        return loginRepositorio.registrarUsuario(login);

    }

    public boolean verificarUsuarioenBasedeDatos(String usuario) {
        var logins = loginRepositorio.getLogins();

        for (var temporal : logins) {
            if (temporal.getUsuario().equals(usuario)) {
                return true;
            }
        }

        return false;
    }
}
