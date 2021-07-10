package ir.ac.kntu;

import ir.ac.kntu.characters.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Menu {
    private Stage stage;
    private Scene scene;
    private GridPane gridPane;
    private BorderPane pane;
    private Player player;

    public Menu(Stage stage, Scene scene, BorderPane pane) {
        this.stage = stage;
        this.scene = scene;
        this.pane = pane;
    }

    public void startMenu(){
        GameButton playButton = new GameButton(new Image("assets/play.png"),new Image("assets/playHold.png"));
        GameButton continueButton = new GameButton(new Image("assets/continue.png"),new Image("assets/continueHold.png"));
        stage.setResizable(false);
        pane.setMinSize(600,700);

        BackgroundImage myBI= new BackgroundImage(new Image("assets/2.png",600,700,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        pane.setBackground(new Background(myBI));

        playButton.getCurrentImageView().setOnMouseClicked(e->{
            signUpPlayerMenu();
            BorderPane root = new BorderPane();
//            root.setStyle("-fx-border-width: 0 0 5 0;");
//
//            GridPane gridPane = new GridPane();
//
//            gridPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
//            gridPane.setMaxWidth(600);
//            gridPane.setMaxHeight(500);
//            root.setCenter(gridPane);
//            scene.setRoot(root);
//            GameLogic gameLogic = new GameLogic(gridPane,scene,stage);
//            gameLogic.start();
        });



        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(50);
        vbox.getChildren().addAll(playButton.getCurrentImageView());
        vbox.getChildren().addAll(continueButton.getCurrentImageView());
        vbox.setMinSize(200,700);


        pane.setLeft(null);
        pane.setCenter(vbox);
        pane.setRight(null);
    }

    public void showPlayers(){

    }
    public void signUpPlayerMenu(){
        Stage signUpStage = new Stage();
        Label label = new Label("Player Name");
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Cambria",13));
        TextField playerNameInput = new TextField();
        Button submitButton = new Button("Submit");
        submitButton.setOnMouseClicked(e->{
            if (!playerNameInput.getText().isEmpty()){
                System.out.println(playerNameInput.getText());
            }
            signUpStage.close();
        });

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(label,playerNameInput);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20,20,20,20));
        vBox.getChildren().addAll(hBox,submitButton);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinSize(300,400);

        BackgroundImage myBI= new BackgroundImage(new Image("assets/playerSignUp.png",300,400,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        vBox.setBackground(new Background(myBI));
        Scene scene = new Scene(vBox,300,400);
        signUpStage.setTitle("Player SignUp");
        signUpStage.setScene(scene);
        signUpStage.initModality(Modality.APPLICATION_MODAL);
        signUpStage.setScene(scene);
        signUpStage.show();
    }

}
