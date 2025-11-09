package servicios;

import objetos.Paciente;
import objetos.Usuario;

public class UsuarioSesion {

    // Clase que se encarga de mantener el usuario iniciado en memoria

    // guardar el usuario logueado
    private static Paciente usuarioActual;

    public static void setUsuarioActual(Paciente usuario) {
        usuarioActual = usuario;
    }

    // devuelve el usuario actualmente en sesion
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }

    // verificar si hay sesion activa
    public static boolean haySesionActiva() {
        return usuarioActual != null;
    }
}
