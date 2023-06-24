package com.lyx.batch.command;

public class Main {
    public static void main(String[] args) {
        Content c = new Content();

        InsertCommand insertCommand = new InsertCommand(c);
        insertCommand.doit();
        insertCommand.undo();

        CopyCommand copyCommand = new CopyCommand(c);
        copyCommand.doit();
        copyCommand.undo();

        DeleteCommand deleteCommand = new DeleteCommand(c);
        deleteCommand.doit();
        deleteCommand.undo();
        System.out.println(c.msg);
    }
}
