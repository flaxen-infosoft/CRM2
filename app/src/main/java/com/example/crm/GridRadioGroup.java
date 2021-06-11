package com.example.crm;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class GridRadioGroup {
	int[] radioButtonId = {R.id.op1, R.id.op2, R.id.op3, R.id.op4};
	ArrayList<RadioButton> radioButtons;
	RadioGroup.OnCheckedChangeListener rglistener;

	public GridRadioGroup(Context context, RadioGroup rg) {
		radioButtons = new ArrayList<>();
		CompoundButton.OnCheckedChangeListener rblistener = (buttonView, isChecked) -> {
			if (isChecked) {
				for (int i = 0; i < 4; i++) {
					if (buttonView.getId() == radioButtonId[i]) {
						radioButtons.get(i).setChecked(true);
						if (rglistener != null) {
							rglistener.onCheckedChanged(null, radioButtonId[i]);
						}
					} else
						radioButtons.get(i).setChecked(false);
				}
			}
		};

		for (int i = 0; i < 4; i++) {
			RadioButton rb = rg.findViewById(radioButtonId[i]);
			rb.setOnCheckedChangeListener(rblistener);
			radioButtons.add(rb);
		}
	}

	public void setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener listener) {
		this.rglistener = listener;
	}

	public int getCheckedChildId() {
		for (int i = 0; i < 4; i++) {
			if (radioButtons.get(i).isChecked())
				return radioButtonId[i];
		}
		return 0;
	}

	public void setCheked(int pos) {
		radioButtons.get(pos).setChecked(true);
	}
}
