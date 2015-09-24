package hr.foi.challenge.challengeclient.fragments;

/**
 * Created by Tomislav Turek on 23.09.15..
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.adapters.ProjectListAdapter;
import hr.foi.challenge.challengeclient.helpers.MvpFactory;
import hr.foi.challenge.challengeclient.models.Project;
import hr.foi.challenge.challengeclient.mvp.presenters.ProjectListPresenter;
import hr.foi.challenge.challengeclient.mvp.views.ProjectListView;

public class ProjectFragment extends Fragment implements ProjectListView {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ProjectListPresenter presenter;
    boolean flag;

    @Bind(R.id.listView) ListView listView;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ProjectFragment newInstance(int sectionNumber, boolean flag) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putBoolean("flag", flag);
        fragment.setArguments(args);
        return fragment;
    }

    public ProjectFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = MvpFactory.getPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        listView.setAdapter(null);
        flag = this.getArguments().getBoolean("flag");
        presenter.loadProjects(flag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onReceived(List<Project> projectList) {
        listView.setAdapter(new ProjectListAdapter(getContext(), projectList));
    }

    @Override
    public void onReceivedFailed() {

    }

    @Override
    public void onReceivedEmpty() {

    }
}
