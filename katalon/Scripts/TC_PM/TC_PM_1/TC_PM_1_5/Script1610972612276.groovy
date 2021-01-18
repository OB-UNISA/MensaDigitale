import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CustomKeywords.'com.mensadigitale.LoginAddetto.navigateWithCookieAndParams'('http://localhost:8080/mensadigitale/piatto', 
    'action=getTuttiPiatti&destination=compilaMenu.jsp')

WebUI.click(findTestObject('Object Repository/Page_Menu/Page_NuovoMenu/Page_Compila Men/button_Nuovo Piatto'))

WebUI.waitForElementPresent(findTestObject('Page_Menu/Page_NuovoMenu/Page_Compila Men/input_Nome_nomePiatto'), 3)

WebUI.setText(findTestObject('Object Repository/Page_Menu/Page_NuovoMenu/Page_Compila Men/input_Nome_nomePiatto'), 'Pizza')

WebUI.setText(findTestObject('Object Repository/Page_Menu/Page_NuovoMenu/Page_Compila Men/input_Ingredienti_ingredienti'), 
    'LATTE,FARINA')

WebUI.setText(findTestObject('Object Repository/Page_Menu/Page_NuovoMenu/Page_Compila Men/input_Calorie_calorie'), '1')

WebUI.setText(findTestObject('Object Repository/Page_Menu/Page_NuovoMenu/Page_Compila Men/input_Proteine_proteine'), '1')

WebUI.setText(findTestObject('Object Repository/Page_Menu/Page_NuovoMenu/Page_Compila Men/input_Grassi_grassi'), '-1')

WebUI.click(findTestObject('Object Repository/Page_Menu/Page_NuovoMenu/Page_Compila Men/button_Salva'))

WebUI.verifyAlertNotPresent(2)

WebUI.closeBrowser()

