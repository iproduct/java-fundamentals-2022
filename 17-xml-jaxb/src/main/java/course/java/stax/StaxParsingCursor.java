package course.java.stax;

import java.io.FileInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class StaxParsingCursor {
	public static void main(String[] args) {
        XMLInputFactory inputFactory = null;
        try {
            inputFactory = XMLInputFactory.newFactory();
            inputFactory.setProperty(XMLInputFactory.IS_COALESCING, false);
            inputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, true);
            inputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, true);
        } catch (Exception e) {e.printStackTrace();}
        System.out.println("FACTORY: " + inputFactory);

        try {                                             
            XMLStreamReader streamReader = inputFactory.createXMLStreamReader(
            		new FileInputStream("xml/demo.xml"));
            if (streamReader.getEventType() == XMLStreamReader.START_DOCUMENT) {
                System.out.println("<?xml version=\"" + streamReader.getVersion() + "\""
                        + " encoding=\"" + streamReader.getCharacterEncodingScheme() + "\"?>");
            }
            while (streamReader.hasNext()) {
                int parsingEventType = streamReader.next();
//                printParsingEventType(parsingEventType);
                if (streamReader.isStartElement()) {
                    System.out.print("<" + streamReader.getName());
                    int count = streamReader.getAttributeCount();
                    for (int i = 0; i < count; i++) {
                        System.out.print(" " + streamReader.getAttributeName(i) + "=\""
                        		+ streamReader.getAttributeValue(i) + "\"");
                    }
                    System.out.print(">");
                } else if (streamReader.isEndElement()) {
                    System.out.print("</" + streamReader.getName() + ">");
                } else if (streamReader.hasText()) {
                	System.out.print(streamReader.getText());
                }
            }
        } catch (XMLStreamException e) {
            System.out.println(e.getMessage());
            if (e.getNestedException() != null) {
                e.getNestedException().printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns the String representation of the given integer constant.
     *
     * @param eventType Type of event.
     * @return String representation of the event
     */
    public static final String getEventTypeString(int eventType) {
        switch (eventType) {
        case XMLEvent.START_ELEMENT:
            return "START_ELEMENT";

        case XMLEvent.END_ELEMENT:
            return "END_ELEMENT";

        case XMLEvent.PROCESSING_INSTRUCTION:
            return "PROCESSING_INSTRUCTION";

        case XMLEvent.CHARACTERS:
            return "CHARACTERS";

        case XMLEvent.COMMENT:
            return "COMMENT";

        case XMLEvent.START_DOCUMENT:
            return "START_DOCUMENT";

        case XMLEvent.END_DOCUMENT:
            return "END_DOCUMENT";

        case XMLEvent.ENTITY_REFERENCE:
            return "ENTITY_REFERENCE";

        case XMLEvent.ATTRIBUTE:
            return "ATTRIBUTE";

        case XMLEvent.DTD:
            return "DTD";

        case XMLEvent.CDATA:
            return "CDATA";

        case XMLEvent.SPACE:
            return "SPACE";
        }

        return "UNKNOWN_EVENT_TYPE , " + eventType;
    }

    private static void printParsingEventType(int eventType) {
        System.out.println(
                "EVENT TYPE(" + eventType + ") = "
                + getEventTypeString(eventType));
    }
}
