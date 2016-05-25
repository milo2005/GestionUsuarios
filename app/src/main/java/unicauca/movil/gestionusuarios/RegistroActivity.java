package unicauca.movil.gestionusuarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import unicauca.movil.gestionusuarios.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        binding.setOnClick(this);
    }

    @Override
    public void onClick(View v) {

    }
}
