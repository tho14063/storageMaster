package com.example.storagemaster.storagemaster;

/**
 * Created by Alex the one and only on 2/27/2018.
 */

        import android.app.Activity;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class ItemListAdapter extends ArrayAdapter<Item> {

    private final Activity context;
    private final ArrayList<Item> itemList;

    public ItemListAdapter(Activity context, ArrayList<Item> itemList) {
        super(context, R.layout.listitem, itemList);
        this.context=context;
        this.itemList = itemList;

    }

    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder mainViewHolder = null;
//        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.listitem, null, true);
            ViewHolder viewHolder = new ViewHolder();

            TextView txtTitle = (TextView) view.findViewById(R.id.itemname);
            final TextView quantityView = (TextView) view.findViewById(R.id.quantity);

            txtTitle.setText(itemList.get(position).getItemName());
            quantityView.setText("" + itemList.get(position).getQuantity());

            viewHolder.name = (TextView) view.findViewById(R.id.itemname);
            viewHolder.subtractB = (Button) view.findViewById(R.id.subtractbutton);
            viewHolder.qty = (TextView) view.findViewById(R.id.quantity);
            viewHolder.addB = (Button) view.findViewById(R.id.addbutton);

            viewHolder.subtractB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemList.get(position).setQuantity(itemList.get(position).getQuantity() - 1);
                    quantityView.setText("" + itemList.get(position).getQuantity());
                }
            });

            viewHolder.addB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemList.get(position).setQuantity(itemList.get(position).getQuantity() + 1);
                    quantityView.setText("" + itemList.get(position).getQuantity());
                }
            });
            view.setTag(viewHolder);
//        }
//        else {
//            Log.d("STRING", "Position created from else " + position);
//                mainViewHolder = (ViewHolder) view.getTag();
//                mainViewHolder.name.setText(itemList.get(position).getItemName());
//            }
        return view;

    };

    public class ViewHolder {
        TextView name;
        Button subtractB;
        TextView qty;
        Button addB;
    }
}
