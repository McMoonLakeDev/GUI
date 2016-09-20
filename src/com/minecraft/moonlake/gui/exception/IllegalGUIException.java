package com.minecraft.moonlake.gui.exception;

import com.minecraft.moonlake.exception.MoonLakeException;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class IllegalGUIException extends MoonLakeException {

    public IllegalGUIException() {

        this("The GUI Illegal Exception.");
    }

    public IllegalGUIException(String message) {

        super(message);
    }

    public IllegalGUIException(String message, Throwable t) {

        super(message, t);
    }
}
