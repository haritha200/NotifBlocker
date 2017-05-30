package com.bobdylan.haritha.notificationblocker;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;


import java.util.List;

public class AppListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    public static Context context;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_list, container, false);

        mRecyclerView=(RecyclerView) view.findViewById(R.id.app_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();     //connect (and refetch data from) this mRecyclerview
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


    private void updateUI() {
        if (mRecyclerView.getAdapter()!= null)
            mRecyclerView.getAdapter().notifyDataSetChanged();
        else mRecyclerView.setAdapter(new AppAdapter());

    }


    public class AppHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private AppType mApp;
        private TextView mTitle, mPkgname;
        private ImageView mImageView;
        private SwitchCompat mChecked;

        public AppHolder(View itemView) {
            super(itemView);

            mTitle= (TextView) itemView.findViewById(R.id.item_app_name);
            mPkgname=(TextView)itemView.findViewById(R.id.item_package_name);
            mChecked=(SwitchCompat) itemView.findViewById(R.id.item_switch);
            mImageView=(ImageView)itemView.findViewById(R.id.item_app_icon);

            itemView.setOnClickListener(this);
        }

        public void bindAppToHolder(AppType app) {
            mApp=app;

            mChecked.setChecked(app.isChecked());
            mChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mApp.setChecked(isChecked);
                }
            });
            mPkgname.setText(app.getPkgname());
            mTitle.setText(app.getLabel());
            mImageView.setImageDrawable(app.getIcon());
        }


        @Override
        public void onClick(View v) {

            Intent intent= AppActivity.newIntent(getActivity(), mApp.getPkgname());
            startActivity(intent);
        }
    }



    public class AppAdapter extends RecyclerView.Adapter<AppHolder>{

        private List<AppType> mApps= AppLab.get(getActivity()).getApps();

        @Override
        public AppHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            View view= inflater.inflate(R.layout.item_of_list, parent, false);

            return new AppHolder(view);
        }

        @Override
        public void onBindViewHolder(AppHolder holder, int position) {
            AppType app=mApps.get(position);

            holder.bindAppToHolder(app);

        }

        @Override
        public int getItemCount() {
            return mApps.size();

        }


    }


}



