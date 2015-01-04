/*
 * Copyright (c) 2015, Kieron Wilkinson
 */

package org.softpres.langtonsant;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Controller implements Initializable {

  @FXML
  private Group cells;
  @FXML
  private TextField antCount;
  @FXML
  private TextField pattern;
  @FXML
  private Slider fps;
  @FXML
  private Button startStop;

  private Grid grid;
  private List<Ant> ants;
  private Timeline timeline;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    grid = new Grid(cells);
    enableStartOnlyWhenValidInput();
  }

  private void enableStartOnlyWhenValidInput() {
    // JavaFX needs built-in support for regular expressions here :)
    startStop.disableProperty().bind(Bindings.or(
          antCount.textProperty().isEmpty(),
          pattern.textProperty().isEmpty()
    ));
  }

  public void startStop() {
    if (startStop.getText().equals("Start")) {
      startStop.setText("Stop");
      LifeCycle lifeCycle = createLifecycle();
      ants = createAnts(lifeCycle);
      timeline = createTimeline();
      timeline.play();
    } else {
      startStop.setText("Start");
      timeline.stop();
    }
  }

  private List<Ant> createAnts(LifeCycle lifeCycle) {
    int ants = Integer.valueOf(antCount.getText());

    return IntStream.range(0, ants)
          .mapToObj(i -> new Ant(grid, lifeCycle))
          .collect(Collectors.toList());
  }

  private LifeCycle createLifecycle() {
    return LifeCycle.of(pattern.getText());
  }

  private Timeline createTimeline() {
    Timeline result = new Timeline();
    result.setCycleCount(Animation.INDEFINITE);
    result.getKeyFrames().add(new KeyFrame(Duration.seconds(1), this::tick));
    result.rateProperty().bind(fps.valueProperty());
    result.setOnFinished((event) -> startStop.setText("Start"));
    return result;
  }

  private void tick(ActionEvent event) {
    ants.forEach(Ant::tick);
  }

  public void reset() {
    grid.reset();
  }

}
