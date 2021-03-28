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
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.exercicio1topicos.Constantes.BUNDLE;
import static com.example.exercicio1topicos.Constantes.CAMPO_OBRIGATORIO;
import static com.example.exercicio1topicos.Constantes.DATA_DE_NASCIMENTO_INVALIDA;
import static com.example.exercicio1topicos.Constantes.DATA_DE_NASCIMENTO_REGEX;
import static com.example.exercicio1topicos.Constantes.EMAIL_INVALIDO;
import static com.example.exercicio1topicos.Constantes.TAMANHO_DATA_DE_NASCIMENTO;
import static com.example.exercicio1topicos.Constantes.TAMANHO_NOME;
import static com.example.exercicio1topicos.Constantes.TAMANHO_TELEFONE;
import static com.example.exercicio1topicos.Constantes.USUARIOS_CADASTRADOS_KEY;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty(message = CAMPO_OBRIGATORIO)
    @Length(min = 3, max = 20, message = TAMANHO_NOME)
    private TextInputEditText idNome;

    @NotEmpty(message = CAMPO_OBRIGATORIO)
    @Length(min = 13, max = 13, message = TAMANHO_TELEFONE)
    private TextInputEditText idTelefone;

    @NotEmpty(message = CAMPO_OBRIGATORIO)
    @Pattern(regex = DATA_DE_NASCIMENTO_REGEX, message = DATA_DE_NASCIMENTO_INVALIDA)
    @Length(min = 10, max = 10, message = TAMANHO_DATA_DE_NASCIMENTO)
    private TextInputEditText idDataNascimento;

    @NotEmpty(message = CAMPO_OBRIGATORIO)
    @Email(message = EMAIL_INVALIDO)
    private TextInputEditText idEmail;

    @Checked(message = CAMPO_OBRIGATORIO)
    private RadioGroup rgGenero;

    private CheckBox cbMusica;
    private CheckBox cbFilme;

    public ArrayList<Usuario> usuariosCadastrados;
    private ArrayList<CheckBox> checkBoxes;

    private Button btCadastrar;
    private Button btEnviar;

    public Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();

        validator = new Validator(this);
        validator.setValidationListener(this);

        this.btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Tela2Activity.class);

                Bundle args = new Bundle();
                args.putSerializable(USUARIOS_CADASTRADOS_KEY, usuariosCadastrados);
                intent.putExtra(BUNDLE, args);

                startActivity(intent);
            }
        });

        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
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
        Date data = new Date();
//        data.
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

    @Override
    public void onValidationSucceeded() {
        Usuario usuario = getUsuario();

        usuariosCadastrados.add(usuario);

        Toast toast = Toast.makeText(getApplicationContext(), usuario.toString(), Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String erro = error.getCollatedErrorMessage(this);

            Toast.makeText(this, erro, Toast.LENGTH_LONG).show();
        }
    }
}