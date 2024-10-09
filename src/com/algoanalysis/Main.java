package com.algoanalysis;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Adding 10,000 but excluding 100,000 for testing
        int[] sizes = {100, 500, 1000, 10000};  // Included 10,000 for larger testing

        // Create instances of each sorting/searching class
        BubbleSort bubbleSort = new BubbleSort();
        InsertionSort insertionSort = new InsertionSort();
        SelectionSort selectionSort = new SelectionSort();
        MergeSort mergeSort = new MergeSort();
        LinearSearch linearSearch = new LinearSearch();
        BinarySearch binarySearch = new BinarySearch();

        // Run analysis for sorting algorithms
        runSortingAnalysis("Bubble Sort", bubbleSort, sizes);
        runSortingAnalysis("Insertion Sort", insertionSort, sizes);
        runSortingAnalysis("Selection Sort", selectionSort, sizes);
        runSortingAnalysis("Merge Sort", mergeSort, sizes);

        // Run analysis for searching algorithms
        runSearchingAnalysis("Linear Search", linearSearch, sizes);
        runSearchingAnalysis("Binary Search", binarySearch, sizes);
    }

    // Helper function for running sorting algorithms
    public static void runSortingAnalysis(String algorithmName, Object sortingAlgorithm, int[] sizes) {
        System.out.println("Running analysis for: " + algorithmName);
        for (int size : sizes) {
            long[] times = new long[5];  // Keeping 5 runs for each input size

            for (int i = 0; i < 5; i++) {  // Running the algorithm 5 times for each size
                int[] arr = generateRandomArray(size);  // Generate random array of the given size

                long startTime = System.nanoTime();

                // Call the appropriate sorting method based on the class type
                if (sortingAlgorithm instanceof BubbleSort) {
                    ((BubbleSort) sortingAlgorithm).bubbleSort(arr);
                } else if (sortingAlgorithm instanceof InsertionSort) {
                    ((InsertionSort) sortingAlgorithm).insertionSort(arr);
                } else if (sortingAlgorithm instanceof SelectionSort) {
                    ((SelectionSort) sortingAlgorithm).selectionSort(arr);
                } else if (sortingAlgorithm instanceof MergeSort) {
                    ((MergeSort) sortingAlgorithm).mergeSort(arr, 0, arr.length - 1);
                }

                long endTime = System.nanoTime();
                times[i] = endTime - startTime;  // Store the time taken for each run
            }

            // Display the results for the sorting algorithm
            displayResults(algorithmName, size, times);
        }
    }

    // Helper function for running searching algorithms
    public static void runSearchingAnalysis(String algorithmName, Object searchingAlgorithm, int[] sizes) {
        System.out.println("Running analysis for: " + algorithmName);
        for (int size : sizes) {
            long[] times = new long[5];  // Keeping 5 runs for each input size

            for (int i = 0; i < 5; i++) {
                int[] arr = generateRandomArray(size);  // Generate random array of the given size
                int key = arr[(int) (Math.random() * arr.length)];  // Randomly select a key to search for

                long startTime = System.nanoTime();

                // Call the appropriate searching method based on the class type
                if (searchingAlgorithm instanceof LinearSearch) {
                    ((LinearSearch) searchingAlgorithm).linearSearch(arr, key);
                } else if (searchingAlgorithm instanceof BinarySearch) {
                    Arrays.sort(arr);  // Binary search requires a sorted array
                    ((BinarySearch) searchingAlgorithm).binarySearch(arr, key);
                }

                long endTime = System.nanoTime();
                times[i] = endTime - startTime;  // Store the time taken for each run
            }

            // Display the results for the searching algorithm
            displayResults(algorithmName, size, times);
        }
    }

    // Helper function to display results
    public static void displayResults(String algorithm, int size, long[] times) {
        long fastestTime = Arrays.stream(times).min().getAsLong();
        long slowestTime = Arrays.stream(times).max().getAsLong();
        long averageTime = (long) Arrays.stream(times).average().getAsDouble();

        System.out.println(algorithm + " - Array size: " + size);
        System.out.println("Fastest Time: " + fastestTime + " ns");
        System.out.println("Slowest Time: " + slowestTime + " ns");
        System.out.println("Average Time: " + averageTime + " ns");
        System.out.println();
    }

    // Helper function to generate a random array of the specified size
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 10000);  // Fill array with random numbers
        }
        return arr;
    }
}
