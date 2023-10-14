package edu.hw2.task3;

import edu.hw2.task3.connectionManagers.DefaultConnectionManager;
import edu.hw2.task3.connectionManagers.FaultyConnectionManager;
import edu.hw2.task3.connections.ConnectionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class PopularCommandExecutorTest {

    @Test
    @DisplayName("DefaultConnectionManagerTest")
    void testDefaultConnectionManager() throws Exception{
        PopularCommandExecutor pce = new PopularCommandExecutor(new DefaultConnectionManager(), 2);
        try {
            pce.updatePackages();
        } catch (ConnectionException e) {
            assertEquals(e.getCause(), new ConnectionException().getCause());
        }
    }


    @Test
    @DisplayName("FaultyConnectionManagerTest")
    void testFaultyConnectionManager() throws Exception {
        PopularCommandExecutor pce = new PopularCommandExecutor(new FaultyConnectionManager(), 2);

        try {
            pce.updatePackages();
        } catch (ConnectionException e) {
            assertEquals(e.getCause(), new ConnectionException().getCause());
        }
    }
}
