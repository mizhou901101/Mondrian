package org.demo.test.service;

import org.demo.core.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("/spring/application.xml")
public class IndexServiceTest {
	
	@Autowired
	private IndexService indexService;
	
	@Test
	public void test() {
		System.out.println(indexService.getSentence());
	}
}
