package com.example.exercicio1topicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.exercicio1topicos.models.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btCadastrar;
    private Button btEnviar;

    private TextInputEditText idNome;
    private TextInputEditText idTelefone;
    private TextInputEditText idDataNascimento;
    private TextInputEditText idEmail;

    private RadioGroup rgGenero;

    private CheckBox cbMusica;
    private CheckBox cbFilme;

    public ArrayList<Usuario> usuariosCadastrados;

    private ArrayList<CheckBox> checkBoxes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();

        this.btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Tela2Activity.class);

                Bundle args = new Bundle();
                args.putSerializable("usuarios", usuariosCadastrados);
                intent.putExtra("bundle", args);

                startActivity(intent);
            }
        });

        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = getUsuario();

                usuariosCadastrados.add(usuario);

                Toast toast = Toast.makeText(getApplicationContext(), usuario.toString(), Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }


    public Usuario getUsuario() {
        Usuario usuario = new Usuario();

        usuario.setEmail(this.idEmail.getText().toString());
        usuario.setNome(this.idNome.getText().toString());
        usuario.setTelefone(this.idTelefone.getText().toString());

        int selectedRadioButton = this.rgGenero.getCheckedRadioButtonId();
        RadioButton rbGenero = findViewById(selectedRadioButton);

        usuario.setGenero(rbGenero.getText().toString());

        ArrayList<String> selecionados = new ArrayList<String>();

        for (CheckBox interesse : this.checkBoxes) {
            if (interesse.isChecked()) {
                selecionados.add(interesse.getText().toString());
            }
        }

        usuario.setInteresses(selecionados);
//        Date data = new Date();
//        usuario.setDataNascimento(this.idDataNascimento.getText().toString());

        return usuario;
    }

    public ArrayList<Usuario> getUsuariosCadastrados() {
        return usuariosCadastrados;
    }


    private void inicializarComponentes() {
        this.btCadastrar = this.findViewById(R.id.btCadastrar);
        this.btEnviar = this.findViewById(R.id.btEnviar);

        this.idNome = this.findViewById(R.id.idNome);
        this.idTelefone = this.findViewById(R.id.idTelefone);
        this.idDataNascimento = this.findViewById(R.id.idDataNascimento);
        this.idEmail = this.findViewById(R.id.idEmail);

        this.rgGenero = this.findViewById(R.id.rgGenero);
        this.cbMusica = this.findViewById(R.id.cbMusica);
        this.cbFilme = this.findViewById(R.id.cbFilme);

        this.usuariosCadastrados = new ArrayList<Usuario>();

        this.checkBoxes = new ArrayList<CheckBox>();
        this.checkBoxes.add(cbFilme);
        this.checkBoxes.add(cbMusica);
    }

}