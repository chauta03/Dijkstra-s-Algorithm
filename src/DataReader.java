/**
 * DataReader: Takes vertices and their destinations and corresponding
 * weights to build an adjacency list.
 * 
 *      This class reads lines from a file. For each line, the class reads
 * every information for one vertex. If the line contains valid information,
 * the class starts analyzing. Else, it thows IOException. This program runs
 * with the assumption that provided file has valid information.
 * 
 * Usage:
 *      Create DataReader(filename) and use function readData()
 *  "filename" is an external file containing input to read.
 * 
 * Input: 
 *      The class reads its input from a file passed in as a parameter. A valid
 *  input consists of lines with the first integer is the starting vertex following
 *  by blocks which have destinations and corresponding weights:
 *      "1  2,1  3,1"
 *  1 is the starting vertex. The first block is 2,1 in which 2 is the destination
 *  and 1 is the weight. The second block is 3,1 in which 3 is the destination, and
 *  1 is the weight.
 * 
 * Output:
 *      For each line of valid input, this class adds a new vertex in adjacency list
 *  which is ArrayList<LinkedList<Edge>> - an ArrayList consists of serveral LinkedList
 *  with edges as elements.
 * 
 * Function:
 *      1. DataReader(filename): reads file and construct adjacency list
 *      2. buildList(String firstLine): takes each line and adds edges to specific 
 *                                      linkedlist of corresponding vertex.
 *      3. addEdge(LinkedList<Edge> a, int start, int destination, int weight):
 *         takes starting vertex, destination, and weight and adds the edge to 
 *          the correct linkedlist.
 * 
 * 
 *  Author: Chau Ta
 * 
 *  Creation Date: May 8th, 2022
 *          modified: May 9th, 2022 -- add comments
 *  
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.*;

public class DataReader {
    private String filename;
    private BufferedReader reader = null;

    /**
     * Constructs an object that can read edges from a file. The data is assumed
     * to be in the following fomat:
     * 
     *      Starting vertex     destination,weight      destination,weight
     * 
     * For example, the following would be a vaid single line:
     * <pre>
     *      1   3,1     4,2     2,3
     * </pre>
     * @param filename
     */
    public DataReader(String filename)
    {
        //Create an object that can read from the file.
        try
        {
            this.reader = new BufferedReader(new FileReader(filename));
        }
        catch (IOException e)
        {
            System.err.println("Cannot open file "+ filename+" for reading");
        }

        this.filename = filename;
    }

    /**
     * Reads edge information from the given hich must be in the format 
     *  specified in the documentation for the Data constructor.
     *  Precondition: the file must have been successfully opened for
     *                reading.
     *      @return ArrayList<LinkedList<Edge>> which is the adjacency list
     */
    public ArrayList<LinkedList<Edge>> readData()
    {
        ArrayList<LinkedList<Edge>> myArray = new ArrayList<LinkedList<Edge>>();

        try
        {
            // Read lines until hitting a null or blank line.
            String nextLine;
            while ( !((nextLine = this.reader.readLine()) == null ) &&
                    ! nextLine.trim().equals(""))
            {
                // The line read in contains the first line of information for
                // a vertex.
                Debug.println("\n" + "nextLine: " + nextLine);

                LinkedList<Edge> linkedlist = buildList(nextLine);

                myArray.add(linkedlist);
            }
        }
        catch (IOException e) 
        {
            System.err.println("Could not read file " + filename);
            return null;
        }

        return myArray;
    }

    /**
     * Build a LinkedList using initial information already read in (starting
     * vertex, destinations and weights).
     *      @param firstLine
     *      @return
     *      @throws IOException
     */
    public LinkedList<Edge>  buildList(String firstLine) throws IOException
    {
        //Create separate strings of all the blocks(without spaces)
        LinkedList<Edge> mylist = new LinkedList<Edge>();

        // Create separate strings of all blocks (without spaces).
        String[] blocks = firstLine.split("	");

        // Then concatenate all those strings together.
        for ( String eachBlock : blocks)
        {
            Debug.println("\n" + eachBlock);

            // The first block is the starting vertex.
            String start = blocks[0];
            
            // The following blocks are destinations and weights
            if ( eachBlock != blocks[0] )
            {
                String[] commas = eachBlock.split(",");
                addEdge(mylist, Integer.parseInt(start), Integer.parseInt(commas[0]), Integer.parseInt(commas[1]));
            }

        }
        
        return mylist;
    } 

    /**
     * Creates a new edge with destination and weight. Prints starting vertex, destination,
     * and weight of this edge.
     *      @param a corresponding LinkedList of start
     *      @param start for debugging purpose
     *      @param destination
     *      @param weight
     */
    static void addEdge(LinkedList<Edge> a, int start, int destination, int weight)
    {
        Edge edge = new Edge(destination, weight);
        a.add(edge);
        Debug.println("start: " + start);
        Debug.println("destination: " + destination);
        Debug.println("weight:" + weight);
    }
}
