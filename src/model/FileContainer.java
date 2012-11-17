package model;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileContainer {

	public void toevoegenLijn(String lijn) {
		// TODO Auto-generated method stub
		
	}
	
	public void schrijven(String bestand, String info){
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(new File(bestand)));
			
			output.write(info);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	
	
	public String lezen (String bestand){
		BufferedReader input = null;
		String catalogus="";
		try {
			input = new BufferedReader(new FileReader(new File(bestand)));
			String lijn="";
			try {
				while((lijn = input.readLine()) != null){
					catalogus += lijn + "\n";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return catalogus;		
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
