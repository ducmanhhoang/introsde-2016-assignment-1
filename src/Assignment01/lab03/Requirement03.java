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

public class Requirement03 {

	DocumentBuilderFactory factory = null;
	DocumentBuilder builder = null;
	Document doc = null;
	XPathFactory xPathFactory = null;
	XPath xPath = null;
	XPathExpression expr = null;
	NodeList nList = null;

	public static void main(String[] args) throws Exception {
		System.out.println("********************************Prints the Health Profile of the person that is given the id******************************");
		Requirement03 app = new Requirement03();
		app.printPersonHealthProfile(Integer.parseInt(args[0]));
	}

	public Requirement03() throws Exception {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		doc = builder.parse("people.xml");
		xPathFactory = XPathFactory.newInstance();
		xPath = xPathFactory.newXPath();
	}

	/*
	 * Based on Lab03
	 * 3.A function that accepts id as parameter and prints the HealthProfile of the person with that id
	 */

	public void printPersonHealthProfile(long id) throws Exception {
		System.out.println("Getting Health Profile of person having id number = " + id);
		String exprPerson = "/people/person[@id='" + id + "']";

		nList = (NodeList) xPath.compile(exprPerson).evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; null != nList && i < nList.getLength(); i++) {
			Element ePerson = (Element) nList.item(i);

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
