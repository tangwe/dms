package com.dms.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dms.service.RoomService;

public class RoomTest extends JunitBase {
	@Autowired
	private RoomService roomService;

	@Test
	public void testAddRoom() {
		for (int i = 0; i < 40; i++) {
			String str = "10";
			if (str.length() > 3) {
				str = str.replace("0", "");
			}
			try {
				roomService.addRoom(str, "备注备注", 1, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testAddRoom1() {
		try {
			roomService.addRoom("101", "备注备注", 1, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
