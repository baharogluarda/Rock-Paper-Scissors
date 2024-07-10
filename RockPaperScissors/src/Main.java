import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int difficulty = getDifficulty(scanner);

        Game game = new Game(difficulty);
        game.play();
    }

    private static int getDifficulty(Scanner scanner) {
        int difficulty = -1;

        while (difficulty < 1 || difficulty > 3) {
            System.out.println("Zorluk seviyesini seçin (1: Kolay, 2: Orta, 3: Zor): ");

            if (scanner.hasNextInt()) {
                difficulty = scanner.nextInt();

                if (difficulty < 1 || difficulty > 3) {
                    System.out.println("Geçersiz seçim, lütfen 1, 2 veya 3 giriniz.");
                }

            } else {
                System.out.println("Geçersiz giriş, lütfen tek bir rakam giriniz.");
                scanner.next();
            }
        }
        return difficulty;
    }

}
