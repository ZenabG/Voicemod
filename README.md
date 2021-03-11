# Voicemod

This is a maven project created using **Selenium Framework with Java**.

The project follows **Page object model** mechanism with **Page Factory**. **Javadoc** is added for each method. **Extent Report** is used for creating reports with logs and screenshot (only in case of failure). **TestNG framework** is used for executing the tests.

The project hierarchy is ->

**src/main/java** :
consists of 3 packages - base, pages and utilities. **Base** package consists of BaseClass and Constants. **Pages** package consists of WebElements and methods for each page. **Utilities** package consists of excel sheet will all manual test steps and geckodriver.

**src/test/java** :
consists of 3 packages - tests, voicemodDownloads and test-XMLs. **Tests** package consists of TestNG test created for the exercise. **VoicemodDownloads** stores all the downloaded exe for installing voicemod. **Test-XMLs** consists of TestNG suite for running all the tests.

**test-extent** - this folder stores extent report for each run and screenshots for failed test step.

**Note:**

- If the project is imported in eclipse, add the TestNG libraries from Help > Intall New Software > update site URL in "Work with:" field >    https://dl.bintray.com/testng-team/testng-eclipse-release/. The testNG dependency is added in pom.xml but still this step is needed in eclipse to install TestNG libraries.
