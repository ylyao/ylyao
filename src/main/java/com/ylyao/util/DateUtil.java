package com.ylyao.util;

/**
 * 版权所有：恒生聚源
 * 项目名称:irp
 * 创建者: loubin
 * 创建日期: 2010-6-10
 * 文件说明: 常用日期转换函数的封装
 * 最近修改者：loubin
 * 最近修改日期：2010-6-10
 * 
 * 
 * 
 */
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期格式参数如下：
 * 
 * <blockquote>
 * <table border=0 cellspacing=3 cellpadding=0 summary="Chart shows pattern letters, date/time component, presentation, and examples.">
 *     <tr bgcolor="#ccccff">
 *         <th align=left>Letter
 *         <th align=left>Date or Time Component
 *         <th align=left>Presentation
 *         <th align=left>Examples
 *     <tr>
 *         <td><code>G</code>
 *         <td>Era designator
 *         <td><a href="#text">Text</a>
 *         <td><code>AD</code>
 *     <tr bgcolor="#eeeeff">
 *         <td><code>y</code>
 *         <td>Year
 *         <td><a href="#year">Year</a>
 *         <td><code>1996</code>; <code>96</code>
 *     <tr>
 *         <td><code>M</code>
 *         <td>Month in year
 *         <td><a href="#month">Month</a>
 *         <td><code>July</code>; <code>Jul</code>; <code>07</code>
 *     <tr bgcolor="#eeeeff">
 *         <td><code>w</code>
 *         <td>Week in year
 *         <td><a href="#number">Number</a>
 *         <td><code>27</code>
 *     <tr>
 *         <td><code>W</code>
 *         <td>Week in month
 *         <td><a href="#number">Number</a>
 *         <td><code>2</code>
 *     <tr bgcolor="#eeeeff">
 *         <td><code>D</code>
 *         <td>Day in year
 *         <td><a href="#number">Number</a>
 *         <td><code>189</code>
 *     <tr>
 *         <td><code>d</code>
 *         <td>Day in month
 *         <td><a href="#number">Number</a>
 *         <td><code>10</code>
 *     <tr bgcolor="#eeeeff">
 *         <td><code>F</code>
 *         <td>Day of week in month
 *         <td><a href="#number">Number</a>
 *         <td><code>2</code>
 *     <tr>
 *         <td><code>E</code>
 *         <td>Day in week
 *         <td><a href="#text">Text</a>
 *         <td><code>Tuesday</code>; <code>Tue</code>
 *     <tr bgcolor="#eeeeff">
 *         <td><code>a</code>
 *         <td>Am/pm marker
 *         <td><a href="#text">Text</a>
 *         <td><code>PM</code>
 *     <tr>
 *         <td><code>H</code>
 *         <td>Hour in day (0-23)
 *         <td><a href="#number">Number</a>
 *         <td><code>0</code>
 *     <tr bgcolor="#eeeeff">
 *         <td><code>k</code>
 *         <td>Hour in day (1-24)
 *         <td><a href="#number">Number</a>
 *         <td><code>24</code>
 *     <tr>
 *         <td><code>K</code>
 *         <td>Hour in am/pm (0-11)
 *         <td><a href="#number">Number</a>
 *         <td><code>0</code>
 *     <tr bgcolor="#eeeeff">
 *         <td><code>h</code>
 *         <td>Hour in am/pm (1-12)
 *         <td><a href="#number">Number</a>
 *         <td><code>12</code>
 *     <tr>
 *         <td><code>m</code>
 *         <td>Minute in hour
 *         <td><a href="#number">Number</a>
 *         <td><code>30</code>
 *     <tr bgcolor="#eeeeff">
 *         <td><code>s</code>
 *         <td>Second in minute
 *         <td><a href="#number">Number</a>
 *         <td><code>55</code>
 *     <tr>
 *         <td><code>S</code>
 *         <td>Millisecond
 *         <td><a href="#number">Number</a>
 *         <td><code>978</code>
 *     <tr bgcolor="#eeeeff">
 *         <td><code>z</code>
 *         <td>Time zone
 *         <td><a href="#timezone">General time zone</a>
 *         <td><code>Pacific Standard Time</code>; <code>PST</code>; <code>GMT-08:00</code>
 *     <tr>
 *         <td><code>Z</code>
 *         <td>Time zone
 *         <td><a href="#rfc822timezone">RFC 822 time zone</a>
 *         <td><code>-0800</code>
 * </table>
 * </blockquote>
 *
 * @author Heroying
 * @version $上午12:44:46 2011-10-24 $
 * @since JDK1.5
 */
public class DateUtil {
	public DateUtil() {
	}

	/*
	 * 名称：strToDate 功能：将指定的字符串转换成日期 输入：aStrValue: 要转换的字符串; aFmtDate: 转换日期的格式,
	 * 默认为:"yyyy/MM/dd" aDteRtn: 转换后的日期
	 */
	public static Date strToDate(String aStrValue, String aFmtDate) throws ParseException {
		Date aDteRtn = Calendar.getInstance().getTime();
		if (aFmtDate.length() == 0) {
			aFmtDate = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat fmtDate = new SimpleDateFormat(aFmtDate);

		try {
			aDteRtn.setTime(fmtDate.parse(aStrValue).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return aDteRtn;
	}

	public static Date stringToDate(String str) {
		Date return_date = null;
		String format = "";
		if (str.length() > 13) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if (str.length() > 10) {
			format = "yyyy-MM-dd HH:mm";
		} else {
			format = "yyyy-MM-dd";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return_date = sdf.parse(str);
		} catch (ParseException e) {

			e.getMessage();
			return null;
		}
		return return_date;
	}
	
	// ***************************************************
	// 名称：dateToStr
	// 功能：将指定的日期转换成字符串
	// 输入：aDteValue: 要转换的日期;
	// aFmtDate: 转换日期的格式, 默认为:"yyyy-MM-dd"
	// 输出：
	// 返回：转换之后的字符串
	// ***************************************************
	public static String dateToStr(Date aDteValue, String aFmtDate) {
		String strRtn = null;

		if (null==aFmtDate || aFmtDate.length() == 0) {
			aFmtDate = "yyyy-MM-dd";
		}
		if (aDteValue == null) {
			aDteValue = new Date();
		}
		SimpleDateFormat fmtDate = new SimpleDateFormat(aFmtDate);
		strRtn = fmtDate.format(aDteValue);
		return strRtn;
	}

	/**
	 * 取得今天的最后一个时刻
	 * 
	 * @return 今天的最后一个时刻
	 */
	public static Date currentEndDate() {
		return getEndDate(new Date());
	}

	/**
	 * 取得今天的第一个时刻
	 * 
	 * @return 今天的第一个时刻
	 */
	public static Date currentStartDate() {
		return getStartDate(new Date());
	}

	/**
	 * 获取某天的起始时间, e.g. 2005-10-01 00:00:00.000
	 * 
	 * @param date
	 *            日期对象
	 * @return 该天的起始时间
	 */
	public static Date getStartDate(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * 获取某天的结束时间, e.g. 2005-10-01 23:59:59.999
	 * 
	 * @param date
	 *            日期对象
	 * @return 该天的结束时间
	 */
	public static Date getEndDate(Date date) {

		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return cal.getTime();
	}

	/**
	 * 取得指定天数后的时间
	 * 
	 * @param date
	 *            基准时间
	 * @param dayAmount
	 *            指定天数，允许为负数
	 * @return 指定天数后的时间
	 */
	public static Date addDay(Date date, int dayAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dayAmount);
		return calendar.getTime();
	}

	/**
	 * 取得指定月数后的时间
	 * 
	 * @param date
	 *            基准时间
	 * @param dayAmount
	 *            指定天数，允许为负数
	 * @return 指定天数后的时间
	 */
	public static Date addMonth(Date date, int monthAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthAmount);
		return calendar.getTime();
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String formatDate(Date date, String formatStr) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);
	}

	public static Date getLastWeekBegin() {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.set(Calendar.WEEK_OF_MONTH, Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) - 1);
		beginTimecal.set(Calendar.DAY_OF_WEEK, 1);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 0);
		beginTimecal.set(Calendar.MINUTE, 0);
		beginTimecal.set(Calendar.SECOND, 0);
		return beginTimecal.getTime();
	}

	public static Date getLastWeekEnd() {
		Calendar endTimecal = Calendar.getInstance();
		endTimecal.set(Calendar.WEEK_OF_MONTH, Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) - 1);
		endTimecal.set(Calendar.DAY_OF_WEEK, 7);
		endTimecal.set(Calendar.HOUR_OF_DAY, 23);
		endTimecal.set(Calendar.MINUTE, 59);
		endTimecal.set(Calendar.SECOND, 59);
		return endTimecal.getTime();

	}

	public static Date getThisWeekBegin() {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.set(Calendar.DAY_OF_WEEK, 1);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 0);
		beginTimecal.set(Calendar.MINUTE, 0);
		beginTimecal.set(Calendar.SECOND, 0);
		beginTimecal.set(Calendar.MILLISECOND, 0);
		return beginTimecal.getTime();
	}
	
	public static Date getWeekBegin(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK,1);
		return c.getTime();
	}
	public static Date getWeekEnd(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK,7);
		return c.getTime();
	}
	
	/**
	 * 转换成数据用的时间
	 * @param date
	 * @return
	 */
	public static Date convertDbDate(Date date){
		Timestamp t = new Timestamp(date.getTime());
		return (Date)t;
	}

	/**
	 * 得到当前周的最后一天
	 * 
	 * @author loubin
	 * @version 1.0, 2010-12-30下午05:52:53
	 * @return
	 */
	public static Date getThisWeekEnd() {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.set(Calendar.DAY_OF_WEEK, 7);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 23);
		beginTimecal.set(Calendar.MINUTE, 59);
		beginTimecal.set(Calendar.SECOND, 59);
		beginTimecal.set(Calendar.MILLISECOND, 999);
		return beginTimecal.getTime();
	}

	/**
	 * 得到当前月的开始
	 * 
	 * @author loubin
	 * @version 1.0, 2010-12-30下午06:19:18
	 * @return
	 */
	public static Date getThisMonthBegin() {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.set(Calendar.DAY_OF_MONTH, 1);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 0);
		beginTimecal.set(Calendar.MINUTE, 0);
		beginTimecal.set(Calendar.SECOND, 0);
		beginTimecal.set(Calendar.MILLISECOND, 0);
		return beginTimecal.getTime();
	}

	/**
	 * 得到当前月的最后一天
	 * 
	 * @author loubin
	 * @version 1.0, 2010-12-30下午06:19:23
	 * @return
	 */
	public static Date getThisMonthEnd() {
		Calendar beginTimecal = Calendar.getInstance();
		int end = beginTimecal.getActualMaximum(Calendar.DAY_OF_MONTH);
		beginTimecal.set(Calendar.DAY_OF_MONTH, end);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 23);
		beginTimecal.set(Calendar.MINUTE, 59);
		beginTimecal.set(Calendar.SECOND, 59);
		beginTimecal.set(Calendar.MILLISECOND, 999);
		return beginTimecal.getTime();
	}

	/**
	 * 得到上月的开始
	 * 
	 * @author loubin
	 * @version 1.0, 2010-12-30下午06:19:18
	 * @return
	 */
	public static Date getLastMonthBegin() {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH) - 1);
		beginTimecal.set(Calendar.DAY_OF_MONTH, 1);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 0);
		beginTimecal.set(Calendar.MINUTE, 0);
		beginTimecal.set(Calendar.SECOND, 0);
		beginTimecal.set(Calendar.MILLISECOND, 0);
		return beginTimecal.getTime();
	}

	/**
	 * 得到上月的最后一天
	 * 
	 * @author loubin
	 * @version 1.0, 2010-12-30下午06:19:23
	 * @return
	 */
	public static Date getLastMonthEnd() {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH) - 1);
		int end = beginTimecal.getActualMaximum(Calendar.DAY_OF_MONTH);
		beginTimecal.set(Calendar.DAY_OF_MONTH, end);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 23);
		beginTimecal.set(Calendar.MINUTE, 59);
		beginTimecal.set(Calendar.SECOND, 59);
		beginTimecal.set(Calendar.MILLISECOND, 999);
		return beginTimecal.getTime();
	}

	public static Date getMonthBegin(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH,1);
		return c.getTime();
	}
	/**
	 * 得到当前年的第一天
	 * 
	 * @author loubin
	 * @version 1.0, 2010-12-30下午07:28:54
	 * @return
	 */
	public static Date getThisYearBegin() {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.set(Calendar.DAY_OF_YEAR, 1);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 0);
		beginTimecal.set(Calendar.MINUTE, 0);
		beginTimecal.set(Calendar.SECOND, 0);
		beginTimecal.set(Calendar.MILLISECOND, 0);
		return beginTimecal.getTime();
	}

	/**
	 * 得到当前年的第一天
	 * 
	 * @author loubin
	 * @version 1.0, 2010-12-30下午07:28:54
	 * @return
	 */
	public static Date getThisYearEnd() {
		Calendar beginTimecal = Calendar.getInstance();
		int end = beginTimecal.getActualMaximum(Calendar.DAY_OF_YEAR);
		beginTimecal.set(Calendar.DAY_OF_YEAR, end);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 23);
		beginTimecal.set(Calendar.MINUTE, 59);
		beginTimecal.set(Calendar.SECOND, 59);
		beginTimecal.set(Calendar.MILLISECOND, 59);
		return beginTimecal.getTime();
	}

	/**
	 * 得到年中-6-30
	 * 
	 * @author loubin
	 * @version 1.0, 2010-12-30下午07:32:52
	 * @return
	 */
	public static Date getThisHalfYear() {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.set(Calendar.MONTH, Calendar.JUNE);
		int end = beginTimecal.getActualMaximum(Calendar.DAY_OF_MONTH);
		beginTimecal.set(Calendar.JUNE, end);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 23);
		beginTimecal.set(Calendar.MINUTE, 59);
		beginTimecal.set(Calendar.SECOND, 59);
		beginTimecal.set(Calendar.MILLISECOND, 999);
		return beginTimecal.getTime();
	}

	/**
	 * 获取相差日期
	 * 
	 * @author loubin
	 * @version 1.0, 2011-1-18下午05:10:03
	 * @param updateDate
	 * @param date
	 * @return
	 */
	public static int diffDay(Date updateDate, Date currentDate) {
		if (updateDate == null || currentDate == null) {
			return 0;
		}
		if (updateDate.compareTo(currentDate) < 0) {
			Date tempDate = updateDate;
			updateDate = currentDate;
			currentDate = tempDate;
		}
		double day = (updateDate.getTime() - currentDate.getTime()) / (24.0 * 60.0 * 60.0 * 1000.0);

		return Double.valueOf(day).intValue();
	}

	public static Date getOpenMarketTime(Date changeDate) {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.setTime(changeDate);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 9);
		beginTimecal.set(Calendar.MINUTE, 30);
		beginTimecal.set(Calendar.SECOND, 0);
		beginTimecal.set(Calendar.MILLISECOND, 0);
		return beginTimecal.getTime();
	}

	public static Date getCloseMarketTime(Date date) {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.setTime(date);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 15);
		beginTimecal.set(Calendar.MINUTE, 0);
		beginTimecal.set(Calendar.SECOND, 0);
		beginTimecal.set(Calendar.MILLISECOND, 0);
		return beginTimecal.getTime();
	}

	public static Date getJYDataSyncOverTime(Date date) {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.setTime(date);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 19);
		beginTimecal.set(Calendar.MINUTE, 0);
		beginTimecal.set(Calendar.SECOND, 0);
		beginTimecal.set(Calendar.MILLISECOND, 0);
		return beginTimecal.getTime();
	}

	/**
	 * 获取年报时间
	 * 
	 * @author loubin
	 * @version 2011-4-11下午03:44:11
	 * @return
	 * @return Date
	 */
	public static Date getYearReportDate(String time) {
		String[] timeArray = time.split("-");
		int month = Integer.valueOf(timeArray[0]);
		int day = Integer.valueOf(timeArray[1]);
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.set(Calendar.MONTH, month - 1);
		beginTimecal.set(Calendar.DAY_OF_MONTH, day);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 0);
		beginTimecal.set(Calendar.MINUTE, 0);
		beginTimecal.set(Calendar.SECOND, 0);
		beginTimecal.set(Calendar.MILLISECOND, 0);
		return beginTimecal.getTime();
	}

	/**
	 * 获取组合月
	 * 
	 * @author loub
	 * @version 2011-6-28下午07:00:31
	 * @param createDate
	 * @return
	 * @return Date
	 */
	public static Date getThisPortfolioMonthEnd(Date createDate) {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.setTime(createDate);
		int end = beginTimecal.getActualMaximum(Calendar.DAY_OF_MONTH);
		beginTimecal.set(Calendar.DAY_OF_MONTH, end);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 23);
		beginTimecal.set(Calendar.MINUTE, 59);
		beginTimecal.set(Calendar.SECOND, 59);
		beginTimecal.set(Calendar.MILLISECOND, 999);
		return beginTimecal.getTime();
	}

	/**
	 * 上投组合生效时间控制，小于等于1点，还可以将生效时间设置为今天
	 * 
	 * @author loub
	 * @version 2012-2-8上午08:53:13
	 * @return
	 * @return Date
	 */
	public static Date getSTOpenMarketTime() {
		Calendar currnet = Calendar.getInstance();
		currnet.set(Calendar.HOUR_OF_DAY, 13);
		currnet.set(Calendar.MINUTE, 0);
		currnet.set(Calendar.SECOND, 0);
		currnet.set(Calendar.MILLISECOND, 0);
		return currnet.getTime();
	}
	
	/**
	 * 工银生效时间控制，小于等于1点，还可以将生效时间设置为今天
	 * 
	 * 
	 * @author loub
	 * @version 2012-2-8上午08:53:13
	 * @return
	 * @return Date
	 */
	public static Date getGYOpenMarketTime() {
		Calendar currnet = Calendar.getInstance();
		currnet.set(Calendar.HOUR_OF_DAY, 9);
		currnet.set(Calendar.MINUTE, 30);
		currnet.set(Calendar.SECOND, 0);
		currnet.set(Calendar.MILLISECOND, 0);
		return currnet.getTime();
	}
	
	/** 
     * @reference oracle.sql.Datum.timestampValue(); 
     * @return 
     */ 
	public static Timestamp getOracleTimestamp(Object value) { 
		try { 
		Class clz = value.getClass(); 
		Method m = clz.getMethod("timestampValue", null); 
		                       //m = clz.getMethod("timeValue", null); 时间类型 
		                       //m = clz.getMethod("dateValue", null); 日期类型 
		return (Timestamp) m.invoke(value, null); 
		
		} catch (Exception e) { 
		return null;
		} 
	}
	
	public static String toIraStr(Object value){
		if (value == null){
			return "";
		}
		String str = null;
		if (value instanceof String){
			str = String.valueOf(value);
		}
		if (value instanceof Date){
			str = DateUtil.dateToStr((Date)value, "yyyy-MM-dd HH:mm:ss");
		}
		Date today = DateUtil.currentStartDate();
		String thisDay = DateUtil.dateToStr(today, "yyyy-MM-dd");
		String yesterday = DateUtil.dateToStr(DateUtil.addDay(today, -1), "yyyy-MM-dd");
		String thisYear = thisDay.split("-")[0];
		if (str.startsWith(thisDay)){
			return str.replace(thisDay,"").trim().equals("") ? "今天":str.replace(thisDay, "").trim();
		}
		if (str.startsWith(yesterday)){
			return "昨天";
		}
		if (str.startsWith(thisYear)){
			return str.replace(thisYear+"-", "").trim();
		}
		return str;
	}
	
	public static void main(String[] args) {
//		System.out.println("当日的开始:" + getStartDate(new Date()));
//		System.out.println("当日的结束:" + getEndDate(new Date()));
//		System.out.println("当周的开始:" + getThisWeekBegin());
//		System.out.println("当周的结束:" + getThisWeekEnd());
//		System.out.println("上周的开始:" + getLastWeekBegin());
//		System.out.println("上周的结束:" + getLastWeekEnd());
//		System.out.println("当前月的开始:" + getThisMonthBegin());
//		System.out.println("当前月的结束:" + getThisMonthEnd());
//		System.out.println("上月的开始:" + getLastMonthBegin());
//		System.out.println("上月的结束:" + getLastMonthEnd());
//		System.out.println("当季的开始:" + getCurrentSeasonBegin());
//		System.out.println("当季的结束:" + getCurrentSeasonEnd());
//		System.out.println("当年的开始:" + getThisYearBegin());
//		System.out.println("当年的结束:" + getThisYearEnd());
//		System.out.println("当年的年中:" + getThisHalfYear());
//		System.out.println("当日开盘时间:" + getOpenMarketTime(new Date()));
//		System.out.println("年报时间:" + getYearReportDate("04-30"));
//		System.out.println("组合月:" + getThisPortfolioMonthEnd(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH)));
//		System.out.println("上投组合生效时间:" + getSTOpenMarketTime());
		
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 15);
		System.out.println(dateToStr(c.getTime(), "yyyy-MM-dd")+"周开始日:" + dateToStr(getWeekBegin(c.getTime()), "yyyy-MM-dd"));
		System.out.println(dateToStr(c.getTime(), "yyyy-MM-dd")+"周结束日:" + dateToStr(getWeekEnd(c.getTime()), "yyyy-MM-dd"));
		System.out.println(dateToStr(c.getTime(), "yyyy-MM-dd")+"月开始日:" + dateToStr(getMonthBegin(c.getTime()), "yyyy-MM-dd"));
	}
	
	public static String getIds(List<Long> ids){
		String inId = "";
		for (Long id : ids){
			inId += id+",";
		}
		if (ids.size() > 0){
			return inId.substring(0, inId.length()-1);
		}
		return "";
	}
}
