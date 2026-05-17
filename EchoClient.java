package org.example;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {

        String serverAddress = "127.0.0.1";
        int port = 33251;

        try (Socket socket = new Socket(serverAddress, port);
            BufferedReader input = new BufferedReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        ){
            System.out.println("Подключение к серверу...");
            String message = "Привет сервер";
            output.println(message);
            System.out.println("Отправлено сообщение " + message);
            String response =input.readLine();
            System.out.println("Ответ сервера " + response);
        }catch (IOException e){
            System.out.println("Ошибка клиента"+e.getMessage());
        }

    }
}