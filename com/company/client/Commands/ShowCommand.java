package com.company.client.Commands;

import com.company.common.Net.CommandResult;
import com.company.common.Net.Request;
import com.company.common.Net.ResultStatus;
import com.company.client.Parser.InputHelper;
import com.company.client.Parser.Token;
import com.company.client.Programm.Connection;

import java.util.AbstractList;

/**
 * Вывести список элементов коллекции
 */
public class ShowCommand implements Command {
    private Connection connection;

    public ShowCommand(Connection connection){
        this.connection = connection;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public void execute(AbstractList<Token> tokens) {
        InputHelper.displayInput(tokens);

        Request<?> request = new Request<Object>(this.getName(), null);
        CommandResult result = connection.sendRequest(request);

        if(result.status == ResultStatus.OK){
            System.out.println(result.message);
        }
        else{
            System.out.println("Произошла ошибка: " + result.message);
        }
    }
}