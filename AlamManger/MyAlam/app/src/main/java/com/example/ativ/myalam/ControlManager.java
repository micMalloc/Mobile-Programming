package com.example.ativ.myalam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ATIV on 2017-12-16.
 */
public class ControlManager {
    private Context mActivity = null;

    public ControlManager (int initValue, Context input) {
        int code = initValue;
        mActivity = input;

        while (code <= 4) {
            code = controlActivity(code);
        }
    }

    public int controlActivity (int code) {
        Intent stage = null;

        switch (code) {
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                //// TODO: 2017-12-16 Study How to use startActivity from Java Class // e.g. Activity.getApplicationContext() 
                //stage = new Intent( Activity.getApplicationContext(), ShakeActivity.class);
                //stage = new Intent(mActivity, ShakeActivity.class);
                break;
            }
            default: {

            }
        }
        return 0;
    }
}
