package stepDefinitions;

import MyRunner.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;


public class MenuHelpStepDefinition extends TestRunner {

	public RemoteWebDriver driver = this.connection;

	
	@Given("^open home Page$")
	public void user_home_page() throws InterruptedException {
		System.out.println(driver.getCapabilities());
		driver.get("https://app.dev.conceptsnbeyond.com/index.html?clientKey=03dd6b9c-96aa-3c20-badb-7bef7311a9b6");
		Thread.sleep(5000);
	}

	@When("^User clicks Menu$")
	public void user_click_menu() {
		WebElement li = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div[1]/div[1]/div[1]/button"));
		li.click();
	}

	@Then("User clicks Help")
	public void user_click_help_menu() {
		WebElement li = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div/div/ul/li[5]/a/i/span"));
		li.click();
	}

//	@Then("^user clicks Help$")
//	public void select_second_item() {
//		WebElement li = driver.findElement(By.name("li2"));
//		li.click();
//	}

//	@Then("^user clicks Help$")
//	public void user_clicks_help() throws InterruptedException {
//		WebElement text = driver.findElement(By.id("sampletodotext"));
//		text.clear();
//		driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
//		Thread.sleep(1000);
//		driver.findElement(By.id("addbutton")).click();
//		Thread.sleep(2000);
//	}

//	@Then("Ensure presence of new window {string}")
//	public void text_exists() {
//		String item = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[6]/span")).getText();
//		Assert.assertTrue(item.contains("At a Glance 2.0 - At a glance - Confluence"), "Expected : Yey, Let's add it to list Actual : " + item);
//	}

	@Then("Ensure presence of new window {string}")
	public void ensurePresenceOfSite(String newTitle) {
		String winHandleBefore = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		assertEquals(newTitle, driver.getTitle());
		driver.close();
		driver.switchTo().window(winHandleBefore);
	}

}
