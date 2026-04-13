package uzdevums1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class DarbsArFailu {
	static void saglabat(Minka runcis, String failaNosaukums) {
		try {
			FileWriter fw = new FileWriter(failaNosaukums, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(runcis.nolasitAtributus());
			pw.println("++++++++++++++++++++++++++++++++++++++");
			pw.close();
			JOptionPane.showMessageDialog(null,
					"Ieraksts ievietots "+failaNosaukums, "Paziņojums",
					JOptionPane.INFORMATION_MESSAGE);
			
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Kļūda ierakstot failā!",
					"Kļūda", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	static void nolasit (String failaNosaukums) {
		// Pabeigt metodi faila nolasīšanai
	}
}
