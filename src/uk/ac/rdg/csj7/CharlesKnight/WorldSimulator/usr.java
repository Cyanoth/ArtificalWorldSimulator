package uk.ac.rdg.csj7.CharlesKnight.WorldSimulator;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class usr {

	private static AWorld interactWorld;
	private static Boolean programRunning = true;
	
	public static void setWorld(AWorld setWorld)
	{
		interactWorld = setWorld;
	}
	
	public static void displayMenu()
	{
		System.out.println("File\n\t1. New configuration\n\t2. Open Configuation File\n\t3. Save Current Configuation\n\t"
				+ "4. Save As..\n\t5. Exit\nView\n\t6. Display Current Configuation\n\t7. Edit Configuation\n\t8. Display All Life Forms Info\n\t"
				+ "9. Display Map Info\nEdit\n\t10. Modify Current Life Form\n\t11. Remove Current Life Form\n\t12. Add New Life Form\n"
				+ "Simulation\n\t13. Run\n\t14. Pause/Restart\n\t15. Reset\n\t16. Toggle Map at Each Interation(CUR)\nHelp\n\t"
				+ "17. Display Application Information\n\t18. Display Author Information");
	}
	
	public static void startMenu()
	{
		Scanner usrRead = new Scanner(System.in);
	
		
		while (programRunning)
		{
			//Get user input
			displayMenu();
			System.out.println("\n\nInsert Option:");
			int usrInput = usrRead.nextInt();
			
		switch (usrInput)
		{
			case 1: //New Configuation
				config.getConfigFromUserInputs();
				break;
			case 2: //Open Configuation
				config.loadConfiguationFile(config.getConfiguationPath(), interactWorld);
				break;
			case 3: //Save Configuation
				config.writeConfiguationFile(config.getConfiguationPath(), interactWorld);
				break;
			case 4: //Save As Configuation
				//Add JDialog Browse
				break;
			case 5: //Exit
				programRunning = false;
				break;
			case 6: //Display current configuation
				config.displayConfiguationFile();
				break;
			case 7: //Edit Configuation
				config.getConfigFromUserInputs();
				break;
			case 8: //Display all lifeform informations
				interactWorld.announceLifeFormInformation();
				break;
			case 9: //Display Map Information
				interactWorld.announceMapInformation();
				break;
			case 10: //Modify Current Life Form
				break;
			case 11: //Remove current life form
				break;
			case 12: //Add new life form
				break;
			case 13: //Run Simulation
				//TODO: Ask user for amount of cycles
				interactWorld.runSimulation(10);
				break;
			case 14: //Pause/Restart Simulation
				//???
				break;
			case 15: //Reset Simulation
				interactWorld.resetSimulation();
				break;
			case 16: //Toggle Map Iteration
				interactWorld.toggleMapPrint();
				break;
			case 17: //Display app info
				displayApplicationInfo();
				break; 
			case 18: //display auth info
				displayAuthorInfo();
				break;
			default:
				System.out.println("Invalid Option!");
				break;
		}
		
		
		System.out.println("\nPress Enter To Continue...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		
	}
	
	private static ABug bugWritter(String typeWrite)
	{
		return null;
	}
	
	private static void displayApplicationInfo()
	{
		System.out.println("The application version is 1.0.0");
	}
	
	private static void displayAuthorInfo()
	{
		System.out.println("The author of this application is Charles Knight");
	}
	
	
}
