package gauss;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GaussCalculator2Test {

    GaussCalculator2 a;
    ArrayList<ArrayList<Double>> matrise, workingMatrise;
    ArrayList<Double> vector, workingVector;


    @BeforeEach
    public void beforeEach(){
        ArrayList<Double> row0 = new ArrayList<>(Arrays.asList(0.0,1.0,0.0));
        ArrayList<Double> row1 = new ArrayList<>(Arrays.asList(2.0,1.0,0.0));
        ArrayList<Double> row2 = new ArrayList<>(Arrays.asList(1.0,0.0,1.0));

        this.matrise = new ArrayList<>(Arrays.asList(row0, row1, row2));
        this.workingMatrise = new ArrayList<>(Arrays.asList(row0, row1, row2));
        this.vector = new ArrayList<>(Arrays.asList(1.0,2.0,4.0));
        this.workingVector = new ArrayList<>(Arrays.asList(1.0,2.0,4.0));

        this.a = new GaussCalculator2(matrise, vector);
    }

    @Test
    public void testConstructor(){
        assertEquals(new GaussCalculator2(matrise, vector).getMatrix(), this.matrise);
        assertEquals(new GaussCalculator2(matrise, vector).getVector(), this.vector);
        assertEquals(new GaussCalculator2(matrise, vector).getVector(), this.workingVector);
        assertEquals(new GaussCalculator2(matrise, vector).getMatrix(), this.workingMatrise);
    }
    
    @Test
    public void testGaussEliminationStep1(){
        ArrayList<Double> currentRow0 = new ArrayList<>(Arrays.asList(1.0,0.5,0.0));
        ArrayList<Double> currentRow1 = new ArrayList<>(Arrays.asList(0.0,1.0,0.0));
        ArrayList<Double> currentRow2 = new ArrayList<>(Arrays.asList(1.0,0.0,1.0));
        ArrayList<ArrayList<Double>> currentMatrise = new ArrayList<>(Arrays.asList(currentRow0, currentRow1, currentRow2));
        ArrayList<Double> currentVector = new ArrayList<>(Arrays.asList(1.0,1.0,4.0));
        a.gaussEliminationStep1();
        assertEquals(currentMatrise, a.getWorkingMatrise());
        assertEquals(currentVector, a.getWorkingVector());
    }

    @Test
    public void testGaussEliminationStep2(){
        ArrayList<Double> currentRow0 = new ArrayList<>(Arrays.asList(1.0,0.5,0.0));
        ArrayList<Double> currentRow1 = new ArrayList<>(Arrays.asList(0.0,1.0,0.0));
        ArrayList<Double> currentRow2 = new ArrayList<>(Arrays.asList(0.0,-0.5,1.0));
        ArrayList<ArrayList<Double>> currentMatrise = new ArrayList<>(Arrays.asList(currentRow0, currentRow1, currentRow2));
        ArrayList<Double> currentVector = new ArrayList<>(Arrays.asList(1.0,1.0,3.0));

        a.gaussEliminationStep1();
        a.gaussEliminationStep2();

        assertEquals(currentMatrise, a.getWorkingMatrise());
        assertEquals(currentVector, a.getWorkingVector());
    }

    @Test
    public void testGaussEliminationStep3(){
        
        ArrayList<Double> currentRow1 = new ArrayList<>(Arrays.asList(1.0,0.0));
        ArrayList<Double> currentRow2 = new ArrayList<>(Arrays.asList(-0.5,1.0));
        ArrayList<ArrayList<Double>> currentMatrise = new ArrayList<>(Arrays.asList(currentRow1, currentRow2));
        ArrayList<Double> currentVector = new ArrayList<>(Arrays.asList(1.0,3.0));

        a.gaussEliminationStep1();
        a.gaussEliminationStep2();
        a.gaussEliminationStep3();

        assertEquals(currentMatrise, a.getWorkingMatrise());
        assertEquals(currentVector, a.getWorkingVector());

        ArrayList<Double> currentRow0 = new ArrayList<>(Arrays.asList(1.0,0.5,0.0));
        currentRow1 = new ArrayList<>(Arrays.asList(0.0,1.0,0.0));
        currentRow2 = new ArrayList<>(Arrays.asList(0.0,-0.5,1.0));
        currentMatrise = new ArrayList<>(Arrays.asList(currentRow0, currentRow1, currentRow2));
        currentVector = new ArrayList<>(Arrays.asList(1.0,1.0,3.0));

        assertEquals(currentMatrise, a.getMatrix());
        assertEquals(currentVector, a.getVector());
    }

    @Test
    public void testGaussElimination(){
        ArrayList<Double> currentRow0 = new ArrayList<>(Arrays.asList(1.0,0.0,0.0));
        ArrayList<Double> currentRow1 = new ArrayList<>(Arrays.asList(0.0,1.0,0.0));
        ArrayList<Double> currentRow2 = new ArrayList<>(Arrays.asList(0.0,0.0,1.0));
        ArrayList<ArrayList<Double>> currentMatrise = new ArrayList<>(Arrays.asList(currentRow0, currentRow1, currentRow2));
        ArrayList<Double> currentVector = new ArrayList<>(Arrays.asList(0.5,1.0,3.5));

        a.gaussElimination();
        assertEquals(currentMatrise, a.getMatrix());
        assertEquals(currentVector, a.getVector());
    }
}