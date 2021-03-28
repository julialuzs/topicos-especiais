package com.example.exercicio1topicos.models;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

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

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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
        return this.getNome() + "\'" +
                this.getEmail() + "\'" +
                this.getTelefone() + "\'" +
                this.getDataNascimento() +
                this.getGenero() + "\'" +
                this.getInteressesString();
    }

}
