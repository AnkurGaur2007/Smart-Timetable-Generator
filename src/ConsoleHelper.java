public class ConsoleHelper {

    public static void printSeparator() {
        System.out.println("--------------------------------------------------");
    }


    public static void printTitle(String title) {
        System.out.println("==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }

    public static void printBlankLine() {
        System.out.println();
    }

    public static void printSection(String sectionName) {
        printBlankLine();
        printSeparator();
        System.out.println(sectionName);
        printSeparator();
    }
}
