package objetos;

public class Login extends Usuario {
    private String usuario;
    private String contraseña;

    public Login(String usuario, String contraseña) {
        super(0, "", "", "", "", "", "", "", "", "");
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Login() {
        super(0, "", "", "", "", "", "", "", "", "");
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
