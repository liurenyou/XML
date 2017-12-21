package basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 使用DOM解析XML文档
 * @author liurenyou
 *
 */
public class ParseXMLDemo {
	public static void main(String[] args) {
		/*
		 * 使用DOM文档解析XML文档过程:
		 * 1.创建SAXReader
		 * 2.使用SAXReader读取XML文档并生成Document对象，这一步由于
		 * 需要将XML文档内容加载到内存中，所以比较费时、消耗系统资源
		 * 3.通过Document获取根元素
		 * 4.通过根元素根据XML文档结构逐级获取子元素，获取XML文档信息
		 */
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new FileInputStream("myemp.xml"));
			/*
			 * Element getRootElement()
			 * Document提供的获取根元素的方法，每一个实例表示XML文档中的
			 * 一个元素(一对标签)
			 */
			Element root = doc.getRootElement();
			/*
			 * 将XML文档中的每一个信息解析出来并以一个Emp实例保存，最后
			 * 将Emp实例存入一个集合
			 */
			List<Emp> empList = new ArrayList<Emp>();
			/*
			 * Element所提供获取子元素的相关方法:
			 * List elements()
			 * 获取当前元素的所有子元素
			 * 
			 * List elements(String name)
			 * 获取当前元素下指定名字的所有同名子元素
			 * 
			 * Element elements(String name)
			 * 获取当前元素下指定名字的子元素
			 */
			@SuppressWarnings("unchecked")
			List<Element> eles = root.elements();
			for(Element empEle : eles) {
				Element nameEle = empEle.element("name");
				String name = nameEle.getText();//解析名字
				int age = Integer.parseInt(empEle.elementText("age"));//解析年龄
				String gender = empEle.elementText("gender");//获取性别
				int salary = Integer.parseInt(empEle.elementText("salary"));//获取工资
				Attribute attr = empEle.attribute("ID");
				int id = Integer.parseInt(attr.getValue());
				
				Emp emp = new Emp(id,name,age,gender,salary);
				empList.add(emp);
			}
			for(Emp emp : empList) {
				System.out.println(emp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
