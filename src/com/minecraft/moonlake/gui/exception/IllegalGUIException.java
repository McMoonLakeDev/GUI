package com.minecraft.moonlake.gui.exception;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class IllegalGUIException extends RuntimeException {

    public IllegalGUIException() {

        this("The GUI Illegal Exception.");
    }

    public IllegalGUIException(String message) {

        super(message);
    }

    public IllegalGUIException(Throwable t) {

        super(t);
    }

    public IllegalGUIException(String message, Throwable t) {

        super(message, t);
    }
}
