import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		// Create a HashMap object called capitalCities
		HashMap<String, String> capitalCities = new HashMap<String, String>();

		// Add keys and values (Country, City)
		capitalCities.put("England", "London");
		capitalCities.put("Germany", "Berlin");
		capitalCities.put("Norway", "Oslo");
		capitalCities.put("USA", "Washington DC");
		System.out.println(capitalCities);
		//hashMap Size
		System.out.println(""+capitalCities.size());

		//loop in a hash map
		// Print keys(countries)
		for (String i : capitalCities.keySet()) 
		{
			System.out.println(i);
		}


		//loop in values
		// Print values(capitals)
		for (String i : capitalCities.values()) 
		{
			System.out.println(i);
		}

		// Print keys and values
		for (String i : capitalCities.keySet()) 
		{
			System.out.println("key: " + i + " value: " + capitalCities.get(i));
		}


		System.out.println(""+capitalCities.get("England"));
		capitalCities.remove("England");
		System.out.println(capitalCities);
		capitalCities.clear();
		System.out.println(capitalCities);
		
		
		
		////////////////////////////////////////////////
		//another example
		
		
		// Create a HashMap object called people
	    HashMap<String, Integer> people = new HashMap<String, Integer>();


	    // Add keys and values (Name, Age)
	    people.put("John", 32);
	    people.put("Steve", 30);
	    people.put("Angie", 33);

	    for (String i : people.keySet()) {
	      System.out.println("key: " + i + " value: " + people.get(i));
	    }
		
		
		
		
	}
}