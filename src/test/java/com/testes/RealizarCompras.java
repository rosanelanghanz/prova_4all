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

public class RealizarCompras {
	
	public WebDriver driver;
	private Produtos produto;
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
	public void realizarCompras() {
		
		produto = new Produtos(driver);
		produto.abrirPagina();
		produto.filtrarCategoria("Bebidas");
		produto.selecionarProdutos("Coca-cola lata");
		produto.selecionarProdutos("Fanta uva lata");
		produto.selecionarProdutos("Água mineral sem gás");
		produto.filtrarCategoria("Todos");
		produto.selecionarProdutos("Rissole médio");
		produto.selecionaCarrinho();
		
		carrinho = new Carrinho(driver);
		assertTrue(carrinho.carregou());
		carrinho.adicionarProdutos("Rissole médio", 9);
		carrinho.diminuirProdutos("Rissole médio", 5); 
		carrinho.validarTotal();
		carrinho.finalizarCompra();
		assertEquals(carrinho.validarMsg(),"Pedido realizado com sucesso!");
		carrinho.fecharMensagem();
	
	}
	

}
