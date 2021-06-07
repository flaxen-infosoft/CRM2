package com.example.crm.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.GridRadioGroup;
import com.example.crm.R;

import java.util.ArrayList;

public class QuestionPaperAdapter extends RecyclerView.Adapter<QuestionPaperAdapter.QuestionViewHolder> {
	ArrayList<Question> questions;
	Context context;

	public QuestionPaperAdapter(ArrayList<Question> questions, Context context) {
		this.questions = questions;
		this.context = context;
	}

	@NonNull
	@Override
	public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(context).inflate(R.layout.question_item_layout, parent, false);
		return new QuestionViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {

		holder.que.setText(questions.get(position).getQue());
		ArrayList<String> options = questions.get(position).getOptions();
		holder.op1.setText(options.get(0));
		holder.op2.setText(options.get(1));
		holder.op3.setText(options.get(2));
		holder.op4.setText(options.get(3));
	}

	@Override
	public int getItemCount() {
		return questions.size();
	}

	public class QuestionViewHolder extends RecyclerView.ViewHolder {
		TextView que;
		RadioButton op1, op2, op3, op4;
		GridRadioGroup grg;
		RadioGroup rg;

		public QuestionViewHolder(@NonNull View itemView) {
			super(itemView);
			que = itemView.findViewById(R.id.que);
			op1 = itemView.findViewById(R.id.op1);
			op2 = itemView.findViewById(R.id.op2);
			op3 = itemView.findViewById(R.id.op3);
			op4 = itemView.findViewById(R.id.op4);
			rg = itemView.findViewById(R.id.gridrg);
			grg = new GridRadioGroup(context, rg);

			grg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(@Nullable RadioGroup group, int checkedId) {
					int pos = getAdapterPosition();
					switch (checkedId) {
						case R.id.op1:
							questions.get(pos).setAns(0);
							break;
						case R.id.op2:
							questions.get(pos).setAns(1);
							break;
						case R.id.op3:
							questions.get(pos).setAns(2);
							break;
						case R.id.op4:
							questions.get(pos).setAns(3);
							break;
					}
				}
			});
		}
	}
}
