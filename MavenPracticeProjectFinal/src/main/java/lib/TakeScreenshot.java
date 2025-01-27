package lib;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshot {

	public void CreateScreenShot(WebDriver d1, String screenshotname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)d1;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("./screenshot/"+screenshotname+".png"));
		System.out.println("screenshot is capture");
	}
}
