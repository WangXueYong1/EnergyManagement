package util;
import java.io.IOException;
import java.util.Date;
public class DataUtilTest {
	public static void main(String[]str) {
		int port = 6606;
		Thread t = null;
		try {
			t = new GreetingServer(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.run();
		Date date1 =new Date();
		int[] yearMonthDayHourMinuteSencondWeek = DataUtil.getYearMonthDayHourMinuteSencondWeek(date1);//年月日时分秒星期几一年中第几个星期
		System.out.println(yearMonthDayHourMinuteSencondWeek.toString());//[2018, 8, 27, 16, 40, 3, 1, 35]		
	}
}
