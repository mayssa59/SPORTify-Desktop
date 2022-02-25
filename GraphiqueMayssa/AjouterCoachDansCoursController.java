/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueMayssa;

import Entite.Cours;
import Entite.Utilisateur;
import Service.ServiceCoachDansCours;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterCoachDansCoursController implements Initializable {

    @FXML
    private TableView<Cours> tableCours;
    @FXML
    private TableColumn<Cours, String> type_cours;
    @FXML
    private TableColumn<Cours, String> date_cours;
    @FXML
    private TableColumn<Cours, String> heure_cours;
    @FXML
    private TableColumn<Cours, String> duree_cours;
    @FXML
    private TableColumn<Cours, Integer> place_dispo;
     @FXML
  private TableColumn<Cours,String> coach_cours;
     
     
     
    public ObservableList<Cours> data=FXCollections.observableArrayList() ;
   
    
    
   List<Cours> cours = new ArrayList();
    @FXML
    private ImageView myimageview;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCoachDansCours S1 = new ServiceCoachDansCours();
        cours = new ArrayList();
        try {
            cours = S1.afficherCours();
            type_cours.setCellValueFactory(new PropertyValueFactory<Cours,String>("Type_Cours"));
            date_cours.setCellValueFactory(new PropertyValueFactory<Cours,String>("Date"));
            heure_cours.setCellValueFactory(new PropertyValueFactory<Cours,String>("Heure"));
            duree_cours.setCellValueFactory(new PropertyValueFactory<Cours,String>("Duree"));
            place_dispo.setCellValueFactory(new PropertyValueFactory<Cours,Integer>("Place_Disponible"));
            coach_cours.setCellValueFactory(new PropertyValueFactory<Cours,String>("MailCoach"));
            data.clear();
            for (int i = 0; i < cours.size(); i++) {
                data.add(cours.get(i));
                System.out.println(cours.get(i).toString());
            }
            tableCours.setItems(data);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AjouterCoachDansCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    

    @FXML
    private void affecterCours(ActionEvent event) {
        
                        try {
                            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/InterfacesGraphiques/ListesCoach.fxml"));
                            Parent root1=(Parent) fxmlLoader.load();
                            int selectedIndex = tableCours.getSelectionModel().getSelectedIndex();
                            if (selectedIndex==-1) {
        JOptionPane.showMessageDialog(null,"Try Again");
                            }else{
                                Cours c = cours.get(selectedIndex);
                                
                                if (c.getMailCoach()== "pas de coach pour ce cours") {
                                    ListesCoachController listescoachController=fxmlLoader.getController();
                                    listescoachController.setCours(c);
                                    Stage stage=new Stage();
                                    stage.setScene(new Scene(root1));
                                    stage.show();
                                }else{
        JOptionPane.showMessageDialog(null,"coach déja affecté");
                                }

                            }

                        } catch (IOException ex) {
                            Logger.getLogger(AjouterCoachDansCoursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        tableCours.refresh();
        
    }

    @FXML
    private void supprimerCoach(ActionEvent event) throws SQLException {
        TrayNotification tray = null;
        ServiceCoachDansCours S1 = new ServiceCoachDansCours();
       int selectedIndex = tableCours.getSelectionModel().getSelectedIndex();
       if (selectedIndex==-1) {
        JOptionPane.showMessageDialog(null,"Try Again");
       }else{
        Cours c= (Cours) tableCours.getSelectionModel().getSelectedItem();
        
        System.out.println("test :"+selectedIndex);
        
            S1.supprimerCoachDeCours(c);
            Stage stage = (Stage) tableCours.getScene().getWindow();
          tray = new TrayNotification("SPORTify", "Coach supprimé", NotificationType.SUCCESS);
      
        tray.showAndDismiss(Duration.seconds(6));
        }

    }

   

    @FXML
    private void modifierCoach(ActionEvent event) {
        try {
                            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/InterfacesGraphiques/ListesCoach.fxml"));
                            Parent root1=(Parent) fxmlLoader.load();
                            int selectedIndex = tableCours.getSelectionModel().getSelectedIndex();
                            if (selectedIndex==-1) {
                               JOptionPane.showMessageDialog(null,"Try Again");
                            }else{
                                Cours c = cours.get(selectedIndex);
                                
                                if (c.getMailCoach()== c.getMailCoach()) {
                                    ListesCoachController listescoachController=fxmlLoader.getController();
                                    listescoachController.setCours(c);
                                    Stage stage=new Stage();
                                    stage.setScene(new Scene(root1));
                                    stage.show();
                                }else{
                                    JOptionPane.showMessageDialog(null,"Pas de coach");
                                }

                            }

                        } catch (IOException ex) {
                            Logger.getLogger(AjouterCoachDansCoursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
    }

   
    

    
    
}
