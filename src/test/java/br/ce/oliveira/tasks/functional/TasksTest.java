package br.ce.oliveira.tasks.functional;


import org.junit.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TasksTest {
	
	@SuppressWarnings("deprecation")
	public WebDriver acessarAplicacao() throws MalformedURLException {
//		WebDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.15.5:4444/wd/hub"),options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to("http://192.168.15.5:8080/tasks");
		
		return driver;
	}
	
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
			// clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
			
			// inserir descrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// inserir data
		driver.findElement(By.id("dueDate")).sendKeys("31/10/2026");
			
			// clicar em salvar
		driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", message);
			//fechar o browser
		driver.quit();
	}
		
		@Test
		public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
			WebDriver driver = acessarAplicacao();
			try {
				// clicar em Add Todo
				driver.findElement(By.id("addTodo")).click();
				
				// inserir data
				driver.findElement(By.id("dueDate")).sendKeys("31/10/2026");
				
				// clicar em salvar
				driver.findElement(By.id("saveButton")).click();
				
				// validar mensagem de sucesso
				String message = driver.findElement(By.id("message")).getText();
				Assert.assertEquals("Fill the task description", message);
			} finally {
				// fechar o browser
				driver.quit();
			}
		}
		
		@Test
		public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
			WebDriver driver = acessarAplicacao();
			try {
				// clicar em Add Todo
				driver.findElement(By.id("addTodo")).click();
				
				// inserir descrição
				driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
				
				// clicar em salvar
				driver.findElement(By.id("saveButton")).click();
				
				// validar mensagem de sucesso
				String message = driver.findElement(By.id("message")).getText();
				Assert.assertEquals("Fill the due date", message);
			} finally {
				//fechar o browser
				driver.quit();
			}
		}
		
		@Test
		public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
			WebDriver driver = acessarAplicacao();
			try {
				// clicar em Add Todo
				driver.findElement(By.id("addTodo")).click();
				
				// inserir descrição
				driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
				
				// inserir data
				driver.findElement(By.id("dueDate")).sendKeys("31/10/2000");
				
				// clicar em salvar
				driver.findElement(By.id("saveButton")).click();
				
				// validar mensagem de sucesso
				String message = driver.findElement(By.id("message")).getText();
				Assert.assertEquals("Due date must not be in past", message);
			} finally {
				//fechar o browser
				driver.quit();
		}
	}
}
	
	
