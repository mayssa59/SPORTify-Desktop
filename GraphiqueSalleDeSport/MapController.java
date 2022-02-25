/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueSalleDeSport;


import Entite.SalleDeSport;
import Service.ServiceSalleDeSport;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MapController implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;
    
    @FXML
    private TextField addressTextField;
    
    private GoogleMap map;
    
    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();
     ServiceSalleDeSport service = new ServiceSalleDeSport();
    SalleDeSport salle = null;

   @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* mapView.addMapInializedListener(this);
        address.setValue(SalleDeSportController.address);
        this.address.bind(addressTextField.textProperty());
        addressTextField.setText(SalleDeSportController.address);*/
       
       mapView.addMapInializedListener(this);
        address.bind(addressTextField.textProperty());
        //salle = service.getAdresse(0);

        //addressTextField.setText(salle.getAdresse());
       
        
    
    }   

  
        @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        
        //Once the map has been loaded by the Webview, initialize the map details.
      
          MapOptions options = new MapOptions();
          LatLong center = new LatLong(36.8099, 10.137307);
                     options.center(center)
                .mapMarker(true)
                .zoom(12)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);

                 map = mapView.createMap(options);
        
        
        //Add a couple of markers to the map.
        MarkerOptions markerOptions = new MarkerOptions();
        LatLong markerLatLong = new LatLong(36.8099, 10.137307);
        markerOptions.position(markerLatLong)
                .title("My new Marker")
                .animation(Animation.DROP)
                .visible(true);

        Marker myMarker = new Marker(markerOptions);

       /* MarkerOptions markerOptions2 = new MarkerOptions();
        LatLong markerLatLong2 = new LatLong(36.8587351, 10.137307);
        markerOptions2.position(markerLatLong2)
                .title("My new Marker")
                .visible(true);

        Marker myMarker2 = new Marker(markerOptions2);*/

        map.addMarker(myMarker);
        //map.addMarker(myMarker2);

        //Add an info window to the Map.
        InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content("<h4>Here</h4>")
                .position(center);

        InfoWindow window = new InfoWindow(infoOptions);
        window.open(map, myMarker);
        
    }
  
    
    @FXML
    public void addressTextFieldAction(ActionEvent event) {
        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong = null;
          System.out.println("lol");
            if( status == GeocoderStatus.ZERO_RESULTS) {
                 System.out.println("lool");
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                 System.out.println("loool");
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
             System.out.println("looool");
              map.setCenter(latLong);
             //Add a couple of markers to the map.
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLong)
                .title("My new Marker")
                .animation(Animation.DROP)
                .visible(true);

        Marker myMarker = new Marker(markerOptions);
        map.addMarker(myMarker);

           

        });
    }
    
}
