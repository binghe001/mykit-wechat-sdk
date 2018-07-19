package io.mykit.wechat.utils.common;

import java.math.BigDecimal;

/**
 * 距离工具类
 * @author liuyazhuang
 *
 */
public final class DistanceUtils {
	/**
	 * 格式化距离
	 * @param distance
	 * @return
	 */
	public static String parseDoubleDistanceToStringFormat(Double distance){
		if(distance >= 1000){
			double b=distance/1000.000;
	        BigDecimal big=new BigDecimal(b).setScale(1,BigDecimal.ROUND_HALF_UP);
	        return big.doubleValue()+"km";
		}else{
			Long b=Math.round(distance);//保留整数
			return b+"m";
		}
	}
}	
