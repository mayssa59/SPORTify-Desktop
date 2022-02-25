/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueUser;

import Utils.CodeGenerator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class CodeVerificationController implements Initializable {

    @FXML
    private TextField tfCode;
    @FXML
    private Button btnValidierCode;
    @FXML
    private Label labelErreur;
    @FXML
    private Label labelX;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void ValiderCode(ActionEvent event) throws IOException {
        if (tfCode.getText().equals(CodeGenerator.getCode())){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangerMdp.fxml"));
            Parent root = loader.load();
            Stage stageInscription= new Stage();
            stageInscription.initStyle(StageStyle.UNDECORATED);
            stageInscription.setScene(new Scene(root, 600, 400 ));
            stageInscription.show();   
            Stage stage = (Stage) labelX.getScene().getWindow();
            stage.close();
        } else{
            labelErreur.setText("Vérifiez le code inséré.");
        }
    }  
}
