package br.com.biblioteca.test;

import br.com.biblioteca.util.CalculadoraMulta;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculadoraMultaTest {

    @Test
    public void testCalcularMultaSemAtraso() {
        double multa = CalculadoraMulta.calcularMulta(0, 2.5);
        assertEquals(0.0, multa, 0.001);
    }

    @Test
    public void testCalcularMultaComAtraso() {
        double multa = CalculadoraMulta.calcularMulta(3, 2.5);
        assertEquals(7.5, multa, 0.001);
    }
}
