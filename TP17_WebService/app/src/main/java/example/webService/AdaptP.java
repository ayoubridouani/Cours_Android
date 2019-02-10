package example.webService;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class AdaptP extends ArrayAdapter<Produit> {
    ArrayList<Produit> arl;
    int res;
    Context context;

    public AdaptP(Context context, int resource, ArrayList<Produit> objects) {
        super(context, resource, objects);
        this.arl=objects;
        this.res=resource;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v=inflater.inflate(res,parent,false);

        TextView t_ref = v.findViewById(R.id.textView);
        TextView t_des = v.findViewById(R.id.textView1);

        t_ref.setText(arl.get(position).getRef());
        t_des.setText(arl.get(position).getDes());

        return v;
    }
}
