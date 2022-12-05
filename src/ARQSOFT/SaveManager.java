package ARQSOFT;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class SaveManager {
    // Methods
    public boolean saveSpreadsheet(Spreadsheet spreadsheet, String fileName){
        boolean toReturn;
        try{
            FileWriter writer = new FileWriter(fileName);
            for (ArrayList<Cell> row: spreadsheet.getCells()){
                String tmp="";
                for (Cell c: row){
                    tmp+=c.getContent()+";";
                }
                tmp=tmp.substring(0, tmp.length() - 1);
                writer.write(tmp +"\n");
            }
            writer.close();
            toReturn = true;
        } catch (IOException e) {
            toReturn = false;
        }

        return toReturn;
    }
    public Spreadsheet loadSpreadsheet(String fileName){
        Spreadsheet spreadsheet = Spreadsheet.GetInstance();
        spreadsheet.reset();
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
                    spreadsheet.modifyCellContent(spreadsheet.coordinateTranslator(coordinates),tmpContent);
                    coordinates[0]++;
                    tmpContent="";
                } else if ((char) data == '\n') {
                    spreadsheet.modifyCellContent(spreadsheet.coordinateTranslator(coordinates),tmpContent);
                    coordinates[1]++;
                    columns=coordinates[0];
                    coordinates[0]=1;
                    tmpContent="";
                } else {
                    tmpContent+=(char)data;
                }
            }
            spreadsheet.setColumnsNumber(columns);
            spreadsheet.setRowsNumber(coordinates[1]-1);
            reader.close();
            return spreadsheet;
        } catch (IOException e) {
            return spreadsheet;
        }
    }
}
