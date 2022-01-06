package test;

import org.junit.Test;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class PersonTest {
	static final File FILE = new File("C:\\Users\\kungreat\\IdeaProjects\\baseTeachJava\\src\\test\\user.xml");
	@Test
	public void ts() throws Exception {
		DocumentBuilderFactory defaultInstance = DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder documentBuilder = defaultInstance.newDocumentBuilder();
		Document parse = documentBuilder.parse(FILE);
		NodeList useraAccount = parse.getElementsByTagName("useraAccount");
		NodeList childNodes = useraAccount.item(0).getChildNodes();
		for(int x=0;x<childNodes.getLength();x++){
			System.out.println(childNodes.item(x).getNodeName());
			System.out.println(childNodes.item(x).getNodeType());
			if(childNodes.item(x).getNodeName().equals("address")){
				Element item =(Element) childNodes.item(x);
				item.setAttribute("name","hello");
			}
		}
		//官方提供的转换工具类
		TransformerFactory transformerFactory = TransformerFactory.newDefaultInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source source = new DOMSource(parse);
		Result result = new StreamResult(FILE);
		transformer.transform(source,result);
	}

}
