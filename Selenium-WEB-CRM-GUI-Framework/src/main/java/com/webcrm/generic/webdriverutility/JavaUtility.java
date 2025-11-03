package com.webcrm.generic.webdriverutility;

import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber() {
		Random random= new Random();
		int randomNo= random.nextInt(7000);
		return randomNo;
		
	}
	


}
