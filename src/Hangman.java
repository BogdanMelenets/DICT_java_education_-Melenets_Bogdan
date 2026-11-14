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
        // System.out.println(result);
        for (int f = 1;   f <= result.length();   f++) {result_ekran=result_ekran+"-";}

        for (int j = 1;   j <= 8;   j++)
        {
            System.out.println(result_ekran );
            System.out.print("Input a letter:>");
            char symbol = scanner.next().charAt(0);
            //шукаємо індекси входження символу у строку та коригуємо result_ekran
            for (int i = 0;   i <= (result.length()-1);   i++)
            {
                if (result.charAt(i) == symbol) {work_result_ekran=work_result_ekran+symbol ;}
                else {work_result_ekran=work_result_ekran+result_ekran.charAt(i);}
            }
            result_ekran = work_result_ekran;
            work_result_ekran="";
        }

        if (result.equals(result_ekran)) {System.out.println("You survived!");}
            else {System.out.println("You lost!");}


        scanner.close();
    }
}

