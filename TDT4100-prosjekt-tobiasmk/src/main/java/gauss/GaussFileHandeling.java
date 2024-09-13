package gauss;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class GaussFileHandeling {
    private ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
    
    private ArrayList<Double> vector = new ArrayList<>();
    
    
    private File file = new File("/Users/tobiaskroll/Documents/skole/2.semester/Objekt/TDT4100-prosjekt-tobiasmk/src/main/java/gauss/gaussFile.txt");

    
    public ArrayList<Double> getVector() {
        return this.vector;
    }
    
    public ArrayList<ArrayList<Double>> getMatrix() {
        return this.matrix;
    }

    public void importFIle(){
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                ArrayList<Double> row = new ArrayList<>();
                String txt = sc.nextLine();
                String[] txtSplit = txt.split(" ");
                for (int i = 0; i<txtSplit.length-1; i++){
                    row.add(Double.parseDouble(txtSplit[i]));
                }
                this.vector.add(Double.parseDouble(txtSplit[txtSplit.length-1]));
                this.matrix.addFirst(row);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Feil i filen:\n" + e);
        }
    }

    public void saveFile(ArrayList<ArrayList<Double>> matrix, ArrayList<Double> vector){
        this.matrix = matrix;
        this.vector = vector;
        String txt = "";
        for(int i = this.matrix.size()-1; i>=0; i--){
            for(int j = 0; j<this.matrix.get(i).size(); j++){
                txt += this.matrix.get(i).get(j) + " ";
            }
            txt += this.vector.get(i) + "\n";
        }
        try {
            Files.writeString(Path.of("/Users/tobiaskroll/Documents/skole/2.semester/Objekt/TDT4100-prosjekt-tobiasmk/src/main/java/gauss/gaussFile.txt"), txt);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        GaussFileHandeling a = new GaussFileHandeling();
        a.importFIle();
        System.out.println(a.matrix);
        System.err.println(a.vector);
    }
}
