package uzdevums1;

import java.io.File;
import java.time.Year;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class MinkuTante {
	static String virknesParbaude(String zinojums, String noklusejums) {
		String virkne;
		
		do {
			virkne = JOptionPane.showInputDialog(zinojums, noklusejums);
			if(virkne == null)
				return null;
						
			virkne = virkne.trim();			
		} while(!Pattern.matches("^[\\p{L} ]+$", virkne));
		
		return virkne;
	}
	
	static int skaitlaParbaude(String zinojums, int min, int max) {
		String ievade;
		int skaitlis;
		while(true) {
			ievade = JOptionPane.showInputDialog(zinojums, min);
			if(ievade == null)
				return -1;
			
			try {
				skaitlis = Integer.parseInt(ievade);
				if(skaitlis < min || skaitlis > max) {
					JOptionPane.showMessageDialog(null,
					"Norādītais skaitlis ir nederīgā intervālā!", 
					"Nekorekti dati", JOptionPane.WARNING_MESSAGE);
					continue;
				}
				
				return skaitlis;
				
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, 
				"Netika ievadīts vesels skaitlis!", "Nekorekti dati", 
				JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static void main(String[] args) {
		String izvelne, failaNosaukums = System.getProperty("user.home")
				+ File.separator + "Desktop" + File.separator + "minkas.txt";
		Minka runcis = null;
		
		String[] darbibuSaraksts = {"Izveidot kaķi", "Izsaukt metodi", 
				"Saglabāt failā", "Apskatīt saglabātos kaķus", "Aizvērt"};
		
		do {
			izvelne = (String)JOptionPane.showInputDialog(
				null, "Izvēlies darbību", "Darbību izvēle", 
				JOptionPane.QUESTION_MESSAGE, null, darbibuSaraksts,
				darbibuSaraksts[0]);
			
			if(izvelne == null)
				izvelne = "Aizvērt";
			
			switch(izvelne) {
				case "Izveidot kaķi":
					String minkasVards, skirne, spalvasKrasa, saimnieks, cels;
					int dzGads = 0;
					boolean siksnina;
					/* Novērst iespēju uzsākt objekta izveidi, ja pie datu ievades
					 * nospiests "Cancel" visiem atribūtiem
					 */
					
					minkasVards = virknesParbaude("Ievadi kaķa vārdu!", "Rudis");
					if(minkasVards == null)
						break;
					
					skirne = virknesParbaude("Ievadi kaķa šķirni!", "Meinkūns");
					if(skirne == null)
						break;
					
					spalvasKrasa = virknesParbaude("Ievadi kaķa kažoka krāsu", "Ruds");
					if(spalvasKrasa == null)
						break;
					
					dzGads = skaitlaParbaude("Ievadi kaķa dzimšanas gadu!", 
							(Year.now().getValue() - 18), Year.now().getValue());
					if(dzGads == -1)
						break;
					
					saimnieks = virknesParbaude("Ievadi kaķa saimnieka vārdu", "Mirdza");
					if(saimnieks == null)
						break;
					
					int poga = JOptionPane.showConfirmDialog(null,
							"Vai kaķim ir siksniņa?", "Kaķa siksniņas informācija", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(poga == -1)
						break;
					
					siksnina = (poga == 0)? true : false;
					cels = virknesParbaude("Ievadi bildes nosaukumu", "rudis");
					if(cels == null)
						break;
					
					runcis = new Minka(minkasVards, skirne, spalvasKrasa, saimnieks, 
								dzGads, siksnina, cels);
					break;
					
				case "Izsaukt metodi":
					if(runcis != null) {
						String[] metodes = {"Paglaudīt", "Nolasīt atribūtus", 
								"Pabarot", "Nolikt gulēt", "Palielināt vecumu", 
								"Apskatīt vecumu", "Medīt"};
						String m = (String)JOptionPane.showInputDialog(null, 
								"Izvēlies metodi", "Metodes izvēle", 
								JOptionPane.QUESTION_MESSAGE, null, 
								metodes, metodes[0]);
						if(m == null)
							break;
						
						switch(m) {
							case "Paglaudīt":
								runcis.murrat();
								break;
								
							case "Nolasīt atribūtus":
								JOptionPane.showMessageDialog(null, runcis.nolasitAtributus(), 
										"Informācija", JOptionPane.INFORMATION_MESSAGE);
								break;
								
							case "Pabarot":
								String atbilde = runcis.pabarot(
										virknesParbaude("Ar ko pabarot kaķi?", "Zivi"));
								JOptionPane.showMessageDialog(null, "Kaķis atgriež "+atbilde, 
										"Informācija", JOptionPane.INFORMATION_MESSAGE);
								break;
								
							case "Nolikt gulēt":
								String prieksmets = 
								virknesParbaude("Ko dosi kaķim līdzi uz gultiņu?", "Spilvens");
								if(prieksmets == null || prieksmets.isEmpty())
									runcis.gulet();
								
								else
									runcis.gulet(prieksmets);
								break;
								
							case "Palielināt vecumu":
								runcis.palielinatVecumu();
								break;
								
							case "Apskatīt vecumu":
								runcis.nolasitVecumu();
								break;
								
							case "Medīt":	
								runcis.medit();
								break;
						}
						
					} else
						JOptionPane.showMessageDialog(null,
						"Vispirms izveido kaķi!", "Kļūme", 
						JOptionPane.ERROR_MESSAGE);
					break;
					
				case "Saglabāt failā":
					if(runcis != null) {
						DarbsArFailu.saglabat(runcis, failaNosaukums);
						
					} else
						JOptionPane.showMessageDialog(null,
						"Vispirms izveido kaķi!", "Kļūme", 
						JOptionPane.ERROR_MESSAGE);
					break;
					
				case "Apskatīt saglabātos kaķus":
					DarbsArFailu.nolasit(failaNosaukums);
					break;
					
				case "Aizvērt":
					JOptionPane.showMessageDialog(null, "Programma apturēta!",
							"Informācija", JOptionPane.INFORMATION_MESSAGE);
					break;
			}
		} while(!izvelne.equals("Aizvērt"));
	}
// Kad iespējams iegūt kļūdas ziņojumus?
	// Ja neveidojot objektu cenšas saglabāt failā, tiek atgriezta kļūda
// Cik kaķus šobrīd varam izveidot (vienlaikus)?
	/* -Var izveidot tikai vienu objektu, 
	 * vajadzētu iespēju uzglabāt vairākus
	 */
}
