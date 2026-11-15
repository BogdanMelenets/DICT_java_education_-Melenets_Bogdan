import java.util.Scanner; // 1. Імпортуємо клас Scanner
import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in); // 2. Создаем объект Scanner
    record coffee(String name, int water, int milk, int beans, int cost, int cups) {};
    static List<coffee> coffee_type = new ArrayList<>();
    static List<coffee> need_for_coffee = new ArrayList<>();
    static List<coffee> in_machine_coffee = new ArrayList<>();


    public static void main(String[] args) {

        in_machine_coffee.add(new coffee("00", 400, 540, 120, 550, 9));
        menu();
    }

    public static void print_coffee (coffee cof){
        System.out.println(cof.water +" ml of water");
        System.out.println(cof.milk +" ml of milk");
        System.out.println(cof.beans +" g of coffee beans");
        System.out.println(cof.cups +" of disposable cups");
        System.out.println(cof.cost +" of money"); }

    public static void zmin_machine ( String type){
        if (type.contains("buy")) {
        //змінюємо після видачі кави
        in_machine_coffee.add(new coffee("00",
                in_machine_coffee.get(0).water() - need_for_coffee.get(0).water(),
                in_machine_coffee.get(0).milk() - need_for_coffee.get(0).milk(),
                in_machine_coffee.get(0).beans() - need_for_coffee.get(0).beans(),
                in_machine_coffee.get(0).cost() + need_for_coffee.get(0).cost(),
                in_machine_coffee.get(0).cups() - need_for_coffee.get(0).cups()
                            ));
            in_machine_coffee.remove(0);
            System.out.println("The coffee machine after buying coffee has:");
            print_coffee(in_machine_coffee.get(0));
        }

        if (type.contains("fill")) {
            System.out.println("Write how many ml of water you want to add:");
            System.out.print(">");
            int water = scanner.nextInt();
            System.out.println("Write how many ml of milk you want to add:");
            System.out.print(">");
            int milk = scanner.nextInt();
            System.out.println("Write how many grams of coffee beans you want to add:");
            System.out.print(">");
            int beans = scanner.nextInt();
            System.out.println("Write how many disposable coffee cups you want to add:");
            System.out.print(">");
            int cups = scanner.nextInt();
            //змінюємо після додавання
            in_machine_coffee.add(new coffee("00",
                    in_machine_coffee.get(0).water() + water,
                    in_machine_coffee.get(0).milk() + milk,
                    in_machine_coffee.get(0).beans() + beans,
                    in_machine_coffee.get(0).cost(),
                    in_machine_coffee.get(0).cups() + cups
            ));

             in_machine_coffee.remove(0);
            System.out.println("The coffee machine after filling has:");
            print_coffee(in_machine_coffee.get(0));
        }
        if (type.contains("take")) {
            System.out.println("I gave you " + in_machine_coffee.get(0).cost());

            //змінюємо після додавання
            in_machine_coffee.add(new coffee("00",
                    in_machine_coffee.get(0).water(),
                    in_machine_coffee.get(0).milk(),
                    in_machine_coffee.get(0).beans(),
                    0,
                    in_machine_coffee.get(0).cups()
            ));
            in_machine_coffee.remove(0);
            System.out.println("The coffee machine after taking has:");
            print_coffee(in_machine_coffee.get(0));
        }}

    public static void menu() {

        String menu = "";
        System.out.println("The coffee machine has:");
        print_coffee(in_machine_coffee.get(0));
        do {
            System.out.println("Write action (buy, fill, take):");
            menu = scanner.nextLine();
            if (menu.contains("buy")) {
                buy();
            }
            if (menu.contains("fill")) {
                zmin_machine("fill");
            }
            if (menu.contains("take")) {
                zmin_machine("take");
            }
        } while (!menu.contains("exit"));
    }



    static void buy () {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
            System.out.print(">");
            int menu_coffee=scanner.nextInt();
            if (!coffee_type.isEmpty()) {coffee_type.remove(0);}
            if (menu_coffee==1) {coffee_type.add(new coffee("espresso", 250, 0, 16, 4, 1));}
            if (menu_coffee==2) {coffee_type.add(new coffee("latte", 350, 75, 20, 7, 1));}
            if (menu_coffee==3) {coffee_type.add(new coffee("cappuccino", 200, 100, 12, 6, 1));}

            System.out.println("Write how many cups of coffee you will need:");
            System.out.print(">");
            int count = scanner.nextInt();
            if (need_for_coffee.isEmpty()) { need_for_coffee.add(new coffee(coffee_type.get(0).name, coffee_type.get(0).water * count, coffee_type.get(0).milk * count, coffee_type.get(0).beans * count, coffee_type.get(0).cost*count, count));}
            else { need_for_coffee.remove(0);
             need_for_coffee.add(new coffee(coffee_type.get(0).name, coffee_type.get(0).water * count, coffee_type.get(0).milk * count, coffee_type.get(0).beans * count, coffee_type.get(0).cost*count, count));}

            // Порівнюємо поля двох різних структур
            boolean hasEnoughResources = in_machine_coffee.get(0).water() >= need_for_coffee.get(0).water() &&
                    in_machine_coffee.get(0).milk() >= need_for_coffee.get(0).milk() &&
                    in_machine_coffee.get(0).beans() >= need_for_coffee.get(0).beans() &&
                    in_machine_coffee.get(0).cups() >= need_for_coffee.get(0).cups();
            if (hasEnoughResources) {
                System.out.println("Yes, I can make that amount of coffee");
                zmin_machine("buy");
            } else {
                // Рахуємо, на скільки порцій вистачить кожного інгредієнта окремо
                int cupsByWater = in_machine_coffee.get(0).water() / coffee_type.get(0).water();
                int cupsByMilk = in_machine_coffee.get(0).milk() / coffee_type.get(0).milk();
                int cupsByBeans = in_machine_coffee.get(0).beans() / coffee_type.get(0).beans();
                int cupsByCups = in_machine_coffee.get(0).cups() / coffee_type.get(0).cups();
                int maxCups = Math.min(Math.min(cupsByWater,cupsByCups), Math.min(cupsByMilk, cupsByBeans));
                // Мінімальне з цих трьох чисел і є максимальною кількістю порцій, що можна приготувати
                System.out.println("Yes, I can make that amount of coffee (and even " + maxCups + " more than that)"); // Виведе: 2
            }
        System.out.println("The coffee machine has:");
        print_coffee(in_machine_coffee.get(0));
        }
        }
