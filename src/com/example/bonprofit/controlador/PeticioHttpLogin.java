package com.example.bonprofit.controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import com.example.bonprofit.vista.Carta;

public class PeticioHttpLogin extends AsyncTask<String, Void, String> {

	private Context c;

	public PeticioHttpLogin(Context context) {
		this.c = context;
	}

	protected String doInBackground(String... params) {
		String status = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(
				"http://172.24.1.11/projecte/autentificacio.php");

		int codiEstat = 0;
		try {

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("username", params[0]));
			nameValuePairs.add(new BasicNameValuePair("password", params[1]));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse resposta = httpclient.execute(httppost);
			StatusLine estat = resposta.getStatusLine();
			codiEstat = estat.getStatusCode();
			if (codiEstat == 200) {
				InputStream contingut = resposta.getEntity().getContent();
				status = tractar(contingut);

			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	protected void onPostExecute(String resultat) {
		if (resultat != null) {
			if (resultat.equals("si")) {
				Toast.makeText(c, "Redeccionant...", Toast.LENGTH_SHORT).show();

				new Handler().postDelayed(new Runnable() {
					public void run() {
						Intent intent = new Intent(c, Carta.class);
						c.startActivity(intent);

					}
				}, 2000);

			} else {
				Toast.makeText(c, "Error a l'identificar-se",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private String tractar(InputStream contingut) {
		BufferedReader lector = new BufferedReader(new InputStreamReader(
				contingut));

		try {
			String resposta = lector.readLine();
			return resposta;
		} catch (IOException e) {
			return null;
		}

	}
}
