package gauss;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class GaussFileHandelingTest {
    
    @Test
    public void testSaveFile() throws IOException{
        ArrayList<Double> currentRow0 = new ArrayList<>(Arrays.asList(1.0,0.5,0.0));
        ArrayList<Double> currentRow1 = new ArrayList<>(Arrays.asList(0.0,1.0,0.0));
        ArrayList<Double> currentRow2 = new ArrayList<>(Arrays.asList(0.0,-0.5,1.0));
        ArrayList<ArrayList<Double>> currentMatrise = new ArrayList<>(Arrays.asList(currentRow0, currentRow1, currentRow2));
        ArrayList<Double> currentVector = new ArrayList<>(Arrays.asList(1.0,1.0,3.0));
        GaussFileHandeling a = new GaussFileHandeling();
        a.saveFile(currentMatrise, currentVector);
        assertEquals(new String("0.0 -0.5 1.0 3.0\n0.0 1.0 0.0 1.0\n1.0 0.5 0.0 1.0\n"), Files.readString(Path.of("/Users/tobiaskroll/Documents/skole/2.semester/Objekt/TDT4100-prosjekt-tobiasmk/src/main/java/gauss/gaussFile.txt")));
    }
}
