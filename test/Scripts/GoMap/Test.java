package GoMap;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Date date=new Date();
		SimpleDateFormat f= new SimpleDateFormat("YYYY/dd/MM/hh:mm");
		String year=f.format(date);
		System.out.println(year);
		//System.out.println(date.getYear()-1900);
	}

}
