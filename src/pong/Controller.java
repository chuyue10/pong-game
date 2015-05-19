//package pong;
//
//import javafx.animation.AnimationTimer;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.input.KeyCode;
//import javafx.scene.layout.Pane;
//
//import java.awt.event.KeyEvent;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class Controller implements Initializable {
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        table.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
//            @Override
//            public void handle(javafx.scene.input.KeyEvent event) {
//                KeyCode key = event.getCode();
//
//                if (key == KeyCode.W) {
//                    wPressed.setText("W IS PRESSED.");
//                }
//
//                if (key == KeyCode.UP) {
//                    upPressed.setText("UP IS PRESSED");
//                }
//
//            }
//        });
//
//        table.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
//            @Override
//            public void handle(javafx.scene.input.KeyEvent event) {
//                KeyCode key = event.getCode();
//
//                if (key == KeyCode.W) {
//                    wPressed.setText("W IS RELEASED.");
//                }
//
//                if (key == KeyCode.UP) {
//                    upPressed.setText("UP IS RELEASED");
//                }
//
//            }
//        });
//
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//            }
//        });
//
//
//    }
//}
//
