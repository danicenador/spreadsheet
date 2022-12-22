package ARQSOFT;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static ARQSOFT.Utilities.CoordinateTranslator;


public class SaveManager {
    // Methods
    public boolean saveSpreadsheet(String fileName){
        Spreadsheet spreadsheet = Spreadsheet.GetInstance();
        Iterator iterator = spreadsheet.createIterator();
        boolean toReturn;
        try{
            FileWriter writer = new FileWriter(fileName);
            String tmp="";
            Cell old_c = iterator.getCurrent();
            Cell c = iterator.getCurrent();
            tmp+=c.getContent().replace(';',',')+";";
            while (iterator.hasNext()){
                iterator.next();
                c = iterator.getCurrent();
                int[] coord=CoordinateTranslator(c.getCoordinates());
                int[] oldCoord=CoordinateTranslator(old_c.getCoordinates());
                if (coord[1]!=oldCoord[1]){
                    writer.write(tmp.substring(0, tmp.length() - 1) +"\n");
                    tmp="";
                }
                tmp+=c.getContent().replace(';',',')+";";
                old_c = iterator.getCurrent();
            }
            writer.write(tmp.substring(0, tmp.length() - 1));
            writer.close();
            toReturn = true;
        } catch (IOException e) {
            toReturn = false;
        }

        return toReturn;
    }
    public void loadSpreadsheet(String fileName){
        Spreadsheet spreadsheet = Spreadsheet.GetInstance();
        spreadsheet.reset();
        spreadsheet = Spreadsheet.GetInstance();
        try {
            FileReader reader = new FileReader(fileName);
            int[] coordinates=new int[2];
            coordinates[0] = 1; // column
            coordinates[1] = 1; // row
            String tmpContent ="";
            int data = 0;
            int columns=0;
            while (data != -1){
                data = reader.read();
                if ((char) data == ';'){
                    spreadsheet.modifyCellContent(Utilities.CoordinateTranslator(coordinates),tmpContent.replace(',',';'));
                    coordinates[0]++;
                    tmpContent="";
                } else if ((char) data == '\n') {
                    if (tmpContent.charAt(tmpContent.length()-1)=='\r'){
                        spreadsheet.modifyCellContent(Utilities.CoordinateTranslator(coordinates), tmpContent.substring(0, tmpContent.length() - 1).replace(',',';'));
                    }else{
                        spreadsheet.modifyCellContent(Utilities.CoordinateTranslator(coordinates),tmpContent.replace(',',';'));
                    }
                    coordinates[1]++;
                    columns=coordinates[0];
                    coordinates[0]=1;
                    tmpContent="";
                } else {
                    tmpContent+=(char)data;
                }
            }
            if (!tmpContent.equals("")){
                if (Character.getType(tmpContent.charAt(0))!=0) {
                    spreadsheet.modifyCellContent(Utilities.CoordinateTranslator(coordinates), tmpContent.substring(0, tmpContent.length() - 1).replace(',',';'));
                }else{
                    coordinates[1]--;
                }
            }
            if (columns==0){
                columns=1;
            }
            spreadsheet.setColumnsNumber(columns);
            spreadsheet.setRowsNumber(coordinates[1]);
            reader.close();
        } catch (IOException e) {
        }
    }
}
