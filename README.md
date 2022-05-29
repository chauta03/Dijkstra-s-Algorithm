## Project title
Implementations of Dijkstra's Algorithm

## Getting Started

Welcome to the project: Implementations of Dijkstra's Algorithm. Here is a guideline to help you get started to use the program.

## Purpose of project
The purpose of the project is to use Dijkstra's Algorithm to solve the single-source shortest path problem in different directed
graphs. 
There are two implementations included in this program:
    1. The straightforward technique: Go through each vertex and check each path which require O(mn) time, where m is the number 
                                      of edges and n is the number of vertices.
    2. The heap technique: illustrate the interplay between the design of algorithms and design of data structures to solve the
                           same probele with O((m+n)logn) running time.

## Version
1st version: May 9th,2022

## Folder Structure

The workspace contains two folders, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

## How to start this project
- Include a valid input file with lines of vertices following by connecting vertices and their corresponding weight.
In the following format:
        1   3,2     4,1
        2   5,1     8,3
        ...
where 1 is the starting vertex in the first line, 3 connects to 1 with the weight of 2 and 4 connects to 1 with the
weight of 1.

- There are to provided input file:
    + Test.txt: with 8 vertices
    + Test200vertices.txt: with 200 vertices

- Run App.class and provide filename and starting vertex as it prompts.
  
## Author
    Chau Ta
    Special thanks to Alyce Brandy and Joel Booth for ValidatedInputReader.java
                      Alyce Brandy for Debug.java

## User instructions
- Run App.java, enter filename and starting vertex as it prompts. The program will run both implementations.
- Open App.java and alter the functions
    + Comment out the 2 first functions (dijkstra1 and printResult) to only implement the heap implementation.
    + Comment out the 2 last functions (dijkstra2 and printResult2) to only implement the straightforward implementation.

- Open Indexer.java, there are two implementations for the heap method
    + The original implementation is the second implementation (adding all vertices in the beginning).
    + The first implementation is commented out of the program (without adding all vertices to the heap in the beginning).
If want to use, take out comment signs and comment out the other implementation.

- System.nanoTime() is used in App.java to calculate running time of two functions.
