import Model.AccountPck.Admin;
import View.CommandPannel;

import java.util.Scanner;

public class Main {
    Admin admin=Admin.getInstance();

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
Signup - TechGuru88 - P@ssw0rd - Alex Johnson - AlexJohnson@example.com - 09121234567 - https://examplepfp2.com/alexPfp.jpg
Signup - GamerDudeX - Mike444! - Mike Anderson - mike.anderson@example.com - 09234567890 - https://pictureExample.com/pfptemp.png
FavouriteCategories - Game, Music
Signup - FitnessFreak101 - Fitness@123 - Jake - Robinson - JakeRobinson@example.com - 09095553333 - https://example.com/fitnessPhoto.png
FavouriteCategories - Music, Society, Live
Signup - MovieBuffCentral - movRachel! - Rachel - Davis - RachelDavis@example.com - 09032112322 - https://profilePic2.com/picForRachel.jpg
FavouriteCategories - News, Game, History, Podcast
Signup - MusicLover123 - mySecurePassword!456 - Emily Smith - emily.smith@example.com - 09021221212 - https://example.com/emilyprofilepic.jpg
FavouriteCategories - Music
Report - 1 - Inappropriate content
ManageReport - C - 1
Unban - 1

Login - GamerDudeX - Mike444!
AccountInfo
CreateChannel - Gaming with Mike - I’m Mike, your guide to the exciting world of gaming. - cover
Publish - NV - N - Epic Adventure in Minecraft - Join me, Mike, as we explore the vast and beautiful world of Minecraft - 60 - Game - YouTube.com/GamingWithMike/EpicAdventure - Link to Cover Image - English - 1080 - MP4
Publish - SV - N - Quick Tips for Winning in "Fortnite" - In this short video, I share my top 5 quick tips to help you secure more wins in "Fortnite"!  - 20 - Game - YouTube.com/GamingWithMike/FortniteTips - Link to Cover Image - English - Epic Gaming Background Music
CreatePlaylist - C - Gaming Tips
AddToPlaylist - 1 - 1
AddToPlaylist - 1 - 2
AddToPlaylist - 1 - 1
ViewChannel
Logout
 */