package hw2;

import edu.princeton.cs.introcs.StdRandom;

import java.util.Random;

public class PercolationStats {
    public double totalfraction;
    public int samplesize;
    public double samplemean;
    public double[] fractions;
    public double samplestddev;
    public double sampleconlow;
    public double sampleconhigh;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        samplesize = T;
        fractions = new double[T];
        double totalfraction = 0;
        for (int i = 0; i < T; i++) {
            Percolation test = pf.make(N);
            int opened = 0;
            while (!test.percolates()) {
                test.open(StdRandom.uniform(N), StdRandom.uniform(N));
                opened += 1;
            }
            fractions[i] = opened/(N*N);
            totalfraction += opened/(N*N);
        }

    }

    public double mean() {
        samplemean = totalfraction/samplesize;
        return samplemean;
    }

    public double stddev() {
        mean();
        double varsum = 0;
        for (int i = 0; i < samplesize; i++) {
            varsum += Math.pow(fractions[i] - samplemean, 2);
        }
        double variance = varsum / (samplesize - 1);
        samplestddev = Math.sqrt(variance);
        return samplestddev;
    }

    public double confidenceLow() {
        stddev();
        sampleconlow = samplemean - (1.96 * samplestddev / Math.sqrt(samplesize));
        return sampleconlow;
    }

    public double confidenceHigh() {
        stddev();
        sampleconhigh = samplemean + (1.96 * samplestddev / Math.sqrt(samplesize));
        return sampleconhigh;
    }



}
