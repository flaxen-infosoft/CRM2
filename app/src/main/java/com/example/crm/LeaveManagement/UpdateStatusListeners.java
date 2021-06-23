package com.example.crm.LeaveManagement;

import com.example.crm.Model.LeaveItem;

public interface UpdateStatusListeners {
	public void approve(LeaveItem leaveItem);

	public void reject(LeaveItem leaveItem);
}
