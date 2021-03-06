//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.27 at 01:00:53 PM EEST 
//


package course.java.jaxb.model;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * <p>Java class for extendedNoteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="extendedNoteType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://iproduct.org/notes}noteType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "extendedNoteType", propOrder = {
    "priority"
})
public class ExtendedNoteType
    extends NoteType
{

    @XmlSchemaType(name = "positiveInteger")
    protected int priority;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "positiveInteger")
    protected long id;

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is int
     *     
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is int
     *     
     */
    public void setPriority(int value) {
        this.priority = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is long
     *     
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is long
     *     
     */
    public void setId(long value) {
        this.id = value;
    }

}
