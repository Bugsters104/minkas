package uzdevums1;

import java.util.LinkedList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Metodes {
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
	
	static int meklet(LinkedList<Minka> minkuSaraksts, String vards) {
		for(int i=0; i<minkuSaraksts.size(); i++) {
			if(minkuSaraksts.get(i).apskatitVardu().equalsIgnoreCase(vards)) {
				return i;
			}
		}
		
		JOptionPane.showMessageDialog(null, "Kaķis ar vārdu: " + vards + 
				" netika atrasts!", "Informācija", 
				JOptionPane.WARNING_MESSAGE);
		
		return -1;
	}
}
