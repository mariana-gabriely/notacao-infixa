package conversor;

import estrutura.Pilha;
import java.util.ArrayList;
import java.util.List;

public class ConversorRPN {

    public String converter(String expressaoInfixa) {
        if (expressaoInfixa == null || expressaoInfixa.trim().isEmpty()) {
            throw new IllegalArgumentException("A expressão não pode ser nula ou vazia.");
        }

        List<String> tokens = tokenizar(expressaoInfixa);
        List<String> saida = new ArrayList<>();
        Pilha<String> pilhaOperadores = new Pilha<>();

        for (String token : tokens) {
            if (isNumero(token)) {
                saida.add(token);
            } else if (isOperador(token)) {
                while (!pilhaOperadores.isEmpty() && isOperador(pilhaOperadores.peek())) {
                    String topo = pilhaOperadores.peek();
                    if (precedencia(topo) >= precedencia(token)) {
                        saida.add(pilhaOperadores.pop());
                    } else {
                        break;
                    }
                }
                pilhaOperadores.push(token);
            } else if (token.equals("(")) {
                pilhaOperadores.push(token);
            } else if (token.equals(")")) {
                boolean encontrouAbertura = false;
                while (!pilhaOperadores.isEmpty()) {
                    String topo = pilhaOperadores.pop();
                    if (topo.equals("(")) {
                        encontrouAbertura = true;
                        break;
                    }
                    saida.add(topo);
                }
                if (!encontrouAbertura) {
                    throw new IllegalArgumentException("Parênteses desbalanceados na expressão.");
                }
            } else {
                throw new IllegalArgumentException("Caractere ou token inválido: " + token);
            }
        }

        while (!pilhaOperadores.isEmpty()) {
            String topo = pilhaOperadores.pop();
            if (topo.equals("(") || topo.equals(")")) {
                throw new IllegalArgumentException("Parênteses desbalanceados na expressão.");
            }
            saida.add(topo);
        }

        return String.join(" ", saida);
    }

    private List<String> tokenizar(String expressao) {
        List<String> tokens = new ArrayList<>();
        StringBuilder numeroAtual = new StringBuilder();

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (Character.isWhitespace(c)) {
                if (numeroAtual.length() > 0) {
                    tokens.add(numeroAtual.toString());
                    numeroAtual.setLength(0);
                }
                continue;
            }

            if (Character.isDigit(c) || c == '.') {
                numeroAtual.append(c);
            } else if (isOperador(String.valueOf(c)) || c == '(' || c == ')') {
                if (numeroAtual.length() > 0) {
                    tokens.add(numeroAtual.toString());
                    numeroAtual.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else {
                throw new IllegalArgumentException("Caractere inválido encontrado: " + c);
            }
        }

        if (numeroAtual.length() > 0) {
            tokens.add(numeroAtual.toString());
        }

        return tokens;
    }

    private boolean isNumero(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperador(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private int precedencia(String operador) {
        switch (operador) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }
}
