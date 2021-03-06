package unicauca.movil.gestionusuarios;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import unicauca.movil.gestionusuarios.databinding.ActivityLoginBinding;
import unicauca.movil.gestionusuarios.models.Usuario;
import unicauca.movil.gestionusuarios.net.api.UsuarioApi;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, UsuarioApi.OnLogin {

    ActivityLoginBinding binding;
    UsuarioApi api;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("preferencias",MODE_PRIVATE);
        boolean logged = preferences.getBoolean("logged", false);
        if(logged){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.setOnClick(this);

        api = new UsuarioApi(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.in:
                api.login(binding.usr.getText().toString()
                ,binding.pass.getText().toString(),this);

                break;
            case R.id.reg:
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onLogin(boolean success, Usuario usuario) {
        if(success){
            SharedPreferences.Editor editor =  preferences.edit();
            editor.putBoolean("logged", true);
            editor.commit();


            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);


        }else{
            Toast.makeText(this,"Usuario o Pass Incorrecto"
            ,Toast.LENGTH_SHORT).show();
        }

    }
}
