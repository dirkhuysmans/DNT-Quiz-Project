package controller;

import java.io.BufferedWriter;
import java.io.IOException;
import model.Leerling;
import model.LeerlingContainer;

public class leerlingController {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try{
		Leerling leerling = new Leerling("leerling1",1);
		LeerlingContainer lc = new LeerlingContainer();
		lc.voegLeerlingToe(leerling);
		leerling = new Leerling("leerling2",2);
		lc.voegLeerlingToe(leerling);
		
		/*lc.getLeerlingContainer();
		//System.out.print(lc.getLeerlingContainer());
		@SuppressWarnings("resource")
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(lc.getFileName())));
		for (Leerling lln:lc.getLeerlingContainer()){
			bw.write(lln.getNaam()+"; "+lln.getKlas()+"\n");
		}
		bw.write("Einde");
		bw.flush();
		
		BufferedReader br =  new BufferedReader(new FileReader(new File("leerlingen.txt"));
		*/
		lc.wegschrijven();
		lc=null;
		lc = new LeerlingContainer();
		lc.lezen();
		System.out.print(lc.toString());
		
		
		}
		
		catch (Exception e){
			throw new Exception(e.getMessage());
		}

	}

}
