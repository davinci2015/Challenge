package hr.foi.challenge.challengeclient.fragments;

/**
 * Created by Tomislav Turek on 23.09.15..
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.activities.RankActivity;
import hr.foi.challenge.challengeclient.adapters.ProjectListAdapter;
import hr.foi.challenge.challengeclient.helpers.MvpFactory;
import hr.foi.challenge.challengeclient.models.Project;
import hr.foi.challenge.challengeclient.mvp.presenters.ProjectListPresenter;
import hr.foi.challenge.challengeclient.mvp.views.ProjectListFragmentView;
import hr.foi.challenge.challengeclient.mvp.views.ProjectListView;

public class ProjectFragment extends Fragment implements ProjectListFragmentView {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private ProjectListPresenter presenter;

    private ProjectListView projectListView;

    private boolean flag;

    private ProjectListAdapter adapter;

    @Bind(R.id.listView)
    ListView listView;

    @Bind(R.id.codeText)
    EditText code;

    @Bind(R.id.code_layout)
    LinearLayout codeLayout;

    @Bind(R.id.rank_button)
    Button rankButton;

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
        listView.setOnItemClickListener(listListener);

        flag = this.getArguments().getBoolean("flag");

        if (flag) {
            codeLayout.setVisibility(View.GONE);
            rankButton.setVisibility(View.VISIBLE);
        } else {
            codeLayout.setVisibility(View.VISIBLE);
            rankButton.setVisibility(View.GONE);
        }

        presenter.loadProjects(flag);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        projectListView = (ProjectListView) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.rank_button)
    protected void onClickRank() {
        startActivity(new Intent(getActivity(), RankActivity.class));
    }

    @Override
    public void onReceived(List<Project> projectList) {
        projectListView.hideProgress();

        adapter = new ProjectListAdapter(getContext(), projectList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onReceivedFailed() {
        projectListView.hideProgress();
        projectListView.onPostFetchFail();
    }

    @Override
    public void onReceivedEmpty() {
        projectListView.hideProgress();
        projectListView.onPostFetchEmpty();
    }

    @Override
    public void onProjectSelected(long projectID, String title, String description) {
        projectListView.onProjectSelected(projectID, title, description);
    }

    ListView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Project project = adapter.getItem(position);
            onProjectSelected(project.getId(), project.getName(), project.getDescription());
        }
    };

    @Override
    public void onCodeSuccess() {
        projectListView.hideProgress();
        projectListView.showError(R.string.code_valid);
        onResume();
    }

    @Override
    public void onCodeFailed() {
        projectListView.hideProgress();
        projectListView.showError(R.string.code_error);
    }

    @Override
    public void onCodeUsed() {
        projectListView.hideProgress();
        projectListView.showError(R.string.code_used);
    }

    @Override
    public void showProgress() {
        projectListView.showProgress();
    }

    @OnClick(R.id.submitCode)
    void submitCode() {
        if (TextUtils.isEmpty(code.getText().toString())) {
            projectListView.showError(R.string.code_empty_error);
        } else {
            presenter.sendCode(code.getText().toString());
        }
    }
}
