package com.example.bonprofit.vista;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.xml.sax.SAXException;

import com.example.bonprofit.R;
import com.example.bonprofit.controlador.ParserSAX;

import com.example.bonprofit.controlador.PlatAdapter;
import com.example.bonprofit.model.Plat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.ListView;
import android.widget.Toast;

public class Llistadeplats extends Activity {

	private ListView lista;
	private PlatAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_llista_plats);
		gui();
	}

	private void gui() {

		Bundle extras = getIntent().getExtras();
		int tipus = extras.getInt("tipus");
		String tipus2 = tipus + "";

		new PeticioHttp().execute(tipus2.toString());

	}

	class PeticioHttp extends AsyncTask<String, Void, ArrayList<Plat>> {

		private ArrayList<Plat> plats = new ArrayList<Plat>();

		@Override
		protected ArrayList<Plat> doInBackground(String... params) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://172.24.1.11/projecte/obtenirPlats.php");

			int codiEstat = 0;
			try {

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("tipus", params[0]));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse resposta = httpclient.execute(httppost);
				StatusLine estat = resposta.getStatusLine();
				codiEstat = estat.getStatusCode();
				if (codiEstat == 200) {
					InputStream contingut = resposta.getEntity().getContent();
					plats = tractar(contingut);
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			return plats;
		}

		protected void onPostExecute(ArrayList<Plat> plats) {

			adapter = new PlatAdapter(Llistadeplats.this, plats);
			lista = (ListView) findViewById(R.id.llistaPrimers);
			lista.setAdapter(adapter);

		}

	}

	private ArrayList<Plat> tractar(InputStream xml) throws SAXException,
			IOException, ParserConfigurationException {
		SAXParserFactory generadorSAX = SAXParserFactory.newInstance();
		SAXParser analitzador = generadorSAX.newSAXParser();
		ParserSAX p = new ParserSAX();

		// analitzar el fitxer xml
		analitzador.parse(xml, p);
		// obtenir la llista
		return p.getLlista();
	}

	private void triaidioma() {
		final CharSequence[] items = { "Catala (Per defecte)", "Alemany",
				"Italia", "Françes" };

		AlertDialog.Builder builder = new AlertDialog.Builder(
				Llistadeplats.this);
		builder.setTitle("Idiomes disponibles");
		builder.setIcon(R.drawable.llengua);
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				Toast.makeText(getApplicationContext(), items[item],
						Toast.LENGTH_SHORT).show();
			}
		});
		AlertDialog alert = builder.create();

		alert.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {
		case R.id.Home:
			startActivity(new Intent(this, Carta.class));
			break;

		case R.id.Llengua:
			triaidioma();
			break;
		case R.id.Comentaris:
			startActivity(new Intent(this, Llistacomentaris.class));
			break;

		case R.id.Sortir:
			startActivity(new Intent(this, Login.class));
			break;

		}
		return true;

	}

}
