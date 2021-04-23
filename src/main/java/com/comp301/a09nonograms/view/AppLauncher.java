package com.comp301.a09nonograms.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    stage.setTitle("Nonograms");

    StackPane pane = new StackPane();

    Button btn = new Button();
    btn.setText("Click me");

    pane.getChildren().add(btn);

    Scene scene = new Scene(pane, 300, 250);
    stage.setScene(scene);

    stage.show();
  }
}
