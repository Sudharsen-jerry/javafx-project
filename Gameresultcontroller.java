package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class GameResultController {

    @FXML
    private Label congratsLabel;
    @FXML
    private Label winnerName;
    @FXML
    private Label gameOverLabel;
    @FXML
    private Button playAgainButton;
    @FXML
    private Button nextRoundButton;
    @FXML
    private Button exitGameButton;

    private Stage primaryStage;

    private int player1Wins = 0;
    private int player2Wins = 0;
    private int rounds = 1;

    public void initialize() {
        playAgainButton.setOnAction(this::handlePlayAgain);
        nextRoundButton.setOnAction(this::handleNextRound);
        exitGameButton.setOnAction(this::handleExitGame);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    void setGameResultText(final String gameResultText1, final String winner) {
        if (gameResultText1.equals("Draw")) {
            gameOverLabel.setText("Game Draw");
            congratsLabel.setVisible(false);
            winnerName.setVisible(false);
        } else {
            if (winner.equals("Player 1")) {
                player1Wins++;
            } else {
                player2Wins++;
            }
            if (rounds == 3) {
                if (player1Wins > player2Wins) {
                    winnerName.setText("Player 1 Wins the Game!");
                } else if (player1Wins < player2Wins) {
                    winnerName.setText("Player 2 Wins the Game!");
                } else {
                    winnerName.setText("It's a Draw!");
                }
            } else {
                winnerName.setText(winner + " Wins this Round!");
            }
            congratsLabel.setText("Congratulations!");
            gameOverLabel.setText("Game Over");
        }
    }

    private void handlePlayAgain(ActionEvent event) {
        resetGame();
    }

    private void handleNextRound(ActionEvent event) {
        if (rounds < 3) {
            rounds++;
            resetGame();
        }
    }

    private void handleExitGame(ActionEvent event) {
        if (primaryStage != null) {
            primaryStage.close();
        }
    }

    private void resetGame() {
        try {
            URL welcomeScreenResource = getClass().getResource("WelcomeScreen.fxml");
            Objects.requireNonNull(welcomeScreenResource, "Error loading game welcome screen");

            Stage stage = (Stage) playAgainButton.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(welcomeScreenResource)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
