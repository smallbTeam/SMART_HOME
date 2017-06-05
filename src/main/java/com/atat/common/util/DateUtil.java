package com.atat.common.util;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * time tools
 * @Description: 
 * @author anjianchao
 * @date 2015-5-22 下午04:36:15
 */
public class DateUtil {
	
	/**
	 * 利用枚举标识时间格式
	 * @Description: 
	 * @author anjianchao
	 * @date 2015-3-25 下午02:41:51
	 */
	public enum DatePattern{
		
		YYYYMMDDHHmmss("yyyy-MM-dd HH:mm:ss"),
		YYYYMMDD("yyyy-MM-dd"),
		YYYYMMDDHHmmss1("yyyy/MM/dd HH:mm:ss"),
		YYYYMMDD1("yyyyMMdd"),
		YYYYMMDDHHmmss2("yyyyMMddHHmmss");
		private String value;
		
		DatePattern(String value){
			this.value = value;
		}
		
		public String getValue(){
			
			return this.value;
		}
	}
	/**
	 * 得到当前时间
	 * @return
	 */
	public static String getDate(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDateTime = dateFormat.format( now ); 
		return currentDateTime;
	}
	
	/**
	 * 得到当前时间
	 * @return
	 */
	public static String getStrCurrentDate(String datePattern){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		String currentDateTime = dateFormat.format( now ); 
		return currentDateTime;
	}
	/**
	 * 得到当前时间
	 * @return
	 */
	public static Date getDateCurrentDate(String datePattern){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		String currentDateTime = dateFormat.format( now ); 
		Date date = null;
		try {
			date = dateFormat.parse(currentDateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 格式化时间
	 * @param datePattern
	 * @return
	 * @throws ParseException 
	 */
	public static String getCurrentTimeString(String datePattern,String date) throws ParseException{
		return new SimpleDateFormat(datePattern).format(new SimpleDateFormat(datePattern).parse(date));
	}
	/**
	 * 格式化时间
	 * @param datePattern
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getCurrentTimeDate(String datePattern,String date)throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		String currentDateTime = dateFormat.format(new SimpleDateFormat(datePattern).parse(date)); 
		Date getDate = null;
		try {
			getDate = dateFormat.parse(currentDateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getDate;
	}
	
	/**
	 * 格式化时间
	 * @param datePattern
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getCurrentTimeString(String datePattern,Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		String currentDateTime = dateFormat.format( date ); 
		Date getDate = null;
		try {
			getDate = dateFormat.parse(currentDateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getDate;
	}
	
	/**
	 * @param type (year:减一年；month:减一月；day:减一天)
	 * @param paramTime
	 * @return
	 */
	public static String timeSubtraction(String type,String paramTime){
		String resultTime = "";// 结果时间
		SimpleDateFormat sdf = new SimpleDateFormat(DatePattern.YYYYMMDD.getValue());//格式化
		Calendar cal = Calendar.getInstance();//得到一个Calendar的日历实例
		try {
			cal.setTime(sdf.parse(paramTime));
			if(StringUtils.equals("year", type)){
				cal.add(Calendar.YEAR,-1);
			}else if(StringUtils.equals("month", type)){
				cal.add(Calendar.MONTH, -1);
			}else if(StringUtils.equals("day", type)){
				cal.add(Calendar.DATE,-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultTime = sdf.format(cal.getTime());
		return resultTime;
	}
	/**
	 * 时间相减startTime 开始时间 paramSystemTime 系统时间
	 * @return 时间相减工具方法 day(日) 
	 */
	public static int timeSubtractionDay(String paramSystemTime,String startTime) {
		int resultTime = 0;// 结果时间
		SimpleDateFormat sdf = new SimpleDateFormat(DatePattern.YYYYMMDD.getValue());// 格式化对象
		Calendar calSystem = Calendar.getInstance();// 系统时间
		Calendar calStart = Calendar.getInstance();// 交易开始时间
		try {
			calSystem.setTime(sdf.parse(paramSystemTime));
			calStart.setTime(sdf.parse(startTime));
			long l=calSystem.getTimeInMillis()-calStart.getTimeInMillis();
			resultTime=new Long(l/(1000*60*60*24)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultTime;
	}
	/**
	 * 时间相减 startTime 开始时间 paramSystemTime 系统时间
	 * @return 时间相减工具方法 Second(秒) 
	 */
	public static int timeSubtractionSecond(String paramSystemTime,String startTime) {
		int resultTime = 0;// 结果时间
		SimpleDateFormat sdf = new SimpleDateFormat(DatePattern.YYYYMMDDHHmmss.getValue());// 格式化对象
		Calendar calSystem = Calendar.getInstance();// 系统时间
		Calendar calStart = Calendar.getInstance();// 交易开始时间
		try {
			calSystem.setTime(sdf.parse(paramSystemTime));
			calStart.setTime(sdf.parse(startTime));
			long l=calStart.getTimeInMillis()-calSystem.getTimeInMillis();
			resultTime=new Long(l/1000).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultTime;
	}

	/**
	 * 将字符串装换为时间
	 * @param date
	 * @return
	 */
	public static Date getDateByStr(String date){
		
		DateFormat format1 = new SimpleDateFormat(DatePattern.YYYYMMDDHHmmss.getValue());
		
		try {
			return format1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
		
	} 
	//凌晨时间
	public static Date getMon() {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    Date end = calendar.getTime();
	    SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		 SimpleDateFormat formater2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		 try {
			end = formater2.parse(formater.format(new Date())+ " 23:59:59");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return end;
	}
	//获得当前日期,格式:yyyymmdd
	public static String getCurrentDate2(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//

		String currentDateTime = dateFormat.format( now ); 
		//System.out.println(currentDateTime);

		return currentDateTime;
	}
	//判断是否为一天 a结束时间  
	public static int sameday(Date a){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String d1 = sdf.format(a);//第一个时间
		String d2 = sdf.format(new Date());//第二个时间
		if(d1.equals(d2)){
			return 1;
		}else{
			return 0;
		}
	}
	
	public static String changeDateToString(Date date,DatePattern dp){
		SimpleDateFormat sdf = new SimpleDateFormat(dp.getValue());
		String d1 = sdf.format(date);//第一个时间
		return d1;
	}

}
