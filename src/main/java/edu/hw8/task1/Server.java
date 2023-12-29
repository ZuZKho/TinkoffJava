package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {

    ServerSocket serverSocket;
    ExecutorService executor;
    AtomicBoolean terminated = new AtomicBoolean(false);

    String[] dict = new String[] {
        "Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления"
    };

    public void terminate() {
        try {
            terminated.set(true);
            serverSocket.close();
            executor.shutdown();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(int nThreads, int port) {
        terminated.set(false);
        executor = Executors.newFixedThreadPool(nThreads);
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            if (!terminated.get()) {
                throw new RuntimeException(e);
            }
        }
    }

    private class ClientHandler implements Runnable {

        private Socket socket;

        ClientHandler(Socket clientSocket) {
            socket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                String substr = in.readLine().strip();

                for (String s : dict) {
                    if (s.contains(substr)) {
                        out.write(s);
                        out.flush();
                        return;
                    }
                }
                out.write("-\n");
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException("Client handler error");
            }
        }
    }
}
