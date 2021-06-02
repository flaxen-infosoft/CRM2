package com.example.crm.HRManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.R;

public class TestPaperActivity extends AppCompatActivity {

    Button btn_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_paper);

        btn_done = findViewById(R.id.test_done_btn);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestPaperActivity.this, LogicalreasoningTestPaper.class);
                startActivity(intent);
            }
        });


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


    }
}