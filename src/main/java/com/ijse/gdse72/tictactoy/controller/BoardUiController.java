package com.ijse.gdse72.tictactoy.controller;

import com.ijse.gdse72.tictactoy.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardUiController implements Initializable, BoardUi {

    public Board board = new BoardImpl(this);

    public static Button[][] buttons = new Button[3][3];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // ui eke thina button tika array ekakata set kara gaththa
        buttons[0][0] = btn00;
        buttons[0][1] = btn01;
        buttons[0][2] = btn02;
        buttons[1][0] = btn10;
        buttons[1][1] = btn11;
        buttons[1][2] = btn12;
        buttons[2][0] = btn20;
        buttons[2][1] = btn21;
        buttons[2][2] = btn22;
    }


    @FXML
    private Button btn00;

    @FXML
    private Button btn01;

    @FXML
    private Button btn02;

    @FXML
    private Button btn10;

    @FXML
    private Button btn11;

    @FXML
    private Button btn12;

    @FXML
    private Button btn20;

    @FXML
    private Button btn21;

    @FXML
    private Button btn22;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label lblDefeat;

    @FXML
    private Label lblError;

    @FXML
    private Label lblDrow;

    @FXML
    private Label lblWin;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    void btnOnAction(ActionEvent event) {
        Button clickedBtn = (Button) event.getSource(); //toch karana button eke location eka aragena Button type ekata cast kara gaththa

        int row = -1;
        int col = -1;

        if(clickedBtn.getText().isEmpty()){ //click karapu button eka emptyda kiyala beluwa

            System.out.println("Valid Move");
            lblError.setText(" ");

            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {

                    if (buttons[i][j] == clickedBtn) { //click karapu button eke row ekayi column ekayi hoya gannawa
                        row = i;
                        col = j;
                    }
                }
            }

            HumanPlayer humanPlayer = new HumanPlayer(board);    //human class eka access karanna ona nisa object ekak hada gaththa
            humanPlayer.move(row,col);                           //human class eke move methode ekata call kara
            update(row, col, true);


            AiPlayer aiPlayer = new AiPlayer(board, this);   //Ai class eka access karanna ona nisa object ekak hada gaththa
            aiPlayer.move(row,col);

//          update(row, col, false);
        }else {
            System.out.println("In Valid Button..!");
            lblError.setText("In Valid Button..!");
        }
    }

    @Override
    public void update(int row, int col, boolean isHuman) {
        if(isHuman){
            buttons[row][col].setText("X");
        }else{
            buttons[row][col].setText("O");
        }
       // buttons[row][col].setDisable(true);
    }

    @Override
    public void notifyWinner() {

        Winner winner = board.checkWinner();

        String win;

        if (winner != null) {

            win = String.valueOf(winner.winningPiece);

            if ( win == "X"){

                System.out.println("Human win");
                lblError.setText("You Won");
                setDisableBord(true);

            } else if ( win == "O") {

                System.out.println("Ai win");
                lblError.setText("You Lost");
                setDisableBord(true);

            }else {

                System.out.println("Draw");
                lblError.setText("Draw..!");
                setDisableBord(true);
            }
        } else {
            System.out.println("No winner yet.");
        }
    }

    void setDisableBord(boolean isDis){
        if(isDis){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setDisable(true);
                }
            }

        }else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setDisable(false);
                }
            }
        }
    }
    @FXML
    void btnRetryOnAction(ActionEvent event) {
        board.initializeBoard();
        for (Button[] row : buttons ) {
            for (Button button : row) {
                button.setText("");

            }
        }
        setDisableBord(false);
        lblError.setText("");
    }

}
