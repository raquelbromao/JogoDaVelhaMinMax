import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TesteInterface {
	static Shell shlJogo = new Shell();
	static Label lblVitorias = new Label(shlJogo, SWT.NONE);
	static Label lblDerrotas = new Label(shlJogo, SWT.NONE);
	static Label lblEmpates = new Label(shlJogo, SWT.NONE);
	// array com os Buttons para automatizar o código (1 a n, sendo n o
	// tamanho desejado que nesse caso é 10, para evitar erros de contagem, button 0 não é usado)
	static Button[] btn2 = new Button[10];
	static int[] copiaTabuleiro = new int[10];
	static int numVitorias;
	static int numDerrotas;
	static int numEmpates;
	static Aviso aviso = new Aviso();

	public static int verificaPlacar2(Button[] b) {
		// Se X (usuário) ganha retorna -1
		if((b[1].getText().equals("X") && b[2].getText().equals("X") && b[3].getText().equals("X"))
		|| (b[4].getText().equals("X") && b[5].getText().equals("X") && b[6].getText().equals("X"))
		|| (b[7].getText().equals("X") && b[8].getText().equals("X") && b[9].getText().equals("X"))
		|| (b[1].getText().equals("X") && b[5].getText().equals("X") && b[9].getText().equals("X"))
		|| (b[3].getText().equals("X") && b[5].getText().equals("X") && b[7].getText().equals("X"))
		|| (b[1].getText().equals("X") && b[4].getText().equals("X") && b[7].getText().equals("X"))
		|| (b[2].getText().equals("X") && b[5].getText().equals("X") && b[8].getText().equals("X"))
		|| (b[3].getText().equals("X") && b[6].getText().equals("X") && b[9].getText().equals("X"))) {
			return -1;
		}

		// Se O (algoritmo) ganha retorna +1
		if((b[1].getText().equals("O") && b[2].getText().equals("O") && b[3].getText().equals("O"))
		|| (b[4].getText().equals("O") && b[5].getText().equals("O") && b[6].getText().equals("O"))
		|| (b[7].getText().equals("O") && b[8].getText().equals("O") && b[9].getText().equals("O"))
		|| (b[1].getText().equals("O") && b[5].getText().equals("O") && b[9].getText().equals("O"))
		|| (b[3].getText().equals("O") && b[5].getText().equals("O") && b[7].getText().equals("O"))
		|| (b[1].getText().equals("O") && b[4].getText().equals("O") && b[7].getText().equals("O"))
		|| (b[2].getText().equals("O") && b[5].getText().equals("O") && b[8].getText().equals("O"))
		|| (b[3].getText().equals("O") && b[6].getText().equals("O") && b[9].getText().equals("O"))) {
			return 1;
		}

		// Senão retorna 0 que é empate
		return 0;
	}

	// Função que verifica se o tabuleiro esta com todas as casas preenchidas com jogadas
	public static boolean verificaTabuleiro(Button[] b) {
		// Verifica se o tabuleiro está cheio ou ainda faltam casas preenchidas
		// Se o tabuleiro está cheio o jogo acabou
		// Se há alguma casa vazia o jogo ainda não acabou
		int j = 1;
		for (int i = 1; i <= 9; i++) {
			if (b[i].getText().equals("X") || b[i].getText().equals("O")) {
				j++;
			}
		}

		// Tabuleiro cheio -> FIM DE JOGO
		if (j == 9) {
			return false;
			// Tabuleiro com casa vazia -> CONTINUA O JOGO
		} else {
			return true;
		}
	}

	/*
	 * Função que transforma o tabuleiro 
	 * 1. Todo btn que tiver "X" -> int valor 1
	 * 2. Todo btn que tiver "O" -> int valor 2 
	 * 3. Todo btn que tiver " " -> int valor 0
	 */
	public static int[] modificaTabuleiro(Button[] b) {
		int[] tabuleiro = new int[10];

		for (int i = 1; i <= 9; i++) {
			if (b[i].getText().equals("X")) {
				tabuleiro[i] = 1;
			} else if (b[i].getText().equals("O")) {
				tabuleiro[i] = 2;
			} else {
				tabuleiro[i] = 0;
			}
		}

		for (int i = 1; i <= 9; i++) {
			System.out.print(tabuleiro[i] + " ");
		}

		System.out.println("\n ---  ");
		return tabuleiro;
	}

	// Função que verifica qual ponto ganho nessa rodada
	public static int verificaPlacar(int[] casas) {
		// int ponto = 0;

		// Se X (usuário) ganha retorna -10
		if((casas[1] == 1 && casas[2] == 1 && casas[3] == 1)
		|| (casas[4] == 1 && casas[5] == 1 && casas[6] == 1)
		|| (casas[7] == 1 && casas[8] == 1 && casas[9] == 1)
		|| (casas[1] == 1 && casas[5] == 1 && casas[9] == 1)
		|| (casas[3] == 1 && casas[5] == 1 && casas[7] == 1)
		|| (casas[1] == 1 && casas[4] == 1 && casas[7] == 1)
		|| (casas[2] == 1 && casas[5] == 1 && casas[8] == 1)
		|| (casas[3] == 1 && casas[6] == 1 && casas[9] == 1)) {
			return -10;
		}

		// Se O (algoritmo) ganha retorna +10
		if((casas[1] == 2 && casas[2] == 2 && casas[3] == 2) 
		|| (casas[4] == 2 && casas[5] == 2 && casas[6] == 2)
		|| (casas[7] == 2 && casas[8] == 2 && casas[9] == 2)
		|| (casas[1] == 2 && casas[5] == 2 && casas[9] == 2)
		|| (casas[3] == 2 && casas[5] == 2 && casas[7] == 2)
		|| (casas[1] == 2 && casas[4] == 2 && casas[7] == 2)
		|| (casas[2] == 2 && casas[5] == 2 && casas[8] == 2)
		|| (casas[3] == 2 && casas[6] == 2 && casas[9] == 2)) {
			return 10;
		}

		int cont = 0;
		for (int i = 1; i <= 9; i++) {
			if (casas[i] != 0) {
				cont++;
			}
		}
		
		// Senão retorna 0 que é empate
		if (cont >= 9) {
			return 0;
		}
		
		// Significa que não cheguei no final ainda
		return -1;
	}

	/*
	 * Função que verifica quais casas estão vazias no tabuleiro
	 * 
	 * 1. Verifica casas do tabuleiro 
	 * 		a. Achou casa vazia: adiciona em casasLivres o número da casa do tabuleiro que está vazia 
	 * 		b. Não achou casa vazia: pula pra póxima casa do tabuleiro
	 * 
	 */
	public static int[] verificaCasasVazias(int[] tabuleiro) {
		int cont = 0;
		int[] casasLivres = new int[10];
		for (int i = 1; i <= 9; i++) {
			if (tabuleiro[i] == 0) {
				// Se a posição i do tabuleiro for igual a 0 (casa vazia)
				// Incrementa contador casaLivres[cont] = posição i
				System.out.println("Tabuleiro [" + i + "]: " + tabuleiro[i]);
				cont++;
				System.out.println("Contador = " + cont);
				casasLivres[cont] = i;
				System.out.println("casasLivres[cont] = " + i);
			}
		}
		return casasLivres;
	}

	/*
	 * Método MinMax
	 */
	public static int minmax(int[] tabuleiro, String nivel) {
		int maiorPonto = -10;
		int menorPonto = 10;
		int indice = 0;
		int[] jogadaPC = new int[10];
		// 1. Copia tabuleiro e transfere para aux para análise de casas vazias
		int[] aux = verificaCasasVazias(tabuleiro);
		int casasVazias = 0;

		System.out.println("\n");
		// 2. Verifica posições vazias
		for (int i = 1; i <= 9; i++) {
			// System.out.print(aux[i]+" ");
			// 2.a. Quando encontra casa vazia incrementa casasVazias
			if (aux[i] > 0) {
				casasVazias++;
			}
		}
		System.out.println("\nNo de casas vazias: " + casasVazias);
		System.out.println("\n");

		int cont = 1;

		// Enquanto contador não atingir o número de casasVazias
		// Continua executando essa porção recursiva do código
		while (cont <= casasVazias) {
			int mut = aux[cont];

			// Casa mult do tabuleiro recebe 2 -> "O"
			tabuleiro[mut] = 2;

			// Chamada para movimento mínimo que retorna o ponto
			if(nivel.equals("MAX")) {
				int ponto = Minimo(tabuleiro);
				// Se o ponto atual é maior que o maior ponto
				// Maior ponto recebe ponto
				if (ponto > maiorPonto) {
					maiorPonto = ponto;
					indice = 0;
					jogadaPC[indice] = mut;
					// Se o ponto atual é igual ao maiorPonto, nada muda
					// Maior ponto permanece igual
				} else if (ponto == maiorPonto) {
					indice++;
					jogadaPC[indice] = mut;
				}
			} else {
				int ponto = Maximo(tabuleiro);
				// Se o ponto atual é menor que o menor ponto
				// Menor ponto recebe ponto
				if (ponto < menorPonto) {
					maiorPonto = ponto;
					indice = 0;
					jogadaPC[indice] = mut;
					// Se o ponto atual é igual ao menorPonto, nada muda
					// Menor ponto permanece igual
				} else if (ponto == menorPonto) {
					indice++;
					jogadaPC[indice] = mut;
				}
			}

			// Se o ponto atual é maior que o maior ponto
			// Maior ponto recebe ponto
			/*if (ponto > maiorPonto) {
				maiorPonto = ponto;
				indice = 0;
				jogadaPC[indice] = mut;
				// Se o ponto atual é igual ao maiorPonto, nada muda
				// Maior ponto permanece igual
			} else if (ponto == maiorPonto) {
				indice++;
				jogadaPC[indice] = mut;
			}*/

			// Depois de percorrer um das casas vazia o cont incrementa
			// E o tabuleiro na posição mult fica vazio (0 -> "")
			tabuleiro[mut] = 0;
			cont++;
		}

		int r = 0;
		if (indice > 0) {
			Random x = new Random();
			r = x.nextInt(indice);
		}

		return jogadaPC[r];
	}

	public static int Minimo(int[] tabuleiro) {
		int valorJogada = verificaPlacar(tabuleiro);
		if (valorJogada != -1) {
			return valorJogada;
		}

		int menorPonto = 10;
		int[] aux = new int[10];
		aux = verificaCasasVazias(tabuleiro);
		int casasVazias = 0;

		for (int i = 1; i <= 9; i++) {
			if (aux[i] > 0) {
				casasVazias++;
			}
		}

		int cont = 1;

		while (cont <= casasVazias) {
			int mut = aux[cont];
			// Casa mult do tabuleiro recebe 1 -> "X"
			tabuleiro[mut] = 1;

			int ponto = Maximo(tabuleiro);
			if (ponto < menorPonto) {
				menorPonto = ponto;
			}
			tabuleiro[mut] = 0;
			cont++;
		}
		return menorPonto;
	}

	public static int Maximo(int[] tabuleiro) {
		int valorJogada = verificaPlacar(tabuleiro);
		if (valorJogada != -1) {
			return valorJogada;
		}
		int maiorPonto = -10;
		int[] aux = new int[10];
		aux = verificaCasasVazias(tabuleiro);
		int casasVazias = 0;

		for (int i = 1; i <= 9; i++) {
			if (aux[i] > 0) {
				casasVazias++;
			}
		}

		int cont = 1;
		while (cont <= casasVazias) {
			int mut = aux[cont];
			// Casa mult do tabuleiro recebe 2 -> "O"
			tabuleiro[mut] = 2;

			int ponto = Minimo(tabuleiro);
			if (ponto > maiorPonto) {
				maiorPonto = ponto;
			}

			tabuleiro[mut] = 0;
			cont++;
		}
		return maiorPonto;
	}

	public static void rodadaPC(Button[] btn2) {
		// Copia tabuleiro e muda sua forma de interpretação button -> int
		copiaTabuleiro = modificaTabuleiro(btn2);
		// Passa o tabuleiro transformado pro mimmax e recebe o valor da melhor posição
		int casaPC = minmax(copiaTabuleiro,"MAX");
		System.out.println("Jogada PC: " + casaPC);
		btn2[casaPC].setText("O");

		if (verificaPlacar2(btn2) == -1) {
			aviso.main(null, 2);
			System.out.println("Vencedor: VOCÊ! \n");
			numVitorias++;
			lblVitorias.setText("VIT\u00D3RIAS: " + numVitorias);
			// Reseta tabuleiro
			for (int i = 1; i <= 9; i++) {
				btn2[i].setText("");
			}
			// PC
		} else if (verificaPlacar2(btn2) == 1) {
			aviso.main(null, 3);
			System.out.println("Vencedor: PC \n");
			numDerrotas++;
			lblDerrotas.setText("DERROTAS: " + numDerrotas);
			// Reseta tabuleiro
			for (int i = 1; i <= 9; i++) {
				btn2[i].setText("");
			}
			// Verifica se o tabuleiro está cheio
		} else if (verificaTabuleiro(btn2) == false) {
			aviso.main(null, 4);
			// Tabuleiro cheio -> FIM DE JOGO
			System.out.println("\nTabuleiro cheio! Fim de jogo\n");
			System.out.println("Empate\n");
			numEmpates++;
			lblEmpates.setText("EMPATES: " + numEmpates);
			// Reseta tabuleiro
			for (int i = 1; i <= 9; i++) {
				btn2[i].setText("");
			}
		}
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		// Shell shlJogo = new Shell();
		shlJogo.setSize(505, 416);
		shlJogo.setText("Jogo da Velha MinMax");

		Label lblVitorias = new Label(shlJogo, SWT.NONE);
		lblVitorias.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblVitorias.setBounds(323, 55, 90, 15);
		lblVitorias.setText("VIT\u00D3RIAS: ");

		Label lblDerrotas = new Label(shlJogo, SWT.NONE);
		lblDerrotas.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblDerrotas.setBounds(323, 76, 90, 15);
		lblDerrotas.setText("DERROTAS: ");

		Label lblEmpates = new Label(shlJogo, SWT.NONE);
		lblEmpates.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblEmpates.setBounds(323, 97, 90, 15);
		lblEmpates.setText("EMPATES: ");

		// Cria nove botões (CASAS) e indica o que acontece quando clicar em um deles
		for (int i = 1; i <= 9; i++) {
			btn2[i] = new Button(shlJogo, SWT.NONE);
			btn2[i].setFont(SWTResourceManager.getFont("Segoe UI", 40, SWT.BOLD));

			// Resolve o erro da linha 132: "Local variable i defined in an enclosing scope must be final or effectively final"
			final Integer innerI = new Integer(i);

			// Adiciona evento de clique em cada Button
			btn2[i].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					// Verifica se posição está disponível
					if (btn2[innerI].getText().equals("X") || btn2[innerI].getText().equals("O")) {
						// Mensagem de erro: Seleção Inválida
						aviso.main(null, 1);
						// Tabuleiro não cheio e posição válida
					} else {
						btn2[innerI].setText("X");
						// Verifica se alguém venceu
						// USUÁRIO
						if (verificaPlacar2(btn2) == -1) {
							aviso.main(null, 2);
							numVitorias++;
							lblVitorias.setText("VIT\u00D3RIAS: " + numVitorias);
							// Reseta tabuleiro
							for (int i = 1; i <= 9; i++) {
								btn2[i].setText("");
							}
							// PC
						} else if (verificaPlacar2(btn2) == 1) {
							aviso.main(null, 3);
							numDerrotas++;
							lblDerrotas.setText("DERROTAS: " + numDerrotas);
							// Reseta tabuleiro
							for (int i = 1; i <= 9; i++) {
								btn2[i].setText("");
							}
							// Verifica se o tabuleiro está cheio
						} else if (verificaTabuleiro(btn2) == false) {
							aviso.main(null, 4);
							// Tabuleiro cheio -> FIM DE JOGO
							numEmpates++;
							lblEmpates.setText("EMPATES: " + numEmpates);
							// Reseta tabuleiro
							for (int i = 1; i <= 9; i++) {
								btn2[i].setText("");
							}
						} else {
							rodadaPC(btn2);
						}
					}
				}
			});
		}

		// Define as posições de cada Button na shell
		btn2[1].setBounds(27, 42, 80, 80);
		btn2[2].setBounds(113, 42, 80, 80);
		btn2[3].setBounds(199, 42, 80, 80);
		btn2[4].setBounds(27, 128, 80, 80);
		btn2[5].setBounds(113, 128, 80, 80);
		btn2[6].setBounds(199, 128, 80, 80);
		btn2[7].setBounds(27, 214, 80, 80);
		btn2[8].setBounds(113, 214, 80, 80);
		btn2[9].setBounds(199, 214, 80, 80);

		Button btnNovoJogo = new Button(shlJogo, SWT.NONE);
		btnNovoJogo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Reseta tabuleiro
				for (int i = 1; i <= 9; i++) {
					btn2[i].setText("");
				}
			}
		});
		btnNovoJogo.setBounds(323, 148, 75, 25);
		btnNovoJogo.setText("Novo Jogo");

		Button btnFechar = new Button(shlJogo, SWT.NONE);
		btnFechar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlJogo.close();
			}
		});
		btnFechar.setBounds(323, 179, 75, 25);
		btnFechar.setText("Fechar");

		shlJogo.open();
		shlJogo.layout();
		while (!shlJogo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}