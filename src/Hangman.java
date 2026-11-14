import java.util.Scanner; // 1. Імпортуємо клас Scanner

public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 2. Создаем объект Scanner
        System.out.println("HANGMAN");
        System.out.print("Guess the word:>");
        String line = scanner.nextLine(); // 3. Считываем строку
        String str1="java";
        if (str1.equals(line)) {System.out.println("You survived!");}
           else {System.out.println("You lost!");}
        scanner.close();
    }
}

