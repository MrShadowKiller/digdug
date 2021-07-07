package ir.ac.kntu;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class JavaFxApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        root.setStyle("-fx-border-width: 0 0 5 0;");
        Rectangle rectangle = new Rectangle(10, 20, Color.RED);
        Rectangle rectangle1 = new Rectangle(30, 60, Color.BLUE);

        Button button = new Button("Salam");

                GridPane gridPane = new GridPane();

        root.getChildren().add(gridPane);

        Scene scene = new Scene(root, 800, 600, Color.rgb(240, 240, 240));
        scene.setOnKeyPressed(e -> {
            System.out.println(GridPane.getRowIndex(rectangle1));
            System.out.println(GridPane.getColumnIndex(rectangle1));

        });

        gridPane.add(rectangle, 10, 10);
        gridPane.add(rectangle1, 1, 2);

        // Setting stage properties
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("DigDig");

        stage.setScene(scene);
        stage.show();
    }
}
