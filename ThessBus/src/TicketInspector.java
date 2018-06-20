import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.google.zxing.NotFoundException;

public class TicketInspector extends User implements Serializable {

	public String name;
	public String inspector_num;
	//����������� ��������� ����������� 
	public HashMap<String, Integer> durations = new HashMap<>(); 

	
	public TicketInspector(String aName, String aPassword, String username) {
		super(username, aPassword);
		this.name = aName;

		durations.put("01", 80);   durations.put("01�", 80);  durations.put("02", 65);   durations.put("02�", 70);
		durations.put("03", 60);   durations.put("04�", 85);  durations.put("04�", 85);  durations.put("04�", 90);
		durations.put("05", 62);   durations.put("06", 65);   durations.put("07", 60);   durations.put("08", 65);
		durations.put("09", 50);   durations.put("09�", 54);  durations.put("09�", 57);  durations.put("10", 64);
		durations.put("11", 60);   durations.put("11�", 63);  durations.put("12", 70);   durations.put("14", 65);
		durations.put("14�", 65);  durations.put("15", 35);   durations.put("15�", 40);  durations.put("14", 40);
		durations.put("17", 57);   durations.put("18", 70);   durations.put("19", 70);   durations.put("19�", 68);
		durations.put("20", 65);   durations.put("21", 56);   durations.put("21�", 60);  durations.put("22", 55);
		durations.put("23", 70);   durations.put("24", 65);   durations.put("25", 67);   durations.put("25�", 68);
		durations.put("26", 75);   durations.put("27", 70);   durations.put("27�", 72);  durations.put("27�", 75);
		durations.put("28�", 82);  durations.put("28�", 80);  durations.put("29", 75);   durations.put("29�", 78);
		durations.put("30", 50);   durations.put("31", 70);   durations.put("32", 78);   durations.put("32�", 78);
		durations.put("33", 40);   durations.put("33�", 42);  durations.put("34", 45);   durations.put("35", 30);
		durations.put("36", 45);   durations.put("36�", 45);  durations.put("36�", 45);  durations.put("36�", 45);
		durations.put("36�", 40);  durations.put("36�", 40);  durations.put("36�", 40);  durations.put("36�", 40);
		durations.put("36�", 40);  durations.put("36�", 40);  durations.put("36�", 40);  durations.put("37", 40);
		durations.put("38", 40);   durations.put("39", 40);   durations.put("39�", 40);  durations.put("40", 40);
		durations.put("40�", 40);  durations.put("40�", 40);  durations.put("42", 40);   durations.put("42�", 40);
		durations.put("42�", 40);  durations.put("43", 40);   durations.put("45", 40);   durations.put("45�", 40);
		durations.put("45�", 40);  durations.put("50", 40);   durations.put("51", 40);   durations.put("51�", 40);
		durations.put("52", 40);   durations.put("52�", 40);  durations.put("53", 40);   durations.put("54", 40);
		durations.put("54�", 40);  durations.put("55", 40);   durations.put("55�", 40);  durations.put("55�", 40);
		durations.put("55�", 40);  durations.put("55�", 40);  durations.put("55�", 40);  durations.put("55�", 40);
		durations.put("55�", 40);  durations.put("56", 40);   durations.put("56�", 40);  durations.put("57", 40);
		durations.put("58", 40);   durations.put("59", 40);   durations.put("59�", 40);  durations.put("59�", 40);
		durations.put("59�", 40);  durations.put("60�", 40);  durations.put("60�", 40);  durations.put("60�", 40);
		durations.put("61", 40);   durations.put("61�", 40);  durations.put("64", 40);   durations.put("64�", 40);
		durations.put("64�", 40);  durations.put("64�", 40);  durations.put("64�", 40);  durations.put("66", 40);
		durations.put("67", 40);   durations.put("67�", 40);  durations.put("67�", 40);  durations.put("68�", 40);
		durations.put("68�", 40);  durations.put("69�", 40);  durations.put("69�", 40);  durations.put("69�", 40);
		durations.put("69�", 40);  durations.put("69�", 40);  durations.put("69�", 40);  durations.put("69�", 40);
		durations.put("70", 40);   durations.put("70�", 40);  durations.put("71", 40);   durations.put("71�", 40);
		durations.put("72", 40);   durations.put("72�", 40);  durations.put("72�", 40);  durations.put("76", 40);
		durations.put("76�", 40);  durations.put("76�", 40);  durations.put("77", 40);   durations.put("77�", 40);
		durations.put("77�", 40);  durations.put("77�", 40);  durations.put("77�", 40);  durations.put("77�", 40);
		durations.put("79", 40);   durations.put("79�", 40);  durations.put("79�", 40);  durations.put("80", 40);
		durations.put("80�", 40);  durations.put("80�", 40);  durations.put("80�", 40);  durations.put("80�", 40);
		durations.put("81", 40);   durations.put("81�", 40);  durations.put("81�", 40);  durations.put("81�", 40);
		durations.put("81�", 40);  durations.put("82�", 40);  durations.put("82�", 40);  durations.put("82�", 40);
		durations.put("82�", 40);  durations.put("82�", 40);  durations.put("82�", 40);  durations.put("83", 40);
		durations.put("83�", 40);  durations.put("83�", 40);  durations.put("83�", 40);  durations.put("83�", 40);
		durations.put("83�", 40);  durations.put("83�", 40);  durations.put("84�", 40);  durations.put("84�", 40);
		durations.put("85", 40);   durations.put("85�", 40);  durations.put("85�", 40);  durations.put("85�", 40);
		durations.put("85�", 40);  durations.put("85�", 40);  durations.put("85�", 40);  durations.put("85�", 40);
		durations.put("85�", 40);  durations.put("85�", 40);  durations.put("86", 40);   durations.put("86�", 40);
		durations.put("86�", 40);  durations.put("86�", 40);  durations.put("86�", 40);  durations.put("86�", 40);
		durations.put("86�", 40);  durations.put("86�", 40);  durations.put("86�", 40);  durations.put("86�", 40);
		durations.put("86�", 40);  durations.put("86�", 40);  durations.put("86�", 40);  durations.put("86�", 40);
		durations.put("86�", 40);  durations.put("87�", 40);  durations.put("87�", 40);  durations.put("87�", 40);
		durations.put("87�", 40);  durations.put("87�", 40);  durations.put("87�", 40);  durations.put("87�", 40);
		durations.put("87�", 40);  durations.put("87�", 40);  durations.put("87�", 40);  durations.put("87�", 40);
		durations.put("87�", 40);  durations.put("87�", 40);  durations.put("87�", 40);  durations.put("87�", 40);
		durations.put("88", 40);   durations.put("88�", 40);  durations.put("88�", 40);  durations.put("88�", 40);
		durations.put("88�", 40);  durations.put("88�", 40);  durations.put("88�", 40);  durations.put("88�", 40);
		durations.put("89�", 40);  durations.put("89�", 40);  durations.put("89�", 40);  durations.put("89�", 40);
		durations.put("89�", 40);  durations.put("90", 40);   durations.put("90�", 40);  durations.put("90�", 40);
		durations.put("90�", 40);  durations.put("90�", 40);  durations.put("91�", 40);  durations.put("91�", 40);
		durations.put("91�", 40);  durations.put("91�", 40);  durations.put("91�", 40);  durations.put("91�", 40);
		durations.put("91�", 40);  durations.put("91�", 40);  durations.put("91�", 40);  durations.put("92", 40);
		durations.put("92�", 40);  durations.put("92�", 40);  durations.put("92�", 40);  durations.put("�1", 40);
		durations.put("�1�", 40);  durations.put("�1", 40);
		
	}
	
	public Product browseQR(String filepath_of_qr) throws FileNotFoundException, NotFoundException, IOException {			
		String product_num;
		Product product;
		
		product_num = QRcode.decodeQRCodeImage(filepath_of_qr);
		
		product = (Product) FileManager.search(product_num, "Products.dat");
		
		return product;
	}
	
	//duration = {70, 90, 120, 1, 3, 6, 12} depending on the product (multi-way ticket or card)
	//bus, validation = 0, null if product = card
	//validation = null if product = 1way ticket
	
	//�� ������� �� ������� ����� ���� ���� ���� ��� 12
	public boolean ticketValidation(String date_time, int duration, String bus, String validation_date_time, double price) throws ParseException {
		Set<Integer> foo = new HashSet<>();
		foo.add(1);
		foo.add(3);
		foo.add(6);
		foo.add(12);
		
		String dates = date_time.substring(0,10);
		String times  = date_time.substring(11, 19);
		
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		Date date = null;
		Date time = null;
			
		date = sdf.parse(dates);
		time = sdf2.parse(times);
		
		//Date current_date = Calendar.getInstance().getTime();
		Date current_date;
		current_date = sdf.parse(sdf.format(new Date()));
		Date current_time;
		current_time = sdf2.parse(sdf2.format(new Date()));
		
		if(foo.contains(duration)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, 1);
			Date expiration_date = cal.getTime();
			expiration_date = sdf.parse(sdf.format(expiration_date));
			
			//� ����� ��� �����
			if((expiration_date.after(current_date)) || (expiration_date.equals(current_date))) {
				return true;
			}
			else //� ����� ���� �����
				return false;
			
		}
		
		if(date.equals(current_date)) {
		
			//������� ����� ���������� �� ��������� ��� �� ��������� ����� �� 78�
			if((bus.equals("N1") || bus.equals("N1A")) && (price != 2.0))
				return false;
			
			if(validation_date_time.equals("-") == false) {
				String validation_times = validation_date_time.substring(11, 19);
				Date validation_time = null;
				validation_time = sdf2.parse(validation_times);
				long diff = validation_time.getTime() - time.getTime();
			
				if(diff/(1000*60) <= duration) {
					
					long diff2 = current_time.getTime() - validation_time.getTime();
					if(diff2/(1000*60) > durations.get(bus))
						return false;
					return true;
				}
				return false;
			}
			else {
				long diff2 = current_time.getTime() - time.getTime();
				if(diff2/(1000*60) > durations.get(bus))
					return false;
				return true;
			}
		
		}
		else
			return false;
	}

}

//���������� �� ����� �� ������� ��� �������� ��� ������� ���� closeOperation?? �
//�� ������� � ������ ��� ���������� � ������� ��� FileManager ��� �������� ��� �������� 
//�� ����� ��� ��� SignOut �
//�� ���������� ������� ��� retrieve ��������� ��� ��������� ���� ��� ������� ��� �� �������
//����� ���� ��� ���������� ��������� ���������� ��� ������� �
//Alerts ��� login �� ��� ����������� �������� � 
//�������� ������������ ��� ������ ��� ������� ��� ����� ���������, ������ ��������� �
//�� ������������ boolean ��������� � �� ������ ��� �� ���������� ���� �������; - ticketValidation �
//Register -> StartScreen ������ stage �
//�������� ���������� ������ ��� navBar ��� ��� ������ �
//�������� �������� ��������� ��� ��������, ��������� ��������� ��� �������� ������ -> ������
//DepositController!! �
//������� ����������: �� ����������� �������