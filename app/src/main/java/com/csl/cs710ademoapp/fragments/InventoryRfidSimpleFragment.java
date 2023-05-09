package com.csl.cs710ademoapp.fragments;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.csl.cs710ademoapp.MainActivity;
import com.csl.cs710ademoapp.adapters.ReaderListAdapter;

public class InventoryRfidSimpleFragment extends CommonFragment {
    final private boolean bAdd2End = false;
    private String mDid = null;
    int vibrateTimeBackup = 0;

    private ListView rfidListView;
    private TextView rfidEmptyView;
    private TextView rfidRunTime;
    private TextView rfidYieldView;
    private TextView rfidRateView;
    private Button button, buttonShow;

    private ReaderListAdapter listTagsAdapter;
    boolean needDupElim = true;

    public InventoryRfidSimpleFragment() {
        super("InventoryRfidSimpleFragment");
    }

    boolean bRunningInventory = false;

    Runnable runnableStartBeep = new Runnable() {
            @Override
            public void run() {
                //if (MainActivity.isInventoryRfidRequestNewSound()) MainActivity.sharedObjects.playerN.start(); //playerN.setVolume(300, 300);
                //else
                MainActivity.sharedObjects.playerO.start();
            }
        };

}
