package ir.ac.kntu;

import ir.ac.kntu.logic.Menu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Mohammad Shahabadi 9927453
 */
public class JavaFxApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage)  {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-border-width: 0 0 5 0;");

        GridPane gridPane = new GridPane();

        gridPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setMaxWidth(600);
        gridPane.setMaxHeight(500);


        Scene scene = new Scene(root, 600, 700, Color.rgb(240, 240, 240));
        Menu menu = new Menu(stage, scene, root);
        menu.startMenu();


        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("DigDug");

        stage.setScene(scene);
        stage.show();
    }
}
