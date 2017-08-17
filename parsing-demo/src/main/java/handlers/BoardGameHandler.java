package handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BoardGameHandler extends DefaultHandler {

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		str = str.trim();

		System.out.println(str);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("/" + qName);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.print(qName + ": ");
		for (int i = 0; i < attributes.getLength(); i++) {
			System.out.print(attributes.getValue(i) + " ");
		}
		System.out.print("\n");
	}

}
