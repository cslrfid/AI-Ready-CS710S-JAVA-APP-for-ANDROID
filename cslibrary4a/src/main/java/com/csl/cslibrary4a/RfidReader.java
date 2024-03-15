package com.csl.cslibrary4a;

import android.content.Context;

public class RfidReader {
    Context context; Utility utility;
    public RfidReader(Context context, Utility utility) {
        this.context = context;
        this.utility = utility;
    }
    private void appendToLog(String s) { utility.appendToLog(s); }
}
