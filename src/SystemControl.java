import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SystemControl {

    // Metoda pentru a salva mesajul într-un fișier text
    private static void logMessage(String message) {
        try {
            String userHome = System.getProperty("user.home");
            Path desktopPath = Paths.get(userHome, "Desktop", "system_log.txt");


            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = now.format(formatter);


            String logEntry = "[" + timestamp + "] " + message + System.lineSeparator();

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
            } else {
                System.out.println("Sistemul de operare nu este suportat.");
            }
        } catch (IOException e) {
            System.out.println("Eroare la repornirea sistemului: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int delay = 30; // Valoare implicită de întârziere

        // Dacă există un al doilea argument, setează timpul de întârziere
        if (args.length > 1) {
            try {
                delay = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Timpul de întârziere nu este valid, se va folosi valoarea implicită de 30 secunde.");
            }
        }

        if (args.length > 0) {
            String action = args[0];
            if (action.equalsIgnoreCase("restart")) {
                restartSystem(delay);
            } else {
                System.out.println("Acțiune necunoscută. Utilizați 'restart'.");
            }
        } else {
            System.out.println("Vă rugăm să specificați o acțiune. Utilizați 'restart'.");
        }
    }
}
