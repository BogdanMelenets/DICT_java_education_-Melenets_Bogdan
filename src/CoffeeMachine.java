import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeMachine {

    // Можливі стани кавомашини
    enum State {
        CHOOSING_ACTION,    // Очікування головної команди (buy, fill, take, remaining, exit)
        CHOOSING_COFFEE,    // Очікування вибору кави (1, 2, 3, back)
        CHOOSING_CUPS,      // Очікування кількості порцій кави
        FILLING_WATER,      // Очікування кількості води
        FILLING_MILK,       // Очікування кількості молока
        FILLING_BEANS,      // Очікування кількості зерен
        FILLING_CUPS,       // Очікування кількості стаканчиків
        EXIT                // Завершення роботи
    }

    record coffee(String name, int water, int milk, int beans, int cost, int cups) {}

    // Поля класу (стан машини, списки та тимчасові змінні для поповнення)
    private State currentState;
    private List<coffee> coffee_type = new ArrayList<>();
    private List<coffee> need_for_coffee = new ArrayList<>();
    private List<coffee> in_machine_coffee = new ArrayList<>();

    private int fillWater = 0;
    private int fillMilk = 0;
    private int fillBeans = 0;

    // Конструктор ініціалізує початкові запаси та запускає головне меню
    public CoffeeMachine() {
        in_machine_coffee.add(new coffee("00", 400, 540, 120, 550, 9));
        System.out.println("The coffee machine has:");
        print_coffee(in_machine_coffee.get(0));
        setMainMenu();
    }

    public boolean isWorking() {
        return currentState != State.EXIT;
    }

    // ЄДИНИЙ МЕТОД, ЯКИЙ ПРИЙМАЄ РЯДОК З КОНСОЛІ ТА ОБРОБЛЯЄ ЙОГО ЗАЛЕЖНО ВІД СТАНУ
    public void processInput(String input) {
        switch (currentState) {
            case CHOOSING_ACTION -> handleAction(input);
            case CHOOSING_COFFEE -> handleCoffeeSelection(input);
            case CHOOSING_CUPS -> handleCupsCount(input);
            case FILLING_WATER -> {
                fillWater = Integer.parseInt(input.trim());
                currentState = State.FILLING_MILK;
                System.out.println("Write how many ml of milk you want to add:");
                System.out.print(">");
            }
            case FILLING_MILK -> {
                fillMilk = Integer.parseInt(input.trim());
                currentState = State.FILLING_BEANS;
                System.out.println("Write how many grams of coffee beans you want to add:");
                System.out.print(">");
            }
            case FILLING_BEANS -> {
                fillBeans = Integer.parseInt(input.trim());
                currentState = State.FILLING_CUPS;
                System.out.println("Write how many disposable coffee cups you want to add:");
                System.out.print(">");
            }
            case FILLING_CUPS -> {
                int fillCups = Integer.parseInt(input.trim());
                zmin_machine_fill(fillWater, fillMilk, fillBeans, fillCups);
                setMainMenu();
            }
            case EXIT -> {}
        }
    }

    // Обробка головних команд меню
    private void handleAction(String menu) {
        if (menu.contains("buy")) {
            currentState = State.CHOOSING_COFFEE;
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back:");
            System.out.print(">");
        } else if (menu.contains("fill")) {
            currentState = State.FILLING_WATER;
            System.out.println("Write how many ml of water you want to add:");
            System.out.print(">");
        } else if (menu.contains("take")) {
            zmin_machine("take");
            setMainMenu();
        } else if (menu.contains("remaining")) {
            System.out.println("The coffee machine has:");
            print_coffee(in_machine_coffee.get(0));
            setMainMenu();
        } else if (menu.contains("exit")) {
            currentState = State.EXIT;
        } else {
            setMainMenu();
        }
    }

    // Крок 1 купівлі: вибір виду кави
    private void handleCoffeeSelection(String menu_coffee1) {
        if (menu_coffee1.contains("back")) {
            setMainMenu();
            return;
        }

        int menu_coffee = Integer.parseInt(menu_coffee1.trim());
        if (!coffee_type.isEmpty()) {
            coffee_type.remove(0);
        }
        if (menu_coffee == 1) {
            coffee_type.add(new coffee("espresso", 250, 0, 16, 4, 1));
        } else if (menu_coffee == 2) {
            coffee_type.add(new coffee("latte", 350, 75, 20, 7, 1));
        } else if (menu_coffee == 3) {
            coffee_type.add(new coffee("cappuccino", 200, 100, 12, 6, 1));
        }

        // Переходимо в стан очікування кількості порцій
        currentState = State.CHOOSING_CUPS;
        System.out.println("Write how many cups of coffee you will need:");
        System.out.print(">");
    }

    // Крок 2 купівлі: введення кількості порцій та перевірка ресурсів
    private void handleCupsCount(String countInput) {
        int count = Integer.parseInt(countInput.trim());

        if (!need_for_coffee.isEmpty()) {
            need_for_coffee.remove(0);
        }
        need_for_coffee.add(new coffee(
                coffee_type.get(0).name,
                coffee_type.get(0).water * count,
                coffee_type.get(0).milk * count,
                coffee_type.get(0).beans * count,
                coffee_type.get(0).cost * count,
                count
        ));

        boolean hasEnoughResources = in_machine_coffee.get(0).water() >= need_for_coffee.get(0).water() &&
                in_machine_coffee.get(0).milk() >= need_for_coffee.get(0).milk() &&
                in_machine_coffee.get(0).beans() >= need_for_coffee.get(0).beans() &&
                in_machine_coffee.get(0).cups() >= need_for_coffee.get(0).cups();

        if (hasEnoughResources) {
            System.out.println("Yes, I can make that amount of coffee");
            zmin_machine("buy");
        } else {
            // Захист від ділення на 0 (для еспресо milk == 0)
            int cupsByWater = coffee_type.get(0).water() > 0 ? in_machine_coffee.get(0).water() / coffee_type.get(0).water() : Integer.MAX_VALUE;
            int cupsByMilk = coffee_type.get(0).milk() > 0 ? in_machine_coffee.get(0).milk() / coffee_type.get(0).milk() : Integer.MAX_VALUE;
            int cupsByBeans = coffee_type.get(0).beans() > 0 ? in_machine_coffee.get(0).beans() / coffee_type.get(0).beans() : Integer.MAX_VALUE;
            int cupsByCups = in_machine_coffee.get(0).cups() / coffee_type.get(0).cups();

            int maxCups = Math.min(Math.min(cupsByWater, cupsByCups), Math.min(cupsByMilk, cupsByBeans));
            System.out.println("No, I can make only " + maxCups + " cup(s) of coffee");
        }

        setMainMenu();
    }

    public void print_coffee(coffee cof) {
        System.out.println(cof.water + " ml of water");
        System.out.println(cof.milk + " ml of milk");
        System.out.println(cof.beans + " g of coffee beans");
        System.out.println(cof.cups + " of disposable cups");
        System.out.println(cof.cost + " of money");
    }

    public void zmin_machine(String type) {
        if (type.contains("buy")) {
            in_machine_coffee.set(0, new coffee("00",
                    in_machine_coffee.get(0).water() - need_for_coffee.get(0).water(),
                    in_machine_coffee.get(0).milk() - need_for_coffee.get(0).milk(),
                    in_machine_coffee.get(0).beans() - need_for_coffee.get(0).beans(),
                    in_machine_coffee.get(0).cost() + need_for_coffee.get(0).cost(),
                    in_machine_coffee.get(0).cups() - need_for_coffee.get(0).cups()
            ));
            System.out.println("The coffee machine after buying coffee has:");
            print_coffee(in_machine_coffee.get(0));
        }

        if (type.contains("take")) {
            System.out.println("I gave you " + in_machine_coffee.get(0).cost());
            in_machine_coffee.set(0, new coffee("00",
                    in_machine_coffee.get(0).water(),
                    in_machine_coffee.get(0).milk(),
                    in_machine_coffee.get(0).beans(),
                    0,
                    in_machine_coffee.get(0).cups()
            ));
            System.out.println("The coffee machine after taking has:");
            print_coffee(in_machine_coffee.get(0));
        }
    }

    private void zmin_machine_fill(int water, int milk, int beans, int cups) {
        in_machine_coffee.set(0, new coffee("00",
                in_machine_coffee.get(0).water() + water,
                in_machine_coffee.get(0).milk() + milk,
                in_machine_coffee.get(0).beans() + beans,
                in_machine_coffee.get(0).cost(),
                in_machine_coffee.get(0).cups() + cups
        ));
        System.out.println("The coffee machine after filling has:");
        print_coffee(in_machine_coffee.get(0));
    }

    private void setMainMenu() {
        currentState = State.CHOOSING_ACTION;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    // Точка входу: Scanner передає рядки в об'єкт machine
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine();

        while (machine.isWorking()) {
            String userInput = scanner.nextLine();
            machine.processInput(userInput);
        }

        scanner.close();
    }
}

