/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueCours;

import Utils.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter; 
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author aissa
 */
public class MainProg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws WriterException, IOException, NotFoundException {
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
            }
    }
	public static void generate_qr(String image_name,String qrCodeData) {
        try {
            String filePath = "C:\\Users\\asus\\Documents\\ESPRIT\\Projet Integré\\Code\\SPORTify\\QRCODE\\"+image_name+".png";
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
}
 

		
        
                
   
          
                
          
           
        
    
    

