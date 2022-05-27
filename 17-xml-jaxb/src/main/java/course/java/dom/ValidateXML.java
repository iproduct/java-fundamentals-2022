package course.java.dom;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.XMLConstants;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ValidateXML {

    public static void validate(String xmlschema, String xmlfile) {
        try {
            // ...
// Exception handling is not shown.
// The ErrorHandler implementation which could just do System.err dumps is not shown.

// build an XSD-aware SchemaFactory
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

// hook up org.xml.sax.ErrorHandler implementation.
//            ErrorHandler myErrorHandler = schemaFactory.getErrorHandler();
            schemaFactory.setErrorHandler(new ErrorHandler() {
				
				@Override
				public void warning(SAXParseException exception) throws SAXException {
					System.err.println("!!! WARNING: "+ exception.getMessage());	
				}
				
				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
					throw exception;
				}
				
				@Override
				public void error(SAXParseException exception) throws SAXException {
					System.err.println("!!! ERROR: "+ exception.getMessage());
				}
			});

// get the custom xsd schema describing the required format for my XML files.
            Schema schemaXSD = schemaFactory.newSchema(new File(xmlschema));

// Create a Validator capable of validating XML files according to my custom schema.
            Validator validator = schemaXSD.newValidator();

// Get a parser capable of parsing vanilla XML into a DOM tree
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();

// parse the XML purely as XML and get a DOM tree represenation.
            Document document = parser.parse(new File(xmlfile));


// parse the XML DOM tree againts the stricter XSD schema
//            validator.validate(new DOMSource(document),null);
            validator.validate(new StreamSource(new File(xmlfile)),null);
        } catch (IOException ex) {
            Logger.getLogger(ValidateXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException saxex) {
            saxex.printStackTrace();
        } catch (ParserConfigurationException pcex) {
            pcex.printStackTrace();
        }
    }
    public static void main(String[] args){
    	ValidateXML.validate("note9.xsd", "note2.xml");
    	System.out.println("Done.");
    }
}

