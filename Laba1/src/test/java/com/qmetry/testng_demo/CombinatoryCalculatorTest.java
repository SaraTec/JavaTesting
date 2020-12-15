package com.qmetry.testng_demo;

import com.google.common.collect.ImmutableSet;
import com.qmetry.BitwiseCalculator;
import com.qmetry.CombinatoryCalculator;
import com.qmetry.models.TrigonometryConstantsModel;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CombinatoryCalculatorTest {
    private static CombinatoryCalculator calculator;

    @BeforeClass
    public static void initCalculator() { calculator = new CombinatoryCalculator(); double temp = TrigonometryConstantsModel.pi; }

    @BeforeTest
    public void beforeEachTest() {
        //System.out.println("This is executed before each Test");
    }

    @AfterTest
    public void afterEachTest() {
        //System.out.println("This is exceuted after each Test");
    }

    @Test
    public void testSimpleCombinations(){
        //calculator.simpleCombinations(ImmutableSet.of(1,6,4,5), 4);
        //assertEquals(48, result);
    }
    @Test
    public void testSimplePermutations(){
        ArrayList<Integer> result =  calculator.simplePermutations(new Integer[]{1,2,3},3);
        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 1, 3, 3, 1, 2, 1, 3, 2, 2, 3, 1, 3, 2, 1));
        assertEquals(expectedResult, result);
    }
}
