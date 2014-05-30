package com.example.bonprofit.controlador;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bonprofit.R;
import com.example.bonprofit.model.Dialogiplat;
import com.example.bonprofit.model.Plat;

public class PlatAdapter extends ArrayAdapter<Plat> implements OnClickListener {

	Vista vista;

	class Vista {
		public TextView nom, descripcio;
		public ImageView imatge;

	}

	private ArrayList<Plat> dades;
	private Bitmap[] imatges;

	public PlatAdapter(Activity context, ArrayList<Plat> dades) {
		super(context, R.layout.activity_llista_plats, dades);
		this.dades = dades;
		imatges = new Bitmap[dades.size()];
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View element = convertView;

		// optimització de codi per vizualitzar mes ràpid
		if (element == null) {
			LayoutInflater inflater = ((Activity) getContext())
					.getLayoutInflater();
			element = inflater.inflate(R.layout.llistat, null);

			vista = new Vista();
			vista.nom = (TextView) element.findViewById(R.id.tNomplat);
			vista.descripcio = (TextView) element
					.findViewById(R.id.tDescripcio);
			vista.imatge = (ImageView) element.findViewById(R.id.iPlat);

			element.setTag(vista);

		} else {
			vista = (Vista) element.getTag();
		}

		if (imatges[position] == null) {
			try {
				imatges[position] = new descarregarImatge().execute(
						"http://172.24.1.11/projecte/"
								+ dades.get(position).getImg() + ".jpg").get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

		}

		vista.nom.setText(dades.get(position).getNom());
		vista.descripcio.setText(dades.get(position).getDescripcio());
		vista.imatge.setImageBitmap(imatges[position]);

		final Dialogiplat dp = new Dialogiplat();

		dp.setImatge("http://172.24.1.11/projecte/normals/"
				+ dades.get(position).getImg() + ".jpg");

		vista.imatge.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				dp.show(((Activity) getContext()).getFragmentManager(), null);

			}
		});

		return element;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	private class descarregarImatge extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... urls) {
			Bitmap bitmap = null;
			for (String url : urls) {
				try {
					bitmap = BitmapFactory.decodeStream((InputStream) new URL(
							url).getContent());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return bitmap;
		}
	}
}
