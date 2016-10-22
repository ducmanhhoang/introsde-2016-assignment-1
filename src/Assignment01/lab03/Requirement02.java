package Assignment01.lab03;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Requirement02 {
	
	DocumentBuilderFactory factory = null;
	DocumentBuilder builder = null;
	Document doc = null;
	XPathFactory xPathFactory = null;
	XPath xPath = null;
	XPathExpression expr = null;
	NodeList nList = null;
	
	public static void main(String[] args) throws Exception {
		System.out.println("********************************Prints all detail information of all people******************************");
		Requirement02 app = new Requirement02();
		app.printPeopleDetailInfos();
	}
	
	public Requirement02() throws Exception {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		doc = builder.parse("people.xml");
		xPathFactory = XPathFactory.newInstance();
		xPath = xPathFactory.newXPath();
	}
	
	/*
	 * Based on Lab03
	 * 2.Make a function that prints all people in the list with detail
	 */
	
	public void printPeopleDetailInfos() throws Exception {
		String exprPeople = "/people/person";
		
		expr = xPath.compile(exprPeople);
		nList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nList.getLength(); i++) {
			Element ePerson = (Element) nList.item(i);
			System.out.println("Person ID: " + ePerson.getAttribute("id"));
			System.out.println("Full name: " + ePerson.getElementsByTagName("firstname").item(0).getTextContent() + " "
					+ ePerson.getElementsByTagName("lastname").item(0).getTextContent());
			System.out.println("Birthdate: " + ePerson.getElementsByTagName("birthdate").item(0).getTextContent());

			NodeList hpList = ePerson.getElementsByTagName("healthprofile");
			for (int j = 0; j < hpList.getLength(); j++) {
				Element eHP = (Element) hpList.item(j);
				System.out.println("Last update: " + eHP.getElementsByTagName("lastupdate").item(0).getTextContent());
				System.out.println("Weight: " + eHP.getElementsByTagName("weight").item(0).getTextContent());
				System.out.println("Height: " + eHP.getElementsByTagName("height").item(0).getTextContent());
				System.out.println("BMI: " + eHP.getElementsByTagName("bmi").item(0).getTextContent());
				System.out.println("--------------------------------------");
				System.out.println();
			}
		}
	}
}
