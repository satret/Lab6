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
 * Заменить значение по ключу, если новое значение больше старого
 */
public class ReplaceIfGreaterCommand implements Command{
    Connection connection;
    private Scanner scanner;

    public ReplaceIfGreaterCommand(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return "replace_if_greater";
    }

    @Override
    public String getDescription() {
        return "заменить значение по ключу, если новое значение больше старого / replace_if_greater id \"name\" distance";
    }

    @Override
    public void execute(AbstractList<Token> tokens) throws Exception {
        InputHelper.displayInput(tokens);
        if(tokens == null){
            throw new IllegalArgumentException("Сисок токенов не может быть равен null!");
        }

        // Токенов должно быть 4: название команды и 3 аргумента
        if(tokens.size() != 4){
            throw new IllegalArgumentException("Аргументов этой команды должно быть 3.");
        }

        Route newRoute = new Route();

        InputHelper.receiveId(newRoute, tokens.get(1));

        InputHelper.receiveName(newRoute, tokens.get(2));
        InputHelper.receiveDistance(newRoute, tokens.get(3));

        InputHelper.receiveCoordinates(newRoute, scanner);
        InputHelper.receiveFrom(newRoute, scanner);
        InputHelper.receiveTo(newRoute, scanner);

        Request<?> request = new Request<Route>(this.getName(), newRoute);
        CommandResult result = connection.sendRequest(request);

        if(result.status == ResultStatus.OK){
            System.out.println(result.message);
        }
        else{
            System.out.println("Произошла ошибка: " + result.message);
        }
    }
}