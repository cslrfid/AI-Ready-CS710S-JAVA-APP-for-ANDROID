package com.csl.cslibrary4a;

import android.content.Context;

import java.util.ArrayList;

public class RfidReader {
    RfidConnector rfidConnector; RfidReaderChipR2000 rfidReaderChipR2000; RfidReaderChipE710 rfidReaderChip;
    public ArrayList<RfidConnector.CsReaderRfidData> mRfidToWrite;
    Context context; Utility utility; CsReaderConnector108 csReaderConnector108; CsReaderConnector csReaderConnector;
    public RfidReader(Context context, Utility utility, CsReaderConnector108 csReaderConnector108, CsReaderConnector csReaderConnector) {
        this.context = context;
        this.utility = utility;
        this.csReaderConnector108 = csReaderConnector108;
        this.csReaderConnector = csReaderConnector;

        rfidConnector = new RfidConnector(context, utility); mRfidToWrite = rfidConnector.mRfidToWrite;
        if (csReaderConnector108 != null) {
            rfidReaderChipR2000 = new RfidReaderChipR2000(context, utility, csReaderConnector108);
        } else {
            rfidReaderChip = new RfidReaderChipE710(context, utility, csReaderConnector);
            rfidConnector.rfidConnectorCallback = new RfidConnector.RfidConnectorCallback(){
                @Override
                public boolean callbackMethod(byte[] dataValues) {
                    return rfidReaderChip.decode710Data(dataValues);
                }
            };
        }
    }
    private void appendToLog(String s) { utility.appendToLog(s); }

    public boolean getRfidOnStatus() {
        return rfidConnector.getOnStatus();
    }
    public boolean isRfidFailure() {
        return rfidConnector.rfidFailure;
    }
    public int tagFocus = -1;
    public int getTagFocus() {
        if (csReaderConnector108 != null) {
            tagFocus = rfidReaderChipR2000.rx000Setting.getImpinjExtension();
            if (tagFocus > 0) tagFocus = ((tagFocus & 0x10) >> 4);
        } else {
            tagFocus = rfidReaderChip.rx000Setting.getImpinjExtension() & 0x04;
        }
        return tagFocus;
    }
    public boolean setTagFocus(boolean tagFocusNew) {
        boolean bRetValue;
        if (csReaderConnector108 != null) {
            bRetValue = rfidReaderChipR2000.rx000Setting.setImpinjExtension(tagFocusNew, (fastId > 0 ? true : false));
        } else {
            bRetValue = rfidReaderChip.rx000Setting.setImpinjExtension(tagFocusNew, (fastId > 0 ? true : false));
        }
        if (bRetValue) tagFocus = (tagFocusNew ? 1 : 0);
        return bRetValue;
    }
    public int fastId = -1;
    public int getFastId() {
        if (rfidReaderChipR2000 != null) {
            fastId = rfidReaderChipR2000.rx000Setting.getImpinjExtension();
            if (fastId > 0) fastId = ((fastId & 0x20) >> 5);
        } else {
            fastId = rfidReaderChip.rx000Setting.getImpinjExtension() & 0x02;
        }
        return fastId;
    }
    public boolean setFastId(boolean fastIdNew) {
        boolean bRetValue;
        if (csReaderConnector108 != null) {
            bRetValue = rfidReaderChipR2000.rx000Setting.setImpinjExtension((csReaderConnector108.rfidReader.tagFocus > 0 ? true : false), fastIdNew);
        } else {
            bRetValue = rfidReaderChip.rx000Setting.setImpinjExtension((csReaderConnector.rfidReader.tagFocus > 0 ? true : false), fastIdNew);
        }
        if (bRetValue) fastId = (fastIdNew ? 1 : 0);
        return bRetValue;
    }
    void macRead(int address) {
        if (rfidReaderChipR2000 != null) rfidReaderChipR2000.rx000Setting.readMAC(address);
        else rfidReaderChip.rx000Setting.readMAC(address);
    }
    public boolean macWrite(int address, long value) {
        if (rfidReaderChipR2000 != null) return rfidReaderChipR2000.rx000Setting.writeMAC(address, value);
        else return rfidReaderChip.rx000Setting.writeMAC(address, value);
    }
    public void set_fdCmdCfg(int value) {
        macWrite(0x117, value);
    }
    public void set_fdRegAddr(int addr) {
        macWrite(0x118, addr);
    }
    public void set_fdWrite(int addr, long value) {
        macWrite(0x118, addr);
        macWrite(0x119, value);
    }
    public void set_fdPwd(int value) {
        macWrite(0x11A, value);
    }
    public void set_fdBlockAddr4GetTemperature(int addr) {
        macWrite(0x11b, addr);
    }
    public void set_fdReadMem(int addr, long len) {
        macWrite(0x11c, addr);
        macWrite(0x11d, len);
    }
    public void set_fdWriteMem(int addr, int len, long value) {
        set_fdReadMem(addr, len);
        macWrite(0x11e, value);
    }
    public void setImpinJExtension(boolean tagFocus, boolean fastId) {
        if (rfidReaderChipR2000 != null) {
            int iValue = 0;
            if (tagFocus) iValue |= 0x10;
            if (fastId) iValue |= 0x20;
            boolean bRetValue;
            bRetValue = macWrite(0x203, iValue);
        } else {
            boolean bRetValue;
            bRetValue = rfidReaderChip.rx000Setting.setImpinjExtension(tagFocus, fastId);
            if (bRetValue) this.tagFocus = (tagFocus ? 1 : 0);
        }
    }
}
