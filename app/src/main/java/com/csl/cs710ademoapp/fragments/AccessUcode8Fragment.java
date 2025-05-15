package com.csl.cs710ademoapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.csl.cs710ademoapp.MainActivity;
import com.csl.cs710ademoapp.R;
import com.google.android.material.tabs.TabLayout;

public class AccessUcode8Fragment extends CommonFragment {
    final boolean DEBUG = true;
    Spinner spinnerTagSelect;
    RadioButton radioButtonSelectEpc, radioButtonSelectEpcTid, radioButtonSelectEpcBrand, radioButtonSelectEpcBrandTidCheck;
    enum nxpTag {
        ucode8, ucodeDNA, others
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState, false);
        return inflater.inflate(R.layout.fragment_access_ucode8, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        spinnerTagSelect = (Spinner) getActivity().findViewById(R.id.accessNxpTagSelect);
        ArrayAdapter<CharSequence> targetAdapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.nxp_options, R.layout.custom_spinner_layout);
        targetAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTagSelect.setAdapter(targetAdapter1); spinnerTagSelect.setSelection(0);
        spinnerTagSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.OperationsTabLayout);
                TabLayout.TabView tabView = tabLayout.getTabAt(2).view; tabView.setVisibility(View.GONE);
                TabLayout.TabView tabViewUntrace = tabLayout.getTabAt(3).view; tabViewUntrace.setVisibility(View.GONE);
                LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.accessNxpUcode8Select); layout.setVisibility(View.GONE);
                if (position == nxpTag.ucode8.ordinal()) {
                    MainActivity.mDid = "E2806894";
                    if (MainActivity.csLibrary4A.get98XX() == 0) tabViewUntrace.setVisibility(View.VISIBLE);
                    layout.setVisibility(View.VISIBLE);
                } else if (position == nxpTag.ucodeDNA.ordinal()) {
                    MainActivity.mDid = "E2C06";
                    tabView.setVisibility(View.VISIBLE);
                    if (MainActivity.csLibrary4A.get98XX() == 0) tabViewUntrace.setVisibility(View.VISIBLE);
                } else MainActivity.mDid = "E2806";
                MainActivity.csLibrary4A.appendToLog("new mDid = " + MainActivity.mDid);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        radioButtonSelectEpc = (RadioButton) getActivity().findViewById(R.id.accessUC8SelectEpc);
        radioButtonSelectEpcTid = (RadioButton) getActivity().findViewById(R.id.accessUC8SelectEpcTid);
        radioButtonSelectEpcBrand = (RadioButton) getActivity().findViewById(R.id.accessUC8SelectEpcBrand);
        radioButtonSelectEpcBrandTidCheck = (RadioButton) getActivity().findViewById(R.id.accessUC8SelectEpcBrandTidCheck);
        if (MainActivity.csLibrary4A.get98XX() == 2) {
            radioButtonSelectEpc.setChecked(true);
            radioButtonSelectEpcBrand.setVisibility(View.GONE);
            radioButtonSelectEpcBrandTidCheck.setVisibility(View.GONE);
        } else radioButtonSelectEpcBrand.setChecked(true);

        MainActivity.csLibrary4A.setSameCheck(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    boolean userVisibleHint = false;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            userVisibleHint = true;
            MainActivity.csLibrary4A.appendToLog("AccessUcode8Fragment is now VISIBLE");
            //            setNotificationListener();
        } else {
            if (spinnerTagSelect != null && spinnerTagSelect.getSelectedItemPosition() == nxpTag.ucode8.ordinal()) {
                if (radioButtonSelectEpc != null && radioButtonSelectEpcTid != null && radioButtonSelectEpcBrand != null && radioButtonSelectEpcBrandTidCheck != null) {
                    if (radioButtonSelectEpc.isChecked()) {
                        MainActivity.csLibrary4A.appendToLog("Selected EPC");
                        MainActivity.mDid = "E2806894A";
                    }
                    if (radioButtonSelectEpcTid.isChecked()) {
                        MainActivity.csLibrary4A.appendToLog("Selected EPC+TID");
                        MainActivity.mDid = "E2806894B";
                    }
                    if (radioButtonSelectEpcBrand.isChecked()) {
                        MainActivity.csLibrary4A.appendToLog("Selected EPC+BRAND");
                        MainActivity.mDid = "E2806894C";
                    }
                    if (radioButtonSelectEpcBrandTidCheck.isChecked()) {
                        MainActivity.csLibrary4A.appendToLog("Selected EPC+BRAND");
                        MainActivity.mDid = "E2806894d";
                    }
                    MainActivity.csLibrary4A.appendToLog("newDid 1 = " + MainActivity.mDid);
                }
            }
            userVisibleHint = false;
            MainActivity.csLibrary4A.appendToLog("AccessUcode8Fragment is now INVISIBLE");
        }
    }

    public AccessUcode8Fragment() {
        super("AccessUcode8Fragment");
    }
}
