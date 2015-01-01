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
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Controller implements Initializable {

  private static final int RANGE = 100;
  private static final int CELL_SIZE = 4;
  private static final int FPS = 30;

  public Group grid;
  private Timeline timeline = new Timeline();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // I'm forcing myself to use a stream here, but without for combinations or a zip method
    // it's not very nice, and the imperative for loop would probably have been nicer
    Stream<Rectangle> cells = IntStream.range(0, RANGE).boxed().flatMap(
          x -> IntStream.range(0, RANGE).mapToObj(y -> cell(x, y)));

    grid.getChildren().addAll(cells.collect(toList()));
  }

  private Rectangle cell(int x, int y) {
    Rectangle cell = new Rectangle();
    cell.setWidth(CELL_SIZE);
    cell.setHeight(CELL_SIZE);
    cell.setX(x + (x * CELL_SIZE));
    cell.setY(y + (y * CELL_SIZE));
    return cell;
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
    grid.getChildren().stream().forEach(node -> ((Rectangle)node).setFill(Color.gray(r.nextDouble())));
  }

}
