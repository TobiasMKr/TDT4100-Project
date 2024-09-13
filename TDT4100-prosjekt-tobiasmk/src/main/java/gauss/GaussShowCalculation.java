package gauss;

import java.util.ArrayList;

public class GaussShowCalculation {
    ArrayList<ArrayList<Double>> matrix;
    ArrayList<Double> vector;

    public GaussShowCalculation(ArrayList<ArrayList<Double>> matrix, ArrayList<Double> vector){
        this.matrix = matrix;
        this.vector = vector;
    }

    public String printFinale(){
        String finale = "";
        for (int i = 0; i<this.matrix.size(); i++){
            for(int j = 0; j<this.matrix.get(i).size(); j++){
                if(this.matrix.get(i).get(j) != 0){
                    finale += "x" + ((int)j+1) + " "; 
                }
            }
            Object[] rowNotZero = this.matrix.get(i).stream().filter(c -> c != 0).toArray();
            if (rowNotZero.length == 0 && this.vector.get(i) != 0){
                finale = "No answer";
                return finale;
            }
            finale += "= " + this.vector.get(i) + "\n";
        }
        return finale;
    }
}
