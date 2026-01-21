package com.ijse.gdse72.tictactoy.service;

import java.util.Arrays;

public class BoardImpl implements Board {

    public static Piece[][] pieces;
    BoardUi boardUi;

    public BoardImpl(BoardUi boardUi) {
        this.boardUi = boardUi;

        pieces = new Piece[3][3];
        initializeBoard(); //constuctor eka set karanakota empty set karanawa
    }

//    @Override
//    public BoardUi getBoardUi() {
//        return this.boardUi;
//    }

    @Override
    public void initializeBoard() { //bord ekata empty set garagaththa
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public boolean isLegalMove(int row, int col) { //butoon eka emty lesa thibe deyi balayi
        boolean isLegal = false;

        if(row < 0 || row >= 3 || col < 0 || col >= 3) {
            return isLegal;
        }
        isLegal =(pieces[row][col] == Piece.EMPTY);
            return isLegal;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        if(isLegalMove(row, col)) {
            pieces[row][col] = piece; //toch karana button eka real arra ekata set karanawa
        }

        printBoard();//tarminat eke game eka print karala balanna
        checkWinner();
        boardUi.notifyWinner();

    }

    @Override
    public Winner checkWinner() {
        // Check rows
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i][0] != Piece.EMPTY && pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2]) {

                return new Winner(pieces[i][0]);  // winin pice eka return karanawa new object ekak widihata
            }
        }

        // Check columns
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[0][i] != Piece.EMPTY && pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i]) {

                return new Winner(pieces[0][i]);
            }
        }

        // Check diagonals
        if (pieces[0][0] != Piece.EMPTY && pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2]) {

            return new Winner(pieces[0][0]);
        }

        if (pieces[0][2] != Piece.EMPTY && pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0]) {


            return new Winner(pieces[0][2]);
        }

        if (isBoardFull()) {

            return new Winner(Piece.EMPTY);// drow wela nam
        }
        return null;  // winner kenek hari drow wela hari nethnam.
    }

    // Check for a draw
    boolean isBoardFull(){
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                
                if (pieces[i][j] == Piece.EMPTY) {

                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
                System.out.println(Arrays.toString(pieces[i]));
        }
    }
}
