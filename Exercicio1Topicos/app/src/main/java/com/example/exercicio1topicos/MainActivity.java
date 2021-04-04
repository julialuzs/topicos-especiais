package com.example.exercicio1topicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.exercicio1topicos.models.Usuario;
import com.github.rtoshiro.util.format.MaskFormatter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.pattern.MaskPattern;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private TextInputLayout txtNome;
    private TextInputLayout txtEmail;
    private TextInputLayout txtTelefone;
    private TextInputLayout txtDataNascimento;

    @NotEmpty(message = CAMPO_OBRIGATORIO)
    @Length(min = 3, max = 20, message = TAMANHO_NOME)
    private TextInputEditText etNome;

    @NotEmpty(message = CAMPO_OBRIGATORIO)
    @Length(min = 14, max = 14, message = TAMANHO_TELEFONE)
    private TextInputEditText etTelefone;

    @NotEmpty(message = CAMPO_OBRIGATORIO)
    @Pattern(regex = DATA_DE_NASCIMENTO_REGEX, message = DATA_DE_NASCIMENTO_INVALIDA)
    @Length(min = 10, max = 10, message = TAMANHO_DATA_DE_NASCIMENTO)
    private TextInputEditText etDataNascimento;

    @NotEmpty(message = CAMPO_OBRIGATORIO)
    @Email(message = EMAIL_INVALIDO)
    private TextInputEditText etEmail;

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

        usuario.setEmail(this.etEmail.getText().toString());
        usuario.setNome(this.etNome.getText().toString());
        usuario.setTelefone(this.etTelefone.getText().toString());

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


        Editable date = this.etDataNascimento.getText();
        String dateString = date.toString();


        usuario.setDataNascimento(dateString);

        return usuario;
    }

    public ArrayList<Usuario> getUsuariosCadastrados() {
        return usuariosCadastrados;
    }

    private void inicializarComponentes() {
        this.btCadastrar = this.findViewById(R.id.btCadastrar);
        this.btEnviar = this.findViewById(R.id.btEnviar);

        this.txtNome = this.findViewById(R.id.txtNome);
        this.txtEmail = this.findViewById(R.id.txtEmail);
        this.txtTelefone = this.findViewById(R.id.txtTelefone);
        this.txtDataNascimento = this.findViewById(R.id.txtDataNascimento);

        this.etNome = this.findViewById(R.id.idNome);
        this.etTelefone = this.findViewById(R.id.idTelefone);
        this.etDataNascimento = this.findViewById(R.id.idDataNascimento);
        this.etEmail = this.findViewById(R.id.idEmail);

        this.rgGenero = this.findViewById(R.id.rgGenero);
        this.cbMusica = this.findViewById(R.id.cbMusica);
        this.cbFilme = this.findViewById(R.id.cbFilme);

        this.usuariosCadastrados = new ArrayList<Usuario>();

        this.checkBoxes = new ArrayList<CheckBox>();
        this.checkBoxes.add(cbFilme);
        this.checkBoxes.add(cbMusica);

        validator = new Validator(this);
        validator.setValidationListener(this);

        this.setarMascaras();
    }

    @Override
    public void onValidationSucceeded() {
        Usuario usuario = getUsuario();

        usuariosCadastrados.add(usuario);

        this.limparInputs();

        Toast toast = Toast.makeText(getApplicationContext(), usuario.toString(), Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {

            View view = error.getView();
            String mensagemErro = error.getCollatedErrorMessage(this);

            if (view instanceof TextInputEditText) {

                switch (view.getId()) {
                    case R.id.idNome:
                        this.txtNome.setError(mensagemErro);
                        break;
                    case R.id.idEmail:
                        this.txtEmail.setError(mensagemErro);
                        break;
                    case R.id.idTelefone:
                        this.txtTelefone.setError(mensagemErro);
                        break;
                    case R.id.idDataNascimento:
                        this.txtDataNascimento.setError(mensagemErro);
                        break;
                }
            }

        }
    }

    private void setarMascaras() {
        MaskFormatter dateFormatter = new MaskFormatter("[0-3][0-9]/[0-1][0-9]/[0-9][0-9][0-9][0-9]");

        dateFormatter.registerPattern(new MaskPattern("[0-3]"));
        dateFormatter.registerPattern(new MaskPattern("[0-9]"));
        dateFormatter.registerPattern(new MaskPattern("[0-1]"));

        MaskTextWatcher mtd = new MaskTextWatcher(this.etDataNascimento, dateFormatter);
        this.etDataNascimento.addTextChangedListener(mtd);

        SimpleMaskFormatter telefoneFormatter = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtt = new MaskTextWatcher(this.etTelefone, telefoneFormatter);
        this.etTelefone.addTextChangedListener(mtt);
    }

    private void limparInputs() {
       this.etTelefone.setText("");
       this.etDataNascimento.setText("");
       this.etEmail.setText("");
       this.etNome.setText("");
       this.rgGenero.clearCheck();
       this.cbMusica.setChecked(false);
       this.cbFilme.setChecked(false);
    }
}