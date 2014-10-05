package com.dms.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dms.pojo.SimpleMark;
import com.dms.service.MarkService;

public class MarkTest extends JunitBase {
	@Autowired
	private MarkService markService;

	@Test
	public void testGetMonthMark() {
		long startTime = System.currentTimeMillis();
		List<SimpleMark> marks = markService.getMonthMark(1, "201408");
		if (marks != null && marks.size() > 0) {
			for (SimpleMark mark : marks) {
				// System.out.println(mark.getSorceValue2());
				System.out.println("sorceCount: " + mark.getSorceCountValue());
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("执行时间：" + (endTime - startTime));
	}
}
