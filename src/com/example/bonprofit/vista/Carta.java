package com.example.bonprofit.vista;

import com.example.bonprofit.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class Carta extends Activity implements OnClickListener {

	private ImageButton btprimer, btsegon, btbeguda, btpostres;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carta);
		gui();

	}

	private void gui() {
		// TODO Auto-generated method stub
		btprimer = (ImageButton) findViewById(R.id.btnPrimerPlat);
		btprimer.setOnClickListener(this);
		btsegon = (ImageButton) findViewById(R.id.btnSegonPlat);
		btsegon.setOnClickListener(this);
		btpostres = (ImageButton) findViewById(R.id.btnPostres);
		btpostres.setOnClickListener(this);
		btbeguda = (ImageButton) findViewById(R.id.btnBegudes);
		btbeguda.setOnClickListener(this);

	}

	public void onClick(View v) {
		Intent i;

		switch (v.getId()) {
		case R.id.btnPrimerPlat:

			i = new Intent(Carta.this, Llistadeplats.class);
			i.putExtra("tipus", 1);
			startActivity(i);
			overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		case R.id.btnSegonPlat:

			i = new Intent(Carta.this, Llistadeplats.class);
			i.putExtra("tipus", 2);
			startActivity(i);
			overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		case R.id.btnPostres:

			i = new Intent(Carta.this, Llistadeplats.class);
			i.putExtra("tipus", 3);
			startActivity(i);
			overridePendingTransition(R.anim.left_in, R.anim.left_out);

			break;
		case R.id.btnBegudes:

			i = new Intent(Carta.this, Llistadeplats.class);
			i.putExtra("tipus", 4);
			startActivity(i);
			overridePendingTransition(R.anim.left_in, R.anim.left_out);

			break;

		default:
			break;
		}

	}

	private void triaidioma() {
		final CharSequence[] items = { "Catala (Per defecte)", "Alemany",
				"Italia", "Françes" };

		AlertDialog.Builder builder = new AlertDialog.Builder(Carta.this);
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
			// ja i ets
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
