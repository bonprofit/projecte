package com.example.bonprofit.vista;

import com.example.bonprofit.R;
import com.example.bonprofit.model.DbHelper;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;

public class Afegircomentari extends Activity {
	private EditText titol;
	private EditText tema;
	private EditText descripcio;
	private Button btnAfegir;
	private DbHelper mHelper;
	private SQLiteDatabase dataBase;
	private String id, titolx, temax, descripciox;
	private boolean isUpdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_afegircomentari);

		titol = (EditText) findViewById(R.id.editTextTitol);
		tema = (EditText) findViewById(R.id.editTextTema);
		descripcio = (EditText) findViewById(R.id.editTextDescripcio);

		btnAfegir = (Button) findViewById(R.id.btnHola);
		btnAfegir.setEnabled(false);

		isUpdate = getIntent().getExtras().getBoolean("update");
		if (isUpdate) {
			id = getIntent().getExtras().getString("ID");
			titolx = getIntent().getExtras().getString("Fname");
			temax = getIntent().getExtras().getString("Lname");
			descripciox = getIntent().getExtras().getString("telefon");
			titol.setText(titolx);
			tema.setText(temax);
			descripcio.setText(descripciox);

		}

		mHelper = new DbHelper(this);

		// A cada camp que volem fer obligatori en el nostre formolari, mirem si
		// s'emplenen o no
		titol.addTextChangedListener(mWatcher);
		tema.addTextChangedListener(mWatcher);
		descripcio.addTextChangedListener(mWatcher);

	}

	/*
	 * Metode que fem servir per mirar si els camps text hi entren dades o no
	 */
	private TextWatcher mWatcher = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			boolean nomNotEmpty = titol.getText().length() > 0;
			boolean cognomNotEmpty = tema.getText().length() > 0;
			boolean telefonNotEmpty = descripcio.getText().length() > 0;
			btnAfegir.setEnabled(nomNotEmpty && cognomNotEmpty
					&& telefonNotEmpty);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

	};

	// Quant fem clic al boto.....
	public void btnHolaClick(View v) {

		titolx = titol.getText().toString().trim();
		temax = tema.getText().toString().trim();
		descripciox = descripcio.getText().toString().trim();
		if (titolx.length() > 0 && temax.length() > 0
				&& descripciox.length() > 0) {
			saveData();
		} else {
			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
					Afegircomentari.this);
			alertBuilder.setTitle("Invalid Data");
			alertBuilder.setMessage("Please, Enter valid data");
			alertBuilder.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();

						}
					});
			alertBuilder.create().show();
		}

	}

	private void saveData() {
		// TODO Auto-generated method stub
		dataBase = mHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(DbHelper.KEY_TITOL, titolx);
		values.put(DbHelper.KEY_TEMA, temax);
		values.put(DbHelper.KEY_DESCRIPCIO, descripciox);

		System.out.println("");
		if (isUpdate) {
			// Actualitzem la base de dades amb les noves dates
			dataBase.update(DbHelper.TABLE_NAME, values, DbHelper.KEY_ID + "="
					+ id, null);
		} else {
			// Inserim dades a la base de dades
			dataBase.insert(DbHelper.TABLE_NAME, null, values);
		}
		// Tenquem la base de dades
		dataBase.close();
		finish();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
