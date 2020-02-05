package com.paginas;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Produtos {

	private WebDriver driver;
	
	public Produtos (WebDriver driver) {
		this.driver = driver;
	}
	
	// Abrir o site
	public Produtos abrirPagina() {
		driver.get("https://shopcart-challenge.4all.com/");
		return this;
	}
	
	// Selecionar categoria
	public void filtrarCategoria(String nomeCategoria) {
		WebElement campoCategoria = driver.findElement(By.id("open-categories-btn"));
		campoCategoria.click();
		WebElement categoria = driver.findElement(By.xpath("//*[text()='"+nomeCategoria+"']"));
		categoria.click();
	}
	
	// Adicionar produtos no carrinho
	public void selecionarProdutos (String nomeProduto) {
		WebElement produto = driver.findElement(
				By.xpath("//*[@class='sc-bdVaJa hRjvgd']//*[text()='"+nomeProduto+"']//../../..//button[contains(text(),'Adicionar ao carrinho')]"));
		produto.click();   
				
	}
	
	//Selecionar o carrinho
	public void selecionaCarrinho () {
		WebElement carrinho = driver.findElement(By.id("cart-btn"));
		carrinho.click();		
	}
		
	
}
