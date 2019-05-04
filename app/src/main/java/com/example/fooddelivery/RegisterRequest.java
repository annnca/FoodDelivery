package com.example.fooddelivery;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
//request to the php file on the server and get a response as a string

public class RegisterRequest extends StringRequest {
    private static  final String REGISTER_REQUEST_URL ="https://mcproj1234.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username, String email, String password,Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL,listener, null );
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("email", email+"");
    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }

}
