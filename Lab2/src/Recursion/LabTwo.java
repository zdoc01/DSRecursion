package Recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class LabTwo {

	/**
	 * Entry point for the program. Reads in the file and stores the matrix. Runs the stored matrix through the
	 * determinant method to calculate the determinant.
	 * @param args
	 */
	public static void main(String[] args) {
	
		BufferedReader 	input;
		BufferedWriter 	output;
		LabTwo			lab;
		String			z;
		String []		stringArray;
		int [][] 		intArray;
    	int 			index;
    	int				dimension;
    	int				det;
		
		lab = new LabTwo();
		
		//  Check for two command line arguments.
        if (args.length != 2) {
            System.out.println("Usage:  java Recursion.LabTwo [input file]" +
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
        	try {
        		dimension = Integer.parseInt(z);
            	intArray = new int [dimension][dimension];
        	} catch (Exception exception) {
        		System.err.println(exception.toString());
                return;
        	}
        	
        	z = lab.readMatrix(input);
        	
        	// check for empty strings before parsing
        	if (z.length() == 0) {
        		continue;
        	}
        	
        	// parse the integers and place them into an integer array for processing.
        	for (int i = 0; i < intArray.length; i++) {
        		stringArray = z.split(" ");
        		index = 0;
        		
        		for (int j = 0; j < intArray[i].length; j++) {
        			
        			try {
        				intArray[i][j] = Integer.parseInt(stringArray[index]);
        			} catch (Exception exception) {
        				System.err.println(exception.toString());
                        return;
                	}
        			
        			index++;
        		}
        		
        		z = lab.readMatrix(input);
        	}
        	
        	// calculate the determinant
        	det = lab.Determinant(intArray);
        	
        	// send data to output
        	lab.writeOutput(intArray, det, output);
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
	 * Read in the strings of integers from the input file.
	 * @param input		the first string in the input file.
	 * @return The first string of integers.
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
	 * Write the results to the output file in an easy-to-read format.
	 * @param intArray	the original matrix passed into the program
	 * @param det		the value of the determinant for the matrix.
	 * @param output	the output file.
	 */
	private void writeOutput(int[][] intArray, int det, BufferedWriter output) {
		
		 try {
			 	output.write("The following matrix:");
			 	output.newLine();
			 	output.newLine();
			 	
			 	for (int i = 0; i < intArray.length; i++){
	        		for (int j = 0; j < intArray.length; j++){
	        			
	        			output.write(intArray[i][j] + " ");
	        		}
	        		
	        		output.newLine();
			 	}
			 	
			 	output.newLine();
			 	output.write("Has a determinant value of " + det + ".");
			 	output.newLine();
			 	output.newLine();
			 	output.write("---------------------------------------");
			 	output.newLine();
			 	output.newLine();
			 	
	        } catch (IOException ioException) {
	            System.err.println(ioException.toString());
	            System.exit(3);
	        }
	        
	        return;
	}
	
	/**
	 * Compute the minor of the matrix array passed into the application.
	 * @param intArray	The matrix passed into the method.
	 * @param j			The column value to be removed to compute the minor.
	 * @return The minor of the passed-in matrix.
	 */
	public int[][] Minor (int[][] intArray, int j) {
		
		int		p;
		int		q;
		int		matrixSize;
		int[][]	minor;
		
		matrixSize = intArray.length;
		minor = new int[matrixSize][matrixSize];
		
		// if the matrix is 1x1 - return the value in the matrix as the minor.
		// else calculate the minor of the matrix by creating a new array (the minor) which excludes
		// the first row and jth column from the original matrix.
		if (matrixSize == 1) {
			minor[0][0] = intArray[0][0];
			return minor;
		}
		else
			minor = new int[matrixSize-1][matrixSize-1];
			
			p = 0;
			for (int i = 0; i < matrixSize; i++) {
				
				if (i == 0)
					continue;
				
				q = 0;
				for (int z = 0; z < intArray[i].length; z++) {
					
					if (z == j)
						continue;
					
					minor[p][q] = intArray[i][z];

					q++;
				}
			
				p++;
			}
			
			System.out.println(Arrays.deepToString(minor));
			
			return minor;
	}
	
	/**
	 * Compute the determinant of the matrix passed into the application
	 * @param intArray	The original matrix passed into the program and each subsequent matrix created by the method 'minor'.
	 * @return The value of the determinant of the matrix.
	 */
	public int Determinant (int[][] intArray) {
		
		int[][]		minor;
		int			det;
		int			i;
		
		det = 0;
		i = 0;
		
		// if the matrix is 1x1 - return that value as the determinant.
		// else compute the sum of the products and return the determinant.
		for (int j = 0; j < intArray[i].length; j++) {
			
			if (intArray.length == 1) {
				return intArray[0][0];
			}
			else
				//System.out.println(intArray[i][j]);
				minor = this.Minor(intArray, j);
				det += (int) Math.pow(-1, (i + j))*intArray[i][j]*Determinant(minor);
			}

		return det;
	}
}
