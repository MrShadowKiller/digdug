package ir.ac.kntu;

import ir.ac.kntu.items.Dirt;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class JavaFxApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-border-width: 0 0 5 0;");
        Dirt dirt = new Dirt();

        GridPane gridPane = new GridPane();

        MapData mapData = new MapData();
        MapBuilder mapBuilder = new MapBuilder(gridPane,mapData);
        mapBuilder.startBuild();

        gridPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        gridPane.setMaxWidth(600);
        gridPane.setMaxHeight(500);
        root.setCenter(gridPane);

        Scene scene = new Scene(root, 600, 700, Color.rgb(240, 240, 240));

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
        stage.setTitle("DigDig");

        stage.setScene(scene);
        stage.show();
    }
}
