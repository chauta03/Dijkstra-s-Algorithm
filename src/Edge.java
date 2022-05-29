/**
 * Class Edge for Edge objects. An Edge includes destination and the weight.
 * 
 * Functions:
 *      1. getDestination() returns the destination
 *      2. getWeight() returns the weight
 *      3. toString() returns the information about this edge including 
 *                    destination and weight
 * 
 * 
 * Author: Chau Ta
 * 
 *  Creation Date:  May 8th, 2022
 * 
 */
public class Edge
{
    private int destination;
    private int weight;
    
    /**
     * Creates an Edge. 
     * @param destination - the destination connecting the starting vertex 
     * @param weight - the weight starting vertex needs to go to the destination
     */
    public Edge(int destination, int weight) 
    {
        this.destination = destination;
        this.weight = weight;
    }
    
    /**
     * Returns the destination 
     * @return
     */
    public int getDestination() {
        return destination;
    }
    
    /**
     * Returns the weight of the edge
     * @return
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * Returns the string that includes information contained in this Edge (destination 
     * and weight). 
     * @return string
     */
    public String toString()
    {
        return ("destination: " + destination + "\n" + "weight: " + weight + "\n");
    }
}

