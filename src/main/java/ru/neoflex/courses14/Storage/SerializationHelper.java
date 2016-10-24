package ru.neoflex.courses14.Storage;

import java.io.*;

/* Created with IntelliJ IDEA.
 * User: Руслан
 * Date: 12.12.14
 * Time: 3:00 
 */
public class SerializationHelper {
    public void writeObject(File file, Object obj) throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        Throwable throwable = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.flush();
        } catch (FileNotFoundException e) {
            throwable = e;
            throw e;
        } catch (IOException e) {
            throwable = e;
            throw e;
        } finally {
            if (throwable == null) {
                oos.close();
                fos.close();
            } else {
                try {
                    oos.close();
                    fos.close();
                } catch (Throwable unused) {
                }
            }
        }
    }

    public Object readObject(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Object obj = null;
        Throwable throwable = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            obj = ois.readObject();
        } catch (FileNotFoundException e) {
            throwable = e;
            throw e;
        } catch (IOException e) {
            throwable = e;
            throw e;
        } catch (ClassNotFoundException e) {
            throwable = e;
            throw e;
        } finally {
            if (throwable == null) {
                ois.close();
                fis.close();
            } else {
                try {
                    ois.close();
                    fis.close();
                } catch (Throwable unused) {
                }
            }
        }
        return obj;
    }
}
