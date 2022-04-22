package com.levs2001;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class presents the answer on test from BIOCAD internship.
 * I was forced to write the answers to both questions in the same class
 * as the solution must be presented in the same file.
 * So there are some private included classes here.
 * <p>
 * Full repository this task: https://github.com/levs2001/BiocadTrainee.git
 */
public class BioCad {
    private static final String JSON_FILE_PATH = "./json_files/biocad.json";

    /**
     * Print Fibonacci numbers in console.
     *
     * @param fibCount - count of Fibonacci numbers to print
     */
    private static void printFibonacci(int fibCount) {
        System.out.println("Fibonacci Task, count of fibonacci nums is " + fibCount + ":");
        int fib1 = 0;
        int fib2 = 1;
        int fybSum = fib1 + fib2;

        for (int i = 2; i < fibCount + 2; i++) {
            // Printing current fib number from row
            System.out.println(fib1);
            fib1 = fib2;
            fib2 = fybSum;
            fybSum = fib1 + fib2;
        }

        System.out.println();
    }

    /**
     * Class Table can be load from json file, it represents our table.
     */
    private static class Table {
        private final Map<String, List<Integer>> rows;

        private Table(Map<String, List<Integer>> rows) {
            this.rows = rows;
        }

        /**
         * Load Table from json file
         *
         * @param jsonFilePath filename of file with table in json format.
         * @return new Table with table content from json
         */
        public static Table load(String jsonFilePath) throws IOException {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, List<Integer>> rows = objectMapper.readValue(new File(jsonFilePath), new TypeReference<>() {
            });
            return new Table(rows);
        }

        /**
         * Prints table in console.
         */
        public void printInConsole() {
            System.out.println("Table from json: ");
            System.out.println("[Name: Values]");
            for (var row : rows.entrySet()) {
                printRow(row);
            }
        }

        private static void printRow(Map.Entry<String, List<Integer>> row) {
            System.out.print(row.getKey() + ": ");
            for (var el : row.getValue()) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // First task output:
        printFibonacci(20);

        // Second task init:
        Table table;
        try {
            table = Table.load(JSON_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Can't load json file for table.");
            return;
        }
        // Second task output:
        table.printInConsole();
    }
}
