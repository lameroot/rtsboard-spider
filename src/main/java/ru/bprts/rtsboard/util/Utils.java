package ru.bprts.rtsboard.util;

import org.jsefa.common.lowlevel.LowLevelDeserializer;
import org.jsefa.common.lowlevel.io.LineSegment;
import org.jsefa.csv.CsvDeserializer;
import org.jsefa.csv.CsvDeserializerImpl;
import org.jsefa.rbf.lowlevel.RbfLowLevelDeserializerImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by lameroot on 28.03.15.
 */
public class Utils {

    public static String getOriginalCsvLine(CsvDeserializer csvDeserializer) {
        if ( null == csvDeserializer || !(csvDeserializer instanceof CsvDeserializerImpl) ) return null;
        try {
            Method method = CsvDeserializerImpl.class.getDeclaredMethod("getLowLevelDeserializer");
            method.setAccessible(true);
            LowLevelDeserializer lowLevelDeserializer = (LowLevelDeserializer)method.invoke(csvDeserializer);
            if (lowLevelDeserializer == null) return null;
            Field field = RbfLowLevelDeserializerImpl.class.getDeclaredField("currentSegment");
            field.setAccessible(true);
            return ((LineSegment) field.get(lowLevelDeserializer)).getContent();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void unZipIt(String zipFile, String outputFolder){

        byte[] buffer = new byte[1024];

        try{

            //create output directory is not exists
            File folder = new File(outputFolder);
            if(!folder.exists()){
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while(ze!=null){

                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);

                System.out.println("file unzip : "+ newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("Done");

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
