package com.dominatingKnights;

import com.dominatingKnights.Board;

//  LSP: 2110576
//  2 uzd 12 dominating knights 8x8 board

public class KnightsProblem {

    public static void main(String[] args) {
        Board board = new Board();
        if(!board.solveQuarter(0)){
            System.out.println("Solution could not be found");
        }else{
            board.finishFillingBoard();
            board.printBoard();
        }
    }
}
