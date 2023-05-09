package com.csl.cs710library4a;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.csl.cs108library4a.Cs108Library4A;

import java.util.List;

public class CsLibrary4A {
    String stringVersion = "4.8.0";
    Utility utility;
    Cs710Library4A cs710Library4A;
    com.csl.cs108library4a.Cs108Library4A cs108Library4A;

    public CsLibrary4A(Context context, TextView mLogView) {
        utility = new Utility(context, mLogView);
        cs710Library4A = new Cs710Library4A(context, mLogView);
        cs108Library4A = new Cs108Library4A(context, mLogView);
        stringNOTCONNECT = " is called before Connection !!!";
        dBuV_dBm_constant = cs108Library4A.dBuV_dBm_constant;
        iNO_SUCH_SETTING = cs108Library4A.iNO_SUCH_SETTING;
    } //41

    public String getlibraryVersion() { //called before connection
        if (false) Log.i("Hello2", "getlibraryVersion");
        return stringVersion;
    } //152

    public String byteArrayToString(byte[] packet) { //called before connection
        if (false) Log.i("Hello2", "byteArrayToString");
        return utility.byteArrayToString(packet);
    } //156

    public void appendToLog(String s) { //called before connection
        if (false) Log.i("Hello2", "appendToLog");
        utility.appendToLog(s);
    } //160

    public void appendToLogView(String s) {
        if (false) Log.i("Hello2", "appendToLogView");
        utility.appendToLogView(s);
    } //164

    public boolean isBleScanning() { //called before connection
        boolean bValue = false, bValue1 = false, bValue7 = false;
        bValue1 = cs108Library4A.isBleScanning();
        bValue7 = cs710Library4A.isBleScanning();
        if (bValue1 && bValue7) bValue = true;
        else if (bValue1 == false && bValue7 == false) { }
        else Log.i("Hello2", "isBleScanning: bVAlue1 = " + bValue1 + ", bValue7 = " + bValue7);
        if (false) Log.i("Hello2", "isBleScanning");
        return bValue;
    } //170

    public boolean scanLeDevice(final boolean enable) { //called before connection
        boolean bValue = false, bValue1 = false, bValue7 = false;
        bValue1 = cs108Library4A.scanLeDevice(enable);
        bValue7 = cs710Library4A.scanLeDevice(enable);
        if (bValue1 && bValue7) bValue = true;
        else if (bValue1 == false && bValue7 == false) { }
        else Log.i("Hello2", "scanLeDevice: bValue1 = " + bValue1 + ", bValue7 = " + bValue7);
        if (false) Log.i("Hello2", "scanLeDevice");
        return bValue;
    } //177

    public String getBluetoothDeviceAddress() {
        if (isCs108Connected()) return cs108Library4A.getBluetoothDeviceAddress();
        else if (isCs710Connected()) return cs710Library4A.getBluetoothDeviceAddress();
        else Log.i("Hello2", "getBluetoothDeviceAddress" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getBluetoothDeviceAddress");
        return null;
    } //233

    int bConnectStatus = 0;
    private boolean isCs108Connected() { return (bConnectStatus == 1); }
    private boolean isCs710Connected() { return (bConnectStatus == 7); }
    public boolean isBleConnected() { //called before connection
        boolean bValue = false;
        if (isCs108Connected()) {
            bValue = cs108Library4A.isBleConnected();
            if (bValue == false) bConnectStatus = 0;
        } else if (isCs710Connected()) {
            bValue = cs710Library4A.isBleConnected();
            if (bValue == false) bConnectStatus = 0;
        } else {
            bValue = cs108Library4A.isBleConnected();
            if (bValue) bConnectStatus = 1;
            else {
                bValue = cs710Library4A.isBleConnected();
                if (bValue) bConnectStatus = 7;
                else bConnectStatus = 0;
            }
        }
        if (false) Log.i("Hello2", "isBleConnected");
        return bValue;
    } //238

    public void connect(ReaderDevice readerDevice) { //called before connection
        if (readerDevice.getServiceUUID2p1() == 0) {
            com.csl.cs108library4a.ReaderDevice readerDevice1 = new com.csl.cs108library4a.ReaderDevice(
                    readerDevice.getName(), readerDevice.getAddress(), readerDevice.getSelected(),
                    readerDevice.getDetails(), readerDevice.getCount(), readerDevice.getRssi(),
                    readerDevice.getServiceUUID2p1());
            cs108Library4A.connect(readerDevice1);
        } else if (readerDevice.getServiceUUID2p1() == 2) cs710Library4A.connect(readerDevice);
        else appendToLog("invalid serviceUUID = " + readerDevice.getServiceUUID2p1());
        if (false) Log.i("Hello2", "connect");
    } //334

    public void disconnect(boolean tempDisconnect) { //called before connection
        if (isCs108Connected()) cs108Library4A.disconnect(tempDisconnect);
        else if (isCs710Connected()) cs710Library4A.disconnect(tempDisconnect);
        if (false) Log.i("Hello2", "disconnect");
    } //357

    public String checkVersion() {
        if (isCs108Connected()) return cs108Library4A.checkVersion();
        else if (isCs710Connected()) return cs710Library4A.checkVersion();
        else Log.i("Hello2", "checkVersion" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "checkVersion");
        return null;
    } //392

    public int getRssi() {
        if (isCs108Connected()) return cs108Library4A.getRssi();
        else if (isCs710Connected()) return cs710Library4A.getRssi();
        else Log.i("Hello2", "getRssi" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getRssi");
        return -1;
    } //411

    public long getStreamInRate() {
        if (isCs108Connected()) return cs108Library4A.getStreamInRate();
        else if (isCs710Connected()) return cs710Library4A.getStreamInRate();
        else Log.i("Hello2", "getStreamInRate" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getStreamInRate");
        return -1;
    } //414

    public long getTagRate() {
        if (isCs108Connected()) return cs108Library4A.getTagRate();
        else if (isCs710Connected()) return cs710Library4A.getTagRate();
        else Log.i("Hello2", "getTagRate" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getTagRate");
        return -1;
    } //415

    public boolean getRfidOnStatus() {
        if (isCs108Connected()) return cs108Library4A.getRfidOnStatus();
        else if (isCs710Connected()) return cs710Library4A.getRfidOnStatus();
        else Log.i("Hello2", "getRfidOnStatus" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getRfidOnStatus");
        return false;
    } //417

    public boolean isBarcodeFailure() {
        if (isCs108Connected()) return cs108Library4A.isBarcodeFailure();
        else if (isCs710Connected()) return cs710Library4A.isBarcodeFailure();
        else Log.i("Hello2", "isBarcodeFailure" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "isBarcodeFailure");
        return false;
    } //420

    public boolean isRfidFailure() { //called before connection
        if (isCs108Connected()) return cs108Library4A.isRfidFailure();
        else if (isCs710Connected()) return cs710Library4A.isRfidFailure();
        if (false) Log.i("Hello2", "isRfidFailure");
        return false;
    } //421

    public void setSameCheck(boolean sameCheck1) {
        if (isCs108Connected()) cs108Library4A.setSameCheck(sameCheck1);
        else if (isCs710Connected()) cs710Library4A.setSameCheck(sameCheck1);
        else Log.i("Hello2", "setSameCheck" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setSameCheck");
    } //423

    public void setReaderDefault() {
        if (isCs108Connected()) cs108Library4A.setReaderDefault();
        else if (isCs710Connected()) cs710Library4A.setReaderDefault();
        else Log.i("Hello2", "setReaderDefault" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setReaderDefault");
    } //429

    public void saveSetting2File() {
        if (isCs108Connected()) cs108Library4A.saveSetting2File();
        else if (isCs710Connected()) cs710Library4A.saveSetting2File();
        else Log.i("Hello2", "saveSetting2File" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "saveSetting2File");
    } //667

    public String getMacVer() {
        if (isCs108Connected()) return cs108Library4A.getMacVer();
        else if (isCs710Connected()) return cs710Library4A.getMacVer();
        else Log.i("Hello2", "getMacVer" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getMacVer");
        return null;
    } //738

    public int getPortNumber() {
        if (isCs108Connected()) return cs108Library4A.getPortNumber();
        else if (isCs710Connected()) return cs710Library4A.getPortNumber();
        else Log.i("Hello2", "getPortNumber" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getPortNumber");
        return -1;
    } //754

    public int getAntennaSelect() {
        if (isCs108Connected()) return cs108Library4A.getAntennaSelect();
        else if (isCs710Connected()) return cs710Library4A.getAntennaSelect();
        else Log.i("Hello2", "getAntennaSelect" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getAntennaSelect");
        return -1;
    } //758

    public boolean setAntennaSelect(int number) {
        if (isCs108Connected()) return cs108Library4A.setAntennaSelect(number);
        else if (isCs710Connected()) return cs710Library4A.setAntennaSelect(number);
        else Log.i("Hello2", "setAntennaSelect" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setAntennaSelect");
        return false;
    } //765

    public boolean getAntennaEnable() {
        if (isCs108Connected()) return cs108Library4A.getAntennaEnable();
        else if (isCs710Connected()) return cs710Library4A.getAntennaEnable();
        else Log.i("Hello2", "getAntennaEnable" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getAntennaEnable");
        return false;
    } //771

    public boolean setAntennaEnable(boolean enable) {
        Log.i("Hello2", "setAntennaEnable");
        return false; } //779

    public long getAntennaDwell() {
        if (isCs108Connected()) return cs108Library4A.getAntennaDwell();
        else if (isCs710Connected()) return cs710Library4A.getAntennaDwell();
        else Log.i("Hello2", "getAntennaDwell" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getAntennaDwell");
        return -1;
    } //789

    public boolean setAntennaDwell(long antennaDwell) {
        if (isCs108Connected()) return cs108Library4A.setAntennaDwell(antennaDwell);
        else if (isCs710Connected()) return cs710Library4A.setAntennaDwell(antennaDwell);
        else Log.i("Hello2", "setAntennaDwell" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setAntennaDwell");
        return false;
    } //796

    public long getPwrlevel() {
        if (isCs108Connected()) return cs108Library4A.getPwrlevel();
        else if (isCs710Connected()) return cs710Library4A.getPwrlevel();
        else Log.i("Hello2", "getPwrlevel" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getPwrlevel");
        return -1;
    } //803

    public boolean setPowerLevel(long pwrlevel) {
        if (isCs108Connected()) return cs108Library4A.setPowerLevel(pwrlevel);
        else if (isCs710Connected()) return cs710Library4A.setPowerLevel(pwrlevel);
        else Log.i("Hello2", "setPowerLevel" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setPowerLevel");
        return false;
    } //811

    public int getQueryTarget() {
        if (isCs108Connected()) return cs108Library4A.getQueryTarget();
        else if (isCs710Connected()) return cs710Library4A.getQueryTarget();
        else Log.i("Hello2", "getQueryTarget" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getQueryTarget");
        return -1;
    } //821

    public int getQuerySession() {
        if (isCs108Connected()) return cs108Library4A.getQuerySession();
        else if (isCs710Connected()) return cs710Library4A.getQuerySession();
        else Log.i("Hello2", "getQuerySession" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getQuerySession");
        return -1;
    } //829

    public int getQuerySelect() {
        if (isCs108Connected()) return cs108Library4A.getQuerySelect();
        else if (isCs710Connected()) return cs710Library4A.getQuerySelect();
        else Log.i("Hello2", "getQuerySelect" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getQuerySelect");
        return -1;
    } //833

    public boolean setTagGroup(int sL, int session, int target1) {
        if (isCs108Connected()) return cs108Library4A.setTagGroup(sL, session, target1);
        else if (isCs710Connected()) return cs710Library4A.setTagGroup(sL, session, target1);
        else Log.i("Hello2", "setTagGroup" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setTagGroup");
        return false;
    } //836

    public int getTagFocus() {
        if (isCs108Connected()) return cs108Library4A.getTagFocus();
        else if (isCs710Connected()) return cs710Library4A.getTagFocus();
        else Log.i("Hello2", "getTagFocus" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getTagFocus");
        return -1;
    } //842

    public boolean setTagFocus(boolean tagFocusNew) {
        if (isCs108Connected()) return cs108Library4A.setTagFocus(tagFocusNew);
        else if (isCs710Connected()) return cs710Library4A.setTagFocus(tagFocusNew);
        else Log.i("Hello2", "setTagFocus" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setTagFocus");
        return false;
    } //849

    public boolean getInvAlgo() {
        if (isCs108Connected()) return cs108Library4A.getInvAlgo();
        else if (isCs710Connected()) return cs710Library4A.getInvAlgo();
        else Log.i("Hello2", "getInvAlgo" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getInvAlgo");
        return false;
    } //857

    public boolean setInvAlgo(boolean dynamicAlgo) {
        if (isCs108Connected()) return cs108Library4A.setInvAlgo(dynamicAlgo);
        else if (isCs710Connected()) return cs710Library4A.setInvAlgo(dynamicAlgo);
        else Log.i("Hello2", "setInvAlgo" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setInvAlgo");
        return false;
    } //861

    public List<String> getProfileList() {
        if (isCs108Connected()) return cs108Library4A.getProfileList();
        else if (isCs710Connected()) return cs710Library4A.getProfileList();
        else Log.i("Hello2", "getProfileList" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getProfileList");
        return null;
    } //900

    public int getCurrentProfile() {
        if (isCs108Connected()) return cs108Library4A.getCurrentProfile();
        else if (isCs710Connected()) return cs710Library4A.getCurrentProfile();
        else Log.i("Hello2", "getCurrentProfile" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getCurrentProfile");
        return -1;
    } //905

    public boolean setCurrentLinkProfile(int profile) {
        if (isCs108Connected()) return cs108Library4A.setCurrentLinkProfile(profile);
        else if (isCs710Connected()) return cs710Library4A.setCurrentLinkProfile(profile);
        else Log.i("Hello2", "setCurrentLinkProfile" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setCurrentLinkProfile");
        return false;
    } //930

    public void resetEnvironmentalRSSI() {
        if (isCs108Connected()) cs108Library4A.resetEnvironmentalRSSI();
        else if (isCs710Connected()) cs710Library4A.resetEnvironmentalRSSI();
        else Log.i("Hello2", "resetEnvironmentalRSSI" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "resetEnvironmentalRSSI");
    } //954

    public String getEnvironmentalRSSI() {
        if (isCs108Connected()) return cs108Library4A.getEnvironmentalRSSI();
        else if (isCs710Connected()) return cs710Library4A.getEnvironmentalRSSI();
        else Log.i("Hello2", "getEnvironmentalRSSI" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getEnvironmentalRSSI");
        return null;
    } //955

    public int getHighCompression() {
        if (isCs108Connected()) return cs108Library4A.getHighCompression();
        else if (isCs710Connected()) return cs710Library4A.getHighCompression();
        else Log.i("Hello2", "getHighCompression" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getHighCompression");
        return -1;
    } //969

    public int getRflnaGain() {
        if (isCs108Connected()) return cs108Library4A.getRflnaGain();
        else if (isCs710Connected()) return cs710Library4A.getRflnaGain();
        else Log.i("Hello2", "getRflnaGain" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getRflnaGain");
        return -1;
    } //972

    public int getIflnaGain() {
        if (isCs108Connected()) return cs108Library4A.getIflnaGain();
        else if (isCs710Connected()) return cs710Library4A.getIflnaGain();
        else Log.i("Hello2", "getIflnaGain" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getIflnaGain");
        return -1;
    } //973

    public int getAgcGain() {
        if (isCs108Connected()) return cs108Library4A.getAgcGain();
        else if (isCs710Connected()) return cs710Library4A.getAgcGain();
        else Log.i("Hello2", "getAgcGain" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getAgcGain");
        return -1;
    } //976

    public boolean setRxGain(int highCompression, int rflnagain, int iflnagain, int agcgain) {
        if (isCs108Connected()) return cs108Library4A.setRxGain(highCompression, rflnagain, iflnagain, agcgain);
        else if (isCs710Connected()) return cs710Library4A.setRxGain(highCompression, rflnagain, iflnagain, agcgain);
        else Log.i("Hello2", "setRxGain" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setRxGain");
        return false;
    } //978
    
    public int FreqChnCnt() {
        if (isCs108Connected()) return cs108Library4A.FreqChnCnt();
        else if (isCs710Connected()) return cs710Library4A.FreqChnCnt();
        else Log.i("Hello2", "FreqChnCnt" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "FreqChnCnt");
        return -1;
    } //1806

    public double getLogicalChannel2PhysicalFreq(int channel) {
        if (isCs108Connected()) return cs108Library4A.getLogicalChannel2PhysicalFreq(channel);
        else if (isCs710Connected()) return cs710Library4A.getLogicalChannel2PhysicalFreq(channel);
        else Log.i("Hello2", "getLogicalChannel2PhysicalFreq" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getLogicalChannel2PhysicalFreq");
        return -1;
    } //2003

    public byte getTagDelay() {
        if (isCs108Connected()) return cs108Library4A.getTagDelay();
        else if (isCs710Connected()) return cs710Library4A.getTagDelay();
        else Log.i("Hello2", "getTagDelay" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getTagDelay");
        return -1;
    } //2046

    public boolean setTagDelay(byte tagDelay) {
        if (isCs108Connected()) return cs108Library4A.setTagDelay(tagDelay);
        else if (isCs710Connected()) return cs710Library4A.setTagDelay(tagDelay);
        else Log.i("Hello2", "setTagDelay" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setTagDelay");
        return false;
    } //2050

    public boolean setIntraPkDelay(byte intraPkDelay) {
        if (isCs108Connected()) return cs108Library4A.setIntraPkDelay(intraPkDelay);
        else if (isCs710Connected()) return cs710Library4A.setIntraPkDelay(intraPkDelay);
        else Log.i("Hello2", "setIntraPkDelay" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setIntraPkDelay");
        return false;
    } //2056

    public boolean setDupDelay(byte dupElim) {
        if (isCs108Connected()) return cs108Library4A.setDupDelay(dupElim);
        else if (isCs710Connected()) return cs710Library4A.setDupDelay(dupElim);
        else Log.i("Hello2", "setDupDelay" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setDupDelay");
        return false;
    } //2058

    public long getCycleDelay() {
        if (isCs108Connected()) return cs108Library4A.getCycleDelay();
        else if (isCs710Connected()) return cs710Library4A.getCycleDelay();
        else Log.i("Hello2", "getCycleDelay" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getCycleDelay");
        return -1;
    } //2060

    public boolean setCycleDelay(long cycleDelay) {
        if (isCs108Connected()) return cs108Library4A.setCycleDelay(cycleDelay);
        else if (isCs710Connected()) return cs710Library4A.setCycleDelay(cycleDelay);
        if (false) Log.i("Hello2", "setCycleDelay");
        return false;
    } //2064

    public void getAuthenticateReplyLength() {
        if (isCs108Connected()) cs108Library4A.getAuthenticateReplyLength();
        else if (isCs710Connected()) cs710Library4A.getAuthenticateReplyLength();
        else Log.i("Hello2", "getAuthenticateReplyLength" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getAuthenticateReplyLength");
    } //2069

    public boolean setTam1Configuration(int keyId, String matchData) {
        Log.i("Hello2", "setTam1Configuration");
        return false; } //2072
    public boolean setTam2Configuration(int keyId, String matchData, int profile, int offset, int blockId, int protMode) {
        Log.i("Hello2", "setTam2Configuration");
        return false; } //2085

    public int getUntraceableEpcLength() {
        if (isCs108Connected()) return cs108Library4A.getUntraceableEpcLength();
        else if (isCs710Connected()) return cs710Library4A.getUntraceableEpcLength();
        else Log.i("Hello2", "getUntraceableEpcLength" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getUntraceableEpcLength");
        return -1;
    } //2124

    public boolean setUntraceable(boolean bHideEpc, int ishowEpcSize, int iHideTid, boolean bHideUser, boolean bHideRange) {
        Log.i("Hello2", "setUntraceable 1");
        return false; } //2127
    public boolean setUntraceable(int range, boolean user, int tid, int epcLength, boolean epc, boolean uxpc) {
        Log.i("Hello2", "setUntraceable 2");
        return false; } //2130

    public int getBeepCount() {
        if (isCs108Connected()) return cs108Library4A.getBeepCount();
        else if (isCs710Connected()) return cs710Library4A.getBeepCount();
        else Log.i("Hello2", "getBeepCount" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getBeepCount");
        return -1;
    } //2135

    public boolean setBeepCount(int beepCount) {
        if (isCs108Connected()) return cs108Library4A.setBeepCount(beepCount);
        else if (isCs710Connected()) return cs710Library4A.setBeepCount(beepCount);
        else Log.i("Hello2", "setBeepCount" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setBeepCount");
        return false;
    } //2138

    public boolean getInventoryBeep() {
        if (isCs108Connected()) return cs108Library4A.getInventoryBeep();
        else if (isCs710Connected()) return cs710Library4A.getInventoryBeep();
        else Log.i("Hello2", "getInventoryBeep" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getInventoryBeep");
        return false;
    } //2144

    public boolean setInventoryBeep(boolean inventoryBeep) {
        if (isCs108Connected()) return cs108Library4A.setInventoryBeep(inventoryBeep);
        else if (isCs710Connected()) return cs710Library4A.setInventoryBeep(inventoryBeep);
        else Log.i("Hello2", "setInventoryBeep" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setInventoryBeep");
        return false;
    } //2145

    public boolean getInventoryVibrate() {
        if (isCs108Connected()) return cs108Library4A.getInventoryVibrate();
        else if (isCs710Connected()) return cs710Library4A.getInventoryVibrate();
        else Log.i("Hello2", "getInventoryVibrate" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getInventoryVibrate");
        return false;
    } //2151

    public boolean setInventoryVibrate(boolean inventoryVibrate) {
        if (isCs108Connected()) return cs108Library4A.setInventoryVibrate(inventoryVibrate);
        else if (isCs710Connected()) return cs710Library4A.setInventoryVibrate(inventoryVibrate);
        else Log.i("Hello2", "setInventoryVibrate" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setInventoryVibrate");
        return false;
    } //2152

    public int getVibrateTime() {
        if (isCs108Connected()) return cs108Library4A.getVibrateTime();
        else if (isCs710Connected()) return cs710Library4A.getVibrateTime();
        else Log.i("Hello2", "getVibrateTime" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getVibrateTime");
        return -1;
    } //2158

    public boolean setVibrateTime(int vibrateTime) {
        if (isCs108Connected()) return cs108Library4A.setVibrateTime(vibrateTime);
        else if (isCs710Connected()) return cs710Library4A.setVibrateTime(vibrateTime);
        else Log.i("Hello2", "setVibrateTime" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setVibrateTime");
        return false;
    } //2161

    public int getVibrateWindow() {
        if (isCs108Connected()) return cs108Library4A.getVibrateWindow();
        else if (isCs710Connected()) return cs710Library4A.getVibrateWindow();
        else Log.i("Hello2", "getVibrateWindow" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getVibrateWindow");
        return -1;
    } //2167

    public boolean setVibrateWindow(int vibrateWindow) {
        if (isCs108Connected()) return cs108Library4A.setVibrateWindow(vibrateWindow);
        else if (isCs710Connected()) return cs710Library4A.setVibrateWindow(vibrateWindow);
        else Log.i("Hello2", "setVibrateWindow" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setVibrateWindow");
        return false;
    } //2170

    public boolean getSaveFileEnable() {
        if (isCs108Connected()) return cs108Library4A.getSaveFileEnable();
        else if (isCs710Connected()) return cs710Library4A.getSaveFileEnable();
        else Log.i("Hello2", "getSaveFileEnable" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getSaveFileEnable");
        return false;
    } //2176

    public boolean setSaveFileEnable(boolean saveFileEnable) {
        if (isCs108Connected()) return cs108Library4A.setSaveFileEnable(saveFileEnable);
        else if (isCs710Connected()) return cs710Library4A.setSaveFileEnable(saveFileEnable);
        else Log.i("Hello2", "setSaveFileEnable" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setSaveFileEnable");
        return false;
    } //2177

    public boolean getSaveCloudEnable() {
        if (isCs108Connected()) return cs108Library4A.getSaveCloudEnable();
        else if (isCs710Connected()) return cs710Library4A.getSaveCloudEnable();
        else Log.i("Hello2", "getSaveCloudEnable" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getSaveCloudEnable");
        return false;
    } //2185

    public boolean setSaveCloudEnable(boolean saveCloudEnable) {
        if (isCs108Connected()) return cs108Library4A.setSaveCloudEnable(saveCloudEnable);
        else if (isCs710Connected()) return cs710Library4A.setSaveCloudEnable(saveCloudEnable);
        else Log.i("Hello2", "setSaveCloudEnable" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setSaveCloudEnable");
        return false;
    } //2186

    public boolean getSaveNewCloudEnable() {
        if (isCs108Connected()) return cs108Library4A.getSaveNewCloudEnable();
        else if (isCs710Connected()) return cs710Library4A.getSaveNewCloudEnable();
        else Log.i("Hello2", "getSaveNewCloudEnable" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getSaveNewCloudEnable");
        return false;
    } //2192

    public boolean setSaveNewCloudEnable(boolean saveNewCloudEnable) {
        Log.i("Hello2", "setSaveNewCloudEnable");
        return false; } //2193

    public boolean getSaveAllCloudEnable() {
        if (isCs108Connected()) return cs108Library4A.getSaveAllCloudEnable();
        else if (isCs710Connected()) return cs710Library4A.getSaveAllCloudEnable();
        else Log.i("Hello2", "getSaveAllCloudEnable" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getSaveAllCloudEnable");
        return false;
    } //2198

    public boolean setSaveAllCloudEnable(boolean saveAllCloudEnable) {
        Log.i("Hello2", "setSaveAllCloudEnable");
        return false; } //2199

    public String getServerLocation() {
        if (isCs108Connected()) return cs108Library4A.getServerLocation();
        else if (isCs710Connected()) return cs710Library4A.getServerLocation();
        else Log.i("Hello2", "getServerLocation" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getServerLocation");
        return null;
    } //2207

    public boolean setServerLocation(String serverLocation) {
        if (isCs108Connected()) return cs108Library4A.setServerLocation(serverLocation);
        else if (isCs710Connected()) return cs710Library4A.setServerLocation(serverLocation);
        else Log.i("Hello2", "setServerLocation" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setServerLocation");
        return false;
    } //2210

    public int getServerTimeout() {
        if (isCs108Connected()) return cs108Library4A.getServerTimeout();
        else if (isCs710Connected()) return cs710Library4A.getServerTimeout();
        else Log.i("Hello2", "getServerTimeout" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getServerTimeout");
        return -1;
    } //2216

    public boolean setServerTimeout(int serverTimeout) {
        if (isCs108Connected()) return cs108Library4A.setServerTimeout(serverTimeout);
        else if (isCs710Connected()) return cs710Library4A.setServerTimeout(serverTimeout);
        else Log.i("Hello2", "setServerTimeout" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setServerTimeout");
        return false;
    } //2217

    public int getRetryCount() {
        if (isCs108Connected()) return cs108Library4A.getRetryCount();
        else if (isCs710Connected()) return cs710Library4A.getRetryCount();
        else Log.i("Hello2", "getRetryCount" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getRetryCount");
        return -1;
    } //2232

    public boolean setRetryCount(int retryCount) {
        if (isCs108Connected()) return cs108Library4A.setRetryCount(retryCount);
        else if (isCs710Connected()) return cs710Library4A.setRetryCount(retryCount);
        else Log.i("Hello2", "setRetryCount" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setRetryCount");
        return false;
    } //2240

    public byte getIntraPkDelay() {
        if (isCs108Connected()) return cs108Library4A.getIntraPkDelay();
        else if (isCs710Connected()) return cs710Library4A.getIntraPkDelay();
        else Log.i("Hello2", "getIntraPkDelay" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getIntraPkDelay");
        return -1;
    } //2255

    public byte getDupDelay() {
        if (isCs108Connected()) return cs108Library4A.getDupDelay();
        else if (isCs710Connected()) return cs710Library4A.getDupDelay();
        else Log.i("Hello2", "getDupDelay" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getDupDelay");
        return -1;
    } //2257

    public int getInvSelectIndex() {
        if (isCs108Connected()) return cs108Library4A.getInvSelectIndex();
        else if (isCs710Connected()) return cs710Library4A.getInvSelectIndex();
        else Log.i("Hello2", "getInvSelectIndex" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getInvSelectIndex");
        return -1;
    } //2286

    public boolean getSelectEnable() {
        if (isCs108Connected()) return cs108Library4A.getSelectEnable();
        else if (isCs710Connected()) return cs710Library4A.getSelectEnable();
        else Log.i("Hello2", "getSelectEnable" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getSelectEnable");
        return false;
    } //2289

    public int getSelectTarget() {
        if (isCs108Connected()) return cs108Library4A.getSelectTarget();
        else if (isCs710Connected()) return cs710Library4A.getSelectTarget();
        else Log.i("Hello2", "getSelectTarget" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getSelectTarget");
        return -1;
    } //2294

    public int getSelectAction() {
        if (isCs108Connected()) return cs108Library4A.getSelectAction();
        else if (isCs710Connected()) return cs710Library4A.getSelectAction();
        else Log.i("Hello2", "getSelectAction" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getSelectAction");
        return -1;
    } //2297

    public int getSelectMaskBank() {
        if (isCs108Connected()) return cs108Library4A.getSelectMaskBank();
        else if (isCs710Connected()) return cs710Library4A.getSelectMaskBank();
        else Log.i("Hello2", "getSelectMaskBank" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getSelectMaskBank");
        return -1;
    } //2300

    public int getSelectMaskOffset() {
        if (isCs108Connected()) return cs108Library4A.getSelectMaskOffset();
        else if (isCs710Connected()) return cs710Library4A.getSelectMaskOffset();
        else Log.i("Hello2", "getSelectMaskOffset" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getSelectMaskOffset");
        return -1;
    } //2303

    public String getSelectMaskData() {
        if (isCs108Connected()) return cs108Library4A.getSelectMaskData();
        else if (isCs710Connected()) return cs710Library4A.getSelectMaskData();
        else Log.i("Hello2", "getSelectMaskData" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getSelectMaskData");
        return null;
    } //2306

    public boolean setInvSelectIndex(int invSelect) {
        Log.i("Hello2", "setInvSelectIndex");
        return false; } //2317

    public boolean setSelectCriteriaDisable(int index) {
        if (isCs108Connected()) return cs108Library4A.setSelectCriteriaDisable(index);
        else if (isCs710Connected()) return cs710Library4A.setSelectCriteriaDisable(index);
        else Log.i("Hello2", "setSelectCriteriaDisable" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setSelectCriteriaDisable");
        return false;
    } //2351

    public boolean setSelectCriteria(int index, boolean enable, int target, int action, int bank, int offset, String mask, boolean maskbit) {
        if (isCs108Connected()) return cs108Library4A.setSelectCriteria(index, enable, target, action, bank, offset, mask, maskbit);
        else if (isCs710Connected()) return cs710Library4A.setSelectCriteria(index, enable, target, action, bank, offset, mask, maskbit);
        else Log.i("Hello2", "setSelectCriteria 1" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setSelectCriteria 1");
        return false;
    } //2355

    public boolean setSelectCriteria(int index, boolean enable, int target, int action, int delay, int bank, int offset, String mask) {
        Log.i("Hello2", "setSelectCriteria 2");
        return false; } //2378

    public boolean getRssiFilterEnable() {
        if (isCs108Connected()) return cs108Library4A.getRssiFilterEnable();
        else if (isCs710Connected()) return cs710Library4A.getRssiFilterEnable();
        else Log.i("Hello2", "getRssiFilterEnable" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getRssiFilterEnable");
        return false;
    } //2452

    public int getRssiFilterType() {
        if (isCs108Connected()) return cs108Library4A.getRssiFilterType();
        else if (isCs710Connected()) return cs710Library4A.getRssiFilterType();
        else Log.i("Hello2", "getRssiFilterType" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getRssiFilterType");
        return -1;
    } //2458

    public int getRssiFilterOption() {
        if (isCs108Connected()) return cs108Library4A.getRssiFilterOption();
        else if (isCs710Connected()) return cs710Library4A.getRssiFilterOption();
        if (false) Log.i("Hello2", "getRssiFilterOption");
        return -1;
    } //2465

    public boolean setRssiFilterConfig(boolean enable, int rssiFilterType, int rssiFilterOption) {
        if (isCs108Connected()) return cs108Library4A.setRssiFilterConfig(enable, rssiFilterType, rssiFilterOption);
        else if (isCs710Connected()) return cs710Library4A.setRssiFilterConfig(enable, rssiFilterType, rssiFilterOption);
        else Log.i("Hello2", "setRssiFilterConfig" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setRssiFilterConfig");
        return false;
    } //2471

    public double getRssiFilterThreshold1() {
        if (isCs108Connected()) return cs108Library4A.getRssiFilterThreshold1();
        else if (isCs710Connected()) return cs710Library4A.getRssiFilterThreshold1();
        else Log.i("Hello2", "getRssiFilterThreshold1" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getRssiFilterThreshold1");
        return -1;
    } //2477

    public double getRssiFilterThreshold2() {
        if (isCs108Connected()) return cs108Library4A.getRssiFilterThreshold2();
        else if (isCs710Connected()) return cs710Library4A.getRssiFilterThreshold2();
        else Log.i("Hello2", "getRssiFilterThreshold2" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getRssiFilterThreshold2");
        return -1;
    } //2488

    public boolean setRssiFilterThreshold(double rssiFilterThreshold1, double rssiFilterThreshold2) {
        if (isCs108Connected()) return cs108Library4A.setRssiFilterThreshold(rssiFilterThreshold1, rssiFilterThreshold2);
        else if (isCs710Connected()) return cs710Library4A.setRssiFilterThreshold(rssiFilterThreshold1, rssiFilterThreshold2);
        else Log.i("Hello2", "setRssiFilterThreshold" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setRssiFilterThreshold");
        return false;
    } //2495

    public long getRssiFilterCount() {
        if (isCs108Connected()) return cs108Library4A.getRssiFilterCount();
        else if (isCs710Connected()) return cs710Library4A.getRssiFilterCount();
        else Log.i("Hello2", "getRssiFilterCount" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getRssiFilterCount");
        return -1;
    } //2505

    public boolean setRssiFilterCount(long rssiFilterCount) {
        Log.i("Hello2", "setRssiFilterCount");
        return false; } //2508

    public boolean getInvMatchEnable() {
        if (isCs108Connected()) return cs108Library4A.getInvMatchEnable();
        else if (isCs710Connected()) return cs710Library4A.getInvMatchEnable();
        else Log.i("Hello2", "getInvMatchEnable" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getInvMatchEnable");
        return false;
    } //2513

    public boolean getInvMatchType() {
        if (isCs108Connected()) return cs108Library4A.getInvMatchType();
        else if (isCs710Connected()) return cs710Library4A.getInvMatchType();
        else Log.i("Hello2", "getInvMatchType" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getInvMatchType");
        return false;
    } //2516

    public int getInvMatchOffset() {
        if (isCs108Connected()) return cs108Library4A.getInvMatchOffset();
        else if (isCs710Connected()) return cs710Library4A.getInvMatchOffset();
        else Log.i("Hello2", "getInvMatchOffset" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getInvMatchOffset");
        return -1;
    } //2519

    public String getInvMatchData() {
        if (isCs108Connected()) return cs108Library4A.getInvMatchData();
        else if (isCs710Connected()) return cs710Library4A.getInvMatchData();
        else Log.i("Hello2", "getInvMatchData" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getInvMatchData");
        return null;
    } //2522

    public boolean setPostMatchCriteria(boolean enable, boolean target, int offset, String mask) {
        if (isCs108Connected()) return cs108Library4A.setPostMatchCriteria(enable, target, offset, mask);
        else if (isCs710Connected()) return cs710Library4A.setPostMatchCriteria(enable, target, offset, mask);
        else Log.i("Hello2", "setPostMatchCriteria" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setPostMatchCriteria");
        return false;
    } //2543

    public int mrfidToWriteSize() {
        if (isCs108Connected()) return cs108Library4A.mrfidToWriteSize();
        else if (isCs710Connected()) return cs710Library4A.mrfidToWriteSize();
        else Log.i("Hello2", "mrfidToWriteSize" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "mrfidToWriteSize");
        return -1;
    } //2550

    public void mrfidToWritePrint() {
        Log.i("Hello2", "mrfidToWritePrint");
    } //2553

    public boolean startOperation(Cs710Library4A.OperationTypes operationTypes) {
        if (isCs108Connected()) {
            Cs108Library4A.OperationTypes operationTypes1 = null;
            switch (operationTypes) {
                case TAG_RDOEM:
                    operationTypes1 = Cs108Library4A.OperationTypes.TAG_RDOEM;
                    break;
                case TAG_INVENTORY_COMPACT:
                    operationTypes1 = Cs108Library4A.OperationTypes.TAG_INVENTORY_COMPACT;
                    break;
                case TAG_INVENTORY:
                    operationTypes1 = Cs108Library4A.OperationTypes.TAG_INVENTORY;
                    break;
                case TAG_SEARCHING:
                    operationTypes1 = Cs108Library4A.OperationTypes.TAG_SEARCHING;
                    break;
            }
            return cs108Library4A.startOperation(operationTypes1);
        } else if (isCs710Connected()) return cs710Library4A.startOperation(operationTypes);
        else Log.i("Hello2", "startOperation" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "startOperation");
        return false;
    } //2563

    public boolean abortOperation() {
        if (isCs108Connected()) return cs108Library4A.abortOperation();
        else if (isCs710Connected()) return cs710Library4A.abortOperation();
        else Log.i("Hello2", "abortOperation" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "abortOperation");
        return false;
    } //2601

    public void restoreAfterTagSelect() {
        if (isCs108Connected()) cs108Library4A.restoreAfterTagSelect();
        else if (isCs710Connected()) cs710Library4A.restoreAfterTagSelect();
        else Log.i("Hello2", "restoreAfterTagSelect" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "restoreAfterTagSelect");
    } //2609

    public boolean setSelectedTagByTID(String strTagId, long pwrlevel) {
        Log.i("Hello2", "setSelectedTagByTID");
        return false; } //2647

    public boolean setSelectedTag(String strTagId, int selectBank, long pwrlevel) {
        if (isCs108Connected()) return cs108Library4A.setSelectedTag(strTagId, selectBank, pwrlevel);
        else if (isCs710Connected()) return cs710Library4A.setSelectedTag(strTagId, selectBank, pwrlevel);
        else Log.i("Hello2", "setSelectedTag 1" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setSelectedTag 1");
        return false;
    } //2651

    public boolean setSelectedTag(String selectMask, int selectBank, int selectOffset, long pwrlevel, int qValue, int matchRep) {
        if (isCs108Connected()) return cs108Library4A.setSelectedTag(selectMask, selectBank, selectOffset, pwrlevel, qValue, matchRep);
        else if (isCs710Connected()) return cs710Library4A.setSelectedTag(selectMask, selectBank, selectOffset, pwrlevel, qValue, matchRep);
        else Log.i("Hello2", "setSelectedTag 2" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setSelectedTag 2");
        return false;
    } //2851

    public boolean setMatchRep(int matchRep) {
        if (isCs108Connected()) return cs108Library4A.setMatchRep(matchRep);
        else if (isCs710Connected()) return cs710Library4A.setMatchRep(matchRep);
        else Log.i("Hello2", "setMatchRep" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setMatchRep");
        return false;
    } //2659

    public int getCountryNumberInList() {
        if (isCs108Connected()) return cs108Library4A.getCountryNumberInList();
        else if (isCs710Connected()) return cs710Library4A.getCountryNumberInList();
        else Log.i("Hello2", "getCountryNumberInList" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getCountryNumberInList");
        return -1;
    } //2949

    public String[] getCountryList() {
        if (isCs108Connected()) return cs108Library4A.getCountryList();
        else if (isCs710Connected()) return cs710Library4A.getCountryList();
        else Log.i("Hello2", "getCountryList" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getCountryList");
        return null;
    } //2950

    public boolean setCountryInList(int countryInList) {
        if (isCs108Connected()) return cs108Library4A.setCountryInList(countryInList);
        else if (isCs710Connected()) return cs710Library4A.setCountryInList(countryInList);
        else Log.i("Hello2", "setCountryInList" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setCountryInList");
        return false;
    } //3005

    public boolean getChannelHoppingStatus() {
        if (isCs108Connected()) return cs108Library4A.getChannelHoppingStatus();
        else if (isCs710Connected()) return cs710Library4A.getChannelHoppingStatus();
        else Log.i("Hello2", "getChannelHoppingStatus" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getChannelHoppingStatus");
        return false;
    } //3046

    public boolean setChannelHoppingStatus(boolean channelOrderHopping) {
        Log.i("Hello2", "setChannelHoppingStatus");
        return false; } //3061

    public int getChannel() {
        if (isCs108Connected()) return cs108Library4A.getChannel();
        else if (isCs710Connected()) return cs710Library4A.getChannel();
        else Log.i("Hello2", "getChannel" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getChannel");
        return -1;
    } //3083

    public boolean setChannel(int channelSelect) {
        if (isCs108Connected()) return cs108Library4A.setChannel(channelSelect);
        else if (isCs710Connected()) return cs710Library4A.setChannel(channelSelect);
        else Log.i("Hello2", "setChannel" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setChannel");
        return false;
    } //3094

    public byte getPopulation2Q(int population) {
        if (isCs108Connected()) return cs108Library4A.getPopulation2Q(population);
        else if (isCs710Connected()) return cs710Library4A.getPopulation2Q(population);
        else Log.i("Hello2", "getPopulation2Q" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getPopulation2Q");
        return -1;
    } //3339

    public int getPopulation() {
        if (isCs108Connected()) return cs108Library4A.getPopulation();
        else if (isCs710Connected()) return cs710Library4A.getPopulation();
        else Log.i("Hello2", "getPopulation" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getPopulation");
        return -1;
    } //3348

    public boolean setPopulation(int population) {
        if (isCs108Connected()) return cs108Library4A.setPopulation(population);
        else if (isCs710Connected()) return cs710Library4A.setPopulation(population);
        else Log.i("Hello2", "setPopulation" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setPopulation");
        return false;
    } //3349

    public byte getQValue() {
        if (isCs108Connected()) return cs108Library4A.getQValue();
        else if (isCs710Connected()) return cs710Library4A.getQValue();
        else Log.i("Hello2", "getQValue" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getQValue");
        return -1;
    } //3359

    public boolean setQValue(byte byteValue) {
        if (isCs108Connected()) return cs108Library4A.setQValue(byteValue);
        else if (isCs710Connected()) return cs710Library4A.setQValue(byteValue);
        else Log.i("Hello2", "setQValue" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setQValue");
        return false;
    } //3362

    public String getBarcodeDate() {
        if (isCs108Connected()) return cs108Library4A.getBarcodeDate();
        else if (isCs710Connected()) return cs710Library4A.getBarcodeDate();
        else Log.i("Hello2", "getBarcodeDate" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getBarcodeDate");
        return null;
    } //3375

    public String getRadioSerial() {
        if (isCs108Connected()) return cs108Library4A.getRadioSerial();
        else if (isCs710Connected()) return cs710Library4A.getRadioSerial();
        else Log.i("Hello2", "getRadioSerial" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getRadioSerial");
        return null;
    } //3377

    public String getRadioBoardVersion() {
        if (isCs108Connected()) return cs108Library4A.getRadioBoardVersion();
        else if (isCs710Connected()) return cs710Library4A.getRadioBoardVersion();
        else Log.i("Hello2", "getRadioBoardVersion" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getRadioBoardVersion");
        return null;
    } //3393

    public boolean getBarcodeOnStatus() {
        if (isCs108Connected()) return cs108Library4A.getBarcodeOnStatus();
        else if (isCs710Connected()) return cs710Library4A.getBarcodeOnStatus();
        else Log.i("Hello2", "getBarcodeOnStatus" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getBarcodeOnStatus");
        return false;
    } //3398

    public boolean setBarcodeOn(boolean on) {
        if (isCs108Connected()) return cs108Library4A.setBarcodeOn(on);
        else if (isCs710Connected()) return cs710Library4A.setBarcodeOn(on);
        else Log.i("Hello2", "setBarcodeOn" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setBarcodeOn");
        return false;
    } //3412

    public boolean setVibrateOn(int mode) {
        if (isCs108Connected()) return cs108Library4A.setVibrateOn(mode);
        else if (isCs710Connected()) return cs710Library4A.setVibrateOn(mode);
        else Log.i("Hello2", "setVibrateOn" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setVibrateOn");
        return false;
    } //3433

    public boolean barcodeSendCommandTrigger() {
        if (isCs108Connected()) return cs108Library4A.barcodeSendCommandTrigger();
        else if (isCs710Connected()) return cs710Library4A.barcodeSendCommandTrigger();
        else Log.i("Hello2", "barcodeSendCommandTrigger" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "barcodeSendCommandTrigger");
        return false;
    } //3468

    public boolean barcodeSendCommandSetPreSuffix() {
        if (isCs108Connected()) return cs108Library4A.barcodeSendCommandSetPreSuffix();
        else if (isCs710Connected()) return cs710Library4A.barcodeSendCommandSetPreSuffix();
        else Log.i("Hello2", "barcodeSendCommandSetPreSuffix" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "barcodeSendCommandSetPreSuffix");
        return false;
    } //3480

    public boolean barcodeSendCommandResetPreSuffix() {
        Log.i("Hello2", "barcodeSendCommandResetPreSuffix");
        return false; } //3503
    public boolean barcodeSendCommandConinuous() {
        Log.i("Hello2", "barcodeSendCommandConinuous");
        return false; } //3522

    public String getBarcodeVersion() {
        if (isCs108Connected()) return cs108Library4A.getBarcodeVersion();
        else if (isCs710Connected()) return cs710Library4A.getBarcodeVersion();
        else Log.i("Hello2", "getBarcodeVersion" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getBarcodeVersion");
        return null;
    } //3539

    public String getBarcodeSerial() {
        if (isCs108Connected()) return cs108Library4A.getBarcodeSerial();
        else if (isCs710Connected()) return cs710Library4A.getBarcodeSerial();
        else Log.i("Hello2", "getBarcodeSerial" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getBarcodeSerial");
        return null;
    } //3563

    public boolean barcodeInventory(boolean start) {
        if (isCs108Connected()) return cs108Library4A.barcodeInventory(start);
        else if (isCs710Connected()) return cs710Library4A.barcodeInventory(start);
        else Log.i("Hello2", "barcodeInventory" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "barcodeInventory");
        return false;
    } //3658

    public String getBluetoothICFirmwareVersion() {
        if (isCs108Connected()) return cs108Library4A.getBluetoothICFirmwareVersion();
        else if (isCs710Connected()) return cs710Library4A.getBluetoothICFirmwareVersion();
        else Log.i("Hello2", "getBluetoothICFirmwareVersion" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getBluetoothICFirmwareVersion");
        return null;
    } //3683

    public String getBluetoothICFirmwareName() {
        if (isCs108Connected()) return cs108Library4A.getBluetoothICFirmwareName();
        else if (isCs710Connected()) return cs710Library4A.getBluetoothICFirmwareName();
        else Log.i("Hello2", "getBluetoothICFirmwareName" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getBluetoothICFirmwareName");
        return null;
    } //3686

    public boolean setBluetoothICFirmwareName(String name) {
        if (isCs108Connected()) return cs108Library4A.setBluetoothICFirmwareName(name);
        else if (isCs710Connected()) return cs710Library4A.setBluetoothICFirmwareName(name);
        else Log.i("Hello2", "setBluetoothICFirmwareName" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setBluetoothICFirmwareName");
        return false;
    } //3693

    public boolean forceBTdisconnect() {
        if (isCs108Connected()) return cs108Library4A.forceBTdisconnect();
        else if (isCs710Connected()) return cs710Library4A.forceBTdisconnect();
        else Log.i("Hello2", "forceBTdisconnect" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "forceBTdisconnect");
        return false;
    } //3696

    public String hostProcessorICGetFirmwareVersion() {
        if (isCs108Connected()) return cs108Library4A.hostProcessorICGetFirmwareVersion();
        else if (isCs710Connected()) return cs710Library4A.hostProcessorICGetFirmwareVersion();
        else Log.i("Hello2", "hostProcessorICGetFirmwareVersion" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "hostProcessorICGetFirmwareVersion");
        return null;
    } //3699

    public String getHostProcessorICSerialNumber() {
        if (isCs108Connected()) return cs108Library4A.getHostProcessorICSerialNumber();
        else if (isCs710Connected()) return cs710Library4A.getHostProcessorICSerialNumber();
        else Log.i("Hello2", "getHostProcessorICSerialNumber" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getHostProcessorICSerialNumber");
        return null;
    } //3702

    public String getHostProcessorICBoardVersion() {
        if (isCs108Connected()) return cs108Library4A.getHostProcessorICBoardVersion();
        else if (isCs710Connected()) return cs710Library4A.getHostProcessorICBoardVersion();
        else Log.i("Hello2", "getHostProcessorICBoardVersion" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getHostProcessorICBoardVersion");
        return null;
    } //3709

    public boolean batteryLevelRequest() {
        if (isCs108Connected()) return cs108Library4A.batteryLevelRequest();
        else if (isCs710Connected()) return cs710Library4A.batteryLevelRequest();
        else Log.i("Hello2", "batteryLevelRequest" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "batteryLevelRequest");
        return false;
    } //3729

    public boolean setAutoBarStartSTop(boolean enable) {
        if (isCs108Connected()) return cs108Library4A.setAutoBarStartSTop(enable);
        else if (isCs710Connected()) return cs710Library4A.setAutoBarStartSTop(enable);
        else Log.i("Hello2", "setAutoBarStartSTop" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setAutoBarStartSTop");
        return false;
    } //3763

    public boolean getTriggerReporting() {
        if (isCs108Connected()) cs108Library4A.getTriggerReporting();
        else if (isCs710Connected()) cs710Library4A.getTriggerReporting();
        else Log.i("Hello2", "getTriggerReporting" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getTriggerReporting");
        return false;
    } //3780

    public boolean setTriggerReporting(boolean triggerReporting) {
        if (isCs108Connected()) return cs108Library4A.setTriggerReporting(triggerReporting);
        else if (isCs710Connected()) return cs710Library4A.setTriggerReporting(triggerReporting);
        else Log.i("Hello2", "setTriggerReporting" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setTriggerReporting");
        return false;
    } //3781

    public int iNO_SUCH_SETTING; //3791
    public short getTriggerReportingCount() { //called before connection
        if (isCs108Connected()) return cs108Library4A.getTriggerReportingCount();
        else if (isCs710Connected()) return cs710Library4A.getTriggerReportingCount();
        else Log.i("Hello2", "getTriggerReportingCount" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getTriggerReportingCount");
        return 5;
    } //3793

    public boolean setTriggerReportingCount(short triggerReportingCount) {
        if (isCs108Connected()) return cs108Library4A.setTriggerReportingCount(triggerReportingCount);
        else if (isCs710Connected()) return cs710Library4A.setTriggerReportingCount(triggerReportingCount);
        else Log.i("Hello2", "setTriggerReportingCount" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setTriggerReportingCount");
        return false;
    } //3801

    public String getBatteryDisplay(boolean voltageDisplay) {
        if (isCs108Connected()) return cs108Library4A.getBatteryDisplay(voltageDisplay);
        else if (isCs710Connected()) return cs710Library4A.getBatteryDisplay(voltageDisplay);
        else Log.i("Hello2", "getBatteryDisplay is called befoe connection !!!");
        if (false) Log.i("Hello2", "getBatteryDisplay");
        return null;
    } //3829

    String stringNOTCONNECT;
    public String isBatteryLow() {
        if (isCs108Connected()) return cs108Library4A.isBatteryLow();
        else if (isCs710Connected()) return cs710Library4A.isBatteryLow();
        else Log.i("Hello2", "isBatteryLow" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "isBatteryLow");
        return null;
    } //3841

    public int getBatteryCount() {
        if (isCs108Connected()) return cs108Library4A.getBatteryCount();
        else if (isCs710Connected()) return cs710Library4A.getBatteryCount();
        else Log.i("Hello2", "getBatteryCount" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getBatteryCount");
        return -1; } //3970

    public boolean getTriggerButtonStatus() {
        if (isCs108Connected()) return cs108Library4A.getTriggerButtonStatus();
        else if (isCs710Connected()) return cs710Library4A.getTriggerButtonStatus();
        else Log.i("Hello2", "getTriggerButtonStatus" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getTriggerButtonStatus");
        return false;
    } //3971

    public int getTriggerCount() {
        if (isCs108Connected()) return cs108Library4A.getTriggerCount();
        else if (isCs710Connected()) return cs710Library4A.getTriggerCount();
        else Log.i("Hello2", "getTriggerCount" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getTriggerCount");
        return -1; } //3972

    public void setNotificationListener(Cs108Connector.NotificationListener listener) {
        if (isCs108Connected()) {
            cs108Library4A.setNotificationListener(new com.csl.cs108library4a.Cs108Connector.NotificationListener() {
                @Override
                public void onChange() {
                    listener.onChange();
                }
            });
        } else if (isCs710Connected()) cs710Library4A.setNotificationListener(listener);
        else Log.i("Hello2", "setNotificationListener" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setNotificationListener");
    } //3973

    public int getBatteryDisplaySetting() {
        if (isCs108Connected()) return cs108Library4A.getBatteryDisplaySetting();
        else if (isCs710Connected()) return cs710Library4A.getBatteryDisplaySetting();
        else Log.i("Hello2", "getBatteryDisplaySetting" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getBatteryDisplaySetting");
        return -1;
    } //3976

    public boolean setBatteryDisplaySetting(int batteryDisplaySelect) {
        if (isCs108Connected()) return cs108Library4A.setBatteryDisplaySetting(batteryDisplaySelect);
        else if (isCs710Connected()) return cs710Library4A.setBatteryDisplaySetting(batteryDisplaySelect);
        else Log.i("Hello2", "setBatteryDisplaySetting" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setBatteryDisplaySetting");
        return false;
    } //3977

    public double dBuV_dBm_constant = -1;
    public int getRssiDisplaySetting() { //called before connection
        if (isCs108Connected()) return cs108Library4A.getRssiDisplaySetting();
        else if (isCs710Connected()) return cs710Library4A.getRssiDisplaySetting();
        if (false) Log.i("Hello2", "getRssiDisplaySetting");
        return 0;
    } //3985

    public boolean setRssiDisplaySetting(int rssiDisplaySelect) {
        if (isCs108Connected()) return cs108Library4A.setRssiDisplaySetting(rssiDisplaySelect);
        else if (isCs710Connected()) return cs710Library4A.setRssiDisplaySetting(rssiDisplaySelect);
        else Log.i("Hello2", "setRssiDisplaySetting" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setRssiDisplaySetting");
        return false;
    } //3986

    public int getVibrateModeSetting() {
        if (isCs108Connected()) return cs108Library4A.getVibrateModeSetting();
        else if (isCs710Connected()) return cs710Library4A.getVibrateModeSetting();
        else Log.i("Hello2", "getVibrateModeSetting" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getVibrateModeSetting");
        return -1;
    } //3993

    public boolean setVibrateModeSetting(int vibrateModeSelect) {
        if (isCs108Connected()) return cs108Library4A.setVibrateModeSetting(vibrateModeSelect);
        else if (isCs710Connected()) return cs710Library4A.setVibrateModeSetting(vibrateModeSelect);
        else Log.i("Hello2", "setVibrateModeSetting" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setVibrateModeSetting");
        return false;
    } //3994

    public int getSavingFormatSetting() {
        if (isCs108Connected()) return cs108Library4A.getSavingFormatSetting();
        else if (isCs710Connected()) return cs710Library4A.getSavingFormatSetting();
        else Log.i("Hello2", "getSavingFormatSetting" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getSavingFormatSetting");
        return -1;
    } //4001

    public boolean setSavingFormatSetting(int savingFormatSelect) {
        if (isCs108Connected()) return cs108Library4A.setSavingFormatSetting(savingFormatSelect);
        else if (isCs710Connected()) return cs710Library4A.setSavingFormatSetting(savingFormatSelect);
        else Log.i("Hello2", "setSavingFormatSetting" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setSavingFormatSetting");
        return false;
    } //4002

    public int getCsvColumnSelectSetting() {
        if (isCs108Connected()) return cs108Library4A.getCsvColumnSelectSetting();
        else if (isCs710Connected()) return cs710Library4A.getCsvColumnSelectSetting();
        else Log.i("Hello2", "getCsvColumnSelectSetting" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getCsvColumnSelectSetting");
        return -1;
    } //4020

    public boolean setCsvColumnSelectSetting(int csvColumnSelect) {
        if (isCs108Connected()) return cs108Library4A.setCsvColumnSelectSetting(csvColumnSelect);
        else if (isCs710Connected()) return cs710Library4A.setCsvColumnSelectSetting(csvColumnSelect);
        else Log.i("Hello2", "setCsvColumnSelectSetting" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setCsvColumnSelectSetting");
        return false;
    } //4021

    public Cs108Connector.Cs108ScanData getNewDeviceScanned() { //called before connection
        com.csl.cs108library4a.Cs108Library4A.Cs108ScanData cs108ScanData1;
        Cs108Connector.Cs108ScanData cs108ScanData = cs710Library4A.getNewDeviceScanned();
        if (cs108ScanData == null) {
            cs108ScanData1 = cs108Library4A.getNewDeviceScanned();
            if (cs108ScanData1 != null) cs108ScanData = new Cs108Connector.Cs108ScanData(cs108ScanData1.getDevice(), cs108ScanData1.rssi, cs108ScanData1.getScanRecord());
        }
        if (false) Log.i("Hello2", "getNewDeviceScanned");
        return cs108ScanData;
    } //4026

    public Cs108Connector.Rx000pkgData onRFIDEvent() {
        if (isCs108Connected()) {
            Cs108Connector.Rx000pkgData rx000pkgData = null;
            com.csl.cs108library4a.Cs108Connector.Rx000pkgData rx000pkgData1 = cs108Library4A.onRFIDEvent();
            if (rx000pkgData1 != null) {
                rx000pkgData = new Cs108Connector.Rx000pkgData();
                switch (rx000pkgData1.responseType) {
                    case TYPE_18K6C_INVENTORY_COMPACT:
                        rx000pkgData.responseType = Cs108Connector.HostCmdResponseTypes.TYPE_18K6C_INVENTORY_COMPACT;
                        break;
                    case TYPE_18K6C_INVENTORY:
                        rx000pkgData.responseType = Cs108Connector.HostCmdResponseTypes.TYPE_18K6C_INVENTORY;
                        break;
                    default:
                        Log.i("Hello2", "onRFIDEvent: responseType = " + rx000pkgData1.responseType.toString());
                }
                rx000pkgData.flags = rx000pkgData1.flags;
                rx000pkgData.dataValues = rx000pkgData1.dataValues;
                rx000pkgData.decodedTime = rx000pkgData1.decodedTime;
                rx000pkgData.decodedRssi = rx000pkgData1.decodedRssi;
                rx000pkgData.decodedPhase = rx000pkgData1.decodedPhase;
                rx000pkgData.decodedChidx = rx000pkgData1.decodedChidx;
                rx000pkgData.decodedPort = rx000pkgData1.decodedPort;
                rx000pkgData.decodedPc = rx000pkgData1.decodedPc;
                rx000pkgData.decodedEpc = rx000pkgData1.decodedEpc;
                rx000pkgData.decodedCrc = rx000pkgData1.decodedCrc;
                rx000pkgData.decodedData1 = rx000pkgData1.decodedData1;
                rx000pkgData.decodedData2 = rx000pkgData1.decodedData2;
                rx000pkgData.decodedResult = rx000pkgData1.decodedResult;
                rx000pkgData.decodedError = rx000pkgData1.decodedError;
            }
            return rx000pkgData;
        }
        else if (isCs710Connected()) return cs710Library4A.onRFIDEvent();
        else Log.i("Hello2", "onRFIDEvent" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "onRFIDEvent");
        return null;
    } //4034

    public byte[] onNotificationEvent() {
        if (isCs108Connected()) return cs108Library4A.onNotificationEvent();
        else if (isCs710Connected()) return cs710Library4A.onNotificationEvent();
        else Log.i("Hello2", "onNotificationEvent" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "onNotificationEvent");
        return null;
    } //4062

    public byte[] onBarcodeEvent() {
        if (isCs108Connected()) return cs108Library4A.onBarcodeEvent();
        else if (isCs710Connected()) return cs710Library4A.onBarcodeEvent();
        else Log.i("Hello2", "onBarcodeEvent" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "onBarcodeEvent");
        return null;
    } //4073

    public String getModelNumber() {
        if (isCs108Connected()) return cs108Library4A.getModelNumber();
        else if (isCs710Connected()) return cs710Library4A.getModelNumber();
        else Log.i("Hello2", "getModelNumber" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "getModelNumber");
        return null;
    } //4188

    public boolean setRx000KillPassword(String password) {
        if (isCs108Connected()) return cs108Library4A.setRx000KillPassword(password);
        else if (isCs710Connected()) return cs710Library4A.setRx000KillPassword(password);
        else Log.i("Hello2", "setRx000KillPassword" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setRx000KillPassword");
        return false;
    } //4223

    public boolean setRx000AccessPassword(String password) {
        if (isCs108Connected()) return cs108Library4A.setRx000AccessPassword(password);
        else if (isCs710Connected()) return cs710Library4A.setRx000AccessPassword(password);
        else Log.i("Hello2", "setRx000AccessPassword" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setRx000AccessPassword");
        return false;
    }

    public boolean setAccessRetry(boolean accessVerfiy, int accessRetry) {
        if (isCs108Connected()) return cs108Library4A.setAccessRetry(accessVerfiy, accessRetry);
        else if (isCs710Connected()) return cs710Library4A.setAccessRetry(accessVerfiy, accessRetry);
        else Log.i("Hello2", "setAccessRetry" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setAccessRetry");
        return false;
    }

    public boolean setInvModeCompact(boolean invModeCompact) {
        if (isCs108Connected()) return cs108Library4A.setInvModeCompact(invModeCompact);
        else if (isCs710Connected()) return cs710Library4A.setInvModeCompact(invModeCompact);
        else Log.i("Hello2", "setInvModeCompact" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setInvModeCompact");
        return false;
    }

    public boolean setAccessLockAction(int accessLockAction, int accessLockMask) {
        if (isCs108Connected()) return cs108Library4A.setAccessLockAction(accessLockAction, accessLockMask);
        else if (isCs710Connected()) return cs710Library4A.setAccessLockAction(accessLockAction, accessLockMask);
        else Log.i("Hello2", "setAccessLockAction" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setAccessLockAction");
        return false;
    }

    public boolean setAccessBank(int accessBank) {
        if (isCs108Connected()) return cs108Library4A.setAccessBank(accessBank);
        else if (isCs710Connected()) return cs710Library4A.setAccessBank(accessBank);
        else Log.i("Hello2", "setAccessBank 1" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setAccessBank 1");
        return false;
    }

    public boolean setAccessBank(int accessBank, int accessBank2) {
        if (isCs108Connected()) return cs108Library4A.setAccessBank(accessBank, accessBank2);
        else if (isCs710Connected()) return cs710Library4A.setAccessBank(accessBank, accessBank2);
        else Log.i("Hello2", "setAccessBank 2" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setAccessBank 2");
        return false;
    }

    public boolean setAccessOffset(int accessOffset) {
        if (isCs108Connected()) return cs108Library4A.setAccessOffset(accessOffset);
        else if (isCs710Connected()) return cs710Library4A.setAccessOffset(accessOffset);
        else Log.i("Hello2", "setAccessOffset 1" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setAccessOffset 1");
        return false;
    }

    public boolean setAccessOffset(int accessOffset, int accessOffset2) {
        if (isCs108Connected()) return cs108Library4A.setAccessOffset(accessOffset, accessOffset2);
        else if (isCs710Connected()) return cs710Library4A.setAccessOffset(accessOffset, accessOffset2);
        else Log.i("Hello2", "setAccessOffset 2" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setAccessOffset 2");
        return false;
    }

    public boolean setAccessCount(int accessCount) {
        if (isCs108Connected()) return cs108Library4A.setAccessCount(accessCount);
        else if (isCs710Connected()) return cs710Library4A.setAccessCount(accessCount);
        else Log.i("Hello2", "setAccessCount 1" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setAccessCount 1");
        return false;
    }

    public boolean setAccessCount(int accessCount, int accessCount2) {
        if (isCs108Connected()) return cs108Library4A.setAccessCount(accessCount, accessCount2);
        else if (isCs710Connected()) return cs710Library4A.setAccessCount(accessCount, accessCount2);
        else Log.i("Hello2", "setAccessCount 2" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setAccessCount 2");
        return false;
    }

    public boolean setAccessWriteData(String dataInput) {
        if (isCs108Connected()) return cs108Library4A.setAccessWriteData(dataInput);
        else if (isCs710Connected()) return cs710Library4A.setAccessWriteData(dataInput);
        else Log.i("Hello2", "setAccessWriteData" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setAccessWriteData");
        return false;
    }

    public boolean setTagRead(int tagRead) {
        if (isCs108Connected()) return cs108Library4A.setTagRead(tagRead);
        else if (isCs710Connected()) return cs710Library4A.setTagRead(tagRead);
        else Log.i("Hello2", "setTagRead" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setTagRead");
        return false;
    } //4235

    public boolean setInvBrandId(boolean invBrandId) {
        if (isCs108Connected()) return cs108Library4A.setInvBrandId(invBrandId);
        else if (isCs710Connected()) return cs710Library4A.setInvBrandId(invBrandId);
        else Log.i("Hello2", "setInvBrandId" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setInvBrandId");
        return false;
    } //4243

    public float decodeCtesiusTemperature(String strActData, String strCalData) {
        Log.i("Hello2", "decodeCtesiusTemperature");
        return -1; } //4278
    public float decodeMicronTemperature(int iTag35, String strActData, String strCalData) {
        Log.i("Hello2", "decodeMicronTemperature");
        return -1; } //4323

    public boolean sendHostRegRequestHST_CMD(Cs108Connector.HostCommands hostCommand) {
        if (isCs108Connected()) Log.i("Hello2", "sendHostRegRequestHST_CMD: hostCommand = " + hostCommand.toString());
        else if (isCs710Connected()) return cs710Library4A.sendHostRegRequestHST_CMD(hostCommand);
        else Log.i("Hello2", "sendHostRegRequestHST_CMD" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "sendHostRegRequestHST_CMD");
        return false;
    } //4237

    public boolean setPwrManagementMode(boolean bLowPowerStandby) { //called before connection
        if (isCs108Connected()) return cs108Library4A.setPwrManagementMode(bLowPowerStandby);
        else if (isCs710Connected()) return cs710Library4A.setPwrManagementMode(bLowPowerStandby);
        if (false) Log.i("Hello2", "setPwrManagementMode");
        return false;
    } //4241

    public void macWrite(int address, long value) {
        Log.i("Hello2", "macWrite");
    } //4248
    public void set_fdCmdCfg(int value) {
        Log.i("Hello2", "set_fdCmdCfg");
    } //4250
    public void set_fdRegAddr(int addr) {
        Log.i("Hello2", "set_fdRegAddr");
    } //4253
    public void set_fdWrite(int addr, long value) {
        Log.i("Hello2", "set_fdWrite");
    } //4254
    public void set_fdPwd(int value) {
        Log.i("Hello2", "set_fdPwd");
    } //4258
    public void set_fdBlockAddr4GetTemperature(int addr) {
        Log.i("Hello2", "set_fdBlockAddr4GetTemperature");
    } //4259
    public void set_fdReadMem(int addr, long len) {
        Log.i("Hello2", "set_fdReadMem");
    } //4262
    public void set_fdWriteMem(int addr, int len, long value) {
        Log.i("Hello2", "set_fdWriteMem");
    } //4266

    public void setImpinJExtension(boolean tagFocus, boolean fastId) {
        if (isCs108Connected()) cs108Library4A.setImpinJExtension(tagFocus, fastId);
        else if (isCs710Connected()) cs710Library4A.setImpinJExtension(tagFocus, fastId);
        else Log.i("Hello2", "setImpinJExtension" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "setImpinJExtension");
    } //4271

    public String strFloat16toFloat32(String strData) {
        Log.i("Hello2", "strFloat16toFloat32");
        return null; } //4387
    public String str2float16(String strData) {
        Log.i("Hello2", "str2float16");
        return null; } //4393
    public String temperatureC2F(String strValue) {
        Log.i("Hello2", "temperatureC2F");
        return null; } //4424
    public String temperatureF2C(String strValue) {
        Log.i("Hello2", "temperatureF2C");
        return null; } //4436

    public int get98XX() {
        if (isCs108Connected()) return cs108Library4A.get98XX();
        else if (isCs710Connected()) return cs710Library4A.get98XX();
        else Log.i("Hello2", "get98XX" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "get98XX");
        return -1;
    } //4445

        //Cs108Connector Routine
    public int invalidata, invalidUpdata, validata; //123
    public boolean checkHostProcessorVersion(String version, int majorVersion, int minorVersion, int buildVersion) {
        if (isCs108Connected()) return cs108Library4A.checkHostProcessorVersion(version, majorVersion, minorVersion, buildVersion);
        else if (isCs710Connected()) return cs710Library4A.checkHostProcessorVersion(version, majorVersion, minorVersion, buildVersion);
        else Log.i("Hello2", "checkHostProcessorVersion" + stringNOTCONNECT);
        if (false) Log.i("Hello2", "checkHostProcessorVersion");
        return false; } //421
}
