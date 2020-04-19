package us.ryguy.reports.util;

public class ArrayUtils {
    //Why do I insist on using Java 7
    public String join(String[] a, String sep) {
        StringBuilder sb = new StringBuilder();
        for(String st : a) {
            sb.append(st + sep);
        }
        return sb.toString();
    }
    public String[] removeFromArray(String[] a, int index) {
        String[] resultant = new String[a.length - 1];
        int j = 0;
        for(int i = 0; i < a.length; i++) {
            if(i == index) continue;
            resultant[j++] = a[i];
        }
        return resultant;
    }
}
