/*
 * Copyright (c) 2015, Kieron Wilkinson
 */

package org.softpres.langtonsant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

  private static final String fxml = "/org/softpres/langtonsant/window.fxml";

  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource(fxml));

    primaryStage.setTitle("Langton's Ant");
    primaryStage.setScene(new Scene(root, 560, 590));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
