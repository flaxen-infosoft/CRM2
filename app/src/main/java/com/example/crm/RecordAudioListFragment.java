package com.example.crm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.SalesManagement.RecyclerViewAdapterMeetingListnew;

import java.io.File;

import javax.annotation.Nullable;

import lombok.NonNull;


public class RecordAudioListFragment extends Fragment {

    RecyclerView recyclerView;
    File[] allfiles;
    RecyclerViewAdapterMeetingListnew recyclerviewadap;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_record_audio_list, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {String path=getActivity().getExternalFilesDir("/").getAbsolutePath();
    File directory=new File(path);
    allfiles=directory.listFiles();
   recyclerView=view.findViewById(R.id.recylist);
   recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
   recyclerviewadap=new RecyclerViewAdapterMeetingListnew(allfiles,getContext());
   recyclerView.setAdapter(recyclerviewadap);


    }
}