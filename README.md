 Project Name : GoalPlanSheet(GPS)
 
 ********************************************************************************************************
 General Info :  

 * This is an Automation Framework for testing GoalPlanSheet(GPS). It is based on Page Object Model. 
 * Package gps.Base has Base class holds the methods to initialize and terminate the WebDriver object 
   and also methods common for all classes.
 * Package gps.PageObjects contains Page classes which keep Page WebElements and methods performing 
   action on these webElements.
 * Package gps.TestCases contains Test classes having Test cases that rely on Page object WebElements 
   and methods.
 * Package gps.Utilities contains classes with utility methods for eg ReadFromProperties, ReadFromExcel.

  ********************************************************************************************************
  Prerequisites
  * Java(JDK)
  * IDE (Eclipse /Intellij)

  ********************************************************************************************************
  Technologies :
     Name                       Version
  * Java                       jdk-13.0.2
  * Selenium-java               3.14.0
  * TestNG                       6.11
  * Maven                       3.5.3
  * Log4j                       1.2.17
  * Extent Reports              3.1.5
 
  *********************************************************************************************************
  Running the Tests:
 
  * Import Project into Eclipse or any other IDE.
  * Open testng.xml file and provide values for Parameters such as Browser(on which you want to run test),
    ReadFrom(from where you want to read username/password) .
  * Provide Test class name(which you wish to execute) in classes section and Save File.
  * Open pom.xml file and right click on Run As and run as Maven test
  * Go to Reports folder and Check Report with Current Date and time and Open with System Editor.
  * Go to Screenshots folder and check screenshots for Failed Tests(if any).
  * Go to Logs folder and check logs in Logs file.
 
 ****************************************************************************************************
 
