package unicauca.movil.gestionusuarios.models;

/**
 * Created by Dario Chamorro on 25/05/2016.
 */
public class Login {

    boolean success;
    Usuario usuario;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
