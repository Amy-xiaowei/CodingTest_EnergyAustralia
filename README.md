# CodingTest_EnergyAustralia
the coding test from EnergyAustralia

I use Tomcat(8.5)+Maven+Spring MVC to implement the coding challenge, I've already export the project to bandfestival.war. Please deploy the bandfestival.war to your Tomcat, or you can import  the project bandfestival to your Eclipse, please use jre1.8.

How to access?
Please access the address http://localhost:8080/bandfestival/index from any browser i.e. Chrome, IE. You will see there is a button("clickMe"), please click it to get the required data.

If the correct response from URL("http://eacodingtest.digital.energyaustralia.com.au/api/v1/festival") is retrieved, it will display the data on the web page as required.

If there is no response from URL, it will prompt - No data returned, please try later after around 30s.

I did TDD Junit test, please see the src/main/test/MainTest.java.
