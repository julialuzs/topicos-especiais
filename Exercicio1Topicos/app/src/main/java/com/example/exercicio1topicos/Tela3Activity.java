package com.example.exercicio1topicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.exercicio1topicos.models.Usuario;

import java.util.ArrayList;

public class Tela3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);

        Usuario usuario = this.getUsuario();

        Toast toast = Toast.makeText(getApplicationContext(), usuario.toString(), Toast.LENGTH_LONG);
        toast.show();
    }

    private Usuario getUsuario() {
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("bundle");

        return (Usuario) args.getSerializable("usuario");
    }
}