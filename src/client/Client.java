package client;

import shared.Config;
import shared.Punteggio;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private final String ip;
    private final int port;
    ArrayList<Punteggio> classifica;
    BufferedReader responseReader;
    PrintWriter requestWriter;
    ObjectInputStream dataReader;
    ObjectOutputStream dataWriter;
    Socket socket;
    Config config = new Config();

    public Client() {
        this.ip = config.getIp();
        this.port = config.getPort();
        classifica = new ArrayList<>();
    }

    private void connect() throws IOException {
        System.out.println("Connessione al server");
        socket = new Socket(this.ip, this.port);
        dataWriter = new ObjectOutputStream(socket.getOutputStream());
        dataWriter.flush();
        dataReader = new ObjectInputStream(socket.getInputStream());
        responseReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        requestWriter = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Connesso al server");
    }

    private void closeConnection() throws IOException {
        System.out.println("Sto chiudendo la connessione");
        socket.close();
        responseReader.close();
        requestWriter.close();
        dataReader.close();
        dataWriter.close();
        System.out.println("Connessione chiusa");
    }

    public void addPunteggio(Punteggio punteggio) throws IOException {
        connect();
        try {
            System.out.println("Invio la richiesta ADD");
            requestWriter.println("ADD");
            System.out.println("Invio il punteggio");
            dataWriter.writeObject(punteggio);
            dataWriter.flush();
            System.out.println("Punteggio inviato");
        } finally {
            closeConnection();
        }
    }

    public ArrayList<Punteggio> getPunteggio() throws IOException {
        connect();
        ArrayList<Punteggio> classificaRicevuta = null;
        try {
            System.out.println("Invio la richiesta GET");
            requestWriter.println("GET");
            System.out.println("Richiedo la classifica");
            classificaRicevuta = (ArrayList<Punteggio>) dataReader.readObject();
            System.out.println("Classifica ricevuta: " + classificaRicevuta.size() + " elementi");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return classificaRicevuta;
    }
}
