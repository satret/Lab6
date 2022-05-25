package com.company.client.Programm;

import com.company.common.Net.CommandResult;
import com.company.common.Net.ResultStatus;
import com.company.common.Net.Request;

import java.io.*;
import java.net.Socket;

public class Connection {
    protected int port;

    public Connection(int port){
        this.port = port;
    }

    // тут как-то запросы отправляться будут
    public CommandResult sendRequest(Request<?> request){
        if(request == null){
            throw new IllegalArgumentException("Запрос не может быть null!");
        }

        try{
            Socket socket = new Socket("127.0.0.1", port);

            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(request);

            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            CommandResult result = (CommandResult) ois.readObject();
            return result;
        }
        catch (IOException | ClassNotFoundException exc){
            return new CommandResult(ResultStatus.OK, "Не удалось подключиться к серверу, выполнение команды отменено.");
        }
    }
}