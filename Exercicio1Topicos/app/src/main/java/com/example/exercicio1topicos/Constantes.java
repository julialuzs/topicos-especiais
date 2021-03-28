package com.example.exercicio1topicos;

class Constantes {

    public final static String CAMPO_OBRIGATORIO = "Este campo é obrigatório";
    public static final String EMAIL_INVALIDO = "E-mail inválido";
    public static final String DATA_DE_NASCIMENTO_INVALIDA = "Data de nascimento inválida";
    public static final String TAMANHO_NOME = "Campo deve conter entre 3 e 20 caracteres";
    public static final String TAMANHO_TELEFONE = "Campo deve conter 13 caracteres";
    public static final String TAMANHO_DATA_DE_NASCIMENTO = "Campo deve conter 10 caracteres";

    public static final String DATA_DE_NASCIMENTO_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

    public static final String USUARIOS_CADASTRADOS_KEY = "usuarios";
    public static final String USUARIO_KEY = "usuario";
    public static final String BUNDLE = "bundle";
}
