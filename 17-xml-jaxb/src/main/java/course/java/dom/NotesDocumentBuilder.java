package course.java.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class NotesDocumentBuilder {
	public static final String XS_FILE_NAME = "note.xsd";
	public static final String XML_FILE_NAME = "notes.xml";
	public static final String XML_OUTPUT_FILE_NAME = "notes.xml";

	public static void main(String[] args) {
		// Create validation schema
		SchemaFactory sf = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema schema = sf.newSchema(new File(XS_FILE_NAME));
			// Create document builder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(true);
			dbf.setNamespaceAware(true);
			dbf.setSchema(schema);

			DocumentBuilder db = dbf.newDocumentBuilder();
			db.setErrorHandler(new ErrorHandler() {
				@Override
				public void warning(SAXParseException exception)
						throws SAXException {
					System.err.println("PARSING WARNING: "
							+ exception.getMessage());
				}

				@Override
				public void error(SAXParseException exception)
						throws SAXException {
					System.err.println("PARSING ERROR: "
							+ exception.getMessage());
				}

				@Override
				public void fatalError(SAXParseException exception)
						throws SAXException {
					System.err.println("PARSING FATAL ERROR: "
							+ exception.getMessage());
					throw exception;
				}
			});
			Document doc = db.parse(new File(XML_FILE_NAME));

			addNote(doc, "Trayan", "Office", "New Course",
					"New JavaEE course started");
			// Manipulate document
			// Element rootElement = doc.getDocumentElement();
			NodeList notes = doc.getElementsByTagName("note");
			for (int i = 0; i < notes.getLength(); i++) {
				printNote((Element) notes.item(i));
			}

			// Save document to file
			TransformerFactory tf = TransformerFactory.newInstance();
			try {
//				Transformer tr = tf.newTransformer(new StreamSource(new File("")));
				Transformer tr = tf.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(
						XML_OUTPUT_FILE_NAME));
				tr.transform(source, result);
				;
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		} catch (SAXException e) {
			System.err.println("Xml Schema file not found: " + XS_FILE_NAME);
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void printNote(Element note) {
		System.out.println("TO: "
				+ note.getElementsByTagName("to").item(0).getFirstChild()
						.getNodeValue());
		System.out.println("FROM: "
				+ note.getElementsByTagName("from").item(0).getFirstChild()
						.getNodeValue());
		System.out.println("HEADING: "
				+ note.getElementsByTagName("heading").item(0).getFirstChild()
						.getNodeValue());
		System.out.println("BODY: "
				+ note.getElementsByTagName("body").item(0).getFirstChild()
						.getNodeValue());
		System.out
				.println("--------------------------------------------------------------------");
	}

	private static void addNote(Document doc, String from, String to,
			String heading, String body) {
		Element noteE = doc.createElement("note");
		Element toE = doc.createElement("to");
		toE.appendChild(doc.createTextNode(to));
		Element fromE = doc.createElement("from");
		fromE.appendChild(doc.createTextNode(from));
		Element headingE = doc.createElement("heading");
		headingE.appendChild(doc.createTextNode(heading));
		Element bodyE = doc.createElement("body");
		bodyE.appendChild(doc.createTextNode(body));
		noteE.appendChild(toE);
		noteE.appendChild(fromE);
		noteE.appendChild(headingE);
		noteE.appendChild(bodyE);
		doc.getElementsByTagName("notes").item(0).appendChild(noteE);
	}

}
