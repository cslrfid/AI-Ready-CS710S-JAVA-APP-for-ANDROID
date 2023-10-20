package com.csl.cslibrary4a;

public class Cs108ReadData {
    public enum Cs108ConnectedDevices {
        RFID, BARCODE, NOTIFICATION, SILICONLAB, BLUETOOTH, OTHER
    }
    public Cs108ConnectedDevices cs108ConnectedDevices;
    public byte[] dataValues;
    public boolean invalidSequence;
    public long milliseconds;
}
