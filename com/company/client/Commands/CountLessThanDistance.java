package com.company.client.Commands;

import com.company.common.Net.CommandResult;
import com.company.common.Net.Request;
import com.company.common.Net.ResultStatus;
import com.company.client.Parser.InputHelper;
import com.company.client.Parser.Parser;
import com.company.client.Parser.Token;
import com.company.client.Programm.Connection;

import java.util.AbstractList;

/**
 * Посчитать количество элементов, поле distance которых меньше, чем заданный
 */
public class CountLessThanDistance implements Command{
    private Connection connection;

    public  CountLessThanDistance(Connection connection){
        this.connection = connection;
    }

    @Override
    public String getName() {
        return "count_less";
    }

    @Override
    public String getDescription() {
        return "Посчитать количество элементов меньше, чем заданный / count_less";
    }

    @Override
    public void execute(AbstractList<Token> tokens) throws Exception {
        InputHelper.displayInput(tokens);
        long id;
        try{
            id = Parser.parseLong(tokens.get(1));
        }
        catch (Exception e){
            throw new Exception("Парсинг агрумента distance не удался. " + e.getMessage());
        }

        Request<?> request = new Request<Long>(this.getName(), id);
        CommandResult result = connection.sendRequest(request);

        if(result.status == ResultStatus.OK){
            System.out.println(result.message);
        }
        else{
            System.out.println("Произошла ошибка: " + result.message);
        }
    }
}