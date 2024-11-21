import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SystemControl2 {

    // Metoda pentru a salva mesajul într-un fișier text
    private static void logMessage(String message) {
        try {
            String userHome = System.getProperty("user.home");
            Path desktopPath = Paths.get(userHome, "Desktop", "system_log.txt");
            String logEntry = message + System.lineSeparator();

            Files.write(desktopPath, logEntry.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Mesajul a fost salvat pe Desktop.");
        } catch (IOException e) {
            System.out.println("Eroare la salvarea mesajului: " + e.getMessage());
        }
    }

    // Metoda pentru a reporni sistemul
    private static void restartSystem(int delay) {
        String os = System.getProperty("os.name").toLowerCase();
        logMessage("Repornire calculator, laboratorul 5");
        try {
            if (os.contains("win")) {
                Runtime.getRuntime().exec("shutdown -r -t " + delay);
            } else if (os.contains("linux")) {
                Runtime.getRuntime().exec("reboot");
            } else {
                System.out.println("Sistemul de operare nu este suportat.");
            }
        } catch (IOException e) {
            System.out.println("Eroare la repornirea sistemului: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int delay = 0;

        // Dacă există un al doilea argument, setează timpul de întârziere
        if (args.length > 1) {
            try {
                delay = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Timpul de întârziere nu este valid, se va folosi valoarea implicită de 30 secunde.");
            }
        }

        String action = args[0];
        if (action.equalsIgnoreCase("restart")) {
            restartSystem(delay);
        } else {
            System.out.println("Acțiune necunoscută. Utilizați 'restart'.");
        }
    }
}
