package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.Main;
import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BottomView implements FXComponent{

    private Controller controller;
    private static Label message;

    public BottomView(Controller c){
        controller = c;
    }

  public Parent render() {

    FlowPane pane = new FlowPane();
    pane.setAlignment(Pos.CENTER);

    // Vbox

    VBox vbox = new VBox();
    pane.getChildren().add(vbox);

    vbox.getChildren().add(new Label(""));
    vbox.getChildren().add(new Label(""));

    // Victory message

    Label win = new Label("YOU'VE SOLVED THIS PUZZLE");
    win.getStyleClass().add("win");
    HBox hbox = new HBox(win);
    hbox.setAlignment(Pos.CENTER);
    vbox.getChildren().add(hbox);
    win.setVisible(controller.isSolved());

    vbox.getChildren().add(new Label(""));
    vbox.getChildren().add(new Label(""));

    // Message label

    Label message = new Label(controller.getMessage());
    message.getStyleClass().add("message");
    HBox hbox2 = new HBox(message);
    hbox2.setAlignment(Pos.CENTER);
    vbox.getChildren().add(hbox2);


    vbox.getChildren().add(new Label(""));
    vbox.getChildren().add(new Label(""));
    vbox.getChildren().add(new Label(""));
    vbox.getChildren().add(new Label(""));

    vbox.setAlignment(Pos.CENTER);

    return pane;

    }

}
