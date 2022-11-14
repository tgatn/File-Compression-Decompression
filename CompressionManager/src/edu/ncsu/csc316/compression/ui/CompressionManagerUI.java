package edu.ncsu.csc316.compression.ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.compression.manager.ReportManager;

/**
 * CompressionManager UI that users will interact with
 * 
 * @author Tung Tran
 */
public class CompressionManagerUI {

	

	/**
	 * Main method that will run on execution
	 * 
	 * @param args is arguments in command line
	 */
	public static void main(String[] args) {
		System.out.println(
				"Please type in the address of the file you would like to compress or decompress. For this program, press enter to enter your input, and input Q to quit.");
		Scanner scnr = new Scanner(System.in);
		
		String file = scnr.next();
		if (file.equals("Q")) {
			System.exit(0);
		}
		
		ReportManager manager = null;
		
		boolean valid = false;
		
		while (!valid) {
			try {
				if (file.equals("Q")) {
					System.exit(0);
				}
				manager = new ReportManager(file);
				valid = true;
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred when processing " + file + ". Please type in the address of the file you would like to compress or decompress. For this program, press enter to enter your input, and input Q to quit.");
				file = scnr.next();
			}
		}
		
		String cmd = "";
		do {
			System.out.println("Would you like to compress or decompress " + file + "? Type C to compress or D to decompress, and press enter.");
			cmd = scnr.next();
			if (cmd.equals("C")) {
				System.out.println(manager.compress());
				scnr.close();
				System.exit(0);
			} else if (cmd.equals("D")) {
				System.out.println(manager.decompress());
				scnr.close();
				System.exit(0);
			}
		} while (!cmd.equals("C") && !cmd.equals("D"));
		
		
		scnr.close();
		
		System.out.println("Done");
	}
}
