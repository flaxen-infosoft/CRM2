package com.example.crm.HRManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.QuestionPaper;
import com.example.crm.Model.QuestionPaperAdapter;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestPaperActivity extends AppCompatActivity {

    Button btn_done;
    RecyclerView rv;
    QuestionPaperAdapter qpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_paper);
        rv = findViewById(R.id.recyclerView);


        btn_done = findViewById(R.id.test_done_btn);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestPaperActivity.this, LogicalreasoningTestPaper.class);
                startActivity(intent);
            }
        });
    }

    public void processQuestionPaper(QuestionPaper questionPaper) {
        qpa = new QuestionPaperAdapter(questionPaper.getQuestions(), this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(qpa);
    }

    public void getQuestionPaper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroInterface ri = retrofit.create(RetroInterface.class);
        Call<QuestionPaper> qpaper = ri.getQuestionPaper();
        qpaper.enqueue(new Callback<QuestionPaper>() {
            @Override
            public void onResponse(Call<QuestionPaper> call, Response<QuestionPaper> response) {
                QuestionPaper qp = response.body();
                processQuestionPaper(qp);
            }

            @Override
            public void onFailure(Call<QuestionPaper> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content), "Failed to load question paper", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });

    }
}



        /*
        To instantiate GripRadioGroup

        GridRadioGroup grg = new GridRadioGroup(this, R.id.gridrg);

		grg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(@Nullable RadioGroup group, int checkedId) {
				switch (checkedId) {
					case R.id.op1:
						break;
					case R.id.op2:
						break;
					case R.id.op3:
						break;
					case R.id.op4:
						break;
				}
			}
		});

         */