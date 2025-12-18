import java.util.*;
import java.lang.*;

/* * Question 4 :
    Differentiate String, StringBuffer and StringBuilder. Write a program to perform String
    related operations using length(), isEmpty(), chatAt(), toString(), equals(), compareTo(),
    contains(), indexOf(), lastIndexOf(), startsWith(), endsWith(), matches(), substring(),
    toLowerCase(), trim(), replace(), split(), join(), and valueOf().
 */

public class Question4 {
    public static void main(String[] args) {
        // --- String examples ---
        String s = "  Hello, World!  ";
        System.out.println("String s: \"" + s + "\"");
        System.out.println("length(): " + s.length());
        System.out.println("isEmpty(): " + s.isEmpty());
        System.out.println("charAt(2): " + s.charAt(2));                 // 'H'
        System.out.println("toString(): " + s.toString());
        System.out.println("equals(\\\"  Hello, World!  \\\"): " + s.equals("  Hello, World!  "));
        System.out.println("compareTo(\"Hello\"): " + s.compareTo("Hello"));
        System.out.println("contains(\"World\"): " + s.contains("World"));
        System.out.println("indexOf('o'): " + s.indexOf('o'));
        System.out.println("lastIndexOf('o'): " + s.lastIndexOf('o'));
        System.out.println("startsWith(\"  H\"): " + s.startsWith("  H"));
        System.out.println("endsWith(\"  \"): " + s.endsWith("  "));
        System.out.println("matches(\"\\\\s*Hello, World!\\\\s*\"): " + s.matches("\\s*Hello, World!\\s*"));
        System.out.println("substring(2,7): \"" + s.substring(2, 7) + "\""); // "Hello"
        System.out.println("toLowerCase(): \"" + s.toLowerCase() + "\"");
        System.out.println("trim(): \"" + s.trim() + "\"");
        System.out.println("replace(',', ';'): \"" + s.replace(',', ';') + "\"");
        String[] parts = s.split(",");
        System.out.println("split(','): " + Arrays.toString(parts));
        System.out.println("join with \"|\": " + String.join("|", parts));
        System.out.println("valueOf(123): " + String.valueOf(123));
        System.out.println();

        // --- StringBuffer examples ---
        StringBuffer sb = new StringBuffer("HelloBuffer");
        System.out.println("StringBuffer sb: \"" + sb + "\"");
        System.out.println("length(): " + sb.length());
        System.out.println("isEmpty() equivalent (length()==0): " + (sb.length() == 0));
        System.out.println("charAt(1): " + sb.charAt(1));
        System.out.println("toString(): " + sb.toString());
        // equals() on StringBuffer is Object.equals (reference equality)
        System.out.println("equals(new StringBuffer(\"HelloBuffer\")): " + sb.equals(new StringBuffer("HelloBuffer")));
        // compareTo not present; use toString().compareTo(...)
        System.out.println("compareTo(\"HelloBuffer\"): " + sb.toString().compareTo("HelloBuffer"));
        // contains not present; use indexOf
        System.out.println("contains(\"Buffer\") equivalent (indexOf!=-1): " + (sb.indexOf("Buffer") != -1));
        System.out.println("indexOf(\"Buf\"): " + sb.indexOf("Buf"));
        System.out.println("lastIndexOf(\"l\"): " + sb.lastIndexOf("l"));
        System.out.println("substring(5,11): \"" + sb.substring(5, 11) + "\""); // "Buffer"
        // replace in StringBuffer uses index ranges
        sb.replace(5, 11, "Buf");
        System.out.println("after replace(5,11,\"Buf\"): \"" + sb + "\"");
        System.out.println("valueOf(sb): " + String.valueOf(sb));
        System.out.println();

        // --- StringBuilder examples ---
        StringBuilder sbd = new StringBuilder("HelloBuilder");
        System.out.println("StringBuilder sbd: \"" + sbd + "\"");
        System.out.println("length(): " + sbd.length());
        System.out.println("isEmpty() equivalent (length()==0): " + (sbd.length() == 0));
        System.out.println("charAt(2): " + sbd.charAt(2));
        System.out.println("toString(): " + sbd.toString());
        System.out.println("equals(new StringBuilder(\"HelloBuilder\")): " + sbd.equals(new StringBuilder("HelloBuilder")));
        System.out.println("compareTo using toString(): " + sbd.toString().compareTo("HelloBuilder"));
        System.out.println("indexOf(\"Builder\"): " + sbd.indexOf("Builder"));
        System.out.println("lastIndexOf(\"l\"): " + sbd.lastIndexOf("l"));
        System.out.println("substring(5): \"" + sbd.substring(5) + "\""); // "Builder"
        sbd.replace(5, sbd.length(), "B");
        System.out.println("after replace(5,length(),\"B\"): \"" + sbd + "\"");
        System.out.println("valueOf(sbd): " + String.valueOf(sbd));
    }
}

