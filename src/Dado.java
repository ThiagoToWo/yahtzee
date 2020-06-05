import java.util.Random;
import java.util.TreeMap;

import javax.swing.ImageIcon;

/**
 * @author Thiago ToWo
 *
 */
public class Dado {
	private int d1, d2, d3, d4, d5;
	private int[] dados = new int[5];
	private ImageIcon[] imagDados = new ImageIcon[5];
	TreeMap<Integer, ImageIcon> mapDados = new TreeMap<Integer, ImageIcon>();
	private String[] nomeDados = {"face0.jpg", "face1.jpg", "face2.jpg", "face3.jpg", "face4.jpg", "face5.jpg", "face6.jpg"};
	private ImageIcon zero = new ImageIcon(getClass().getResource(nomeDados[0]));
	public void jogarDados() {
		d1 = new Random().nextInt(6) + 1;
		d2 = new Random().nextInt(6) + 1;
		d3 = new Random().nextInt(6) + 1;
		d4 = new Random().nextInt(6) + 1;
		d5 = new Random().nextInt(6) + 1;
		
		dados[0] = d1;
		dados[1] = d2;
		dados[2] = d3;
		dados[3] = d4;
		dados[4] = d5;
		imagDados[0] = new ImageIcon(getClass().getResource(nomeDados[d1]));
		imagDados[1] = new ImageIcon(getClass().getResource(nomeDados[d2]));
		imagDados[2] = new ImageIcon(getClass().getResource(nomeDados[d3]));
		imagDados[3] = new ImageIcon(getClass().getResource(nomeDados[d4]));
		imagDados[4] = new ImageIcon(getClass().getResource(nomeDados[d5]));
		
		
	}	
	
	public int getDados(int i) {
		return dados[i];
	}
	
	public ImageIcon getImagDados(int i) {
		return imagDados[i];
	}
	
	public ImageIcon getImagZero() {
		return zero;
	}

	public void zerar() {
		for (int i = 0; i < 5; i++) {
			dados[i] = 0;
			imagDados[i] = zero;
		}
		
	}
}
