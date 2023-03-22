# testng-browserstack

[TestNG](http://testng.org) Integration with BrowserStack.

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)



- Clone the repository
- Replace YOUR_USERNAME and YOUR_ACCESS_KEY with your BrowserStack access credentials in browserstack.yml.
- Install dependencies `mvn compile`
- To run the test suite having cross-platform with parallelization, run `mvn test -P sample-test`
- To run local tests, run `mvn test -P sample-local-test`

Understand how many parallel sessions you need by using our [Parallel Test Calculator](https://www.browserstack.com/automate/parallel-calculator?ref=github)


This repository uses the BrowserStack SDK to run tests on BrowserStack. Follow the steps below to install the SDK in your test suite and run tests on BrowserStack:

* Create sample browserstack.yml file with the browserstack related capabilities with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings) and place it in your root folder.
* Add maven dependency of browserstack-java-sdk in your pom.xml file
```sh
<dependency>
    <groupId>com.browserstack</groupId>
    <artifactId>browserstack-java-sdk</artifactId>
    <version>LATEST</version>
    <scope>compile</scope>
</dependency>
```
* Modify your build plugin to run tests by adding argLine `-javaagent:${com.browserstack:browserstack-java-sdk:jar}` and `maven-dependency-plugin` for resolving dependencies in the profiles `sample-test` and `sample-local-test`.
```
            <plugin>
               <artifactId>maven-dependency-plugin</artifactId>
                 <executions>
                   <execution>
                     <id>getClasspathFilenames</id>
                       <goals>
                         <goal>properties</goal>
                       </goals>
                   </execution>
                 </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>config/sample-local-test.testng.xml</suiteXmlFile>
                    </suiteXmlFiles>
                    <argLine>
                        -javaagent:${com.browserstack:browserstack-java-sdk:jar}
                    </argLine>
                </configuration>
            </plugin>
```
* Install dependencies `mvn compile`



- Clone the repository
- Install dependencies `gradle build`
- To run the test suite having cross-platform with parallelization, run `gradle sampleTest`
- To run local tests, run `gradle sampleLocalTest`

Understand how many parallel sessions you need by using our [Parallel Test Calculator](https://www.browserstack.com/automate/parallel-calculator?ref=github)


This repository uses the BrowserStack SDK to run tests on BrowserStack. Follow the steps below to install the SDK in your test suite and run tests on BrowserStack:

* Following are the changes required in `gradle.build` -
    * Add `compileOnly 'com.browserstack:browserstack-java-sdk:latest.release'` in dependencies
    * Fetch Artifact Information and add `jvmArgs` property in tasks *SampleTest* and *SampleLocalTest* :
  ```
  def browserstackSDKArtifact = configurations.compileClasspath.resolvedConfiguration.resolvedArtifacts.find { it.name == 'browserstack-java-sdk' }
  
  task sampleTest(type: Test) {
    useTestNG() {
      dependsOn cleanTest
      useDefaultListeners = true
      suites "config/sample-test.testng.xml"
      jvmArgs "-javaagent:${browserstackSDKArtifact.file}"
    }
  }
  ```

* Install dependencies `gradle build`

## Commands to execute test
### Using CLI command
#### Web
* Single Run - `mvn test -P single_web -Dbrowserstack.config=browserstack-web.yml`
* Local Run - `mvn test -P local_web -Dbrowserstack.config=browserstack-web.yml`

#### Android
* Single Run - `mvn test -P single_android -Dbrowserstack.config=browserstack-android.yml`
* Local Run - `mvn test -P local_android -Dbrowserstack.config=browserstack-android.yml` - Change `app` path in the `browserstack-android.yml` file

#### iOS
* Single Run - `mvn test -P single_ios -Dbrowserstack.config=browserstack-ios.yml`
* Local Run - `mvn test -P local_ios -Dbrowserstack.config=browserstack-ios.yml` - Change `app` path in the `browserstack-ios.yml` file

### Using Environment variable
* Web - `export BROWSERSTACK_CONFIG_FILE=browserstack-web.yml`
* Android - `export BROWSERSTACK_CONFIG_FILE=browserstack-android.yml`
* iOS - `export BROWSERSTACK_CONFIG_FILE=browserstack-ios.yml`

#### Web
* Single Run - `mvn test -P single_web`
* Local Run - `mvn test -P local_web`

#### Android
* Single Run - `mvn test -P single_android`
* Local Run - `mvn test -P local_android` - Change `app` path in the `browserstack-android.yml` file

#### iOS
* Single Run - `mvn test -P single_ios`
* Local Run - `mvn test -P local_ios` - Change `app` path in the `browserstack-ios.yml` file


* You can view your test results on the [BrowserStack Automate dashboard](https://www.browserstack.com/automate)
