package edu.hw2.task3;

import edu.hw2.task3.connectionManagers.ConnectionManager;
import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.connections.ConnectionException;
import org.apache.logging.log4j.core.pattern.EqualsBaseReplacementConverter;
import java.util.logging.Logger;

public class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;
    private final Logger logger = Logger.getLogger("myLogger");

    PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        Connection connection = manager.getConnection();

        for (int i = 0; i < maxAttempts; i++) {
            try {
                connection.execute(command);
                break;
            } catch (ConnectionException e) {
                if (i == maxAttempts - 1) {

                    try {
                        connection.close();
                    } catch (Exception ignored) {
                        logger.info("Error while closing connection!");
                    }

                    throw new ConnectionException(e.getCause());
                }
            }
        }

        try {
            connection.close();
        } catch (Exception ignored) {
            logger.info("Error while closing connection!");
        }
    }

}
