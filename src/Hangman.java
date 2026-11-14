import java.util.Scanner; // 1. Імпортуємо клас Scanner
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hangman {
    public static void main(String[] args) {
        // Создаем пустой список строк
        List<String> words = new ArrayList<>();
        // Добавляем элементы
        words.add("python");
        words.add("java");
        words.add("javascript");
        words.add("kotlin");
        Scanner scanner = new Scanner(System.in); // 2. Создаем объект Scanner
        Random random = new Random();
        System.out.println("HANGMAN");
        String result= words.get(random.nextInt(4)); // result випадково обираємо зі списку
        String result_ekran = result.substring(0, 2);
        for (int i = 3;   i <= result.length();   i++) {
            result_ekran=result_ekran+"-";
        }
        System.out.print("Guess the word " + result_ekran + ":>");
        String line = scanner.nextLine(); // 3. Считываем строку
        if (result.equals(line)) {System.out.println("You survived!");}
           else {System.out.println("You lost!");}
        scanner.close();
    }
}

