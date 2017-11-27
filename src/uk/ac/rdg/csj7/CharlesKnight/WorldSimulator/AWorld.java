package uk.ac.rdg.csj7.CharlesKnight.WorldSimulator;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import uk.ac.rdg.csj7.CharlesKnight.WorldSimulator.AWorld.Direction;

public class AWorld {

	public enum Direction { North, South, East, West, None };
	
	private int dim;
	private int densityFood;
	private int desnityObstacles;
	private int maxSensingDistance;
	private int numBug;
	private Boolean mapPrint = true;
	private char[][] DMap;
	private List<ABug> bugs = new ArrayList<ABug>();
	
	public AWorld()
	{
			System.out.println("An empty world has been created!");
	}
	
	public AWorld(int dimenson, int densityFood, int densityObjectes, int maxSense, int numBugs)
	{
		newSimulation(dimenson, densityFood, densityObjectes, maxSense, numBugs);	
	}
	
	public void resetSimulation()
	{
		
	}
	
	public void newSimulation(int dimenson, int densityFood, int densityObjectes, int maxSense, int numBugs)
	{
		this.setDim(dimenson);
		this.densityFood = densityFood;
		this.desnityObstacles = densityObjectes;
		this.maxSensingDistance = maxSense;
		this.numBug = numBugs;
		
		DMap = new char[getDim()][getDim()];
		createMap();
		populateMap(); //Add Food & Obsacles
		populateEntites(); //Add some bugs
		
	}
	
	//Map Creation & Population Functions
	private void createMap() //Create an empty map with borders.
	{
		for (int y = 0; y < getDim(); y++) //Fill With Empties
		{
			for (int x =0; x < getDim(); x++)
			{
				DMap[x][y] = ' ';
			}
		}
		
		for (int y = 0; y < getDim(); y++) //Fill The Borders
		{
			for (int x =0; x < getDim(); x++)
			{
				if (x == 0 || x == getDim() - 1)
				{
					DMap[x][y] = '-';
				} 
				if (y == 0 | y == getDim() - 1)
				{
					DMap[x][y] = '|';
				}
			}
		}
	
	}
	
	private void populateMap()
	{
		for (int fd=0; fd < densityFood; fd++)
		{
			//Add a food block which is a random number between 1 and 9
			updateMapSquare((new MapCoord(randLocation(), randLocation())), Character.forDigit(randInt(0,9), 10));					
		}
		
		for (int ob=0; ob < desnityObstacles; ob++)
		{
			//Add an obstacle
			updateMapSquare((new MapCoord(randLocation(), randLocation())), 'X');
		}
		
	}
	
	private void populateEntites()
	{
		for (int bu=0; bu < numBug; bu++)
		{
			bugs.add(new ABug("Dog", String.valueOf((char) (bu + 65)), (new MapCoord(randLocation(), randLocation())), 0, bu, this));
			updateMapSquare(bugs.get(bu).getPosition(), bugs.get(bu).getSymbol());
		}
	}
	
	//Map Update Functions
	public void printMap()
	{
		if (mapPrint)
		{
			for (int y = 0; y < getDim(); y++)
			{
				System.out.println("");
				for (int x =0; x < getDim(); x++)
				{
					System.out.print("  " + DMap[y][x]);
				}
			}
		}
		
	}
	
	public void updateMapSquare(MapCoord loc, char val)
	{
		DMap[loc.x][loc.y] = val;		
	}
	
	public char getMapSquare(MapCoord loc)
	{
		try {
		return DMap[loc.x][loc.y];
		
		}
		catch (IndexOutOfBoundsException e) {
			return 'X';
		}
	}
	
	//Simulation Function
	public void runSimulation(int numOfCycles)
	{
		for (int nu = 0; nu < numOfCycles; nu++)
		{
			System.out.println("\n\n\n****************** CYCLE ***********");
			System.out.println("******************" + "NUM: " + nu + "***********");
			
			for (int bg = 0; bg < numBug; bg++)
			{
				bugs.get(bg).foodFindMove();
			}
			
			printMap();			
		}
		
		for (int bg = 0; bg < numBug; bg++)
		{
			System.out.println("\n\n**FINAL STATS**");
			System.out.println("Bug: " + bugs.get(bg).getSymbol() + " Energy: " + bugs.get(bg).getEnergy());
		}
		
		System.out.println("end of simulation!)");
	}

	public void announceLifeFormInformation()
	{
		for (int bg =0; bg <numBug; bg++)
		{
			System.out.println("Bug " + bg + ": Name: " + bugs.get(bg).getName() + " Position: (" + bugs.get(bg).getPosition().x + 
					", " + bugs.get(bg).getPosition().y + ") Energy: " + bugs.get(bg).getEnergy());
		}
	}
	
	public void announceMapInformation()
	{
		System.out.println("Map Size: " + dim + " Obstacles: " + desnityObstacles + " Starting Food: " + densityFood + 
				"Remaining Food: " + "??"); //TODO make function to find how much food is left.
	}
	
	public int getMaxSense()
	{
		return maxSensingDistance;
	}
	
	//Random Generators
	public static Direction randDirection()
	{
		int val = randInt(0, 3);
		switch (val)
		{
		case 0:
			return Direction.North;
		case 1: 
			return Direction.South;
		case 2:
			return Direction.East;
		case 3:
			return Direction.West;
		default:
			return Direction.North;
		}
				
	}
	
	public int randLocation() {

	    Random rand = new Random();
	    int randomNum = rand.nextInt(((this.getDim()-2) - 1) + 1) + 1;

	    return randomNum;
	}
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	public int getDim() {
		return dim;
	}

	public void setDim(int dim) {
		this.dim = dim;
	}
	
	public String worldToCSV()
	{
		//dimenson, densityFood, densityObj, maxSense, numBugs
		String builder = this.dim + "," + this.densityFood + "," + this.desnityObstacles + "," + this.maxSensingDistance +
				"," + this.numBug;
		return builder;
	}

	public void toggleMapPrint() {
		mapPrint = !mapPrint; //toggle mapprint
	}
	
}
