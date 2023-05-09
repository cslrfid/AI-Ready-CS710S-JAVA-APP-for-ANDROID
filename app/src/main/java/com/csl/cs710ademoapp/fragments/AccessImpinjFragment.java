package com.csl.cs710ademoapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.csl.cs710ademoapp.MainActivity;
import com.csl.cs710ademoapp.R;

public class AccessImpinjFragment extends CommonFragment {
    CheckBox checkBoxTagFocus, checkBoxFastId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState, false);
        return inflater.inflate(R.layout.fragment_access_impinj, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        checkBoxTagFocus = (CheckBox) getActivity().findViewById(R.id.accessIMPINJTagFocus);
        if (MainActivity.csLibrary4A.get98XX() != 0) checkBoxTagFocus.setText(checkBoxTagFocus.getText().toString() + " (When enabled, tag select is disabled.)");
        checkBoxFastId = (CheckBox) getActivity().findViewById(R.id.accessIMPINJFastId);
        MainActivity.csLibrary4A.appendToLog("CheckBoxTagFocus is set");

        MainActivity.csLibrary4A.setSameCheck(false);
        data2Restore.iQuerySession = MainActivity.csLibrary4A.getQuerySession();
        data2Restore.iQueryTarget = MainActivity.csLibrary4A.getQueryTarget();
        data2Restore.tagDelay = MainActivity.csLibrary4A.getTagDelay();
        data2Restore.dwellTime = MainActivity.csLibrary4A.getAntennaDwell();
        data2Restore.tagFocus = MainActivity.csLibrary4A.getTagFocus();
    }

    class Data2Restore {
        int iQuerySession;
        int iQueryTarget;
        byte tagDelay;
        long dwellTime;
        int tagFocus;
    }
    Data2Restore data2Restore = new Data2Restore();

    @Override
    public void onDestroy() {
        MainActivity.csLibrary4A.abortOperation();
        MainActivity.csLibrary4A.setSameCheck(true);
        MainActivity.csLibrary4A.setTagGroup(MainActivity.csLibrary4A.getQuerySelect(), data2Restore.iQuerySession, data2Restore.iQueryTarget);
        MainActivity.csLibrary4A.setTagDelay((byte)data2Restore.tagDelay);
        MainActivity.csLibrary4A.setAntennaDwell(data2Restore.dwellTime);
        MainActivity.csLibrary4A.setTagFocus(data2Restore.tagFocus > 0 ? true : false);
        MainActivity.csLibrary4A.restoreAfterTagSelect();
        super.onDestroy();
    }

    boolean userVisibleHint = false;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            userVisibleHint = true;
            MainActivity.csLibrary4A.appendToLog("AccessImpinjFragment is now VISIBLE");
        } else {
            int iValue = 0;
            if (checkBoxTagFocus != null && checkBoxFastId != null) {
                if (checkBoxTagFocus.isChecked()) {
                    MainActivity.csLibrary4A.setTagGroup(MainActivity.csLibrary4A.getQuerySelect(), 1, 0);
                    MainActivity.csLibrary4A.setTagDelay((byte)0);
                    MainActivity.csLibrary4A.setAntennaDwell(2000);

                    iValue |= 0x10;
                } else MainActivity.csLibrary4A.setTagGroup(MainActivity.csLibrary4A.getQuerySelect(), 0, 2);
                if (checkBoxFastId.isChecked()) iValue |= 0x20;
                MainActivity.csLibrary4A.appendToLog("HelloK: iValue = " + String.format("%20X", iValue));
                MainActivity.mDid = "E28011" + String.format("%02X", iValue);
                MainActivity.csLibrary4A.setImpinJExtension(checkBoxTagFocus.isChecked(), checkBoxFastId.isChecked());
            }
            userVisibleHint = false;
            MainActivity.csLibrary4A.appendToLog("AccessImpinjFragment is now INVISIBLE" + (checkBoxFastId != null ? (" with Value = " + iValue) : ""));
        }
    }

    public AccessImpinjFragment() {
        super("AccessImpinjFragment");
    }
}