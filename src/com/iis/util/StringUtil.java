package com.iis.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.druid.filter.config.ConfigTools;

public class StringUtil {
	private static Logger log = LoggerFactory.getLogger(StringUtil.class);
	
	public static String covertListToString(List<String> list){
		StringBuffer sb = new StringBuffer();
		for (String s : list) {
			sb.append(s + ",");
		}
		
		//判空
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
	/**
	 * Description: list转换成带点的'11','12'字符串
	 * @param: @param list
	 * @param: @return
	 * @author 戴泳材
	 * @date：2014-4-29 下午4:19:29
	 */
	public static String covertListToPointString(List<String> list){
		StringBuffer sb = new StringBuffer();
		for (String s : list) {
			sb.append("'" + s + "',");
		}
		
		//判空
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
	public static String covertIntListToString(List<Integer> list){
		StringBuffer sb = new StringBuffer();
		for (Integer s : list) {
			sb.append(s + ",");
		}
		//判空
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	/**
	 * 把一个数字转换成指定位数的字符串，不足位数前面补0
	 * @param i 数字
	 * @param c 位数
	 * @return
	 */
	public static String numberToString(int i,int c){
		String ret = String.valueOf(i);
		for (int a = String.valueOf(i).length(); a < c; a++){
			ret = "0" + ret;
		}
		return ret;
	}
	
	private static String replace(String value) {
		return value.matches("^\'(.*)\'$") ? value : "'" + value + "'";
	}

	public static String change(String value) {
		StringBuffer sb = new StringBuffer();
		if (-1 != value.indexOf(',')) {
			String[] temp = value.split(",");
			for (int i = 0; i < temp.length; i++) {
				sb.append(replace(temp[i]));
				sb.append(',');
			}
			sb.deleteCharAt(sb.length() - 1);
		} else {
			sb.append(replace(value));
		}
		return sb.toString();
	}

	public static String spFormat(String value) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			sb.append('[');
			sb.append(Integer.toHexString(value.codePointAt(i)));
			sb.append(']');
		}
		return sb.toString();
	}

	public static String dateFormat(String yyyyMMdd) {
		StringBuffer sb = new StringBuffer();
		sb.append(yyyyMMdd.substring(0, 4));
		sb.append("-");
		sb.append(yyyyMMdd.substring(4, 6));
		sb.append("-");
		sb.append(yyyyMMdd.substring(6, 8));
		return sb.toString();
	}

	public static String dateShowFormat(String yyyyMMdd) {
		StringBuffer sb = new StringBuffer();
		sb.append(yyyyMMdd.substring(0, 4));
		sb.append("年");
		sb.append(yyyyMMdd.substring(4, 6));
		sb.append("月");
		sb.append(yyyyMMdd.substring(6, 8));
		sb.append("日");
		return sb.toString();
	}

	public static String dateUnFormat(String yyyy_MM_dd) {
		StringBuffer sb = new StringBuffer();
		sb.append(yyyy_MM_dd.substring(0, 4));
		sb.append(yyyy_MM_dd.substring(5, 7));
		sb.append(yyyy_MM_dd.substring(8, 10));
		return sb.toString();// 测试

	}
	
	public static String numberToLarge(Integer num){
		String temp=String.valueOf(num.intValue());
		if(num==1){
			temp="一";
		}
		else if(num==2){
			temp="二";
		}
		else if(num==3){
			temp="三";
		}
		else if(num==4){
			temp="四";
		}
		else if(num==5){
			temp="五";
		}
		else if(num==6){
			temp="六";
		}
		else if(num==7){
			temp="七";
		}
		else if(num==8){
			temp="八";
		}
		else if(num==9){
			temp="九";
		}else if(num == 10){
			temp="十";
		} else if(num <20) {
			String str=String.valueOf(num);
			temp="十"+baiceNumberToLarge(Integer.parseInt(str.substring(1, 2)));
			
		} else if(num < 100) {
			String str=String.valueOf(num);
			temp=baiceNumberToLarge(Integer.parseInt(str.substring(0, 1)))+"十";
			
			if(!"0".equals(str.substring(1, 2))){
				temp+=baiceNumberToLarge(Integer.parseInt(str.substring(1, 2)));
			}
		} else if(num<1000) {
			String str=String.valueOf(num);
			temp=baiceNumberToLarge(Integer.parseInt(str.substring(0, 1)))+"百";
			
			if(!"0".equals(str.substring(1, 2))){
				temp+=baiceNumberToLarge(Integer.parseInt(str.substring(1, 2)))+"十";
			}			
			if(!"0".equals(str.substring(2, 3))){
				temp+=baiceNumberToLarge(Integer.parseInt(str.substring(1, 2)));
			}			
		}
		return  temp;
	}
	
	public static String baiceNumberToLarge(Integer num){
		String temp=String.valueOf(num.intValue());
		if(num==1){
			temp="一";
		}
		else if(num==2){
			temp="二";
		}
		else if(num==3){
			temp="三";
		}
		else if(num==4){
			temp="四";
		}
		else if(num==5){
			temp="五";
		}
		else if(num==6){
			temp="六";
		}
		else if(num==7){
			temp="七";
		}
		else if(num==8){
			temp="八";
		}
		else if(num==9){
			temp="九";
		}
		else if(num==10){
			temp="十";
		}
		else if(num==11){
			temp="十一";
		}
		
		return  temp;
	}	

	public static String baiceNumberToSpecial(Integer num) {
		String temp = String.valueOf(num.intValue());
		if (num == 1) {
			temp = "①";
		} else if (num == 2) {
			temp = "②";
		} else if (num == 3) {
			temp = "③";
		} else if (num == 4) {
			temp = "④";
		} else if (num == 5) {
			temp = "⑤";
		} else if (num == 6) {
			temp = "⑥";
		} else if (num == 7) {
			temp = "⑦";
		} else if (num == 8) {
			temp = "⑧";
		} else if (num == 9) {
			temp = "⑨";
		} else if (num == 10) {
			temp = "⑩";
		}
		return temp;
	}
	
	public static String findANDReplace(String sql, String newStr) {
		String temp = "";
		if (sql.indexOf("ISWRITE=0") != -1) {
			temp = sql.replace("ISWRITE=0", newStr);
			return temp;
		} else if (sql.indexOf("ISWRITE=1") != -1) {
			temp = sql.replace("ISWRITE=1", newStr);
			return temp;
		} else if (sql.indexOf("1=1") != -1) {
			temp = sql.replace("1=1", newStr);
			return temp;
		}
		return null;

	}

	/**
	 * 时间格式转化 20100315--->2010-03-15
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTime(String time) {

		String strTime = time.substring(0, 4);
		strTime += "-";
		strTime += time.substring(4, 6);
		strTime += "-";
		strTime += time.substring(6, 8);
		return strTime;
	}

	/**
	 * 分解字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String splitString(String str) {

		if (null == str || "".equals(str) || str.indexOf("_") == -1) {
			return str;
		} else {
			String[] splitStr = str.split("_");
			return splitStr[0];
		}

	}

	/**
	 * null 转换
	 * 
	 * @param num
	 * @return
	 */
	public static Integer nullToInteger(Integer num) {

		if (null != num) {
			return num;
		} else {
			return 0;
		}

	}

	public static Integer nullToInteger(String num) {

		if (null == num || "".equals(num) || "-1".equals(num)) {
			return 0;
		} else {
			return Integer.parseInt(num);
		}
	}

	public static Double nullToDouble(String num) {

		if (null == num || "".equals(num) || "-1".equals(num)) {
			return 0.00;
		} else {
			return Double.valueOf(num);
		}
	}

	public static Double nullToDouble(Double num) {

		if (null != num) {
			return num;
		} else {
			return 0.00;
		}
	}

	public static double nullToDouble(Integer num) {

		if (null != num) {
			return num;
		} else {
			return 0.00;
		}

	}

	public static String formatNumber(Integer num1, Integer num2) {

		String reStr = "";
		DecimalFormat myFormatter = new DecimalFormat("#.##");
		if (0 != nullToInteger(num2)) {
			double values = nullToDouble(num1) / nullToDouble(num2);
			reStr = myFormatter.format(values * 100);
		} else {
			reStr = "100";
		}
		return reStr;
	}

	public static String getFormatDate(String date) {

		NumberFormat n = NumberFormat.getNumberInstance();
		String reStr = "";
		if (null == date) {
			return "0.00";
		} else {
			double v = Double.parseDouble(date);
			n.setMinimumFractionDigits(2);
			reStr = n.format(v);
		}
		return reStr;
	}

	/**
	 * 数据格式化
	 * 
	 * @param num
	 * @return
	 */
	public static double formatNum(double... nums) {

		DecimalFormat myFormatter = new DecimalFormat("#.##");
		double d = 0.00;
		for (double num : nums) {
			d += num;
		}
		String reNum = myFormatter.format(d);
		return Double.valueOf(reNum);
	}
	
	/**
	 * 数据格式化
	 * @param num
	 * @param pattern "#.####"
	 * @return
	 */
	public static double formatNum(double num ,String pattern) {

		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String reNum = myFormatter.format(num);
		return Double.valueOf(reNum);
	}
	
	public static String formatStringNum(double num ,String pattern) {

		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String reNum = myFormatter.format(num);
		return reNum;
	}

	/**
	 * 数据格式化
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static String formatNumber(Double num1, Double num2) {

		String reStr = "";
		DecimalFormat myFormatter = new DecimalFormat("#.##");
		if (0 != nullToDouble(num2)) {
			double values = nullToDouble(num1) / nullToDouble(num2);
			reStr = myFormatter.format(values * 100);
		} else {
			reStr = "100";
		}
		return reStr;
	}

	/**
	 * null转换成空格
	 * 
	 * @param str
	 * @return
	 */
	public static String toNullTransString(String str) {

		if (null == str) {
			return "";
		}
		return str;
	}

	/**
	 * String对象转换成toString或者"",主要用于map取值
	 * 
	 * @param str
	 * @return
	 */
	public static String toNullObjectTransString(Object str) {

		if (null == str) {
			return "";
		}
		return str.toString();
	}

	/**
	 * nulldouble对象转换为string
	 * 
	 * @param str
	 * @return
	 */
	public static String nullToDouble(Object str) {

		if (null == str) {
			return "0.00";
		}
		Double tmp = Double.parseDouble(str.toString());
		return tmp.toString();
	}
	/**
	 * 两个时间进行比较，返回时间大的
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */

	public static String timeCompare(String time1, String time2) {

		SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat smt = new SimpleDateFormat("yyyyMMdd");
		String performTime = sFormat.format(new Timestamp(System
				.currentTimeMillis()));

		if (null == time1 || "".equals(time1)) {
			time1 = performTime;
		}

		if (null == time2 || "".equals(time2)) {
			time2 = performTime;
		}

		try {
			if (smt.parse(time1).after(smt.parse(time2))) {
				return time2;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time1;
	}

	public static synchronized String outputToJSArray(String[] strs) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			sb.append("'" + strs[i] + "',");
		}
		if (sb.length() != 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	public static synchronized String outputToJSArrayb(String[] strs) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			sb.append("\"" + strs[i] + "\",");
		}
		return sb.deleteCharAt(sb.length() - 1).toString();
	}

	/**
	 * 审计索引号编写规则: 业务属性拼音缩写+发现违规事实的年份（初次设置均为2010）+操作属性+违规类型（两层）+违规事实顺序号
	 * 本方法不包括违规事实顺序号
	 * 
	 * @param recordType
	 *            业务属性拼音缩写
	 * @param operateProperty
	 *            操作属性
	 * @param disciplineType
	 *            违规类型
	 * @return
	 */
	public static String getRecordId(String recordType, String operateProperty,
			String disciplineType) {
		StringBuffer recordId = new StringBuffer();
		recordId.append(recordType);
		recordId.append(DateUtil.convertDatetoMyString("yyyy", new Date()));
		recordId.append(operateProperty);
		recordId.append(disciplineType);
		recordId.append("00");
		return recordId.toString();
	}

	/**
	 * 数组的方式添加在store中
	 * 
	 * @param list
	 *            要显示字段值
	 * @return
	 */
	public static String getArrayDate(List<String[]> showList) {
		StringBuffer arrayString = new StringBuffer();
		arrayString.append("[");
		for (String str[] : showList) {
			arrayString.append("[");
			for (String field : str) {
				arrayString.append("'");
				if (null != field) {
					String temp = field;
					temp = temp.replaceAll("\'", "");
					temp = temp.replaceAll("\n", "");
					temp = temp.replaceAll("\r", "");
					temp = temp.replaceAll("\r\n'", "");
					arrayString.append(temp);
				} else {
					arrayString.append(field);
				}
				arrayString.append("',");
			}
			arrayString.deleteCharAt(arrayString.length() - 1);
			arrayString.append("],");

		}
		if (showList.size() > 0) {
			arrayString.deleteCharAt(arrayString.length() - 1);
		}
		arrayString.append("]");
		return arrayString.toString();
	}

	/**
	 * 数组的方式添加在store中
	 * 
	 * @param list
	 *            要显示字段值
	 * @return
	 */
	public static String getArrayData(String[] array) {
		StringBuffer arrayString = new StringBuffer();
		arrayString.append("[");
		for (String field : array) {
			arrayString.append("");
			if (null != field) {
				String temp = field;
				temp = temp.replaceAll("\'", "");
				temp = temp.replaceAll("\n", "");
				temp = temp.replaceAll("\r", "");
				temp = temp.replaceAll("\r\n'", "");
				arrayString.append(temp);
			} else {
				arrayString.append(field);
			}
			arrayString.append(",");
		}
		arrayString.deleteCharAt(arrayString.length() - 1);
		arrayString.append("]");

		return arrayString.toString();
	}

	/**
	 * 用json对象的方式，展现数据
	 * 
	 * @param showList
	 * @param totalRecords
	 * @param feilds
	 *            要显示的字段
	 * @param cnFeilds
	 *            显示的中文名
	 * @return
	 */
	public static String getDynamiColumnJson(List<String[]> showList,
			String[] feilds, String[] cnFeilds, int totalRecords) {
		StringBuffer jsonString = new StringBuffer();
		jsonString.append(getJsonDate(showList, feilds, totalRecords));
		jsonString = jsonString.deleteCharAt(jsonString.length() - 1);

		jsonString.append(",'columModle':[");
		int i = 0;
		for (String c : cnFeilds) {
			jsonString.append("{'header': '" + c + "','dataIndex': '"
					+ feilds[i] + "'}, ");
			i++;
		}
		if (cnFeilds.length > 0) {
			jsonString.deleteCharAt(jsonString.length() - 1);
		}
		jsonString.append("]");

		jsonString.append(",'fieldsNames':[");
		int j = 0;
		for (String f : feilds) {
			jsonString.append("{name: '" + f + "'},");
			j++;
		}
		if (feilds.length > 0) {
			jsonString.deleteCharAt(jsonString.length() - 1);
		}
		jsonString.append("]");
		jsonString.append("}");

		return jsonString.toString();

	}

	/**
	 * 用json对象的方式，展现数据
	 * 
	 * @param showList
	 * @param totalRecords
	 * @param feilds
	 *            要显示的字段
	 * @return
	 */
	public static String getJsonDate(List<String[]> showList, String[] feilds,
			int totalRecords) {

		StringBuffer jsonString = new StringBuffer();
		jsonString.append("{");
		jsonString.append("totalRecords:" + totalRecords + ",");
		jsonString.append("'results':[");
		for (String[] str : showList) {
			int i = 0;
			jsonString.append("{");
			for (String string : str) {
				jsonString.append(feilds[i].trim() + ":'");
				String s = "";
				if (null == string || "".equals(string.trim())
						|| "null".equalsIgnoreCase(string.trim())) {
					jsonString.append(s + "',");
				} else {
					jsonString.append(replaceString(string.trim()) + "',");  //去掉换行符replaceString
				}

				i++;
			}
			jsonString.deleteCharAt(jsonString.length() - 1);
			jsonString.append("},");
		}
		if (showList.size() > 0) {
			jsonString.deleteCharAt(jsonString.length() - 1);
		}
		jsonString.append("]}");
		return jsonString.toString();
	}

	/**
	 * 用模型数据 json对象的方式，展现数据
	 * 
	 * @param showList
	 * @param totalRecords
	 * @param feilds
	 *            要显示的字段
	 * @return
	 */
	public static String getModelJsonDate(List<String[]> showList,
			String[] feilds, int totalRecords) {
		StringBuffer jsonString = new StringBuffer();
		jsonString.append("{");
		jsonString.append("totalRecords:" + totalRecords + ",");
		jsonString.append("'results':[");
		int j = 0;
		for (String[] str : showList) {
			int i = 0;
			jsonString.append("{");

			for (int k = 0; k < str.length; k++) {
				String string = str[k];
				jsonString.append(feilds[i].trim() + ":'");
				String s = "";
				if (null == string.trim() || "".equals(string.trim())
						|| "null".equalsIgnoreCase(string.trim())) {
					jsonString.append(s + "',");
					// }else if("查看".equals(feilds[i].trim())){
					// jsonString.append("影像',");
					// k=-1;
				} else {
//					if ((feilds[i].indexOf("金额")) != -1
//							|| (feilds[i].indexOf("余额")) != -1) {
//						jsonString.append("￥" + string + "',");
//					} else {
						jsonString.append(string + "',");
//					}
				}
				i++;
			}
			jsonString.append("num:'" + j + "'");
			jsonString.append("},");
			j++;
		}
		if (showList.size() > 0) {
			jsonString.deleteCharAt(jsonString.length() - 1);
		}
		jsonString.append("]}");
		return jsonString.toString();
	}

	public static List<String[]> getResult(List<String[]> resultList,
			List<Integer> cs, List<String> img) {

		List<String[]> list = new ArrayList<String[]>();
		for (String[] row : resultList) {
			StringBuffer para = new StringBuffer();
			StringBuffer para2 = new StringBuffer();
			String[] ro = (String[]) row;
			for (int i = 0; i < cs.size(); i++) {
				para.append(img.get(i) + "=" + ro[cs.get(i)].trim() + "&");
				para2.append(img.get(i) + "*" + ro[cs.get(i)].trim() + "$");
			}
			String paras = para.deleteCharAt(para.length() - 1).toString();
			String str[] = new String[ro.length + 1];
			str[0] = paras.trim();
			for (int i = 1; i < str.length; i++) {
				str[i] = ro[i - 1].trim();
			}
			list.add(str);
		}

		return list;
	}

	public static List<String[]> getResult(List<String[]> resultList,
			List<Integer> cs, List<String> img, int imgLocation) {

		List<String[]> list = new ArrayList<String[]>();
		for (String[] row : resultList) {
			StringBuffer para = new StringBuffer();
			StringBuffer para2 = new StringBuffer();
			String[] ro = (String[]) row;
			for (int i = 0; i < cs.size(); i++) {
				para.append(img.get(i) + "=" + ro[cs.get(i) + 1] + "&");
				para2.append(img.get(i) + "*" + ro[cs.get(i) + 1] + "$");
			}
			String paras = para.deleteCharAt(para.length() - 1).toString();
			String str[] = new String[ro.length + 1];

			for (int i = 0; i < ro.length + 1; i++) {
				if (i < imgLocation) {
					str[i] = ro[i].trim();
				} else if (i > imgLocation) {
					str[i] = ro[i - 1].trim();
				} else {
					str[imgLocation] = paras.trim();
				}
			}
			list.add(str);
		}

		return list;
	}

	/**
	 * 过滤字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String getStringFormat(String str) {
		String[] format = str.split("@");
		List<String> fieldList = new ArrayList<String>();// 字段名
		List<String> valueList = new ArrayList<String>();// 值
		int i = 0;
		for (String string : format) {
			if (i % 2 == 0) {
				fieldList.add(string);
			} else {
				valueList.add(string);
			}
			i++;
		}
		StringBuffer filters = new StringBuffer();
		int j = 0;
		for (String s : valueList) {
			filters.append(fieldList.get(j));
			filters.append(" like '%");
			filters.append(s + "%'");
			if (j < valueList.size() - 1) {
				filters.append(" and ");
			}
			j++;
		}
		return filters.toString();
	}

	/**
	 * 过滤字符串 IN操作 字符组合成IN操作，此方式适合过滤条件为List的情况
	 * 
	 * @param str
	 * @return
	 */
	public static String getStringFormatIn(String str, Map replaceStr,
			Map strType) {
		String[] format = str.split("@");
		List<String> fieldList = new ArrayList<String>();// 字段名
		List<String> valueList = new ArrayList<String>();// 值
		int i = 0;
		for (String string : format) {
			if (i % 2 == 0) {
				fieldList.add(string);
			} else {
				valueList.add(string);
			}
			i++;
		}
		StringBuffer filters = new StringBuffer();
		int j = 0;

		for (String s : valueList) {
			String fieldName = fieldList.get(j);
			if (j > 0) {
				filters.append(" and ");
			}
			filters.append(fieldName);
			filters.append(" in(");
			String[] sArray = s.split(",");
			int x = 0;
			for (String sy : sArray) {
				if (replaceStr.size() > 0) {
					if (!"int".equals(strType.get(fieldName))) {// 字符类型
						filters.append("'");
					}
					filters.append(replaceStr.get(sy));
					if (!"int".equals(strType.get(fieldName))) {
						filters.append("'");
					}
				} else {
					if (!"int".equals(strType.get(fieldName))) {
						filters.append("'");
					}
					filters.append("'" + sy + "'");
					if (!"int".equals(strType.get(fieldName))) {
						filters.append("'");
					}
				}
				x++;
				if (x <= sArray.length - 1) {
					filters.append(",");
				}

			}
			filters.append(" )");
			j++;
		}
		return filters.toString();
	}

	
	/**
	 * 将过滤条件转化为哈希结构
	 * 
	 * @param filters 
	 * @return
	 */
	public static Map<String,String[]> getFiltersMap(String filters) {
		if(StringUtils.isEmpty(filters)) {
			return null;
		}
		Map<String,String[]> map = new HashMap<String,String[]>();
		int index = 0;
		String key = null;
		String[] values = null;
		String[] filtersArr = filters.split("@");
		for(String filter:filtersArr) {
			if(index % 2 == 0) {
				key = filter;
			} else {
				values = filter.split(",");
				map.put(key, values);
			}
			index++;
		}
		return map;
	}


	public static String getDoubleString(String str) {
		String splitStr[] = str.split("E-");
		StringBuffer tempStr = new StringBuffer("0.");
		if (splitStr.length > 1) {
			String pointStr[] = splitStr[0].split("\\.");
			int num = Integer.parseInt(splitStr[1]);
			if (pointStr.length > 1) {
//				int tempInt = Integer.parseInt(pointStr[1]);
				for (int i = 0; i < num - 1; i++) {
					tempStr.append("0");
				}
				tempStr.append(pointStr[0]);
				tempStr.append(pointStr[1]);
			} else {
				for (int i = 0; i < num; i++) {
					tempStr.append("0");
				}
			}
			return tempStr.toString();
		} else {
			return str;
		}

	}

	/**
	 * 格式化字符串
	 * 
	 * @param colums
	 * @return
	 */
	public static String formatString(String colums) {
		String str = "";
		str = colums.replaceAll("\\[", "");
		str = str.replaceAll("\\]", "");
//		str = str.replaceAll("\\)", "");
//		str = str.replaceAll("\\(", "");
		str = str.replaceAll("（", "");
		str = str.replaceAll("）", "");
		str = str.replaceAll("/", "");
		str = str.replaceAll("\\\\", "");
		str = str.replaceAll("\\}", "");
		str = str.replaceAll("\\{", "");
		str = str.replaceAll("\'", "");
		str = str.replaceAll("\"", "");
		str = str.replaceAll(",", "");
		str = str.replaceAll("\r", "");
		str = str.replaceAll("\n", "");
		str = str.replaceAll("\r\n", "");
		return str;
	}

	/**
	 * 对小数进入格式化
	 * 
	 * @param colums
	 *            数据
	 * @return
	 */
	public static String formatNumber(String colums) {
		String str = "";
		str = colums.replaceAll("\\[", "");
		str = str.replaceAll("\\]", "");
		str = str.replaceAll("\\)", "");
		str = str.replaceAll("\\(", "");
//		str = str.replaceAll("（", "");
//		str = str.replaceAll("）", "");
		str = str.replaceAll("/", "");
		str = str.replaceAll("\\\\", "");
		str = str.replaceAll("\\}", "");
		str = str.replaceAll("\\{", "");
		str = str.replaceAll("\'", "");
		str = str.replaceAll("\"", "");
		str = str.replaceAll(",", "");
		str = str.replaceAll("\r", "");
		str = str.replaceAll("\n", "");
		str = str.replaceAll("\r\n", "");
		str = StringUtil.getDoubleString(str);
		return str;
	}

	/**
	 * 判断一个字符串是否是数字
	 * @description isNum
	 * @param @param str
	 * @param @return
	 * @return boolean
	 * @author liliang
	 * @date 2014-5-6下午04:55:48
	 */
	public static boolean isNum(String str){
		if(StringUtil.isNull(str)) {
			return false;
		}
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	/**
	 * 判断整型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isIntger(String str) {
		return str.matches("[\\d]+");
	}

	/**
	 * 判断小数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		return str.matches("[\\d.]+");
	}

	public static String getColNames(String colums) {
		String str = "";
		str = colums.replaceAll("\\[", "");
		str = str.replaceAll("\\]", "");
		str = str.replaceAll("\\)", "");
		str = str.replaceAll("\\(", "");
//		str = str.replaceAll("（", "");
//		str = str.replaceAll("）", "");
		str = str.replaceAll("/", "");
		str = str.replaceAll("\\\\", "");
		str = str.replaceAll("\\}", "");
		str = str.replaceAll("\\{", "");
		str = str.replaceAll("\'", "");
		str = str.replaceAll("\"", "");
		str = str.replaceAll("\r", "");
		str = str.replaceAll("\n", "");
		str = str.replaceAll("\r\n", "");
		str = str.replaceAll(":", "");
		str = Pattern.compile("[\\s\\p{Zs}]").matcher(str).replaceAll("");
		return str;
	}
	
	/**
	 * 去掉\r , \n
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceString(String str) {
		str = str.replaceAll("\"", "");
		str = str.replaceAll("\r", "");
		str = str.replaceAll("\n", "");
		str = str.replaceAll("\r\n", "");
		//str = Pattern.compile("[\\s\\p{Zs}]").matcher(str).replaceAll("");
		return str;
	}

	public static List<String[]> getFiterDate(List<String[]> resultList) {
		List<String[]> resultList1 = new ArrayList<String[]>();
		for (String[] ss : resultList) {
			int i = 0;
			String f[] = new String[ss.length];
			for (String s : ss) {
				f[i++] = StringUtil.formatString(s);
			}
			resultList1.add(f);
		}
		return resultList1;
	}

	/**
	 * 过滤加工数据
	 * 
	 * @param resultList
	 *            数据
	 * @param fieldType
	 *            数据类型
	 * @return
	 */
	public static List<String[]> getFiterDate(List<String[]> resultList,
			List<String> fieldList) {
		String TypeString = "DECIMAL,DEC,NUMERIC,NUM,DOUBLE,DOUBLE PRECISION,FLOAT";

		List<String[]> resultList1 = new ArrayList<String[]>();
		for (String[] ss : resultList) {
			int i = 0;
			String f[] = new String[ss.length];
			for (String s : ss) {
				if (TypeString.indexOf(fieldList.get(i).toUpperCase()) != -1) {
					f[i++] = StringUtil.formatNumber(s);
				} else {
					f[i++] = StringUtil.formatString(s);
				}
			}
			resultList1.add(f);
		}
		return resultList1;
	}

	/**
	 * 过滤加工数据
	 * 
	 * @param String
	 *            数据
	 * @return
	 */
	public static boolean isNumberType(String type) {
		String TypeString = "DECIMAL,DEC,NUMERIC,NUM,DOUBLE,DOUBLE PRECISION,FLOAT";
		if (TypeString.indexOf(type.toUpperCase()) != -1) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 输出到页面
	 * 
	 * @param response
	 * @param str
	 * @return
	 */
	public static void println(HttpServletResponse response, String str) {
		response.setContentType("text/json; charset=utf-8");
		try {
			response.getWriter().print(str);
		} catch (Exception e) {
			log.error("输出到页面失败" + e);
			e.printStackTrace();
		}
	}

	/**
	 * 对文件特殊字符处理
	 * 
	 * @param str
	 * @return
	 */
	public static boolean fileSecialCharacter(String str) {
		String single[] = str.split("\'");
		String andMark[] = str.split("&");
		if (single.length < 2 && andMark.length < 2) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 得到传入String参数的字节长度(中文两个字节)
	 * 如果参数为空将抛出IllegalArgumentException异常
	 * 
	 * @param arg String字符串
	 * @return int
	 */
	public static int getBytesLength(String arg){
		if(arg == null) {
			throw new IllegalArgumentException("参数不能为空.");
		}
		return arg.getBytes().length;
	}
	
	
	/**
	 * 参数存放条件列表，返回查询条件
	 * 
	 * @param list 存放字符串条件 格式(如：字段名=值)
	 * @return String
	 */
	public static String getConditionFromList(List<String> list) {
		if(list == null) return "";
		StringBuffer conditionBuffer = new StringBuffer();
		for(String condition:list){
			if(conditionBuffer.length() == 0) {
				conditionBuffer.append(" ").append(condition).append(" ");
			} else {
				conditionBuffer.append(" and ").append(condition).append(" ");
			}
		}
		return conditionBuffer.toString();
	}

	/**
	 * 判断是否为null或者空字符串，是返回false 
	 * @param s 要判断的字符串
	 * @return 
	 */
	public static boolean isNotNull(String s){
		if(s==null || "".equals(s.trim()) || "null".equalsIgnoreCase(s) ||"undefined".equals(s))
			return false;
		else
			return true;
	}
	/**
	 * 
	 */
	public static String getNumberByString(String str){
		Object in = null;
		String ing = null;
		for (int i = 0; i < str.length(); i++) {
			in = str.charAt(i);
			if (in.toString().matches("[0-9]{1,}") == true) {
				if (ing != null) {
					ing = ing.concat(in.toString());
				} else {
					ing = in.toString();
				}
			}
		}
//		System.out.println(ing);
		return ing;
	}
	
	/**
	 * 把ascii码转换成对应字符串
	 * @param str 要转换的ascii码
	 * @param sp ascii码直接连接的字符
	 * @return
	 */
	public static String getStringByAscii(String str,String sp){
		String[] proNames=str.split(sp);
		StringBuffer sb=new StringBuffer();
		for (String pName : proNames) {
			char c = (char)Integer.parseInt(pName.trim());
			sb.append(c);
		}
		return sb.toString();
	}
	
	/**
	 * 把字符串转化成ascii码
	 * @param value 字符串
	 * @return
	 */
	public static String stringToAscii(String value){
	  StringBuffer sbu = new StringBuffer();
	  char[] chars = value.toCharArray();
	  for (int i=0; i < chars.length; i++) {
		  if(i!= chars.length - 1){
			  sbu.append((int)chars[i]).append(",");
		   }else {
			   sbu.append((int)chars[i]);
		   }
	  }
	  return sbu.toString();
	 }

	
	/**
	 * 去掉左边空格
	 * 
	 * @param arg
	 * @return
	 */
	public static String ltrim(String arg) {
		if(StringUtils.isEmpty(arg)) return "";
		while(arg.indexOf(" ") == 0 && arg.length() > 1) {
			arg = arg.substring(1, arg.length());
		}
		return arg;
	}
	
	/**
	 * 去掉右边空格
	 * 
	 * @param arg
	 * @return
	 */
	public static String rtrim(String arg) {
		if(StringUtils.isEmpty(arg)) return "";
		while(arg.lastIndexOf(" ") == arg.length()-1 && arg.length() > 1) {
			arg = arg.substring(0, arg.length()-1);
		}
		return arg;
	}
	
	/**
	 * 去掉左边和右边空格
	 * 
	 * @param arg
	 * @return
	 */
	public static String lrtrim(String arg) {
		return ltrim(rtrim(arg));
	}
	
	/**
	 * 数组转换为String，中间用,隔开
	 * @param array
	 * @return
	 */
	public static String arrayToString(String[] array) {
		String value = "";
		if(array == null) return "";
		for(int i = 0; i < array.length; i++) {
			value += value.length() == 0 ? array[i] : ","+array[i];
		}
		return value;
	}
	
	public static String replaceAllSpace(String arg) {
		if(StringUtils.isEmpty(arg)) return arg;
		return Pattern.compile("[\\s\\p{Zs}]").matcher(arg).replaceAll("");
	}
	
	/**
	 * 数字格式转化
	 * @param num 小数点位数
	 * @param data 数据
	 * @return
	 */
	public static String dataReverse(int num,String data){
		StringBuffer format=new StringBuffer("0.");
		for(int i=0;i<num;i++){
			format.append("0");
		}
		if(null==data||"".equals(data)||"null".equalsIgnoreCase(data)){
			data="0.00";
		}
	    DecimalFormat myFormatter = new DecimalFormat(format.toString()); 
	    double values = Double.parseDouble(data); 
	    return myFormatter.format(values);
	}
	
	/**
	 * 四舍五入求值
	 * 
	 * @param v     原始数值
	 * @param scale 小数点位数
	 * @return 如果scale参数小于0则返回v
	 */
	public static double round(double v,int scale) {
		if(scale<0){
			return v;
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal oneValue = new BigDecimal("1");
		return b.divide(oneValue,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static String formate(double v,String formater) {
		DecimalFormat d = new DecimalFormat(formater);
		return d.format(v);
	}
	
	/**
	 * Long型转换为Integer
	 * @param v
	 * @return
	 */
	public static Integer longToInt(Long v) {
		if(v == null) 
			return 0;
		return Integer.parseInt(v+"");	
	}
	
	/**
	 * 获取一个n位的随机数
	 * @return
	 */
	public static String getRandom(int num) {
		double v = Math.random();
		Long l = 1L;
		for(int i = 0; i < num; i++) {
			l = l*10;
		}
		StringBuffer f = new StringBuffer();
		while(f.length() < num) f.append("0");
		return formate(v*l,f.toString());
	}
	
	/**
	 * 
	 * @param columnIndex 列数
	 * @param name  名字
	 * @param cnName 中文名
	 * @return
	 */
	
	/**
	 * 分析字段信息，用于sql查询
	 * @param str 字段值
	 * @return
	 */
	public static String getInFindInfo(String str) {
		if(null== str || "".equals(str)) {
			return "";
		}
		String temp[] =str.split(",");
		StringBuffer buf = new StringBuffer();
	    for (int i = 0; i < temp.length; i++) {
	    	buf.append("'").append(temp[i]).append("',");
		}
		if(null !=buf && !"".equals(temp.toString()) ){
			buf.deleteCharAt(buf.length()-1);
		}
		
		return buf.toString();
	}
	
	/**
	 * 格式化 BigDecimal 数据
	 * @param dec 数字值
	 * @param  str 格式化
	 * @return
	 */
	public static String getDecimalFormat(BigDecimal dec, String str) {
		if(dec == null) {
			dec = new BigDecimal("0");
		}
		
		DecimalFormat format = new DecimalFormat(str);
		return format.format(dec);
	}
	
	/**
	 * 得到层次结构图
	 * @param floor 层次
	 * @param currentFloor 当前层
	 * @param symbol  符号 默认为"、"
	 * @return
	 */
	public static String getFloorFlag(int floor,int currentFloor,String symbol){
		
		if(null==symbol){
			symbol="、";
		}
		String temp ="";
		StringBuffer buf = new StringBuffer();
		for(int i=0;i<currentFloor;i++){
			temp=temp+"";
		}
		
		if(currentFloor==1){
			
			buf.append("(")
				.append(numberToLarge(floor))
				.append(")")
				.append(symbol);
			
		} else if(currentFloor==2){
			
			buf.append(temp).append(floor).append(symbol);
			
		} else if(currentFloor==3){
			
			buf.append(temp)
			   .append(floor).append(")")
			   .append(symbol);
			
		} else if(currentFloor==4){
			
			buf.append(temp).append("(")
			   .append(floor).append(")")
			   .append(symbol);
			
		} else if(currentFloor==5){
			
			buf.append(temp)
			   .append(getNumberFlag(floor))
			   .append(symbol);
			
		} else if(currentFloor==6){
			
			buf.append(temp)
			   .append(getNumberFlag(floor))
			   .append(symbol);
			
		}  else {
			
			buf.append(temp)
			   .append(floor);
			
		}  
		
		
		return buf.toString();
	}
	
	public static String getNumberFlag(int num){
		
		if(num==1){
			return "①";
		} else if(num==2){
			return "②";
		} else if(num==3){
			return "③";
		} else if(num==4){
			return "④";
		} else if(num==5){
			return "⑤";
		} else if(num==6){
			return "⑥";
		} else if(num==7){
			return "⑦";
		} else if(num==8){
			return "⑧";
		} else if(num==9){
			return "⑨";
		} else if(num==2){
			return "⑩";
		} else {
			return String.valueOf(num);
		}
	}
	
	/**
	 * 得到sql以#& &#的sql语句的字段信息
	 * @param sql sql语句
	 * @return
	 */
	public static List<String> getSqlField(String sql){
		
		List<String> list  = new ArrayList<String>();
		Pattern pattern = Pattern.compile("\\#(\\&.*?\\&\\#)");//正则表达式
		Matcher matcher = pattern.matcher(sql);
		
		while(matcher.find()){
			String temp=matcher.group(1).replaceAll("#", "");
			temp=temp.replaceAll("&", "");
			list.add(temp);
		}	
		
		return list;
	}
	
	/**
	 * 判断是否为null或者空字符串，是返回false 
	 * @param s 要判断的字符串
	 * @return 
	 */
	public static boolean isNull(String s){
		if(s==null || "".equals(s.trim()) || "null".equalsIgnoreCase(s) ||"undefined".equals(s))
			return true;
		else
			return false;
	}
	
	public static Integer changLong2Int(Long l) {
		if(l == null) 
			return null;
		else 
			return Integer.valueOf(String.valueOf(l));
	}
	
	public static Long changInt2Long(Integer i) {
		if(i == null) 
			return null;
		else 
			return Long.valueOf(String.valueOf(i));
	}
	
	
	public static String convertBlobToString(ByteArrayInputStream input){
		byte[] byte_data = new byte[input.available()];
		input.read(byte_data, 0, byte_data.length);
		String contentStr = new String(byte_data);
		return contentStr;
	}
	
	public static String convertBlobToStringOracle(InputStream input){
		String result = "";
		try {
//			System.out.println("@@@@@@@"+blob.read());
			input.read();
			byte[] byte_data = new byte[input.available()];
			input.read(byte_data, 0, byte_data.length);
		
			result = new String(byte_data);
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		return result;
	}
	
	/**
	 * 判断是否为null或者空字符串，是返回false 
	 * @param s 要判断的字符串
	 * @return 
	 */
	public static List<String> convertArrayToList(String[] arr){
		
		List<String> list = new ArrayList<String>();
		for (String a : arr) {
			list.add(a);
		}
		return list;
	}
	
	/**
	 * 解决EXT自带时间控件
	 * @param filter
	 * @return
	 */
	public static String getSql(String filter){
		filter = filter.replace("[", "").replace("{", "").replace("}", "").replace("]", "").replace("\"", "");
		String[] filters = filter.split(",");
		int floor = filters.length/4;
		StringBuffer sql = new StringBuffer();
		for (int i = 0; i < floor; i++) {
			String time = "";
			for (int j = 3; j >= 0; j--) {
				int k = 4 * i + j;
				if (j == 3) {
					sql.append(" and ").append("substr(" + filters[k].split(":")[1] + ",1,10) ");
				}
				if (j == 2) {
					time = filters[k].split(":")[1];
				}
				if (j == 1) {
					String type = filters[k].split(":")[1];
					if ("eq".equals(type)) {
						sql.append("=").append(" '").append(time).append("'");
					} else if ("lt".equals(type)) {
						sql.append("<=").append(" '").append(time).append("'");
					} else if ("gt".equals(type)) {
						sql.append(">=").append(" '").append(time).append("'");
					}
				}
			}
		}
		return sql.toString();
	}
	/**
	 * 把多个逗号分隔的String放入一个list
	 * @param str
	 * @return
	 */
	public static List<String> buildList(String...str){
		String[] temArray=null;
		List<String> list=new ArrayList<String>();
		for(String s:str){
			temArray=s.split(",");
			for(String temarray:temArray){
				list.add(temarray);
			}
		}
		return list;
	}
	
	/**
	 * 把多个逗号分隔的String构造成一个逗号分够的String
	 * @param str
	 * @return
	 */
	public static String buildString(boolean quotes  ,String...str){
		StringBuffer sb=new StringBuffer();
		String result="";
		for(String s:str){
			if(isNotNull(s)){
				sb.append(s).append(",");
			}
		}
		if(sb.length()>0){
			result=sb.substring(0,sb.length()-1);
			if(quotes){
				result=result.replaceAll(",", "','");
				result="'"+result+"'"; 
			}
			return result;
			
		}else{
			return "";
		}
		
	}
	
	/**
	 * 把多个逗号分隔的String构造成一个逗号分够的String
	 * @param str
	 * @return
	 */
	public static String buildStringWithCommaCN(boolean quotes  ,String...str){
		StringBuffer sb=new StringBuffer();
		String result="";
		for(String s:str){
			if(isNotNull(s)){
				sb.append(s).append("，");
			}
		}
		if(sb.length()>0){
			result=sb.substring(0,sb.length()-1);
			if(quotes){
				result=result.replaceAll("，", "'，'");
				result="'"+result+"'"; 
			}
			return result;
			
		}else{
			return "";
		}
		
	}

	/**
	 * 获得定长字符串 ，不足前面补0
	 * @param str
	 * @param length
	 * @return
	 * @throws CmsRunTimeException
	 * @descriptiongetFixedLenLeftStr
	 * @param @param str
	 * @param @param length
	 * @param @return
	 * @param @throws CmsRunTimeException
	 * @return String
	 * @author liliang
	 * @date 2014-4-16下午08:54:27
	 */
	public static String getFixedLenLeftStr(String str,int length){
		if(null == str || (length <=0 || str.length()>length)){
			throw new RuntimeException("输入的参数不合法："+str);
		}
		while(null==str || str.length()<length){
			str = "0"+(null==str?"1":str);
		}
		return str;
	}
	
	/**
	 * 获得定长字符串 ，不足前面补0
	 * @param str
	 * @param length
	 * @return
	 * @throws CmsRunTimeException
	 * @descriptiongetFixedLenLeftStr
	 * @param @param str
	 * @param @param length
	 * @param @return
	 * @param @throws CmsRunTimeException
	 * @return String
	 * @author liliang
	 * @date 2014-4-16下午08:54:27
	 */
	public static String getFixedLenLeftStr(int str, int length) {
		return getFixedLenLeftStr(String.valueOf(str), length);
	}
	
	/**
	 * Object转换为String  null值转换为空字符串
	 * @description objectToString
	 * @param @param o
	 * @param @return
	 * @return String
	 * @author liliang
	 * @date 2014-4-29下午05:26:25
	 */
	public static String objectToString(Object o) {
		if(o == null) return "";
		else return String.valueOf(o);
	}
	
	/**
	 * Object转换为Long  空值转换为0
	 * @description objectToLong 
	 * @param @param o
	 * @param @return
	 * @return Long
	 * @author liliang
	 * @date 2014-4-29下午05:27:41
	 */
	public static Long objectToLong(Object o) {
		if(StringUtils.isBlank(objectToString(o)))
			return 0L;
		else 
			return Long.valueOf(objectToString(o));
	}
	
	/**
	 * Object转换为Double  空值转换为0
	 * @description objectToLong 
	 * @param @param o
	 * @param @return
	 * @return Long
	 * @author liliang
	 * @date 2014-4-29下午05:27:41
	 */
	public static Double objectToDouble(Object o) {
		if(StringUtils.isBlank(objectToString(o))) 
			return 0D;
		else 
			return Double.valueOf(objectToString(o));
	}

	/**
	 * Object转换为Integer  空值转换为0
	 * @description objectToLong 
	 * @param @param o
	 * @param @return
	 * @return Long
	 * @author liliang
	 * @date 2014-4-29下午05:27:41
	 */
	public static Integer objectToInteger(Object o) {
		if(StringUtils.isBlank(objectToString(o))) 
			return 0;
		else 
			return Integer.valueOf(objectToString(o));
	}
	
	/**
	 * 
	 * @Description:  获取集合对象中的属性值
	 * @author: 孙振兴   
	 * @date:2014-8-21 下午09:40:34
	 * @param args 集合对象
	 * @param propName 要获取的实体类中属性的名称
	 * @param split 分隔符
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static String getObjectPropValues(List<?> args,String propName,String split){
		Assert.notNull(split);
		Assert.notNull(propName);
		Assert.notNull(args, "所需的集合不能为空,至少需要有一个元素");
		StringBuffer buf = new StringBuffer();
		Object value = null;
		for(Object obj : args){
			for(Field fd : obj.getClass().getDeclaredFields()){
				fd.setAccessible(true);
				if(fd.getName().equals(propName)){
					try {
						value = fd.get(obj);
						if(fd.getType().equals(String.class)){
							buf.append("'");
							buf.append(value == null ? "" :value.toString());
							buf.append("'");
						}else{
							buf.append(value);
						}
						buf.append(split);
					} catch (Exception e) {
						e.printStackTrace();
					}
					continue;
				}
			}
		}
		String ret = buf.toString();
		if(ret.endsWith(split)){
			ret = ret.substring(0,ret.length() - split.length());
		}
		return ret;
	}
	
	
	public static String nullTOString(Object s) {
		return nullToString(s, "");
//		return s == null ? "" : s.toString().trim();
	}
	public static Object Objecttrim(Object s) {
		return s==null||"".equals(s.toString()) ? s :String.valueOf(s).trim();
//		return s == null ? "" : s.toString().trim();
	}
	public static String nullToString(Object s , String defaults){
		return s==null||"".equals(s.toString()) ? defaults : s.toString().trim();
	}
	
	public static Integer nullToInteger(Object src,Integer def){
		String _tmp = StringUtil.nullTOString(src);
		return null==src||"".equals(_tmp) ? def : Integer.parseInt(_tmp);
	}
	
	//防止SQL注入攻击
	 public static String TransactSQLInjection(String str)
      {
            return str.replaceAll(".*([';]+|(--)+).*", " ");
      }
	
	
	//截取LIST   
	public static List subList(List list,Integer start,Integer end)throws RuntimeException{
		if(start == null && end == null) return list;           
		start = start==null?0:start<0?0:start;                                
		end  = end==null?list.size():end > list.size()?list.size():end;  
		if(end<=start) throw new RuntimeException("参数设置错误！");    
		return list.subList(start, end);         
	}
	
	/**
	 * 字符串除去前导0 如00001除去前导0后为1
	 * @param s 包含前导0的字符串
	 * @return
	 */
     public static  String trimZero(String str) {
		   if(strIsNull(str)) { //为空直接返回-1
			   return "" ;
		   }
		   str = str.trim();
		   int leng = str.length();
		   int i = 0 ;
		   for(; i < leng ; i ++) {
			   if(str.charAt(i) != '0') { //找到第一个不为0的索引
				   break ;
			   }
		   }
		   return str.substring(i,leng).trim();
	   }	
	public static int[] split(String value){
		int len = value.length();
		int[] result = new int[len];
		for(int i = 0 ; i < len ; i++){
			result[i] = value.charAt(i)-48;
		}
		return result;
	}
//--------------------------------------梅龙 add-----
	   
	   //金额格式串
		/**
		 * PATTERN_A 金额格式化模式 = " #,##0.00"
		 */
	   public final static String PATTERN_A =" #,##0.00";
	   /**
	    * PATTERN_B 金额格式化模式 = " #,##0"
	    */
	   public final static String PATTERN_B =" #,##0";
	   /**
	    *  PATTERN_C 金额格式化模式 = " #,##0.00%";
	    */
	   public final static String PATTERN_C =" #,##0.00%";
	   /**
	    * 1.匹配科学计数 e或者E必须出现有且只有一次 不含Dd
	    * 正则 ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))$
	    */
	   public final static String SCIENTIFIC_A ="^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))$";
	   /**
	    * 2.匹配科学计数 e或者E必须出现有且只有一次 结尾包含Dd
	    * 正则 ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))[dD]?$
	    */
	   public final static String SCIENTIFIC_B ="^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))[dD]?$";
	   /**
	 	 * 3.匹配科学计数 是否含有E或者e都通过 结尾含有Dd的也通过（针对Double类型）
	 	 * 正则 ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$
	     */
	   public final static String SCIENTIFIC_C ="^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$";
	   /**
	 	 * 4.匹配科学计数 是否含有E或者e都通过 结尾不含Dd
	 	 * 正则 ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?$
	    */
	   public final static String SCIENTIFIC_D ="^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?$";
	   
	  
	    private static final String UNIT = "万千佰拾万千佰拾亿千佰拾万千佰拾元角分厘";	
		private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";
		//金额大写最大支持数
		private static final BigDecimal MAX_VALUE = new BigDecimal("99999999999999999.99");
		private static final BigDecimal ZERO = new BigDecimal("0.00");
		private static final BigDecimal HUNDRED = new BigDecimal("100");
		private static final BigDecimal THOUSAND = new BigDecimal("1000");
	   //-------------------------字符串操作
	   /**
	    * 除去字符串空格
	    * @param str 字符串
	    */
	   public static  String trim(String str) {
		   if(strIsNull(str)) {
			   return "";
		   }
		   return str.trim();
	   }
	   /**
	    * 判断字符串为空
	    * @param str
	    * @return
	    */
	    public static  boolean strIsNull(String str) {
	    	return str==null || "".equals(str.trim())  ?  true : false ;
	    }
	    /**
	     * 判断字符串是否不为空
	     * @param str
	     * @return
	     */
	     public static  boolean strNotNull(String str) {
	     	return str==null || "".equals(str.trim()) ?  false : true ;
	     }
	     
	     public static boolean isNull(Object obj){
	    	 if(obj == null){
	    		 return true;
	    	 }
	    	 if(obj.toString() == null || "".equals(obj.toString().trim())){
	    		 return true;
	    	 }
	    	 return false;
	     }
	     
	     public static boolean isNoNull(Object obj){
	    	 return !isNull(obj);
	     }
	     //-------------------------------------------------金额格式化--------------------------------------------
	     /**
	      * 格式金额 不带币别
	      * 如将12345 格式为 12,456
	      * @param str  字符串金额数字 为空默认为"0.00"
	      * @return
	      */
	     public static  String formatSum(String str) {
	    	 if(strIsNull(str)) {
	    		 str="0.00";
	    	 }
	    	 DecimalFormat df = new DecimalFormat(PATTERN_A);
	    	 return df.format(new BigDecimal(str)).trim();
	     } 
	     /**
	      * 带币别格式金额
	      * 如 1235,787 格式化为 CNY 1,125,79
	      * @param str  字符串金额数字 为空默认为"0.00"
	      * @param ccy  币别代号 可以为空 则为不带币别格式化 
	      * @return
	      */
	     public static  String formatByCurreny(String str,String ccy) {
	    	 if(strIsNull(str)) {
	    		 str="0.00";
	    	 }
	    	 if(strIsNull(ccy)) {
	    		 ccy="";
	    	 }
	    	 String pattern = ccy.concat(PATTERN_A).trim();;
	    	 DecimalFormat df = new DecimalFormat(pattern);
	    	 return df.format(new BigDecimal(str));
	     }
	     /**
	      * 金额格式为带币别符号的形式
	      * 如124123 格式为 ￥124,123
	      * @param str    字符串金额数字 为空默认为"0.00"
	      * @param locale 国家区域  为空默认为Locale.CHINA,locale使用Local类中定义的常量
	      * @return
	      */
	     public static  String formatBySymbol(String str,Locale locale) {
	    	 if(strIsNull(str)) {
	    		 str="0.00";
	    	 }
	    	 if(locale == null ) {
	    		 locale = Locale.CHINA ;//默认人民币
	    	 }
	    	 Currency currency = Currency.getInstance(locale) ;
	    	 String pattern = currency.getSymbol().concat(PATTERN_A).trim();;
	         DecimalFormat df = new DecimalFormat(pattern);
	    	 return df.format(new BigDecimal(str));
	     }
	     /**
	      * 将格式化后的金额转换为数字
	      * 如 CNY 1,238.98 转为 1238.98
	      * @param str  格式化后的字符串金额数字 如：CNY 1,238.98
	      * @param ccy  币别代号.ccy必须和str中的币别一直 用于设置匹配模式
	      * @return
	      */
	     public static  String getformatNum(String str) {
	    	 String pattern = PATTERN_A.trim()  ;
	    	 String frmstar = str.trim(); ;
	    	 for(int i = 0 ; i < frmstar.length() ; i ++) {
	    		 if(frmstar.charAt(i)>='0' && frmstar.charAt(i) <= '9') {
	    			 //除去币别代号 找到格式金额的第一个数字索引即可
	    			 frmstar = frmstar.substring(i,frmstar.length());
	    			 break;
	    		 }
	    	 }
	    	 DecimalFormat df = new DecimalFormat(pattern);
	    	 df.setParseBigDecimal(true);
	    	 Number pase = null ;
			try {
				pase = df.parse(frmstar);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    	 return  pase.toString();
	     }
	     /**
	      * 将数字格式化为百分比
	      * @param str 字符串金额数字 为空则默认为"0.00"
	      * @return
	      */
	     public static  String formatPercent(String str) {
	    	 if(strIsNull(str)) {
	    		 str="0.00";
	    	 }
	    	 DecimalFormat df = new DecimalFormat(PATTERN_C);
	    	 df.setGroupingUsed(false);
	    	 return  df.format(Double.valueOf(str)).trim() ;
	     }
	     //-------------------------------------------------字符串--------------------------------------------     

		/**
		 * 对字符串补齐前导0 如123前导补3个0后=000123
		 * @param s 字符串
		 * @param leng 补0后的字符串总长度 
		 * 补0个数 = leng - s.length();
		 * @return 补0后的字符串
		 */
	     public static  String  addZero(String str,int maxleng) {
			   if(strIsNull(str)) { //为空直接返回-1
				   return "" ;
			   }
			   str = str.trim(); 
			   if(str.length() >= maxleng) return str;
			   int max = maxleng - str.length() ; //计算补0的个数
			   String zero="";
			   for(int i =0 ; i < max ; i ++ ) {
				   zero  = zero.concat("0");
			   }
			   return zero.concat(str).trim() ;
		   }
	    /**
	     * 将小写金额转换为大写金额，如将 12345.78 转换为“壹万贰千叁佰肆拾伍元柒角捌分”
	     * 支持：123.123----001.001--1,234,234.234--RMB 1,234.234 类似四种格式转大写
	     * 最大支持 到 万亿 如：肆万万零壹千亿元整
	     * @param mstr 金额数字 
	     * @return 大写金额
	     */
	 	public static  String RMBUpperCase(String mstr){
	 		if(StringUtil.strIsNull(mstr)) return "格式金额为空";
	 		//处理 RMB 12 或者RMB 123,233,.90 格式 除去币别代号
	   	  for(int i = 0 ; i < mstr.length() ; i ++) {
			 if(mstr.charAt(i)>='0' && mstr.charAt(i) <= '9') {
				 //除去币别代号 找到格式金额的第一个数字索引即可
				 mstr = mstr.substring(i,mstr.length());
				 break;
			 }
		  }
			//处理 格式化的金额 如 1,234,234.234 除去“,”
		  if(mstr.indexOf(",") >-1) { 
				mstr = mstr.replaceAll(",", "");
			}
		  BigDecimal big = null ;
	 		try {
	 			 big = new BigDecimal(mstr);
			}catch(Exception e) {
				e.printStackTrace();
				return "格式金额数字非法";
			}
			if(big.compareTo(ZERO) < 0 || big.compareTo(MAX_VALUE) > 0)
				 return "格式金额超长或为负数";
			BigDecimal lnum = big.multiply(THOUSAND).setScale(0, BigDecimal.ROUND_HALF_UP) ;
			if(lnum.compareTo(ZERO) == 0)
				return "零元整";
			String strValue = lnum + "";
			//i用来控制数
			int i = 0;
			//j用来控制单位
			int j = UNIT.length() - strValue.length();
			String rs = "";
			boolean isZero = false;
			for(;i < strValue.length(); i++,j++){
				char ch = strValue.charAt(i);
				System.out.println("ch="+ch);
				if(ch == '0'){
					isZero = true;
					if(UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万' || UNIT.charAt(j) == '元'){
						rs = rs + UNIT.charAt(j);
					}
				}else{
					if(isZero){
					  rs = rs + "零";
					  isZero = false;
					}
					rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);
				}	
			}
			
			if(!rs.endsWith("角")&& !rs.endsWith("分") && !rs.endsWith("厘")){		
				rs = rs + "整";			
			}	
			////100001000 转换后是：壹亿万壹千元整 需要替换
			rs = rs.replaceAll("亿万","亿");//
			return rs;
		} 
	 	/**
	 	 * 判断是不是科学计数法 如果是 返回true
	 	 * 匹配科学计数 e或者E必须出现有且只有一次 结尾不含D
	 	 * 匹配模式可参考本类定义的 SCIENTIFIC_A，SCIENTIFIC_B,SCIENTIFIC_C,SCIENTIFIC_D
	 	 * 若判断为其他模式可调用 StringUtil.Regular(String str,String pattern)方法
	 	 * @param str 科学计数字符串
	 	 * @return boolean
	 	 */
	 	public static  boolean isScientific(String str){
			if(StringUtil.strIsNull(str))
				return false; 
			Pattern p = Pattern.compile(StringUtil.SCIENTIFIC_A);
			Matcher m = p.matcher(str);
			return m.matches();
	 	}
	 	/**
	 	 * 匹配是否符合正则表达式pattern 匹配返回true
	 	 * @param str 匹配的字符串
	 	 * @param pattern 匹配模式
	 	 * @return boolean
	 	 */
	 	public static  boolean Regular(String str,String pattern){
			if(StringUtil.strIsNull(str))
				return false;         
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(str);
			return m.matches();
	 	}
	 	/**金额格式 保留两位小数以，分隔
	 	 * @param inputStr
	 	 * @return
	 	 */
	 	public static String moneyFormat(String inputStr){
			if("".equals(inputStr))
				return "";          
			try {
			    NumberFormat formater = NumberFormat.getInstance();
			    formater.setMinimumFractionDigits(2); //保留两位小数
			    return formater.format(Double.valueOf(inputStr));
			} catch (Exception e) {
				return inputStr;
			}
		}
	 	
	 	/**
	 	 * 金额格式保留两位小数
	 	 * @param d
	 	 * @return
	 	 * @throws Exception
	 	 */
	 	public static String moneyFormat(Object d) throws Exception{
			return moneyFormat(d,"");
	 	}
	 	
	 	/**
		 * 金额格式保留两位小数
		 * @param inputStr
		 * @return
	 	 * @throws Exception 
		 */
		public static String moneyFormat(Object d,String pattern) throws Exception{
			pattern=(pattern==null||pattern.equals(""))?"###0.00":pattern;
			if(d == null || "".equals(d)){
				d = "0";
			}
			try{
				Double.valueOf(d.toString());
			}catch(Exception e){
				throw new Exception("格式化金额异常"+e.getMessage());
			}
			DecimalFormat df = new DecimalFormat(pattern);
			try{
				return df.format(Double.valueOf(d.toString()));
			}catch(Exception e){
				return String.valueOf(d);
			}
		}
		/**
		 * 生成一个随机整数
		 * @return
		 */
		public static int getOneRandom(){
			Random random = new Random(System.currentTimeMillis());
			return random.nextInt(9999);
		}
		
		/**
	 	 * 把中文转成Unicode码
	 	 * @param str
	 	 * @return
	 	 */
	 	public static String String2Unicode(String str){
	 		String result="";
	 		for (int i = 0; i < str.length(); i++){
	             int chr1 = (char) str.charAt(i);
	             if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)
	                 result+="\\u" + Integer.toHexString(chr1);
	             }else{
	             	result+=str.charAt(i);
	             }
	         }
	 		return result;
	 	}
		
	 	/**
	 	 * @Description : 把带分隔符的string转化为list 
	 	 * @author : 戴泳材
	 	 * @date :   2014-9-19 上午09:33:46
	 	 * @param target 如"12,23,23"
	 	 * @param regex 分隔符 如带“,”分割的
	 	 * @return
	 	 */
	 	public static List<String> covertStringToList(String target, String regex){
	 		List<String> sList = new ArrayList<String>();
	 		if (StringUtil.isNotNull(target)) {
	 			String s[] = target.split(regex);
	 			for (String st: s) {
	 				sList.add(st);
	 			}
	 		}
			return sList;
		}
	 	
	 	/**
	 	 * 
	 	 * @Description: 获取加密的用户名字符串
	 	 * @author: 孙振兴   
	 	 * @date:2014-12-23 上午10:48:54
	 	 * @param userName 明文用户名
	 	 * @return 
	 	 */
	 	public static String getEcryptUserName(String userName){
	 		try {
				return ConfigTools.encrypt(userName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
	 	}
	 	
	 	/**
	 	 * 
	 	 * @Description: 获取加密的密码字符串
	 	 * @author: 孙振兴   
	 	 * @date:2014-12-23 上午10:48:54
	 	 * @param password 明文密码
	 	 * @return 
	 	 */
	 	public static String getEcryptPass(String password){
	 		try {
				return ConfigTools.encrypt(password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
	 	}
	 	
	 	
	 	/**
	 	 * 
	 	 * @Description: 解密加密的用户名字符串
	 	 * @author: 孙振兴   
	 	 * @date:2014-12-23 上午10:48:54
	 	 * @param userName 密文用户名
	 	 * @return 
	 	 */
	 	public static String getDecryptUserName(String userName){
	 		try {
				return ConfigTools.decrypt(userName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
	 	}
	 	
	 	/**
	 	 * 
	 	 * @Description: 解密加密的密码字符串
	 	 * @author: 孙振兴   
	 	 * @date:2014-12-23 上午10:48:54
	 	 * @param password 密文密码
	 	 * @return 
	 	 */
	 	public static String getDecryptPass(String password){
	 		try {
				return ConfigTools.decrypt(password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
	 	}
	 	
	 	/**
	 	 * 传统方式
	 	 * @Description: 获取加密的密码字符串
	 	 * @author: 孙振兴   
	 	 * @date:2014-12-23 上午10:48:54
	 	 * @param userName 明文用户名
	 	 * @param jarPath 要加密使用的druid包的路径,druid为1.0以上
	 	 * @return 
	 	 */
	 	public static String getEcryptPass(String password,String jarPath){
	 		Runtime rt = Runtime.getRuntime();
	        Process pro = null;
	        StringBuffer sb=new StringBuffer();
	        try {
	        	pro = rt.exec("java -cp "+jarPath+" com.alibaba.druid.filter.config.ConfigTools "+password);
	        	BufferedReader br=new BufferedReader(new InputStreamReader(pro.getInputStream())); 
				String inline = br.readLine();
				while(!"".equals(inline) && inline != null){   
					sb.append(inline);
					inline = br.readLine();
				}
				br.close();
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	 		return sb.toString();
	 	}
	 	
	 	/**
	 	 * 
	 	 * @desc 数字转换为中文格式
	 	 * @author 王运海
	 	 * @date Dec 31, 2014 4:43:51 PM
	 	 * @param num
	 	 * @return
	 	 */
	 	public static String numtoCN(String numStr){
	 		Integer num = Integer.valueOf(numStr);
			String temp = String.valueOf(num.intValue());
			if(num == 0){
				temp = "0";
			}else if(num == 1){
				temp = "一";
			}else if(num == 2){
				temp = "二";
			}else if(num == 3){
				temp = "三";
			}else if(num == 4){
				temp = "四";
			}else if(num == 5){
				temp = "五";
			}else if(num == 6){
				temp= "六";
			}else if(num == 7){
				temp = "七";
			}else if(num == 8){
				temp = "八";
			}else if(num == 9){
				temp = "九";
			}else if(num == 10){
				temp = "十";
			}else if(num == 11){
				temp = "十一";
			}else if(num == 12){
				temp = "十二";
			}else if(num == 13){
				temp = "十三";
			}else if(num == 14){
				temp = "十四";
			}else if(num == 15){
				temp = "十五";
			}else if(num == 16){
				temp = "十六";
			}else if(num == 17){
				temp = "十七";
			}else if(num == 18){
				temp = "十八";
			}else if(num == 19){
				temp = "十九";
			}else if(num == 20){
				temp = "二十";
			}else if(num == 21){
				temp = "二十一";
			}else if(num == 22){
				temp = "二十二";
			}else if(num == 23){
				temp = "二十三";
			}else if(num == 24){
				temp = "二十四";
			}else if(num == 25){
				temp = "二十五";
			}else if(num == 26){
				temp = "二十六";
			}else if(num == 27){
				temp = "二十七";
			}else if(num == 28){
				temp = "二十八";
			}else if(num == 29){
				temp = "二十九";
			}else if(num == 30){
				temp = "三十";
			}else if(num == 31){
				temp = "三十一";
			}
			return  temp;
		}
	 	
	 	/**
	 	 * 
	 	 * @desc 日期转换为中文格式
	 	 * @author 王运海
	 	 * @date Dec 31, 2014 4:59:57 PM
	 	 * @param date
	 	 * @return
	 	 */
	 	public static String DateToCN(String date){
	 		String[] strs = date.split("-");
	 		String year = "";
	 		for(int i = 0; i< strs[0].length(); i++){
	 			year += numtoCN(String.valueOf(strs[0].charAt(i)));
	 		}
	 		String month = "";
	 		month = numtoCN(strs[1]);
	 		String day = "";
	 		day = numtoCN(strs[2]);
	 		String returnval = year + "年" + month + "月" + day + "日";
	 		return returnval;
	 	}
}








