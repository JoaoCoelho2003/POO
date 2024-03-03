import java.util.*;


public class Menu {
    public interface Handler {
        public void execute() throws VintageException;
    }

    public interface PreCondition {
        public boolean condition();
    }

    private List<Handler> handlers;
    private List<PreCondition> preConditions;
    private List<String> options;

    public Menu(String[] options) {
        this.options = Arrays.asList(options);
        this.handlers = new ArrayList<>();
        this.preConditions = new ArrayList<>();
        this.options.forEach(s -> {
            this.preConditions.add(() -> true);
            this.handlers.add(() -> System.out.println("\nATENÇÃO: Opção não implementada!"));
        });
    }

    public void run() {
        int choice;
        do {
            showMenu();
            choice = readChoice();
            if (choice > 0 && !this.preConditions.get(choice - 1).condition()) {
                System.out.println("Opção indisponível! Tente novamente.");
            } else if (choice > 0) {
                try {
                    this.handlers.get(choice - 1).execute();
                } catch (VintageException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (choice != 0);
    }

    public void setHandler(int i, Handler h) {
        this.handlers.set(i - 1, h);
    }

    public void setPreCondition(int i, PreCondition b) {
        this.preConditions.set(i - 1, b);
    }

    public void showMenu() {
        System.out.println("-----MENU-----");
        for (int i = 0; i < this.options.size(); i++) {
            System.out.println(i + 1 + " - " + this.options.get(i));
        }
        System.out.println("0 - EXIT");
    }

    public int readChoice() {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.print("Choice: ");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            choice = -1;
        }
        if (choice < 0 || choice > this.options.size()) {
            choice = -1;
            System.out.println("INVALID CHOICE!");
        }
        return choice;
    }
}
