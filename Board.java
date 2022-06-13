package com.dominatingKnights;

public class Board {
    private final int N = 8;
    private Cell[][] board;


    public Board() {
        board = new Cell[N][N];
        for (int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                Cell cell = new Cell();
                board[i][j] = cell;
            }
        }
        board[2][2].setStatus(1);
        board[2][2].setAttacked(true);
    }

    public boolean solveQuarter(int knightNum){
        if(knightNum >= 2) {
            if (isQuarterAttacked()) {
                return true;
            }else{
                return false;
            }
        }
        for(int i = 1; i < N/2; i++){
            for(int j = 1; j < N/2; j++){
                if(j == 2 && i == 2){
                    continue;
                }
                if(canBePlaced(i, j)){
                    placeKnight(i, j);
                    if (solveQuarter(knightNum + 1)) {
                        return true;
                    }
                    removeKnight(i, j);
                }
            }
        }
        return false;
    }

    private boolean canBePlaced(int row, int col){
        if (board[row][col].getStatus() == 1) {
            return false;
        }
        if(row == 2 && col != 2){
            if(col == 1 && board[row][col+2].getStatus() == 1 ){
                return false;
            }
            if(col == 3 && board[row][col-2].getStatus() == 1 ){
                return false;
            }
        }

        if(col == 2 && row != 2){
            if (row == 1 && board[row+2][col].getStatus() == 1){
                return false;
            }
            if (row == 3 && board[row-2][col].getStatus() == 1){
                return false;
            }
        }

        return true;
    }

    private void removeKnight(int x, int y) {
        board[x][y].setStatus(0);

        if(checkCell(x, y)){
            board[x][y].setAttacked(false);
        }

        if (y + 2 < N && x + 1 < N && checkCell(x + 1, y + 2)) {
            board[x + 1][y + 2].setAttacked(false);
        }

        if (y + 2 < N && x - 1 >= 0 && checkCell(x - 1, y + 2)) {
            board[x - 1][y + 2].setAttacked(false);
        }

        if (y + 1 < N && x + 2 < N && checkCell(x + 2, y + 1)) {
            board[x + 2][y + 1].setAttacked(false);
        }

        if (y + 1 < N && x - 2 >= 0 && checkCell(x - 2, y + 1)) {
            board[x - 2][y + 1].setAttacked(false);
        }

        if (y - 2 >= 0 && x + 1 < N && checkCell(x + 1, y - 2)) {
            board[x + 1][y - 2].setAttacked(false);
        }

        if (y - 2 >= 0 && x - 1 >= 0 && checkCell(x - 1, y - 2)) {
            board[x - 1][y - 2].setAttacked(false);
        }
        if (y - 1 >= 0 && x - 2 >= 0 && checkCell(x - 2 , y - 1)) {
            board[x - 2][y - 1].setAttacked(false);
        }

        if (y - 1 >= 0 && x + 2 < N && checkCell(x + 2, y - 1)) {
            board[x + 2][y - 1].setAttacked(false);
        }

    }

    private void placeKnight(int x, int y) {
        board[x][y].setStatus(1);
        board[x][y].setAttacked(true);

        if (y + 2 < N && x + 1 < N) {
            board[x + 1][y + 2].setAttacked(true);
        }

        if (y + 2 < N && x - 1 >= 0) {
            board[x - 1][y + 2].setAttacked(true);
        }

        if (y + 1 < N && x + 2 < N) {
            board[x + 2][y + 1].setAttacked(true);
        }

        if (y + 1 < N && x - 2 >= 0) {
            board[x - 2][y + 1].setAttacked(true);
        }

        if (y - 2 >= 0 && x + 1 < N) {
            board[x + 1][y - 2].setAttacked(true);
        }

        if (y - 2 >= 0 && x - 1 >= 0) {
            board[x - 1][y - 2].setAttacked(true);
        }
        if (y - 1 >= 0 && x - 2 >= 0) {
            board[x - 2][y - 1].setAttacked(true);
        }

        if (y - 1 >= 0 && x + 2 < N) {
            board[x + 2][y - 1].setAttacked(true);
        }

    }

    private boolean isQuarterAttacked() {
        for (int i = 0; i < N / 2; i++) {
            if (!board[i][0].isAttacked()) {
                return false;
            }
        }

        for (int j = 0; j < N / 2; j++) {
            if (!board[0][j].isAttacked()) {
                return false;
            }
        }

        if (!board[1][1].isAttacked() || !board[3][3].isAttacked()) {
            return false;
        }
        return true;
    }


    //turning by 90 degrees
    public void finishFillingBoard() {
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                board[i][j + 4] = board[4 - j - 1][i];
                board[i + 4][j + 4] = board[4 - i - 1][4 - j - 1];
                board[i + 4][j] = board[j][4 - i - 1];
            }

        }
    }

    private boolean checkCell(int x, int y) {
        if ((y + 2 < N && x + 1 < N) && board[x + 1][y + 2].getStatus() == 1 ||
                (y + 2 < N && x - 1 >= 0) && board[x - 1][y + 2].getStatus() == 1 ||
                (y + 1 < N && x + 2 < N) && board[x + 2][y + 1].getStatus() == 1 ||
                (y + 1 < N && x - 2 >= 0) && board[x - 2][y + 1].getStatus() == 1 ||
                (y - 2 >= 0 && x + 1 < N) && board[x + 1][y - 2].getStatus() == 1 ||
                (y - 2 >= 0 && x - 1 >= 0) && board[x - 1][y - 2].getStatus() == 1 ||
                (y - 1 >= 0 && x + 2 < N) && board[x + 2][y - 1].getStatus() == 1) {
            return false;
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].getStatus() == 1) {
                    System.out.print(" K");
                } else {
                    System.out.print(" " + board[i][j].getStatus());
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}

