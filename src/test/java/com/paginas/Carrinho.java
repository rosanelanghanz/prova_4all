package com.paginas;

import static org.junit.Assert.assertEquals;

import java.awt.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Carrinho {

	private WebDriver driver;
	
	public Carrinho (WebDriver driver) {
		this.driver = driver;
	}
	
	public void adicionarProdutos(String nomeProduto, int qtde) {
		for(int i = 0; i < qtde; i++){
			WebElement product = driver.findElement(
					By.xpath("//*[contains(text(), '"+nomeProduto+"')]//../div[1]/div[2]"));
			product.click();
		}
		
	}
		
	//Diminuir produtos no carrinho
	public void diminuirProdutos (String nomeProduto, int qtde) {
		
		for (int i = 0; i < qtde; i++) {
			
			WebElement produto = driver.findElement(
					By.xpath("//*[contains(text(), '"+nomeProduto+"')]//../div[1]/div[1]"));
			produto.click();
		}
	}
	
	// Finaliza a compra
	public void finalizarCompra() {
		WebElement finalizar = driver.findElement(By.id("finish-checkout-button"));
		finalizar.click();
		
	}
	
	//Realiza a validação da mensagem da compra
	public String validarMsg() {
		WebElement mensagem = driver.findElement(By.xpath("//*[contains(text(),'Pedido realizado com sucesso!')]"));
		return mensagem.getText();
		
	}
	
	//Fecha a mensagem do sucesso da compra
	public void fecharMensagem() {
		WebElement fechar = driver.findElement(By.xpath("//*[contains(text(),'Fechar')]"));
		fechar.click();
	}

	//Verifica se a página do carrinho carregou
	public boolean carregou() {
		return driver.findElement(By.xpath("//*[contains(text(),'BELLA GULA')]")).isDisplayed();
	}

	//Realiza a validação do total dos produtos
	public void validarTotal() {
		
		int qtdItem = 0;
		double valorItem = 0.0f;
		double valorTotal = 0.0f;
		double valorSubtotal;
		
		WebElement quantidadeItemWebElement;
		WebElement valorItemWebElement;
		
		//Faz um array da <li class="sc-csuQGl wfUhd">
		ArrayList<WebElement> listaItens = (ArrayList<WebElement>) driver.findElements(By.xpath("//*[@class='sc-csuQGl wfUhd']"));
		
		for (int i = 0 ; i < listaItens.size(); i++) {
			quantidadeItemWebElement = driver.findElement(By.id(String.format("product-%d-qtd", i)));
			valorItemWebElement = driver.findElement(By.id( String.format("product-%d-price", i) ));
			
			// Quantidade dos itens
			qtdItem = Integer.parseInt(quantidadeItemWebElement.getText());
			
			// Valor do item (ex: R$&nbsp;3,5)
			// Remove moeda e subtitui , por . e remove os espaços.
			valorItem = Double.parseDouble(valorItemWebElement.getText().replace("R$", "").replace(",", ".").trim());
			
			// Realiza o cálculo concatenando os valores a cada laço de repetição
			valorTotal += qtdItem * valorItem;								
		}
		
		// Pega o subtotal e Remove moeda e subtitui , por . e remove os espaços.
		WebElement subtotal = driver.findElement(By.id("subtotal-price"));
		valorSubtotal = Double.parseDouble(subtotal.getText().replace("R$", "").replace(",", ".").trim());
			
		//Faz a comparação dos valores
		assertEquals(valorSubtotal + "", valorTotal + "");
	}
	
	
	
}
