package com.ijse.gdse72.tictactoy.service;

public class HumanPlayer extends Player {

    public HumanPlayer(Board board) {
        super(board);
    }

    @Override
    public void move(int row, int col) {

            board.updateMove(row, col, Piece.X);

    }
}
