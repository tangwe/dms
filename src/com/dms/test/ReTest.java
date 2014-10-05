package com.dms.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.dms.service.RepairService;

public class ReTest extends JunitBase {
	@Autowired
	private RepairService repairService;

	public void testFind() {
	}
}
