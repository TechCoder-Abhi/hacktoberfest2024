import java.io.*;
import java.util.*;

// This class represents a directed graph using adjacency list representation
class Graph {
    private int numVertices; // Number of vertices in the graph
    private LinkedList<Integer>[] adjacencyList; // Array of lists for Adjacency List Representation

    // Constructor
    @SuppressWarnings("unchecked") 
    Graph(int vertices) {
        numVertices = vertices;
        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i)
            adjacencyList[i] = new LinkedList<>();
    }

    // Function to add an edge into the graph
    void addEdge(int source, int destination) {
        // Add destination to source's list.
        adjacencyList[source].add(destination);
    }

    // A function used by DFS to explore nodes
    void exploreDFS(int currentVertex, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[currentVertex] = true;
        System.out.print(currentVertex + " ");

        // Recur for all the vertices adjacent to this vertex
        for (int neighbor : adjacencyList[currentVertex]) {
            if (!visited[neighbor]) {
                exploreDFS(neighbor, visited);
            }
        }
    }

    // The function to perform DFS traversal.
    void performDFS(int startVertex) {
        boolean[] visited = new boolean[numVertices]; // Mark all vertices as not visited
        System.out.print("DFS Traversal starting from vertex " + startVertex + ": ");
        exploreDFS(startVertex, visited);
        System.out.println(); // New line for better readability
    }

    // Method to print the adjacency list of the graph
    void printGraph() {
        System.out.println("Adjacency List Representation of the Graph:");
        for (int i = 0; i < numVertices; i++) {
            System.out.print("Vertex " + i + ": ");
            for (Integer neighbor : adjacencyList[i]) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    // Driver Code
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        Graph graph = new Graph(vertices);

        System.out.println("Enter the edges (format: source destination). Type 'exit' to stop.");
        
        // Input loop for edges
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                break; // Exit the loop on user command
            }

            String[] parts = input.split(" ");
            if (parts.length == 2) {
                try {
                    int source = Integer.parseInt(parts[0]);
                    int destination = Integer.parseInt(parts[1]);

                    // Add edge to the graph
                    graph.addEdge(source, destination);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter integers for source and destination.");
                }
            } else {
                System.out.println("Please enter two integers for the edge.");
            }
        }

        // Print the graph
        graph.printGraph();

        // Perform DFS traversal from a user-defined starting vertex
        System.out.print("Enter the starting vertex for DFS: ");
        int startVertex = scanner.nextInt();
        graph.performDFS(startVertex);

        scanner.close(); // Close the scanner
    }
}
