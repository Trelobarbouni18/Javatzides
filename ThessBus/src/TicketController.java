import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.google.zxing.WriterException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicketController extends MainController implements Initializable {

	@FXML
	private Button oneWayNormal;
	@FXML
	private Button oneWayReduced;
	@FXML
	private Button twoWayNormal;
	@FXML
	private Button twoWayReduced;
	@FXML
	private Button ThreeWayNormal;
	@FXML
	private Button ThreeWayReduced;
	@FXML
	private Button FourWayNormal;
	@FXML
	private Button FourWayReduced;
	@FXML
	private Button AirportNormal;
	@FXML
	private Button AirportReduced;
	@FXML
	private Pane buttonsPane;
	@FXML
	private VBox navBarVBox;
	@FXML
	private Hyperlink signOutHyperlink;
	@FXML
	private ComboBox<String> busesComboBox;
	@FXML
	private Label usernameMenu;
	@FXML 
	private Label balanceMenu;

	private String bus = "";
	private Passenger owner = Main.loginUser;
	private double cost;
	

	public void TicketData(Ticket newTicket) throws WriterException, IOException{	
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("����� �������� ��� ������ �� ����������;");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				if (owner.getBalance() < cost) {
					Alert alert1 = new Alert(AlertType.ERROR);
					alert1.setTitle("Alert");
					alert1.setHeaderText(null);
					alert1.setContentText("��� ����� ������ �������");
					alert1.showAndWait();
				}else {
					Alert newalert = new Alert(AlertType.CONFIRMATION);
					newalert.setTitle("Alert");
					newalert.setHeaderText(null);
					newalert.setContentText("������! �� ��������� ��� ����������!");
					newalert.showAndWait();
					
					owner.reduceBalance(cost);
					owner.addProduct(newTicket);
					
					Passenger temp = new Passenger(Main.loginUser.getUsername(),
					Main.loginUser.getPassword(), Main.loginUser.getEmail(),
					Main.loginUser.getCardNum(), Main.loginUser.getId(),
					Main.loginUser.getPhoneNum(), Main.loginUser.getPassport(),
					Main.loginUser.getBalance());

					FileManager.updatePassenger(Main.loginUser, "Users.dat", temp);
					FileManager.insertProducts(Main.loginUser.getUsername(), Main.loginUser.getProducts(),
							"Products.dat");
				}
			} 
			else {
			    // ... user chose CANCEL or closed the dialog
			}
	}
		

	public void onClickedOneWay(ActionEvent e) throws WriterException, IOException {
		if (bus != "") {
			cost = 0.5 * owner.getCheck();
			Ticket newTicket = new Ticket(cost, owner, "�����", 1, bus);
			TicketData(newTicket);
		} else
			noBusSelectedAlert();
	}

	public void onClickedTwoWay(ActionEvent e) throws WriterException, IOException {
		if (bus != "") {
			cost = 0.6 * owner.getCheck();
			Ticket newTicket = new Ticket(cost, owner, "������", 2, bus);
			TicketData(newTicket);
		} else
			noBusSelectedAlert();
	}
	
	public void onClickedThreeWay(ActionEvent e) throws WriterException, IOException {
		if (bus != "") {
			cost = 0.8 * owner.getCheck();
			Ticket newTicket = new Ticket(cost, owner, "�������", 3, bus);
			TicketData(newTicket);
		} else
			noBusSelectedAlert();
	}
	
	public void onClickedFourWay(ActionEvent e) throws WriterException, IOException {
		if (bus != "") {
			cost = 1.0 * owner.getCheck();
			Ticket newTicket = new Ticket(cost, owner, "���������", 4, bus);
			TicketData(newTicket);
		} else
			noBusSelectedAlert();
	}
	

public void onClickedAirport(ActionEvent e)  throws WriterException, IOException {

		if (bus != "") {
			cost = 1.0 * owner.getCheck();
			Ticket newTicket = new Ticket(cost, owner, "����������", 1, bus);
			TicketData(newTicket);
		} else
			noBusSelectedAlert();

	}
	
	
	public void comboBoxChoice(ActionEvent actionEvent) {
		bus = busesComboBox.getValue().substring(0, 2);
		bus = bus + ((busesComboBox.getValue().substring(2, 3).equals("�")) ? "�" : "");
	}

	public void noBusSelectedAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Alert");
		alert.setHeaderText(null);
		alert.setContentText("����� ������� ���������!");
		alert.showAndWait();
	}

	/*
	 * public void onClickedOneWayRedused(ActionEvent e) {
	 * System.out.println("HEY"); }
	 * 
	 * public void onClickedTwoWayRedused(ActionEvent e) {
	 * System.out.println("HEY"); }
	 */

	/*
	 * public void clickedButton(ActionEvent e) {
	 * 
	 * Ticket ticket = new Ticket(null, 1.0, owner, null, null, null, 1, 12, null);
	 * 
	 * if(e.getSource().equals(oneWayNormal)) { ticket.setPrice(1);
	 * ticket.setNo_of_routes(1); } else if(e.getSource().equals(oneWayReduced)) {
	 * ticket.setPrice(0.5); ticket.setNo_of_routes(1); } else
	 * if(e.getSource().equals(twoWayNormal)) { ticket.setPrice(1.20);
	 * ticket.setNo_of_routes(2); } else if(e.getSource().equals(twoWayReduced)) {
	 * ticket.setPrice(0.60); ticket.setNo_of_routes(2); }
	 * 
	 * int reply = JOptionPane.showConfirmDialog(null,
	 * "����� �������� ��� ��� �� ����������;", "Close?",
	 * JOptionPane.YES_NO_OPTION); if (reply == JOptionPane.YES_OPTION) {
	 * if(owner.getBalance() >= ticket.getPrice()) {
	 * owner.reduceBalance(ticket.getPrice()); owner.addProduct(ticket); } else
	 * JOptionPane.showMessageDialog(null, "����� ����� ������ ", null,
	 * JOptionPane.WARNING_MESSAGE); }
	 * 
	 * }
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		busesComboBox.setPromptText("���������");
		busesComboBox.setItems(
				FXCollections.observableArrayList("01: �.�. ��������� - ����������� - ���. ������������ - �.�.�.�.",
						"01�: �.�. ��������� - ����������� - ���. ������������ - �.�.�.�. ���� �����������",
						"02: �.�. ���� - �.�. ������� ���� ��������", 
						"02�: �.�. ���� - �.�. ������� ���� ������������ �.�.�.",
						"03: �.�. ���� - �.�. �������",
						"04�: �������� - �.�.�.�. ���������� - ���������",
						"04�: �.�.�.�. ���������� - �������� - ���������",
						"04�: ��������� - �.�.�.�. ���������� - ��������� �����" ,
						"05: �.����� - ���������",
						"06: ��������� - ���������",
						"07: ��.������� - ������������",
						"08: ���� - �.�.�.�. ���� ��������",
						"09: �.�. ������� - ����������",
						"09�: �.�. ������� - ���� ��� �� ����������",
						"09�: �.�. ������� - ���������� �.�.�.�",
						"10: �������� - �.�. �������",
						"11: ������ - �.�. �������",
						"11�: ������ - �.�. ������� ������� ����������",
						"12: �.�.�.�. - ���� ������",
						"14: ��� ������ - �.�. �������",
						"14�: ��� ������ - �.�. ������� ���� �������",
						"15: ������� ��������� - �������� ������",
						"15�: ������� ��������� - ��������",
						"16: ������������� - �������� ������",
						"17: ��������� - �.�. �������",
						"18: ��.��������� - ��������",
						"19: ��������� �������� - �.�. �������",
						"19�: �.�. ������� - ��������� ��������",
						"20: �������� - ������������",
						"21: ������� - ������������",
						"21�: ������� - ������������ ���� �����������",
						"22: ��� ����",
						"23: �.�. ������� - ������",
						"24: ��. ���������� - ����� ������",
						"25: ������� - ���������",
						"25A: ������� - ����� ���������",
						"26: �������� - ��.����������",
						"27: ����������� - ������������",
						"27�: ����������� - ������������ - �.�.�.�.",
						"27�: ����������� - ������������ - �������� - ������",
						"28�: ���. ������������ - 424 �.�.�.�. - ������������",
						"28�: 424 �.�.�.�. - ���. ������������ - �.�. ��������� - �����������",
						"29: ������� - ������������",
						"29�: ������� - ������������ ���� ���������",
						"30: ��������� - �������", 
						"31: �������� - ����", 
						"32: �.��������� - ������������",
						"32�: �.��������� - ������������ ���� ������������",
						"33: ��.����������� - ���������",
						"33�: ��.����������� - ��������� - �������� ������ ���������",
						"34: �.��������� - ������������",
						"35: ������� - ���������",
						"36: �������� - �.�.�.�. ���������� - ������ - �.�. �.�.�.�",
						"36�: �������� - ���� ����������� - ������ - �.�. �.�.�.�",
						"36�: �������� - �.�.�.�. ���������� - ������ - ���� �����������",
						"36�: �������� - �.�.�.�. ���������� - ������ - �.�. �.�.�.� - ���� �����������",
						"36�: �������� - �.�.�.�. ���������� - ���� ����������� - ������ ",
						"36�: �������� - �.�.�.�. ���������� - ������ - �.�.�.� ",
						"36�: �������� - ������",
						"36�: �������� - �.�.�.�. ���������� - ������ - ���� �����������",
						"36�: �������� - �.�.�.�. ����������",
						"36�: �������� - ������ - ���� �����������",
						"37: �.�. ������� - ��������",
						"38: �.�. ������� - ��� ��������",
						"39: ������� - ����������",
						"39�: ������� - ���������� - �������� ������ ���������",
						"40: �.�. ������� - ��������",
						"40�: �.�. ������� - �������� ���� �.�.�.�.",
						"40�: �.�. ������� - �������� ���� �����������",
						"42: �������� - ���������� �������",
						"42�: ���������� ������� ��� ��������",
						"42�: ���������� ������� - �����������",
						"43: ������ �������",
						"45: �.�.�.�. ��������� - �.�.�.�. ���������� - ������",
						"45�: �.�.�.�. ��������� - �.�.�.�. ����������",
						"45�: �.�.�.�. ��������� - ������ - �.�.�.�. ����������",
						"50: ����������� ������",
						"51: �.�. ������� - ������",
						"51�: ������ - ���������",
						"52: �.�. ������� - �.�.�.�. - ������",
						"53: ������ ������",
						"54: �.�. ������� - �����",
						"54�: �.�. ������� - ����� ���� �.�. ��.���������",
						"55: ����������� - �����������",
						"55�: ����������� - ��������",
						"55�: ����������� - ������ ��������",
						"55�: ����������� - �������",
						"55�: ����������� - ����� ���� ��.��",
						"55�: ����������� - ����� - ����������",
						"55�: ����������� - ���������� - �����",
						"55�: ����������� - ����������",
						"56: �.�. ������� - �����������",
						"56�: �.�. ������� - ����������� - ��������",
						"57: ������������ - ����������� - �/�� ��������",
						"58: ��������� - �������� - �/�� ��������",
						"59: ������ ������",
						"59�: �������� ������ - �������",
						"59�: �������� ������",
						"59�: ������ ������ - ����������",
						"60�: �������� ��������� �����������(�����/�����)",
						"60�: �������� ��������� �����������(�.��������)",
						"60�: �������� - ����� - �.�. ����",
						"61: ��������� - �����������",
						"61�: ��������� - ����������� - ������",
						"64: �.�. ������� - ������",
						"64�: ���������  - ������ ������� �����",
						"64�: ���������  - ������ ����������� ������",
						"64�: �.�. ������� - ������ ������",
						"64�: �.�. ������� - ������ ������� ����� ������",
						"66: �������� - �����",
						"67: �.�. ���� - ������",
						"67�: ������ - ������������ - ����",
						"67�: �.�. ���� - ������ - ������� ������������",
						"68�: �.�. ���� - ������� ������������ - ������������",
						"68�: �.�. ���� - ���� - ������",
						"69�: �.�. ���� - �������",
						"69�: �.�. ���� - ������� - ������� - �.�.�.",
						"69�: ���� - ������� - ��������",
						"69�: ���� - ������� - �.�.�. - ��������",
						"69�: ���� - ������� - �.�.�. - �������",
						"72: �.�. ���� - �.���������",
						"72�: �.�. ���� - �.��������� ���� ������������",
						"72�: �.�. ���� - ���������� - �.��������� ������ ������",
						"76: �.�. ���� - ����������",
						"76�: ���������� - �.���������",
						"76�: �.�. ���� - ���������� - �.�.�. - �.�.�.",
						"77: �.��������� - �������",
						"77�: �.��������� - ������� - �������� - ������ - �.�.�. - �.�.�.",
						"77�: �.��������� - ������� ���� �.�.�. - �.�.�.",
						"77�: �.��������� - ������� - �������� - ������",
						"77�: �.��������� - ������� ���� �.�.�.",
						"79: �.�. ���� - ����������",
						"79�: �.�. ���� - ���������� - �.�.�.�. ����������",
						"79�: �.�.�.�. ���������� - ���������� - �.�. ����",
						"80: �.�.�.�. - �������",
						"80�: �.�.�.�. - ������� ���� ����������",
						"80�: ������� - �.�.�.�. ���� ����������",
						"80�: �.�.�.�. - ������� ���� ������",
						"80�: �.�.�.�. - ������� ����� - ���������",
						"81: �.�.�.�. - ��������",
						"81�: �.�.�.�. - ��.��������� - ��������",
						"81�: �.�.�.�. - ��.��������� - �.�.�.�.",
						"81�: ������� - ��������",
						"81�: ��������� - �.�.�.�.",
						"82�: ���������� - ����������",
						"82�: ���������� - ����������",
						"82�: �.�.�.�. - ���������",
						"82�: ��������� - �.�.�.�.",
						"82�: �.�.�.�. - ���������",
						"82�: ��������� - �.�.�.�.",
						"83: �������� - ���/���� (������)",
						"83�: �������� - ���/���� (������� - �������� - ���/����)",
						"83�: �������� - ���/���� (������� - ������ - ���/����)",
						"83�: �������� - ���/���� ���� ������� �������",
						"83�: ����������� - �������� (�������� ����������� ���� ��� �������)",
						"83�: �������� - ���/���� ���� �.�.�.",
						"83�: �������� - ���/���� (�������� - �������� - ���/����",
						"84�: ���� - ����������� - ������ - ����",
						"84�: ���� - ������ - ����������� - ����",
						"85: ���/���� - �������������",
						"85�: ���/���� - ������",
						"85�: ���/���� - ������ - ���������",
						"85�: ���/���� - ������ - �������������",
						"85�: �������� - �������������",
						"85�: ���/���� - ������� - ������������",
						"85�: ���/���� - ������� - ������",
						"85�: ���/���� - �������� - �������������",
						"85�: ���/���� - ������ - ������� - ���������",
						"85�: ���/���� - ��������� - �������������",
						"86: ���/���� - ����������",
						"86�: ��������� - ����������",
						"86�: ��������� - ����������",
						"86�: ������ - ��������� - ����������",
						"86�: ������ - �������� - ����������",
						"86�: �������� - ��������� - ��������� - ����������",
						"86�: ������ - ��������� - ����������",
						"86�: �������� - ��������� - ����������",
						"86�: �������� - ��������� - ����������",
						"86�: ������ - �������� - ��������� - ����������",
						"86�: �������� - ����������",
						"86�: ���������� - ������ - �����������",
						"86�: �������� - ��������� - �������� - ����������",
						"86�: �������� - ��������� - ����������",
						"86�: ���������� - ��������� - ������ �������",
						"87�: ����� - ��������",
						"87�: ���� - �������� - ��������� - ��������� - ������",
						"87�: �������� - ��.�������",
						"87�: ��������� - ��������",
						"87�: �������� - ��������� - ������",
						"87�: ���� - �������� - ��������� - ������� - �����",
						"87�: ���� - �������� - ��.�������� - ���������� - ��������� - �����",
						"87�: �.�. �.�.�.�. - �������� - ��������� - �����",
						"87�: ���� - �������� - ����� - ��.������� - ����������",
						"87�: ���� - �������� - ����� - ��.�������",
						"87�: ���� - �������� - ��������� - ���������",
						"87�: ���� - �������� - ����� - ���������",
						"87�: ���� - �������� - ����� - ��������� - ��.�������",
						"87�: ���� - �������� - ��������� - �������",
						"87�: �������� - ��.������� - ����������",
						"88: ���� - �������� ���� ����",
						"88�: ���� - ������ - ��������",
						"88�: ���� - �������� - ������",
						"88�: ���� - �������� - ������� ���� ����",
						"88�: ���� - �������� - ������� ��� ������ ����������",
						"88�: ���� - �������� ��� ������ ����������",
						"88�: ���� - �������� ��� ������� - ����",
						"88�: ���� - �������� ��� ������� - ������ ����������",
						"89�: �.�.�.�. - ��������",
						"89�: �.�.�.� - �����������",
						"89�: ����������� - �.�.�.�",
						"89�: �/��� ���������� - �����������",
						"90: �������� - �������",
						"90�: ������� - �������",
						"90�: ����������� - �������",
						"90�: ����������� - ��������",
						"90�: ������� - ���������",
						"91�: �������� - �������",
						"91�: �������� - �������(�������)",
						"91�: �������� - ������� - ������",
						"91�: �������� - ������ - ��������(�������� - ����������)",
						"91�: �������� - �������� - �������� ",
						"91�: �������� - �������� �����������",
						"91�: �������� - ����������",
						"91�: �������� - �������",
						"91�: ������� - ��������",
						"92: �������� - ����� - �������",
						"92�: �������� - �����",
						"92�: �������� - ��������� ���������",
						"92�: �������� - ������" ,
						"�1:���� - ����������",
						"N1A: ���� - ���������� ��������� ���� �.�. �.�.�.�.",
						"�1: ���� - ���������� ���������"));

		if (owner != null) {
			if (owner.getCheck() == 1) {
				oneWayNormal.setMouseTransparent(true);
				twoWayNormal.setMouseTransparent(true);
				ThreeWayNormal.setMouseTransparent(true);
				FourWayNormal.setMouseTransparent(true);
				AirportNormal.setMouseTransparent(true);
			} else {
				oneWayReduced.setMouseTransparent(true);
				twoWayReduced.setMouseTransparent(true);
				ThreeWayReduced.setMouseTransparent(true);
				FourWayReduced.setMouseTransparent(true);
				AirportReduced.setMouseTransparent(true);
			}
			
			usernameMenu.setText(Main.loginUser.getUsername());
			balanceMenu.setText(Double.toString(Main.loginUser.getBalance()));
		}

	}

	public VBox getNavBarVBox() {
		return navBarVBox;
	}

	public Hyperlink getSignOutHyperlink() {
		return signOutHyperlink;
	}

	public Button getOneWayNormal() {
		return oneWayNormal;
	}

	public Button getOneWayReduced() {
		return oneWayReduced;
	}

	public Button getTwoWayNormal() {
		return twoWayNormal;
	}

	public Button getTwoWayReduced() {
		return twoWayReduced;
	}
	
	

	public Button getThreeWayNormal() {
		return ThreeWayNormal;
	}

	public Button getThreeWayReduced() {
		return ThreeWayReduced;
	}

	public Button getFourWayNormal() {
		return FourWayNormal;
	}

	public Button getFourWayReduced() {
		return FourWayReduced;
	}

	public Button getAirportNormal() {
		return AirportNormal;
	}

	public Button getAirportReduced() {
		return AirportReduced;
	}

	public Pane getButtonsPane() {
		return buttonsPane;
	}

	public ComboBox<String> getBusesComboBox() {
		return busesComboBox;
	}

}
