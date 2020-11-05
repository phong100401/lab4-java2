package JavaBook;

import java.util.Scanner;

public class TestBook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book m1 = new Book();
        m1.displayMenu();
        int choice;
        do {
            System.out.print("Choose your action (15 to return to menu): ");
            choice = Integer.parseInt(sc.nextLine());
            while ((choice > 16) || (choice < 1)) {
                System.out.println("You chose a wrong action, please choose again");
                choice = Integer.parseInt(sc.nextLine());
            }
            switch (choice) {
                case 1:
                    m1.menu1();
                    break;
                case 2:
                    m1.menu2();
                    break;
                case 3:
                    m1.menu3();
                    break;
                case 4:
                    m1.menu4();
                    break;
                case 5:
                    m1.menu5();
                    break;
                case 6:
                    m1.menu6();
                    break;
                case 7:
                    m1.menu7();
                    break;
                case 8:
                    m1.menu8();
                    break;
                case 9:
                    m1.menu9();
                    break;
                case 10:
                    m1.menu10();
                    break;
                case 11:
                    m1.menu11();
                    break;
                case 12:
                    m1.menu12();
                    break;
                case 13:
                    m1.menu13();
                    break;
                case 14:
                    m1.menu14();
                    break;
                case 15:
                    m1.displayMenu();
                    break;
                default:
                    break;
            }
            System.out.println();
        } while (choice != 16);
    }
}
