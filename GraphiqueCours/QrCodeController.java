/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueCours;


import Entite.Cours;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author user
 */
public class QrCodeController implements Initializable {


    /**
     * Initializes the controller class.
     */
   @FXML
    private Label Type_Cours;
    @FXML
    private Label heure;
    private Label duree;
    private Label date;
     @FXML
    private ImageView photoItem;
     
   private Cours cours;
    private MyListener myListener;
    
     private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(cours);
    }
     public void setData(Cours t, MyListener myListener) {
        this.cours = t;
        this.myListener = myListener;
        Type_Cours.setText(t.getTypeCours());
          
        heure.setText(String.valueOf(t.getHeure())+"H");
         
             File file = new File("C:\\Users\\asus\\Documents\\ESPRIT\\Projet Integré\\Code\\SPORTify\\QRCODE\\"+t.getIdCours()+".png");
        Image image = new Image(file.toURI().toString());
        photoItem.setImage(image);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
