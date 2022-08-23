package de.trexic.inventoryguiapi.internal.exeptions;

public class UnsupportedInventoryTypeException extends Exception{
    public UnsupportedInventoryTypeException(String error) {
        super(error);
    }
}
