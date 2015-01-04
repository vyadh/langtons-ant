/*
 * Copyright (c) 2015, Kieron Wilkinson
 */

package org.softpres.langtonsant;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Controller implements Initializable {

  private static final int ANTS = 1;
  private static final LifeCycle lifeCycle = LifeCycle.of("LR");

  @FXML
  private Slider fps;
  @FXML
  private Group cells;
  private Grid grid;
  private List<Ant> ants;
  private Timeline timeline;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    grid = new Grid(cells);
    ants = IntStream.range(0, ANTS).mapToObj(i -> new Ant(grid, lifeCycle)).collect(Collectors.toList());
  }

  public void restart(ActionEvent actionEvent) {
    stop(actionEvent);
    timeline = new Timeline();
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), this::tick));
    timeline.rateProperty().bind(fps.valueProperty());
    timeline.play();
  }

  public void stop(ActionEvent actionEvent) {
    if (timeline != null) {
      timeline.stop();
    }
  }

  private void tick(ActionEvent event) {
    ants.forEach(Ant::tick);
  }

}
