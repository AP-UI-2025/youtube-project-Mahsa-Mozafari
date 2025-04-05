import View.CommandPannel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandPannel commandPannel = new CommandPannel();

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();

            commandPannel.handleCommand(input);
        }
    }
}