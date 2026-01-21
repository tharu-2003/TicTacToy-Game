package com.ijse.gdse72.tictactoy.service;

public interface BoardUi {                           //UI ekayi System ekayi connect karayi

    void update(int row, int col,boolean isHuman);   // UI ekata X / O set karanawa
    void notifyWinner();                             // userta winner kuda kiyala pennanawa

}
