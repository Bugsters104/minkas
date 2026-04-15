package uzdevums1;

import java.io.BufferedReader;
import java.io.FileReader;
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
		String teksts, str = "";
		try {
			FileReader fr = new FileReader(failaNosaukums);
			BufferedReader br = new BufferedReader(fr);
			while((teksts = br.readLine()) != null) {
				str += teksts + "\n";
			}
			br.close();
			JOptionPane.showMessageDialog(null, str, "Saglabātie kaķi", 
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Kļūda nolasot failu!", 
					"Kļūme", JOptionPane.ERROR_MESSAGE);
		}
	}
}
