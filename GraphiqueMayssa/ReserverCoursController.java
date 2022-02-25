/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueMayssa;

import Entite.Cours;
import Service.ServiceCoachDansCours;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ReserverCoursController implements Initializable {
    
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
    private TableColumn<Cours, String> coach_cours;
    public ObservableList<Cours> data=FXCollections.observableArrayList() ;
    @FXML
    private TableView<Cours> tableCours;

  List<Cours> cours = new ArrayList();
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
    private void ReserverAction(ActionEvent event){
       try { 
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/InterfacesGraphiques/Confirmation.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        int selectedIndex = tableCours.getSelectionModel().getSelectedIndex();
                   if (selectedIndex==-1) {
                                 JOptionPane.showMessageDialog(null,"Try Again");

                         }else{
                       Cours c = cours.get(selectedIndex);

                  if (c.getPlace_Disponible() != 0) {
                   ConfirmationController confirmationController= fxmlloader.getController();
                 confirmationController.setCours(c);
        Stage stageConfirmation= new Stage();
        stageConfirmation.initStyle(StageStyle.UNDECORATED);
        stageConfirmation.setScene(new Scene(root2));
        stageConfirmation.show(); 
        }else{
                       JOptionPane.showMessageDialog(null,"Try Again");

               }
        
       
                            
                            }
       
    } catch (IOException ex) {
                            Logger.getLogger(ReserverCoursController.class.getName()).log(Level.SEVERE, null, ex);
                        }  
    }

}
