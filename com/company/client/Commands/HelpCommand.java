package com.company.client.Commands;

import com.company.client.Parser.InputHelper;
import com.company.client.Parser.Token;
import com.company.client.Programm.CommandManager;

import java.util.AbstractList;

/**
 * Вывести список команд и их описания
 */
public class HelpCommand implements Command {
    CommandManager manager;

    public HelpCommand(CommandManager manager){
        this.manager = manager;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

    @Override
    public void execute(AbstractList<Token> tokens) {
        InputHelper.displayInput(tokens);
        System.out.println(manager.getCommandsNameWithDescription());
    }
}