package basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * XML(可扩展标记语言)
 * 标准通用标记语言的子集，是一种用于标记电子文件使其具有结构性的标记语言
 * 
 * 使用DOM生成XML文件
 * @author liurenyou
 *
 */
public class WriterXMLDemo {
	public static void main(String[] args) {
		List<Emp> empList = new ArrayList<Emp>();
		empList.add(new Emp(1,"盖伦",24,"男",3150));
		empList.add(new Emp(2,"盖伦",24,"男",3150));
		empList.add(new Emp(3,"盖伦",24,"男",3150));
		empList.add(new Emp(4,"盖伦",24,"男",3150));
		empList.add(new Emp(5,"盖伦",24,"男",3150));
		
		XMLWriter writer = null;
		/*
		 * 使用DOM生成XML文档步骤:
		 * 1.创建一个Document表示一个XML文档
		 * 2.向文件中添加根元素
		 * 3.按照预定的格式从根元素开始逐级添加子元素
		 * 4.创建XMLWriter
		 * 5.将Document对象通过XMLWriter写出XML文档
		 */
		Document doc = DocumentHelper.createDocument();	
		/*
		 * Document提供了添加根元素的方法
		 * Element addElement(String name)
		 * 添加给定名字的元素，并将该元素返回，以便于追加操作
		 * 但Document的添加根元素的方法只能调用一次，因为一个
		 * 文档只能有一个根元素
		 */
		Element root = doc.addElement("list");
		/*
		 * 将集合中的每一个Emp对象转换成一个Emp元素添加到根元素中
		 */
		for(Emp emp : empList) {
			/*
			 * Element提供了添加子标签方法，方法定义与Document的一致
			 */
			Element empEle = root.addElement("emp");
			Element nameEle = empEle.addElement("name");
			nameEle.addText(emp.getName());
			empEle.addElement("age").addText(emp.getAge()+"");
			empEle.addElement("gender").addText(emp.getGender());
			empEle.addElement("salary").addText(emp.getSalary()+"");
			empEle.addAttribute("ID", emp.getId()+"");	//ID的添加需要用到addAttribute(String name,Emp emp)
			
			try {
				writer = new XMLWriter(new FileOutputStream("myemp.xml"),OutputFormat.createPrettyPrint());
				writer.write(doc);
				System.out.println("写出完毕");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(writer!=null) {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
