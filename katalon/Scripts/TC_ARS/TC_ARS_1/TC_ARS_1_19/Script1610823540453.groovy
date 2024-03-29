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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CustomKeywords.'com.mensadigitale.MensaDigitale.navigateWithCookie'('http://localhost:8080/mensadigitale/attivazione.jsp')

WebUI.setText(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Nome_nome'), 'Mario')

WebUI.setText(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Cognome_cognome'), 'Rossi')

WebUI.setText(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Datadi Nascita_dataDiNascita'), '20/02/1999')

WebUI.setText(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Comunedi Nascita_comuneDiNascita'), 
    'Napoli')

WebUI.setText(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Indirizzo_indirizzo'), 'Via roma 123')

WebUI.setText(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Provinciadi Nascita_provinciaDiNascita'), 
    'NA')

WebUI.setText(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Cittadinanza_cittadinanza'), 'Italiana')

WebUI.setText(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_CodiceFiscale_codiceFiscale'), 'RSSMRA99B20F839J')

WebUI.click(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Si_rifugiato'))

WebUI.click(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Si_residenzaNucleo'))

WebUI.setText(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_IndirizzoEmail_email'), 'm.rossi999@studenti.unisa.it')

WebUI.setText(findTestObject('Page_Attivazione/Page_MD_Login/input_ConfermaIndirizzo Email_confermaEmail'), 'm.rossi999@studenti.unisa.it')

WebUI.click(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Presentazionedati relativi alla condi_fa2033'))

WebUI.click(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Dichiarazione_responsabilita'))

WebUI.click(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Acconsento_btn btn-success text-center'))

WebUI.verifyAlertPresent(2)

WebUI.closeBrowser()

