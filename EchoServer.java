package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args)
    {
        int port = 33251;

        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Сервер запущен на порту " + port);
            System.out.println("Ожидание подключения клиента...");
            while (true){
                Socket clientSocket = serverSocket.accept();

                System.out.println("Клиент подключился" + clientSocket.getInetAddress());
                try (
                        BufferedReader input= new BufferedReader(new InputStreamReader
                                (clientSocket.getInputStream()));

                        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                ){
                    String message = input.readLine();
                    System.out.println("Получено сообщение " + message);
                    output.println("Эхо: " + message);
                }catch (IOException e){
                    System.out.println( "Ошибка при работе с клиентом"+e.getMessage());
                }finally {
                    clientSocket.close();
                    System.out.println("Клиент отключен");
                }
            }
        }catch (IOException e){
            System.out.println("Ошибка сервера"+e.getMessage());
        }
    }
}