package ir.ac.kntu;

import ir.ac.kntu.items.Dirt;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class JavaFxApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-border-width: 0 0 5 0;");

        GridPane gridPane = new GridPane();

        gridPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setMaxWidth(600);
        gridPane.setMaxHeight(500);


//        root.setCenter(gridPane);


        Scene scene = new Scene(root, 600, 700, Color.rgb(240, 240, 240));
        Menu menu = new Menu(stage, scene, root);
        menu.startMenu();

//        scene.setOnKeyPressed(e -> {
//
//
//        });

//        for (Node node : gridPane.getChildren()){
//            GridPane.setHgrow(node,Priority.ALWAYS);
//            GridPane.setHgrow(node,Priority.ALWAYS);
//        }
        // Setting stage properties
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("DigDug");

        stage.setScene(scene);
        stage.show();
    }
}
