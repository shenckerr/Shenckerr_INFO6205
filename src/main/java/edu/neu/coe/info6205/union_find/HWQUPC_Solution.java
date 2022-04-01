package edu.neu.coe.info6205.union_find;

import edu.neu.coe.info6205.graphs.BFS_and_prims.StdRandom;

public class HWQUPC_Solution {
    public static int count(int n) {

        int edges = 0;
        UF_HWQUPC uf = new UF_HWQUPC(n,true);
        while (uf.count > 1) {
            int i = StdRandom.uniform(n);
            int j = StdRandom.uniform(n);
            if (uf.find(i) != uf.find(j)) uf.union(i, j);
            edges++;
        }
        return edges;

    }




    private static int sum(int[] a) {

        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }


    public static double mean(int[] a) {
        int sum = sum(a);
        return 1.0 * sum / a.length;
    }




    public static void main(String[] args) {

        int n = 102400;
        int trials = 1000;
        int[] edges = new int[trials];
        // repeat the experiment
        for (int t = 0; t < trials; t++) {
            edges[t] = count(n);
        }
        System.out.println("no of objects = " + n);
        System.out.println("average no of pairs generated      = " + mean(edges));
    }
}
