

public class Main {
	public static void main(String[] args) {

	
		// escala-se as variaveis para inteiros multiplicando-as por 10^n, onde n =
		// precis�o
		// precis�o pode ser usada para diferentes desempenhos

		// Transformar novas vari�veis em forma bin�ria ou usar como ints compridos

		/*
		 * SELE��O fa(Ci) -> adapta��o do Cromossoma Ci(fun��o de adapta��o) Sum(fa(ci))
		 * -> soma das adapta��es de toda a popula��o P(Ci escolhido) =
		 * fa(Ci)/Sum(fa(ci)) S�o selecionados alguns daqui
		 */

		/*
		 * REPRODU��O Os selecionados sao emparelhados aleatoriamente Por par, com
		 * Pcrossover, pelo menos 1 crossover point � escolhido, de 1 a
		 * N(Cromossoma.size()) Troca-se o material genetico
		 */

		/*
		 * MUTA��O Pode acontecer com Pm muito baixo
		 */

	
		
		// DIA HORA TEMA DURA PAPERS full
		

		System.out.println("Cromossomo padrao com 1 dia, 1 sessao e 1 papers");
//		 		 DIA    HORA    TEMA     DURA    AA AA	TT TT   full
//String cromo = "01 -  11      11       001     00 01  10 11   1";

//		String cromo = "01111100101000110111";
//		Conference c1 = new Conference(cromo);
//		System.out.println(c1);


		
		System.out.println("Cromossomo padrao com 1 dia, 2 sessao e 1 papers");
//		          DIA    HORA   TEMA    DURA    AP AA AA	TT TT   full       Sessao 2
//String cromo = "01 -  11      11       001    00  00 01  10 11   1          0111001  01 000110111

//		String cromo = "01111100100000110111111100100000110111";
//		Conference c1 = new Conference(cromo);
//		System.out.println(c1);


		
		System.out.println("Cromossomo padrao com 1 dia, 2 sessao e 2 papers");
//                DIA    HORA   TEMA    DURA   AP AA AA	TT TT  full  papper2        Sessao 2		     paper 2
//String cromo = "01 -  11      11       001   00  00 01  10 11  1          			01 11 001 000110111  011110110 
							//           paper				paper 2
		// Cromossomo com apresentadores diferentes e funcionando
		String cromo = "01 11 11     111 11 10 11 10 11 1   01 00 01 10 11 1    1111001  00000110111  10100000011";
		Conference c1 = new Conference(cromo);
		System.out.println(c1);

		

		System.out.println("Cromossomo padrao com 2 dia, 1 sessao e 2 papers");
//                DIA    HORA   TEMA    DURA    AA AA	TT TT  full  papper2        Sessao 2		     paper 2
//String cromo = "01 -  11      11       001     00 01  10 11  1          			01 11 001 000110111  011110110 

//		String cromo = "0011110010001101110111101100011001000110111011110110"; 
//		Conference c1 = new Conference(cromo);
//		System.out.println(c1);
		
		
		System.out.println("score final: " + Genetic.getScore(c1));

		// <DIA> <SESS�ES> <DIA> <SESS�ES> <DIA> <SESS�ES>, para 3 dias
		// * Ex:
		// * 00 01010101010 01 00000000000 10 11101010001
		// <SESSOES>
		// TT DD PPPPPP PPPPPPP

		/*
		 * THE REAL DEAL NOW
		 * 
		 * Indiv�duo : uma solu��o que representa TODOS os dias e todas as sess�es
		 * associadas
		 * 
		 * <DIA> <SESS�ES> <DIA> <SESS�ES> <DIA> <SESS�ES>, para 3 dias Ex: 00
		 * 01010101010 01 00000000000 10 11101010001
		 * 
		 * 
		 * <Sess�es>: XX YYYYYYYYY XX representa o hor�rio da sess�o(inicio/fim da
		 * manh�/tarde) YYYY.. represneta o getGenome da classe session
		 * 
		 * 
		 */

	}
}
