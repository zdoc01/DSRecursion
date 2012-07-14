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
		String []		stringArray;
		int [][] 		intArray;
		int [][]		minor;
    	int 			index;
    	int				dimension;
		
		lab = new LabTwo();
		
		//  Check for two command line arguments.
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
        
        // process the input by setting the dimension of the matrix and storing the matrix in a 2D array.
        z = lab.readMatrix(input);
        index = 0;
        while (z != null) {
        	
        	// set the first line of input as the dimension of the matrix then process the matrix
        	dimension = Integer.parseInt(z);
        	intArray = new int [dimension][dimension];
        	z = lab.readMatrix(input);
        	
        	// check for empty strings before parsing
        	if (z.length() == 0) {
        		continue;
        	}
        	
        	// parse the integers and place them into an integer array for processing.
        	for (int i = 0; i < intArray.length; i++) {
        		stringArray = z.split(" ");
        		index = 0;
        		
        		//System.out.println("eye = " + i);
        		//System.out.println("LENGTH = " + intArray[i].length);
        		
        		for (int j = 0; j < intArray[i].length; j++) {
        			intArray[i][j] = Integer.parseInt(stringArray[index]);
        			
        			//System.out.println(stringArray[j]);
        			//System.out.println("intArray " + intArray[i][j]);
        			
        			index++;
        		}
        		
        		z = lab.readMatrix(input);
        	}
        	
        	//System.out.print(Arrays.deepToString(intArray));
        	minor = lab.minor(intArray);
        	lab.writeOutput(intArray, output);
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
	 * Write the results to the output file.
	 * @param w
	 * @param output	the output file.
	 */
	private void writeOutput(int[][] intArray, BufferedWriter output) {
		
		 try {
			 	for (int i = 0; i < intArray.length; i++){
	        		for (int j = 0; j < intArray.length; j++){
	        			
	        			output.write(intArray[i][j] + " ");
	        		}
	        		
	        		output.newLine();
			 	}
	        } catch (IOException ioException) {
	            System.err.println(ioException.toString());
	            System.exit(3);
	        }
	        
	        return;
	}
	
	public int[][] minor (int[][] intArray) {
		
		int		p;
		int		q;
		int		matrixSize;
		int[][]	minorArray;
		
		System.out.println("intArray = " + Arrays.deepToString(intArray));
		
		matrixSize = intArray.length;
		minorArray = new int[matrixSize][matrixSize];
		
		// if the matrix is 1x1 - return the value in the matrix as the minor.
		// else calculate the minor of the matrix by excluding the first row and column from the original matrix.
		if (matrixSize == 1) {
			minorArray[0][0] = intArray[0][0];
			System.out.println("minorArray = " + Arrays.deepToString(minorArray));
			return minorArray;
		}
		else
			minorArray = new int[matrixSize-1][matrixSize-1];
			
			p = 0;
			for (int i = 0; i < matrixSize; i++) {
				
				if (i == 0)
					continue;
				
				q = 0;
				for (int j = 0; j < intArray[i].length; j++) {
					
					if (j == 0)
						continue;
					
					minorArray[p][q] = intArray[i][j];
					q++;
				}
				
				p++;
			}
			
			System.out.println("minorArray = " + Arrays.deepToString(minorArray));
			return minorArray;
	}
}
