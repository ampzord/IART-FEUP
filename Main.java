


public class Main {
	public static void main(String[] args) {
		System.out.println("hello world!");
		
		//Algoritmos de evolução
		
		//1 - população inicial
		
		//2 - alguns são selecionados (selected[] )
		
		//3 - os selected são emparelhados e cruzam entre si, criando os descendents[] -> reprodução
		
		//4 - possibilidade de os descendents[] sofrerem mutação
		
		//5 - Se solução, end; se não, 2;
		
		
		// Indivíduos -> acho que sao as sessões(most likely) ou então os dias.
		
		//Numero de Genes = Numero de atributos
		
		/*
		 * Sessão tem:
		 * duração, tema, papers(2 full papers no minimo), apresentador 	
		 * Cada elemento seria um gene. 
		 */
		
		/*
		 * Há no maximo M sessões. Logo, são 1...M individuos selecionados
		 * OU 1...M cromossomas como população inicial(most likely)
		 * Durante 3 dias e, por dia, no máximo 4 horarios para sessões, com coffee breaks e almoço.
		 */
		
		//escala-se as variaveis para inteiros multiplicando-as por 10^n, onde n = precisão
		//precisão pode ser usada para diferentes desempenhos
		
		//Transformar novas variáveis em forma binária
		
		
		
		/*
		 * SELEÇÃO
		 * fa(Ci) -> adaptação do Cromossoma Ci(função de adaptação)
		 * Sum(fa(ci)) -> soma das adaptações de toda a população
		 * P(Ci escolhido) = fa(Ci)/Sum(fa(ci))
		 * São selecionados alguns daqui
		 */
		
		
		/*
		 * REPRODUÇÃO
		 * Os eelcionados sao emparelhados aleatoriamente
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
		

	}

}
