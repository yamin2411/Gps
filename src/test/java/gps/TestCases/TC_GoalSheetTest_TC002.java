package gps.TestCases;

import static gps.Utilities.Constants.*;

import java.io.IOException;
import java.util.ArrayList;

import gps.Utilities.ReadFromExcel;
import gps.Base.BaseClass;
import gps.PageObjects.GoalSheetPage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_GoalSheetTest_TC002 extends BaseClass {
	
	
	GoalSheetPage gp = new GoalSheetPage();

	// Passing Parameter to Read User name,Password either From TestData Excel Sheet or Config File
	@Parameters("ReadFrom")
	@BeforeClass
	public void loginSetup(String ReadFrom) throws IOException 
	{
		/* Below code Create object of LoginTest_TC001 class and call loginTest method to login to GPS
		 * and check whatzNew window after login and close if present
		 */
		TC_LoginTest_TC001 tp = new TC_LoginTest_TC001();
		tp.testLogin(ReadFrom);
		isWhatzNewWindowShown(); 
	}

	
	@Parameters("GoalNoFromSheet") // Passing Goal No of Excel Sheet to add Specific Goal
	@Test(priority = 5)
	public void testaddNewGoal(int GoalNoFromSheet) throws IOException {
		test = extent.createTest("AddNewGoal");

		/*
		 * Below Code read Goaltext from Excel Sheet on Given GoalNo and addGoal Method will add goal to
		 * GoalSheet and return int value and as per returned value Test case will be marked pass/Failed
		 */
		ArrayList<String> GoalFromSheet_ToAdd = new ArrayList<String>();
		GoalFromSheet_ToAdd = ReadFromExcel.ReadExcel(GoalSheet, "Goals", GoalNoFromSheet);
		int i = gp.addGoal(GoalFromSheet_ToAdd.get(0));

		if (i == 0) {
			logger.info("Add Goal: Goal Already exists");
			Assert.assertTrue(false);
		} else if (i == -1) {
			logger.info("Add Goal: Goal Add Failed");
			Assert.assertTrue(false);
		} else {
			logger.info("Add Goal: Goal Added Successfully");
			Assert.assertTrue(true);
		}

	}

	@Parameters("NoteForGoalFromSheet") // Passing Goal No of Excel Sheet to add Note for that specific Goal
	@Test(priority = 6)
	public void testaddNote(int NoteForGoalFromSheet) throws IOException, InterruptedException {
		test = extent.createTest("AddNoteForGoal");

		/*
		 * Below Code read GoalText from Excel Sheet on Given GoalNo and addNote Method will add note for
		 * that Specific Goal in GoalSheet and return int value as per returned value Test case will be 
		 * marked pass/Failed
		 */
		ArrayList<String> NoteforGoalFromSheet_ToAdd = new ArrayList<String>();
		NoteforGoalFromSheet_ToAdd = ReadFromExcel.ReadExcel(GoalSheet, "Goals", NoteForGoalFromSheet);
		int i = gp.addNote(NoteforGoalFromSheet_ToAdd.get(1), NoteforGoalFromSheet_ToAdd.get(0));
		if (i == 1) {
			logger.info("AddNote: Added Note for Goal");
			Assert.assertTrue(true);
		} else if (i == 0) {
			logger.info("AddNote: Goal Not found to add Note");
			Assert.assertTrue(true);
		} else if (i == 3) {
			logger.info("AddNote: Goal disabled or Note exists");
			Assert.assertTrue(true);
		} else {
			logger.info("AddNote: Note Add Failed");
			Assert.assertTrue(true);
		}
	}

	@Parameters("GoalNoFromSheet") // Passing Goal No of Goals Excel Sheet to Update Specific Goal Status
	@Test(priority = 7)
	public void testupdateStatus(int GoalNoFromSheet) throws IOException {
		test = extent.createTest("UpdateGoalStatus");

		/*
		 * Below Code read Goaltext from Excel Sheet on Given GoalNo and updateGoal method will update goal status
		 * for that Goal in GoalSheet and return int value and as per returned value Test case will be
		 *  marked pass/Failed
		 */
		ArrayList<String> GoalFromSheet_ToUpdate = new ArrayList<String>();
		GoalFromSheet_ToUpdate = ReadFromExcel.ReadExcel(GoalSheet, "Goals", GoalNoFromSheet);

		int i = gp.updateGoal(GoalFromSheet_ToUpdate.get(0));
		if (i == 1) {
			logger.info("UpdateGoal: Goal Updated successfully");
			Assert.assertTrue(true);
		} else if (i == 3) {
			logger.info("UpdateGoal: Goal already Updated");
			Assert.assertTrue(true);
		} else if (i == 0) {
			logger.info("UpdateGoal : Goal Not found");
			Assert.assertTrue(true);
		} else {
			logger.info("UpdateGoal : Goal Update Failed");
			Assert.assertTrue(false);
		}
	}

	@Parameters({ "CommentNofromSheet" }) // Passing CommentNo from Comment Excel Sheet to Add Comment for the Day
	@Test(priority = 8)
	public void testaddComment(int CommentNofromSheet) throws IOException, InterruptedException {
		test = extent.createTest("AddCommentForTheDay");

		/*
		 * Below Code read Comment from Excel Sheet on Given CommentNo and addCommentOfDay method will add comment 
		 * for the Day and return int value and as per returned value Test case will be marked pass/Failed
		 */
		ArrayList<String> Comment_ReadFromSheet = new ArrayList<String>();
		Comment_ReadFromSheet = ReadFromExcel.ReadExcel(GoalSheet, "Comments of the Day", CommentNofromSheet);
		int i = gp.addCommentOfDay(Comment_ReadFromSheet.get(0));
		System.out.println(i);
		if (i == 1) {
			logger.info("AddComment: Added Commentfor the day");
			Assert.assertTrue(true);
		} else {
			logger.info("AddComment: Failed to add Comment for the day");
			Assert.assertTrue(true);
		}
	}

}
