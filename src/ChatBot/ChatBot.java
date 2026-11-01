package ChatBot;
import java.util.Scanner; // 1. Імпортуємо клас Scanner

public class ChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Етап 1
        String botName = "Bogdan";
        int birthYear = 2026;
        System.out.println("Hello! My name is " + botName + ".");
        System.out.println("I was created in " + birthYear + ".");
        // Етап 2
        System.out.println("Please, remind me your name.");
        String your_name = scanner.nextLine(); // Зчитуємо рядок
        System.out.println("What a great name you have," + your_name + "!");
        // Етап 3
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");
        int remainder3 = scanner.nextInt(); // Зчитуємо число
        int remainder5 = scanner.nextInt(); // Зчитуємо число
        int remainder7 = scanner.nextInt(); // Зчитуємо число
        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.println("Your age is " + age + " that's a good time to start programming!");
        // Етап 4
        System.out.println("Now I will prove to you that I can count to any number you want!");
        int userInp = scanner.nextInt(); // Зчитуємо число
        for (int i = 0;   i <= userInp;   i++) {
            System.out.println(i+" !");
        }
        // Етап 5
        System.out.println("What are the main types of loops in Java?");
        System.out.println("1 - while");
        System.out.println("2 - while, for");
        System.out.println("3 - while, for, do-while, for-each");
        System.out.println("4 - while, for, do-while");
        int res = 0;
        do {
            res = scanner.nextInt();
            if (res != 3) {System.out.println("Wrong answer");};
            if (res==3) {
                System.out.println("Correct");
                System.out.println("Goodbye, have a nice day!");
            }
        } while (res != 3);
       scanner.close();
    }
}
