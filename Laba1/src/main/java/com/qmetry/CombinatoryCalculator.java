package com.qmetry;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Set;

public class CombinatoryCalculator {
    final static Logger logger = Logger.getLogger(CombinatoryCalculator.class);
    final
    public <T> ArrayList<T> simplePermutations(T[] elements, int n) {
        ArrayList<T> result = new ArrayList<T>();
        logger.info("simplePermutataion method");
        simplePermutationsRecursive(elements, n, result);

        return result;
    }

    public <T> void simplePermutationsRecursive(T[] elements, int n, ArrayList<T> result) {
        if(n == 1) {
            //result = elements;
            addArray(result, elements);
        } else {
            for(int i = 0; i < n-1; i++) {
                simplePermutationsRecursive(elements, n - 1, result);
                if(n % 2 == 0) {
                    swap(elements, i, n-1);
                } else {
                    swap(elements, 0, n-1);
                }
            }
            simplePermutationsRecursive(elements, n - 1, result);
        }
    }

    private <T> void addArray(ArrayList<T> result, T[] input) {
        System.out.print('\n');
        for(int i = 0; i < input.length; i++) {
            result.add(input[i]);
            //System.out.print(input[i]);
        }
    }
    private <T> void swap(T[] input, int a, int b) {
        T tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    public void simpleCombinations(ImmutableSet<Integer> set, int size){
        logger.info("simpleCombinations method");
        Set<Set<Integer>> combinations = Sets.combinations(set, size);
        for (Set<Integer> aSa : combinations) {
            for (Integer bSa : aSa) {
                System.out.println(bSa);
            }
        }
    }
}
