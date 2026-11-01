package ChatBot;
import java.util.Scanner; // 1. Імпортуємо клас Scanner

public class ChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String botName = "Bogdan";
        int birthYear = 2026;

        System.out.println("Hello! My name is " + botName + ".");
        System.out.println("I was created in " + birthYear + ".");
        System.out.println("Please, remind me your name.");
        String your_name = scanner.nextLine(); // Зчитуємо рядок
        System.out.println("What a great name you have," + your_name + "!");
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");
        int remainder3 = scanner.nextInt(); // Зчитуємо число
        int remainder5 = scanner.nextInt(); // Зчитуємо число
        int remainder7 = scanner.nextInt(); // Зчитуємо число
        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.println("Your age is " + age + " that's a good time to start programming!");

        scanner.close();


    }
}
