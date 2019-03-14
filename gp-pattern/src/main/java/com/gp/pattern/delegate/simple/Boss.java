package com.gp.pattern.delegate.simple;

public class Boss {

    public void command(String command, Manager manager) {
        manager.doing(command);
    }
}


