package controller;

import model.Calculadora;
import view.CalculadoraView;
import java.lang.Math;

public class CalculadoraController {
   
    public static void calcular(String valorInicial, String valorMensal, String taxaJuros, String periodo) {
        Calculadora calc = new Calculadora();
        try {
            //Dados de entrada 
            calc.setValorInicialInvestido(Float.parseFloat(valorInicial));
            calc.setValorMensalInvestido(Float.parseFloat(valorMensal));
            calc.setRendimentoMensal(Float.parseFloat(taxaJuros)/100f);
            calc.setPeriodoMensal(Integer.parseInt(periodo));
            
            //calculos resultantes
            calc.setValorTotalFinal(calcularValorTotalFinal(calc.getValorInicialInvestido(),calc.getRendimentoMensal(),calc.getValorMensalInvestido(),calc.getPeriodoMensal()));
            calc = calculaTotalInvestido(calc);
            calc = calculaTotalJuros(calc);
            CalculadoraView.resultados(calc);
            } catch (Exception e) {
                CalculadoraView.excecao(1);
            }
    }
    
    //função que calcula o total investido
    public static Calculadora calculaTotalInvestido(Calculadora calc){
        calc.setValorTotalInvestido(calc.getValorInicialInvestido() + (calc.getPeriodoMensal()*calc.getValorMensalInvestido()));
        return calc;
    }
    
    //Função que calcula o toal de juros
    public static Calculadora calculaTotalJuros(Calculadora calc){
        int diaRetirado = calc.getPeriodoMensal()*30;
        float aliquota;
        //Setando valor da aliquota
        if(diaRetirado <=180){
            aliquota = 22.5f;
        }else if(diaRetirado <=360){
            aliquota = 20f;
        }else if(diaRetirado <=720){
            aliquota = 17.5f;
        }else{
            aliquota = 20f;
        }
        
        calc.setValorTotalJuros(calc.getValorTotalFinal() - calc.getValorTotalInvestido());
        calc.setValorImpostoRenda((calc.getValorTotalJuros()*aliquota)/100);
        calc.setValorTotalJuros(calc.getValorTotalJuros()-calc.getValorImpostoRenda());
        calc.setValorTotalFinal(calc.getValorTotalFinal()-calc.getValorImpostoRenda());
        return calc;
    }
   
    
    //função recusiva para calcular o juros
    public static float calcularValorTotalFinal(float capital, float juros, float aporte, int periodo) {
    // M = (C x (1 + ((i / 100)) ^ n)) + (a × (((((1 + (i / 100))) ^ n )− 1) ÷ (i / 100)))
        float valorTotalFinal = (float) ((float) (capital*(Math.pow(1+juros, periodo))) + (aporte*(Math.pow(1+juros, periodo)-1)/(juros)));
        return valorTotalFinal;
    }
}