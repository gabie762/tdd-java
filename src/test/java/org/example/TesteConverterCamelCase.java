package org.example;

import java.util.List;

import junit.framework.TestCase;

// Unit test for simple converterCameCase.

/* REQUISITOS:
    1. O metodo recebe uma palavra e retonar a lista de uma palavra com letra inicial minuscula.

    2. O metodo recebe mais de uma palavra em camelCase e retonar uma lista de palavras minusculas.

    3. O metodo recebe uma sigla maiuscula e retonar a sigla em maiuscula.

 */

public class TesteConverterCamelCase extends TestCase {

    public void testConverteUmaPalavra() {
        assertEquals(List.of("nome"), CamelCase.converterCamelCase("nome"));
    }

    public void testConverteUmaPalavraMaiuscula() {
        assertEquals(List.of("nome"), CamelCase.converterCamelCase("Nome"));
    }

    public void testConverteMaisDeUmaPalavra() {
        List<String> resultado = CamelCase.converterCamelCase("nomeComposto");
        assertEquals(List.of("nome", "composto"), resultado);
    }

    public void testConverteMaisDeUmaPalavraComMaiusculaInicial() {
        List<String> resultado = CamelCase.converterCamelCase("NomeComposto");
        assertEquals(List.of("nome", "composto"), resultado);
    }

    public void testSiglaMaiuscula(){
        assertEquals(List.of("CPF"), CamelCase.converterCamelCase("CPF"));
    }

    public void testePalavraeSiglaMaiuscula(){
        List<String> resultado = CamelCase.converterCamelCase("numeroCPF");
        assertEquals(List.of("numero","CPF"), resultado);
    }

    public void testSiglaMaiusculaComPalavra(){
        List<String> resultado = CamelCase.converterCamelCase("numeroCPFContribuinte");
        assertEquals(List.of("numero","CPF", "contribuinte"), resultado);
    }
}
