package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.Main;
import com.comp301.a09nonograms.controller.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppLauncher extends Application {

  private static BorderPane pane;
  private static HeaderView hv = new HeaderView(Main.getController());
  private static PuzzleView pv = new PuzzleView(Main.getController());
  private static BottomView bv = new BottomView(Main.getController());

  public void start(Stage stage) {
    stage.setTitle("Nonograms");

    pane = new BorderPane();

    pane.setTop(hv.render());
    pane.setCenter(pv.render());
    pane.setBottom(bv.render());

    // Scene setting

    Scene scene = new Scene(pane, 500, 700);
    scene.getStylesheets().add("style/main.css");
    stage.setScene(scene);

    stage.show();
  }

  public static void refresh() {
    pane.setTop(hv.render());
    pane.setCenter(pv.render());
    pane.setBottom(bv.render());
  }
}
