package view;

import java.util.Scanner;
import controller.CalculadoraController;
import model.Calculadora;

public class CalculadoraView {

    public static void menu() {
        Scanner scan = new Scanner(System.in);
        String valorInicial;
        String valorMensal;
        String taxaJuros;
        String periodo;

        System.out.println("Valor inicial (R$):");
        valorInicial = scan.nextLine();
        System.out.println("Valor mensal (R$):");
        valorMensal = scan.nextLine();
        System.out.println("Taxa de juros (% / MÃªs):");
        taxaJuros = scan.nextLine();
        System.out.println("PerÃ­odo (Mensal):");
        periodo = scan.nextLine();
        
        CalculadoraController.calcular(valorInicial, valorMensal, taxaJuros, periodo);
    }

    public static void resultados(Calculadora calc) {
        System.out.println("\n------ Resultados ------\n");

        System.out.println("Valor total final (R$):");
        System.out.println(calc.getValorTotalFinal());
        System.out.println("Valor total investido (R$):");
        System.out.println(calc.getValorTotalInvestido());
        System.out.println("Total em juros (R$):");
        System.out.println(calc.getValorTotalJuros());
        System.out.println("Total de imposto de renda (R$):");
    }

    public static void excecao(int idExcecao) {
        switch (idExcecao) {
            case 1:
                System.out.println("Erro no valor inicial.");
                break;
            case 2:
                System.out.println("Erro no valor mensal.");
                break;
            case 3:
                System.out.println("Erro na taxa de juros.");
                break;
            case 4:
                System.out.println("Erro no periodo.");
                break;
            default:
                break;
        }

        System.out.println("Tente novamente.\n");
    }
}