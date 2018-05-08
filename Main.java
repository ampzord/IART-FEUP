import java.math.BigInteger;

public class Main {
	public static void main(String[] args) {

	
		/*
		 * H� no maximo M sess�es. Logo, s�o 1...M individuos selecionados OU 1...M
		 * cromossomas como popula��o inicial(most likely) Durante 3 dias e, por dia, no
		 * m�ximo 4 horarios para sess�es, com coffee breaks e almo�o.
		 */

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

		/*
		 * Conclus�es tendo em conta o exemplo dos slides Cria-se 1...M cromossomas com
		 * tamanho = n� de atributos Estes cromossomas s�o inicializados aleatoriamente
		 * e em formato bin�rio Aplica-se fun��o de adapta��o a esses cromossomas
		 * Calcula-se a P(Ci escolhido) para cada Roleta para serem selecionados alguns
		 * // Ou o 1...M sess�es s�o aqui ou ent�o � no tamanho da popula��o CONFIRM
		 * AFTER ROLETA
		 * 
		 */

		/*
		 * Combina��es dos Papers 30 30 1 Hora + 20 + 20 + 20 = 60 111 + 30 + 30 = 60
		 * 110 + 20 + 30 = 50 101 + 20 + 20 = 40 100 + 30 = 30 011 + 20 = 20 010 + 0 = 0
		 * 001
		 * 
		 * 
		 * FUN��O DE ADAPTA��O: Ap�s gerir popula��o
		 * 
		 * para cada um dos casos que estamos verificando, podemos atribuir um �fator de
		 * importancia�, por exemplo, o fator de importancia para os temas de sessoes
		 * iguais � 0.2, o fator de importancia para o tema da sessao inserido nos
		 * papers � 0.5, etc dessa forma alem de calcularmos a fitness de cada uma das
		 * variantes, temos tamb�m um fator de importancia geral em relacao ao resto.
		 * 
		 * �Filtrar� popula��o por dia. (criar 3 arrays, um por dia, e cada inclui
		 * sess�es para esse dia)
		 * 
		 * aqui podemos tamb�m comparar os dias e retonar um valor pra funcao fitness
		 * conforme a diferenca entre o tempo das duracoes. ou seja, se o primeiro dia
		 * tem 3 horas de papers, o segundo tem 6 horas, a diferneca � de 3 horas, o que
		 * retornaria um fitness baixo mas se o priemiro dia tem 3 horas e o segundo tem
		 * 2 horas e meia, o retorno seria um finess alto. talvez isso entre em conflito
		 * com o item abaixo, conferir qual o melhor.
		 * 
		 * Por cada array(cada dia)
		 * 
		 * Verificar se tema da sess�o est� contido nos temas dos papers Se houver algum
		 * tema que n�o fa�a parte, retorna 0.
		 * 
		 * Verificar se h�, no m�nimo, dois full papers Se n�o h�, retorna 0;
		 * 
		 * Verificar se cada apresentador est� associado a cada paper Se n�o, retorna 0.
		 * 
		 * 
		 * Verificar se h� temas de sess�es iguais Se sim, escolher o que tem maior
		 * dura��o(da table anterior, o mais acima)
		 * 
		 * 
		 * perguntar ao professor se devemos retirar papers n�o relacionados com a
		 * sess�o(ap�s decidirmos se � melhor ou n�o)
		 * 
		 */

		/*
		 * H�, no m�ximo, 4 * M sess�es por dia(M = n�mero de sess�es em paralelo).
		 * 
		 * 
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
		String cromo = "01 11 11     001 11 10 11 10 11 1   01 00 01 10 11 1    1111001  00000110111  10100000011";
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
		 * verificar se o apresentador est� representador
		 * 
		 * ap�s gerar aleat�riamente, � criada um objeto Conference que faz parse do
		 * cromossoma todo
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

	}
}
