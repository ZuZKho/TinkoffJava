package edu.hw2.task3.connectionManagers;

import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.connections.FaultyConnection;
import edu.hw2.task3.connections.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    public Connection getConnection() {
        Random rnd = new Random();
        if (rnd.nextInt(2) == 0) {
            return new StableConnection();
        }
        return new FaultyConnection();
    }

}
