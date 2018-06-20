
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginScreenController extends MainController implements Initializable {
	@FXML
	public Button loginButton;
	@FXML
	public TextField usernameField;
	@FXML
	public PasswordField passwordField;
	@FXML
	private Hyperlink visitorHyperlink;
	
	public void onClickedLogin(ActionEvent e) throws IOException {

		Stage primarystage = Main.getStagefromEvent(e);
		 
		if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Alert");
			alert.setHeaderText(null);
			alert.setContentText("��� ����� ����������� ������/� ��� �� ����� �����!");
			Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                alert.close();
            }
		} 
		else {
			if (usernameField.getText().contains("inspector")) {
				Main.loginIns = (TicketInspector) FileManager.searchUser(usernameField.getText(),
						passwordField.getText(), "Inspectors.dat");
			}
			else
				Main.loginUser = (Passenger) FileManager.searchUser(usernameField.getText(), passwordField.getText(),
						"Users.dat");

			if (Main.loginUser != null) {
				primarystage.close();
				Main.loginUser.setProducts(FileManager.getProducts(Main.loginUser.getUserNum(), "Products.dat"));
				Main.loginUser.setFines(FileManager.getFines(Main.loginUser.getUserNum(), "Fines.dat"));
				for (Product product : Main.loginUser.getProducts()) {
					System.out.println(product.getDate_time());
				}
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScreen.fxml"));
				Parent root = null;
				root = loader.load();
				Scene scene = new Scene(root);
				
				//setUserData so that the fxml file of the loader can be retrieved
	    		scene.setUserData(loader);
				
				stage.setScene(scene);
				stage.setTitle("ThessBus: Home");
				stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

					@Override
					public void handle(WindowEvent arg0) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirmation Alert");
						alert.setHeaderText(null);
						alert.setContentText("��� ������� �� �����;");

						Optional<ButtonType> result = alert.showAndWait();

						if (result.get() == ButtonType.OK) {
							Passenger temp = new Passenger(Main.loginUser.getUsername(),
										Main.loginUser.getPassword(), Main.loginUser.getEmail(),
										Main.loginUser.getCardNum(), Main.loginUser.getId(),
										Main.loginUser.getPhoneNum(), Main.loginUser.getPassport(),
										Main.loginUser.getBalance(), Main.loginUser.getUserNum());

							System.out.println(Main.loginUser.getUserNum() + " " + temp.getUserNum());
							FileManager.updatePassenger(Main.loginUser, "Users.dat", temp);
							FileManager.updateFines(Main.loginUser.getUserNum(), Main.loginUser.getFines(), "Fines.dat");
							
							Main.loginUser = null;
						} else
							arg0.consume();
						
					}
				});
				stage.show();
				
			}
			else if (Main.loginIns != null) {
				primarystage.close();
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Inspector.fxml"));
				Parent root = null;
				root = loader.load();
				Scene scene = new Scene(root);
				
				//setUserData so that the fxml file of the loader can be retrieved
	    		scene.setUserData(loader);
				
				stage.setScene(scene);
				stage.setTitle("ThessBus: Inspector");
				stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

					@Override
					public void handle(WindowEvent arg0) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirmation Alert");
						alert.setHeaderText(null);
						alert.setContentText("��� ������� �� �����;");

						Optional<ButtonType> result = alert.showAndWait();

						if (result.get() == ButtonType.CANCEL) {
							arg0.consume();
						}
						
						Main.loginIns = null;
					}
				});
				stage.show();
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Alert");
				alert.setHeaderText(null);
				alert.setContentText("Error. Username or password doesn't match.");
				Optional<ButtonType> result = alert.showAndWait();
	            if (result.get() == ButtonType.OK){
	                alert.close();
	            }
			}
		}
	}

	public void onClickedSignUp(ActionEvent actionEvent) throws Exception {
		Stage primaryStage = MainController.getStageFromEvent(actionEvent);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterScreen.fxml"));
		Parent root = null;
		root = loader.load();

		Scene scene = new Scene(root);

		// setUserData so that the fxml file of the loader can be retrieved
		scene.setUserData(loader);

		primaryStage.setScene(scene);
		primaryStage.setTitle("ThessBus: Sign Up");
		primaryStage.show();
	}

	public void onHyperlinkVisitor(ActionEvent actionEvent) throws Exception {
		Stage primaryStage = MainController.getStageFromEvent(actionEvent);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("VisitorScreen.fxml"));
		Parent root = null;
		root = loader.load();

		Scene scene = new Scene(root);

		// setUserData so that the fxml file of the loader can be retrieved
		scene.setUserData(loader);

		primaryStage.setScene(scene);
		primaryStage.setTitle("ThessBus: Visitor");
		primaryStage.show();
	}
	
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
