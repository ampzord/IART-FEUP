import java.math.BigInteger;

public class Main {
	public static void main(String[] args) {
		System.out.println("hello world!");
		
		//Algoritmos de evolução
		
		//1 - população inicial
		
		//2 - alguns são selecionados (selected[] )
		
		//3 - os selected são emparelhados e cruzam entre si, criando os descendents[] -> reprodução
		
		//4 - possibilidade de os descendents[] sofrerem mutação
		
		//5 - Se solução, end; se não, 2;
		
		
		// Indivíduos -> acho que sao as sessões
		
		//Numero de Genes = Numero de atributos
		
		/*
		 * Sessão tem:
		 * duração, tema, papers(2 full papers no minimo), apresentador, dia da apresentação
		 * Cada elemento seria um gene. 
		 */
		
		/*
		 * Há no maximo M sessões. Logo, são 1...M individuos selecionados
		 * OU 1...M cromossomas como população inicial(most likely)
		 * Durante 3 dias e, por dia, no máximo 4 horarios para sessões, com coffee breaks e almoço.
		 */
		
		//escala-se as variaveis para inteiros multiplicando-as por 10^n, onde n = precisão
		//precisão pode ser usada para diferentes desempenhos
		
		//Transformar novas variáveis em forma binária ou usar como ints compridos
		
		/*
		 * SELEÇÃO
		 * fa(Ci) -> adaptação do Cromossoma Ci(função de adaptação)
		 * Sum(fa(ci)) -> soma das adaptações de toda a população
		 * P(Ci escolhido) = fa(Ci)/Sum(fa(ci))
		 * São selecionados alguns daqui
		 */
		
		
		/*
		 * REPRODUÇÃO
		 * Os selecionados sao emparelhados aleatoriamente
		 * Por par, com Pcrossover, pelo menos 1 crossover point é escolhido, de 1 a N(Cromossoma.size())
		 * Troca-se o material genetico
		 */
		
		/*
		 * MUTAÇÃO
		 * Pode acontecer com Pm muito baixo
		 */
		
		
		/*
		 * Conclusões tendo em conta o exemplo dos slides
		 * Cria-se 1...M cromossomas com tamanho = nº de atributos
		 * Estes cromossomas são inicializados aleatoriamente e em formato binário
		 * Aplica-se função de adaptação a esses cromossomas
		 * Calcula-se a P(Ci escolhido) para cada
		 * Roleta para serem selecionados alguns // Ou o 1...M sessões são aqui ou então é no tamanho da população
		 * CONFIRM AFTER ROLETA
		 * 
		 */	
		
		
		/*
		 * Combinações dos Papers
		 * 30 30
		 1 Hora 	+ 20 + 20 + 20   = 60			111
					+ 30 + 30		 = 60			110
					+ 20 + 30        = 50			101
					+ 20 + 20 	  	 = 40			100
					+ 30			 = 30		 	011
					+ 20 			 = 20			010
				    + 0				 = 0			001
		 */
		
		
	/*
		FUNÇÃO DE ADAPTAÇÃO:
		Após gerir população 
		
		para cada um dos casos que estamos verificando, podemos atribuir um ´fator de importancia´, 
		por exemplo, o fator de importancia para os temas de sessoes iguais é 0.2,
		o fator de importancia para o tema da sessao inserido nos papers é 0.5, etc
		dessa forma alem de calcularmos a fitness de cada uma das variantes, temos também um fator de
		importancia geral em relacao ao resto.
		
		´Filtrar´ população por dia. (criar 3 arrays, um por dia, e cada inclui sessões para esse dia)
		
		* aqui podemos também comparar os dias e retonar um valor pra funcao fitness conforme a diferenca entre
		* o tempo das duracoes.
		* ou seja, se o primeiro dia tem 3 horas de papers, o segundo tem 6 horas, a diferneca é de 3 horas, 
		* o que retornaria um fitness baixo
		* mas se o priemiro dia tem 3 horas e o segundo tem 2 horas e meia, o retorno seria um finess alto. 
		* talvez isso entre em conflito com o item abaixo, conferir qual o melhor.
		
		Por cada array
			Verificar se há temas de sessões iguais
				Se sim, escolher o que tem maior duração(da table anterior, o mais acima)
						Se = duração, não há problema
			
			Verificar se tema da sessão está contido nos temas dos papers
				buscar fitness relacionado com o nº papers associado ao tema
					(retornar valor maior se mais papers relacionados com o tema da sessão)
					
			
			
			
			
			
							
		
		
	 */
		String a = "5"; //101
		System.out.println(Utilities.transform2Bin(5, 4));

	}
}
