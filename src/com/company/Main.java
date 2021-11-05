package com.company;

/*
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
*/

import java.io.*;

public class Main {

    private static Boolean doPing(String hostName) {
        String cmdCommand = "ping -n 1 " + hostName;
        try {
            var p1 = java.lang.Runtime.getRuntime().exec(cmdCommand);
            if (p1.waitFor() != 0) return false;
            var br = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            return (br.lines().filter((String line) -> line.indexOf("TTL=") > 0).count() > 0);
        } catch (IOException | InterruptedException e) {
            System.err.printf("command: %s \nerror: %s \n", cmdCommand, e);
        }
        return false;
    }

    public static void main(String[] args) {

        var hostName = "github.com";
        var isSuccess = doPing(hostName);

        System.out.printf("host %s available: %s \n", hostName, isSuccess);
    }
}

