package uk.ac.rdg.csj7.CharlesKnight.WorldSimulator;

import uk.ac.rdg.csj7.CharlesKnight.WorldSimulator.AWorld.Direction;

public class ABug {
	private String bugSpecies = "NotSet!";
	private String bugName = "NotSet!";
	private char bugSymbol = '?';
	private MapCoord coords; //TODO: Add get setter
	private int bugEnergy = -1;
	private int bugID = -1;
	private AWorld world;


	public ABug() //Empty Constructor
	{}
	
	public ABug(String species, String name, MapCoord position, 
			int energy, int ID, AWorld world)
	{
		bugSpecies = species;
		bugName = name;
		bugSymbol = name.charAt(0);
		coords = position;
		bugEnergy = energy;
		bugID = ID;
		setWorld(world);
	}
	
	public String toString()
	{
		return bugName + "(" + coords.x + "," + coords.y + ")";
	}
	public String toText()
	{
		return "Bug ID: " + bugID + "\nBug Name: " + bugName + "\nBug Species: " + bugSpecies + "\nBug Symbol: " + bugSymbol +
				"\nPosition (H, V): " + "(" + coords.x + "," + coords.y + ")" +
				"\nBug Energy: " + bugEnergy + "\n ---------------------- \n";
	}
	public String toCSV()
	{
		//id, name, species, symbol, x, y, energy
		String builder = this.bugID + ", " + this.bugName + ", " + this.bugSpecies + ", " + this.bugSymbol + ", "+
		this.coords.x + ", " + this.coords.y + ", " + this.bugEnergy;
		
		return builder;
	}
	
	
    private void setWorld(AWorld w) {
        world = w;
        
    }
    
    private MapCoord getCoordsFromDirection(MapCoord startingCoords, Direction d, int offset)
    {
    	int newX = startingCoords.x;
    	int newY = startingCoords.y;
    	
    	switch (d)
		{
		case North:
			newX -= offset;
			break;
		case South:
			newX += offset;
			break;
		case West:
			newY -= offset;
			break;
		case East:
			newY += offset;
			break;
		case None:
			break;
		}
    	
    	return (new MapCoord(newX, newY));
    }
    
    //World functions
    private int smellFood(Direction d)
    {
    	int maxSense = world.getMaxSense(); //TODO: Get from world
    	
    	for (int i = 0; i < maxSense; i++)
    	{
    		if (Character.isDigit(world.getMapSquare(getCoordsFromDirection(coords, d, i))))
    		{
    			return i; //Return how far away the food is
    			
    		}
    	}
    	
    	return 320000; //No food was found.    	
    }
    
    private Direction getDirectionOfFood()
    {
    	int nearestSlot = 320000;
    	Direction inDirection = Direction.None;
    	
    	if (smellFood(Direction.North) < nearestSlot)
    	{
    		nearestSlot = smellFood(Direction.North);
    		inDirection = Direction.North;
    	}
    	if (smellFood(Direction.South) < nearestSlot)
    	{
    		nearestSlot = smellFood(Direction.South);
    		inDirection = Direction.South;
    	}
    	if (smellFood(Direction.East) < nearestSlot)
    	{
    		nearestSlot = smellFood(Direction.East);
    		inDirection = Direction.East;
    	}
    	if (smellFood(Direction.West) < nearestSlot)
    	{
    		nearestSlot = smellFood(Direction.West);
    		inDirection = Direction.West;
    	}
    	
    	return inDirection;
    }
    
    public void foodFindMove()
    {
    	Direction anyFood = getDirectionOfFood();
    	if (anyFood != Direction.None)
    	{
    		System.out.println("Bug " +this.bugSymbol + " has found food in " + anyFood + 
    				" at " + smellFood(anyFood) + " places away!");
    		move(getDirectionOfFood()); //Move in the direction of food
    	}
    	else
    	{

    		move(AWorld.randDirection()); //Move in a random direction
    	}
    }
    
    public int move(Direction d)
    {
    	// - if an obstacle or move bug.
    	// 0 if succeed or + if it's food
		try {
		
			int dim = world.getDim();
			MapCoord tempNewCoords = getCoordsFromDirection(coords, d, 1);
			if (tempNewCoords.x >= (dim-1) ||  tempNewCoords.x < 1 || tempNewCoords.y < 1 || tempNewCoords.y >= (dim-1) ) //Not out of bounds
			{
				System.out.println("couldn't move bug: " + getSymbol() + " due to OOB");
				return -1;
			}
			if (Character.isLetter(world.getMapSquare(tempNewCoords)) && world.getMapSquare(tempNewCoords) == ' ')
			{
				System.out.println("couldn't move bug: " + getSymbol() + " due to object/bug in the way!");
				return -1;
			}
			else
			{
				int foodScore = 0;
				if (Character.isDigit(world.getMapSquare(tempNewCoords)))
				{
					foodScore += (int) (world.getMapSquare(tempNewCoords) - 48);
					this.setEnergy(getEnergy() + foodScore);
					System.out.println("Bug " + this.getSymbol() + " eat food val:" + foodScore);
				}
				
				world.updateMapSquare(coords, ' '); //Clear current Square
				this.setPosition(tempNewCoords); //new coords + 1 on the direction
				world.updateMapSquare(coords, getSymbol());

				System.out.println("\nBug" + getName() + " has moved: " + d);
				
				return foodScore;
			}
			
		}
			catch (IndexOutOfBoundsException e)
			{
				System.out.println("\noob!");
				return -1;
			}
		
    }

	
	//Get and Set For the Bug Species
	public String getSpecies()
	{ return bugSpecies; }
	public void setSpecies(String Species)
	{ bugSpecies = Species; }
	
	//Get and Set for the Bug's Name
	public String getName()
	{ return bugName; }
	public void setName(String Name)
	{ bugName =  Name; }
	
	//Get and Set for Bug's Symbol
	public char getSymbol()
	{ return bugSymbol;	}
	public void setSymbol(char Symbol)
	{ bugSymbol = Symbol; }	
	
	//Get and Set for Bug's Energy
	public int getEnergy()
	{ return bugEnergy; }
	public void setEnergy(int Energy)
	{ bugEnergy = Energy; }
	
	//Get and Set for Bug's ID
	public int getID()
	{ return bugID; }
	public void setID(int ID)
	{ bugID = ID; }

	public MapCoord getPosition()
	{
		return coords;
	}
	public void setPosition(MapCoord pos)
	{
		coords = pos;
	}
	
}
