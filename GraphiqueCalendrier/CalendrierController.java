/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueCalendrier;

import Utils.DataSource;
import com.pepperonas.fxiconics.FxIconicsLabel;
import com.pepperonas.fxiconics.MaterialColor;
import com.pepperonas.fxiconics.gmd.FxFontGoogleMaterial;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Yassine Karoui
 */
public class CalendrierController implements Initializable {

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
    private Button btnProfil;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Button btnPreviousMonth;
    @FXML
    private Label lblMonthYear;
    @FXML
    private Button btnNextMonth;
    @FXML
    private GridPane gpMain;
    @FXML
    private Pane pnlOrders;

        LocalDateTime ldtControl;
    Connection conn = DataSource.getInstance().getCnx();
    @FXML
    private AnchorPane fxCalendrier;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ldtControl = LocalDateTime.now();
        loadMonth(ldtControl);
    }    

    int getColumn(LocalDateTime ldt) {
        int i = 0;
        while (ldt.getDayOfWeek() != DayOfWeek.SUNDAY) {
            i++;
            ldt = ldt.plusDays(1);
        }

        return i;
    }

    @FXML
    private void handleBTNMonthChange(ActionEvent event) {
        if (((Button) event.getSource()).getId().equals("leftButtonWithImage")) {
            ldtControl = ldtControl.minusMonths(1);
            loadMonth(ldtControl);
        } else if (((Button) event.getSource()).getId().equals("rightButtonWithImage")) {
            ldtControl = ldtControl.plusMonths(1);
            loadMonth(ldtControl);
        }
    }

    private void loadMonth(LocalDateTime ldt) {
        if (gpMain.getChildren().size() > 0) {
            gpMain.getChildren().clear();
        }

        loadGridPaneFirstRow();
        lblMonthYear.setText(ldt.getMonth() + " " + ldt.getYear());

        LocalDateTime ldtIterator = ldt.minusDays(ldt.getDayOfMonth() - 1);
        ldtIterator.format(DateTimeFormatter.ISO_DATE);

        int control = getColumn(ldtIterator);
        int control2 = 0;
        int i = 0;
        while (ldtIterator.getMonth() == ldt.getMonth()) {
            if (i == 0 || i == 1 && control2 <= control) {
                i = 1;
                control2++;
            } else {
                i = ((control2 - (control + 1)) / 7) + 2;
                control2++;
            }

            Label tempLabel = new Label(Integer.toString(ldtIterator.getDayOfMonth()));

            gpMain.add(createCell(tempLabel, ldtIterator), ldtIterator.getDayOfWeek().getValue() - 1, i);

            ldtIterator = ldtIterator.plusDays(1);
        }
    }

    private void loadGridPaneFirstRow() {
        String[] string = {"Monday", "Tuesday", "Wednesday", "Thrusday", "Friday", "Saturday", "Sunday"};
        for (int i = 0; i < string.length; i++) {
            Label tempLabel = new Label(string[i]);
            GridPane.setHalignment(tempLabel, HPos.CENTER);
            gpMain.add(tempLabel, i, 0);
        }
    }

    private BorderPane createCell(Label label, LocalDateTime ldt) {

        BorderPane cell = new BorderPane();
        label.setOnMouseClicked(e -> {
            try {
                addNewNote(ldt);
            } catch (IOException ex) {
                Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(loadNotesForDate(ldt));
        BorderPane.setAlignment(vbox, Pos.CENTER);
        cell.setCenter(vbox);

        BorderPane.setAlignment(label, Pos.TOP_RIGHT);
        cell.setTop(label);
        cell.getStyleClass().add("cell");

        return cell;
    }

    private List<HBox> loadNotesForDate(LocalDateTime ldt) {
        List<HBox> tempList = new ArrayList();

        String sql = "Select Type_Cours FROM cours WHERE Date = ?";

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql);) {

            Date convertedDatetime = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
            SimpleDateFormat sm = new SimpleDateFormat("YYYY-MM-dd");

            String strDate = sm.format(convertedDatetime);

            pstmt.setString(1, strDate);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("true");
                FxIconicsLabel labelTextDefault
                        = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_cancel)
                                .size(24)
                                .text("")
                                .color(MaterialColor.ORANGE_500)
                                .build();

                String tempNote = rs.getString(1);
                System.out.println(tempNote);
                Label tempLabel = new Label(tempNote);

                labelTextDefault.setOnMouseClicked(e -> {
                    deleteNote(ldt, tempNote);
                    loadMonth(ldt);
                });


                HBox hbox = new HBox();
                hbox.getChildren().add(labelTextDefault);
                hbox.getChildren().add(tempLabel);
                tempList.add(hbox);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tempList;
    }

    private void addNewNote(LocalDateTime ldt) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GraphiqueCours/Home.fxml"));
        Stage stage = new Stage();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(tableViewParent, 450, 450));
        stage.show();
        Image icon = new Image(getClass().getResourceAsStream("logo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("SPORTify-Gestion salle de sport");

    }
    private void deleteNote(LocalDateTime ldt, String note) {
        if (deleteConfirmation(note).get() == ButtonType.OK) {
            String sql = "DELETE FROM cours WHERE Date = ?";
            try (
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                Date convertedDatetime = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
                SimpleDateFormat sm = new SimpleDateFormat("YYYY-MM-dd");

                String strDate = sm.format(convertedDatetime);
                pstmt.setString(1, strDate);

                pstmt.executeUpdate();
      
            } catch (SQLException ex) {
                exceptionDialog(ex.getMessage());
            }
        }
        //Notif
        Notifications n = Notifications.create()
                .title("NOTIFICATION")
                .text(" Cours supprimé !!")
                .position(Pos.TOP_CENTER)
                .hideAfter(Duration.seconds(3));
        n.darkStyle();
        n.show();
    }
    
    

    private void exceptionDialog(String exceptionText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Error!");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        alert.getDialogPane().setExpanded(true);
        alert.showAndWait();
    }

    private Optional<ButtonType> deleteConfirmation(String note) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete course Dialog");
        alert.setHeaderText("Delete course");
        alert.setContentText("Are you sure you want to delete this course!");

        TextArea textArea = new TextArea();
        textArea.setText(note);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setContent(expContent);
        Optional<ButtonType> result = alert.showAndWait();

        ObservableList<String> optionList = FXCollections.observableArrayList();
        return result;
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
}
