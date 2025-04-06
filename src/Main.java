import Model.AccountPck.Admin;
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

/*
Signup - TechGuru88 - P@ssw0rd - Alex Johnson - AlexJohnson@example.com - 09121234567 - https://examplepfp2.com/alexPfp.jpg
FavouriteCategories - History, Society
Signup - GamerDudeX - Mike444! - Mike Anderson - mike.anderson@example.com - 09234567890 - https://pictureExample.com/pfptemp.png
FavouriteCategories - Game, Music
Signup - FitnessFreak101 - Fitness@123 - Jake - Robinson - JakeRobinson@example.com - 09095553333 - https://example.com/fitnessPhoto.png
FavouriteCategories - Music, Society, Live
Signup - MovieBuffCentral - movRachel! - Rachel - Davis - RachelDavis@example.com - 09032112322 - https://profilePic2.com/picForRachel.jpg
FavouriteCategories - News, Game, History, Podcast
Signup - MusicLover123 - mySecurePassword!456 - Emily - Smith - emily.smith@example.com - 09021221212 - https://example.com/emilyprofilepic.jpg
FavouriteCategories - Music
 */