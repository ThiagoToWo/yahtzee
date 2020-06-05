import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class Gui extends JFrame {	
	
	private String autor = "Autor: Thiago de Oliveira Alves\ntowo497@gmail.com";
	private String versao = "Vers�o: 0.1 \n 27-04-2020\n\n";
	private String regras = "Como Jogar\n\n" + 
				"O jogo consiste em 13 rodadas. Em cada rodada, voc� joga os dados e ent�o marca uma das 13 categorias.\n" + 
				"Voc� tem que marcar uma categoria em cada rodada, o que significa que para o fim do jogo voc� pode ter\n" +
				"que se conformar em marcar zero em algumas categor�as. O objetivo do jogo � maximizar sua pontua��o total.\n" + 
				"O jogo termina uma vez que todas as 13 categorias forem marcadas.\n\n" +
				"Jogando os Dados\n\n" +
				"Voc� tem cinco dados que voc� pode jogar. Para come�ar, clique no bot�o \"Rolar 3\". Depois que voc�\n" +
				"joga os dados, voc� pode aceitar o jogo atual, ou jogar novamente qualquer um ou at� todos os cinco dados.\n" +
				"Para jogar novamente alguns dos dados, clique nos dados que desejar manter (cada dado a ser mantido ficar�\n" +
				"escuro) ent�o clique no mesmo bot�o que utilizou para jogar os dados da primeira vez. Isto jogar� os dados\n" +
			    "n�o selecionados e deixar� os selcionados inalterados. Voc� pode jogar os dados um total de tr�s vezes:\n" +
				"a jogada inicial (a qual voc� tem que jogar todos os dados), mais duas jogadas de qualquer um ou at� todos\n" +
				"os dados. Depois de rodar tr�s vezes ou quando achar que fez uma boa pontua�ao, voc� tem que marcar o jogo.\n" +
				"Uma vez que voc� marcou o jogo, voc� joga todos os dados novamente e repete o processo. Voc� continua at�\n" +
				"todas as 13 categorias estarem preenchidas, quando ent�o, termina a partida.\n\n" +
				"Regras\n\n" +
				"Nos bot�es \"One (um), Two (dois), Three (tr�s), Four (quatro), Five (cinco), e Six (seis)\", o n�mero de\n" + 
				"pontos marcado depende do n�mero de dados em sua combina��o que tiver o valor daquele bot�o. Nos \"3 of a\n" + 
				"kind, 4 of a kind, Full House, Small Straight, Large Straight, Yahtzee e Chance\", o n�mero de pontos\n" +
				"marcado � fixo para alguns e vari�vel para outros. Semelhantemente, voc� receber� pontos de gratifica��o\n" +
				"em certos casos. Tenha cuidado para n�o p�r uma combina��o no bot�o errado, porque o bot�o � desabilitado\n" +
				"e n�o poder� mais ser usado. A meta do jogo n�o � s� encher todas as caixas para cada combina��o, mas\n" +
				"tamb�m obter tantos pontos quanto poss�vel. Jogando freq�entemente, voc� perceber� que certas combina��es,\n" +
				"como um Yahtzee, n�o s�o t�o f�cil de serem obtidas.\n\n" +
				"Tabela com os Valores de Pontua��o\n\n" +
				"One: soma de todos os valores 1.\n" +
				"Two: soma de todos os valores 2.\n" + 
				"Three: soma de todos os valores 3.\n" +
				"Four: soma de todos os valores 4.\n" +
				"Five: soma de todos os valores 5.\n" +
				"Six: soma de todos os valores 6.\n" +
				"Bonus: se a soma das categorias acima ultrapassar 63 pontos, recebe 50 pontos.\n" +
				"3 of a kind: no m�nimo 3 iguais. Vale a soma dos dados.\n" +
				"4 of a kind: no m�nimo 4 iguais. Vale a soma dos dados.\n" +
				"Full House: 2 iguais + 3 iguais. Vale 25 pontos.\n" +
				"Small Straight: sequ�ncia de no m�nimo 4 n�meros. Vale 30 pontos.\n" +
				"Large Straight: sequ�ncia de 5 n�meros. Vale 40 pontos.\n" +
				"Yahtzee: 5 iguais. Vale 50 pontos.\n" +
				"Chance: qualquer combina��o. Vale a soma dos dados.";
	private int vez = 3;
	private int total = 0;
	private JButton botRolar;
	private JButton botSubmit;
	private JToggleButton botDados;
	private JToggleButton[] listaDados = new JToggleButton[5];
	int[] valDoBot�oDados = new int[5];
	private String[] combin = {"One --------------------", "Two --------------------", "Three ------------------",
			"Four -------------------", "Five --------------------", "Six ---------------------",
			"3 of a kind -------------", "4 of a kind -------------", "Full House -------------",
			"Small Straight ---------", "Large Straigth ---------", "Yahtzee ----------------", "Chance ----------------"};
	private JToggleButton[] listaCombin = new JToggleButton[13];	
	private JLabel labTotal;
	private int pontoRecord;
	private Dado dado = new Dado();
	private Verificador ver = new Verificador();	
	private Font fonteVis�svel = new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 24);
	
	public void construir() {
		setTitle("Yahtzee!");
		
		// barra de menu
		JMenuBar barraDeMenu = new JMenuBar();	
		JMenu menuNovoJogo = new JMenu("Novo jogo");
		JMenuItem vai = new JMenuItem("Go!");
		vai.addActionListener(new NovoListener());
		JMenu menuSobre = new JMenu("Informa��es");		
		JMenuItem autoria = new JMenuItem("Autor");
		autoria.addActionListener(new AutorListener());
		JMenuItem versao = new JMenuItem("Sobre o aplicativo");
		versao.addActionListener(new VersaoListener());
		JMenuItem regras = new JMenuItem("Sobre o jogo");
		regras.addActionListener(new RegrasListener());
		JMenuItem pontos = new JMenuItem("Ver record");
		pontos.addActionListener(new RecordListener());
		menuNovoJogo.add(vai);
		menuSobre.add(autoria);
		menuSobre.add(versao);
		menuSobre.add(regras);
		menuSobre.add(pontos);
		barraDeMenu.add(menuNovoJogo);
		barraDeMenu.add(menuSobre);
		setJMenuBar(barraDeMenu);
		
		// painel norte
		JPanel painelNorte = new JPanel();		
		painelNorte.setLayout(new GridLayout(0, 5));	
		for (int i = 0; i < 5; i++) {
			botDados = new JToggleButton(dado.getImagZero());
			botDados.setEnabled(false);
			painelNorte.add(botDados);
			listaDados[i] = botDados;
		}		
		getContentPane().add(BorderLayout.NORTH, painelNorte);
		
		// painel central
		JPanel painelCentral = new JPanel();
		painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 300));
		painelCentral.setLayout(new GridLayout(14, 2));
		ContabListener cl = new ContabListener();
		ButtonGroup bg = new ButtonGroup();
		for (int i = 0; i < 13; i++) {
			JLabel labCombin = new JLabel(combin[i]);
			labCombin.setFont(fonteVis�svel);
			//labCombin.setHorizontalAlignment(SwingConstants.RIGHT);
			JToggleButton botCombin = new JToggleButton();	
			botCombin.setFont(fonteVis�svel);
			bg.add(botCombin);
			painelCentral.add(labCombin);
			painelCentral.add(botCombin);
			listaCombin[i] = botCombin;				
		}		
		labTotal = new JLabel("Total = ");	
		labTotal.setFont(fonteVis�svel);
		painelCentral.add(labTotal);
		getContentPane().add(BorderLayout.CENTER, painelCentral);
		
		// painel sul
		JPanel painelSul = new JPanel();
		botRolar = new JButton("Rolar " + vez);
		botRolar.addActionListener(new RolarListener());
		botRolar.setFont(fonteVis�svel);
		JLabel labVazio = new JLabel("      ");	
		botSubmit = new JButton("Contabilizar");
		botSubmit.setFont(fonteVis�svel);
		botSubmit.addActionListener(new ContabListener());
		painelSul.add(botRolar);
		painelSul.add(labVazio);
		painelSul.add(botSubmit);
		getContentPane().add(BorderLayout.SOUTH, painelSul);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(500, 200);
		setSize(900, 700);;
		setVisible(true);
	}
	
	public class RolarListener implements ActionListener {		
		@Override
		public void actionPerformed(ActionEvent e) {
			botSubmit.setEnabled(true);
			dado.jogarDados();			
			for (int i = 0; i < 5; i++) {
				if (!listaDados[i].isSelected()) {
					listaDados[i].setEnabled(true);
					listaDados[i].setIcon(dado.getImagDados(i));					
					valDoBot�oDados[i] = dado.getDados(i);
				}
			}							
			ver.verificarTudo(valDoBot�oDados);				
			for (int i = 0; i < 13; i++) {	
				if (listaCombin[i].isEnabled()) {
					listaCombin[i].setText("" + ver.getCombina��es(i));					
				}				
			}			
			vez--;
			botRolar.setText("Rolar " + vez);
			if (vez == 0) {
				botRolar.setEnabled(false);
			}
		}
	}
	
	public class ContabListener implements ActionListener {		
		@Override
		public void actionPerformed(ActionEvent e) {
			int jogadasFeitas = 0;			
			int condi��oBonos = 0;
			for (int i = 0; i < 13; i++) {
				if (listaCombin[i].isEnabled()) {
					if (listaCombin[i].isSelected()) {
						JToggleButton botSelect = listaCombin[i];
						int numDoBot = Integer.parseInt(botSelect.getText());
						total += numDoBot;
						listaCombin[i].setEnabled(false);
						botSubmit.setEnabled(false);						
						if (botRolar.isEnabled()) {
							vez = 3;
							botRolar.setText("Rolar " + vez);
							for (int j = 0; j < 5; j++) {
								listaDados[j].setSelected(false);
								listaDados[j].setText(" ");
							}
						} else {
							botRolar.setEnabled(true);
							vez = 3;
							botRolar.setText("Rolar " + vez);
							for (int k = 0; k < 5; k++) {
								listaDados[k].setSelected(false);
								listaDados[k].setText(" ");
							}
						}
						dado.zerar();
						for (int k = 0; k < 5; k++) {
							if (!listaDados[k].isSelected()) {
								listaDados[k].setIcon(dado.getImagDados(k));
								listaDados[k].setEnabled(false);
								valDoBot�oDados[k] = dado.getDados(k);
							}
						}
					}
				}				
				if (!listaCombin[i].isEnabled()) {
					jogadasFeitas++;					
				}
			}			
			labTotal.setText("Total = " + total);			
			if (jogadasFeitas == 13) {
				botRolar.setEnabled(false);
				for (int i = 0; i < 6; i++) {
					JToggleButton botSelect = listaCombin[i];
					int numDoBot = Integer.parseInt(botSelect.getText());
					condi��oBonos += numDoBot;
				}
				if (condi��oBonos > 63) {				
					total += 50;
					labTotal.setText("Total = " + total + " B�nus +35");					
				} 				
				try {
					File fl = new File("record");
					BufferedReader br = new BufferedReader(new FileReader(fl));
					String[] record = br.readLine().split("/");
					pontoRecord = Integer.parseInt(record[0]);
					br.close();
					
					if (pontoRecord < total) {
						String nome = JOptionPane.showInputDialog(null,"Nome: ", "Novo Record!", JOptionPane.QUESTION_MESSAGE);						
						FileWriter fw = new FileWriter(fl);	
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write("" + total + "/" + nome + "/" + new Date());
						bw.close();
					}					
				} catch (IOException e2) {
					try {
					FileWriter fw = new FileWriter("record");	
					BufferedWriter bw = new BufferedWriter(fw);					
					bw.write("" + 0 + "/" + "null" + "/" + "null");
					bw.close();
					
					File fl = new File("record");
					BufferedReader br = new BufferedReader(new FileReader(fl));
					String[] record = br.readLine().split("/");
					pontoRecord = Integer.parseInt(record[0]);
					br.close();
					
					if (pontoRecord < total) {
						String nome = JOptionPane.showInputDialog(null,"Nome: ", "Novo Record!", JOptionPane.QUESTION_MESSAGE);						
						FileWriter fw2 = new FileWriter(fl);	
						BufferedWriter bw2 = new BufferedWriter(fw2);
						bw2.write("" + total + "/" + nome + "/" + new Date());
						bw2.close();
					}	
					} catch (IOException e1) {						
						e1.printStackTrace();
					}							
				} 
			}															
		}
	}
		
	public class NovoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Gui().construir();
		}

	}
	
	private class AutorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {			
			JOptionPane.showMessageDialog(null, autor, "Sobre mim", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private class VersaoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, versao, "Sobre este", JOptionPane.INFORMATION_MESSAGE);

		}
	}
	
	public class RegrasListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {			
				JOptionPane.showMessageDialog(null, regras, "Regras", JOptionPane.INFORMATION_MESSAGE);				
		}
	}
	
	public class RecordListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				BufferedReader br = new BufferedReader(new FileReader("record"));
				String record = br.readLine();				
				JOptionPane.showMessageDialog(null, record, "Maior pontua��o", JOptionPane.INFORMATION_MESSAGE);
				br.close();
			} catch (IOException e2) {
				JOptionPane.showMessageDialog(null, "Que tal fazer uma boa primeira pontua��o?", "Maior pontua��o", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}	
}

