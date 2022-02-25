/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueUser;

import Service.ServiceAdministrateur;
import Service.ServiceUtilisateur;
import Utils.SendEmail;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * FXML Controller class
 *
 * @author Yassine Karoui
 */
public class InterfaceIAController implements Initializable {
    ServiceUtilisateur SU = new ServiceUtilisateur();
    ServiceAdministrateur SA = new ServiceAdministrateur();
    private static  int id;
    private static String surnom;
    @FXML
    private Button btnCreeUnCompte;
    @FXML
    private Button btnSeConnecter;
    @FXML
    private Button btnMdpOublie;
    @FXML
    private TextField tfPseudo;
    @FXML
    private PasswordField tfMdp;
    @FXML
    private Label tfIncorret;
    @FXML
    private Label labelCorrect;
    @FXML
    private Label labelMail;
    @FXML
    private Label labelMail1;
    @FXML
    private Label labelX;
    @FXML
    private Button btnFb;
    
    public static int getId() {
        return id;
    }   
    
     public static void setId(int id) {
        InterfaceIAController.id = id;
    }
     
    public static String getSurnom() {
        return surnom;
    }

    public static void setSurnom(String surnom) {
        InterfaceIAController.surnom = surnom;
    }
    /**
     * Initializes the controller class.
     */   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void FermerFenetre(MouseEvent event) {
        Stage stage = (Stage) labelX.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void CreeUnCompte(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Captcha.fxml"));
        Parent root = loader.load();
        Stage stageInscription= new Stage();
        stageInscription.initStyle(StageStyle.UNDECORATED);
        stageInscription.setScene(new Scene(root, 449, 437 ));
        stageInscription.show(); 
        Stage stage = (Stage) labelX.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void SeConnecter(ActionEvent event) throws SQLException, IOException, InterruptedException {
        tfIncorret.setText("");        
        labelCorrect.setText("");
        labelMail.setText("");
        labelMail1.setText("");
        if (tfPseudo.getText().isEmpty() == false && tfMdp.getText().isEmpty() == false ){
            int test = SU.authentification(tfPseudo.getText(), tfMdp.getText());
            int test1 = SA.authentification(tfPseudo.getText(), tfMdp.getText());
            if (test == 0 && test1 == 0)
                tfIncorret.setText("Pseudo ou mot de passe incorrect");
            else if (test != 0){
                if(SU.getRoleById(test).equals("Client")){
                    id = test;
                    Stage stage = (Stage) tfIncorret.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilClient.fxml"));
                    Parent root = loader.load();
                    Stage stageInscription= new Stage();
                    stageInscription.initStyle(StageStyle.UNDECORATED);
                    stageInscription.setScene(new Scene(root, 1050, 576));
                    stageInscription.show(); 
                    
                } else if (SU.getRoleById(test).equals("Coach")){
                    id = test;
                    Stage stage = (Stage) tfIncorret.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilClient.fxml"));
                    Parent root = loader.load();
                    Stage stageInscription= new Stage();
                    stageInscription.initStyle(StageStyle.UNDECORATED);
                    stageInscription.setScene(new Scene(root, 1050, 576));
                    stageInscription.show(); 
                    
                    
                } else if (SU.getRoleById(test).equals("Proprietaire salle de sport")){
                    Stage stage = (Stage) tfIncorret.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueSalleDeSport/SalleDeSport.fxml"));
                    Parent root = loader.load();
                    Stage stageInscription= new Stage();
                    stageInscription.initStyle(StageStyle.UNDECORATED);
                    stageInscription.setScene(new Scene(root, 1050, 576));
                    stageInscription.show(); 

                }
                } else if (test1 != 0){
                labelCorrect.setText("Bienvenu Admin");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilClient.fxml"));
                Parent root = loader.load();
                Stage stageInscription= new Stage();
                stageInscription.initStyle(StageStyle.UNDECORATED);     
                stageInscription.setScene(new Scene(root, 1050, 576));
                stageInscription.show();
            }
                
        } else if(tfPseudo.getText().isEmpty() == true && tfMdp.getText().isEmpty() == true)
            tfIncorret.setText("Entrer votre Pseudo et mot de passe s'il vous plait");
          else if(tfPseudo.getText().isEmpty() == true)
            tfIncorret.setText("Entrer votre Pseudo s'il vous plait"); 
          else if(tfMdp.getText().isEmpty() == true)
            tfIncorret.setText("Entrer votre mot de passe s'il vous plait");   
    }

    @FXML
    private void motDePasseOublie(ActionEvent event) throws SQLException, IOException {
        tfIncorret.setText("");        
        labelCorrect.setText("");
        labelMail.setText("");
        labelMail1.setText("");
        if (tfPseudo.getText().isEmpty() == false){
            if (SendEmail.Send(tfPseudo.getText()) == 1){
                surnom = tfPseudo.getText();
                labelMail.setText("Email envoyé");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CodeVerification.fxml"));
                Parent root = loader.load();
                Stage stageInscription= new Stage();
                stageInscription.initStyle(StageStyle.UNDECORATED);
                stageInscription.setScene(new Scene(root, 600, 400));
                stageInscription.show(); 
                Stage stage = (Stage) labelX.getScene().getWindow();
                stage.close();            }
            else
                labelMail1.setText("Pseudo incorrect");
        } else
            tfIncorret.setText("Entrer votre Pseudo"); 
    }

    

    
    /*
    private String appId = "751344368899370";
    private String appSecret = "0dbd22ca455aa38b91caff10cdcac2c9";
    private String redirectUrl = "https://localhost/";
    private String state = "9814";
    private String redirectUrlEncoded = "https%3A%2F%2Flocalhost%2F";
    private String code = "AQDQJ2A9Jq2RoC2ilL518EX4GHXqgBsB__3WhYxITKCUsJSSejYun7TyLCL2LsHxcVJnPnbVHDUhkf_MNt2FXFli_DllH2bm1QR4Evv_0VKulMeP5LhBCuZpVw5JAc3Gyph_HTXI2R9730o9ttm6DdagWJ8sHNWI6BkfFPxr2OJmLokJqi2QSuFV93SloSQersM4wSBkommCNDZt-kax-egGGRHN_JZTLwhvJFf5kC8K24vA9sJYxMiNeXG68Lf9sb8iw-MsdkgnMHwKOfi0h8_h0VhHdg3p-V0VrE6oanVfR8-jun5VIPGo9jyqcmhw_Ppjv2UyFElvSsv8fszdNpXD";
    private String authentification = "https://www.facebook.com/v8.0/dialog/oauth?client_id="+appId+"&redirect_uri="+redirectUrl+"&state"+state;
    private String graph = "https://graph.facebook.com/v8.0/oauth/access_token?client_id="+appId+"&redirect_uri="+redirectUrl+"&client_secret="+appSecret+"&code=";
    
 
    @FXML
    private void SeConnecterAvecFacebook(ActionEvent event) {
        
       WebView webView = new WebView();
       WebEngine eg = webView.getEngine();
       eg.load(authentification);
       Pane wView = new Pane();
       wView.getChildren().add(webView);
       Stage stage = new Stage();
       stage.initModality(Modality.APPLICATION_MODAL);
       stage.setScene(new Scene(wView, 818 , 654));
       stage.show();
       
       
       
       
       eg.locationProperty().addListener((obs,oldlocation,newlocation) ->{
           if(newlocation != null && newlocation.startsWith("https://localhost/")){
                int codeOffset= newlocation.indexOf("code=");
                String code = newlocation.substring(codeOffset + "code=".length());
                //graph += code;
                DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
                FacebookClient.AccessToken accessToken = facebookClient.obtainUserAccessToken(appId, appSecret, "https://localhost/", code);
                
                String access_token = accessToken.getAccessToken();
                
                FacebookClient fbClient = new DefaultFacebookClient(access_token,Version.LATEST);
                fbClient.createClientWithAccessToken(access_token);
                JsonObject profile_pic = fbClient.fetchObject("me/picture", JsonObject.class, Parameter.with("redirect", "false"));
                User user = fbClient.fetchObject("me", User.class);
                
                int si = profile_pic.toString().indexOf("url\":\"");
                int ei = profile_pic.toString().indexOf("\",\"");
                String profile_url = profile_pic.toString().substring(si+6, ei);
                
                labelCorrect.setText(user.getName());
                System.out.println(user.getFirstName());

                
                stage.close();
                
                
           }
       }
       );
       

       
        
    }

    */
    
    
    @FXML
    private void SeConnecterAvecFacebook(ActionEvent event) {
        String appId = "751344368899370";
        String domain = "https://localhost/";
        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=email,public_profile";
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver= new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
        
        while(true){
            if (!driver.getCurrentUrl().contains("facebook.com")){
                String url = driver.getCurrentUrl();
                accessToken = url.replaceAll(".*#access_token=(.+)&.*","$1");
                driver.quit();
                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                User user = fbClient.fetchObject("me", User.class);
                labelCorrect.setText(user.getName());     
                
            }
            
        }
    }
    
    
    
}
