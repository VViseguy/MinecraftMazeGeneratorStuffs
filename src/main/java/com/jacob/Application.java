package com.jacob;

public class Application {


    public static void main(String [] args) {
        Maze myMaze = new Maze(5,5,3);
//        myMaze.loadFromFile("maze.txt");
//        myMaze.printTextFile("maze.txt");
        myMaze.printNbtFile("myMaze.nbt");
    }

}
