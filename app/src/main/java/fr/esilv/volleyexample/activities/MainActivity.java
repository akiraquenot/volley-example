package fr.esilv.volleyexample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.lang.ref.WeakReference;

import fr.esilv.volleyexample.R;
import fr.esilv.volleyexample.VolleyApplication;
import fr.esilv.volleyexample.requests.ReadMeStringRequest;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        //Create and launch a new Request
        ReadMeStringRequest readMeStringRequest = new ReadMeStringRequest(new ReadMeRequestListener(this), new ReadMeErrorListener(this));
        VolleyApplication.getRequestQueue().add(readMeStringRequest);
    }

    private static class ReadMeRequestListener implements Response.Listener<String> {

        private WeakReference<MainActivity> mainActivityWeakReference;

        public ReadMeRequestListener(MainActivity mainActivity) {
            mainActivityWeakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void onResponse(String response) {
            // We create a WeakReference there because of the Network Operation.
            // When the Response of the operation arrives, the Activity could be destroyed (i.e. if the Application is killed)
            // If the Activity is destroyed, the reference will be null ans no NullPointerException will be thrown.
            MainActivity mainActivity = mainActivityWeakReference.get();
            if (mainActivity != null) {
                mainActivity.textView.setText(response);
            }
        }
    }

    private static class ReadMeErrorListener implements Response.ErrorListener {

        private WeakReference<MainActivity> mainActivityWeakReference;

        public ReadMeErrorListener(MainActivity mainActivity) {
            mainActivityWeakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            MainActivity mainActivity = mainActivityWeakReference.get();
            if (mainActivity != null) {
                mainActivity.textView.setText(R.string.error);
            }
        }
    }
}
