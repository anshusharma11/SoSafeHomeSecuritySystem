package sosafe.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Time {
	private int HH;
	private int MM;
	private int SS;
	
	public Time(int hh, int mm, int ss) {
		HH = hh;
		MM = mm;
		SS = ss;
	}
	
	public Time(Date date) {
	  	Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		HH = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
		MM = calendar.get(Calendar.MINUTE);
		SS = calendar.get(Calendar.SECOND);
	}
	/**
	 * @return the hH
	 */
	public int getHH() {
		return HH;
	}
	/**
	 * @param hH the hH to set
	 */
	public void setHH(int hH) {
		HH = hH;
	}
	/**
	 * @return the mM
	 */
	public int getMM() {
		return MM;
	}
	/**
	 * @param mM the mM to set
	 */
	public void setMM(int mM) {
		MM = mM;
	}
	/**
	 * @return the sS
	 */
	public int getSS() {
		return SS;
	}
	/**
	 * @param sS the sS to set
	 */
	public void setSS(int sS) {
		SS = sS;
	}
	/**
	 * @return the Date
	 */
	public Date getDate() {
		Calendar calendar = new GregorianCalendar(2000,1,1,HH,MM,SS);
		return calendar.getTime();
	}
	
	public String toString() {
		return ""+HH+":"+MM+":"+SS;
	}
}