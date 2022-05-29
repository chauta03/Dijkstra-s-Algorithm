/**
 * TestDataReader
 * 
 * Calls DataReader in a variety of ways to thoroughly test that function.
 * 
 * Usage: Run the file and enter the filename
 * 
 * Pre-condition: the file must be in the standard form as Test.txt and Test200vertices.txt
 * 
 * This function prints a series of edges in the adjacency list.
 *      - Total number of vertices
 *      - First destinations of every vertex
 *      - Print every destination of each vertex in order
 * 
 * Author: Chau Ta
 * 
 * Creation Date: May 8th, 2022
 *  modified: May 9th, 2022     Change the loop to print the list in a more readable way
 * 
 * 
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class TestDataReader {
    public static void main (String[] args) { 
        // Turn on debug file
        Debug.turnOn();
        
        // Prompt for the filename
        String input = ValidatedInputReader.getString("Enter a filename.", "");
        
        // Print out the filename
        Debug.println(input);
       
        // Create new DataReader object and read it and put it into a list
        DataReader a = new DataReader(input); 
        ArrayList<LinkedList<Edge>> Array = a.readData();
        
        // Print the number of vertices
        System.out.println("\n" + "numVer: " + Array.size() + "\n");

        // Print the first linkedlist (vertex 1)
        Debug.println("Array0" + Array.get(0) + "\n");

        //Loop through the array and print out output
        int vertex = 0;
        for (LinkedList linkedlist : Array)
        {
            Debug.println("LinkedList " + vertex + " of vertex " + (vertex+1) + "\n" );
            //Debug.println("linkedlist0" + linkedlist.get(0));
            for (int i = 0; i < linkedlist.size(); i++)
            {
                System.out.print(linkedlist.get(i) + "\n");
            }
            vertex++;
        }
    }
}
