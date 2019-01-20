import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WeatherParser {
	private static ArrayList<String> cities = new ArrayList<String>();
	private static ArrayList<Integer> currentTemperatures = new ArrayList<Integer>();
	private static ArrayList<Integer> dayLows = new ArrayList<Integer>();
	private static ArrayList<Integer> dayHighs = new ArrayList<Integer>();
	private static ArrayList<Integer> humidities = new ArrayList<Integer>();
	private static ArrayList<Float> pressures = new ArrayList<Float>();
	private static ArrayList<Float> visibilities = new ArrayList<Float>();
	private static ArrayList<Float> windspeed = new ArrayList<Float>();
	private static ArrayList<Integer> windDirections = new ArrayList<Integer>();
	private static ArrayList<String> conditionDescription = new ArrayList<String>();
	private int cityNumber; //Will correspond to the current city being analyzed
	
	public static void main(String [] args)
	{
		System.out.print("Enter the name of an input file: ");
		Scanner scan = new Scanner(System.in);
		String filename = scan.nextLine();
		System.out.println("filename = " + filename);
		
		System.out.println("heeey");
		try {
			int infoNumber = 1;
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr); //Allows you to read line by line
			String line = br.readLine(); //Basically getline in c++, line by line
			String currWord = "";
			int currInt = 0;
			float currFloat = 0;
			while(line != null)
			{
				for(int i = 0; i < line.length(); i++)
				{
					while(i < line.length() && line.charAt(i) != '|')
						{
							
							if(i != line.length()-1 && line.charAt(i+1) != '|' )
							{
								if(infoNumber == 2 || infoNumber == 3 || infoNumber == 4 || infoNumber == 5 || infoNumber == 9)
								{
									currInt += line.charAt(i) -48;
									currInt *= 10;
								}
								else if(infoNumber == 1 || infoNumber == 10)
								{
									currWord += line.charAt(i);
								}
								else if(infoNumber < 9)
								{
									if(line.charAt(i) != '.' && line.charAt(i+1) != '.')
									{
										currFloat += line.charAt(i) -48;
										currFloat *= 10;
									}
									else if(line.charAt(i) != '.')
									{
										currFloat += line.charAt(i) -48;
									}
								}
							}
							else if(infoNumber == 1)
							{
								currWord += line.charAt(i);
								infoNumber++;
								cities.add(currWord);
								currWord = "";
							}
							else if(infoNumber == 2)
							{
								currInt += line.charAt(i)-48;
								infoNumber++;
								currentTemperatures.add(currInt);
								currInt = 0;
							}
							else if(infoNumber == 3)
							{
								currInt += line.charAt(i)-48;
								infoNumber++;
								dayLows.add(currInt);
								currInt = 0;
							}
							else if(infoNumber == 4)
							{
								currInt += line.charAt(i)-48;
								infoNumber++;
								dayHighs.add(currInt);
								currInt = 0;
							}
							else if(infoNumber == 5)
							{
								currInt += line.charAt(i)-48;
								infoNumber++;
								humidities.add(currInt);
								currInt = 0;
							}
							else if(infoNumber == 6)
							{
								
								currFloat += (double)(line.charAt(i) -48) / 10;
								infoNumber++;
								pressures.add(currFloat);
								currFloat = 0;
				
							}
							else if(infoNumber == 7)
							{
								
								currFloat += (double)(line.charAt(i) -48)/ 10;
								infoNumber++;
								visibilities.add(currFloat);
								currFloat = 0;
								
							}
							else if(infoNumber == 8)
							{
								currFloat += (double)(line.charAt(i)-48) /10;
								infoNumber++;
								windspeed.add(currFloat);
								currFloat = 0;
							}
							else if(infoNumber == 9)
							{
								currInt += line.charAt(i)-48;
								infoNumber++;
								windDirections.add(currInt);
								currInt = 0;
							}
							else if(infoNumber == 10)
							{
								currWord += line.charAt(i);
								conditionDescription.add(currWord);
								currWord = "";
							}
							i++;	
						}
				
					
				}
				infoNumber = 1;
				
				line = br.readLine();
				
			}
		
			
			int cityNum = 0;
			boolean cityExists = false;
			String inputCity;
			while(cityExists == false)
			{
			System.out.print("For what city would you like weather information? ");
			inputCity = scan.nextLine();
			for(int i = 0; i < cities.size(); i++)
			{ 
				if(cities.get(i).toLowerCase().equals(inputCity.toLowerCase()));
				{
					cityNum = i;
					cityExists = true;
					break;	
				}
			}
			if(cityExists == true)
			{
			   System.out.println("I do have information about the weather in " + inputCity);
			}
			else
			{
				System.out.println("Unable to find city " + inputCity);
			}
			}
			String possibilities = "1) Temperature \n" + "2) High and low temperature today \n" +
			"3) Humidity \n"+
			"4) Pressure \n" +
			"5) Visibility \n" +
			"6) Wind speed and direction \n" +
			"7) Descriptions of weather conditions \n" +
			"8) Everything \n" +
			"9) Enter a different city ";
			System.out.println(possibilities);
			System.out.println("What information would you like to know?");
			String next = scan.nextLine();
			while(next.length() > 1 || (int)next.charAt(0)-48 > 10 || (int)next.charAt(0)-48 < 0)
			{
				System.out.println("That is not a valid option");
				next = scan.nextLine();	
			}
			infoNumber = (int)next.charAt(0) -48;
			scan.close();
			br.close();
			fr.close();
			
		} catch(FileNotFoundException fnfe) {
			System.out.println("fnfe: " + fnfe.getMessage());
		} catch(IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
		
	}
}
