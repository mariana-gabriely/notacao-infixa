package avaliador;

import estrutura.Pilha;

public class AvaliadorRPN {

    public double avaliar(String expressaoRPN) {
        if (expressaoRPN == null || expressaoRPN.trim().isEmpty()) {
            throw new IllegalArgumentException("A expressão RPN não pode ser nula ou vazia.");
        }

        String[] tokens = expressaoRPN.trim().split("\\s+");
        Pilha<Double> pilha = new Pilha<>();

        for (String token : tokens) {
            if (isNumero(token)) {
                pilha.push(Double.parseDouble(token));
            } else if (isOperador(token)) {
                if (pilha.size() < 2) {
                    throw new IllegalArgumentException("Expressão RPN inválida: operandos insuficientes para o operador '" + token + "'.");
                }
                double operando2 = pilha.pop();
                double operando1 = pilha.pop();
                double resultado = executarOperacao(operando1, operando2, token);
                pilha.push(resultado);
            } else {
                throw new IllegalArgumentException("Token inválido na expressão RPN: " + token);
            }
        }

        if (pilha.size() != 1) {
            throw new IllegalArgumentException("Expressão RPN malformada: quantidade incorreta de operadores e operandos.");
        }

        return pilha.pop();
    }

    private double executarOperacao(double a, double b, String operador) {
        switch (operador) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0.0) {
                    throw new ArithmeticException("Divisão por zero não permitida.");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Operador desconhecido: " + operador);
        }
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
}
