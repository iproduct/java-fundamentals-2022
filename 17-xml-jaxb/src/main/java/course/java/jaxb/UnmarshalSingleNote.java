package course.java.jaxb;

import course.java.jaxb.model.Note;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

/** 
  * This shows how to use JAXB to unmarshal an xml file
  * Then display the information from the content tree
  */

public class UnmarshalSingleNote {

    public static void main (String args[]) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
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
            Schema notesXSD = schemaFactory.newSchema(new File("xml/note.xsd"));


            JAXBContext jc = JAXBContext.newInstance("course.java.jaxb.model");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            unmarshaller.setSchema(notesXSD);

            Note note= (Note)
                     unmarshaller.unmarshal(new File( "xml/note.xml"));

               System.out.println("Note  details "   );

               System.out.println("to: " +  note.getTo());
               System.out.println("from: " +  note.getFrom());
               System.out.println("heading: " +  note.getHeading());
               System.out.println("body: " +  note.getBody());

               System.out.println();

            } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
