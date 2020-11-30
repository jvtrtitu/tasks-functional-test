package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TasksTest {

	public WebDriver acessarAplicacao() throws MalformedURLException, InterruptedException {
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.10.4:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.10.4:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		((JavascriptExecutor) driver).executeScript("window.focus();");
		Thread.sleep(2000);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException, InterruptedException {
		WebDriver driver = acessarAplicacao();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		((JavascriptExecutor) driver).executeScript("window.focus();");
		try {
			//clicar em Add todo
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@id='addTodo']")));
			if(driver.findElement(By.xpath("//a[@id='addTodo']")).isEnabled()) {
				driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			}else {
				driver.navigate().refresh();
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@id='addTodo']")));
				driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			}
			
			Thread.sleep(5000);
			
			//escrever descrição
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='task']")));
			driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste Automatizado");
			//escrever a data
			driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("30/11/2020");
			//clicar em salvar 
			driver.findElement(By.xpath("//input[@id='saveButton']")).click();
			//validar mensagem de sucesso
			((JavascriptExecutor) driver).executeScript("window.focus();");
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[@id='message']")));
			String resposta = driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertEquals("Success!", resposta);
		}finally {
			//fechar o browser
			driver.quit();	
		}		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException, InterruptedException {
		WebDriver driver = acessarAplicacao();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		((JavascriptExecutor) driver).executeScript("window.focus();");
		try {
			//clicar em Add todo
//			driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@id='addTodo']")));
			if(driver.findElement(By.xpath("//a[@id='addTodo']")).isEnabled()) {
				driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			}else {
				driver.navigate().refresh();
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@id='addTodo']")));
				driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			}
			
			Thread.sleep(5000);
			
			//escrever a data
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='dueDate']")));
			driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("30/11/2020");
			//clicar em salvar 
			driver.findElement(By.xpath("//input[@id='saveButton']")).click();
			//validar mensagem de sucesso
			((JavascriptExecutor) driver).executeScript("window.focus();");
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[@id='message']")));
			String resposta = driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertEquals("Fill the task description", resposta);
		}finally {
			//fechar o browser
			driver.quit();	
		}
	}
		
		@Test
		public void naoDeveSalvarTarefaSemData() throws MalformedURLException, InterruptedException {
			WebDriver driver = acessarAplicacao();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			((JavascriptExecutor) driver).executeScript("window.focus();");
			try {
				//clicar em Add todo
//				driver.findElement(By.xpath("//a[@id='addTodo']")).click();
				
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@id='addTodo']")));
				if(driver.findElement(By.xpath("//a[@id='addTodo']")).isEnabled()) {
					driver.findElement(By.xpath("//a[@id='addTodo']")).click();
				}else {
					driver.navigate().refresh();
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@id='addTodo']")));
					driver.findElement(By.xpath("//a[@id='addTodo']")).click();
				}
				
				Thread.sleep(5000);
				
				//escrever descrição
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='task']")));
				driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste Automatizado");
				//clicar em salvar 
				driver.findElement(By.xpath("//input[@id='saveButton']")).click();
				//validar mensagem de sucesso
				((JavascriptExecutor) driver).executeScript("window.focus();");
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[@id='message']")));
				String resposta = driver.findElement(By.xpath("//p[@id='message']")).getText();
				Assert.assertEquals("Fill the due date", resposta);
			}finally {
				//fechar o browser
				driver.quit();	
			}
	}
	
		@Test
		public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException, InterruptedException {
			WebDriver driver = acessarAplicacao();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			((JavascriptExecutor) driver).executeScript("window.focus();");
			try {
				//clicar em Add todo
//				driver.findElement(By.xpath("//a[@id='addTodo']")).click();
				
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@id='addTodo']")));
				if(driver.findElement(By.xpath("//a[@id='addTodo']")).isEnabled()) {
					driver.findElement(By.xpath("//a[@id='addTodo']")).click();
				}else {
					driver.navigate().refresh();
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@id='addTodo']")));
					driver.findElement(By.xpath("//a[@id='addTodo']")).click();
				}
				
				Thread.sleep(5000);
				
				//escrever descrição
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='task']")));
				driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste Automatizado");
				//escrever a data
				driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("26/11/2010");
				//clicar em salvar 
				driver.findElement(By.xpath("//input[@id='saveButton']")).click();
				//validar mensagem de sucesso
				((JavascriptExecutor) driver).executeScript("window.focus();");
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[@id='message']")));
				String resposta = driver.findElement(By.xpath("//p[@id='message']")).getText();
				Assert.assertEquals("Due date must not be in past", resposta);
			}finally {
				//fechar o browser
				driver.quit();	
			}		
		}
	
}
