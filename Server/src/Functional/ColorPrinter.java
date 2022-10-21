package Functional;

public class ColorPrinter {

    public static final String BLUE = "\u001b[34m";
    public static final String WHITE = "\u001b[37m";
    public static final String RED = "\u001b[31m";


    public static void print(String color, Object text) {
        System.out.print(color + text);
    }
    public static void println(String color, Object text) {
        System.out.println(color + text);
    }
}