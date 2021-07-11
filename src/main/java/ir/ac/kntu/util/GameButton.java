package ir.ac.kntu.util;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameButton {
    private final Image normal;

    private final Image hold;

    private ImageView currentImageView;

    public GameButton(Image normal, Image hold) {
        this.normal = normal;
        this.hold = hold;
        currentImageView = new ImageView(normal);
        currentImageView.setFitHeight(60);
        currentImageView.setFitWidth(180);
        currentImageView.setOnMouseEntered(e -> {
            currentImageView.setImage(hold);
        });
        currentImageView.setOnMouseExited(e -> {
            currentImageView.setImage(normal);
        });
    }

    public ImageView getCurrentImageView() {
        return currentImageView;
    }


    public void setImageView(ImageView imageView) {
        this.currentImageView = imageView;
    }
}
