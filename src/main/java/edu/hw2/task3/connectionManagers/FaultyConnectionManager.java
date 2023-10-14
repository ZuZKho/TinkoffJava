package edu.hw2.task3.connectionManagers;

import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.connections.FaultyConnection;

public class FaultyConnectionManager implements ConnectionManager {

    public Connection getConnection() {
        return new FaultyConnection();
    }

}
