package org.example;

import java.util.ArrayList;
import java.util.List;

public class CamelCase {
    public static List<String> converterCamelCase(String original) {
        List<String> palavras = new ArrayList<>();
        if (original == null || original.isEmpty()) return palavras;

        // Se a string inteira for maiúscula, é uma sigla única
        if (isAllUpperCase(original)) {
            palavras.add(original);
            return palavras;
        }

        int n = original.length();
        int i = 0;
        StringBuilder current = new StringBuilder();

        while (i < n) {
            char c = original.charAt(i);
            if (Character.isUpperCase(c)) {
                int acrLen = getAcronymLength(original, i);
                if (acrLen > 0) {
                    // Se houver palavra acumulada antes da sigla, adiciona-a primeiro
                    if (current.length() > 0) {
                        palavras.add(current.toString().toLowerCase());
                        current = new StringBuilder();
                    }
                    // adiciona a sigla detectada (CPF ou similar)
                    palavras.add(original.substring(i, i + acrLen));
                    i += acrLen;
                    current = new StringBuilder();
                    continue;
                }

                // Maiúscula isolada: início de nova palavra
                if (current.length() > 0) {
                    palavras.add(current.toString().toLowerCase());
                    current = new StringBuilder();
                }
                current.append(c);
                i++;
            } else {
                current.append(c);
                i++;
            }
        }

        if (current.length() > 0) {
            palavras.add(current.toString().toLowerCase());
        }

        return palavras;
    }

    // Retorna o comprimento de uma sequência de caracteres maiúsculos começando em 'start'
    private static int countConsecutiveUppercase(String s, int start) {
        int i = start, n = s.length();
        while (i < n && Character.isUpperCase(s.charAt(i))) i++;
        return i - start;
    }

    // Detecta comprimento efetivo da sigla: retorna 0 se não for sigla, ou o comprimento da sigla
    // Ex: "CPF" -> 3 ; "CPFTal" -> 3 (pega CPF) ; "AbC" -> 0
    private static int getAcronymLength(String s, int start) {
        int run = countConsecutiveUppercase(s, start);
        if (run <= 1) return 0;
        if (start + run < s.length() && Character.isLowerCase(s.charAt(start + run))) return run - 1;
        return run;
    }

    // Retorna true se todas as letras presentes forem maiúsculas
    private static boolean isAllUpperCase(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c) && !Character.isUpperCase(c)) return false;
        }
        return true;
    }

    public static List<String> adicionarPalavraNaLista(List<String> palavras, String palavra) {
        if (palavra == null || palavra.isEmpty()) return palavras;
        if (isAllUpperCase(palavra)) {
            palavras.add(palavra);
        } else {
            palavras.add(palavra.toLowerCase());
        }
        return palavras;
    }
}
