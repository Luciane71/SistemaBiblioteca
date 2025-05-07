package br.com.biblioteca.test;

import br.com.biblioteca.controller.EmprestimoController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmprestimoControllerTest {

    public EmprestimoControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCalcularMultaSemAtraso() {
        EmprestimoController controller = new EmprestimoController();
        double multa = controller.calcularMulta(0, 2.5);
        assertEquals(0.0, multa, 0.001);
    }

    @Test
    public void testCalcularMultaComAtraso() {
        EmprestimoController controller = new EmprestimoController();
        double multa = controller.calcularMulta(3, 2.5);
        assertEquals(7.5, multa, 0.001);
    }
}
