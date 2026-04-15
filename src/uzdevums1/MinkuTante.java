package uzdevums1;

import java.io.File;
import java.time.Year;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MinkuTante {
	public static void main(String[] args) {
		String izvelne, failaNosaukums = System.getProperty("user.home")
				+ File.separator + "Desktop" + File.separator + "minkas.txt";
		Minka runcis = null;
		LinkedList<Minka> minkuSaraksts = new LinkedList<>();
		int indekss;
		 
		String[] darbibuSaraksts = {"Izveidot kaķi", 
				"Izveidot kaķi konkrētā pozīcijā",
				"Noteikt uzglabāto kaķu skaitu",
				"Izvadīt kaķu sarakstu",
				"Meklēt kaķi", "Izņemt kaķi no saraksta", 
				"Izņemt kaķi no saraksta pēc indeksa", 
				"Izņemt pirmo kaķi no saraksta", 
				"Izņemt pēdējo kaķi no saraksta", 
				"Dzēst visu sarakstu",
				"Kārtot sarakstu",
				"Izsaukt metodi", 
				"Saglabāt failā", "Apskatīt saglabātos kaķus", "Aizvērt"};
		
		do {
			izvelne = (String)JOptionPane.showInputDialog(
				null, "Izvēlies darbību", "Darbību izvēle", 
				JOptionPane.QUESTION_MESSAGE, null, darbibuSaraksts,
				darbibuSaraksts[0]);
			
			if(izvelne == null)
				izvelne = "Aizvērt";
			
			indekss = Arrays.asList(darbibuSaraksts).indexOf(izvelne);
			
			switch(indekss) {
				case 0:
				case 1:
					String minkasVards, skirne, spalvasKrasa, saimnieks, cels;
					int dzGads = 0;
					boolean siksnina;
					
					minkasVards = Metodes.virknesParbaude("Ievadi kaķa vārdu!", "Rudis");
					if(minkasVards == null)
						break;
					
					skirne = Metodes.virknesParbaude("Ievadi kaķa šķirni!", "Meinkūns");
					if(skirne == null)
						break;
					
					spalvasKrasa = Metodes.virknesParbaude("Ievadi kaķa kažoka krāsu", "Ruds");
					if(spalvasKrasa == null)
						break;
					
					dzGads = Metodes.skaitlaParbaude("Ievadi kaķa dzimšanas gadu!", 
							(Year.now().getValue() - 18), Year.now().getValue());
					if(dzGads == -1)
						break;
					
					saimnieks = Metodes.virknesParbaude("Ievadi kaķa saimnieka vārdu", "Mirdza");
					if(saimnieks == null)
						break;
					
					int poga = JOptionPane.showConfirmDialog(null,
							"Vai kaķim ir siksniņa?", "Kaķa siksniņas informācija", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(poga == -1)
						break;
					
					siksnina = (poga == 0)? true : false;
					cels = Metodes.virknesParbaude("Ievadi bildes nosaukumu", "rudis");
					if(cels == null)
						break;
					
					runcis = new Minka(minkasVards, skirne, spalvasKrasa, saimnieks, 
								dzGads, siksnina, cels);
					
					if (indekss == 0)
						minkuSaraksts.add(runcis);
					
					else if(indekss == 1) {
						int pozicija = 
						Metodes.skaitlaParbaude("Kurā pozīcijā ievietot kaķi?", 
						0, minkuSaraksts.size());
						minkuSaraksts.add(pozicija, runcis);
					}
					break;
					
				case 2:
					JOptionPane.showMessageDialog(null, 
							(minkuSaraksts.isEmpty())? "Sarkastā nav kaķu!" :
								"Sarakstā ir " + minkuSaraksts.size() + " kaķi",
								"Kaķu skaits", JOptionPane.INFORMATION_MESSAGE);
					break;
					
				case 3:
					if(minkuSaraksts.isEmpty())
						JOptionPane.showMessageDialog(null,
								"Saraksts ir tukšs!", "Kaķu saraskts", 
								JOptionPane.WARNING_MESSAGE);
					
					else {
						String virkne = "";
						for(Minka i : minkuSaraksts) {
							virkne += i.nolasitAtributus() +
							"\n+++++++++++++++++++++++++++++++++\n";
						}
						
						JTextArea ta = new JTextArea(virkne, 10, 40);
						ta.setEditable(false);
						JScrollPane sp = new JScrollPane(ta);
						JOptionPane.showMessageDialog(null, sp, 
								"Saglabātie kaķi", JOptionPane.PLAIN_MESSAGE);
					}
					break;
					
				case 4:
					if(minkuSaraksts.isEmpty())
						JOptionPane.showMessageDialog(null,
								"Saraksts ir tukšs!", "Kaķu saraskts", 
								JOptionPane.WARNING_MESSAGE);
					
					else {
						String vards = Metodes.virknesParbaude(
								"Kā sauc kaķi, kuru vēlies atrast?", "Rudis");
						int kuruAtrast = Metodes.meklet(minkuSaraksts, vards);
						
						if(kuruAtrast == -1)
							break;
						
						JOptionPane.showMessageDialog(null, 
								minkuSaraksts.get(kuruAtrast).nolasitAtributus(), 
								"Informācija", JOptionPane.INFORMATION_MESSAGE);
					}
					break;
					
				case 5:					
				case 6:
					if(minkuSaraksts.isEmpty())
						JOptionPane.showMessageDialog(null,
								"Saraksts ir tukšs!", "Kaķu saraskts", 
								JOptionPane.WARNING_MESSAGE);
					
					else {
						if(indekss == 5) {
							String vards = Metodes.virknesParbaude(
									"Kā sauc kaķi, kuru vēlies dzēst?", "Rudis");
							int kuruDzest = Metodes.meklet(minkuSaraksts, vards);
							
							if(kuruDzest == -1)
								break;
							
							minkuSaraksts.remove(kuruDzest);
						
						} else if (indekss == 6) {
							int pozicija = Metodes.skaitlaParbaude(
							"Kurā pozīcijā atrodas dzēšamais kaķis?",
							0, minkuSaraksts.size()-1);
							minkuSaraksts.remove(pozicija);
						}
					}
					break;
					
				case 7:		
					case 8:
						if(minkuSaraksts.isEmpty())
							JOptionPane.showMessageDialog(null,
									"Saraksts ir tukšs!", "Kaķu saraskts", 
									JOptionPane.WARNING_MESSAGE);
						
						else {
							if(indekss == 7)
								minkuSaraksts.removeFirst();
							
							else if (indekss == 8)
								minkuSaraksts.removeLast();
							
							JOptionPane.showMessageDialog(null,
									"Izvēlētais kaķis dzēsts!", "Informācija", 
									JOptionPane.INFORMATION_MESSAGE);
						}
						
					break;
					
				case 9:
					
					break;
				/*	
				case 11:
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
					*/
				case 12:
					if(runcis != null) {
						DarbsArFailu.saglabat(runcis, failaNosaukums);
						
					} else
						JOptionPane.showMessageDialog(null,
						"Vispirms izveido kaķi!", "Kļūme", 
						JOptionPane.ERROR_MESSAGE);
					break;
					
				case 13:
					DarbsArFailu.nolasit(failaNosaukums);
					break;
					
				case 14:
					JOptionPane.showMessageDialog(null, "Programma apturēta!",
							"Informācija", JOptionPane.INFORMATION_MESSAGE);
					break;
			}
		} while(indekss != 14);
	}
// Kad iespējams iegūt kļūdas ziņojumus?
	// Ja neveidojot objektu cenšas saglabāt failā, tiek atgriezta kļūda
// Cik kaķus šobrīd varam izveidot (vienlaikus)?
	/* -Var izveidot tikai vienu objektu, 
	 * vajadzētu iespēju uzglabāt vairākus
	 */
}
