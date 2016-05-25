package unicauca.movil.gestionusuarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import unicauca.movil.gestionusuarios.adapters.UsuarioAdapter;
import unicauca.movil.gestionusuarios.models.Usuario;
import unicauca.movil.gestionusuarios.net.api.UsuarioApi;

public class MainActivity extends AppCompatActivity implements UsuarioApi.OnUsuarios {

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

        UsuarioApi api = new UsuarioApi(this);
        api.getUsuarios(this);
    }

    @Override
    public void onUsuarios(List<Usuario> data) {
        for(Usuario u : data){
            this.data.add(u);
        }
        adapter.notifyDataSetChanged();
    }
}
