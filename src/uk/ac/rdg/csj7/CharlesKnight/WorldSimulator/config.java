package uk.ac.rdg.csj7.CharlesKnight.WorldSimulator;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class config {

	private static String configuationPath = "C:\\Users\\Charlie\\workspace\\ArtificalWorldSimulator\\src\\uk\\ac\\rdg\\csj7\\CharlesKnight\\WorldSimulator\\config.csv";
	private static Boolean modified;
	
	public static Boolean writeConfiguationFile(String path, AWorld instance)
	{
		
		try {
			PrintWriter outConfig = new PrintWriter(path);
			outConfig.println(instance.worldToCSV());
			outConfig.close();
			return true;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static Boolean loadConfiguationFile(String Path, AWorld instance)
	{
		int loadDim = 0;
		int loadFood = 0;
		int loadObj = 0;
		int loadMax = 0;
		int loadNum = 0;
		//dimenson, densityFood, densityObj, maxSense, numBugs
		
		Scanner readConfig;
		try {
			readConfig = new Scanner(new File(configuationPath));

			readConfig.useDelimiter(",");
		        loadDim = readConfig.nextInt();
		        loadFood = readConfig.nextInt();
		        loadObj = readConfig.nextInt();
		        loadMax = readConfig.nextInt();
		        loadNum = readConfig.nextInt();
			readConfig.close();
			
			instance.newSimulation(loadDim, loadFood, loadObj, loadMax, loadNum);
			instance.printMap();
			
			return true;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
				
	}
	
	public static void displayConfiguationFile()
	{
		//Output CURRENT WORLD configuiation and if modified tell user it's modified
		System.out.println("\n**Simulation Configuation**\n");
		
	}
	
	public static String getConfigFromUserInputs()
	{
	//build string into: //dimenson, densityFood, densityObj, maxSense, numBugs
		// then reload/save ectera
		return "";
	}

	public static String getConfiguationPath() {
		return configuationPath;
	}

	public static void setConfiguationPath(String configuationPath) {
		config.configuationPath = configuationPath;
	}
	
}

