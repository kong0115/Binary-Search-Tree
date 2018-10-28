/*
Name of the Student: Siang Swee Kong
	Class: SE 3345
	Section: 003
	Semester: Fall 2018
	Project: 3
	Description: A program that implements binary search tree with lazy deletion to store a valid set of keys in the range[1-99]. 
	This program will read from an input file and perform the desired operations and write the results to an output file.  
	Operations that is supported by this binary search tree are insert, delete, findMin, findMax, contains, toString, height, and size.
*/

import java.io.*;
import java.util.Scanner;

/**A driver program that takes two command line arguments, the first argument is the input file name, and the second argument is the output file name.
* @author Siang Swee Kong
* @version 1.0.0
*/
public class LBSTDriver {
	public static void main(String[] args) {
		LazyBinarySearchTree t1 = new LazyBinarySearchTree(); //create an instance of LazyBinarySearchTree
		try {

			String inputFileName = args[0];		//store the input file name
			File file = new File(inputFileName);
			Scanner scan1 = new Scanner(file); 

			String outputFileName = args[1];   //store the output file name
			FileWriter fileWriter = new FileWriter(outputFileName);
			PrintWriter printWriter = new PrintWriter(fileWriter);

			String method = "";
			int num = 0;   
			while (scan1.hasNextLine())  //read until the end of the file
			{
				method = scan1.nextLine();	//read until the end of the line
				String [] str = method.split(":"); //split the string into two string when : is found in the string
				if(str[0].equalsIgnoreCase("Insert") && str.length == 2) //if the line that read from the file begins with "Insert:"
				{
					try {
						String temp = str[1]; //store the rest of the line
						boolean flag = true;
						for(int i = 0; i < temp.length();i++) //test if the rest of the line just contains 0 - 9
						{
							if(!Character.isDigit(temp.charAt(i))) 
							{
								flag = false; 
								break;
							}
						}
						if(flag == false && temp.charAt(0) != '-') //if it is not an integer
						{
							printWriter.printf("Error in Line: %s:%s%n", str[0],temp);
						}
						else //it is integer
						{
							num = Integer.valueOf(str[1]); //convert the string to int
							printWriter.printf("%b%n",t1.insert(num)); //insert the key
						}
					}
					catch(IllegalArgumentException ex) //catch the key that is outside of the range of 1 - 99
					{
						printWriter.printf("Error in insert: IllegalArgumentException raised. %s%n",ex.getMessage());
					}

				}
				else if(str[0].equalsIgnoreCase("Delete") && str.length == 2) //if the line that read from the file begins with "Delete:"
				{
					try {
						String temp = str[1]; //store the rest of the line
						boolean flag = true;
						for(int i = 0; i < temp.length();i++) //test if the rest of the line just contains 0 - 9
						{
							if(!Character.isDigit(temp.charAt(i)))
							{
								flag = false;
								break;
							}
						}
						if(flag == false && temp.charAt(0) != '-') //if it is not an integer
						{
							printWriter.printf("Error in Line: %s:%s%n", str[0],temp);
						}
						else //it is integer
						{
							num = Integer.valueOf(str[1]); //convert the string to int
							printWriter.printf("%b%n",t1.delete(num)); //delete the key
						}
					}
					catch(IllegalArgumentException ex) //catch the key that is outside of the range of 1 - 99
					{
						printWriter.printf("Error in delete: IllegalArgumentException raised. %s%n",ex.getMessage());
					}
				}
				else if(str[0].equalsIgnoreCase("PrintTree")) //if the line that read from the file begins with "PrintTree"
				{
					printWriter.printf("%s%n",t1.toString()); //print the tree with preorder traversal
				}

				else if(str[0].equalsIgnoreCase("Contains")&& str.length == 2) //if the line that read from the file begins with "Contains:"
				{
					try
					{
						String temp = str[1]; //store the rest of the line
						boolean flag = true;
						for(int i = 0; i < temp.length();i++)
						{
							if(!Character.isDigit(temp.charAt(i))) //test if the rest of the line just contains 0 - 9
							{
								flag = false;
								break;
							}
						}
						if(flag == false && temp.charAt(0) != '-') //if it is not an integer
						{
							printWriter.printf("Error in Line: %s:%s%n", str[0],temp);
						}
						else //it is integer
						{
							num = Integer.valueOf(str[1]); //convert the string to int
							printWriter.printf("%b%n",t1.contains(num)); //find the key
						}
					}
					catch(IllegalArgumentException ex) //catch the key that is outside of the range of 1 - 99
					{
						printWriter.printf("Error in contains: IllegalArgumentException raised. %s%n",ex.getMessage());
					}
				}

				else if(str[0].equalsIgnoreCase("FindMin")) //if the line that read from the file begins with "FindMin"
				{
					printWriter.printf("%d%n", t1.findMin()); //find the smallest non-deleted key in the tree
				}
				else if(str[0].equalsIgnoreCase("FindMax")) //if the line that read from the file begins with "FindMax"
				{
					printWriter.printf("%d%n", t1.findMax()); //find the largest non-deleted key in the tree
				}
				else if(str[0].equalsIgnoreCase("Height")) //if the line that read from the file begins with "Height"
				{
					printWriter.printf("%d%n",t1.height()); //return the height of the tree
				}
				else if(str[0].equalsIgnoreCase("Size"))  //if the line that read from the file begins with "Size"
				{
					printWriter.printf("%d%n",t1.size());	//return the number of elements in the tree including the deleted element
				}

				else //any invalid operations
					printWriter.printf("Error in Line: %s%n", str[0]);
			}

			scan1.close(); //close the input file
			printWriter.close(); //close the output file
		}
		catch (IOException ex){ //catch the exception when the file is not found
			System.out.println("File not found.");
		} 

		System.exit(0); //exit the program
	}
}
