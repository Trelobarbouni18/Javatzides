import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fine implements Serializable {

	String date_time;
	Passenger owner;
	double price;	
	String  inspector_num;
	String bus;
	boolean paid;
	
	public Fine(Passenger owner, String inspector_num, String bus) {
		this.date_time = new SimpleDateFormat("yyyy/MM/dd HHmmss").format(Calendar.getInstance().getTime());
		this.owner = owner;
		countPrice();
		this.inspector_num = inspector_num;
		this.bus = bus;
		this.paid = false;
	}
	
	public Passenger getOwner() {
		return owner;
	}

	public void finePaid()
	{
		paid = true;
	}
	
	public void countPrice()
	{
		this.price = 60/owner.getCheck();
	}

	public double getPrice() {
		return price;
	}

	public String getDate_time() {
		return date_time;
	}

	public String getBus() {
		return bus;
	}

	public boolean isPaid() {
		return paid;
	}
	
}
