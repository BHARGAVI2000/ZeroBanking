package Utility;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenShot {
	WebDriver driver;

	public static String getScreenShot(WebDriver driver, String screenshotName ) throws IOException

	{
		String dateName= new SimpleDateFormat("yyyyMMddmmss").format(new Date());		
		//String dateName= new SimpleDateFormat("yyyymmddmmss").format(new Date());
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		String destination=System.getProperty("user.dir")+"/Screenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(src, finalDestination);

		return destination;
	}

}
