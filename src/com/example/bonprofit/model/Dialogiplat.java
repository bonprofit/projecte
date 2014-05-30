package com.example.bonprofit.model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

import android.view.View;

import android.widget.ImageView;
import android.widget.Toast;

import com.example.bonprofit.R;
import com.example.bonprofit.controlador.DownloadImageTask;

public class Dialogiplat extends DialogFragment {
	private String imatge;

	public Dialogiplat() {

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.activity_dialogiplat, null);
		dialog.setView(view);

		ImageView imatgeTmp = (ImageView) view.findViewById(R.id.imatgeplat);
		imatgeTmp = (ImageView) view.findViewById(R.id.imatgeplat);

		new DownloadImageTask(imatgeTmp).execute(imatge);

		dialog.setPositiveButton("Afegir en la comanda", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();

				Toast.makeText(getActivity(),
						"EL plat escollit s'ha afegit en la comanda",
						Toast.LENGTH_SHORT).show();
			}

		});
		dialog.setNegativeButton("Cancelar", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}

		});

		AlertDialog dialeg = dialog.create();
		return dialeg;
	}

	public void setImatge(String imatge) {
		this.imatge = imatge;
	}

}
