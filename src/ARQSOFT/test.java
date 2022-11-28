package ARQSOFT;

import java.util.ArrayList;

public class test {

    public static void main(String[] args) {
        SystemController sis = new SystemController();

        sis.spreadsheet.modifyCellContent("A1","=C3+1");
        sis.spreadsheet.modifyCellContent("C3","2");

        java.lang.System.out.println(sis.spreadsheet.findCellAndReturn("A1").textValue(sis.spreadsheet));




    }

}
