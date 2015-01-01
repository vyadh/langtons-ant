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
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

  private static final int FPS = 30;

  public Group cells;
  private Grid grid;
  private Timeline timeline = new Timeline();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    grid = new Grid(cells);
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
    grid.cells().forEach(node -> node.setFill(Color.gray(r.nextDouble())));
  }

}
