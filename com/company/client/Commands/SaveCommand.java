package com.company.client.Commands;

import com.company.client.Parser.InputHelper;
import com.company.client.Parser.Token;
import com.company.client.Programm.Connection;
//import jdk.jshell.spi.ExecutionControl;

import java.util.AbstractList;

/**
 * Сохранить состояние коллекции в файле
 */
public class SaveCommand implements Command{
    private Connection connection;

    public SaveCommand(Connection connection){
        this.connection = connection;
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }

    @Override
    public void execute(AbstractList<Token> tokens) {
        InputHelper.displayInput(tokens);

        // Выпилено
    }
}