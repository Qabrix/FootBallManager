package GUIpack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Exporter {
    private  File dbFile;
    private  FileWriter writer;
    public Exporter(){
        try{
            dbFile = new File("db.csv");
            writer = new FileWriter(dbFile);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }
    public  FileWriter getWriter() throws IOException {
        return writer;
    }
    public  void finish() throws IOException {
        writer.flush();
        writer.close();
    }
}
