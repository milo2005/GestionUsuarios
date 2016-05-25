package unicauca.movil.gestionusuarios.net.api;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import unicauca.movil.gestionusuarios.R;
import unicauca.movil.gestionusuarios.models.Login;
import unicauca.movil.gestionusuarios.models.Usuario;
import unicauca.movil.gestionusuarios.net.HttpApi;
import unicauca.movil.gestionusuarios.net.HttpAsyncTask;
import unicauca.movil.gestionusuarios.net.Response;

/**
 * Created by Dario Chamorro on 25/05/2016.
 */
public class UsuarioApi extends HttpApi {

    static final int REQUEST_LOGIN = 0;
    static final int REQUEST_USUARIOS = 1;


    //region CAllback
    public interface OnLogin{
        void onLogin(boolean success, Usuario usuario);
    }

    public interface OnUsuarios{
        void onUsuarios(List<Usuario> data);
    }

    OnLogin onLogin;
    OnUsuarios onUsuarios;
    //endregion

    public UsuarioApi(Context context) {
        super(context);
    }

    //region Login
    public void login(String usr, String pass, OnLogin onLogin){
        this.onLogin = onLogin;
        String url = urlBase+context.getString(R.string.url_login);
        JsonObject json = new JsonObject();
        json.addProperty("usuario", usr);
        json.addProperty("password", pass);
        HttpAsyncTask task = makeTask(REQUEST_LOGIN, HttpAsyncTask.METHOD_POST);
        task.execute(url,json.toString());
    }
    private void processLogin(Response response){
        if(validateError(response)){
            Login login = gson.fromJson(response.getMsg(), Login.class);
            onLogin.onLogin(login.isSuccess(), login.getUsuario());
        }
    }
    //endregion

    public void getUsuarios(OnUsuarios onUsuarios){
        this.onUsuarios = onUsuarios;

        String url = urlBase+context.getString(R.string.url_usuarios);
        HttpAsyncTask task = makeTask(REQUEST_USUARIOS, HttpAsyncTask.METHOD_GET);
        task.execute(url);
    }
    private void processUsuarios(Response response){
        if(validateError(response)){
            Type type = new TypeToken<List<Usuario>>(){}.getType();
            List<Usuario> data = gson.fromJson(response.getMsg(), type);
            onUsuarios.onUsuarios(data);
        }
    }

    @Override
    public void onResponse(int request, Response response) {
        switch (request){
            case REQUEST_LOGIN:
                processLogin(response);
                break;
            case REQUEST_USUARIOS:
                processUsuarios(response);
                break;
        }
    }
}
