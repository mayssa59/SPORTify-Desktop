/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;



import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//  ../GraphiqueUser/InterfaceIA.fxml
/**
 *
 * @author Yassine Karoui
 */
public class TestFX extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueUser/InterfaceIA.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);        
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        
    }
    /**
     * @param args the command line arguments
     */ 
    public static void main(String[] args) {
        
        
        launch(args);
    }
    
}
