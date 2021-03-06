package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.Math;

public class Main {

    public static void main(String[] args) {

        int X = 92;


        long result;
        runTimeTests();

        //result = fibMatrix(X);
        //System.out.println("\n"+X+": "+result+" is the last fib(x) that can be displayed in 64 bits \n");

        /*result = fibRecur(X);
        System.out.println("\nfibRecur "+result+"\n");
        result = fibCache(X);
        System.out.println("\nfibCache "+result+"\n");
        result = fibLoop(X);
        System.out.println("\nfibLoop "+result+"\n");
        result = fibMatrix(X);
        System.out.println("\nfibMatrix "+result+"\n");*/
    }
    /** Get CPU time in nanoseconds since the program(thread) started. */
    /**
     * from: http://nadeausoftware.com/articles/2008/03/java_tip_how_get_cpu_and_user_time_benchmarking#TimingasinglethreadedtaskusingCPUsystemandusertime
     **/
    public static long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;
    }

    public static void runTimeTests( ){

        int X;
        int X_Max = 5000;
        int N;
        int i = 0;
        int fibRecurX_MAX = 46;
        long totalTime = 0;
        long maxTime = 10000000;
        long trialCount = 0;
        long maxTrials = 1000000000;//999999999;
        long timeStampBefore = 0;
        long timeStampAfter = 0;
        long doNothingTimeStampBefore = 0;
        long doNothingTimeStampAfter = 0;
        long doNothingTimeMeasured = 0;
        long timeMeasured = 0;
        long fibRecurAverageTimeMeasured = 0;
        long fibCacheAverageTimeMeasured = 0;
        long fibLoopAverageTimeMeasured = 0;
        long fibMatrixAverageTimeMeasured = 0;
        long fibCacheTimeResults[] = new long[X_Max+1];
        long fibRecurTimeResults[] = new long[X_Max+1];
        long fibLoopTimeResults[] = new long[X_Max+1];
        long fibMatrixTimeResults[] = new long[X_Max+1];
        double fibRecurDoubleRatio = 0;
        double fibCacheDoubleRatio = 0;
        double fibLoopDoubleRatio = 0;
        double fibMatrixDoubleRatio = 0;
        double expectedFibRecurDoubleRatio = 0;
        double expectedFibCacheDoubleRatio = 0;
        double expectedFibLoopDoubleRatio = 0;
        double expectedFibMatrixDoubleRatio = 0;

        /**Print Column Headings**/
        System.out.printf("\n%13s %8s %17s %9s %10s %20s %7s %10s %20s %7s %10s %20s %7s %10s\n", "X","N","FibRecur Time","DR","Exp. DR", "FibCache Time", "DR", "Exp. DR","FibLoop Time","DR","Exp. DR","FibMatrix Time","DR","Exp. DR");
        System.out.printf("%185s\n", "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for ( X = 1; X <= X_Max; X++ ) {

            totalTime = 0;
            trialCount = 0;
            // Time trial for fibRecur
            while (totalTime < maxTime && trialCount < maxTrials) {
                if (X >= fibRecurX_MAX) {
                    trialCount = 1;
                    totalTime = 0;
                    break;
                }
                timeStampBefore = getCpuTime();
                fibRecur(X);
                timeStampAfter = getCpuTime();

                //doNothingTimeStampBefore = getCpuTime();
                //doNothingTimeStampAfter = getCpuTime();
                //doNothingTimeMeasured = doNothingTimeStampAfter - doNothingTimeStampBefore;
                //timeMeasured = timeStampAfter - timeStampBefore - doNothingTimeMeasured;
                timeMeasured = timeStampAfter - timeStampBefore;
                totalTime = totalTime + timeMeasured;
                trialCount++;
            }

            fibRecurAverageTimeMeasured = (totalTime / trialCount) ;
            fibRecurTimeResults[X] = fibRecurAverageTimeMeasured;
            if (X % 2 == 0) {
                fibRecurDoubleRatio = (double) fibRecurTimeResults[X] / fibRecurTimeResults[(X / 2)];
                // expectedFibRecurDoubleRatio = (double) fibRecur(X) / fibRecur(X/2);
                expectedFibRecurDoubleRatio = Math.pow(1.61803398875, X) / Math.pow(1.61803398875, (X / 2));
            }
            if (X >= fibRecurX_MAX) {
                fibRecurDoubleRatio = 0;
                expectedFibRecurDoubleRatio = 0;
            }

            totalTime = 0;
            trialCount = 0;
            // Time trial for fibCache
            while (totalTime < maxTime && trialCount < maxTrials) {
                timeStampBefore = getCpuTime();
                fibCache(X);
                timeStampAfter = getCpuTime();

                //doNothingTimeStampBefore = getCpuTime();
                //doNothingTimeStampAfter = getCpuTime();
                //doNothingTimeMeasured = doNothingTimeStampAfter - doNothingTimeStampBefore;
                //timeMeasured = timeStampAfter - timeStampBefore - doNothingTimeMeasured;
                timeMeasured = timeStampAfter - timeStampBefore;
                totalTime = totalTime + timeMeasured;
                trialCount++;
            }
            fibCacheAverageTimeMeasured = totalTime / trialCount;
            fibCacheTimeResults[X] = fibCacheAverageTimeMeasured;
            if (X % 2 == 0) {
                fibCacheDoubleRatio = (double) fibCacheTimeResults[X] / fibCacheTimeResults[(X / 2)];
                expectedFibCacheDoubleRatio = (double) X / (X / 2);
            }

            totalTime = 0;
            trialCount = 0;
            // Time trial for fibLoop
            while (totalTime < maxTime && trialCount < maxTrials) {
                timeStampBefore = getCpuTime();
                fibLoop(X);
                timeStampAfter = getCpuTime();

                //doNothingTimeStampBefore = getCpuTime();
                //doNothingTimeStampAfter = getCpuTime();
                //doNothingTimeMeasured = doNothingTimeStampAfter - doNothingTimeStampBefore;
                //timeMeasured = timeStampAfter - timeStampBefore - doNothingTimeMeasured;
                timeMeasured = timeStampAfter - timeStampBefore;
                totalTime = totalTime + timeMeasured;
                trialCount++;
            }
            fibLoopAverageTimeMeasured = totalTime / trialCount;
            fibLoopTimeResults[X] = fibLoopAverageTimeMeasured;

            if (X % 2 == 0) {
                fibLoopDoubleRatio = (double) fibLoopTimeResults[X] / fibLoopTimeResults[(X / 2)];
                expectedFibLoopDoubleRatio = (double) X / (X / 2);
            }

            totalTime = 0;
            trialCount = 0;
            // Time trial for fibMatrix
            while (totalTime < maxTime && trialCount < maxTrials) {
                timeStampBefore = getCpuTime();
                fibMatrix(X);
                timeStampAfter = getCpuTime();

                //doNothingTimeStampBefore = getCpuTime();
                //doNothingTimeStampAfter = getCpuTime();
                //doNothingTimeMeasured = doNothingTimeStampAfter - doNothingTimeStampBefore;
                //timeMeasured = timeStampAfter - timeStampBefore - doNothingTimeMeasured;
                timeMeasured = timeStampAfter - timeStampBefore;
                totalTime = totalTime + timeMeasured;
                trialCount++;
            }
            fibMatrixAverageTimeMeasured = totalTime / trialCount;
            fibMatrixTimeResults[X] = fibMatrixAverageTimeMeasured;

            if (X % 2 == 0 && X != 2) {
                fibMatrixDoubleRatio = (double) fibMatrixTimeResults[X] / fibMatrixTimeResults[(X / 2)];
                expectedFibMatrixDoubleRatio = (double) log2(X) / log2(X - 1);
            }


            N = (int) Math.ceil(log2(X + 1));
            if (X == 1) {
                String notApplicable = "na";
                System.out.printf("%13s %8s %17s %9s %10s %20s %7s %10s %20s %7s %10s %20s %7s %10s\n", X, N, fibRecurAverageTimeMeasured, notApplicable, notApplicable, fibCacheAverageTimeMeasured, notApplicable, notApplicable, fibLoopAverageTimeMeasured, notApplicable, notApplicable, fibMatrixAverageTimeMeasured, notApplicable, notApplicable);
            } else if(X >= fibRecurX_MAX ) {
                String notApplicable = "na";
                System.out.printf("%13s %8s %17s %9s %10s %20s %7.2f %10.2f %20s %7.2f %10.2f %20s %7.2f %10.2f\n", X, N, notApplicable, notApplicable, notApplicable, fibCacheAverageTimeMeasured, fibCacheDoubleRatio, expectedFibCacheDoubleRatio, fibLoopAverageTimeMeasured, fibLoopDoubleRatio, expectedFibLoopDoubleRatio, fibMatrixAverageTimeMeasured, fibMatrixDoubleRatio, expectedFibMatrixDoubleRatio);
            }  else if(X%2 !=0) {
                String notApplicable = "na";
                System.out.printf("%13s %8s %17s %9s %10s %20s %7s %10s %20s %7s %10s %20s %7s %10s\n", X, N, fibRecurAverageTimeMeasured, notApplicable, notApplicable, fibCacheAverageTimeMeasured, notApplicable, notApplicable, fibLoopAverageTimeMeasured, notApplicable, notApplicable, fibMatrixAverageTimeMeasured, notApplicable, notApplicable);
            }
            else{
                System.out.printf("%13s %8s %17s %9.2f %10.2f %20s %7.2f %10.2f %20s %7.2f %10.2f %20s %7.2f %10.2f\n", X,N,fibRecurAverageTimeMeasured,fibRecurDoubleRatio,expectedFibRecurDoubleRatio, fibCacheAverageTimeMeasured, fibCacheDoubleRatio, expectedFibCacheDoubleRatio,fibLoopAverageTimeMeasured,fibLoopDoubleRatio,expectedFibLoopDoubleRatio,fibMatrixAverageTimeMeasured,fibMatrixDoubleRatio,expectedFibMatrixDoubleRatio);
            }
        }


    }

    public static int log2(int x){
        return (int) (Math.log(x) / Math.log(2));
    }

    public static long fibRecur( int X) {
        //base case
        if ( X == 0 || X == 1 ){
            return X;
        } else {
            // calculate the result using the previous two results will go until X == 0
            return fibRecur(X - 1) + fibRecur( X - 2);
        }
    }

    public static long fibCache( int X){

        long[] cache = new long[X+2];
        // store results when X == 0 or X == 1
        cache [0] = 0;
        cache [1] = 1;
        // use the previous two results to calculate next result up until X
        for ( int i =  2; i <= X; i++){
            cache[i] = cache[i-1] + cache[i-2];
        }
        return cache[X];
    }

    public static long fibLoop( int X) {

        long A = 0;
        long B = 1;

        if ( X == 0 || X == 1 ) {
            return X;
        }
        // use the previous two results to calculate next result up until X
        for ( int i = 2; i <= X; i++) {
            long next = A + B;
            A = B;
            B = next;
        }
        return B;
    }

    public static long fibMatrix ( int X){

        long fib[][] = new long[][]{{1, 1}, {1,0}};
        if ( X == 0 ){
            return 0;
        }
        matrixPOW( fib, X-1 );
        // returns upper left position of 2x2 matrix
        return fib[0][0];
    }

    public static void matrixMultiply(long A[][], long B[][]){
        //perform matrix multiplication on a 2x2 matrix
        long a = A[0][0] * B[0][0] + A[0][1] * B[1][0];
        long b = A[0][0] * B[0][1] + A[0][1] * B[1][1];
        long c = A[1][0] * B[0][0] + A[1][1] * B[1][0];
        long d = A[1][0] * B[0][1] + A[1][1] * B[1][1];
        //store results into each specific position of the matrix
        A[0][0] = a;
        A[0][1] = b;
        A[1][0] = c;
        A[1][1] = d;
    }

    public static void matrixPOW( long fib[][], int X){
        //base case
        if ( X == 0 || X == 1 ){
            return;
        }
        long mat[][] = new long[][]{{1, 1}, {1,0}};
        // raise the matrix to the desired power
        matrixPOW( fib, X/2 );
        matrixMultiply( fib, fib );
        // if X is odd
        if ( X%2 != 0 ){
            matrixMultiply( fib, mat );
        }
    }
}

