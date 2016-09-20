package com.minecraft.moonlake.gui.exception;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class IllegalGUIButtonConflictException extends IllegalGUIException {

    public IllegalGUIButtonConflictException() {

        this("The GUI Slot and Button Conflict Exception.");
    }

    public IllegalGUIButtonConflictException(String message) {

        super(message);
    }

    public IllegalGUIButtonConflictException(String message, Throwable t) {

        super(message, t);
    }
}
