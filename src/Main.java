import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    // Инициализация полей

    private static boolean isGlobalValid = true;
    private static String filepath = "D:\\untitled3\\example.txt";
    private static char[] charsFromString;

    static {
        try {
            charsFromString = getString().toCharArray();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }

    public static void main(String[] args) {
        //Проверка на всевозможные варианты
        countTest();
        closesWithoutOpensTest();
        withoutCloseTest();

        //Проверка, были ли ошибки
        if (isGlobalValid) {
            System.out.println("Все хорошо");
        }
    }

    private static void countTest() {
        //Проверека на количество закрывающих скобок
        int countOfOpenings = 0;
        int countOfCloses = 0;
        for (char charFromString : charsFromString) {
            if (charFromString == ')') {
                countOfCloses++;
            }
            if (charFromString == '(') {
                countOfOpenings++;
            }
        }
        if (countOfOpenings != countOfCloses) {
            isGlobalValid = false;
            System.out.println("Ошибка, колчиество открывающих, и закрывающих скобок не равно");
        }
    }

    private static void closesWithoutOpensTest() {

        //Проверка нет ли закрывающих скобок, раньше открывающих
        boolean isValid = true;
        boolean wasOpenening = false;
        for (int i = 0; i < charsFromString.length; i++) {
            if (charsFromString[i] == '(') {
                wasOpenening = true;
                continue;
            }
            if ((charsFromString[i] == ')') && (!wasOpenening)) {
                isValid = false;
                isGlobalValid = false;
                break;
            } else {
                isValid = true;
            }
        }
        if (!isValid) {
            System.out.println("Ошибка, нет открывающей скобки");
        }
    }

    private static void withoutCloseTest() {

        // Проверка есть ли закрывающие скобки
        boolean isValid = true;
        for (int i = 0; i < charsFromString.length; i++) {
            if ('(' == charsFromString[i]) {
                for (int j = i; j < charsFromString.length; j++) {
                    isValid = charsFromString[j] == ')';
                }
                if (!isValid) {
                    isGlobalValid = false;
                    break;
                }
            }
        }
        if (!isValid) {
            System.out.println("Ошибка, нет закрывающей скобки.");
        }
    }

    private static String getString() throws FileNotFoundException {
        String result = "";
        Scanner scanner = new Scanner(new FileReader(filepath));
        while (scanner.hasNext()) {
            result = result + scanner.next();
        }
        return result;
    }
}
