/**
 * 
 * Indexer to implement Dijkstra's algorithms using straightforward method and heap to
 * solve the single-source shortest path problem in different directed graphs.
 * 
 * This program reads lines from a file and prompts the starting vertex. Then, it passes
 * the file to DataReader() to process the data. The assumption is provided file has valid
 * information.
 * 
 * Usage:
 *      Enter filename and starting vertex in the prompts.
 *      "filename" is an file containing the input to read and must be in correct format.
 *      If no file is provided, the program gives NullPointerException.
 *      
 * Input:
 *      The program reads its input from a file passed in as a parameter. Valid input 
 *      consists of lines of vertices following by connecting vertices and weights to go
 *      from starting vertices to connecting vertices. 
 * 
 * Output:
 *      This program has two implementation to solve one problem, which give same answer
 *      but different running time. The output would be distances from starting vertex to
 *      other vertices.
 * 
 *  Author: Chau Ta
 * 
 * Creation Date: May 8th, 2022
 *          modified: May 9th, 2022      edit comments and print debug to be more readable
 *      
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Indexer {
    private ArrayList<LinkedList<Edge>> graph;
    private int[] dist1;
    private int[] firstDist2;
    private int[] secondDist2;
    private boolean[] visited1;
    private boolean[] firstVisited2;
    private boolean[] secondVisited2;
    private int numVer;
    private int startVertex;

    private int countVer1;      // Total number of vertices visited in straightforward implementation
    private int countVer2;      // Total number of vertices visited in the second implementation (without adding all vertices)
    private int countVer3;      // Total number of vertices visited in the second implementation following the book (adding all vertices)
    private int countEdge1;     // Total number of edges visited in straightforward implementation
    private int countEdge2;     // Total number of edges visited in the second implementation (without adding all vertices)
    private int countEdge3;     // Total number of edges visited in the second implementation following the book (adding all vertices)
    
    public Indexer() {
        //Prompt user for filename
        String filename = ValidatedInputReader.getString("Enter a filename.", 
                            "Test.txt");

        // Prompt user for starting vertex
        startVertex = ValidatedInputReader.getInteger("Enter start vertex.",
                        1, Integer.MAX_VALUE, 1, "");

        // Read the file and save it in a graph holding my adjacency list
        DataReader a = new DataReader(filename);
        graph = a.readData();

        // The number of vertices
        numVer = graph.size();

        // Initialize boolean[] to check whether one vertex is visited or not
        // Initialize int[] to save the shortest distance from starting vertex
        // 1 for the straightforward implementation
        // 2 for the heap implementation: first is a different implementation without adding all vertices in the beginning
        //                                second is implementation following the book's pseudocode
        visited1 = new boolean[numVer];
        firstVisited2 = new boolean[numVer];
        secondVisited2 = new boolean[numVer];
        dist1 = new int[numVer];
        firstDist2 = new int[numVer];
        secondDist2 = new int[numVer];

        // Set every vertex to be unvisited and distances to infinity
        for (int i = 0; i < numVer; i++)
        {
            visited1[i] = false;
            firstVisited2[i] = false;
            secondVisited2[i] = false;
            dist1[i] = Integer.MAX_VALUE;
            firstDist2[i] = Integer.MAX_VALUE;
            secondDist2[i] = Integer.MAX_VALUE;
        }

        // distance of the starting vertex is 0
        dist1[startVertex-1] = 0;
        firstDist2[startVertex-1] = 0;
        secondDist2[startVertex-1] = 0;

        // Set total number of edges/vertices visited in three implementations to 0
        countEdge1 = 0;
        countEdge2 = 0;
        countEdge3 = 0;
        countVer1 = 0;
        countVer2 = 0;
        countVer3 = 0;

    }

    /**
     * Straightforward implementation of solving single-source shortest path problem
     * in different directed graphs.
     *      @return int[] with distance from starting vertex to other vertices in the graph
     */
    public int[] dijkstra1()
    {
        // Loop through the adjacency list to every vertex
        for (int i = 0; i < numVer; i++)
        {
            // Increment number of visited vertices
            countVer1++;

            // Find the minimum distance
            int minVertex = findMinVertex(dist1, visited1);

            // Mark that vertex to be visited
            visited1[minVertex] = true;

            // Number of vertices that vertex connects
            int numVerInList = graph.get(minVertex).size();

            // For each vertex connecting the given vertex, update the distance if 
            // find the shorter path
            for (int j = 0; j < numVerInList; j++)
            {
                int length = dist1[minVertex] + graph.get(minVertex).get(j).getWeight();
                if ( length < dist1[graph.get(minVertex).get(j).getDestination() - 1])
                {    
                    dist1[graph.get(minVertex).get(j).getDestination() - 1] = length;
                    countEdge1++;
                }
            }
        }

        return dist1;
    }

    /**
     * findMinVertex function: loop through every unvisited vertices to find the one with
     * minimum distance.
     *      @param dist
     *      @param visited
     *      @return vertex with current minimum distance from the starting vertex
     */
    private int findMinVertex(int[] dist, boolean[] visited) 
    {
        // Initialize the shortest path to infinity
        // the vertex with shortest path to -1
        int minDist = Integer.MAX_VALUE;
        int minVertex = -1;

        //Loop through the array to find minimum distance
        for (int i = 0; i < numVer; i++)
        {
            // if the vertex is unvisited and less than current minimum distance, update
            // minVertex
            if ( !visited[i] && dist[i] < minDist )
            {
                minDist = dist[i];
                minVertex = i;
            } 
        }
        return minVertex;
    }

    /**
     * heap version of solving single-source shortest path problem in different 
     * directed graphs using priority queue.
     * 
     *      @return int[] with distance from starting vertex to other vertices in the graph
     */
    public int[] firstDijkstra2()
    {
        // Initialize a priority queue with vertex with less key comes first
        PriorityQueue<Edge> myQueue = new PriorityQueue<Edge>(numVer, (edge1, edge2) -> edge1.getWeight() - edge2.getWeight());
        
        // 1st implementation without adding all vertices from the beginning.
        myQueue.add( new Edge(startVertex - 1, firstDist2[startVertex - 1]) );

         while ( !myQueue.isEmpty() )
        {
            Edge cur = myQueue.poll();
            
            countVer2++;

            for ( Edge edge : graph.get(cur.getDestination()) )
            {
                if ( firstDist2[cur.getDestination()] + edge.getWeight() < firstDist2[edge.getDestination() - 1])
                {    
                    firstDist2[edge.getDestination() - 1] = firstDist2[cur.getDestination()] + edge.getWeight();
                
                    myQueue.add( new Edge(edge.getDestination() - 1, firstDist2[edge.getDestination() - 1]));
                    countEdge2++;
                }
            }
        } 

        return firstDist2;
    }
    
    /**
     * heap version of solving single-source shortest path problem in different 
     * directed graphs using priority queue.
     * 
     *      @return int[] with distance from starting vertex to other vertices in the graph
     */
    public int[] secondDijkstra2()
    {
        // Initialize a priority queue with vertex with less key comes first
        PriorityQueue<Edge> myQueue = new PriorityQueue<Edge>(numVer, (edge1, edge2) -> edge1.getWeight() - edge2.getWeight());
        
        /* 2nd implementation with adding all vertices form the beginning 
         following the instructions in the book*/
        // Add all vertices in the queue with keys are min distance from starting vertex
        for ( int i = 0; i < numVer; i++)
        {
            myQueue.add( new Edge( i, secondDist2[i]));
        }

        // Until the queue is not empty
        while ( !myQueue.isEmpty() )
        {
            countVer3++;

            // poll the first vertex with least key (min dist)
            Edge cur = myQueue.poll();
            
            // mark that vertex as visited
            secondVisited2[cur.getDestination()] = true;

            // for each vertices that vertex connects
            for ( Edge edge : graph.get(cur.getDestination()) )
            {
                // update the distance of connecting vertex if the path through current vertex is smaller
                if ( secondDist2[cur.getDestination()] + edge.getWeight() < secondDist2[edge.getDestination() - 1])
                {    
                    secondDist2[edge.getDestination() - 1] = secondDist2[cur.getDestination()] + edge.getWeight();
                    
                    // add connecting vertex with new key to the queue
                    myQueue.add( new Edge(edge.getDestination() - 1, secondDist2[edge.getDestination() - 1]));
                    
                    countEdge3++;
                }
            }
        }

        return secondDist2;
    }
    
    /**
     * Print all distances from the first implementation
     */
    public void printResults()
    {
        System.out.println("Total number of vertices visited: " + countVer1);
        System.out.println("Total number of edges visited: " + countEdge1);
        for ( int i = 0; i < numVer; i++ )
        {
            System.out.println("dist" + i + ": " + dist1[i]);
        }
    }

    /**
     * Print all distances from the second implementation (heap) (first ver)
     */
    public void printResults2first()
    {
        System.out.println("Total number of vertices visited: " + countVer2);
        System.out.println("Total number of edges visited: " + countEdge2);
        for ( int i = 0; i < numVer; i++ )
        {
            System.out.print("dist" + i + ": " + firstDist2[i] + "\n");
        }
    }

    /**
     * Print all distances from the second implementation (heap) (second ver)
     */
    public void printResults2second()
    {
        System.out.println("Total number of vertices visited: " + countVer3);
        System.out.println("Total number of edges visited: " + countEdge3);
        for ( int i = 0; i < numVer; i++ )
        {
            System.out.print("dist" + i + ": " + secondDist2[i] + "\n");
        }
    }
}
