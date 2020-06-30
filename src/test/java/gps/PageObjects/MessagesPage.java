package gps.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import gps.Base.BaseClass;

public class MessagesPage extends BaseClass
{
	//Messages Page WebElements
	By MessageMenu      = By.xpath("//a[@title='Message List']");
	By MessageNew       = By.xpath("(//*[contains(text(),'new')])[3]");
	By MessageToSelf    = By.id("ctl00_cphBody_grouplink_rblVisibility_0");
	By MessageToOther   = By.id("ctl00_cphBody_grouplink_rblVisibility_1");
	By UsersBox         = By.id("ctl00_cphBody_grouplink_txtAddPerson");
	By AddUserBtn       = By.id("ctl00_cphBody_grouplink_btnAddUser");
	By MessageSubject   = By.id("ctl00_cphBody_txtSubject");
	By TextEditor       = By.xpath("//body[@class='cke_show_borders']");
	By ShowMenuItem     = By.xpath("//a[@id='showMenu']");
	By MessageSendBtn   = By.id("ctl00_cphBody_btnSend");
	By MessageTextSearch= By.id("ctl00_cphBody_txtSearch");
	By MessageSearchBtn = By.id("ctl00_cphBody_lbtnSearch");
	By MessageList      = By.xpath("//table[@id='ctl00_cphBody_gvMessageList']/tbody/tr/td[1]");
	By MessageBody      = By.id("ctl00_cphBody_divMessageDescription");
	
	/*=====================================================================================================================*/
	//Method to Navigate to Compose New Message Menu
	public void goToNewMessageMenu()
	{
		driver.findElement(MessageNew).click();
	}
	
	//Method to Naivgate to Messages Menu
	public void goToMessagesListMenu()
	{
		driver.findElement(MessageMenu).click();
	}
	
	/* Method to Compose New Message either to Self or To Selected People(including Self User) on basis of "To" Parameter
	and check if Message is shown in Messages List or not 
	*/
	public boolean composeMsg(String To, String Users, String Subject, String MessageBody) throws InterruptedException 
	{
		//This if Block will send message to Self User only
		if(To.equalsIgnoreCase("Self"))                
		{
			driver.findElement(MessageToSelf).click();
			driver.findElement(MessageSubject).sendKeys(Subject);
			logger.info("ComposeMsgSelf: Message Subject Entered");
			
		    driver.switchTo().frame(0);
			driver.findElement(TextEditor).sendKeys(MessageBody);
			logger.info("ComposeMsgSelf: Message Text Entered");
			
			driver.switchTo().defaultContent();
			//driver.findElement(MessageSendBtn).click();
			driver.findElement(ShowMenuItem).click();
			int i=filterMsgInMsgList(Subject);
			if(i==1)
			{
				String MessageTextRead=readMsg(Subject);
				if(MessageTextRead.equals(MessageBody))
					return true;
				else 
					return false;
			}
		}
		//This else block will send message to Selected People
		else
			
		{
			driver.findElement(MessageToOther).click();
			WebElement user=driver.findElement(UsersBox);
			String[] values=Users.split(",");
			for(String u : values)
			{
				user.sendKeys(u);
				driver.findElement(AddUserBtn).click();
				user=getElement(driver,user,By.id("ctl00_cphBody_grouplink_txtAddPerson"));
			}
			driver.findElement(MessageSubject).sendKeys(Subject);
			logger.info("ComposeMsgOther: Message Subject Entered");
			
			driver.switchTo().frame(0);
			driver.findElement(TextEditor).sendKeys(MessageBody);
			logger.info("ComposeMsgOther: Message Text Entered");
			
			driver.switchTo().defaultContent();
			//driver.findElement(MessageSendBtn).click();
			driver.findElement(ShowMenuItem).click();
			int i=filterMsgInMsgList(Subject);
			if(i==1)
			{
				String MessageTextRead=readMsg(Subject);
				
				if(MessageTextRead.equals(MessageBody))
					return true;
				else 
					return false;
			}
		}
		return false;
	}
	
	/*Below Method is used to Filter Message in Msg list using Message Subject
	 and return if Message with same subject exists or not */
	public int filterMsgInMsgList(String Subject) throws InterruptedException
	{
		Actions action = new Actions(driver);
	    action.moveToElement(driver.findElement(MessageMenu)).click().perform();
		driver.findElement(MessageTextSearch).sendKeys(Subject);
		driver.findElement(MessageSearchBtn).click();
		int count=driver.findElements(MessageList).size();
		String BeforePath="//table[@id='ctl00_cphBody_gvMessageList']/tbody/tr[";
		String AfterPath="]/td[1]/a";
		for(int i=2;i<=count+1;i++)
		{
			String FirstPath=BeforePath+i+AfterPath;
       		String SubjectText_fromMessages=driver.findElement(By.xpath(FirstPath)).getText();
			if(SubjectText_fromMessages.equals(Subject))
				return 1;
		}
		return 0;
	}
	
	/* Below Method is used to Read Msg for the Message with Same Message Subject and 
	 and will return MessageText*/
	public String readMsg(String Subject)
	{
		int count=driver.findElements(MessageList).size();
		String BeforePath="//table[@id='ctl00_cphBody_gvMessageList']/tbody/tr[";
		String AfterPath="]/td[1]";
		for(int i=2;i<=count+1;i++)
		{
			String FinalPath=BeforePath+i+AfterPath;
			String MessageDetailsLink=FinalPath+"/a";
			String messageSubject=driver.findElement(By.xpath(FinalPath)).getText();
			if(messageSubject.equals(Subject))
					{
				         driver.findElement(By.xpath(MessageDetailsLink)).click();
				         String MessageDescription=driver.findElement(MessageBody).getText();
				         return MessageDescription;
				    }
		}
	  return null;
	}

}
