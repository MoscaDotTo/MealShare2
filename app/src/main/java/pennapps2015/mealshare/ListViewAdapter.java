package pennapps2015.mealshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rob on 9/5/2015.
 */
public class ListViewAdapter extends BaseAdapter {
    List<lvDataParser> outputList;
    public ListViewAdapter(List<lvDataParser> outputList) {
        this.outputList = outputList;
    }

    @Override
    public int getCount() {
        if(outputList== null) { return 0;}
        return outputList.size();
    }

    @Override
    public Object getItem(int position) {
        return outputList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitem, parent, false);
        }
        TextView title = (TextView)convertView.findViewById(R.id.Title);
        TextView subtitle = (TextView)convertView.findViewById(R.id.SubTitle);
        TextView foodLoc = (TextView)convertView.findViewById(R.id.where);

        title.setText(outputList.get(position).content);
        subtitle.setText(outputList.get(position).category);
        foodLoc.setText(outputList.get(position).location);
        return convertView;
    }
}
