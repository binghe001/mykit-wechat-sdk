package io.mykit.wechat.utils.common;

import com.alibaba.fastjson.JSONObject;

import java.util.*;


/**
 * 关于星期的日期工具类
 * 
 * @author liuyazhuang
 *
 */
public class WeekDateUtils extends DateUtils {
	
	public static void main(String[] args) {
		System.out.println(JSONObject.toJSONString(getKeyFromMapByValue(getDateKeyWeekValue(-1, 1))));
		//1989-07-09
		Date startDate = new Date(1987, 6, 9);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1987);
		calendar.set(Calendar.MONTH, 6);
		calendar.set(Calendar.DATE, 9);
		System.out.println(getYears(calendar.getTime(), new Date(), Type.Year));
	}
	
	public enum Type{
		Year, Month, Date
	}
	
	/**
	 * 获取两个时间之间的年份
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getYears(Date startDate, Date endDate, Type type){
		int count = 0;
		 Calendar calBegin = Calendar.getInstance(); //获取日历实例  
		 Calendar calEnd = Calendar.getInstance(); 
		 calBegin.setTime(startDate);
		 calEnd.setTime(endDate);
		 if(Type.Year == type){
			 count =  calEnd.get(Calendar.YEAR) - calBegin.get(Calendar.YEAR);  
		 }else if(Type.Month == type){
			 count =  calEnd.get(Calendar.MONTH) - calBegin.get(Calendar.MONTH);  
		 }else if(Type.Date == type){
			 count =  calEnd.get(Calendar.DATE) - calBegin.get(Calendar.DATE);  
		 }
		 return count;
	}
	
	/**
	 * 获取指定月份的所有日期和星期集合
	 * @param offset:起止月份, 0：当前月, 1:下一个月; 2下下月； 以此类推...  -1:上一个月; -2:上上一个月 ; 以此类推....
	 * @param length:终止月份, 0：当前月, 1:下一个月; 2下下月； 以此类推...  -1:上一个月; -2:上上一个月 ; 以此类推....
	 * @return:日期和星期集合:星期为key 日期为value
	 */
	public static Map<String, List<String>> getKeyFromMapByValue(int offset, int length){
		return getKeyFromMapByValue(getDateKeyWeekValue(offset, length));
	}
	
	/**
	 * 将以date为key, week为value的map转化为以week为key, date为value的map
	 * @param dateWeek
	 * @return
	 */
	public static Map<String, List<String>> getKeyFromMapByValue(Map<String, String> dateWeek){
		Map<String, List<String>> weekDate = new HashMap<String, List<String>>();
		if(!CollectionUtils.isEmpty(dateWeek)){
			for(Map.Entry<String, String> entry : dateWeek.entrySet()){
				//获取日期集合
				List<String> list = weekDate.get(entry.getValue());
				if(ObjectUtils.isEmpty(list)){
					list = new ArrayList<String>();
				}
				list.add(entry.getKey());
				weekDate.put(entry.getValue(), list);
			}
		}
		return weekDate;
	}
	
	/**
	 * 获取指定月份的所有日期和星期集合
	 * @param offset:起止月份, 0：当前月, 1:下一个月; 2下下月； 以此类推...  -1:上一个月; -2:上上一个月 ; 以此类推....
	 * @param length:终止月份, 0：当前月, 1:下一个月; 2下下月； 以此类推...  -1:上一个月; -2:上上一个月 ; 以此类推....
	 * @return:日期和星期集合:日期为key 星期为value
	 */
	public static Map<String, String> getDateKeyWeekValue(int offset, int length){
		Map<String, String> map = new HashMap<String, String>();
		for(int i = offset; i <= length; i++){
			List<Date> list = getAllTheDateOftheMonth(new Date(),i);
			for(Date date: list){
				String weekDay = getDateOfWeek(date);
				map.put(parseDateToString(date, DATE_FORMAT), weekDay);
			}
		}
		return map;
	}
	
	/**
	 * 获取当前日期所在月份的所有日期，指定月份的所有日期
	 * @param date:当前日期
	 * @param n:1下一月;2:下下月..以此类推; -1:上月,-2:上上月...以此类推
	 * @return:返回指定月份的所有日期
	 */
	public static List<Date> getAllTheDateOftheMonth(Date date, int n) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, n);
		int month = cal.get(Calendar.MONTH);
		while(cal.get(Calendar.MONTH) == month){
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}
	
	
	
	/**
	 * 根据日期获得星期
	 * @param date
	 * @return
	 */
	public static String getDateOfWeek(Date date) {
		//String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if(intWeek < 0) intWeek = 0;
		return weekDaysCode[intWeek];
	}
	
	/**
	 * 根据日期获得星期
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(String dateStr, String format) {
		//String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Date date = DateUtils.parseStringDateToDate(dateStr, format);
		String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if(intWeek < 0) intWeek = 0;
		return weekDaysCode[intWeek];
	}
	/**
	 * 根据日期获得星期
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		//String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if(intWeek < 0) intWeek = 0;
		return weekDaysCode[intWeek];
	}

	/**
	 * 获得周一的日期
	 *
	 * @param date
	 * @return
	 */
	public static String getMonday(Date date,String formatString) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		return getSimpleDateFormat(formatString).format(calendar.getTime());

	}

	/**
	 * 获得周三的日期
	 *
	 * @param date
	 * @return
	 */
	public static String getWednesday(Date date, String formatString) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);

		return getSimpleDateFormat(formatString).format(calendar.getTime());

	}

	/**
	 * 获得周五的日期
	 *
	 * @param date
	 * @return
	 */
	public static String getFriday(Date date, String formatString) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);

		return getSimpleDateFormat(formatString).format(calendar.getTime());
	}

	/**
	 * 当前日期前几天或者后几天的日期
	 * 
	 * @param n
	 * @return
	 */
	public static String afterNDay(int n, String formatString) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		calendar.add(Calendar.DATE, n);

		Date date = calendar.getTime();

		String s = getSimpleDateFormat(formatString).format(date);

		return s;

	}

	/**
	 * 判断两个日期是否在同一周
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}
}
