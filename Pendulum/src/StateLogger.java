import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class StateLogger {
    private final SingleBasePivotPendulumSystem physicalSystem;
    private final Path filePath;

    private static void writeStructureToFileRecur(Path filePath, Pivot p) throws IOException {
        /*
         * structure is a bunch of integers saying the number of of children for every
         * pivot in dfs order (i could also add other final variables like length and
         * mass, but for now pretend that length and mass are both one when loading)
         */
        Files.write(filePath, new byte[] { (byte) p.getNumChildrenRods() }, StandardOpenOption.APPEND);
        System.out.println("Printed: " + p.getNumChildrenRods());

        for (Rod bottomRod : p.getBottomRods()) {
            writeStructureToFileRecur(filePath, bottomRod.getBotPivot());
        }
    }

    private void init() {
        try {
            if (Files.exists(filePath)) {
                // TODO: add a confirmation system
                // empty the current contents of the file
                Files.write(filePath, new byte[] {});
            } else {
                Files.createFile(filePath);
            }
            writeStructureToFileRecur(filePath, physicalSystem.basePivot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StateLogger(SingleBasePivotPendulumSystem physicalSystem, String path) {
        this.physicalSystem = physicalSystem;
        this.filePath = Paths.get(path);
        init();
    }

    public void printCurrLog() {
        try {
            for (byte b : Files.readAllBytes(filePath)) {
                System.out.println((int) b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}