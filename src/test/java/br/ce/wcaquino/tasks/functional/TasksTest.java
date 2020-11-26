package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		try {
			//clicar em Add todo
			driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			//escrever descrição
			driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste Automatizado");
			//escrever a data
			driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("26/11/2020");
			//clicar em salvar 
			driver.findElement(By.xpath("//input[@id='saveButton']")).click();
			//validar mensagem de sucesso
			String resposta = driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertEquals("Success!", resposta);
		}finally {
			//fechar o browser
			driver.quit();	
		}		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		try {
			//clicar em Add todo
			driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			//escrever a data
			driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("26/11/2020");
			//clicar em salvar 
			driver.findElement(By.xpath("//input[@id='saveButton']")).click();
			//validar mensagem de sucesso
			String resposta = driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertEquals("Fill the task description", resposta);
		}finally {
			//fechar o browser
			driver.quit();	
		}
	}
		
		@Test
		public void naoDeveSalvarTarefaSemData() {
			WebDriver driver = acessarAplicacao();
			try {
				//clicar em Add todo
				driver.findElement(By.xpath("//a[@id='addTodo']")).click();
				//escrever descrição
				driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste Automatizado");
				//clicar em salvar 
				driver.findElement(By.xpath("//input[@id='saveButton']")).click();
				//validar mensagem de sucesso
				String resposta = driver.findElement(By.xpath("//p[@id='message']")).getText();
				Assert.assertEquals("Fill the due date", resposta);
			}finally {
				//fechar o browser
				driver.quit();	
			}
	}
	
		@Test
		public void naoDeveSalvarTarefaComDataPassada() {
			WebDriver driver = acessarAplicacao();
			try {
				//clicar em Add todo
				driver.findElement(By.xpath("//a[@id='addTodo']")).click();
				//escrever descrição
				driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste Automatizado");
				//escrever a data
				driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("26/11/2010");
				//clicar em salvar 
				driver.findElement(By.xpath("//input[@id='saveButton']")).click();
				//validar mensagem de sucesso
				String resposta = driver.findElement(By.xpath("//p[@id='message']")).getText();
				Assert.assertEquals("Due date  must not be in past", resposta);
			}finally {
				//fechar o browser
				driver.quit();	
			}		
		}
	
}
