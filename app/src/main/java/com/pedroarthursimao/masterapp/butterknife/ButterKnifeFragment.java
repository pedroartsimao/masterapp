package com.pedroarthursimao.masterapp.butterknife;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedroarthursimao.masterapp.MainActivity;
import com.pedroarthursimao.masterapp.R;
import com.pedroarthursimao.masterapp.butterknife.adapter.ListItemAdapter;
import com.pedroarthursimao.masterapp.butterknife.model.ListItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ButterKnifeFragment extends Fragment {

    public static String TITLE = "Butter Knife";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private ListItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_butterknife, container, false);
        ButterKnife.bind(this, rootView);
        setUpToolbar();
        setUpRecyclerView();
        return rootView;
    }

    private void setUpRecyclerView(){
        adapter = new ListItemAdapter(createListItems());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void setUpToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_drawer_menu);
        toolbar.setTitle(this.TITLE);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
    }

    private ArrayList<ListItem> createListItems(){
        ArrayList<ListItem> listItems = new ArrayList<>();
        listItems.add(
                new ListItem(
                        "The Last of Us",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed dui tellus, porta ac congue ultrices, vestibulum fringilla purus. Sed at dapibus mauris, sed pulvinar eros. Aenean eu bibendum sem, eu facilisis felis",
                        BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_launcher)
                )
        );
        listItems.add(
                new ListItem(
                        "The Legend of Zelda",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed dui tellus, porta ac congue ultrices, vestibulum fringilla purus. Sed at dapibus mauris, sed pulvinar eros. Aenean eu bibendum sem, eu facilisis felis",
                        BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_launcher)
                )
        );
        listItems.add(
                new ListItem(
                        "GTA V",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed dui tellus, porta ac congue ultrices, vestibulum fringilla purus. Sed at dapibus mauris, sed pulvinar eros. Aenean eu bibendum sem, eu facilisis felis",
                        BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_launcher)
                )
        );
        listItems.add(
                new ListItem(
                        "Ape Escape",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed dui tellus, porta ac congue ultrices, vestibulum fringilla purus. Sed at dapibus mauris, sed pulvinar eros. Aenean eu bibendum sem, eu facilisis felis",
                        BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_launcher)
                )
        );
        listItems.add(
                new ListItem(
                        "Sonic the Hedgehog",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed dui tellus, porta ac congue ultrices, vestibulum fringilla purus. Sed at dapibus mauris, sed pulvinar eros. Aenean eu bibendum sem, eu facilisis felis",
                        BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_launcher)
                )
        );
        return listItems;
    }
}
