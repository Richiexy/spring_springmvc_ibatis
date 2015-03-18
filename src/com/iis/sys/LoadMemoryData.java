package com.iis.sys;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iis.util.SysUtil;

public class LoadMemoryData {
	
	private static Logger logger = LoggerFactory.getLogger(LoadMemoryData.class);
	
	private static Map<String, Object> dataMap = new HashMap<String, Object>();
	
   //私有的默认构造子                                           
   private LoadMemoryData() {
	   logger.info("System.getProperty(user.dir)= {}",System.getProperty("user.dir"));
   }                               
   //注意，这里没有final                                 
   private static LoadMemoryData commonLoadToMemory=null;                
   //静态工厂方法                                        
   public synchronized  static LoadMemoryData getInstance() {
        if (commonLoadToMemory == null) {                            
        	commonLoadToMemory = new LoadMemoryData();                   
        }                                                
       return commonLoadToMemory;                                    
   }                                                     

	public static Map<String, Object> getDataMap() {
		return dataMap;
	}

	public static void setDataMap(Map<String, Object> dataMap) {
		LoadMemoryData.dataMap = dataMap;
	}
	/**
	 * 加载xml数据到内存
	 */
	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		logger.info("-------------------加载数据进内存-------------------");
//		loadColumnData();
//		loadLeftMenuData();
	}
	/**
	 * 销毁
	 */
	@PreDestroy
	public void destroy() throws Exception { 
		logger.info("Spring Container is destroy! Customer clean up");
		dataMap.clear();
	}
	/**
	 * 加载左侧菜单数据
	 */
	public void loadLeftMenuData(){
		String leftmenu = SysUtil.getPptValue("appconfig", "leftmenu");
		 logger.info("leftmenu is {}",leftmenu);
		 String path = SysUtil.getRealPath()+leftmenu;
		   
		 Map<String, List<Element>> leftMenuMap = SysUtil.getFromXml(path);
		dataMap.put("leftMenuMap",leftMenuMap); 
		
		
		//测试用 打印在日志
		dataMap =SysUtil.getMemoryData();

		leftMenuMap = (Map<String, List<Element>>)dataMap.get("leftMenuMap");
		
		Iterator<Entry<String, List<Element>>> iter = leftMenuMap.entrySet().iterator();   
		while(iter.hasNext()) {   
		    Entry<String, List<Element>> entry = iter.next();  
		    String keyCode = entry.getKey();
		    List<Element> leftMenuSet = entry.getValue();
		    
		    logger.info("keyCode is "+keyCode);
		    for (int i = 0; i < leftMenuSet.size(); i++) { 
				   //取一个leftMenu
				  Element childElementSet =  (Element) leftMenuSet.get(i);
				  //取其中h1a
				  Element childElement = childElementSet.getChild("h1a");
				  //取h1a对应的值
				  String h1aValue = childElement.getText();
				  
				  logger.info("---h1aValue is {}",h1aValue);
				  
				  //取其中lia集合
				  List liaElementSet = childElementSet.getChildren("lia");
				 
				  for (int j = 0; j < liaElementSet.size(); j++) { 
					  //取一个lia
					  Element liaElement = (Element) liaElementSet.get(j);
					  //取lia对应的值
					  String hrefValue = liaElement.getAttributeValue("href");
					  String liaElementValue = liaElement.getText();
					  logger.info("-------hrefValue is {} and liaElementValue is {}",hrefValue,liaElementValue);
				  } 
			}
		}
		//
		
		
	}
	/**
	 * 加载栏目数据
	 */
	public void loadColumnData(){
		String cmsColumn = SysUtil.getPptValue("appconfig", "cms_column");
		 logger.info("cmsColumn is {}",cmsColumn);
		 String path = SysUtil.getRealPath()+cmsColumn;
		 
		logger.info("path is {}",path); //形如E:/tomcat7/webapps/m2e_springMvc_01/WEB-INF/conf/cms_column.xml
		 
		String[] childs = new String[2];
				 childs[0] = "id";
				 childs[1] = "name";
		Map<String, String> columnMap = SysUtil.getFromXml(path,childs);
		dataMap.put("columnMap",columnMap); 
		
		//测试用 打印在日志
		dataMap =SysUtil.getMemoryData();

		columnMap = (Map<String, String>)dataMap.get("columnMap");
		String s1 = columnMap.get("c1");
		
		Iterator<Entry<String,String>> iter = columnMap.entrySet().iterator();   
		while(iter.hasNext()) {   
		    Entry<String, String> entry = iter.next();  
		    logger.info("entry.getKey() is {} and entry.getValue() is {}",entry.getKey(),entry.getValue());
		}
		//
	}
	
}
