package com.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.paginas.Carrinho;
import com.paginas.Produtos;

public class CompraDoces {

	public WebDriver driver;
	private Produtos produtos;
	private Carrinho carrinho;
	
	@Before
	public void abrirNavegador() {
		System.setProperty("webdriver.chrome.driver", "C:\\Projetos\\webdrives\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}
	
	@After
	public void fecharNavegador() {
		driver.quit();
	}
	

	@Test
	public void comprarDoces() {
		produtos = new Produtos(driver);
		produtos.abrirPagina();
		produtos.filtrarCategoria("Doces");
		produtos.selecionarProdutos("Brigadeiro");
		produtos.selecionarProdutos("Alfajor de chocolate");
		produtos.filtrarCategoria("Todos");
		produtos.selecionaCarrinho();
		
		carrinho = new Carrinho(driver);
		assertTrue(carrinho.carregou());
		carrinho.adicionarProdutos("Brigadeiro", 4);
		carrinho.finalizarCompra();
		assertEquals(carrinho.validarMsg(),"Pedido realizado com sucesso!");
		carrinho.fecharMensagem();
				
	}
	
	
	
	
	
	
	
	
}
