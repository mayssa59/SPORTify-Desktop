/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueMayssa;

import Entite.Cours;
import Entite.Utilisateur;
import Service.ServiceClients;
import Service.ServiceCoachDansCours;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ConfirmationController implements Initializable {
public Cours cours; 
    @FXML
    private Button confirmer;
    @FXML
    private Button annuler;

    /**
     * Initializes the controller class.
     */
      @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    public void setCours(Cours c)
    {
        this.cours=c;
        System.out.println("after setting cours in second controller :"+this.cours);
    }

    @FXML
    private void confirmerReservation(ActionEvent event) throws SQLException {
        TrayNotification tray = null;
        ServiceClients S3 = new ServiceClients();
 
            S3.reserverCours(cours);
            Stage stage = (Stage) confirmer.getScene().getWindow();
             tray = new TrayNotification("SPORTIFY", "Cours reservé ", NotificationType.SUCCESS);
      
        tray.showAndDismiss(Duration.seconds(6));
        stage.close();
        }

    @FXML
    private void annulerRetourner(ActionEvent event) {
        Stage stage = (Stage) annuler.getScene().getWindow();
            stage.close();
    }


}


  
   

    

