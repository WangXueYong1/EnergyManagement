package util;

import java.util.Calendar;
import java.util.Date;

public class DataUtil {
	public static int[] getYearMonthDayHourMinuteSencondWeek(Date date1) {
		int[] result = new int[8];
		Calendar cale = Calendar.getInstance();
		cale.setTime(date1);

		// 获取一个日期的年份
		result[0] = cale.get(Calendar.YEAR);

		// 获取一个日期的月份
		result[1]  = (cale.get(Calendar.MONTH)) + 1;

		// 获取一个日期是在一个月中的第几天
		result[2]  = cale.get(Calendar.DAY_OF_MONTH);

		// 获取一个日期是在一个月中的第几天
		// Calendar.DAY_OF_MONTH 和 Calendar.DATE 是等价的
		int dates = cale.get(Calendar.DATE);

		// 当前时：HOUR_OF_DAY-24小时制
		result[3]  = cale.get(Calendar.HOUR_OF_DAY);

		// HOUR-12小时制
		int hour12 = cale.get(Calendar.HOUR);

		// 当前分
		result[4]  = cale.get(Calendar.MINUTE);

		// 当前秒
		result[5]  = cale.get(Calendar.SECOND);

		// 星期几 Calendar.DAY_OF_WEEK用数字（1~7）表示（星期日~星期六）
		result[6]  = cale.get(Calendar.DAY_OF_WEEK) - 1;

		// 当前年的第几周
		result[7]  = cale.get(Calendar.WEEK_OF_YEAR);
		return result;
	}

}
