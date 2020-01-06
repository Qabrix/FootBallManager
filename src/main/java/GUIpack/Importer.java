package GUIpack;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Importer {
    private File dbFile;
    private FileReader reader;
    public Importer(){
        try{
            dbFile = new File("db.csv");
            reader = new FileReader(dbFile);
        }
        catch(Exception ex){
            ex.printStackTrace();
            }
        }
        public  FileReader getReader(){
            return reader;
        }
        public  void finish() throws IOException {
            reader.close();
        }
}
