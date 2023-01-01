import java.io.File;
import java.io.PrintStream;

public class MainRunner {
    private static final int PRINT_LOG = 0, PRINT_TERMINAL = 1, PRINT_NOWHERE = 2;
    private static int printLocation = PRINT_TERMINAL;

    private static void configureSettings() throws Exception {
        switch (printLocation) {
            case (PRINT_LOG):
                System.setOut(new PrintStream(new File("src/log.txt")));
                break;
            case (PRINT_TERMINAL):
                System.setOut(System.out);
                break;
            case (PRINT_NOWHERE):
                System.setOut(new PrintStream(new File("/dev/null"))); // only for mac
                break;
            default:
                throw new Exception("invalid printLocation");
        }
    }

    public static void main(String[] args) throws Exception {
        configureSettings();
        (new Thread(new RealTimeSimulation(1, true, false,
                new MultipleDoublePendulums(9.81, 10, 2.3, 0.0001), -1))).start();
        // (new Thread(new RealTimeSimulation())).run();
    }
}