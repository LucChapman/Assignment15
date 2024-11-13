import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application{
    
    private String currentPlayer = "X"; 
    private Button[][] board = new Button[5][5]; 
    private String[][] gameBoard = new String[5][5]; 

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Button btn = new Button();
                btn.setPrefSize(100, 100);
                btn.setFont(new Font(24));
        
                final int row = i;
                final int col = j;
                btn.setOnAction(e -> handleButtonClick(row, col, btn));
                board[i][j] = btn;
                gameBoard[i][j] = "";
                grid.add(btn, j, i);
            }
        }

        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setTitle("5x5 Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(int i, int j, Button btn) {
  
        if (gameBoard[i][j].equals("")) {
            gameBoard[i][j] = currentPlayer;
            btn.setText(currentPlayer);

            if (checkWinner()) {
                showWinnerAlert();
            } else {
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }
        }
    }

    private boolean checkWinner() {
        for (int i = 0; i < 5; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return true;
            }
        }
        return checkDiagonal1() || checkDiagonal2();
    }

    private boolean checkRow(int row) {
        String firstCell = gameBoard[row][0];
        if (!firstCell.equals("") && firstCell.equals(gameBoard[row][1]) && firstCell.equals(gameBoard[row][2]) &&
            firstCell.equals(gameBoard[row][3]) && firstCell.equals(gameBoard[row][4])) {
            return true;
        }
        return false;
    }

    private boolean checkColumn(int col) {
        String firstCell = gameBoard[0][col];
        if (!firstCell.equals("") && firstCell.equals(gameBoard[1][col]) && firstCell.equals(gameBoard[2][col]) &&
            firstCell.equals(gameBoard[3][col]) && firstCell.equals(gameBoard[4][col])) {
            return true;
        }
        return false;
    }

    private boolean checkDiagonal1() {
        String firstCell = gameBoard[0][0];
        if (!firstCell.equals("") && firstCell.equals(gameBoard[1][1]) && firstCell.equals(gameBoard[2][2]) &&
            firstCell.equals(gameBoard[3][3]) && firstCell.equals(gameBoard[4][4])) {
            return true;
        }
        return false;
    }

    private boolean checkDiagonal2() {
        String firstCell = gameBoard[0][4];
        if (!firstCell.equals("") && firstCell.equals(gameBoard[1][3]) && firstCell.equals(gameBoard[2][2]) &&
            firstCell.equals(gameBoard[3][1]) && firstCell.equals(gameBoard[4][0])) {
            return true;
        }
        return false;
    }

    private void showWinnerAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(currentPlayer + " wins!");
        alert.setContentText("Congratulations, " + currentPlayer + "!");
        alert.showAndWait();

        resetGame();
    }

    private void resetGame() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                gameBoard[i][j] = "";
                board[i][j].setText("");
            }
        }
        currentPlayer = "X";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
