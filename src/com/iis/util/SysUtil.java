package com.iis.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iis.sys.LoadMemoryData;

public class SysUtil{
	
	private static Logger logger = LoggerFactory.getLogger(SysUtil.class);
	private static final String APP="app";//应用程序资源路径
	private static ResourceBundle appconfig=null;
	private static URL url = null;
	private static String retValue = "";
	private static Map<String, String> stringMap = new HashMap<String, String>();
	private static Map<String, List<Element>> listMap = new HashMap<String,  List<Element>>();
	
	public SysUtil(){
	}
	/**
	 * 读取class/app下properties资源
	 * @param fileName
	 * @param keyCode
	 * @return /WEB-INF/conf/cms_column.xml
	 */
	public static String getPptValue(String fileName,String keyCode){
		 appconfig = ResourceBundle.getBundle(APP+File.separator+fileName, Locale.CHINA); 
		 retValue   = appconfig.getString(keyCode);
		 logger.info("keyValue is {}",retValue);
		 return retValue;
	}
	/**
	 * 得到应用更目录
	 * @return E:/tomcat7/webapps/应用名称
	 */
	public static String getRealPath(){
		url =	Thread.currentThread().getContextClassLoader().getResource(""); 
		retValue = url.getPath(); 
		retValue = retValue.substring(1,retValue.length()-17);
		return retValue;
	}
	/**
	 * 读取xml ，读取栏目
	 * @param path 文件路径+文件名
	 * @param childs["id","name"]
	 * @return 栏目的id-name的键值对map
	 */
	public static HashMap<String, String> getFromXml(String path,String[] childs){
		
		try { 
			   SAXBuilder builder = new SAXBuilder(); 
			   Document doc = builder.build(new File(path));
			
			   Element rootElement = doc.getRootElement(); 
			   List<Element> allChildren = rootElement.getChildren(); 
			   logger.info("加载内存数据---{}",rootElement.getName());	   
			   for (int i = 0; i < allChildren.size(); i++) { 
				   
				   
				   
				stringMap.put(((Element) allChildren.get(i)).getChild(childs[0]).getText(),
						   ((Element) allChildren.get(i)).getChild(childs[1]).getText());
				   
			   } 
			}catch (JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		return (HashMap<String, String>) stringMap;
	}
	/**
	 * 读取xml
	 * @param fileName
	 * @return xml的根节点和子节点的list<Element>集合
	 */
	public static HashMap<String, List<Element>> getFromXml(String fileName){
		try { 
			   SAXBuilder builder = new SAXBuilder(); 
			   Document doc = builder.build(new File(fileName));
			//leftMenus
			   Element rootElement = doc.getRootElement(); 
			   //leftMenu集合
			   List<Element> allChildren = rootElement.getChildren(); 
			    
			   listMap.put(rootElement.getName(),allChildren);
			   logger.info("加载内存数据---{}",rootElement.getName());			    
			}catch (JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		return  (HashMap<String, List<Element>>) listMap;
		
	}
	/**
	 * 重新加载内存
	 */
	public static void reloadMemory(){
		LoadMemoryData.setDataMap(null);
		try {
			LoadMemoryData.getInstance().afterPropertiesSet();
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	/**
	 * 取得内存数据
	 * @return map对象
	 */
	public static Map<String, Object> getMemoryData(){
		return LoadMemoryData.getDataMap();
	} 
}
