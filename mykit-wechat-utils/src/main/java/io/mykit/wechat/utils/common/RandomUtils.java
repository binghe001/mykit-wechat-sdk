package io.mykit.wechat.utils.common;

import java.util.*;

/**
 * 产生随机数
 * @author liuyazhuang
 *
 */
public final class RandomUtils {
	
	 /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public synchronized static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
	
	/**
	 * 随机数产生器
	 * @param limit
	 * @return
	 */
	public synchronized static Integer generateRandomNumbers(Integer limit){
		return new Random().nextInt(limit);
	}
	
	/**
	 * 生成随机验证码
	 * @param length：表示生成字符串的长度
	 * @return
	 */
	public synchronized static String getRandomString(int length) { //length
	    return generateRandomString(length);   
	 }
	
	public static void main(String[] args) {
		System.out.println(RandomUtils.generateDateOrderNum(new Date(), 9));;
	}

	/**
	 * 生成随机数字
	 * @param length
	 * @return
	 */
	public static String generateRandomString(int length) {
		String base = "1234567890";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();
	} 
	
	/**
	 * 生成带有日期的订单编号
	 * @param length
	 * @return
	 */
	public synchronized static String generateDateOrderNum(Date date, int length){
		String dateStr = DateUtils.parseDateToString(new Date(), DateUtils.DATE_TIME_NO_FORMAT);
		return dateStr.concat(getDateOrderNum(length));
	}
	/**
	 * 生成订单编号
	 * @param length
	 * @return
	 */
	private static String getDateOrderNum(int length) {
		String num = generateRandomString(length);
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < num.length(); i++){
			list.add(String.valueOf(num.charAt(i)));
		}
		StringBuffer buffer = new StringBuffer();
		Collections.shuffle(list);
		for(int i = 0; i < list.size(); i++){
			buffer.append(list.get(i));
		}
		return buffer.toString();
	}
	
	
	/**
	 * 生成订单编号
	 * @param length
	 * @return
	 */
	public synchronized static String generateOrderNum(int length){
		return getOrderNum(length);
	}
	

	/**
	 * 生成订单编号
	 * @param length
	 * @return
	 */
	private static String getOrderNum(int length) {
		String num = System.currentTimeMillis()+generateRandomString(length);
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < num.length(); i++){
			list.add(String.valueOf(num.charAt(i)));
		}
		StringBuffer buffer = new StringBuffer();
		Collections.shuffle(list);
		for(int i = 0; i < list.size(); i++){
			buffer.append(list.get(i));
		}
		return buffer.toString();
	}
	
	/**
	 * 优惠券编码
	 * @param length
	 * @return
	 */
	public synchronized static String generateNum(int length){
		String number = getOrderNum(length);
		return number.substring(0, length);
	}
	
}
