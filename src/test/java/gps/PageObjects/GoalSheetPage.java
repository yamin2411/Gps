package gps.PageObjects;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gps.Base.BaseClass;

public class GoalSheetPage extends BaseClass
{	
	//GoalSheet Page WebElements
	By GoalTextBox   = By.id("ucAddGoal_txtAddGoal");
	By AddGoalBtn    = By.id("ucAddGoal_btnAddGoal");
	By UpdateBtn     = By.id("btnUpdate");
	By CommentTxtBox = By.id("ucNote_txtNote");
	By AddCommentBtn = By.id("ucNote_btnAddNote");
	By WFhcheckbox   = By.id("chkWFH");
	By GoalSheetDate = By.xpath("//span[@id='lblGoalDate']");
	By ProjectName   = By.xpath("//span[@class='projectNameStyle']");
	By CommentList   = By.xpath("//div[@class='noteTextStyle']");
	
	
	/*=====================================================================================================================*/
	
	/*Below method is used to Add goal in Goal Sheet in case if same goal is not existing using SearchGoalMethod
	and check if Goal added successfully or not using same SearchGoal Method */
	public int addGoal(String Goal)
	{
		int i=searchGoal(Goal);     //SearchGoal Method Returns if Goal is alread added in Goalsheet or not
		if(i==0)
		{
			driver.findElement(GoalTextBox).sendKeys(Goal);
			logger.info("AddGoal: Entered Goal");
			driver.findElement(AddGoalBtn).click();
			driver.navigate().refresh();
			logger.info("AddGoal: Refreshed Screen after GoalAdd");
			i=searchGoal(Goal);
			if(i!=0)
			    return i;
			else
				return -1;
		}
		else
	       return 0;
    }
	
	/* Below Method is used to Add Note for a Specific Goal 
	   only when Goal is already existing & Goal UpdateStatus Checkbox Not disabled & No existing note found
	  and after addition returns if Note is added successfully or not
	 */
	public int addNote(String Note,String GoalText) throws InterruptedException
	{
	   ArrayList<WebElement> GoalSheet_GoalsList= new ArrayList<WebElement>( driver.findElements(By.xpath("//table[@id='dgGoals']/tbody/tr/td[2]")));
	   for(int i=2;i<=GoalSheet_GoalsList.size();i++)
	   {
		   String BeforeXpath1="//table[@id='dgGoals']/tbody/tr[";
		   String AfterXpath1="]/td[2]/span";
		   String AfterXpath2="]/td[3]/input";
		   String Xpath=BeforeXpath1+i+AfterXpath1;
		   String Xpath2=BeforeXpath1+i+AfterXpath2;
		   String Text_Goals_GoalSheet=driver.findElement(By.xpath(Xpath)).getText();
		   if(GoalText.equalsIgnoreCase(Text_Goals_GoalSheet))
			   {
			     
			     if(goalDisable(i) && isNoteFieldNotEmpty(i))
			     {
			             return 3;
			     }
			     else 
			     {
			    	 driver.findElement(By.xpath(Xpath2)).sendKeys(Note);
			    	 
			    	// driver.findElement(UpdateBtn).click();
			    	 driver.navigate().refresh();
				      String Notetext_loclVar=driver.findElement(By.xpath(Xpath2)).getAttribute("value");
				      if(Notetext_loclVar.equals(Note))
				           return 1;
				      else 
				    	  return 2;   
	   		    }
             }
	    }
	  return 0;
    }
	
	
	/*Below Method is used to Add Comment for the Day which is being sent from GoalSheet Test case 
	 and returns if comment is added successfully or not.
	 */
	public int addCommentOfDay(String Comment) throws InterruptedException
	{
		driver.findElement(CommentTxtBox).sendKeys(Comment);
		//driver.findElement(AddCommentBtn).click();
		driver.navigate().refresh();
		if(getCommentofDay(Comment))
			return 1;
		else 
			return 0;
		
	}
	
	/*Below Method is used to Update a Goal if Goal exists in GoalSheet and is not already being updated 
	 */
	public int updateGoal(String GoalText)
	{
		int seq= searchGoal(GoalText);
		if(seq!=0) 
		{
			String BeforeXpath="//table[@id='dgGoals']/tbody/tr[";
			String AfterXpath="]/td[1]/input[2]";
			String AfterXapth2="]/td[1]";
			String Xpath1=BeforeXpath+seq+AfterXpath;
			String Xpath2=BeforeXpath+seq+AfterXapth2;
		    WebElement temp=driver.findElement(By.xpath(Xpath1));
		    if(temp.isEnabled()==true)
		    {
		    	driver.findElement(By.xpath(Xpath2)).click();
		    	driver.findElement(UpdateBtn).click();
		    	driver.navigate().refresh();
		    	if(goalDisable(seq))
		        		return 1;
		    	
		    	else 
		    		return 2;
		    }
		    else
		    	return 3;
		}
	  return 0;
	 }
	
	/*Below method is just used to check if a Goal exists in GoalSheet or not 
	 */
	public int searchGoal(String GoalText)
	{
		ArrayList<WebElement> ar= new ArrayList<WebElement>( driver.findElements(By.xpath("//table[@id='dgGoals']/tbody/tr/td[2]")));
		for(int i=2;i<=ar.size();i++)
		   {
			   String BeforeXpath="//table[@id='dgGoals']/tbody/tr[";
			   String AfterXpath="]/td[2]/span";
			   String Xpath=BeforeXpath+i+AfterXpath;
			   
			   String GoalText_FromGoalSheet=driver.findElement(By.xpath(Xpath)).getText();
			    if(GoalText.equalsIgnoreCase(GoalText_FromGoalSheet))
				   {
				     return i;
				   }
		
		   }
		   return 0;
	 }
	

	/*Below Method will check if a Goal is already updated based on Goal RowNumber in GoalSheet
	 */	
	
	public boolean goalDisable(int GoalNo)
	{
		String BeforeXpath="//table[@id='dgGoals']/tbody/tr[";
		String AfterXpath="]/td[1]/input";
		String Xpath1=BeforeXpath+GoalNo+AfterXpath;
		String ElementDisable=driver.findElement(By.xpath(Xpath1)).getAttribute("value");
		if(ElementDisable.equals("1"))
			return true;
		else
		   return false;
		
	}
	
	/* Below Method will check if there is any existing note for a Goal or not based on Note Rownumber in GoalSheet
	 */
	public boolean isNoteFieldNotEmpty(int NoteNoInGoalSheet)
	{
		 String BeforeXpath="//table[@id='dgGoals']/tbody/tr[";
		 String AfterXpath="]/td[3]/input";
		 String Xpath=BeforeXpath+NoteNoInGoalSheet+AfterXpath;
		 if(driver.findElement(By.xpath(Xpath)).getAttribute("value")==null)
              return false;
		 else
		      return true;
		
	}
	
	/*Below method will get the Comment of the Day Text if there is any */
	public boolean getCommentofDay(String Comment)
	{
		WebElement AddedComments  = driver.findElement(CommentList);
		String CommentText_FromGoalSheet=AddedComments.getText();
		if(Comment.equalsIgnoreCase(CommentText_FromGoalSheet))
			return true;
		else
			return false;
	}
	
	/* Below Method compares GoalSheetDate with CurrentDate
	 * */
	public boolean compareGoalSheetDate(String Currentdate)
	{
		String DateFromGoalSheet=driver.findElement(GoalSheetDate).getText();
		if(DateFromGoalSheet.equals(Currentdate))
			return true;
		else
			return false;
	}
	
	/*Below Method will check Employee Lead Count and will return leadcount if lead exists and 0 in case there is not lead
	 */
	public int countEmployeeLead()
	{
		ArrayList<WebElement> WorkLeadList  = new ArrayList<WebElement>(driver.findElements(By.xpath("//span[@id='lblWorksWithLead']/a")));
		int i =WorkLeadList.size();
		if(i==0)			
           	return 0;		
		else 
		{
		    System.out.print("LeadNames are: ");
			for(WebElement s : WorkLeadList)
			{
				System.out.print(s.getText()+", ");
			}
			System.out.println();
			return i;
		}
	}
	/*Below Method will return Employee's Full Time Project name if any
	 */
	public String employeeFullTimeProject()
	{
		String project_name=driver.findElement(ProjectName).getText();
		if(project_name==null)
			return null;
		else
			return project_name;
	}

}
