package ARQSOFT;

import java.io.File;

public class Checker {
    // Methods

    public static boolean IsDouble(String str) {
        if (str!=null){
            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }else{
            return false;
        }
    }
    public static boolean IsInteger(String str) {
        if (str != null) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }else{
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
        if (coordinates!=null) {
            for (char ch : coordinates.toCharArray()) {
                if (!Character.isLetter(ch)) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == 0) {
                toReturn = false;
            }
            if (!IsInteger(coordinates.substring(i))) {
                toReturn = false;
            }
        }else{
            toReturn = false;
        }
        return toReturn;
    }
}
