/*
 * Copyright (c) 2015, Kieron Wilkinson
 */

package org.softpres.langtonsant;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

  private static final int CELL_SIZE = 4;
  private static final int FPS = 30;

  public Group grid;
  private Timeline timeline = new Timeline();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    for (int i = 0; i <= 100; i++) {
      for (int j = 0; j <= 100; j++) {
        Rectangle cell = new Rectangle();
        cell.setWidth(CELL_SIZE);
        cell.setHeight(CELL_SIZE);
        cell.setX(i + (i * CELL_SIZE));
        cell.setY(j + (j * CELL_SIZE));
        grid.getChildren().add(cell);
      }
    }
  }

  public void start(ActionEvent actionEvent) {
    timeline.stop();
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000 / FPS), this::tick));
    timeline.play();
  }

  private void tick(ActionEvent event) {
    // todo Currently just Random
    Random r = new Random();
    for (Node node : grid.getChildren()) {
      ((Rectangle)node).setFill(Color.gray(r.nextDouble()));
    }
  }

}
