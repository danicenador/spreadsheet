package MainPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Utilities {
    // Methods
    public static int[] CoordinateTranslator(String coordinates){ // AS20 -> [column,row]
        int[] toReturn = new int[2];
        int i = 0;
        for (char ch: coordinates.toCharArray()) {
            if (Character.isDigit(ch)){
                break;
            }else{
                i++;
            }
        }
        char[] ch  = coordinates.substring(0,i).toCharArray();
        int j= ch.length-1;
        for(char c : ch){
            int temp = Character.getNumericValue(c)-9;
            toReturn[0]+=temp*Math.pow(26, j);
            j--;
        }
        toReturn[1]=Integer.parseInt(coordinates.substring(i));
        return toReturn;
    }
    public static String CoordinateTranslator(int[] coordinates){ // [column,row] -> AS20
        String toReturn="";
        ArrayList<Integer> b =new ArrayList<Integer>();
        int a=coordinates[0];
        while (a>26){
            if(a%26!=0){
                b.add(a%26);
                a=a/26;
            }else {
                b.add(26);
                a=a/26-1;
            }
        }
        char c=Character.forDigit(a+9,36);
        toReturn+=Character.toUpperCase(c);
        Collections.reverse(b);
        for (int i:b){
            c=Character.forDigit(i+9,36);
            toReturn+=Character.toUpperCase(c);
        }
        toReturn+=String.valueOf(coordinates[1]);
        return toReturn;
    }
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
