package id.ac.unpar.unparapps;

import android.content.Context;
import android.widget.Toast;

import java.net.CookieManager;

/**
 * Created by Kevin on 12/10/2017.
 */

public class ActivityHelper {

    public static void makeToast(Context c,String message){
        Toast.makeText(c,message,Toast.LENGTH_SHORT).show();
    }
}
