package com.airchina.cqmp.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//import com.sinosoft.prpins.common.util.PrpInsPubTools;

/**
 * 功能：对日期操作的公共方法类 作者：袁伟民 时间：2009-7-29 21:16 修改：
 * 
 */
public class DateUtil {
	
	public static final String YEAR_TO_DAY_EN = "MMMM d,yyyy";

	public static final String YEAR_TO_DAY_CN = "yyyy年M月d日";

	public static final String YEAR_TO_DAY = "yyyy-MM-dd";

	public static final String YEAR_TO_SECOND = "yyyy-MM-dd HH:mm:ss";

	public static final String HOUR_TO_SECOND = "HH:mm:ss";

	public static final String HOUR_TO_MINUTE = "HH:mm";

	public static final String YEAR_TO_DAY_NO_HYPHEN = "yyyyMMdd";
	
	public static final String YEAR_TO_SECOND_NO_HYHEN = "yyyyMMddHHmmss";

	/***
	 * 格式化日期的方法
	 * 
	 * @param date
	 *            带格式化的日期
	 * @param pattern
	 *            格式化的表达式 比如 yyyy-MM-dd
	 * @return 格式化后的字符串
	 */
	public static String formatDate(Date date, String pattern) {
		// 建立日期FORMAT的实例
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	/***
	 * 格式化日期的方法
	 * 
	 * @param date
	 *            带格式化的日期，默认为yyyy-MM-dd
	 * @return 格式化后的字符串
	 */
	public static String formatDate(Date date) {
		return formatDate(date, YEAR_TO_DAY);
	}
	
	/***
	 * 格式化日期的方法
	 * 
	 * @param date
	 *            带格式化的日期，默认为yyyyMMdd
	 * @return 格式化后的字符串
	 */
	public static String formatDateNoHyphen(Date date) {
		return formatDate(date, YEAR_TO_DAY_NO_HYPHEN);
	}

	/**
	 * 利用传入的日期，返回上一个月当天的日期对象
	 * 
	 * @param current
	 *            ：当前日期对象
	 * @return 上一个月的当天的日期对象
	 */
	@SuppressWarnings("deprecation")
	public static Date getPreviousMonth(Date current) {
		if (current.getMonth() != 1) {
			// 如果不是一月份，则直接减去一天
			return new Date(current.getYear(), current.getMonth() - 1,
					current.getDate());
		} else {
			// 如果是一月份，则需要在年上减去一天，月变为12
			return new Date(current.getYear() - 1, 12, current.getDate());
		}
	}

	/**
	 * 利用传入的日期，返回上一个月当天的日期对象
	 * 
	 * @param current
	 *            ：当前日期对象
	 * @return 上一个月的当天的日期对象
	 */
	@SuppressWarnings("deprecation")
	public static Date getNextMonth(Date current) {
		if (current.getMonth() != 12) {
			// 如果不是一月份，则直接减去一天
			return new Date(current.getYear(), current.getMonth() + 1,
					current.getDate());
		} else {
			// 如果是一月份，则需要在年上减去一天，月变为12
			return new Date(current.getYear() + 1, 0, current.getDate());
		}
	}
    
	/**
	 * 利用传入的日期,返回下个月最后一天的日期
	 * guoyonggang
	 * @return
	 */
    public static Date getNextMonthEnd(Date current) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(current);
		lastDate.add(Calendar.MONTH, 1);// 加一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		Date date=lastDate.getTime();
		return date;
	}


	/** 利用传入日期，返回一个上个月日期的yyyyMM的String表达式 */
	public static String getPreviousMonths(Date current) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(getPreviousMonth(current));
	}

	/***
	 * 获取传入日期下一年的日期对象
	 * 
	 * @param date
	 *            传入的日期对象
	 * @return 下一年的日期对象
	 */
	@SuppressWarnings("deprecation")
	public static Date getNextYear(Date date) {
		return new Date(date.getYear() + 1, date.getMonth(), date.getDate());
	}

	/***
	 * 获取传入日期下n年的日期对象
	 * 
	 * @param date
	 *            传入的日期对象
	 * @return 下n年的日期对象
	 */
	@SuppressWarnings("deprecation")
	public static Date getNextNYear(Date date, int n) {
		return new Date(date.getYear() + n, date.getMonth(), date.getDate());
	}

	/***
	 * 计算传入日期的往后顺延N天以后的日期对象
	 * 
	 * @param date
	 *            传入的日期对象
	 * @param n
	 *            往后顺延的天数
	 * @return 顺延后的日期对象
	 */
	public static Date getPreviousNDate(Date date, long n) {
		long time = date.getTime();
		// 用毫秒数来计算新的日期
		time = time - n * 24 * 60 * 60 * 1000;
		return new Date(time);
	}

	/***
	 * 计算传入日期的往前推算N天的日期对象
	 * 
	 * @param date
	 *            传入的日期对象
	 * @param n
	 *            往后顺延的天数
	 * @return 往前推算后的日期对象
	 */
	public static Date getNextNDate(Date date, long n) {
		long time = date.getTime();
		// 用毫秒数来计算新的日期
		time = time + n * 24 * 60 * 60 * 1000;
		return new Date(time);
	}

	/***
	 * 计算传入日期的前一天
	 * 
	 * @param date
	 *            传入日期
	 * @return 传入日期的前一天的日期对象
	 */
	public static Date getPreviousDate(Date date) {
		return getPreviousNDate(date, 1);
	}

	/***
	 * 计算传入日期的后一天
	 * 
	 * @param date
	 *            传入日期
	 * @return 传入日期的后一天的日期对象
	 */
	public static Date getNextDate(Date date) {
		return getNextNDate(date, 1);
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @作者：滑立敏
	 * @日期：2010-01-26
	 * @param date
	 * @return str
	 */
	public static String dateToStr(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(date);
		return str;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @作者：滑立敏
	 * @日期：2010-01-26
	 * @param str
	 * @return date
	 */
	public static Date strToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date strToTime(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 根据传入的日期计算此日期与下一年该日相差的天数
	 * 
	 * @param startDate
	 *            起始日期
	 * @return 天数区间
	 * 
	 * @作者：李阳
	 * @时间：2010-06-22
	 */
	public static int getDaysCountForYear(Date startDate) {
		// 获取传入日期下一年的日期对象
		Date nextYear = getNextYear(startDate);
		// 计算天数
		return getDaysCount(startDate, nextYear);
	}

	/***
	 * 根据起始日期、终止日期计算天数
	 * 
	 * @param startDate
	 *            起始日期
	 * @param endDate
	 *            终止日期
	 * @return 天数区间
	 */
	public static int getDaysCount(Date startDate, Date endDate) {
		return getDaysCount(startDate, 0, endDate, 0);
	}

	/***
	 * 根据年数返回所包含的天数
	 * 
	 * @param year
	 *            年数
	 * @return 该年所包含的天数
	 */
	public static int getDaysFromYear(int year) {
		// 判断是平年还是闰年
		if ((year % 400 == 0) || (year % 100 != 0) && (year % 4 == 0)) {
			return 366;
		} else
			return 365;

	}

	/***
	 * 根据起始日期、起始时间、终止日期、终止时间计算天数
	 * 
	 * @param startDate
	 *            起始日期
	 * @param startHour
	 *            起始小时
	 * @param endDate
	 *            终止日期
	 * @param endHour
	 *            终止小时
	 * @return 天数区间
	 */
	public static int getDaysCount(Date startDate, int startHour, Date endDate,
			int endHour) {
		// 根据起始日期计算起始的毫秒
		long startTime = startDate.getTime();
		// 根据终止日期计算终止的毫秒
		long endTime = endDate.getTime();
		// 通过起始毫秒和终止毫秒的差值，计算天数
		int dayCount = (int) ((endTime - startTime) / (24 * 60 * 60 * 1000) + 1);

		if (endHour <= startHour) {
			if (startHour == 24 && endHour == 0) {
				dayCount = dayCount - 2;
			} else {
				dayCount = dayCount - 1;
			}
		}
		return dayCount;

	}

	/***
	 * 根据起始日期、终止日期计算月数
	 * 
	 * @param startDate
	 *            起始日期
	 * @param endDate
	 *            终止日期
	 * @return 天数区间
	 */
	public static int getMonthsCount(Date startDate, Date endDate) {
		return getMonthsCount(startDate, 0, endDate, 0);
	}

	/***
	 * 根据起始日期、起始时间、终止日期、终止时间计算天数
	 * 
	 * @param startDate
	 *            起始日期
	 * @param startHour
	 *            起始小时
	 * @param endDate
	 *            终止日期
	 * @param endHour
	 *            终止小时
	 * @return 天数区间
	 */
	@SuppressWarnings("deprecation")
	public static int getMonthsCount(Date startDate, int startHour,
			Date endDate, int endHour) {
		// 年份差
		int yearDiff = endDate.getYear() - startDate.getYear();
		// 月份差
		int monthDiff = endDate.getMonth() - startDate.getMonth();
		// 总的月数量
		int monthCount = monthDiff + yearDiff * 12;
		// 天数差
		long dayDiff = endDate.getDate() - startDate.getDate();
		// 不足一个月按一个月计算
		if (dayDiff > 0 || (dayDiff == 0 && endHour > startHour)) {
			monthCount = monthCount + 1;
		}

		return monthCount;

	}

	/***
	 * 根据起始日期、终止日期计算年数
	 * 
	 * @param startDate
	 *            起始日期
	 * @param endDate
	 *            终止日期
	 * @return 天数区间
	 */
	public static int getYearsCount(Date startDate, Date endDate) {
		return getYearsCount(startDate, 0, endDate, 0);
	}

	/***
	 * 根据起始日期、起始时间、终止日期、终止时间计算天数
	 * 
	 * @param startDate
	 *            起始日期
	 * @param startHour
	 *            起始小时
	 * @param endDate
	 *            终止日期
	 * @param endHour
	 *            终止小时
	 * @return 天数区间
	 */
	public static int getYearsCount(Date startDate, int startHour,
			Date endDate, int endHour) {
		int yearCount = 0;

		// 获取之间的月数
		int monthCount = getMonthsCount(startDate, startHour, endDate, endHour);

		yearCount = monthCount / 12;

		if (monthCount % 12 != 0) {
			yearCount++;
		}

		// 返回年数
		return yearCount;
	}

	/**
	 * 根据日期获取总小时数
	 * 
	 * @param date
	 *            日期对象
	 * @return 总小时数
	 */
	public static int getHoursCount(Date date) {
		return getHoursCount(date, 0);
	}

	/**
	 * 根据日期和小时数，获取总小时数
	 * 
	 * @param date
	 *            日期对象
	 * @param hour
	 *            小时数
	 * @return 总小时数
	 */
	public static int getHoursCount(Date date, Integer hour) {
		long hourCount = hour;
		hourCount += date.getTime() / 3600 / 1000;
		return (int) hourCount;
	}

//	// 将util的date类型转为sinosoft的date类型
//	public static com.sinosoft.utility.string.Date parseDate(java.util.Date date) {
//		String strdate = PrpInsPubTools.formatDate(date, "yyyy/MM/dd");
//		com.sinosoft.utility.string.Date d = new com.sinosoft.utility.string.Date(
//				strdate);
//		return d;
//	}

	/***
	 * 比较两个日期的大小
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return -1 ： 开始日期小于结束日期 0 ： 开始日期等于结束日期 1 ： 开始日期大于结束日期
	 */
	public static int compareDate(Date startDate, Date endDate) {
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		if (startTime < endTime) {
			return -1;
		}
		if (startTime == endTime) {
			return 0;
		}
		return 1;
	}

	/***
	 * 比较两个日期的大小
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return -1 ： 开始日期小于结束日期 0 ： 开始日期等于结束日期 1 ： 开始日期大于结束日期
	 */
	public static int compareDate(String startDate, String endDate) {
		return compareDate(strToDate(startDate), strToDate(endDate));
	}

	/***
	 * 根据起始日期、起始时间、终止日期、终止时间计算月数，计算的是去掉多余天数的月数
	 * 
	 * 作者：刘立伟 时间：2010-10-20
	 * 
	 * @param startDate
	 *            起始日期
	 * @param startHour
	 *            起始小时
	 * @param endDate
	 *            终止日期
	 * @param endHour
	 *            终止小时
	 * @return 期间相差的月数(去掉多余的天数)
	 */
	@SuppressWarnings("deprecation")
	public static int getMinMonthsCount(Date startDate, int startHour,
			Date endDate, int endHour) {
		// 年份差
		int yearDiff = endDate.getYear() - startDate.getYear();
		// 月份差
		int monthDiff = endDate.getMonth() - startDate.getMonth();
		// 总的月数量
		int monthCount = monthDiff + yearDiff * 12;
		// 天数差
		long dayDiff = endDate.getDate() - startDate.getDate();
		if(startHour == 0 && endHour == 24){
			dayDiff += 1;
		}
		// 如果天数差小于0或者天数差等于零（并且终止小时小于起始小时），那么月数减一。
		if (dayDiff < 0 || (dayDiff == 0 && endHour < startHour)) {
			monthCount = monthCount - 1;
		}
		return monthCount;

	}

	/***
	 * 根据起始日期、起始时间、终止日期、终止时间计算天数，计算的是相差月后余下的天数
	 * 
	 * @param startDate
	 *            起始日期
	 * @param startHour
	 *            起始小时
	 * @param endDate
	 *            终止日期
	 * @param endHour
	 *            终止小时
	 * @return 余下的天数
	 * 
	 *         作者：刘立伟 时间：2010-10-20
	 */
	@SuppressWarnings("deprecation")
	public static int getOtherDayCount(Date startDate, int startHour,
			Date endDate, int endHour) {
		// 相差的天数
		int dayCount = 0;
		// 时间变量
		long newStartTime = 0;
		// 终保日期getTime变量
		long endTime = 0;
		// 计算日相差
		int dayDiff = endDate.getDate() - startDate.getDate();
		// 如果日相差等于0，那endHour > startHour,那么返回一天，如：2010-10-10：02到2010-10-10:09
		if (dayDiff == 0) {
			if (endHour > startHour) {
				return 1;
			}
			// 如果日相差小于0
		} else if (dayDiff < 0) {
			// 如果startHour < endHour，如：2010-09-18:02------2010-10-10:08
			if (startHour < endHour) {
				Date newStartDate = new Date(endDate.getYear(),
						endDate.getMonth() - 1, startDate.getDate());
				newStartTime = newStartDate.getTime();
				endTime = endDate.getTime();
				dayCount = (int) ((endTime - newStartTime)
						/ (24 * 60 * 60 * 1000) + 1);
				// 如果startHour = endHour，如：2010-09-18:02------2010-10-10:02
			} else if (startHour == endHour) {
				Date newStartDate = new Date(endDate.getYear(),
						endDate.getMonth() - 1, startDate.getDate());
				newStartTime = newStartDate.getTime();
				endTime = endDate.getTime();
				dayCount = (int) ((endTime - newStartTime) / (24 * 60 * 60 * 1000));
				// 如果startHour > endHour
			} else if (startHour > endHour) {
				Date newStartDate = new Date(endDate.getYear(),
						endDate.getMonth() - 1, startDate.getDate());
				newStartTime = newStartDate.getTime();
				endTime = endDate.getTime();
				dayCount = (int) ((endTime - newStartTime)
						/ (24 * 60 * 60 * 1000) + 1);
				// 如：2010-09-18:24------2010-10-10:00
				if (startHour == 24 && endHour == 0) {
					dayCount = dayCount - 2;
					// 如：2010-09-18:14------2010-10-10:10
				} else {
					dayCount = dayCount - 1;
				}
			}
			// 日相差大于0
		} else if (dayDiff > 0) {
			// 如果startHour < endHour
			if (startHour > endHour) {
				Date newStartDate = new Date(endDate.getYear(),
						endDate.getMonth(), startDate.getDate());
				newStartTime = newStartDate.getTime();
				endTime = endDate.getTime();
				// 如：2010-10-10:08------2010-10-18:02
				dayCount = (int) ((endTime - newStartTime) / (24 * 60 * 60 * 1000));
				// 如：2010-10-10:24------2010-10-18:00
				if (startHour == 24 && endHour == 0) {
					dayCount = dayCount - 1;
				}
				// 如果startHour = endHour，如：2010-10-10:08------2010-10-18:08
			} else if (startHour == endHour) {
				Date newStartDate = new Date(endDate.getYear(),
						endDate.getMonth(), startDate.getDate());
				newStartTime = newStartDate.getTime();
				endTime = endDate.getTime();
				dayCount = (int) ((endTime - newStartTime) / (24 * 60 * 60 * 1000));
				// 如果startHour < endHour，如：2010-10-10:02------2010-10-18:08
			} else if (startHour < endHour) {
				Date newStartDate = new Date(endDate.getYear(),
						endDate.getMonth(), startDate.getDate());
				newStartTime = newStartDate.getTime();
				endTime = endDate.getTime();
				dayCount = (int) ((endTime - newStartTime)
						/ (24 * 60 * 60 * 1000) + 1);
			}
		}
		return dayCount;
	}
	
	public static int getAgeByBirthday(String birthday) { 
		int iage = 0; 
		if (birthday != "" || birthday != null) { 
			int year = Integer.parseInt(birthday.substring(0, 4)); 
			int month = Integer.parseInt(birthday.substring(5, 7)); 
			int day = Integer.parseInt(birthday.substring(8,10)); 
			Calendar birthDate = new GregorianCalendar(year, month, day); 
			Calendar today = Calendar.getInstance(); 
			if (today.get(Calendar.YEAR) > birthDate.get(Calendar.YEAR)) { 
				iage = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR) - 1; 
				if (today.get(Calendar.MONTH) + 1 > birthDate .get(Calendar.MONTH)) { 
					iage++; 
				} else if (today.get(Calendar.MONTH) + 1 == birthDate .get(Calendar.MONTH)) { 
	
					if (today.get(Calendar.DAY_OF_MONTH) > birthDate .get(Calendar.DAY_OF_MONTH)) { 
						iage++; 
					} 
				} 
			} 
			return iage; 
		} 
		return 0; 
	}

	public static void main(String[] args) {
		//
	}
}
