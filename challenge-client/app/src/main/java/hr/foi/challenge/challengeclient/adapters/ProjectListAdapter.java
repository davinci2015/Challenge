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
import hr.foi.challenge.challengeclient.models.Project;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public class ProjectListAdapter extends ArrayAdapter<Project> {
    public ProjectListAdapter(Context context, List<Project> objects) {
        super(context, R.layout.list_item_project, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_project, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Project project = getItem(position);
        holder.projectTitle.setText(project.getName());
        holder.projectDesc.setText(project.getDescription());
        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.project_title)
        TextView projectTitle;

        @Bind(R.id.project_desc)
        TextView projectDesc;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
