# lambdatest-demo
Sample project to demonstrate the use of LambdaTest platform to run automated tests in remote browser sessions.
This project / test suite uses Selenium + TestNG

## Configurations

No additional dependencies specific to LambdaTest need to be added in pom.xml

### Driver Setup
Capabilities / BrowserOptions need to be added inorder to run the tests in LambdaTest Browsers. Use [LambdaTest Capabilities Generator](https://www.lambdatest.com/capabilities-generator/) to generate the same for your required browsers versions

- Sample code for Chrome v121.0 (I am sending the credentials as Parameters and storing them in ``TestParameters.lt_username``, ``TestParameters.lt_accessKey``. U may do the same or hardcode it in the below code.)
```Java
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("121.0");
        
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", TestParameters.lt_username); 
        ltOptions.put("accessKey", TestParameters.lt_accessKey);
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("build", "test-build");
        ltOptions.put("project", "lambdatest-demo");
        ltOptions.put("smartUI.project", "lambdatest-demo");
        ltOptions.put("console", "true");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        
        browserOptions.setCapability("LT:Options", ltOptions);
```

- Initialize the driver
```Java
    driver = new RemoteWebDriver(new URL("https://" + TestParameters.lt_username + ":" + TestParameters.lt_accessKey + "@hub.lambdatest.com/wd/hub"), browserOptions);
```

### Passing the Credentials
- If you would like to pass your LambdaTest during runtime rather than hardcoding it, you can follow the below options:

- As TestNG parameters
```xml
    <parameter name="LTusername" value="YOUR_USERNAME" />
    <parameter name="LTaccessKey" value="YOUR_ACCESSKEY" />
```

- As System Properties
```xml
    <plugin>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.22.1</version>
        <configuration>
            <suiteXmlFiles>
            <suiteXmlFile>testng.xml</suiteXmlFile>
            </suiteXmlFiles>
            <systemProperties>
                <property>
                    <name>LTusername</name>
                    <value>${LTusername}</value>
                </property>
                <property>
                    <name>LTaccessKey</name>
                    <value>${LTaccessKey}</value>
                </property>
            </systemProperties>
        </configuration>
    </plugin>
```
- In this case, you will only be able to trigger the test from CLI

```bash
    mvn clean test -DLTusername=YOUR_USERNAME -DLTaccessKey=YOUR_ACCESSKEY
```

## LambdaTest Dashboard
You will be able to view the Test Build progress and results from the LambdaTest Dashboard. 

- Log in to your Lambdatest account
- Navigate to Automation > Web Automation.

And you will be able to view the In Progress as well as Completed tests and their details including step wise screenshots and execution video, provided you have enabled the same in your capabilities.

![Test Builds](https://github.com/krishnapollu/lambdatest-demo/blob/main/images/test-builds.png)

Click on one of the builds and you will be able to see a detailed view of the test execution

![Test Details](https://github.com/krishnapollu/lambdatest-demo/blob/main/images/test-details.png)

## Detailed Documentation
- [LambdaTest Support Docs](https://www.lambdatest.com/support/docs/)
