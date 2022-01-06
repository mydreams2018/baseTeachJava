package test;

import org.junit.Test;
import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

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
			if(childNodes.item(x).getNodeType() == Node.ELEMENT_NODE &&
					childNodes.item(x).getNodeName().equals("address")){
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

	@Test
	public void saxts() throws Exception{
		// SAX解析
		// 1.获取解析工厂
		SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
		// 2.从解析工厂获取解析器
		SAXParser parse = factory.newSAXParser();
		// 3.得到解读器
		XMLReader reader = parse.getXMLReader();
		// 4.设置内容处理器
		MyHandler myHandler = new MyHandler();
		reader.setContentHandler(myHandler);
		// 5.读取xml的文档内容
		reader.parse(new InputSource(new FileInputStream(FILE)));
		System.out.println(myHandler.MAP);
		System.out.println(myHandler.MAPATTR);
	}

	static class MyHandler extends DefaultHandler {
		public Map<String,Object> MAPATTR = new HashMap<>();
		public Map<String,Object> MAP = new HashMap<>();
		public String qName;
		/*
		 * @desc 文档解析开始时调用，该方法只会调用一次
		 * @return void
		 */
		@Override
		public void startDocument() throws SAXException{
			System.out.println("----解析文档开始----");
		}

		/*
		 * 每当遇到起始标签时调用
		 * @param uri xml文档的命名空间
		 * @param localName 标签的名字
		 * @param qName 带命名空间的标签的名字
		 * @param attributes 标签的属性集
		 * @return void
		 */
		@Override
		public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
			this.qName=qName;
			MAPATTR.put(qName,attributes);
		}

		/**
		 *  解析标签内的内容的时候调用
		 * @param ch 当前读取到的TextNode(文本节点)的字节数组
		 * @param start 字节开始的位置，为0则读取全部
		 * @param length 当前TextNode的长度
		 * @return void
		 */
		@Override
		public void characters(char[] ch, int start, int length)throws SAXException {
			String contents = new String(ch,start,length).trim();
			if (contents.length() > 0) {
				MAP.put(this.qName,contents);
			}else{
				System.out.println("内容为-->空");
			}
		}
		/**
		 *  每当遇到结束标签时调用
		 * @param uri xml文档的命名空间
		 * @param localName 标签的名字
		 * @param qName 带命名空间的标签的名字
		 * @return void
		 */
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			System.out.println("标签</"+qName+">解析结束");
		}
		/**
		 * @desc 文档解析结束后调用,该方法只会调用一次
		 * @return void
		 */
		@Override
		public void endDocument() throws SAXException {
			System.out.println("----解析文档结束----");
		}

	}

}
