package com.hook;

import com.base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks extends BaseClass {
	@Before
	public void beforeScenario(Scenario scenario)
	{
		launchDriver();
		log.info("Scenario Executing Start :-"+scenario.getName());
		
	}

	@After
	public void afterScenario(Scenario scenario)
	{
        if(scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
        }
        log.info("Scenario Executing Finish :-"+scenario.getName());
		tearDown();
	}
}
