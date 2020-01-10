package pers.zhc.tools.codecs;

import android.app.Activity;
import android.widget.TextView;

public class JNI {
    static {
        System.loadLibrary("doJNI");
    }

    private TextView tv;
    private Activity activity;

    JNI(TextView tv, Activity activity) {
        this.tv = tv;
        this.activity = activity;
    }

    /**
     * @param f    f
     * @param dF   dF
     * @param mode delete srcFile: 1
     * @return status
     */
    public native int qmcDecode(String f, String dF, int mode);

    @SuppressWarnings("unused")
    //jni method
    public void d(String s, double b) {
        if (b == -1D) {
            activity.runOnUiThread(() -> tv.setText(s));
        } else {
            System.out.println("s = " + s);
            System.out.println("codecs_activity_b = " + b);
            activity.runOnUiThread(() -> tv.setText(String.format("%s", b + "%")));
        }
    }

    /**
     * @param f    f
     * @param dF   dF
     * @param mode delete srcFile: 1
     * @return status
     */
    public native int kwmDecode(String f, String dF, int mode);

    public native int Base128_encode(String f, String dF, int mode);

    public native int Base128_decode(String f, String dF, int mode);
}