package Reader;
import java.util.Scanner;

/**
 * Класс считывает данные, введенные пользователем с консоли.
 */
public class LineReader {
    Scanner scanner;

    public LineReader(Scanner scanner){
        this.scanner = scanner;
    }
    public LineReader(){
        this.scanner = new Scanner(System.in);
    }


    public String getLine(){
        return scanner.nextLine();
    }

    public void printEscortMessage(String message){
        System.out.print(message);
    }

    public void printErrorMessage(String message){
        System.err.print(message);
    }
}
