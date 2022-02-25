/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueProduits;

import Entite.Produits;
import Service.ProduitsCrud;

import java.awt.HeadlessException;
import java.awt.Window;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.activation.DataSource;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ProduitsFXMLController implements Initializable {

    @FXML
    private TableView<Produits> table_produits;
    @FXML
    private TableColumn<Produits, Integer> col_id;
    @FXML
    private TableColumn<Produits, String> col_libelle;
    @FXML
    private TableColumn<Produits, String> col_type;
    @FXML
    private TableColumn<Produits, Integer> col_quantites;
    @FXML
    private TableColumn<Produits, Integer> col_prix;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tflibelle;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfquantites;
    @FXML
    private TextField tfprix;
    private ObservableList<Produits> listM;
    private ObservableList<Produits> listR=FXCollections.observableArrayList();;
    
    private Stage primaryStage;
    

    ObservableList<Produits> list = FXCollections.observableArrayList();
    @FXML
    private TextField ftR;
    ArrayList name = new ArrayList();

    @FXML
    private Button btnstat;

    Connection cnx = Utils.DataSource.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

            ResultSet rs = null;
        try {
            rs = cnx.createStatement().executeQuery("SELECT * FROM produits");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                try {
                    list.add(new Produits(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));
                } catch (SQLException ex) {
                    Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
          col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
          col_quantites.setCellValueFactory(new PropertyValueFactory<>("quantites"));
          col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
          
                
                table_produits.setItems(list);
                UpdateTable();
                table_produits.refresh();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
 ResultSet rs8 = null;
        try {
            rs8 = cnx.createStatement().executeQuery("SELECT libelle FROM produits");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs8.next()){
                name.add(rs8.getString("libelle"));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         TextFields.bindAutoCompletion(ftR,name);
}    

    @FXML
    private void Ajoutproduits(ActionEvent event) throws SQLException {
        TrayNotification tray = null;
         ProduitsCrud sp = new ProduitsCrud();
        int i = Integer.parseInt(tfid.getText());
        int q=  Integer.parseInt(tfquantites.getText());
        int p= Integer.parseInt(tfprix.getText());
        sp.ajouterProduits(new Produits(i,tflibelle.getText(),tftype.getText(),q,p));
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
         col_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
         col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
         col_quantites.setCellValueFactory(new PropertyValueFactory<>("quantites"));
         col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         
        JOptionPane.showMessageDialog(null,"Produit Ajoutée");
        
         UpdateTable();
        table_produits.refresh();
        tray = new TrayNotification("SPORTIFY", "Produit ajouté avec succces ", NotificationType.SUCCESS);
      
        tray.showAndDismiss(Duration.seconds(6));
    }
    
     @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
            int index = table_produits.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    tfid.setText(col_id.getCellData(index).toString());
    tflibelle.setText(col_libelle.getCellData(index));
    tftype.setText(col_type.getCellData(index));
    tfquantites.setText(col_quantites.getCellData(index).toString());
    tfprix.setText(col_prix.getCellData(index).toString());
    
    
    
    }
    
    // private Connection cnx = DataSource.getInstance().getCnx();

    @FXML
    private void edit(ActionEvent event) {
        try {
            UpdateTable();
            int value1 = Integer.parseInt(tfid.getText());
            
            String value2 = tflibelle.getText();
            String value3 = tftype.getText();
            int value4 = Integer.parseInt(tfquantites.getText());
            int value5 = Integer.parseInt(tfprix.getText());

            
            String sql = "update produits set libelle= '"+value2+"',type= '"+value3+"',quantites= '"+
                    value4+"',prix= '"+value5+"' where id='"+value1+"' ";
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");
            UpdateTable();
            
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }
    
    
    
    

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
         TableColumn.CellEditEvent edittedcell = null;
        Produits x = gettempProduits(edittedcell);
        UpdateTable();

        if (x != null) {

            int i = x.getId();
            ProduitsCrud cat = new ProduitsCrud();

            int s = cat.deleteProduits(i);
            UpdateTable();
            
            
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Produit deleted");
                
                alert.showAndWait();
                table_produits.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }
        
        
        
        
    }
    
    
    public Produits gettempProduits(TableColumn.CellEditEvent edittedCell) {
        Produits test = table_produits.getSelectionModel().getSelectedItem();
        return test;
    }
        
    
    
    
    
    
    
     public void UpdateTable() throws SQLException{
            
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_quantites.setCellValueFactory(new PropertyValueFactory<>("quantites"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
         listM = ProduitsCrud.getDataProduits();
        
        table_produits.setItems(listM);
        }



    @FXML
    private void gotostatistic(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../InterfacesGraphiques/statFXML.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnstat.getScene().setRoot(root);
        
        
        
        
    }

    

    @FXML
    private void SearchProduits(MouseEvent event) throws SQLException {
        ;
      
        String value9 = ftR.getText();

      
            PreparedStatement ps = cnx.prepareStatement("select * from produits where libelle  Like'"+value9+"'");
            
            ResultSet rs5 = ps.executeQuery();  
            
            while (rs5.next()){   
                
                listR.add(new Produits(rs5.getInt(1),rs5.getString(2),rs5.getString(3),rs5.getInt(4),rs5.getInt(5)) );               
            }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_quantites.setCellValueFactory(new PropertyValueFactory<>("quantites"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         table_produits.setItems(listR);
    }

   
    
   
    
}
