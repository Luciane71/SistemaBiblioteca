package br.com.biblioteca.util;

public class CalculadoraMulta {

    public static double calcularMulta(long diasAtraso, double valorPorDia) {
        if (diasAtraso <= 0) return 0.0;
        return diasAtraso * valorPorDia;
    }
}

