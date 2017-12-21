package basic;

import java.io.FileInputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 使用XPath检索XML文档信息
 * dom4j外，还需要导入jaxen这个包，否则会报错
 * @author liurenyou
 *
 */
public class XPathDemo {
	public static void main(String[] args) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new FileInputStream("myemp.xml"));
			String xPath = "/list/emp[age>23 and gender='男']/name";
			@SuppressWarnings("unchecked")
			List<Element> list = doc.selectNodes(xPath);
			for(Element e : list) {
				System.out.println(e.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
