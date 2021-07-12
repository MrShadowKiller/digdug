package ir.ac.kntu.logic;

import ir.ac.kntu.JavaFxApplication;
import ir.ac.kntu.util.GameButton;
import ir.ac.kntu.FXDatabase;
import ir.ac.kntu.logic.Map.MapData;
import ir.ac.kntu.modules.characters.Player;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

public class Menu {
    private final Stage stage;
    private final Scene scene;
    private final BorderPane pane;
    private MapData mapData;

    public Menu(Stage stage, Scene scene, BorderPane pane) {
        this.stage = stage;
        this.scene = scene;
        this.pane = pane;
    }

    public void startMenu() {
        mapData = new MapData();
        FXDatabase.getInstance().resetData();

        GameButton playButton = new GameButton(new Image("assets/play.png"), new Image("assets/playHold.png"));
        GameButton continueButton = new GameButton(new Image("assets/continue.png"), new Image("assets/continueHold.png"));

        stage.setResizable(false);
        pane.setMinSize(600, 700);

        BackgroundImage myBI = new BackgroundImage(new Image("assets/2.png", 600, 700, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
        GridPane gridPane = new GridPane();
        FXDatabase.getInstance().setGridPane(gridPane);
        gridPane.setMinSize(600, 500);
        gridPane.setMaxSize(600, 500);
        gridPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


        playButton.getCurrentImageView().setOnMouseClicked(e -> loadPlayers());

        continueButton.getCurrentImageView().setOnMouseClicked(e -> continueGame());

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(50);
        vbox.getChildren().addAll(playButton.getCurrentImageView());
        vbox.getChildren().addAll(continueButton.getCurrentImageView());
        vbox.setMinSize(200, 700);


        pane.setLeft(null);
        pane.setCenter(vbox);
        pane.setRight(null);
    }

    public void loadPlayers() {
//        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/assets/save/players.txt");
//             ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
//            while (true) {
//                try {
//                    mapData.getPlayers().add((Player) inputStream.readObject());
//                } catch (EOFException e) {
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            System.out.println("There isn't any player!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        listPlayers();
    }

    public void listPlayers() {
        Stage listStage = new Stage();
        ListView<String> listView = new ListView<>();
        for (Player player : mapData.getPlayers()) {
            System.out.println(player.getName() + "       " + player.getPlayerHighScore());
            listView.getItems().add(player.getName() + "       " + player.getPlayerHighScore());
        }

        Button newPlayer = new Button("New Player");
        Button submitButton = new Button("Submit");

        newPlayer.setOnMouseClicked(e -> {
            listStage.close();
            signUpPlayerMenu();
        });

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(listView);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.getChildren().addAll(hBox, newPlayer, submitButton);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinSize(300, 400);
        Scene scene = new Scene(vBox, 300, 400);
        listStage.setTitle("Player SignUp");
        listStage.setScene(scene);
        listStage.initModality(Modality.APPLICATION_MODAL);
        listStage.setScene(scene);
        listStage.show();
    }

    public void signUpPlayerMenu() {
        Stage signUpStage = new Stage();
        Label label = new Label("Player Name");
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Cambria", 13));
        TextField playerNameInput = new TextField();
        Button submitButton = new Button("Submit");

        submitButton.setOnMouseClicked(e -> {
            if (!playerNameInput.getText().isEmpty()) {
                System.out.println(playerNameInput.getText());
                Player newPlayer = new Player(mapData, playerNameInput.getText());
                mapData.getPlayers().add(newPlayer);
                mapData.setCurrentPlayer(newPlayer);
                startTheGame();
            }
            signUpStage.close();
        });

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(label, playerNameInput);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.getChildren().addAll(hBox, submitButton);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinSize(300, 400);

        BackgroundImage myBI = new BackgroundImage(new Image("assets/playerSignUp.png", 300, 400, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        vBox.setBackground(new Background(myBI));
        Scene scene = new Scene(vBox, 300, 400);
        signUpStage.setTitle("Player SignUp");
        signUpStage.setScene(scene);
        signUpStage.initModality(Modality.APPLICATION_MODAL);
        signUpStage.setScene(scene);
        signUpStage.show();
    }

    public void startTheGame() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-border-width: 0 0 5 0;");

        root.setCenter(FXDatabase.getInstance().getGridPane());
        scene.setRoot(root);
        GameLogic gameLogic = new GameLogic(root, scene, stage, mapData);
        gameLogic.start();
        gameFinished();
    }

    public void gameFinished() {
        Thread waitForGame = new Thread(() -> {
            while (!mapData.isGameFinished()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Runnable backToMenu = () -> {
                scene.setRoot(pane);
                startMenu();
            };
            Platform.runLater(backToMenu);
        });
        waitForGame.start();
    }

    public void continueGame() {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/assets/save/lastgame/mapdata.txt");
             ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {

            mapData = (MapData) inputStream.readObject();
            GameLogic gameLogic = new GameLogic(pane, scene, stage, mapData);
            gameLogic.start();
        } catch (EOFException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("There isn't any last game!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
