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

public class Requirement04 {
	DocumentBuilderFactory factory = null;
	DocumentBuilder builder = null;
	Document doc = null;
	XPathFactory xPathFactory = null;
	XPath xPath = null;
	XPathExpression expr = null;
	NodeList nList = null;
	
	public static void main(String[] args) throws Exception {
		System.out.println("********************************Prints information of people match a specific weight condition******************************");
		Requirement04 app = new Requirement04();
		app.printPeopleDetailInfos(args[0], args[1]);
	}
	
	public Requirement04() throws Exception {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		doc = builder.parse("people.xml");
		xPathFactory = XPathFactory.newInstance();
		xPath = xPathFactory.newXPath();
	}
	
	/*
	 * Based on Lab03
	 * 4.A function which accepts a weight and an operator (=, > , <) as parameters and prints people that fulfill that condition (i.e., >80Kg, =75Kg, etc.).
	 */
	public void printPeopleDetailInfos(String weight, String operator) throws Exception {
		System.out.println("Printing people have weight " + operator + " " + weight);
		String exprPeople = "/people/person[healthprofile/weight" + operator + weight + "]";
		
		nList = (NodeList) xPath.compile(exprPeople).evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; null != nList && i < nList.getLength(); i++) {
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
