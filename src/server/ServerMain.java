package server;

import java.io.IOException;
import java.sql.SQLException;

public class ServerMain{
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.serverListen();
        }
        catch (IOException e) {
            server.stopServer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
