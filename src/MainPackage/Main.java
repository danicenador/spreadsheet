package MainPackage;

import java.util.ArrayList;
import java.util.Scanner;
import static MainPackage.Utilities.*;

public class Main {
    // Attributes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    Scanner scanner = new Scanner(java.lang.System.in);
    SystemController sis = new SystemController();

    // Methods
    private void header(){
        java.lang.System.out.println("=======================================================================================");
        java.lang.System.out.println(ANSI_CYAN+"                                 SPREADSHEET PROGRAM"+ANSI_RESET);
        java.lang.System.out.println("=======================================================================================");

    }
    private void mainMenu(){
        java.lang.System.out.println();
        java.lang.System.out.println("Main menu:");
        java.lang.System.out.println("_______________________________________________________________________________________");
        java.lang.System.out.println(ANSI_CYAN+"1"+ANSI_RESET+": Save spreadsheet, "+ANSI_CYAN+"2"+ANSI_RESET+": Load spreadsheet, "
                +ANSI_CYAN+"3"+ANSI_RESET+": Show cell content, "+ANSI_CYAN+"4"+ANSI_RESET+": Modify cell content, "
                +ANSI_CYAN);
        java.lang.System.out.println("5"+ANSI_RESET+": Show spreadsheet, "+ANSI_CYAN+"6"+ANSI_RESET+": Quit");
    }
    private void saveSpreadsheet(){
        java.lang.System.out.println("Save spreadsheet");
        java.lang.System.out.println("______________________________________________");
        java.lang.System.out.println("Input save file path:");
        String path = scanner.nextLine();
        sis.saveManager.saveSpreadsheet(path);
        java.lang.System.out.println(ANSI_GREEN + "Spreadsheet successfully saved at: "+path + ANSI_RESET);
    }
    private void loadSpreadsheet(){
        java.lang.System.out.println("Load spreadsheet");
        java.lang.System.out.println("______________________________________________");
        java.lang.System.out.println("Input existing save file path:");
        String path = scanner.nextLine();
        if(FileExists(path)){
            sis.saveManager.loadSpreadsheet(path);
            sis.spreadsheet=Spreadsheet.GetInstance();
            java.lang.System.out.println(ANSI_GREEN + "Spreadsheet successfully loaded" + ANSI_RESET);
        }else{
            java.lang.System.out.println(ANSI_RED + "Save file not found" + ANSI_RESET);
        }
    }
    private void showCellContent(){
        java.lang.System.out.println("Show cell content");
        java.lang.System.out.println("______________________________________________");
        java.lang.System.out.println("Input cell coordinates:");
        String coordinates = scanner.nextLine();
        if(IsCoordinates(coordinates)) {
            if (sis.spreadsheet.insideRange(coordinates)) {
                java.lang.System.out.println(coordinates.toUpperCase() + ": " + sis.spreadsheet.showCellContent(coordinates));
            } else {
                java.lang.System.out.println(coordinates.toUpperCase() + ": ");
            }
        }else{
            java.lang.System.out.println(ANSI_RED + "Invalid coordinates" + ANSI_RESET);
        }
    }
    private void modifyCellContent(){
        java.lang.System.out.println("Modify cell content");
        java.lang.System.out.println("______________________________________________");
        java.lang.System.out.println("Input cell coordinates:");
        String coordinates = scanner.nextLine();
        if(IsCoordinates(coordinates)) {
            java.lang.System.out.println("Previous content");
            if(sis.spreadsheet.insideRange(coordinates)){
                java.lang.System.out.println(coordinates.toUpperCase()+": "+     sis.spreadsheet.showCellContent(coordinates));
            }else{
                java.lang.System.out.println(coordinates.toUpperCase()+": ");
            }
            java.lang.System.out.println("Input new content:");
            String newContent = scanner.nextLine();
            if (!newContent.equals("")){
                sis.spreadsheet.modifyCellContent(coordinates,newContent);
            }
        }else{
            java.lang.System.out.println(ANSI_RED + "Invalid coordinates" + ANSI_RESET);
        }
    }
    public void showSpreadsheet(){
        ArrayList<String> spreadsheet=sis.spreadsheet.showSpreadsheetValue();
        for (String s:spreadsheet){
            System.out.println(s);
        }
    }
    private void run(){
        header();
        while (true) {
            mainMenu();
            String MainMenuChoice = scanner.nextLine();
            switch (MainMenuChoice) {
                case "1" -> {
                    saveSpreadsheet();
                }
                case "2" -> {
                    loadSpreadsheet();
                }
                case "3" -> {
                    showCellContent();
                }
                case "4" -> {
                    modifyCellContent();
                }
                case "5" -> {
                    showSpreadsheet();
                }
                case "6" -> {
                    java.lang.System.out.println("Closing program...");
                    scanner.close();
                    java.lang.System.out.println("Spreadsheet program closed.");
                    return;
                }
                default -> java.lang.System.out.println(ANSI_RED + "Wrong input, " +
                        "please use an index from the Main menu." + ANSI_RESET);

            }
        }
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

}
