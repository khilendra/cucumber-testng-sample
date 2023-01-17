package MyRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.URL;


@CucumberOptions(
        features = "src/main/java/Features",
        glue = {"stepDefinitions"},
        plugin = "json:target/cucumber-reports/CucumberTestReport.json")

public class TestRunner extends AbstractTestNGCucumberTests {

    private TestNGCucumberRunner testNGCucumberRunner;

    public static RemoteWebDriver connection;

    @BeforeClass(alwaysRun = true)
    public void setUpCucumber() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeMethod(alwaysRun = true)
    //@Parameters({"browser", "version", "platform"})
    public void setUpClass() throws Exception {

        String username = "ataglance.test123";
        String accesskey = "77aWTkeqXFsdQ5XOrE6n9rRx1efsvN4SnK98s5B8xhqSVro45T";

        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capability.setCapability(CapabilityType.VERSION, "latest");
        capability.setCapability(CapabilityType.PLATFORM, "Windows 10");

        capability.setCapability("build", "Cucumber Sample Build");

        capability.setCapability("network", true);
        capability.setCapability("video", true);
        capability.setCapability("console", true);
        capability.setCapability("visual", true);

        String gridURL = "https://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub";
        System.out.println(gridURL);
        connection = new RemoteWebDriver(new URL(gridURL), capability);
        System.out.println(capability);
        System.out.println(connection.getSessionId());
    }


    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }
}