package com.example.exercicio1topicos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exercicio1topicos.models.Usuario;

import java.util.stream.Collectors;

import java.util.ArrayList;

public class Tela2Activity extends ListActivity {

    public ArrayList<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        this.usuarios = this.getUsuariosCadastrados();

        ListView listView = this.setListView(this.usuarios);
        this.setOnItemClickListener(listView);
    }

    private ListView setListView(ArrayList<Usuario> usuarios) {
        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                R.layout.activity_tela2_list_item,
                usuarios.stream()
                        .map(usuario -> new StringBuilder(usuario.getNome() + ": " + usuario.getGenero()))
                        .collect(Collectors.toList())
        );

        ListView lvUsuarios = findViewById(android.R.id.list);
        lvUsuarios.setAdapter(adapter);
        return lvUsuarios;
    }

    private void setOnItemClickListener(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Usuario usuario = usuarios.get(index);

                Intent intent = new Intent(Tela2Activity.this, Tela3Activity.class);

                Bundle args = new Bundle();
                args.putSerializable("usuario", usuario);
                intent.putExtra("bundle", args);

                startActivity(intent);
            }
        });
    }

    private ArrayList<Usuario> getUsuariosCadastrados() {
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("bundle");

        return (ArrayList<Usuario>) args.getSerializable("usuarios");
    }


}