package uzdevums1;

import java.awt.Image;
import java.net.URL;
import java.time.Year;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
		if(!cels.endsWith(".png"))
			cels += ".png";
		
		URL resurss = getClass().getResource("/atteli/"+ cels);
		if(resurss != null)
			return new ImageIcon(new ImageIcon(resurss).getImage().
					getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		
		else {
			JOptionPane.showMessageDialog(null, "Nevar atrast bildi: "+cels, 
					"Brīdinājums", JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}
	
	void murrat() {
		JOptionPane.showMessageDialog(null, 
		vards + "saka 'murr murr murrr!'", "Paziņojums", 
		JOptionPane.PLAIN_MESSAGE, bilde);
	}
	
	String nolasitAtributus() {
		return vards + " ir " + vecums + " gadus vecs " + skirne + 
				" šķirnes kaķis ar " + spalvasKrasa + " krāsas kažoku."
		+ "\nKaķim " + ((siksnina) ? "ir" : "nav") + " siksniņa."
		+ "\nKaķa saimnieks ir "+ saimnieks + 
		".\nMedījumu skaits: "+ medijumuSk;
	}
	
	String pabarot(String ediens) {
		if(izsalkums > 0) {
			bilde = iestatitBildi("trauks");
			JOptionPane.showMessageDialog(null, "Ņamm ņamm, man garšo "+
			ediens, "Informācija", JOptionPane.INFORMATION_MESSAGE, bilde);
			ediens = "Tukša bļoda";
			izsalkums--;
			
		} else {
			JOptionPane.showMessageDialog(null, "Murr.., esmu pārēdies un "
			+ ediens + " nevēlos!", "Brīdinājums", JOptionPane.WARNING_MESSAGE);
			izsalkums = kungis();
		}
		return ediens;
	}
	
	// Metožu pārslogošana
	void gule() {
		JOptionPane.showMessageDialog(null, saimnieks + 
		"! Aizmirsi iedot man dzesiņu, nevarēšu pagulēt!", "Kļūda", 
		JOptionPane.ERROR_MESSAGE);
	}
	
	void gulet(String prieksmets) {
		if(prieksmets.equalsIgnoreCase("sedziņa")) {
			bilde = iestatitBildi("gul");
			JOptionPane.showMessageDialog(null, "Zzzz....", "Informācija", 
			JOptionPane.INFORMATION_MESSAGE, bilde);
		
		} else
			JOptionPane.showMessageDialog(null, saimnieks + "! "
		+ prieksmets + " man neder gulēšanai! Šššss ņau!", "Kļūda", 
		JOptionPane.ERROR_MESSAGE);
	}
	
	void palielinatVecumu() {
		if(vecums < 20) {
			vecums++;
			bilde = iestatitBildi("kuka");
			JOptionPane.showMessageDialog(null, vards + 
			" vecums palielināts par vienu gadu!", "Informācija", 
			JOptionPane.INFORMATION_MESSAGE, bilde);
		
		} else 
			JOptionPane.showMessageDialog(null,
			"Sasniegts maksimālais vecums!", "Brīdinājums", 
			JOptionPane.WARNING_MESSAGE);
	}
	
	void nolasitVecumu() {
		JOptionPane.showMessageDialog(null, vards + " ir " + 
	vecums + " gadus vecs",
	"Informācija", JOptionPane.INFORMATION_MESSAGE);
	}
	
	void medit() {
		if((rand.nextInt(3)) == 0) {
			medijumuSk++;
			bilde = iestatitBildi("nokerts");
			JOptionPane.showMessageDialog(null,
			"Medījums tika notverts!", "Veiksme", 
			JOptionPane.INFORMATION_MESSAGE, bilde);
		
		} else { 
			bilde = iestatitBildi("aizlaidas");
			JOptionPane.showMessageDialog(null, "Medījums aizlaidās!", 
			"Neveiksme", JOptionPane.INFORMATION_MESSAGE, bilde);
		}
	}
}
