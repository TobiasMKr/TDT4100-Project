package gauss;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class GaussProjectController {

    @FXML
    private Button saveFile, importFile;

    @FXML
    private ArrayList<ArrayList<TextField>> matrix = new ArrayList<>();

    @FXML
    private ArrayList<TextField> vector = new ArrayList<>();

    @FXML
    private Label finale;

    @FXML
    private TextField r0c0, r0c1, r0c2, r1c0, r1c1, r1c2, r2c0, r2c1, r2c2, sum0, sum1, sum2;

    private double r0c0Value, r0c1Value, r0c2Value, r1c0Value, r1c1Value, r1c2Value, r2c0Value, r2c1Value, r2c2Value, sum0Value, sum1Value, sum2Value;

    private ArrayList<ArrayList<Double>> matrixFinale;
    private ArrayList<Double> vectorFinale;
    

    public void calculate(){
        updateMatrixAndVector();
        ArrayList<TextField> textRow0 = new ArrayList<>(Arrays.asList(r0c0, r0c1, r0c2));
        ArrayList<TextField> textRow1 = new ArrayList<>(Arrays.asList(r1c0, r1c1, r1c2));
        ArrayList<TextField> textRow2 = new ArrayList<>(Arrays.asList(r2c0, r2c1, r2c2));

        this.matrix.add(textRow0);
        this.matrix.add(textRow1);
        this.matrix.add(textRow2);

        this.vector.add(sum2);
        this.vector.add(sum1);
        this.vector.add(sum0);

        GaussCalculator2 calculation = new GaussCalculator2(this.matrixFinale, this.vectorFinale);
        calculation.gaussElimination();
        this.matrixFinale = new ArrayList<ArrayList<Double>>(calculation.getMatrix());
        this.vectorFinale = new ArrayList<Double>(calculation.getVector());
        System.out.println(this.vectorFinale);
        System.out.println(matrixFinale);
        finale.setText(new GaussShowCalculation(this.matrixFinale, this.vectorFinale).printFinale());

    }
    
    public void importFile(){
        updateMatrixAndVector();
        ArrayList<TextField> textRow0 = new ArrayList<>(Arrays.asList(r0c0, r0c1, r0c2));
        ArrayList<TextField> textRow1 = new ArrayList<>(Arrays.asList(r1c0, r1c1, r1c2));
        ArrayList<TextField> textRow2 = new ArrayList<>(Arrays.asList(r2c0, r2c1, r2c2));

        this.matrix.add(textRow2);
        this.matrix.add(textRow1);
        this.matrix.add(textRow0);

        this.vector.add(sum0);
        this.vector.add(sum1);
        this.vector.add(sum2);

        GaussFileHandeling a = new GaussFileHandeling();
        a.importFIle();
        this.matrixFinale = a.getMatrix();
        this.vectorFinale = a.getVector();
        for (int i = 0; i<matrixFinale.size(); i++){
            for(int j = 0; j<matrixFinale.size(); j++){
                this.matrix.get(i).get(j).setText(this.matrixFinale.get(i).get(j).toString());
            }
            this.vector.get(i).setText(this.vectorFinale.get(i).toString());
        }
    }


    public void saveFile(){
        updateMatrixAndVector();
        GaussFileHandeling a = new GaussFileHandeling();
        a.saveFile(this.matrixFinale, this.vectorFinale);
    }


    public void checkDoubleValue(KeyEvent event){
        TextField txt = (TextField)event.getSource();
        try {
            Double.parseDouble(txt.getText());
        } catch (Exception e) {
            txt.setText("");
        }
    }


    public void updateMatrixAndVector(){
        try {
            r0c0Value = Double.parseDouble(r0c0.getText());
            System.out.println("heihri");
        } catch (Exception e) {
            r0c0Value = Double.parseDouble(r0c0.getPromptText());
        }
        try {
            r0c1Value = Double.parseDouble(r0c1.getText());
        } catch (Exception e) {
            r0c1Value = Double.parseDouble(r0c1.getPromptText());
        }
        try {
            r0c2Value = Double.parseDouble(r0c2.getText());
        } catch (Exception e) {
            r0c2Value = Double.parseDouble(r0c2.getPromptText());
        }
        try {
            r1c0Value = Double.parseDouble(r1c0.getText());
        } catch (Exception e) {
            r1c0Value = Double.parseDouble(r1c0.getPromptText());
        }
        try {
            r1c1Value = Double.parseDouble(r1c1.getText());
        } catch (Exception e) {
            r1c1Value = Double.parseDouble(r1c1.getPromptText());
        }
        try {
            r1c2Value = Double.parseDouble(r1c2.getText());
        } catch (Exception e) {
            r1c2Value = Double.parseDouble(r1c2.getPromptText());
        }
        try {
            r2c0Value = Double.parseDouble(r2c0.getText());
        } catch (Exception e) {
            r2c0Value = Double.parseDouble(r2c0.getPromptText());
        }
        try {
            r2c1Value = Double.parseDouble(r2c1.getText());
        } catch (Exception e) {
            r2c1Value = Double.parseDouble(r2c1.getPromptText());
        }
        try {
            r2c2Value = Double.parseDouble(r2c2.getText());
        } catch (Exception e) {
            r2c2Value = Double.parseDouble(r2c2.getPromptText());
        }
        try {
            sum0Value = Double.parseDouble(sum0.getText());
        } catch (Exception e) {
            sum0Value = Double.parseDouble(sum0.getPromptText());
        }
        try {
            sum1Value = Double.parseDouble(sum1.getText());
        } catch (Exception e) {
            sum1Value = Double.parseDouble(sum1.getPromptText());
        }
        try {
            sum2Value = Double.parseDouble(sum2.getText());
        } catch (Exception e) {
            sum2Value = Double.parseDouble(sum2.getPromptText());
        }


        ArrayList<Double> row0 = new ArrayList<>(Arrays.asList(r0c0Value, r0c1Value, r0c2Value));
        ArrayList<Double> row1 = new ArrayList<>(Arrays.asList(r1c0Value, r1c1Value, r1c2Value));
        ArrayList<Double> row2 = new ArrayList<>(Arrays.asList(r2c0Value, r2c1Value, r2c2Value));
    
        this.matrixFinale = new ArrayList<>(Arrays.asList(row0, row1, row2));
    
        this.vectorFinale = new ArrayList<>(Arrays.asList(sum0Value, sum1Value, sum2Value));

    }


}
