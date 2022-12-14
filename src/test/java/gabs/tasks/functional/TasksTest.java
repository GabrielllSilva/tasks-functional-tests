package gabs.tasks.functional;

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
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		try {
		
			//clicar em ADD Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium Gabis");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("09/12/2022");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {		
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();
		try {
		
			//clicar em ADD Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium Gabis");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
			
		} finally {
			
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		try {
		
			//clicar em ADD Todo
			driver.findElement(By.id("addTodo")).click();
						
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/12/2022");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
			
		} finally {
			
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();
		try {
		
			//clicar em ADD Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium Gabis");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		} finally {		
			//fechar o browser
			driver.quit();
		}
	}

}

//OBS: o try{} finally{} tem o objetivo de rodar todo o fluxo de testes, mesmo que falhe em algum ponto ele será conduzido até o final.
