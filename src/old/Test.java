package old;

import java.math.RoundingMode;
import java.text.ChoiceFormat;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Test {
	public static void main(String agrs[]) throws Exception {
//		NumberFormat instance = NumberFormat.getInstance(Locale.CHINA);
		
		//￥20.31  getCurrencyInstance
//		System.out.println(NumberFormat.getCurrencyInstance().format(20.31254));
		//20
//		System.out.println(NumberFormat.getIntegerInstance().format(20.31254));
		//2031%
//		System.out.println(NumberFormat.getPercentInstance().format(20.31254));
		//20.313
	//  System.out.println(NumberFormat.getNumberInstance().format(20.31254));
	
//		instance.setMaximumFractionDigits(4);
//		instance.setMinimumFractionDigits(2);
//		instance.setMaximumIntegerDigits(6);
//		instance.setMinimumIntegerDigits(2);
//		System.out.println(instance.format(2.1));
//		instance.setParseIntegerOnly(true);
//		ParsePosition parsePosition = new ParsePosition(1);
//		System.out.println(instance.parse("ee100.20",parsePosition));
//		System.out.println(parsePosition.getErrorIndex());
//		DecimalFormat decimalFormat = new DecimalFormat(",##0.00");
//		System.out.println(new DecimalFormat("这是一个自定意格式化'#' ,##0.00 fsds").format(.1));
		//System.out.println(new DecimalFormat("0.00").format(152.236));
		Date date = new Date();
		System.out.println(DateFormat.getDateInstance().format(date));
		//System.out.println(DateFormat.getTimeInstance().format(date));
		//System.out.println(DateFormat.getDateTimeInstance().format(date,new StringBuffer("--"),new FieldPosition(0)));
		
//		DateFormat dateInstance = DateFormat.getDateInstance();
//		
//		Calendar calendar = dateInstance.getCalendar();
//		
//		System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
//		calendar.set(Calendar.DATE, 10);
//		calendar.add(Calendar.DATE, -1);
//		System.out.println(calendar.get(Calendar.DATE));
//		Calendar instance = Calendar.getInstance();
//		System.out.println(instance.getTime().toLocaleString());
		SimpleDateFormat simpleDateFormat = 
				new SimpleDateFormat("yyyy-MM-dd G 'hh' HH:mm:ss z");
//		simpleDateFormat.applyPattern("yyyy-MM-dd G 'hh' HH:mm:ss z");
		String format = simpleDateFormat.format(new Date());
		System.out.println(format);
		
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd G 'hh' HH:mm:ss z");
		System.out.println(sp.parse(format));
	}
}
