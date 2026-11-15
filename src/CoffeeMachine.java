import java.util.Scanner; // 1. Імпортуємо клас Scanner
import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in); // 2. Создаем объект Scanner
            record coffee (String name, int water, int milk, int beans) {}
            List<coffee> coffee_type = new ArrayList<>();
            List<coffee> need_for_coffee = new ArrayList<>();
            coffee_type.add(new coffee("Black", 200, 50, 15));
            System.out.println("Write how many cups of coffee you will need:");
            System.out.println(">");
            int count = scanner.nextInt();
            need_for_coffee.add(new coffee(coffee_type.get(0).name, coffee_type.get(0).water*count, coffee_type.get(0).milk*count, coffee_type.get(0).beans*count));
            System.out.println("For "+count+" cups of coffee you will need:");
            System.out.println(need_for_coffee.get(0).water + " ml of water");
            System.out.println(need_for_coffee.get(0).milk + " ml of milk");
            System.out.println(need_for_coffee.get(0).beans + " g of coffee beans");


        }
    }
