package impl;

import MainPackage.Spreadsheet;
import MainPackage.SystemController;
import MainPackage.Utilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static MainPackage.Utilities.IsCoordinates;
import static MainPackage.Utilities.CoordinateTranslator;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SystemTest {

    SystemController SC;


    @BeforeEach
    void doSetup() {
        SC = new SystemController();
    }

    @Test
    void test1() { // coordinate translator & coordinate checker
        int[]b = new int[2];
        b[0]=5;
        b[1]=5;
        String a = Utilities.CoordinateTranslator(b);
        assertEquals("E5",a);
        assertEquals(b[0], CoordinateTranslator(a)[0]);
        assertEquals(b[1], CoordinateTranslator(a)[1]);
        String c = "UVWXYZ5879";
        assertEquals(true, IsCoordinates(c));
        int[] d = CoordinateTranslator(c);
        assertEquals(Utilities.CoordinateTranslator(d),c);
        assertEquals(false, IsCoordinates("This is any string"));
        assertEquals(false, IsCoordinates("1A1"));
        assertEquals(false, IsCoordinates("DF5.7"));
        assertEquals(false, IsCoordinates("FF"));
        assertEquals(false, IsCoordinates("G5H"));
        assertEquals(true, IsCoordinates("A1"));
        assertEquals(true, IsCoordinates("BC23"));
        assertEquals(true, IsCoordinates("DEFHGJ09876"));
    }
    @Test
    void test2() {  // Create cells
        SC.spreadsheet.modifyCellContent("A1","5");
        assertEquals("5.0", SC.spreadsheet.findCellAndReturn("A1").textValue());
        assertEquals(5, SC.spreadsheet.findCellAndReturn("A1").numericalValue());
        SC.spreadsheet.modifyCellContent("ZZ520","texto");
        assertEquals("texto", SC.spreadsheet.findCellAndReturn("ZZ520").textValue());
        // implement error           assertEquals(5, SC.spreadsheet.findCellAndReturn("ZZ520").numericalValue());
        SC.spreadsheet.modifyCellContent("D4","=529*3+1");
        assertEquals("1588.0", SC.spreadsheet.findCellAndReturn("D4").textValue());
        assertEquals(1588, SC.spreadsheet.findCellAndReturn("D4").numericalValue());
        SC.spreadsheet.modifyCellContent("A1","");
        assertEquals("", SC.spreadsheet.findCellAndReturn("A1").textValue());
        assertEquals(0, SC.spreadsheet.findCellAndReturn("A1").numericalValue());
    }
    @Test
    void test3() {  // Formula tree
        SC.saveManager.loadSpreadsheet("./test01");
        SC.spreadsheet= Spreadsheet.GetInstance();
        assertEquals(3, SC.spreadsheet.findCellAndReturn("a1").numericalValue());
        assertEquals(8, SC.spreadsheet.findCellAndReturn("b1").numericalValue());
        assertEquals(64, SC.spreadsheet.findCellAndReturn("c1").numericalValue());
        assertEquals(11, SC.spreadsheet.findCellAndReturn("d1").numericalValue());
        assertEquals(64, SC.spreadsheet.findCellAndReturn("a2").numericalValue());
        assertEquals(3, SC.spreadsheet.findCellAndReturn("b2").numericalValue());
        assertEquals(86, SC.spreadsheet.findCellAndReturn("c2").numericalValue());
        assertEquals(21.5, SC.spreadsheet.findCellAndReturn("d2").numericalValue());
    }

    @Test
    void test4() {  // Observers
        SC.saveManager.loadSpreadsheet("./test02");
        SC.spreadsheet= Spreadsheet.GetInstance();
        assertEquals(1111, SC.spreadsheet.findCellAndReturn("a2").numericalValue());
        SC.spreadsheet.modifyCellContent("a1","2");
        assertEquals(1112, SC.spreadsheet.findCellAndReturn("a2").numericalValue());
        SC.spreadsheet.modifyCellContent("b1","20");
        assertEquals(1122, SC.spreadsheet.findCellAndReturn("a2").numericalValue());
        SC.spreadsheet.modifyCellContent("c1","200");
        assertEquals(1222, SC.spreadsheet.findCellAndReturn("a2").numericalValue());
        SC.spreadsheet.modifyCellContent("d1","2000");
        assertEquals(2222, SC.spreadsheet.findCellAndReturn("a2").numericalValue());
    }

}