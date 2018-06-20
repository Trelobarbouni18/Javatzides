import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartScreenController extends MainController implements Initializable {
	@FXML
	private Label usernameMenu;
	@FXML
	private Label balanceMenu;
	@FXML
	private Label welcome;
	@FXML
	private Label fineLabel;
	@FXML
	private Hyperlink payNow;
	@FXML private Pane payNowPane;
	@FXML
	private Label warningLabel;
	@FXML
	private Button okButton;
	private Card falseProduct;

	public void onClickedTicket(ActionEvent actionEvent) throws IOException {
		Stage primaryStage = getStageFromEvent(actionEvent);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Ticket_Panel.fxml"));
		Parent root = null;
		root = loader.load();

		Scene scene = new Scene(root);

		// setUserData so that the fxml file of the loader can be retrieved
		scene.setUserData(loader);

		primaryStage.setScene(scene);
		primaryStage.setTitle("ThessBus: Ticket Purchase");
		primaryStage.show();
	}

	public void onClickedCard(ActionEvent actionEvent) throws IOException {
		Stage primaryStage = getStageFromEvent(actionEvent);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Card.fxml"));
		Parent root = null;
		root = loader.load();

		Scene scene = new Scene(root);

		// setUserData so that the fxml file of the loader can be retrieved
		scene.setUserData(loader);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ThessBus: Card Purchase");
		primaryStage.show();
	}

	public void onClickPayNow(ActionEvent e) throws IOException {
		ArrayList<String> choices = new ArrayList<>();
		for (Fine f : Main.loginUser.getFines()) {
			if(f.isPaid() == false)
				choices.add("Date Time: " + f.getDate_time() + ", Bus: " + f.getBus() + ", Price: "
							+ Double.toString(f.getPrice()));
		}
		
		ChoiceDialog<String> dialog = new ChoiceDialog<>("������� ���������", choices);
		dialog.setTitle("Pay Fine");
		dialog.setHeaderText(null);
		dialog.setContentText("������� �� ��������" + System.lineSeparator() +"��� ������ �� ���������: ");
		Optional<String> result = dialog.showAndWait();
		
		if (result.isPresent()) {
			for (Fine f : Main.loginUser.getFines()) {
				if (result.get().contains(f.getDate_time())) {
					if (f.getPrice() <= Main.loginUser.getBalance()) {
						f.finePaid();
						Main.loginUser.reduceBalance(f.getPrice());
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Alert");
						alert.setHeaderText(null);
						alert.setContentText("�� �������� ��� ���������");
						alert.showAndWait();
						
						//initialize();
					} 
					else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Alert");
						alert.setHeaderText(null);
						alert.setContentText("�� �������� ��� ��� �������!");
						alert.showAndWait();
						break;
					}

				}
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		usernameMenu.setText(Main.loginUser.getUsername());
		balanceMenu.setText(Double.toString(Main.loginUser.getBalance()));
		welcome.setText("���� ���, " + Main.loginUser.getUsername() + "!");
		
		fineLabel.setText("   ����� (" + Main.loginUser.countUnpaidFines() + ") ��������/� ��������/�" 
				/*") ��������/� ��� ���������: " + Double.toString(Main.loginUser.calculateTotalFines()) + "�"*/);
		
		if(Main.loginUser.countUnpaidFines() == 0) {
			payNow.setMouseTransparent(true);
			payNow.setEffect(new GaussianBlur());
			fineLabel.setText("   ��� ����� �������� ���� �������");
		}
		
		if(Main.loginUser.countMultiWayNotValidatedTickets() > 0) {
			//label ���������� - ������ ��� ����� ��������
		}
		ArrayList<Product> products = Main.loginUser.getProducts();
		for (Product product : products) {
			if(product instanceof Card /*&& ((Card) product).isValid() == false*/ && ((Card) product).isFlag() == false) {
				warningLabel.setVisible(true);
				okButton.setVisible(true);
				falseProduct = ((Card) product);
			}
				
		}
		
	}
	public void OnClickedOk(ActionEvent actionEvent)
	{
		warningLabel.setVisible(false);
		okButton.setVisible(false);
        falseProduct.setFlagTrue();
	}
		
}
