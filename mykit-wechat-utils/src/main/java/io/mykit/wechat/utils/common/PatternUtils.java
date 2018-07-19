package io.mykit.wechat.utils.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {
	
	private static final Pattern NaturalNumberPattern = Pattern.compile("[0-9]*");
	public static boolean isNaturalNumber(String str)
    {
          Matcher isNum = NaturalNumberPattern.matcher(str);
          if( !isNum.matches() )
          {
                return false;
          }
          return true;
    }
}
