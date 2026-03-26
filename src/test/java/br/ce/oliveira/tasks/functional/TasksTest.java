package br.ce.oliveira.tasks.functional;


import org.junit.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to("http://localhost:8080/tasks");
		
		return driver;
	}
	
	
	@Test
	public void deveSalvarTarefaComSucesso() {
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
		public void naoDeveSalvarTarefaSemDescricao() {
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
		public void naoDeveSalvarTarefaSemData() {
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
		public void naoDeveSalvarTarefaComDataPassada() {
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
	
	
