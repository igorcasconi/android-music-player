package com.example.testapplicationjava.services;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testapplicationjava.classes.Music;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ServiceSpotify extends Request<JSONObject> {
  private Response.Listener<JSONObject> response;
  private Map<String, String> headers;
  private Map<String, String> params;

  public ServiceSpotify(String url, Map<String, String> headers, Map<String, String> params, Response.Listener<JSONObject> response, Response.ErrorListener listener) {
    super(Method.GET, url, listener);
    this.headers = headers;
    this.params = params;
    this.response = response;
  }

  @Override
  public Map<String, String> getHeaders() throws AuthFailureError {
    return headers != null ? headers : super.getHeaders();
  }

  @Override
  public Map<String, String> getParams() throws AuthFailureError {
    return params;
  }

  public Priority getPriority() {
    return (Priority.NORMAL);
  }


  @Override
  protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
    try {
      String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
      return (Response.success(new JSONObject(js), HttpHeaderParser.parseCacheHeaders(response)));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected void deliverResponse(JSONObject response) {
    this.response.onResponse(response);
  }
}
//  public void getTracks(String id) {
//    String url = baseURL + "tracks/?ids=" + id;
//    final JSONObject[] retorno = {new JSONObject()};
//
//    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//          (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//              if (response != null)
//                retorno[0] = response;
//            }
//          }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//              // TODO: Handle error
//            }
//          }) {
//      @Override
//      public Map<String, String> getHeaders() throws AuthFailureError {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("X-RapidAPI-Key", rapidKey);
//        params.put("X-RapidAPI-Host", rapidHost);
//        return params;
//      }
//
//    };
//
//    queue.add(jsonObjectRequest);
//  }
