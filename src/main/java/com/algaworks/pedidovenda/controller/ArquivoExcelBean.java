package com.algaworks.pedidovenda.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Scanner;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.algaworks.pedidovenda.model.ArquivoExcel;
import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.repository.CategoriaDAO;
import com.algaworks.pedidovenda.service.ArquivoExcelService;
import com.algaworks.pedidovenda.service.CategoriaService;

@Named
@RequestScoped
public class ArquivoExcelBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ArquivoExcelService arquivoExcelService;

	@Inject
	private CategoriaDAO categoriaDAO;

	@Inject
	private CategoriaService categoriaService;

	private ArquivoExcel arquivoExcel;

	private Part arquivo;

	public ArquivoExcelBean() {
		arquivoExcel = new ArquivoExcel();

	}

	public void salvar() throws IOException {
		// LENDO O ARQUIVO CSV
		Scanner scanner = new Scanner(arquivo.getInputStream(), "UTF-8");
		// sepera cada colona por ","
		scanner.useDelimiter(",");

		while (scanner.hasNext()) {
			String linha = scanner.nextLine();

			if (linha != null && !linha.trim().isEmpty()) {
				linha = linha.replaceAll("\"", "");
				// System.out.println(linha);

				String[] dados = linha.split("\\,");

				Categoria categoria = new Categoria();
				categoria.setDescricao(dados[0]);

				Categoria categoriaPai = this.categoriaDAO.porId(Long.parseLong(dados[1]));
				categoria.setCategoriaPai(categoriaPai);

				this.categoriaService.salvar(categoria);

				System.out.println("Categoria: " + dados[0] + " Sub-categoria: " + dados[1]);

			}

		}

		// transformando para byte
		byte[] arquivoByte = toArrayUsandoJava(arquivo.getInputStream());

		arquivoExcel.setArquivo(arquivoByte);
		// this.arquivoExcelService.salvar(arquivoExcel);

		/*
		 * Scanner conteudo = new Scanner(arquivo.getInputStream());
		 * 
		 * while(conteudo.hasNext()){ System.out.println(conteudo.next()); }
		 */
	}

	public byte[] toArrayUsandoJava(InputStream is) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int reads = is.read();

		while (reads != -1) {
			out.write(reads);
			reads = is.read();
		}
		return out.toByteArray();
	}

	// get and set

	public ArquivoExcel getArquivoExcel() {
		return arquivoExcel;
	}

	public void setArquivoExcel(ArquivoExcel arquivoExcel) {
		this.arquivoExcel = arquivoExcel;
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}

}
