package com.example.bonprofit.vista;

import java.util.ArrayList;

import com.example.bonprofit.R;
import com.example.bonprofit.controlador.AdapterComentaris;
import com.example.bonprofit.model.DbHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Llistacomentaris extends Activity {

	private DbHelper mHelper;
	private SQLiteDatabase dataBase;

	private ArrayList<String> comId = new ArrayList<String>();
	private ArrayList<String> comTitol = new ArrayList<String>();
	private ArrayList<String> comTema = new ArrayList<String>();
	private ArrayList<String> comDescripcio = new ArrayList<String>();

	private ListView comentariList;
	private AlertDialog.Builder build;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_llistacomentaris);

		comentariList = (ListView) findViewById(R.id.List);

		mHelper = new DbHelper(this);

		// Afegim un nou registre
		findViewById(R.id.btnAdd).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						Afegircomentari.class);
				i.putExtra("update", false);
				startActivity(i);

			}
		});

		// Quan fem clic a un element de la llista, l'editem
		comentariList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Intent i = new Intent(getApplicationContext(),
						Afegircomentari.class);
				i.putExtra("Fname", comTitol.get(arg2));
				i.putExtra("Lname", comTema.get(arg2));
				i.putExtra("Telefon", comDescripcio.get(arg2));
				i.putExtra("ID", comId.get(arg2));
				i.putExtra("update", true);
				startActivity(i);

			}
		});

		// Long clic per borrar un registre
		comentariList.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {

				build = new AlertDialog.Builder(Llistacomentaris.this);
				build.setTitle("Borrar comentari " + comTitol.get(arg2));
				build.setMessage("Vos borrar aquest comentari?");
				build.setPositiveButton("Si",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

								Toast.makeText(getApplicationContext(),
										comTitol.get(arg2) + " " + " borrat.",
										Toast.LENGTH_SHORT).show();

								dataBase.delete(
										DbHelper.TABLE_NAME,
										DbHelper.KEY_ID + "=" + comId.get(arg2),
										null);
								displayData();
								dialog.cancel();
							}
						});

				build.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});
				AlertDialog alert = build.create();
				alert.show();

				return true;
			}
		});
	}

	@Override
	protected void onResume() {
		displayData();
		super.onResume();
	}

	/**
	 * Mostrem dades de la base de dades a la llista
	 */
	private void displayData() {
		dataBase = mHelper.getWritableDatabase();
		Cursor mCursor = dataBase.rawQuery("SELECT * FROM "
				+ DbHelper.TABLE_NAME, null);

		comId.clear();
		comTitol.clear();
		comTema.clear();
		comDescripcio.clear();
		if (mCursor.moveToFirst()) {
			do {
				comId.add(mCursor.getString(mCursor
						.getColumnIndex(DbHelper.KEY_ID)));
				comTitol.add(mCursor.getString(mCursor
						.getColumnIndex(DbHelper.KEY_TITOL)));
				comTema.add(mCursor.getString(mCursor
						.getColumnIndex(DbHelper.KEY_TEMA)));
				comDescripcio.add(mCursor.getString(mCursor
						.getColumnIndex(DbHelper.KEY_DESCRIPCIO)));

			} while (mCursor.moveToNext());
		}
		AdapterComentaris llista = new AdapterComentaris(Llistacomentaris.this,
				comId, comTitol, comDescripcio);
		comentariList.setAdapter(llista);
		mCursor.close();
	}

	private void triaidioma() {
		final CharSequence[] items = { "Catala (Per defecte)", "Alemany",
				"Italia", "Françes" };

		AlertDialog.Builder builder = new AlertDialog.Builder(
				Llistacomentaris.this);
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
			// ja hi ets
			break;

		case R.id.Sortir:
			startActivity(new Intent(this, Login.class));
			break;

		}
		return true;

	}

}
