import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private final int difficulty;
    private final Random random;

    public Game(int difficulty) {
        this.difficulty = difficulty;
        this.random = new Random();
    }

    public void play(){
        boolean keepPlaying = true;
        while (keepPlaying){
            int uChoice = getUserChoice();

            int cChoice = getComputerChoice();
            System.out.println("Bilgisayarın seçimi: " + getCompsChoice(cChoice));

            int result = determineWinner(uChoice, cChoice);
            displayResult(result);

            System.out.println();
            keepPlaying = isContinue();

        }
        System.out.println("Oyundan cikis yapiliyor...");
    }

    private boolean isContinue(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Tekrar oynamak ister misiniz? Evet(E) / Hayır(H) : ");
        String choice = scanner.next().toUpperCase();
        return switch (choice) {
            case "E" -> true;
            case "H" -> false;
            default -> {
                System.out.println("Gecersiz secim. Lutfen tekrar deneyiniz.");
                yield isContinue();
            }
        };
    }

    private void displayResult(int result) {
        switch (result) {
            case 0:
                System.out.println("Berabere!");
                break;
            case 1:
                System.out.println("Kazandınız!");
                break;
            case -1:
                System.out.println("Kaybettiniz!");
                break;
        }
    }

    private int getUserChoice() {
        int userChoice = -1;
        Scanner scanner = new Scanner(System.in);

        while (userChoice < 0 || userChoice > 2) {
            System.out.print("Taş, Kağıt veya Makas seçin (taş=0, kağıt=1, makas=2): ");

            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();

                if (userChoice < 0 || userChoice > 2) {
                    System.out.println("Geçersiz seçim, lütfen 0, 1 veya 2 giriniz.");
                }

            } else {
                System.out.println("Geçersiz giriş, lütfen tek bir rakam giriniz.");
                scanner.next(); // Geçersiz girdiyi temizle
            }

        }
        return userChoice;
    }

    public int getComputerChoice(){
        switch (difficulty){
            case 1:
                return random.nextInt(3);
            case 2:
                return (int) (Math.random() * 3);
            case  3:
                int bias = random.nextBoolean() ? 1 : -1;
                return Math.abs((random.nextInt(3) + bias) % 3);
            default:
                return random.nextInt(3);
        }
    }

    private int determineWinner(int userChoice, int computerChoice){
        boolean isUserWin =
                userChoice == 0 && computerChoice == 2 ||
                userChoice == 1 && computerChoice == 0 ||
                userChoice == 2 && computerChoice == 1;

        if (userChoice == computerChoice){
            return 0;
        } else if(isUserWin){
            return 1;
        } else {
            return -1;
        }

    }

    private String getCompsChoice(int choise){
        switch (choise){
            case 0:
                return "Taş";
            case 1:
                return "Kağıt";
            case 2:
                return "Makas";
            default:
                return "";
        }
    }
}
