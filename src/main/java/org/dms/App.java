package org.dms;

import org.dms.configs.Injector;
import org.dms.configs.Seeder;
import org.dms.views.Main;
import org.dms.views.student.RoomScreen;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Injector.startApplication(App.class);

        // Inject the seeder and run it
        Seeder seeder = Injector.getService(Seeder.class);
        seeder.run();

        Main main = new Main();
       // main.executeView();
        Scanner scanner = new Scanner(System.in);
        RoomScreen roomScreen = new RoomScreen(scanner);
        roomScreen.showRoomScreenOptions();
    }
}
