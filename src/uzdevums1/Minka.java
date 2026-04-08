package uzdevums1;

import java.time.Year;
import java.util.Random;

import javax.swing.ImageIcon;

public class Minka {
	// Atribūti
	String vards, skirne, spalvasKrasa, saimnieks;
	int vecums, medijumuSk, izsalkums;
	boolean siksnina;
	ImageIcon bilde;
	Random rand = new Random();
	
	// Konstruktors
	public Minka(String vards, String skirne, String spalvasKrasa,
		String saimnieks, int dzGads, boolean siksnina, String cels) {
		this.vards = vards;
		this.skirne = skirne;
		this.spalvasKrasa = spalvasKrasa;
		this.saimnieks = saimnieks;
		vecums = Year.now().getValue() - dzGads;
		medijumuSk = 0;
		izsalkums = kungis();
		this.siksnina = siksnina;
		bilde = iestatitBildi(cels);
	}
	
	// Metodes
	public int kungis() {
		return rand.nextInt(5)+1;
	}
	
	public ImageIcon iestatitBildi(String cels) {
		// Turpināsim nākošajā nodarbībā
		
		return null;
	}
}
