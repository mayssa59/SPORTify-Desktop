/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueProduits;

import Entite.Produits;
import Service.ProduitsCrud;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class StatFXMLController implements Initializable {

    @FXML
    private BorderPane pane;
  private final ObservableList <PieChart.Data> details =FXCollections.observableArrayList();
        private PieChart piechart;
    @FXML
    private Button btnretour;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        float cv=0;float cn=0; float ce=0;
        ProduitsCrud p=new ProduitsCrud();
                 List <Produits> listp = p.afficherProduits();
                 for( int i=0;i<listp.size();i++)
                 { if (listp.get(i).getType().equals("vetements"))
                     cv+=listp.get(i).getQuantites();
                else if (listp.get(i).getType().equals("nutritions"))
                     cn+=listp.get(i).getQuantites();
                else ce+=listp.get(i).getQuantites();
               
                 
                 }
                 details.addAll(new PieChart.Data("Produits nutritions\n" +""+(cn/(cn+cv+ce))*100+"%",cn ),new PieChart.Data("Produits vetements\n" +""+(cv/(cn+cv+ce))*100+"%",cv ),new PieChart.Data("Produits equipements\n" +""+(ce/(cn+cv+ce))*100+"%",ce ))  ;
                 piechart=new PieChart();
                 piechart.setData(details);
                 piechart.setTitle("statistique des produits");
                 piechart.setLabelsVisible(true);
                 
                 pane.setCenter(piechart);
    }    

    @FXML
    private void goback(MouseEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../InterfacesGraphiques/ProduitsFXML.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnretour.getScene().setRoot(root);
    }
    
}
