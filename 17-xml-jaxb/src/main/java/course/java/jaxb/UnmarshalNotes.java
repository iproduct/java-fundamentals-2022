/**
 * $Id: UsingJAXBTest1.java,v 1.1 2003/01/01 03:18:32 bhakti Exp $
 * Copyright (c) 2003 Sun Microsystems, Inc. All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 * <p>
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */

package course.java.jaxb;

import course.java.jaxb.model.ExtendedNoteType;
import course.java.jaxb.model.NoteListType;
import course.java.jaxb.model.NoteType;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

//import notes.jaxb.Note;
//import notes.jaxb.Notes;


/**
 * This shows how to use JAXB to unmarshal an xml file
 * Then display the information from the content tree
 */

public class UnmarshalNotes {

    public static void main(String args[]) {
        try {
            JAXBContext jc = JAXBContext.newInstance("course.java.jaxb.model");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            //unmarshaller.setValidating(true);

            var jaxbRootElem = (JAXBElement) unmarshaller.unmarshal(new File("xml/notes2.xml"));
            var notes = (NoteListType) jaxbRootElem.getValue();
            List<ExtendedNoteType> noteList = notes.getNote();

            for (int i = 0; i < noteList.size(); i++) {
                System.out.println("Note  details ");
                NoteType note = (NoteType) noteList.get(i);

                System.out.println("to: " + note.getTo());
                System.out.println("from: " + note.getFrom());
                System.out.println("heading: " + note.getHeading());
                System.out.println("body: " + note.getBody());
                System.out.println();
            }

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
}
