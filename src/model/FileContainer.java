package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public abstract class FileContainer {
	

	public void lezen(String bestand){
		try{
			File file = new File(bestand);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String lijn = null;
			while (!(lijn = reader.readLine()).equals("EINDE")){
				toevoegenLijn(lijn);
			}
			reader.close();
		}
		catch (Exception ex){
			
		}
	}
	
	public abstract void toevoegenLijn(String lijn);

	public void schrijven(String bestand, String catalogus){
		try{
			File file = new File(bestand);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(catalogus);
			writer.close();
		}
		catch (Exception ex){
			
		}
	}
}
