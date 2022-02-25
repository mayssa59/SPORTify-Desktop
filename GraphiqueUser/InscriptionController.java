/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueUser;

import Service.ServiceUtilisateur;
import Utils.DataSource;
import Utils.EmailExistant;
import Utils.EmailValide;
import Utils.NumeroTelValide;
import Utils.SendEmail;
import Utils.SurnomExistant;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author Yassine Karoui
 */
public class InscriptionController implements Initializable {
    ObservableList list = FXCollections.observableArrayList();
    ObservableList list1 = FXCollections.observableArrayList();

    private Connection cnx = DataSource.getInstance().getCnx();
    private Statement ste;
    private ResultSet result;
    private ServiceUtilisateur SU = new ServiceUtilisateur();
    private SurnomExistant se = new SurnomExistant();
    private EmailExistant me = new EmailExistant();
    private static String surnomVal;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPseudo;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private DatePicker tfDdn;
    @FXML
    private TextField tfNum;
    @FXML
    private Button btnInscriptionUser;
    @FXML
    private Label labelX;
    @FXML
    private Label labelInscriptionReussie;
    @FXML
    private Label labelPdu;
    @FXML
    private Label labelEdu;
    @FXML
    private Label labelNumInvalide;
    @FXML
    private ChoiceBox<String> CBsexe;
    @FXML
    private ChoiceBox<String> CBrole;
    
    
    public static String getSurnomVal() {
        return surnomVal;
    }

    public static void setSurnomVal(String surnomVal) {
        InscriptionController.surnomVal = surnomVal;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }    

    @FXML
    private void FermerFenetre(MouseEvent event) throws IOException {
        Stage stage = (Stage) labelX.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceIA.fxml"));
        Parent root = loader.load();
        Stage stageInscription= new Stage();
        stageInscription.initStyle(StageStyle.UNDECORATED);
        stageInscription.setScene(new Scene(root, 900, 500 ));
        stageInscription.show();  
    }
    
    private void loadData(){
        list.removeAll(list);
        String a = "Homme";
        String b = "Femme";
        list.addAll(a,b);
        CBsexe.getItems().addAll(list);
        
        list1.removeAll(list1);
        String aa = "Client";
        String bb = "Coach";
        String cc = "Proprietaire salle de sport";
        list1.addAll(aa,bb,cc);
        CBrole.getItems().addAll(list1);
        
    }
    
    @FXML
    private void InscriptionUser(ActionEvent event) throws SQLException, InterruptedException, IOException {        
        if (tfPrenom.getText().isEmpty() == false && tfNom.getText().isEmpty() == false && tfPseudo.getText().isEmpty() == false && tfEmail.getText().isEmpty() == false && tfNum.getText().isEmpty() == false && tfPassword.getText().isEmpty() == false && tfDdn.getValue() != null && CBsexe.getValue()!= null && CBrole.getValue()!= null){
            Calendar now = Calendar.getInstance();
            if (now.get(Calendar.YEAR) - tfDdn.getValue().getYear() > 15){
                int test = SU.inscription(tfPseudo.getText(), tfPrenom.getText(), tfNom.getText(), tfNum.getText(), tfEmail.getText(), tfPassword.getText(), tfDdn.getValue().toString(), CBsexe.getValue(), CBrole.getValue());
                labelInscriptionReussie.setText("");
                labelPdu.setText("");
                labelEdu.setText("");
                labelNumInvalide.setText("");
                if (test == 1){
                    surnomVal = tfPseudo.getText();
                    SendEmail.SendCompteVerification(tfPseudo.getText());
                    Stage stage = (Stage) labelX.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CompteVerification.fxml"));
                    Parent root = loader.load();
                    Stage stageInscription= new Stage();
                    stageInscription.initStyle(StageStyle.UNDECORATED);
                    stageInscription.setScene(new Scene(root, 600, 400 ));
                    stageInscription.show();      
                } else{
                if (se.verifier(tfPseudo.getText()))
                    labelPdu.setText("Surnom deja utilisé");
                if (me.verifier(tfEmail.getText()))
                    labelEdu.setText("Email deja utilisé");
                if (!(NumeroTelValide.verifierNumeroTel(tfNum.getText())))
                    labelNumInvalide.setText("Numero invalide");
                if (!(EmailValide.verifierEmail(tfEmail.getText())))
                    labelEdu.setText("Email invalide");
                } 
            } else{
                labelInscriptionReussie.setText("Vous devez avoir plus de 15ans pour vous inscrire");
                return;
            }
        } else labelInscriptionReussie.setText("Veuillez remplir tous les champs");
        
    }
}
