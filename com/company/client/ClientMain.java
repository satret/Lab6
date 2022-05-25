package com.company.client;


import com.company.client.Programm.Connection;
import com.company.client.Programm.CommandManager;
import com.company.client.Programm.Programm;

import java.util.Scanner;

public class ClientMain {
    // Поскольку ip это localhost, а вот порт меняется, тогда может понадобиться возможность сменить порт
    private static int port = 12345;

    public static void main(String[] args) {
        // Если предан другой порт в командной строке
        if(args.length == 1){
            try{
                port = Integer.parseInt(args[0]);
            }
            catch (Exception e){
                System.out.println("Что-то не получилось спарсить порт из агрументов командной строки, используется стандартный");
            }
        }

        Connection connection = new Connection(port);
        CommandManager cm = new CommandManager(connection, new Scanner(System.in));
        Programm.run(cm, new Scanner(System.in));
    }
}