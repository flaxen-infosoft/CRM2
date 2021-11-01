package com.example.crm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.card.MaterialCardView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Nullable;

import lombok.NonNull;
import pl.droidsonroids.gif.GifImageView;

/**

 */
public class RecordAudioFragment extends Fragment {

   private NavController navController;
   private ImageView listbutton;
   boolean isrecording=false;
   String recordFile;
   String meetingduration;
   private Chronometer timer;
   MaterialCardView complete,cancel;
   MediaRecorder mediaRecorder;
   GifImageView recordbutton;
   String permission=Manifest.permission.RECORD_AUDIO;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_record_audio2, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        navController= Navigation.findNavController(view);
        timer=view.findViewById(R.id.timer33);
       listbutton=view.findViewById(R.id.listbtn);
       complete=view.findViewById(R.id.completecard);
       cancel=view.findViewById(R.id.cancelcard);
       complete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {timer.stop();
           meetingduration=timer.getText().toString();
               Intent intent=new Intent(getContext(),SalesMeetActivity.class);
               int position=getActivity().getIntent().getIntExtra("pos",999);
               intent.putExtra("position",position);
               startActivity(intent);


           }
       });
       recordbutton=view.findViewById(R.id.imageView28);
        timer.start();
       recordbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(isrecording)
               { stopRecording();
               recordbutton.setImageResource(R.drawable.recbuttnew);
                   isrecording=false;
               }
               else {
                   if(checkpermission())
               { startRecording();
                   recordbutton.setImageResource(R.drawable.recordlottie);
                   isrecording = true;
               }
               }
           }
       });
       listbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {navController.navigate(R.id.action_recordAudioFragment_to_recordAudioListFragment2);

           }
       });

    }

    private void startRecording()
    {timer.setBase(SystemClock.elapsedRealtime());

        SimpleDateFormat formatter=new SimpleDateFormat("dd_mm_yyyy_hh:mm:ss", Locale.ENGLISH);
        Date now=new Date();

        recordFile="recording_"+formatter.format(now)+".3gp";
    String recordPath=getActivity().getExternalFilesDir("/").getAbsolutePath();
    mediaRecorder=new MediaRecorder();
    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    mediaRecorder.setOutputFile(recordPath+"/"+recordFile);
    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
    }

    private void stopRecording()
    {timer.stop();
        mediaRecorder.stop();
    mediaRecorder.release();
    mediaRecorder=null;
    }

    private boolean checkpermission()
    { if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED)
    {
        return true;
    }
    else
        { ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.RECORD_AUDIO},33);

        return  false;
    }
    }
}