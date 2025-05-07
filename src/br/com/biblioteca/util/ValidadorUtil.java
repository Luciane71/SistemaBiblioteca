package br.com.biblioteca.util;

public class ValidadorUtil {

    public static boolean isISBNValido(String isbn) {
        return isbn != null && isbn.matches("\\d{3}-\\d{10}");
    }

    public static boolean isCPFValido(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }
}