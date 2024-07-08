package server;

import shared.Punteggio;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class RequestHandler extends Thread {

    Socket clientSocket;
    BufferedReader requestReader;
    PrintWriter responseWriter;
    ObjectInputStream dataReader;
    ObjectOutputStream dataWriter;
    ArrayList<Punteggio> classifica;
    Database db;

    public RequestHandler(Socket socket, ArrayList<Punteggio> classifica, Database db) throws IOException {
        clientSocket = socket;
        this.classifica = classifica;
        this.db = db;
        responseWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        requestReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        dataWriter = new ObjectOutputStream(clientSocket.getOutputStream());
        dataWriter.flush();
        dataReader = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        String request;
        try {
            System.out.println("In attesa di richieste");
            request = requestReader.readLine();
            System.out.println("Richiesta ricevuta:" + request);
        } catch (IOException e) {
            System.out.println("Errore:" + e.getMessage());
            return;
        }

        switch (request) {
            case "ADD":
                addRequest();
                break;
            case "GET":
                sendClassifica();
                break;
            default:
                System.out.println("Richiesta sconosciuta: " + request);
                break;
        }
    }

    public void addRequest() {
        System.out.println("Richiesta ADD");
        Punteggio punteggioRicevuto = null;
        try {
            punteggioRicevuto = (Punteggio) dataReader.readObject();
            System.out.println("Punteggio ricevuto: " + punteggioRicevuto.getPunteggio() + ", " + punteggioRicevuto.nome);
            db.aggiungiPunteggio(punteggioRicevuto.nome, punteggioRicevuto.getPunteggio());
            classifica.add(punteggioRicevuto);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public void sendClassifica() {
        System.out.println("Invio la classifica");
        try {
            dataWriter.writeObject(classifica);
            dataWriter.flush();
            System.out.println("Classifica inviata");
        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}
