package com.minecraft.moonlake.gui.exception;

/**
 * Created by MoonLake on 2016/7/26.
 */
public class IllegalGUIButtonOverflowException extends IllegalGUIException {

    public IllegalGUIButtonOverflowException() {

        this("The GUI Button Overflow Exception.");
    }

    public IllegalGUIButtonOverflowException(String message) {

        super(message);
    }

    public IllegalGUIButtonOverflowException(Throwable t) {

        super(t);
    }

    public IllegalGUIButtonOverflowException(String message, Throwable t) {

        super(message, t);
    }
}
