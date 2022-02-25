package GraphiqueCours;


import Entite.Cours;
import Service.ServiceCours;
import Utils.DataSource;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.FileInputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

public class HomeController implements Initializable {

    private VBox pnItems = null;
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
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;
    @FXML
    private AnchorPane Ajouter1;
    @FXML
    private TextField tf_type;
    @FXML
    private TextField tf_heure;
    @FXML
    private TextField tf_duree;
    @FXML
    private TextField tf_id;
    @FXML
    private Button btn_ajouter;
    @FXML
    private ComboBox<String> CB_Mail;
    @FXML
    private DatePicker date;
    @FXML
    private TextField tf_dispo;
    @FXML
    private AnchorPane Modifier;
    @FXML
    private TextField tf_type1;
    @FXML
    private TextField tf_heure1;
    @FXML
    private TextField tf_duree1;
    @FXML
    private TextField tf_id1;
    @FXML
    private Button btn_modifier;
    @FXML
    private DatePicker date1;
    @FXML
    private ComboBox<String> CB_Mail1;
    @FXML
    private TextField tf_dispo1;
    @FXML
    private AnchorPane Supprimer;
    @FXML
    private Button btn_supprimer;
    @FXML
    private ComboBox<String> CB_Supprimer;
    @FXML
    private TextField tf_recherche;
    @FXML
    private Button btn_trier;
    @FXML
    private TableView<Cours> ListeCours;
    @FXML
    private TableColumn<?, ?> ID_Cours;
    @FXML
    private TableColumn<?, ?> Type_Cours;
    @FXML
    private TableColumn<?, ?> Datee;
    @FXML
    private TableColumn<?, ?> Duree;
    @FXML
    private TableColumn<?, ?> MailCoach;
    @FXML
    private TableColumn<?, ?> Heure;
    @FXML
    private TableColumn<?, ?> dispo;
    @FXML
    private TextField rechercheee;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    private List<Cours> cours = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    @FXML
    private AnchorPane market;
    @FXML
    private Button btnProfil;

    private List<Cours> getData() {
        List<Cours> ttCours = new ArrayList<>();
        Cours cours;

        ServiceCours ss = new ServiceCours();
        ttCours=ss.afficher();

        
        return ttCours;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         ServiceCours ss = new ServiceCours();
          ObservableList<String> Mail = FXCollections.observableArrayList(ss.afficherALLCoach());
                CB_Mail.setItems(Mail);
                CB_Mail1.setItems(Mail);
                ObservableList<String> id = FXCollections.observableArrayList(ss.afficherALLId());
                CB_Supprimer.setItems(id);
          qrcodee();
         
        
      
    }


    @FXML
    public void handleClicks(ActionEvent actionEvent) throws IOException {
        ServiceCours ss = new ServiceCours();
        ObservableList<Cours> items = FXCollections.observableArrayList(ss.afficher());
       
        ID_Cours.setCellValueFactory(new PropertyValueFactory("IdCours"));
        Type_Cours.setCellValueFactory(new PropertyValueFactory("TypeCours"));
        Datee.setCellValueFactory(new PropertyValueFactory("Date"));
        Heure.setCellValueFactory(new PropertyValueFactory("Heure"));
        Duree.setCellValueFactory(new PropertyValueFactory("Duree"));
        MailCoach.setCellValueFactory(new PropertyValueFactory("MailCoach"));
        dispo.setCellValueFactory(new PropertyValueFactory("Place_Disponible"));

        ListeCours.setItems(items); 

        ObservableList<String> Mail = FXCollections.observableArrayList(ss.afficherALLCoach());
        CB_Mail.setItems(Mail);
        CB_Mail1.setItems(Mail);
        ObservableList<String> id = FXCollections.observableArrayList(ss.afficherALLId());
        CB_Supprimer.setItems(id);
        ObservableList<String> listerecherche = FXCollections.observableArrayList(ss.afficherALLId());
        TextFields.bindAutoCompletion(rechercheee,listerecherche);
                
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #D3D3D3");
            pnlCustomer.toFront();
 
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #D3D3D3");
            pnlMenus.toFront();
            Stage stage = (Stage) tf_recherche.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueSalleDeSport/SalleDeSport.fxml"));
            Parent root = loader.load();
            Stage stageInscription= new Stage();
            stageInscription.initStyle(StageStyle.UNDECORATED);

            stageInscription.setScene(new Scene(root, 1050, 576));
            stageInscription.show(); 
            
            
            
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #D3D3D3");
            pnlOverview.toFront();
            Stage stage = (Stage) tf_recherche.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueProduits/Produits.fxml"));
            Parent root = loader.load();
            Stage stageInscription1= new Stage();
            stageInscription1.initStyle(StageStyle.UNDECORATED);
            stageInscription1.setScene(new Scene(root, 1050, 576));
            stageInscription1.show();
            

        }
        if(actionEvent.getSource()==btnOrders)
        {
            pnlOrders.setStyle("-fx-background-color : #D3D3D3");
            pnlOrders.toFront();
            Stage stage = (Stage) tf_recherche.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueReclamation/ReclamationFXML.fxml"));
            Parent root = loader.load();
            Stage stageInscription1= new Stage();
            stageInscription1.initStyle(StageStyle.UNDECORATED);
            stageInscription1.setScene(new Scene(root, 1050, 576));
            stageInscription1.show();
            
            
        }
        if(actionEvent.getSource()==btnPackages)
        {
            pnlOrders.setStyle("-fx-background-color : #D3D3D3");
            pnlOrders.toFront();
            Stage stage = (Stage) tf_recherche.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueCalendrier/Calendrier.fxml"));
            Parent root = loader.load();
            Stage stageInscription1= new Stage();
            stageInscription1.initStyle(StageStyle.UNDECORATED);
            stageInscription1.setScene(new Scene(root, 1050, 576));
            stageInscription1.show();
            
        }
        
        if(actionEvent.getSource()==btnSignout)
        {
            pnlOrders.setStyle("-fx-background-color : #D3D3D3");
            pnlOrders.toFront();
            Stage stage = (Stage) tf_recherche.getScene().getWindow();
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
    private void AjouterCours(ActionEvent event) {
         java.sql.Date sqlDate =java.sql.Date.valueOf(date.getValue());
        ServiceCours ss = new ServiceCours();
        
        if (tf_id.getText().isEmpty()||tf_type.getText().isEmpty()||tf_duree.getText().isEmpty()||tf_heure.getText().isEmpty()||tf_dispo.getText().isEmpty()||CB_Mail.getValue().isEmpty()||date.getValue().equals(""))
        {
             Alert alert = new Alert(Alert.AlertType.ERROR); 
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else{
            if (java.time.LocalDate.now().compareTo(date.getValue())<0)
            {
            
        ss.ajouter(new Cours(tf_id.getText(),tf_type.getText(),sqlDate,Integer.parseInt(tf_heure.getText()),Integer.parseInt(tf_duree.getText()),CB_Mail.getValue(),Integer.parseInt(tf_dispo.getText())));
         JOptionPane.showMessageDialog(null,"Cours Ajouté");
         clean();
            Notifications n = Notifications.create()
                .title("NOTIFICATION")
                .text(" Cours ajouté !!")
                .position(Pos.TOP_CENTER)
                .hideAfter(Duration.seconds(3));
        n.darkStyle();
        n.show();}
            else {
               Notifications n = Notifications.create()
                .title("NOTIFICATION")
                .text(" Cours non ajouté !!")
                .position(Pos.TOP_CENTER)
                .hideAfter(Duration.seconds(3));
        n.darkStyle();
        n.show();
        }
         
    }
    }

    @FXML
    private void ModifierCours(ActionEvent event) {
         java.sql.Date sqlDate =java.sql.Date.valueOf(date1.getValue());
        
        ServiceCours ss = new ServiceCours();
        
                ss.modifier(new Cours(tf_id1.getText(),tf_type1.getText(),sqlDate,Float.parseFloat(tf_heure1.getText()),Integer.parseInt(tf_duree1.getText()),CB_Mail1.getValue(),Integer.parseInt(tf_dispo1.getText())));
Notifications n = Notifications.create()
                .title("NOTIFICATION")
                .text(" Cours modifié !!")
                .position(Pos.TOP_CENTER)
                .hideAfter(Duration.seconds(3));
        n.darkStyle();
        n.show();          clean();
    }

    @FXML
    private void recherche1(MouseEvent event) {
         ServiceCours U = new ServiceCours();
           ObservableList<String> listerecherche = FXCollections.observableArrayList(U.afficherALLId());
               TextFields.bindAutoCompletion(rechercheee,listerecherche);

        Cours UserModif = U.rechercherID(rechercheee.getText());
        tf_id1.setText(UserModif.getIdCours());
        tf_type1.setText(UserModif.getTypeCours());
        tf_heure1.setText(String.valueOf(UserModif.getHeure()));
        tf_duree1.setText(String.valueOf(UserModif.getDuree()));
        CB_Mail1.setValue(UserModif.getMailCoach());
        tf_dispo1.setText(String.valueOf(UserModif.getPlace_Disponible()));
        
    }

    @FXML
    private void SupprimerCours(ActionEvent event) {
         ServiceCours ss = new ServiceCours();
        ss.supprimer(CB_Supprimer.getValue());
         Notifications n = Notifications.create()
                .title("NOTIFICATION")
                .text(" Cours supprimé !!")
                .position(Pos.TOP_CENTER)
                .hideAfter(Duration.seconds(3));
        n.darkStyle();
        n.show();
          clean();
           
    }

    @FXML
    private void rechercherCours(KeyEvent event) {
        ServiceCours ss = new ServiceCours();
        ObservableList<Cours> items = FXCollections.observableArrayList(ss.rechercher(tf_recherche.getText()));
       
           ID_Cours.setCellValueFactory(new PropertyValueFactory("IdCours"));
           Type_Cours.setCellValueFactory(new PropertyValueFactory("TypeCours"));
           Datee.setCellValueFactory(new PropertyValueFactory("Date"));
           Heure.setCellValueFactory(new PropertyValueFactory("Heure"));
           Duree.setCellValueFactory(new PropertyValueFactory("Duree"));
           MailCoach.setCellValueFactory(new PropertyValueFactory("MailCoach"));
           dispo.setCellValueFactory(new PropertyValueFactory("Place_Disponible"));
            

           ListeCours.setItems(items); 
           if(ss.rechercher(tf_recherche.getText()).isEmpty()){
              
          ObservableList<Cours> item = FXCollections.observableArrayList(ss.afficher());
           ID_Cours.setCellValueFactory(new PropertyValueFactory("IdCours"));
           Type_Cours.setCellValueFactory(new PropertyValueFactory("TypeCours"));
           Datee.setCellValueFactory(new PropertyValueFactory("Date"));
           Heure.setCellValueFactory(new PropertyValueFactory("Heure"));
           Duree.setCellValueFactory(new PropertyValueFactory("Duree"));
           MailCoach.setCellValueFactory(new PropertyValueFactory("MailCoach"));
           dispo.setCellValueFactory(new PropertyValueFactory("Place_Disponible"));

           ListeCours.setItems(item); }
              
    }

    @FXML
    private void TrierCours(ActionEvent event) {
        ServiceCours ss = new ServiceCours();
        ObservableList<Cours> items = FXCollections.observableArrayList(ss.TrierDate());
       
           ID_Cours.setCellValueFactory(new PropertyValueFactory("IdCours"));
           Type_Cours.setCellValueFactory(new PropertyValueFactory("TypeCours"));
           Datee.setCellValueFactory(new PropertyValueFactory("Date"));
           Heure.setCellValueFactory(new PropertyValueFactory("Heure"));
           Duree.setCellValueFactory(new PropertyValueFactory("Duree"));
           MailCoach.setCellValueFactory(new PropertyValueFactory("MailCoach"));
           dispo.setCellValueFactory(new PropertyValueFactory("Place_Disponible"));

           ListeCours.setItems(items);  
    }

    private void recherche1(ActionEvent event) {
        ServiceCours U = new ServiceCours();
  ObservableList<String> listerecherche = FXCollections.observableArrayList(U.afficherALLId());
               TextFields.bindAutoCompletion(rechercheee,listerecherche);
        Cours UserModif = U.rechercherID(rechercheee.getText());
        tf_id1.setText(UserModif.getIdCours());
        tf_type1.setText(UserModif.getTypeCours());
        tf_heure1.setText(String.valueOf(UserModif.getHeure()));
        tf_duree1.setText(String.valueOf(UserModif.getDuree()));
        CB_Mail1.setValue(UserModif.getMailCoach());
        tf_dispo1.setText(String.valueOf(UserModif.getPlace_Disponible()));
        
    }

    @FXML
    private void afficherrrr(Event event) {
         ServiceCours ss = new ServiceCours();
        ObservableList<Cours> items = FXCollections.observableArrayList(ss.afficher());
        
           ID_Cours.setCellValueFactory(new PropertyValueFactory("IdCours"));
           Type_Cours.setCellValueFactory(new PropertyValueFactory("TypeCours"));
           Datee.setCellValueFactory(new PropertyValueFactory("Date"));
           Heure.setCellValueFactory(new PropertyValueFactory("Heure"));
           Duree.setCellValueFactory(new PropertyValueFactory("Duree"));
           MailCoach.setCellValueFactory(new PropertyValueFactory("MailCoach"));
           dispo.setCellValueFactory(new PropertyValueFactory("Place_Disponible"));
           

           ListeCours.setItems(items); 
              
                ObservableList<String> Mail = FXCollections.observableArrayList(ss.afficherALLCoach());
                CB_Mail.setItems(Mail);
                CB_Mail1.setItems(Mail);
                ObservableList<String> id = FXCollections.observableArrayList(ss.afficherALLId());
                CB_Supprimer.setItems(id);
                


    }
      private void clean() {
        
        tf_id.setText(null);
        tf_type.setText(null);
        tf_heure.setText(null);
        tf_duree.setText(null);
        tf_dispo.setText(null);
         tf_id1.setText(null);
        tf_type1.setText(null);
        tf_heure1.setText(null);
        tf_duree1.setText(null);
        tf_dispo1.setText(null);
        date.getEditor().clear();
        date1.getEditor().clear();
 
        
       
        
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
                fxmlLoader.setLocation(getClass().getResource("itemm.fxml"));
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
                fxmlLoader.setLocation(getClass().getResource("QrCode.fxml"));
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
    @FXML
    private void rafraichir(Event event) {
        qrcodee();
      
    }
          
    }
          
        
    

  



