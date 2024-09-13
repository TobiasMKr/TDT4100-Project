package gauss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GaussCalculator2 implements GaussEliminationInterface{

    private ArrayList<ArrayList<Double>> matrise, workingMatrise;

    private int indent = 0;
    
    private ArrayList<Double> vector, workingVector;

    public ArrayList<ArrayList<Double>> getWorkingMatrise() {
        return workingMatrise;
    }

    public ArrayList<Double> getWorkingVector() {
        return workingVector;
    }

    public ArrayList<ArrayList<Double>> getMatrix(){
        return this.matrise;
    }

    public ArrayList<Double> getVector(){
        return vector;
    }


    public GaussCalculator2(ArrayList<ArrayList<Double>> matrise, ArrayList<Double> vector){
        this.matrise = new ArrayList<ArrayList<Double>>(matrise);
        this.vector = new ArrayList<Double>(vector);
        this.workingMatrise = new ArrayList<ArrayList<Double>>(matrise);
        this.workingVector = new ArrayList<Double>(vector);
    }

    @Override
    public void gaussElimination() {
        System.out.println(this.matrise);
        System.out.println(this.vector);
        for(int i = 0; i<this.matrise.size(); i++){
            this.gaussEliminationStep1();
            this.gaussEliminationStep2();
            this.gaussEliminationStep3();
        }
        this.workingMatrise = this.matrise;
        gaussEliminationStep4();
    }

    public void gaussEliminationStep1(){
            int indexRow = findRowColumn0(this.workingMatrise)[0];
            ArrayList<Double> firstRow = this.workingMatrise.get(indexRow);
            Double firstNumber = firstRow.get(findRowColumn0(this.workingMatrise)[1]);
            if (firstNumber != 0) {
                firstRow = (ArrayList<Double>)firstRow.stream().map(c -> c/firstNumber).collect(Collectors.toList());
                this.workingVector.addFirst(this.workingVector.get(indexRow)/firstNumber);
            }else{
                this.workingVector.addFirst(this.workingVector.get(indexRow));
            }
            this.workingMatrise.addFirst(firstRow);
            this.workingMatrise.remove(indexRow+1);
            this.workingVector.remove(indexRow+1);
    }

    public void gaussEliminationStep2(){
        int indexColumn = findRowColumn0(this.workingMatrise)[1];
        ArrayList<Double> firstRow = new ArrayList<>(this.workingMatrise.getFirst());
        double firstVector = this.workingVector.getFirst();
        this.workingVector.removeFirst();
        this.workingMatrise.removeFirst();
        for(int i = 0; i<this.workingMatrise.size(); i++){
            double multiplier = this.workingMatrise.get(i).get(indexColumn);
            ArrayList<Double> workingRow = new ArrayList<>(this.workingMatrise.get(i));
            for(int j = 0; j< workingRow.size(); j++){
                workingRow.set(j, workingRow.get(j)-firstRow.get(j)*multiplier);
            }
            this.workingMatrise.set(i, workingRow);
            this.workingVector.set(i, this.workingVector.get(i)-firstVector*multiplier);
        }
        this.workingMatrise.addFirst(firstRow);
        this.workingVector.addFirst(firstVector);
        }
    
    
    public void gaussEliminationStep3(){
        for(int i = indent; i<this.matrise.size(); i++){
            for(int j = indent; j< this.matrise.size(); j++){
                this.matrise.get(i).set(j, this.workingMatrise.get(i-indent).get(j-indent));
            }
            this.vector.set(i, this.workingVector.get(i-indent));
        }
        this.workingMatrise.removeFirst();
        this.workingVector.removeFirst();
        for (int i = 0; i< this.workingMatrise.size(); i++){
            this.workingMatrise.get(i).removeFirst();
        }
        indent++;
        System.out.println(this.matrise);
        System.err.println(this.vector);
    }

    public void gaussEliminationStep4(){
        for(int i = this.matrise.size()-1; i>0; i--){
            int indexNotZero = matrise.size();
            double multiplier = 0;
            for (int j = 0; j<this.matrise.get(i).size(); j++){
                if (this.matrise.get(i).get(j)!=0){
                    indexNotZero = j;
                    multiplier = this.matrise.get(i-1).get(j);
                }
            }
            if (indexNotZero == matrise.size()){
                break;
            }
            for (int j = 0; j<i; j++){
                this.matrise.get(j).set(indexNotZero, this.matrise.get(i).get(j) - this.matrise.get(i).get(j)* multiplier);
                this.vector.set(j, this.vector.get(j)-this.vector.get(j)*multiplier);
            }
        }
    }

    public int[] findRowColumn0(ArrayList<ArrayList<Double>> matrise){
        int firstRow = 0;
        int firstColumn = matrise.size();
        int activeRow = 0;
        int activeColumn = 0;
        for (ArrayList<Double> i : matrise){
            for (Double j : i){
                if (j != 0){
                    break;
                }
                activeColumn ++;
            }
            if (activeColumn < firstColumn){
                firstColumn = activeColumn;
                firstRow = activeRow;
            }
            activeColumn = 0;
            activeRow ++;
        }
        if (firstRow >= this.workingMatrise.size() || firstColumn >= this.workingMatrise.size()){
            return new int[]{0, 0};
        }
        return new int[]{firstRow, firstColumn};
    }


    public static void main(String[] args) {
        ArrayList<Double> row0 = new ArrayList<>(Arrays.asList(0.0,1.0,0.0));
        ArrayList<Double> row1 = new ArrayList<>(Arrays.asList(2.0,1.0,0.0));
        ArrayList<Double> row2 = new ArrayList<>(Arrays.asList(1.0,0.0,1.0));
        ArrayList<Double> vector = new ArrayList<>(Arrays.asList(1.0,2.0,4.0));

        ArrayList<ArrayList<Double>> matrise = new ArrayList<>(Arrays.asList(row0, row1, row2));
        GaussCalculator2 a = new GaussCalculator2(matrise, vector);
        a.gaussEliminationStep1();
        a.gaussElimination();
        System.out.println(a.getMatrix());
        System.out.println(a.getVector());

    }
    
}
