package hr.foi.challenge.challengeclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.models.Feedback;
import hr.foi.challenge.challengeclient.models.Group;

/**
 * Created by Tomislav Turek on 24.09.15..
 */
public class GroupAdapter extends ArrayAdapter<Group> {

    public GroupAdapter(Context context, List<Group> objects) {
        super(context, R.layout.list_item_feedback, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_feedback, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Group group = getItem(position);
        holder.groupName.setText(group.getName());
        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.feedback_title)
        TextView groupName;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }

}
