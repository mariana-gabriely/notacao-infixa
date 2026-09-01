import avaliador.AvaliadorRPN;
import conversor.ConversorRPN;

public class Main {

    public static void main(String[] args) {
        ConversorRPN conversor = new ConversorRPN();
        AvaliadorRPN avaliador = new AvaliadorRPN();

        System.out.println("Exemplo 1:");
        processarExpressao("( 6 + 2 ) * 5 - 8 / 4", conversor, avaliador);
        System.out.println();

        System.out.println("Exemplo 2:");
        processarExpressao("12.5 + 7.5 * 2.0 - 6.0 / 3.0", conversor, avaliador);
        System.out.println();

        System.out.println("Exemplo 3:");
        processarExpressao("( ( 8.4 - 3.4 ) * ( 1.5 + 2.5 ) ) / 4.0", conversor, avaliador);
        System.out.println();

        System.out.println("Exemplo 4:");
        processarExpressao("45.0 / ( 9.0 - 4.0 ) + 3.2 * 5.0", conversor, avaliador);
        System.out.println();

        System.out.println("Exemplo 5:");
        processarExpressao("( 100.0 - 25.0 ) / 5.0 + ( 4.5 * 2.0 ) - 7.0", conversor, avaliador);
    }

    private static void processarExpressao(String expressaoInfixa, ConversorRPN conversor, AvaliadorRPN avaliador) {
        try {
            String expressaoRPN = conversor.converter(expressaoInfixa);
            double resultado = avaliador.avaliar(expressaoRPN);

            System.out.println("Expressão Original: " + expressaoInfixa);
            System.out.println("Expressão RPN     : " + expressaoRPN);
            System.out.println("Resultado         : " + resultado);
        } catch (Exception e) {
            System.out.println("Expressão Original: " + expressaoInfixa);
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
