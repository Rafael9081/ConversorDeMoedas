
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConsultaCotacao consulta = new ConsultaCotacao();

        while (true) {
            System.out.println("*************************************************");
            System.out.println("Seja bem-vindo(a) ao Conversor de Moedas!");
            System.out.print("Digite o código da moeda de origem (ex: USD) ou 'sair' para finalizar: ");
            String moedaOrigem = leitura.nextLine().toUpperCase();

            if (moedaOrigem.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.print("Digite o código da moeda de destino (ex: BRL): ");
            String moedaDestino = leitura.nextLine().toUpperCase();

            System.out.print("Digite o valor que deseja converter: ");
            double valor;
            try {
                valor = Double.parseDouble(leitura.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido.");
                continue; // Volta para o início do loop
            }

            try {
                // 1. Busca as cotações para a moeda de origem
                Moeda moeda = consulta.buscaCotacao(moedaOrigem);
                Map<String, Double> taxas = moeda.conversionRates();

                // 2. Verifica se a moeda de destino existe na lista de cotações
                if (taxas.containsKey(moedaDestino)) {
                    // 3. Pega a taxa de conversão e calcula o resultado
                    Double taxaConversao = taxas.get(moedaDestino);
                    double valorConvertido = valor * taxaConversao;

                    // 4. Exibe o resultado formatado
                    System.out.println("-------------------------------------------------");
                    System.out.printf("O valor de %.2f %s equivale a %.2f %s\n",
                            valor, moedaOrigem, valorConvertido, moedaDestino);
                    System.out.println("-------------------------------------------------");

                } else {
                    System.out.println("Moeda de destino '" + moedaDestino + "' não encontrada nas cotações.");
                }

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Verifique se os códigos das moedas estão corretos.");
            }
        }
        System.out.println("Programa finalizado. Obrigado por usar o Conversor de Moedas!");
        leitura.close();
    }
}