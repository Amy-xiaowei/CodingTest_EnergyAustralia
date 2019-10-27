# CodingTest_EnergyAustralia
the coding test from EnergyAustralia
How to use?
You can access http://ec2-18-221-216-245.us-east-2.compute.amazonaws.com:8080/bandfestival/index , please click the button "clickMe" to display the data. I deployed my bandfestival.war on the Tomcat in one AWS EC2 instance I created.

I use Tomcat(8.5)+Maven+Spring MVC to implement the coding challenge, I've already export the project to bandfestival.war. Please deploy the bandfestival.war to your Tomcat, or you can import  the project bandfestival to your Eclipse, please use jre1.8. 

I finished the coding test on 25 Oct and export the project to testcode.war which has been proved work well with the test URL. From 26 Oct, I've changed the project name and added some comments to the code to make the project more formal and added some code to make the UI more beautiful, so now the project is called bandfestival and it's exported to bandfestival.war, but I  haven't got the URL to test bandfestival.war as the URL you provided is not working on weekends. Thus, if bandfestival.war doesn't work well, please use testcode.war. 

How to access?
Please access the address http://localhost:8080/bandfestival/index from any browser i.e. Chrome, IE. You will see there is a button("clickMe"), please click it then you can see the UI showing the data.

If the correct response from URL("http://eacodingtest.digital.energyaustralia.com.au/api/v1/festival") is retrieved, it will display the data on the web page as required; if there is no response from URL, it will prompt - No data returned, please try later after around 30s.

I did TDD Junit test, please see the src/main/test/MainTest.java.
