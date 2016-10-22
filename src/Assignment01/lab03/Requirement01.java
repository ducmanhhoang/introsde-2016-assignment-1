package Assignment01.lab03;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Requirement01 {
	
	DocumentBuilderFactory factory = null;
	DocumentBuilder builder = null;
	Document doc = null;
	XPathFactory xPathFactory = null;
	XPath xPath = null;
	XPathExpression expr = null;
	NodeList nList = null;
	
	public static void main(String[] args) throws Exception {
		System.out.println("********************************Gets weight and height of a person given id number******************************");
		Requirement01 app = new Requirement01();
		app.getPersonHeight(15);
		app.getPersonWeight(15);
	}
	
	public Requirement01() {
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			doc = builder.parse("people.xml");
			xPathFactory = XPathFactory.newInstance();
			xPath = xPathFactory.newXPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Based on Lab03
	 * Use xpath to implement methods like getWeight and getHeight (getWeight(personID) returns weight of a given person, the same for getHeight)
	 */

	public void getPersonWeight(long id) throws Exception {
		String exprWeights = "/people/person[@id='" + id + "']/healthprofile/weight/text()";
		
		expr = xPath.compile(exprWeights);
		nList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		System.out.println("Gets weight of person with id number = " + id);
		for (int i = 0; i < nList.getLength(); i++) {
			System.out.println(nList.item(i).getNodeValue());
		}
	}

	public void getPersonHeight(long id) throws Exception {
		String exprHights = "/people/person[@id='" + id + "']/healthprofile/height/text()";
		
		expr = xPath.compile(exprHights);
		nList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		System.out.println("Gets height of person with id number = " + id);
		for (int i = 0; i < nList.getLength(); i++) {
			System.out.println(nList.item(i).getNodeValue());

		}
	}

}
