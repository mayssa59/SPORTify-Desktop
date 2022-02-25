/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueMayssa;

import Utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class StatGenreController implements Initializable {

    @FXML
    private BorderPane pane2;
     Connection cnx = DataSource.getInstance().getCnx();;
    Statement stm, stm1;
    private PieChart piechart;
   
    private final ObservableList <PieChart.Data> details =FXCollections.observableArrayList();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        float nbrHomme =0;
        float nbrFemme =0;
     try {
         
         String req = "SELECT * FROM `utilisateur` WHERE `sexe` = 'Homme'";
         stm = cnx.createStatement();
         ResultSet result = stm.executeQuery(req);
         while (result.next()) {
             nbrHomme +=1; 
         }
         String req1 = "SELECT * FROM `utilisateur` WHERE `sexe` = 'Femme'";
         stm1 = cnx.createStatement();
         ResultSet result1 = stm1.executeQuery(req1);
         while (result1.next()) {
             nbrFemme +=1; 
         }
        
             details.addAll(new PieChart.Data("Homme\n"+(nbrHomme/(nbrHomme+nbrFemme))*100+"%",nbrHomme ),new PieChart.Data("Femme\n"+(nbrFemme/(nbrHomme+nbrFemme))*100+"%",nbrFemme ))  ;
                 piechart=new PieChart();
                 piechart.setData(details);
                 piechart.setTitle("statistique des users");
                 piechart.setLabelsVisible(true);
                 
                 pane2.setCenter(piechart);    
     } catch (SQLException ex) {
         Logger.getLogger(StatUsersController.class.getName()).log(Level.SEVERE, null, ex);
     }
    
}
    }    
    

