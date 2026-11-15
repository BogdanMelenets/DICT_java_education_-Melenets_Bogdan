import java.util.Scanner; // 1. Імпортуємо клас Scanner
import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in); // 2. Создаем объект Scanner
            record coffee (String name, int water, int milk, int beans) {}
            List<coffee> coffee_type = new ArrayList<>();
            List<coffee> need_for_coffee = new ArrayList<>();
            List<coffee> in_machine_coffee = new ArrayList<>();
            coffee_type.add(new coffee("Black", 200, 50, 15));
            System.out.println("Write how many ml of water the coffee machine has:");
            System.out.print(">");
            int water = scanner.nextInt();
            System.out.println("Write how many ml of milk the coffee machine has:");
            System.out.print(">");
            int milk = scanner.nextInt();
            System.out.println("Write how many grams of coffee beans the coffee machine has:");
            int beans = scanner.nextInt();
            System.out.print(">");
            in_machine_coffee.add(new coffee("00", water, milk, beans));
            System.out.println("Write how many cups of coffee you will need:");
            System.out.print(">");
            int count = scanner.nextInt();
            need_for_coffee.add(new coffee(coffee_type.get(0).name, coffee_type.get(0).water*count, coffee_type.get(0).milk*count, coffee_type.get(0).beans*count));
            // Порівнюємо поля двох різних структур
            boolean hasEnoughResources = in_machine_coffee.get(0).water() >= need_for_coffee.get(0).water() &&
                    in_machine_coffee.get(0).milk() >= need_for_coffee.get(0).milk() &&
                    in_machine_coffee.get(0).beans() >= need_for_coffee.get(0).beans();
            if (hasEnoughResources) {
                System.out.println("Yes, I can make that amount of coffee" );
            } else {
                // Рахуємо, на скільки порцій вистачить кожного інгредієнта окремо
                int cupsByWater = in_machine_coffee.get(0).water() / coffee_type.get(0).water();
                int cupsByMilk = in_machine_coffee.get(0).milk() / coffee_type.get(0).milk();
                int cupsByBeans = in_machine_coffee.get(0).beans() / coffee_type.get(0).beans();
              // Мінімальне з цих трьох чисел і є максимальною кількістю порцій, що можна приготувати
                int maxCups = Math.min(cupsByWater, Math.min(cupsByMilk, cupsByBeans));
                System.out.println("Yes, I can make that amount of coffee (and even " + maxCups+ " more than that)"); // Виведе: 2
                   }

        }
    }
