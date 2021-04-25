package com.comp301.a09nonograms.view;
import com.comp301.a09nonograms.controller.*;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.control.Button;


public class PuzzleView implements FXComponent{

    private Controller controller;

    public PuzzleView(Controller c){
        controller = c;
    }

    public Parent render() {
        BorderPane pane = new BorderPane();

        int width = controller.getClues().getWidth();
        int height = controller.getClues().getHeight();

        GridPane gp = new GridPane();

        // Makes buttons
        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){
                Button btn;
                if (controller.isEliminated(r,c)){
                    btn = new Button("*");
                } else if (controller.isShaded(r,c)){
                    btn = new Button("X");
                } else {
                    btn = new Button("  ");
                }
                btn.getStyleClass().add("gridButtons");

                gp.add(btn, c+1, r+1, 1, 1);

                int finalC = c;
                int finalR = r;
                btn.setOnMouseClicked((event) -> {

                    controller.setMessage("");

                    if (event.getButton() == MouseButton.PRIMARY)
                    {
                        System.out.println("Left click");
                        controller.toggleShaded(finalR, finalC);
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        System.out.println("Right click");
                        controller.toggleEliminated(finalR, finalC);
                    }

                    AppLauncher.refresh();
                });

            }
        }

        gp.setAlignment(Pos.CENTER);
        pane.setCenter(gp);

        // Clues

        //VBox vbox = new VBox();

        for(int r = 0; r < height; r++){
            HBox h = new HBox();
            for(int i = 0; i < controller.getClues().getRowCluesLength(); i++){
                Label test = new Label(String.valueOf(controller.getClues().getRowClues(r)[i]) + "   ");
                test.getStyleClass().add("clues");
                h.getChildren().add(test);
            }
            //h.setAlignment(Pos.CENTER);
            gp.add(h, 0,r+1);
        }

        for(int c = 0; c < width; c++){
            VBox v = new VBox();
            for(int i = 0; i < controller.getClues().getColCluesLength(); i++){
                Label test = new Label(String.valueOf(controller.getClues().getColClues(c)[i]));
                test.getStyleClass().add("clues");
                HBox hb = new HBox(test);
                hb.setAlignment(Pos.CENTER);
                v.getChildren().add(hb);
            }
            gp.add(v, c+1,0, 1, 1);
        }


        //pane.setLeft(vbox);


        return pane;
    }
}
