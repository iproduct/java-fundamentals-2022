//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.27 at 01:00:53 PM EEST 
//


package course.java.jaxb.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the course.java.jaxb.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Notes_QNAME = new QName("http://iproduct.org/notes", "notes");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: course.java.jaxb.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NoteListType }
     * 
     */
    public NoteListType createNoteListType() {
        return new NoteListType();
    }

    /**
     * Create an instance of {@link NoteType }
     * 
     */
    public NoteType createNoteType() {
        return new NoteType();
    }

    /**
     * Create an instance of {@link ExtendedNoteType }
     * 
     */
    public ExtendedNoteType createExtendedNoteType() {
        return new ExtendedNoteType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoteListType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoteListType }{@code >}
     */
    @XmlElementDecl(namespace = "http://iproduct.org/notes", name = "notes")
    public JAXBElement<NoteListType> createNotes(NoteListType value) {
        return new JAXBElement<NoteListType>(_Notes_QNAME, NoteListType.class, null, value);
    }

}
