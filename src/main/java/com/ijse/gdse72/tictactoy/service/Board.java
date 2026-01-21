package com.ijse.gdse72.tictactoy.service;

public interface Board {
 //   BoardUi getBoardUi();
    void initializeBoard();
    boolean isLegalMove(int row, int col);
    void updateMove(int row, int col, Piece piece);
    Winner checkWinner();
    void printBoard();

}
