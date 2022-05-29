/** 
 * This main file is used to run the program in Indexer, which
 * 
*/
public class App {
    public static void main(String[] args) throws Exception {
        Indexer a = new Indexer();

        System.out.println("The first (straightforward) implementation of solving the shortest path problem: " + "\n");
        a.dijkstra1();
        a.printResults();

        System.out.println("The second (heap) implementation of solving the shortest path problem (first ver): " + "\n");
        a.firstDijkstra2();
        a.printResults2first();

        System.out.println("The second (heap) implementation of solving the shortest path problem (second ver): " + "\n");
        a.secondDijkstra2();
        a.printResults2second();
    }
}
