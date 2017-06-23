import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

public class Lixo {
	// Função que analisa as casas livres para jogadas futuras no tabuleiro
		public static ArrayList<Button[]> sucessor3(Button[] btn, String nivel) {
			//System.out.println("\t\t\tEntrou em [sucessor]");
			// Estrutura que me retorna todas as casas do tabuleiros livres para a próxima jogada
			ArrayList<Button[]> todosFilhos = new ArrayList<Button[]>();
			ArrayList<Integer> casasLivres = sucessor2(btn);
			// 
			// Percorre todas as casas do tabuleiro
			//System.out.println("\t\t\tEntrou no FOR de [sucessor]");
			for (int i = 0; i < 9; i++) {
				//System.out.println("\t\t\t\tEsta analisando o Button "+i+" do FOR de [sucessor]");
				// Se encontra uma casa vazia:
				if (btn[i].getText().equals("")) {
					Button[] filhos = new Button[9];
					
					// Copia TODAS as casas para uma estrutura chamada filhos
					//System.out.println("\t\t\t\tEntrou no FOR2 de [sucessor]");
					 for (int j = 0; j < 9; j++) {
						 //System.out.println("\t\t\t\t\tEsta copiando o conteudo do Button "+j+" para Filho "+j+" do FOR2 de [sucessor]");
						// filhos[j] = btn[j].getText();
						 filhos[j] = btn[j];
					 }
					//System.out.println("\t\t\t\tSaiu do FOR2 de [sucessor]");
					 
					 // Verifica nível
					 // Se nivel = MAX, gera jogada sucessora com O
					 if (nivel.equals("MAX")) {
						 //filhos[i] = "o";
						 if (filhos[i]) {
							 
						 }
						 filhos[i].setText("o");
						 
					 // Se nivel = MIN, gera jogada sucessora com X	 
					 } else {
						 //filhos[i] = "x";
						 filhos[i].setText("x");
					 }
					 
					 // Adiciona casa para estrutura de casas livres
					todosFilhos.add(filhos);
					
					// LIXO
					//if (nivel.equals("MAX")) {
						//filhos[i].setText("");
					 //} else {
						//filhos[i].setText("");
					//}
					// LIXO
				}
			}		
			//System.out.println("\t\t\tSaiu do FOR de [sucessor]");
			
			// Retorno
			// Se não houverem casas vazias (ou seja, jogadas sucessoras (filhos)) retorna null
			if (todosFilhos.size() == 0) {
				//System.out.println("\t\t\tNão há casas vazias no tabuleiro atual");
				return null;
			// Se houverem casas vazias retorna a lista de casas livres para jogada no tabuleiro	
			} else {
				//System.out.println("\t\t\tHá casas vazias no tabuleiro atual: "+casasLivres.size());
				return todosFilhos;
			}
		}
		
		public static Tabela Resultado(ArrayList<Tabela> listaPontos, String nivel) {
			//System.out.println("\t\t\tSaiu de [minmax] e entrou em [Resultado]");
			Tabela resultado = listaPontos.get(0);
			
			// Se o nivel atual é MAX
			if (nivel.equals("MAX")) {
				// Percorre lista de pontos com os objetos tipo Tabela (tabuleiro, ponto, profundidade)
				for (int i = 0; i < listaPontos.size(); i++) {
					if ((listaPontos.get(i).pegaPontos() > resultado.pegaPontos()) ||
						(listaPontos.get(i).pegaPontos() == resultado.pegaPontos() && listaPontos.get(i).profundidade < resultado.profundidade)) {
						resultado = listaPontos.get(i);					
					}
				}			
			// Se o nivel atual é MIN	
			} else {
				for (int i = 0; i < listaPontos.size(); i++) {
					if ((listaPontos.get(i).pegaPontos() < resultado.pegaPontos()) ||
						(listaPontos.get(i).pegaPontos() == resultado.pegaPontos() && listaPontos.get(i).profundidade < resultado.profundidade)) {
						resultado = listaPontos.get(i);
					}
				}
			}
			
			return resultado;
		}
		
		//INCOMPLETO
		// Metodo MinMax
		// 1 - Gera a melhor casa pro movimento do PC
		// 2 - Se não há uma casa ou jogo é finalizado retorna score
		// 3 - Se há sucessor:
		//		a) Aplica MinMax pro sucessor
		//		b) Após a chamada recursiva, i retorna a melhor casa no tabuleiro
	 	public static /*Tabela*/ void minmax(Button[] tabuleiro, String nivel, int profundidade)	{
	 		//System.out.println("\t\tEntrou em [minmax]");
	 		// -------- 1 
			ArrayList<Button[]> filhos = sucessor(tabuleiro,nivel);	
			//System.out.println("\t\tSaiu de [sucessor] e voltou para [minmax]");
			
			while(profundidade <= 2) {		
				for (int i = 0; i < filhos.size(); i++) {
					System.out.println("\n\nFILHO ["+i+"]");
					Button[] teste = filhos.get(i);
					for (int j = 0; j < teste.length; j++) {
						System.out.print("casa["+j+"] = "+teste[j].getText()+" || ");
					}
					
					//minmax(teste,inverteNivel(nivel),profundidade+1);
				}
			}
			
			//minmax(filhos.get(0),inverteNivel(nivel));
			
			//return new Tabela(tabuleiro,1,1);
			
			// -------- 2
			//if(filhos == null && profundidadeFinal != -1) {
				//profundidadeFinal = -1;
				//profundidade = profundidade + 1;
			//}
			
			/*if (filhos == null || verificaSeHaVencedor(tabuleiro)) {
				 return new Tabela(tabuleiro, verificaPlacar2(tabuleiro), profundidade);
			// -------- 3		 
			} else {
				// Passa por cada filho
				// -------- 3.a
				if (profundidadeFinal > filhos.size()) {
					profundidadeFinal = filhos.size();
					profundidade = profundidade + 1;
				} 
				
				ArrayList<Tabela> listaPontos = new ArrayList<Tabela>();
				
				for (int i = 0; i < filhos.size(); i++) {
					listaPontos.add(minmax(filhos.get(i), inverteNivel(nivel), profundidade));
				}	
				
				// -------- 3.b
				//Tabela resultado = Resultado(listaPontos, nivel);
				//System.out.println("\t\t\tSaiu de [Resultado] e voltou para [minmax]");
				Tabela resultado = listaPontos.get(0);
				
				// Se o nivel atual é MAX
				if (nivel.equals("MAX")) {
					// Percorre lista de pontos com os objetos tipo Tabela (tabuleiro, ponto, profundidade)
					for (int i = 0; i < listaPontos.size(); i++) {
						if ((listaPontos.get(i).pegaPontos() > resultado.pegaPontos()) ||
							(listaPontos.get(i).pegaPontos() == resultado.pegaPontos() && listaPontos.get(i).profundidade < resultado.profundidade)) {
							resultado = listaPontos.get(i);					
						}
					}			
				// Se o nivel atual é MIN	
				} else {
					for (int i = 0; i < listaPontos.size(); i++) {
						if ((listaPontos.get(i).pegaPontos() < resultado.pegaPontos()) ||
							(listaPontos.get(i).pegaPontos() == resultado.pegaPontos() && listaPontos.get(i).profundidade < resultado.profundidade)) {
							resultado = listaPontos.get(i);
						}
					}
				}
				
				//return resultado;
				//if (fils == 1) {
					resultado.atualizaTabuleiro(tabuleiro);
				//}
				
				return resultado;
			}*/
		}
		
		// Função que inverte nivel MAX -> MIN e MIN -> MAX 
	 	public static String inverteNivel(String nivel) { 
			if (nivel.equals("MAX")) {
				nivel = "MIN";
			} else {
				nivel = "MAX";
			}
			
			return nivel;
		}
	 	
	 // Função que analisa as casas livres para jogadas futuras no tabuleiro
		public static ArrayList<Button[]> sucessor(Button[] btn, String nivel) {
			// Estrutura que me retorna todas as casas do tabuleiros livres para a próxima jogada
			ArrayList<Button[]> todosFilhos = new ArrayList<Button[]>();
			ArrayList<Integer> casasLivres = sucessor2(btn);
			
			if (verificaTabuleiro(btn) == true) {
				for (int i = 0; i < casasLivres.size(); i++) {
					Button[] jogadaFutura = btn;
					int posicao = casasLivres.get(i);
					
					if (nivel.equals("MAX")) {
						jogadaFutura[posicao].setText("o");
					} else {
						jogadaFutura[posicao].setText("x");
					}
					
					todosFilhos.add(jogadaFutura);
				}			
			}
			
			
			// Retorno
			// Se não houverem casas vazias (ou seja, jogadas sucessoras (filhos)) retorna null
			if (todosFilhos.size() == 0) {
				//System.out.println("\t\t\tNão há casas vazias no tabuleiro atual");
				return null;
			// Se houverem casas vazias retorna a lista de casas livres para jogada no tabuleiro	
			} else {
				//System.out.println("\t\t\tHá casas vazias no tabuleiro atual: "+casasLivres.size());
				return todosFilhos;
			}
		}
		
		public static void rodadaPC(Button[] btn) {
			//System.out.println("Entrou em [rodadaPC]");
			//Tabela jogadaPC = minmax(btn,"MAX",0);
			//System.out.println("Saiu de [minmax] e voltou para [rodadaPC]");
			//int casa = jogadaPC.pegaCasa();		
			//System.out.println("Casa que o PC selecionou foi: "+casa);
			//btn[casa].setText("O");
		}
		
		// Cria nove botões (CASAS) e indica o que acontece quando clicar em um deles
		for (int i = 0; i < 9; i++) {
			btn[i] = new Button(shlJogo, SWT.NONE);
			btn[i].setFont(SWTResourceManager.getFont("Segoe UI", 40, SWT.BOLD));
			
			// Resolve o erro da linha 132: "Local variable i defined in an enclosing scope must be final or effectively final"
			final Integer innerI = new Integer(i);
			
			// Adiciona evento de clique em cada Button
			btn[i].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					// Verifica se posição está disponível
					if (btn[innerI].getText().equals("X") || btn[innerI].getText().equals("O")) {
						// Mensagem de erro: Seleção Inválida
						System.out.println("\nPosição inválida, selecione outra!\n");
					// Tabuleiro não cheio e posição válida	
					} else {
						btn[innerI].setText("X");
						//btn[innerI].setForeground(Color.pink);
						// Verifica se o tabuleiro está cheio
						if (verificaTabuleiro(btn) == false) {
							// Tabuleiro cheio -> FIM DE JOGO
							System.out.println("\nTabuleiro cheio! Fim de jogo\n");
							// Verifica se há vencedor ou empatou
							if (verificaPlacar2(btn) == 0) {
								// Empate
								System.out.println("Empate\n");
								numEmpates++;
								lblEmpates.setText("EMPATES: "+numEmpates);
							} else if (verificaPlacar2(btn) == 1) {
									System.out.println("Vencedor: PC \n");
									numDerrotas++;
									lblDerrotas.setText("DERROTAS: "+numDerrotas);
							} else {
									System.out.println("Vencedor: VOCÊ! \n");
									numVitorias++;
									lblVitorias.setText("VIT\u00D3RIAS: "+numVitorias);
							}
						} else {
							//ArrayList<Integer> cLivres = new ArrayList<Integer>();
							//cLivres = sucessor2(btn);
							//minmax(btn,"MAX",0);
							//cLivres = sucessor2(btn);
							rodadaPC(btn);
								
						}
					}
				}
			});
		}
		
		
		// Função que verifica se o jogo tem vencedor
		/*public static boolean verificaSeHaVencedor(Button[] btn) {
			 //if the score of the game is 0 then return false else we have a winner  
		     return (verificaPlacar2(btn)!= 0) ? true : false;  
		}*/ 
		
		// Função que verifica se casas vencedoras estão disponíveis
		public static boolean verificaPosicaoVencedora(Button[] btn) {
		    if( (btn[0].getText().equals("") && btn[1].getText().equals("") && btn[2].getText().equals("")) || 
		      	(btn[3].getText().equals("") && btn[4].getText().equals("") && btn[5].getText().equals("")) ||  
		       	(btn[6].getText().equals("") && btn[7].getText().equals("") && btn[8].getText().equals("")) || 
		       	(btn[0].getText().equals("") && btn[3].getText().equals("") && btn[6].getText().equals("")) ||  
		       	(btn[1].getText().equals("") && btn[4].getText().equals("") && btn[7].getText().equals("")) || 
		       	(btn[2].getText().equals("") && btn[5].getText().equals("") && btn[8].getText().equals("")) ||  
		       	(btn[0].getText().equals("") && btn[4].getText().equals("") && btn[7].getText().equals("")) || 
		       	(btn[2].getText().equals("") && btn[4].getText().equals("") && btn[6].getText().equals(""))  ) {
		        	return true;
		    }  else {
		       	return false;
		    }
		}
}
