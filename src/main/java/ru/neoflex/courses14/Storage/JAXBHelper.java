package ru.neoflex.courses14.Storage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/* Created with IntelliJ IDEA.
 * User: Руслан
 * Date: 12.12.14
 * Time: 3:08 
 */
public class JAXBHelper {
    public void marshal(Object obj, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, file);
        } catch (JAXBException e) {
            System.out.println(e);
        }
    }

    public Object unmarshal(File file, Class clazz) {
        Object result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            result = unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.out.println(e);
        }
        return result;
    }
}
