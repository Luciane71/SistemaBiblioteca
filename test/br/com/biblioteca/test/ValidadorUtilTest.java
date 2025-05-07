package br.com.biblioteca.test;

import br.com.biblioteca.util.ValidadorUtil;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidadorUtilTest {

    @Test
    public void testISBNValido() {
        assertTrue(ValidadorUtil.isISBNValido("978-1234567890"));
    }

    @Test
    public void testISBNInvalido() {
        assertFalse(ValidadorUtil.isISBNValido("123-456"));
    }

    @Test
    public void testCPFValido() {
        assertTrue(ValidadorUtil.isCPFValido("12345678901"));
    }

    @Test
    public void testCPFInvalido() {
        assertFalse(ValidadorUtil.isCPFValido("abc123"));
    }
}