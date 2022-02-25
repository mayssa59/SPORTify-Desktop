/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueMayssa;

import Entite.Utilisateur;
import Utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class StatUsersController implements Initializable {
 Connection cnx = DataSource.getInstance().getCnx();;
    Statement stm, stm1, stm2, stm3;
    private PieChart piechart;
   
    private final ObservableList <PieChart.Data> details =FXCollections.observableArrayList();
    @FXML
    private BorderPane pane1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        float nbrUtilisateur = 0;
        float nbrClient =0;
        float nbrCoach =0;
        float nbrProp =0;
     try {
         String req = "SELECT * FROM `utilisateur`";
         stm = cnx.createStatement();
         ResultSet result = stm.executeQuery(req);
         while (result.next()) {
             nbrUtilisateur +=1; 
         }
         String req1 = "SELECT * FROM `utilisateur` WHERE `role` = 'Client'";
         stm1 = cnx.createStatement();
         ResultSet result1 = stm1.executeQuery(req1);
         while (result1.next()) {
             nbrClient +=1; 
         }
         String req2 = "SELECT * FROM `utilisateur` WHERE `role` = 'Coach'";
         stm2 = cnx.createStatement();
         ResultSet result2 = stm2.executeQuery(req2);
         while (result2.next()) {
             nbrCoach +=1; 
         }
         String req3 = "SELECT * FROM `utilisateur` WHERE `role` = 'Proprietaire salle de sport'";
         stm3 = cnx.createStatement();
         ResultSet result3 = stm3.executeQuery(req3);
         while (result3.next()) {
             nbrProp +=1; 
         }
         System.out.println(nbrClient);
         System.out.println(nbrCoach);
         System.out.println(nbrProp);
          
             details.addAll(new PieChart.Data("Client\n"+(nbrClient/(nbrUtilisateur))*100+"%",nbrClient ),new PieChart.Data("Coach\n"+(nbrCoach/(nbrUtilisateur))*100+"%",nbrCoach ),new PieChart.Data("Proprietaire\n de salle\n"+(nbrProp/(nbrUtilisateur))*100+"%",nbrProp ))  ;
                 piechart=new PieChart();
                 piechart.setData(details);
                 piechart.setTitle("statistique des users");
                 piechart.setLabelsVisible(true);
                 
                 pane1.setCenter(piechart);    
     } catch (SQLException ex) {
         Logger.getLogger(StatUsersController.class.getName()).log(Level.SEVERE, null, ex);
     }
    
}
}
