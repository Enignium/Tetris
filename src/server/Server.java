package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import shared.Config;
import shared.Punteggio;

public class Server {

    ServerSocket socket;
    ArrayList<Punteggio> classifica = new ArrayList<Punteggio>();
    RequestHandler requestHandler;
    private boolean running = true;
    Config config = new Config();
    Database db = new Database("classifica.db");

    public Server() {
        try {
            this.socket = new ServerSocket(config.getPort());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void serverListen() throws IOException, SQLException {

        System.out.println("Server in ascolto sulla porta: " + config.getPort());
        while (running) {

            Socket socketClient = socket.accept();
            updateClassifica();
            System.out.println("Client connesso");
            requestHandler = new RequestHandler(socketClient, classifica, db);
            requestHandler.start();

        }
    }

    public void updateClassifica() throws SQLException {
        ResultSet classificaDb = db.getListaPunteggi();
        ArrayList<Punteggio> classificaAggiornata = new ArrayList<Punteggio>();
        String nuovoNome;
        int punteggioNuovo;

        if (classifica != null) {
            while (classificaDb.next()) {
                nuovoNome = classificaDb.getString("nome");
                punteggioNuovo = classificaDb.getInt("punteggio");
                System.out.println(nuovoNome + punteggioNuovo);
                classificaAggiornata.add(new Punteggio(punteggioNuovo,nuovoNome));
            }
            this.classifica = classificaAggiornata;
        }

    }

    public void stopServer(){
        running = false;
    }
}
