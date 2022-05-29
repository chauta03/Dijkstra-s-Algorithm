// Class: GraphReader
//
// Author: Pamela Cutter 
// Created on May 10, 2022

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


/**
 * This class contains code to read graph edge data in from
 * a file.  It must be put into an ArrayList as it is read.
 * 
 * @author Pamela Cutter
 * @version May 10, 2022
 */
public class GraphReader 
{
    private String filename;
    private BufferedReader reader = null;

    /** Constructs an object that can read graph edge information
     *  from a file.  The data is assumed to be in the
     *  following format:
     *
     *      SourceVertexNumber  some number of DestinationVertex, EdgeWeight pairs    
     *    
     *
     *   For example   the following would be a valid line:
     *   <pre>
     *      1	2,1	8,2
     *   </pre>
     *      @param filename  the name of the file to open for reading
     */
    public GraphReader(String filename) 
    {
        // Create an object that can read from the file.
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

    /** Reads edge information from the given file, which must be
     *  in the format specified in the documentation for the GraphReader
     *  constructor.
     *  Precondition: the file must have been successfully opened for
     *                reading.
     *      @return List<List<edge>>  (the adjacency list for this graph)
     */
    public List<List<Edge>> readData()  // *** WHAT SHOULD RETURN TYPE BE? ***
    {
      
        List<List<Edge>> adj_list = new ArrayList<List<Edge>>();
        try 
        {
            // Read lines until hitting a null or blank line.
            String nextLine;
            while ( ! ((nextLine = this.reader.readLine()) == null) && 
                    ! nextLine.trim().equals(""))
            {
                // The line read in contains the edge information for 1 vertex.
                // The list of edges that this vertex connects to are added
                // to the adjacency list.  
                adj_list.add(readLine(nextLine)); 
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Could not read file " + filename);
            return null;
        }

        return  adj_list;
    }
    
    /** Builds a list of edges from information read in on one line from
     * the file.  
     *      @param firstLine    a line of info for a vertex
     *      @return List<Edge> (list of edges that are connected to this vertex
     *      @throws IOException
     */
    private List<Edge> readLine(String firstLine) throws IOException
    {
        List<Edge> edges = new ArrayList<Edge>();
       
        // The data on the line is separated by tabs
        String[] blocks = firstLine.split("\t");
      
        // The data files have vertex indices starting at 1.  Shift all 
        // vertex indices down by 1.
        
        int srcVertex = Integer.parseInt(blocks[0])-1;
        for (int j = 1; j<blocks.length; j++)
        {
            String[] ints = blocks[j].split(",");
            int destVertex = Integer.parseInt(ints[0])-1;
            int edgeWt = Integer.parseInt(ints[1]);
            Edge e = new Edge(srcVertex, destVertex, edgeWt);
            edges.add(e);
        }

        return edges;
    }

}