/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueUser;

/**
 *
 * @author Yassine Karoui
 */

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import nl.captcha.Captcha;



/**
 * FXML Controller class
 *
 * @author yassi
 */
public class CaptchaController implements Initializable {

    @FXML
    private ImageView cap;
    @FXML
    private JFXButton submit;
    @FXML
    private TextField code;
    @FXML
    private JFXButton reset;
    @FXML
    private Label labelX;

    /**
     * Initializes the controller class.
     * @return 
     */
    
    public Captcha setCaptcha() {
        Captcha captcha = new Captcha.Builder(250, 200)
                .addText()
                .addBackground()
                .addNoise()
                .gimp()
                .addBorder()
                .build();

        System.out.println(captcha.getImage());
        Image image = SwingFXUtils.toFXImage(captcha.getImage(), null);

        cap.setImage(image);

        return captcha;
    }
    Captcha captcha1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        captcha1 =  setCaptcha();
    
    }

    @FXML
    private void submit(ActionEvent event) throws IOException {
        if (captcha1.isCorrect(code.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
            Parent root = loader.load();
            Stage stageInscription= new Stage();
            stageInscription.initStyle(StageStyle.UNDECORATED);
            stageInscription.setScene(new Scene(root, 504, 701 ));
            stageInscription.show();   
            Stage stage = (Stage) reset.getScene().getWindow();
            stage.close();
        } else {
            String tilte = "Captcha";
            String message = "Code incorrect";
            captcha1 =  setCaptcha();
            code.setText("");
        }
    }

    @FXML
    private void reseting(ActionEvent event) {
        captcha1 =  setCaptcha();
             code.setText("");
    }

    @FXML
    private void exit(MouseEvent event) throws IOException {
        Stage stage = (Stage) labelX.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceIA.fxml"));
        Parent root = loader.load();
        Stage stageInscription= new Stage();
        stageInscription.initStyle(StageStyle.UNDECORATED);
        stageInscription.setScene(new Scene(root, 900, 500 ));
        stageInscription.show();  
    }

}
