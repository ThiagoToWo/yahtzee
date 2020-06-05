import java.util.ArrayList;
import java.util.TreeMap;

public class Verificador {
	private boolean isOne = false;
	private boolean isTwo = false;
	private boolean isThree = false;
	private boolean isFour = false;
	private boolean isFive = false;
	private boolean isSix = false;
	private boolean is3OfAKind = false;
	private boolean is4OfAKind = false;
	private boolean isFullHouse = false;
	private boolean isSmallStraight = false;
	private boolean isLargeStraight = false;
	private boolean is5OfAKind = false;
	private boolean chance = true;
	private int[] combinações = new int[13];	
	private int one, two, three, four, five, six, k3, k4, fh, ss, ls, k5, ch;	
	
	
	public int getCombinações(int i) {
		return combinações[i];
	}	

	private int contarOne(int[] jogo) {
		int count = 0;		
		for (int dado : jogo) {
			if (dado == 1) {
				count++;
			}
		}
		isOne = true;
		return count;
	}
	
	private int contarTwo(int[] jogo) {
		int count = 0;		
		for (int dado : jogo) {
			if (dado == 2) {
				count++;
			}
		}
		isTwo = true;
		return count * 2;
	}
	
	private int contarThree(int[] jogo) {
		int count = 0;		
		for (int dado : jogo) {
			if (dado == 3) {
				count++;
			}
		}
		isThree = true;
		return count * 3;
	}
	
	private int contarFour(int[] jogo) {
		int count = 0;		
		for (int dado : jogo) {
			if (dado == 4) {
				count++;
			}
		}
		isFour = true;
		return count * 4;
	}
	
	private int contarFive(int[] jogo) {
		int count = 0;		
		for (int dado : jogo) {
			if (dado == 5) {
				count++;
			}
		}
		isFive = true;
		return count * 5;
	}
	
	private int contarSix(int[] jogo) {
		int count = 0;		
		for (int dado : jogo) {
			if (dado == 6) {
				count++;
			}
		}
		isOne = true;
		return count * 6;
	}
	
	private int verificar3OfAKind(int[] jogo) {
		TreeMap<Integer, Integer> valores = new TreeMap<Integer, Integer>();
		int total = 0;
		for (int i = 0; i < 5; i++) {
			if (!valores.containsKey(jogo[i])) {
				valores.put(jogo[i], 1);
			} else {
				valores.put(jogo[i], valores.get(jogo[i]) + 1);
			}
		}
		if (valores.containsValue(3) || valores.containsValue(4) || valores.containsValue(5)) {
			is3OfAKind = true;
			total = jogo[0] + jogo[1] + jogo[2] + jogo[3] + jogo[4];			
		}
		return total;
	}
	
	private int verificar4OfAKind(int[] jogo) {
		TreeMap<Integer, Integer> valores = new TreeMap<Integer, Integer>();
		int total = 0;
		for (int i = 0; i < 5; i++) {
			if (!valores.containsKey(jogo[i])) {
				valores.put(jogo[i], 1);
			} else {
				valores.put(jogo[i], valores.get(jogo[i]) + 1);
			}
		}
		if (valores.containsValue(4) || valores.containsValue(5)) {
			is4OfAKind = true;
			total = jogo[0] + jogo[1] + jogo[2] + jogo[3] + jogo[4];			
		}
		return total;
	}
	
	private int verificarFullHouse(int[] jogo) {
		TreeMap<Integer, Integer> valores = new TreeMap<Integer, Integer>();
		int total = 0;
		for (int i = 0; i < 5; i++) {
			if (!valores.containsKey(jogo[i])) {
				valores.put(jogo[i], 1);
			} else {
				valores.put(jogo[i], valores.get(jogo[i]) + 1);
			}
		}
		if (valores.containsValue(2) && valores.containsValue(3)) {
			isFullHouse = true;
			total = 25;			
		}
		return total;
	}
	
	private int verificarSmallStraight(int[] jogo) {
		TreeMap<Integer, Integer> valores = new TreeMap<Integer, Integer>();
		int total = 0;
		for (int i = 1; i <= 6; i++) {
			valores.put(i, 0);
		}		
		for (int i = 0; i < 5; i++) {
			valores.put(jogo[i], valores.get(jogo[i]) + 1);
		}		
		boolean one1 = valores.get(1) >= 1;
		boolean one2 = valores.get(2) >= 1;
		boolean one3 = valores.get(3) >= 1;
		boolean one4 = valores.get(4) >= 1;
		boolean one5 = valores.get(5) >= 1;
		boolean one6 = valores.get(6) >= 1;
		boolean firstSeq = one1 && one2 && one3 && one4; 
		boolean secondSeq = one2 && one3 && one4 && one5; 
		boolean thirddSeq = one3 && one4 && one5 && one6; 
		if (firstSeq || secondSeq || thirddSeq) {
			isSmallStraight = true;
			total = 30;			
		}
		return total;
	}
	
	private int verificarLargeStraight(int[] jogo) {
		TreeMap<Integer, Integer> valores = new TreeMap<Integer, Integer>();
		int total = 0;
		for (int i = 1; i <= 6; i++) {
			valores.put(i, 0);
		}		
		for (int i = 0; i < 5; i++) {
			valores.put(jogo[i], valores.get(jogo[i]) + 1);
		}		
		boolean one1 = valores.get(1) == 1;
		boolean one2 = valores.get(2) == 1;
		boolean one3 = valores.get(3) == 1;
		boolean one4 = valores.get(4) == 1;
		boolean one5 = valores.get(5) == 1;
		boolean one6 = valores.get(6) == 1;
		boolean firstSeq = one1 && one2 && one3 && one4 && one5; 
		boolean secondSeq = one2 && one3 && one4 && one5 && one6;		
		if (firstSeq || secondSeq) {
			isLargeStraight = true;
			total = 40;			
		}
		return total;
	}
	
	private int verificar5OfAKind(int[] jogo) {
		TreeMap<Integer, Integer> valores = new TreeMap<Integer, Integer>();
		int total = 0;
		for (int i = 0; i < 5; i++) {
			if (!valores.containsKey(jogo[i])) {
				valores.put(jogo[i], 1);
			} else {
				valores.put(jogo[i], valores.get(jogo[i]) + 1);
			}
		}
		if (valores.containsValue(5)) {
			is5OfAKind = true;
			total = 50;			
		}
		return total;
	}
	
	private int chance(int[] jogo) {
		int total = jogo[0] + jogo[1] + jogo[2] + jogo[3] + jogo[4];
		return total;
	}
	
	public void verificarTudo(int[] jogo) {
		one = contarOne(jogo);
		two = contarTwo(jogo);
		three = contarThree(jogo);
		four = contarFour(jogo);
		five = contarFive(jogo);
		six = contarSix(jogo);
		k3 = verificar3OfAKind(jogo);
		k4 = verificar4OfAKind(jogo);
		fh = verificarFullHouse(jogo);
		ss = verificarSmallStraight(jogo);
		ls = verificarLargeStraight(jogo);			
		k5 = verificar5OfAKind(jogo);
		ch = chance(jogo);
		combinações[0] = one;
		combinações[1] = two;
		combinações[2] = three;
		combinações[3] = four;
		combinações[4] = five;
		combinações[5] = six;
		combinações[6] = k3;
		combinações[7] = k4;
		combinações[8] = fh;
		combinações[9] = ss;
		combinações[10] = ls;
		combinações[11] = k5;
		combinações[12] = ch;		
		/*System.out.println("jogada = {" + jogo[0] + ", " + jogo[1] + ", " + jogo[2] + ", " +
							jogo[3] + ", " + jogo[4] + "}" );
		System.out.println("one = " + one + "\n" + 
						   "two = " + two + "\n" + 
						   "three = " + three + "\n" + 
						   "four = " + four + "\n" + 
						   "five = " + five + "\n" + 
						   "six = " + six + "\n" + 
						   "3 of a kind = " + k3 + "\n" +
						   "4 of a kind = " + k4 + "\n" +
						   "FullHouse = " + fh + "\n" + 
						   "SmallStraight = " + ss + "\n" +
						   "LargeStraight = " + ls + "\n" +							  
						   "5 of a kind = " + k5 + "\n" +
						   "Chance = " + ch + "\n");*/
	}
}
