package com.minecraft.moonlake.gui.exception;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class IllegalGUISlotOutBoundException extends IllegalGUIException {

    public IllegalGUISlotOutBoundException() {

        this("The GUI Slot Out Bound Exception.");
    }

    public IllegalGUISlotOutBoundException(String message) {

        super(message);
    }

    public IllegalGUISlotOutBoundException(Throwable t) {

        super(t);
    }

    public IllegalGUISlotOutBoundException(String message, Throwable t) {

        super(message, t);
    }
}
