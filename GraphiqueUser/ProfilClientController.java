/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueUser;

import Entite.Cours;
import Entite.Utilisateur;
import GraphiqueCours.MainProg;
import GraphiqueCours.MyListener;
import GraphiqueCours.QrCodeController;
import Service.ServiceCours;
import Service.ServiceUtilisateur;
import Utils.DataSource;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Yassine Karoui
 */
public class ProfilClientController implements Initializable {
    int id = InterfaceIAController.getId();
    private Label labelCompte;
    @FXML
    private Label tfSurnomm;
    @FXML
    private Label tfNomm;
    @FXML
    private Label tfPrenomm;
    private Label tfNumeroTell;
    @FXML
    private Label tfEmaill;
    private Label tfDDNN;
    private Label tfSexee;
    
    ServiceUtilisateur SU = new ServiceUtilisateur();
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Label tfNumm;
    @FXML
    private Label tfDDN;
    @FXML
    private Label tfSexe;
    @FXML
    private AnchorPane market;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Pane pnlOrders;
    
        private MyListener myListener;

    
    private List<Cours> cours = new ArrayList<>();
    @FXML
    private Button btnProfil;
    public Label getLabelCompte() {
        return labelCompte;
    }

    public void setLabelCompte(Label labelCompte) {
        this.labelCompte = labelCompte;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            qrcodee();
            Utilisateur u = SU.getUserById(id);
            System.out.println(u);
            tfSurnomm.setText(u.getSurnom());
            tfNomm.setText(u.getNom());
            tfPrenomm.setText(u.getPrenom());
            tfNumm.setText(u.getNumeroDeTelephone());
            tfEmaill.setText(u.getEmail());

            tfDDN.setText(u.getDateDeNaissance());
            tfSexe.setText(u.getSexe());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProfilClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    
    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #D3D3D3");
            pnlCustomer.toFront();
            Stage stage = (Stage) btnMenus.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueCours/Home.fxml"));
            Parent root = loader.load();            
            Stage stageInscription= new Stage();
            stageInscription.initStyle(StageStyle.UNDECORATED);
            stageInscription.setScene(new Scene(root, 1050, 576));
            stageInscription.show(); 
            
 
        }
        if (event.getSource() == btnMenus) {
            //pnlMenus.setStyle("-fx-background-color : #D3D3D3");
            //pnlMenus.toFront();
            Stage stage = (Stage) btnMenus.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueSalleDeSport/SalleDeSport.fxml"));
            Parent root = loader.load();            
            Stage stageInscription= new Stage();
            stageInscription.initStyle(StageStyle.UNDECORATED);
            stageInscription.setScene(new Scene(root, 1050, 576));
            stageInscription.show(); 
            
        }
        if (event.getSource() == btnOverview) {
            //pnlOverview.setStyle("-fx-background-color : #D3D3D3");
            //pnlOverview.toFront();
             Stage stage = (Stage) btnMenus.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueProduits/Produits.fxml"));
            Parent root = loader.load();
            Stage stageInscription= new Stage();
            stageInscription.initStyle(StageStyle.UNDECORATED);
            stageInscription.setScene(new Scene(root, 1050, 576));
            stageInscription.show(); 
            
            
        }
        if(event.getSource()==btnOrders)
        {
            pnlOrders.setStyle("-fx-background-color : #D3D3D3");
            pnlOrders.toFront();
            
            Stage stage = (Stage) btnMenus.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueReclamation/ReclamationFXML.fxml"));
            Parent root = loader.load();            
            Stage stageInscription= new Stage();
            stageInscription.initStyle(StageStyle.UNDECORATED);
            stageInscription.setScene(new Scene(root, 1050, 576));
            stageInscription.show(); 
        }
        if(event.getSource()==btnPackages)
        {
            pnlOrders.setStyle("-fx-background-color : #D3D3D3");
            pnlOrders.toFront();
            Stage stage = (Stage) tfNumm.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueCalendrier/Calendrier.fxml"));
            Parent root = loader.load();
            Stage stageInscription1= new Stage();
            stageInscription1.initStyle(StageStyle.UNDECORATED);
            stageInscription1.setScene(new Scene(root, 1050, 576));
            stageInscription1.show();
            
        }
        if(event.getSource()==btnProfil)
        {
            
            
        }
        if(event.getSource()==btnSignout)
        {
            pnlOrders.setStyle("-fx-background-color : #D3D3D3");
            pnlOrders.toFront();
            Stage stage = (Stage) btnMenus.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueUser/InterfaceIA.fxml"));
            Parent root = loader.load();
            Stage stageInscription1= new Stage();
            stageInscription1.initStyle(StageStyle.UNDECORATED);
            stageInscription1.setScene(new Scene(root, 1050, 576));
            stageInscription1.show();
            
        }
        
    }

    @FXML
    private void afficherrrr(Event event) {
    }

    @FXML
    private void rafraichir(Event event) {
    }
    
     private List<Cours> getData() {
        List<Cours> ttCours = new ArrayList<>();
        Cours cours;

        ServiceCours ss = new ServiceCours();
        ttCours=ss.afficher();

        
        return ttCours;
    }
    
    public static void generate_qr(String image_name,String qrCodeData) {
        try {
            String filePath = "C:\\Users\\asus\\Documents\\ESPRIT\\Projet Integré\\SPORTify\\QRCODE\\"+image_name+".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    private void qrcode(ActionEvent event) {
      
                Connection cnx = DataSource.getInstance().getCnx();
               
          try {
			 String requete = "SELECT * FROM Cours";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String Yo;
                Yo="Le Type De Cours: "+rs.getString("Type_Cours")+"\n"+"La Date :"+rs.getDate("Date")+"\n"+"l'Heure :"+rs.getInt("Heure")+"H"+"\n"+"La Duree est:"+rs.getInt("Duree")+"MIN"+"\n"+"le Mail Coach:"+rs.getString("MailCoach")+"\n"+"Les Places Dispos :"+rs.getInt("Place_Disponible");
            	MainProg.generate_qr(rs.getString("ID_Cours"),Yo);
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
         cours.clear();
                grid.getChildren().clear();
          cours.addAll(getData());
         
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < cours.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../GraphiqueCours/QrCode.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                QrCodeController itemController = fxmlLoader.getController();
                itemController.setData(cours.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public void qrcodee(){
      Connection cnx = DataSource.getInstance().getCnx();
               
          try {
			 String requete = "SELECT * FROM Cours";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String Yo;
                Yo="Le Type De Cours: "+rs.getString("Type_Cours")+"\n"+"La Date :"+rs.getDate("Date")+"\n"+"l'Heure :"+rs.getInt("Heure")+"H"+"\n"+"La Duree est:"+rs.getInt("Duree")+"MIN"+"\n"+"le Mail Coach:"+rs.getString("MailCoach")+"\n"+"Les Places Dispos :"+rs.getInt("Place_Disponible");
            	MainProg.generate_qr(rs.getString("ID_Cours"),Yo);
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        cours.clear();
                grid.getChildren().clear();
          cours.addAll(getData());
          System.out.println(cours);
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < cours.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../GraphiqueCours/QrCode.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                QrCodeController itemController = fxmlLoader.getController();
                itemController.setData(cours.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
