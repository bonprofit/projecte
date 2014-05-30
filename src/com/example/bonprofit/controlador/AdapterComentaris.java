package com.example.bonprofit.controlador;

import java.util.ArrayList;

import com.example.bonprofit.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterComentaris extends BaseAdapter {
	private Context mContext;
	private ArrayList<String> ids;
	private ArrayList<String> noms;

	private ArrayList<String> telefons;

	public AdapterComentaris(Context c, ArrayList<String> id,
			ArrayList<String> fname, ArrayList<String> telefon) {
		this.mContext = c;

		this.ids = id;
		this.noms = fname;
		this.telefons = telefon;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return ids.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int pos, View child, ViewGroup parent) {
		Holder mHolder;
		LayoutInflater layoutInflater;
		if (child == null) {
			layoutInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			child = layoutInflater.inflate(R.layout.activity_item_comentari,
					null);
			mHolder = new Holder();

			mHolder.txt_Nom = (TextView) child.findViewById(R.id.text_titol);

			mHolder.txt_Telefon = (TextView) child
					.findViewById(R.id.text_descripcio);
			child.setTag(mHolder);
		} else {
			mHolder = (Holder) child.getTag();
		}

		mHolder.txt_Nom.setText(noms.get(pos));

		mHolder.txt_Telefon.setText(telefons.get(pos));

		return child;
	}

	public class Holder {

		TextView txt_Nom;

		TextView txt_Telefon;
	}

}
