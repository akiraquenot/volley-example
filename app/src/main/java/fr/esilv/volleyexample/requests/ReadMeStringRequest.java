package fr.esilv.volleyexample.requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class ReadMeStringRequest extends StringRequest {

    private static final String URL = "https://raw.githubusercontent.com/nguyen-baylatry-esilv/volley-example/master/README.md";

    public ReadMeStringRequest(Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.GET, URL, listener, errorListener);
    }
}
