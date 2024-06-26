package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import application.DBImageHandler;
import application.NXTVMain;
import application.sceneSwitch;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class MainFrameController implements Initializable{
    @FXML private BorderPane borderPane; // name ng border pane
    @FXML private Button dashboardBtn;
    @FXML private AnchorPane detailsCard; 
    @FXML private Button employeesBtn;
    @FXML private Button imgBtn;
    @FXML private ImageView imgPic; //para sa image sa mainframe
    @FXML private Button inventoryBtn;
    @FXML private Button membersBtn;
    @FXML private Button posBtn;
    @FXML private Button pricesBtn;
    @FXML private Button reportsBtn;
    @FXML private Button settingsBtn;
    @FXML private Button syncBtn;
    @FXML private Button changePassBtn;
    @FXML private Button logoutBtn;
    @FXML private VBox centerPart; //center part ng border pane

    @FXML private ImageView cardUserPic; //pic ng user sa deets card

    //para sa detailsCard
    @FXML private Label userName;
    @FXML private Label fullNameLbl;
    @FXML private Label userTypeLbl;

    private boolean cardVisible = true;
    
 

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	setCenterScenes("/FXML/Dashboard.fxml");

        try { //try catch in case na magkaproblem sa pagseset ng data sa labels
            System.out.println(NXTVMain.localLog.getFirstName());
            System.out.println(NXTVMain.localLog.getFirstName() + " " + NXTVMain.localLog.getMiddleName().charAt(0) + ". " + NXTVMain.localLog.getLastName());
            System.out.println(NXTVMain.localLog.getUserType());

            userName.setText(NXTVMain.localLog.getFirstName());
            fullNameLbl.setWrapText(true);
            fullNameLbl.setText(NXTVMain.localLog.getFirstName() + " " + NXTVMain.localLog.getMiddleName().charAt(0) + ". " + NXTVMain.localLog.getLastName());
            userTypeLbl.setText(NXTVMain.localLog.getUserType());
            imgPic.setImage(DBImageHandler.getUserPhoto(NXTVMain.localLog.getUserID()));
            cardUserPic.setImage(DBImageHandler.getUserPhoto(NXTVMain.localLog.getUserID()));

            dashboardBtn.getStyleClass().add("bold-button");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    //    MemberController mem = new MemberController(borderPane); //pasa ung borderPane sa member controller para may magamit na borderpane don pang setCenter ng scenes
	}
    
    
    @FXML
    private void handleButtonAction(ActionEvent e) throws Exception{
        if (e.getSource() == imgBtn) { //update lang visibility ni deets card
            System.out.println("Visibility: " + cardVisible);
            cardVisible = !cardVisible;
            detailsCard.setVisible(cardVisible);
            detailsCard.toFront();
      
            if (!borderPane.getChildren().contains(detailsCard)) {
                borderPane.getChildren().add(detailsCard);
            }

        }
        else if(e.getSource()  == changePassBtn){  //load lang change pass fxml
            System.out.println("CHANGE PASS HERE");
            sceneSwitch sw = new sceneSwitch();
            sw.setFxmlFile("/FXML/MainFrame/ChangePassword.fxml");
            sw.switchScenes();
        }
        else if(e.getSource()  == logoutBtn){ //balik sa logout
            sceneSwitch sw = new sceneSwitch();
            sw.setFxmlFile("/FXML/LoginPage.fxml");
            sw.switchScenes();
        }
        else if(e.getSource() == dashboardBtn) { //loads dashboard lang
        	setCenterScenes("/FXML/Dashboard.fxml");
            System.out.println("Dashboard");
        }
        else if(e.getSource() == posBtn) { //loads pos lang asasdasd
            setCenterScenes("/FXML/POS.fxml");
            System.out.println("POS");
        }
        else if(e.getSource() == reportsBtn) { //loads reports 
        	  setCenterScenes("/FXML/Reports/Reports.fxml");
            System.out.println("REPORTS");
        }
        else if(e.getSource() == membersBtn) {
        	  setCenterScenes("/FXML/Members/Members.fxml");
              System.out.println("MEMBERS");
        }

    }



    public void setCenterScenes(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent centerContent = loader.load();
            borderPane.setCenter(centerContent);
            detailsCard.toFront();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}//end of class
//TODO: pano ung sa summary, need ma load un sa center ng border pane hahaha
