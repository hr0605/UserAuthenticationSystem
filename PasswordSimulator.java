package myProjects;

/* ----------------------------------------------------------------------------------------
 * Creator: Hamshika Rajkumar
 * Purpose: Guides the user in creating a username and a strong password
 * Includes a way to store the username and password
 * Simulates  the experience of logging in  
 * Providing the user with a few tips to ensure security
 * Done by taking the user through 3 stages: 
 * Stage 1 - Creating a username and strong password
 * Stage 2 - Storing username and password
 * Stage 3 - Practice logging in
 * ----------------------------------------------------------------------------------------
 */
import java.util.Scanner;
import java.util.ArrayList;
public class PasswordSimulator {
	// declare static variables
	private static Scanner kbd = new Scanner(System.in);
	private static String password, username;
	private static int passNum = 0;
	
	//create ArrayLists to hold usernames and passwords
	private static ArrayList<String> usernames = new ArrayList<String>();
	private static ArrayList<String> passwords = new ArrayList<String>();
	
	//main method - runs all stages of program
	public static void main (String[] args) {
		System.out.println("Welcome to the Password Simulator");
		// while loop to repeat program for as many sets of usernames and passwords as desired by the user
		String again = "yes";
		while(again.equalsIgnoreCase("yes")) {
			//runs Stage 1 
			System.out.println("\n-----------------------------------------------");
			System.out.println("Stage 1: Creating a username and strong password");
			create();
			
			//runs Stage 2
			System.out.println("\n-----------------------------------------------");
			System.out.println("Stage 2: Storing username and password");
			store();
			passNum++;
			
			//runs Stage 3
			System.out.println("\n-----------------------------------------------");
			System.out.println("Stage 3: Practice logging in");
			login();
			
			//repeat program if the user wants to
			System.out.println("Would you like to go through the stages with another password?");
			System.out.println("If so, enter \"yes\", else, enter \"no\".");
			again = kbd.next();
		}
		
		//end of program, gives user one more chance to view stored username or password
		System.out.println("Thank you for using the Password Simulator.");
		System.out.println("If you would like to view any of your stored usernames or passwords enter \"yes\", else, enter \"no\".");
		String view = kbd.next();
		if(view.equalsIgnoreCase("yes")) {
			System.out.println("Please enter the location of your username or password: ");
			int loc = kbd.nextInt();
			System.out.println("Please enter \"user\" to view username and \"pass\" to view password: ");
			String uOrP = kbd.next();
			
			//calls show() method to display the requested information
			if(uOrP.equalsIgnoreCase("user"))
				show(loc, "user");
			else if(uOrP.equalsIgnoreCase("pass"))
				show(loc, "pass");
		}
	}
	
	// this method (Stage 1) guides the user in creating a username and strong password
	public static void create() {
		//gets username from user and stores in ArrayList 
		System.out.println("Enter a username: ");
		String user = kbd.next();
		username = user;
		
		//lists requirements for a strong password
		System.out.println("Password must meet the following requirements to ensure safety: ");
		System.out.println("- must include upper and lower case letters\n- must have at least 2 numbers");
		System.out.println("- must have at least 2 special characters (no spaces)\n- must be at least 12 characters long");
		System.out.println("*Tip: Don't include any personal information in your password.");
		
		// while loop so the user can repeatedly create a new password until it meets all requirements
		boolean tryAgain = true; 
		while(tryAgain) {
			System.out.println("Enter a password: ");
			String pass = kbd.next();
			//calls check method to determine if the password is appropriate
			// if not, returns which requirement has not been met
			String check = checkPassword(pass);
			
			// prints statement based on return value from checkPassword()
			if(check.equals("length"))
				System.out.println("Your password must be at least 12 characters. Try again.");
			else if(check.equals("upper"))
				System.out.println("Your password must include upper case letters. Try again.");
			else if (check.equals("lower"))
				System.out.println("Your password must include lower case letters. Try again.");
			else if (check.equals("num"))
				System.out.println("Your password must have at least 2 numbers. Try again.");
			else if (check.equals("special"))
				System.out.println("Your password must have at least 2 special characters. Try again.");
			else {
				System.out.println("Congratulations! Your password is strong.");
				
				//password meets all requirements, prompts user to confirm password within 5 tries
				System.out.println("Please confirm your password within 5 tries or you will have to re-create your password: ");
				String cPass = kbd.next();
				for(int j = 4; j >0; j--) {
					if(cPass.compareTo(pass)!= 0) {
						//decreases number of tries each time password is confirmed incorrectly
						System.out.println("Your password does not match. You have " + j + " more tries to confirm this password.");
						System.out.println("Please confirm your password: ");
						cPass = kbd.nextLine();
					}
					else {
						// password is confirmed, end of Stage 1
						System.out.println("Your password is confirmed!");
						tryAgain = false;
						password = pass;
						break;
					}
				}
			}
		}
	}
	
	// this method (used in Stage 1) returns whether all requirements are met for the password
	// or which requirement is not being met, based on the password that is passed as a parameter 
	// if multiple requirements are not met, will return one and in the user's next attempt will return the other
	public static String checkPassword(String pw) {
		if(pw.length() < 12)
			return "length";
		else {
			//checks each char of String pw and counts how many chars align with each requirement 
			int upper =0, lower = 0, num = 0, special = 0;
			for(int i = 0; i <pw.length(); i++) {
				//counts number of uppercase letters
				if(pw.charAt(i) >= 'A' && pw.charAt(i) <= 'Z')
					upper++;
				//counts number of lowercase letters
				else if(pw.charAt(i) >= 'a' && pw.charAt(i) <= 'z')
					lower++;
				//counts number of numbers
				else if(pw.charAt(i) >= '0' && pw.charAt(i) <= '9')
					num++;
				//counts number of special characters
				else
					special++;
			}
			
			//based on the data collected above,
			//returns of which char there isn't enough of
			// or returns "strong" if all requirements are met
			if (upper < 0)
				return "upper";
			else if (lower < 0)
				return "lower";
			else if (num <2)
				return "num";
			else if (special <2)
				return "special";
			else
				return "strong";
		}
		
	}
	
	//this method (Stage 2) stores the username and password created in Stage 1
	//stores in the appropriate ArrayList
	public static void store() {
		//adds element username to ArrayList username
		usernames.add(username);
		//adds element password to ArrayList password
		passwords.add(password);
		// prints to user the index at which their username and password has been stored
		// (both are stored at the same index if the appropriate ArrayList)
		System.out.println("Your username and password has been stored at location " + passNum + ".");
		System.out.println("*Remember this location*");
		//tip for remembering passwords
		System.out.println("*Tip: Although this database stores your username and password,\n always write down important account information. ");
		System.out.println("Make sure the information is written in a secure location inaccessable to unwanted people*");
		
		// while loop to show user the username and password they just stored as many times 
		//until they say "no"
		String show = "user";
		while(show.equalsIgnoreCase("user") || show.equalsIgnoreCase("pass")) {
			System.out.println("Enter \"user\" to see your username.\nEnter \"pass\" to see your password.");
			System.out.println("Enter \"no\" to for neither.");
			show = kbd.nextLine();
			
			//calls method show() to display information as requested by user
			if(show.equalsIgnoreCase("user"))
				show(passNum, "user");
			else if(show.equalsIgnoreCase("pass"))
				show(passNum, "pass");
		}
		//end of Stage 2
	}
	
	// this method takes a String location and a String userOrPass as parameters
	// it then prints the appropriate information at location for the user to view
	public static void show(int location, String userOrPass) {
		if(userOrPass.equals("user"))
			System.out.println(usernames.get(location));
		else if(userOrPass.equals("pass"))
			System.out.println(passwords.get(location));
	}
	
	//this method (Stage 3) helps the user practice logging in 
	// to ensure user is able to log in without any error
	// and they have recorded the correct username, password, and location
	public static void login() {
		boolean success = false;
		System.out.println("You have 5 tries to login successfully.");
		//for loop for a specific number of iteration - to give the user only 5 tries 
		for(int i = 0; i< 5; i++) {
			//asks user for username
			System.out.println("Enter your username: ");
			String user = kbd.next();
			
			//asks user for password
			System.out.println("Enter your password: ");
			String pass = kbd.next();
			
			//asks user for location (index in ArrayList)
			// to check if the entered username and password matches what has been stored
			System.out.println("Enter the location: ");
			int loc = kbd.nextInt();
			
			// tells the user if they enter a location (index) that goes beyond the ArrayList size
			if(loc > usernames.size()-1)
				System.out.println("This location does not exist. Try again.");
			else {
				// if else statement to determine which is incorrect: username, password, or both
				if(user.equals(usernames.get(loc)) && pass.compareTo(passwords.get(loc)) !=0)
					System.out.println("Incorrect password. Try again.");
				else if(user.compareTo(usernames.get(loc)) !=0 && pass.equals(passwords.get(loc)))
					System.out.println("Incorrect username. Try again.");
				else if(user.compareTo(usernames.get(loc))!= 0 && pass.compareTo(passwords.get(loc))!=0)
					System.out.println("Incorrect username and password. Try again.");
				else {
					// both username and password are correct, end of Stage 3
					System.out.println("Successful login!");
					success = true;
					break;
				}
			}
		}
		// after user exceeds 5 tries, end of Stage 3
		// flow of program returns to main method where the user can restart the process
		if(!success)
			System.out.println("Login failed.");
	}
	
}
