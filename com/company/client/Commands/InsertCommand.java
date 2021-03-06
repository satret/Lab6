package com.company.client.Commands;

import com.company.common.DataModels.Route;
import com.company.common.Net.CommandResult;
import com.company.common.Net.Request;
import com.company.common.Net.ResultStatus;
import com.company.client.Parser.InputHelper;
import com.company.client.Parser.Token;
import com.company.client.Programm.Connection;

import java.util.AbstractList;
import java.util.Scanner;

/**
 * Добавить новый элемент в коллекцию
 */
public class InsertCommand implements Command {
    Connection connection;
    Scanner scanner;

    public InsertCommand(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return "insert";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент с заданным ключом / insert \"name\" distance";
    }

    @Override
    public void execute(AbstractList<Token> tokens) throws Exception {
        InputHelper.displayInput(tokens);

        if(tokens == null){
            throw new IllegalArgumentException("Сисок токенов не может быть равен null!");
        }

        // Токенов должно быть 3: название команды и 2 аргумента
        if(tokens.size() != 3){
            throw new IllegalArgumentException("Аргументов этой команды должно быть 2.");
        }

        Route route = new Route();

        InputHelper.receiveName(route, tokens.get(1));
        InputHelper.receiveDistance(route, tokens.get(2));

        InputHelper.receiveCoordinates(route, scanner);
        InputHelper.receiveFrom(route, scanner);
        InputHelper.receiveTo(route, scanner);

        Request<?> request = new Request<Route>(this.getName(), route);
        CommandResult result = connection.sendRequest(request);

        if(result.status == ResultStatus.OK){
            System.out.println(result.message);
        }
        else{
            System.out.println("Произошла ошибка: " + result.message);
        }
    }
}