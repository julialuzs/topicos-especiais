package com.example.exercicio1topicos.models;

import android.text.TextUtils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.exercicio1topicos.Constantes.PADRAO_DATA_DE_NASCIMENTO;

public class Usuario implements Serializable {
    private String nome;
    private String email;
    private String telefone;
    private Date dataNascimento;
    private String genero;
    private ArrayList<String> interesses;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getDataNascimentoString() {
        DateFormat df = new SimpleDateFormat(PADRAO_DATA_DE_NASCIMENTO);
        return df.format(dataNascimento);
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setDataNascimento(String dataNascimentoString) {
        DateFormat df = new SimpleDateFormat(PADRAO_DATA_DE_NASCIMENTO);

        try {
            Date data = df.parse(dataNascimentoString);
            this.dataNascimento = data;

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public ArrayList<String> getInteresses() {
        return interesses;
    }

    public void setInteresses(ArrayList<String> interesses) {
        this.interesses = interesses;
    }

    public String getInteressesString() {
        return TextUtils.join(", ", interesses);
    }

    @Override
    public String toString() {
        return this.getNome() + "\n" +
                this.getEmail() + "\n" +
                this.getTelefone() + "\n" +
                this.getDataNascimentoString() + "\n" +
                this.getGenero() + "\n" +
                this.getInteressesString();
    }

}
