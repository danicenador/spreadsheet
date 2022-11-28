package ARQSOFT;

import java.io.File;

public class FormulaParser {
    // Methods

    public static boolean IsDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean IsInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean FileExists(String path){
        boolean toReturn = false;
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {
            toReturn=true;
        }
        return toReturn;
    }
    public static boolean IsCoordinates(String coordinates){
        boolean toReturn = true;
        int i = 0;
        for (char ch: coordinates.toCharArray()) {
            if (Character.isDigit(ch)){
                break;
            }else{
                i++;
            }
        }
        if (i==0){
            toReturn=false;
        }
        if (!IsInteger(coordinates.substring(i))){
            toReturn=false;
        }
        return toReturn;
    }

    public void processFormula(){}
    public void dependencyUpdate(){}
    public void circularDependencyDetection(){}
    public void suma(){}
    public void max(){}
    public void min(){}
    public void promedio(){}
}
