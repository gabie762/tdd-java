package org.example;

import java.util.ArrayList;
import java.util.List;

public class CamelCase {
    public static List<String> converterCamelCase(String original) {
        if (ehInvalido(original)) return new ArrayList<>();
        if (todaMaiuscula(original)) return List.of(original);
        return dividirCamelCase(original);
    }

    private static boolean ehInvalido(String s) {
        return s == null || s.isEmpty();
    }

    private static boolean todaMaiuscula(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c) && !Character.isUpperCase(c)) return false;
        }
        return true;
    }

    private static List<String> dividirCamelCase(String s) {
        List<String> resultado = new ArrayList<>();
        StringBuilder atual = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            i = processarProximo(s, i, atual, resultado);
        }
        adicionarPalavra(resultado, limparEObter(atual));
        return resultado;
    }

    private static int processarProximo(String s, int i, StringBuilder atual, List<String> resultado) {
        char c = s.charAt(i);
        if (Character.isUpperCase(c)) {
            return processarMaiuscula(s, i, atual, resultado);
        }
        atual.append(c);
        return i + 1;
    }

    private static int processarMaiuscula(String s, int i, StringBuilder atual, List<String> resultado) {
        int comprimento = obterComprimentoSigla(s, i);
        if (comprimento > 0) {
            adicionarPalavra(resultado, limparEObter(atual));
            adicionarPalavra(resultado, s.substring(i, i + comprimento));
            return i + comprimento;
        }
        adicionarPalavra(resultado, limparEObter(atual));
        atual.append(s.charAt(i));
        return i + 1;
    }

    private static void adicionarPalavra(List<String> lista, String palavra) {
        if (palavra == null || palavra.isEmpty()) return;
        lista.add(todaMaiuscula(palavra) ? palavra : palavra.toLowerCase());
    }

    private static String limparEObter(StringBuilder sb) {
        String s = sb.toString();
        sb.setLength(0);
        return s;
    }

    private static int contarMaiusculasConsecutivas(String s, int inicio) {
        int i = inicio, n = s.length();
        while (i < n && Character.isUpperCase(s.charAt(i))) i++;
        return i - inicio;
    }

    private static int obterComprimentoSigla(String s, int inicio) {
        int sequencia = contarMaiusculasConsecutivas(s, inicio);
        if (sequencia <= 1) return 0;
        if (inicio + sequencia < s.length() && Character.isLowerCase(s.charAt(inicio + sequencia))) {
            return sequencia - 1;
        }
        return sequencia;
    }
}
