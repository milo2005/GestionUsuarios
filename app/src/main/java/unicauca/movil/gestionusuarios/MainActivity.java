package unicauca.movil.gestionusuarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import unicauca.movil.gestionusuarios.adapters.UsuarioAdapter;
import unicauca.movil.gestionusuarios.models.Usuario;

public class MainActivity extends AppCompatActivity {

    List<Usuario> data;
    UsuarioAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.list);

        data = new ArrayList<>();
        adapter = new UsuarioAdapter(getLayoutInflater(), data);
        list.setAdapter(adapter);
    }
}
