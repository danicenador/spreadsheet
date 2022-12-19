package impl;

import ARQSOFT.SystemController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ARQSOFT.Checker.IsCoordinates;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SystemTest {

    SystemController SC;


    @BeforeEach
    void doSetup() {
        SC = new SystemController();
    }

    @Test
    void test1() { // coordiante translator & coordinate checker
        int[]b = new int[2];
        b[0]=5;
        b[1]=5;
        String a = SC.spreadsheet.coordinateTranslator(b);
        assertEquals("E5",a);
        assertEquals(b[0],SC.spreadsheet.coordinateTranslator(a)[0]);
        assertEquals(b[1],SC.spreadsheet.coordinateTranslator(a)[1]);
        String c = "UVWXYZ5879";
        assertEquals(true, IsCoordinates(c));
        int[] d = SC.spreadsheet.coordinateTranslator(c);
        assertEquals(SC.spreadsheet.coordinateTranslator(d),c);
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

}