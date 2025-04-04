import View.UserPannel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserPannel userPannel = new UserPannel();

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();

            userPannel.handleCommand(input);
        }
    }
}