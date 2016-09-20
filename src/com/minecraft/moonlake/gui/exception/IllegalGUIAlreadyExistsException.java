package com.minecraft.moonlake.gui.exception;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class IllegalGUIAlreadyExistsException extends IllegalGUIException {

    public IllegalGUIAlreadyExistsException() {

        this("The GUI Already Exists Exception.");
    }

    public IllegalGUIAlreadyExistsException(String message) {

        super(message);
    }

    public IllegalGUIAlreadyExistsException(String message, Throwable t) {

        super(message, t);
    }
}
