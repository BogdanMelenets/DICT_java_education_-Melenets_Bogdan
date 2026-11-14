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
        String result_ekran = "";
        String work_result_ekran = "";
        String line = "";
        String vvedeni = "";
        char symbol=' ';
        // System.out.println(result);
        for (int f = 1;   f <= result.length();   f++) {result_ekran=result_ekran+"-";}
        int rob = 0;
        int pomilka = 0;
        do
        {
            System.out.println(result_ekran );
            System.out.print("Input a letter:>");
            String strsymbol = scanner.nextLine();
            if (strsymbol.length() == 1)
            {
                symbol = strsymbol.charAt(0);
                if (symbol >= 'a' && symbol <= 'z') {
                    line = "";
                    line = line + symbol;
                    if (vvedeni.contains(line)) {
                        System.out.println("You've already guessed this letter.");
                    } else {
                        //шукаємо індекси входження символу у строку та коригуємо result_ekran
                        rob = 0;
                        for (int i = 0; i <= (result.length() - 1); i++) {
                            if (result.charAt(i) == symbol) {
                                work_result_ekran = work_result_ekran + symbol;
                                rob = 1;
                            } else {
                                work_result_ekran = work_result_ekran + result_ekran.charAt(i);
                            }
                        }
                        if (rob == 0) {
                            pomilka++;
                            System.out.println("That letter doesn't appear in the word");
                        }
                        result_ekran = work_result_ekran;
                        work_result_ekran = "";
                        vvedeni = vvedeni + symbol;
                        if (result.equals(result_ekran)) {System.out.println(result_ekran); pomilka=9;}
                    }
                } else {
                    System.out.println("Please enter a lowercase English letter");
                }
            }
            else {System.out.println("You should input a single letter.");}
        } while (pomilka < 8);



        if (result.equals(result_ekran)) {System.out.println("You guessed the word!");
                                          System.out.println("You survived!");}
            else {System.out.println("You lost!");}


        scanner.close();
    }
}

