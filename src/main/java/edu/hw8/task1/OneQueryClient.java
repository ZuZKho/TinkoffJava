package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class OneQueryClient {

    private OneQueryClient() {
    }

    public static String query(String question, int port) throws IOException {
        Socket clientSocket = new Socket("localhost", port);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            out.write(question + "\n");
            out.flush();

            String response = in.readLine();
            return response.strip();
        } finally {
            clientSocket.close();
        }
    }
}
