package Recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class LabTwo {

	/**
	 * Entry point for the program. Reads in the file and stores the matrix. Runs the matrix through the
	 * determinant function to calculate the determinant.
	 * @param args
	 */
	public static void main(String[] args) {
	
		BufferedReader 	input;
		BufferedWriter 	output;
		LabTwo			lab;
		String			z;
		int [][] 		intArray;
		String []		stringArray;
    	int 			maxItems;
    	int 			index;
		
		lab = new LabTwo();
		
		//  Check that there are two command line arguments.
        if (args.length != 2) {
            System.out.println("Usage:  java LabOne [input file]" +
                " [output file]");
            System.exit(1);
        }
        
        //  Open the input and output files.
        try {
            input = new BufferedReader(new FileReader(args[0]));
            output = new BufferedWriter(new FileWriter(args[1]));
        } catch (Exception exception) {
            System.err.println(exception.toString());
            return;
        }
        
        // testing the input and output files
        z = lab.readMatrix(input);
        index = 0;
        while (z != null) {
        	
        	stringArray = z.split(" ");
            maxItems = stringArray.length;
        	//intArray = new int [maxItems];
        	intArray = new int [2][2];
            
        	// check for empty strings before parsing
        	if (z.length() == 0) {
        		continue;
        	}
        	
        	// parse the integers from the string array and place them into an integer array for processing
        	for (int i = 0; i < maxItems; i++) {
        		
        		intArray[index++] = Integer.parseInt(stringArray[i]);
        	}
        	
        	System.out.println(Arrays.toString(intArray));
        	//lab.writeOutput(intArray, output);
        	
        	z = lab.readMatrix(input);
        	index = 0;
        }
        
        // Close the input and output files and return to OS.
        try {
            input.close();
            output.close();
        } catch (Exception exception) {
            System.err.println(exception.toString());
        }
        return;
	}
	
	/**
	 * Read in the first string of characters from the input file.
	 * @param input		the first string in the input file.
	 * @return The first string of characters.
	 */
	private String readMatrix(BufferedReader input) {
		
		String z = "";
		
        try {
             z = input.readLine();
        } catch (IOException ioException) {
            System.err.println(ioException.toString());
            System.exit(2);
        }
        
        return z;
	}
	
	/**
	 * Write the output results to the output file.
	 * @param w
	 * @param output	the output file.
	 */
	private void writeOutput(int[] array, BufferedWriter output) {
		
		 try {
			 	output.write(Arrays.toString(array));
			 	output.newLine();
	        } catch (IOException ioException) {
	            System.err.println(ioException.toString());
	            System.exit(3);
	        }
	        
	        return;
	}
}
