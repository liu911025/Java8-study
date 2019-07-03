package com.java8.study.chap3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 环绕
 */
public class ExecuteAround {

    public static void main(String[] args) throws IOException {
        String s = processFile();
        System.out.println(s);

        String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println("result: " + result);
    }

    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("D:/data.txt"))) {
            return br.readLine();
        }
    }

    public static String processFile(BufferedReaderProcess p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("D:/data.txt"))) {
            return p.processFile(br);
        }
    }

}

@FunctionalInterface
interface BufferedReaderProcess {

    String processFile(BufferedReader br) throws IOException;
}

