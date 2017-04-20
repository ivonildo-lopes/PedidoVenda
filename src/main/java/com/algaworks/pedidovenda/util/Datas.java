package com.algaworks.pedidovenda.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Datas {

	/**
	 * 
	 * CRIA��O DE DATAS
	 */
	public static Date informarData(String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.parse(data);
	}
	
	public static Date informarData2(String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(data);
	}

	public Date criaData(int dia, int mes, int ano) {

		Calendar d = new GregorianCalendar();
		d.set(ano, mes - 1, dia);

		return d.getTime();
	}

	
	/**
	 * FORMATA DATA
	 */
	
	public static String formataDataDiaMes(Date data){
		 DateFormat dataa = new SimpleDateFormat("dd/MM");
		return dataa.format(data);
	}
	
	/**
	 * CALCULA IDADE
	 */
	public int calculaIdade(Date dtNascimento) {

		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(dtNascimento);

		// pega a data atual
		Calendar hoje = Calendar.getInstance();

		// idade = ANO - ano do nascimento
		int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

		if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
			idade--;
		}

		if (hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento
				.get(Calendar.DAY_OF_MONTH)) {
			idade--;
		}

		return idade;
	}

	/**
	 * 
	 * DATA COMPLETA DIA DA SEMANA, XX DE NOME DO MES DE XXXX
	 */

	public String dataCompleta(Date data) {
		DateFormat dataFormatada = DateFormat.getDateInstance(DateFormat.FULL);
		return dataFormatada.format(data);
	}

	public String dataCompleta() {
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();

		DateFormat dataFormatada = DateFormat.getDateInstance(DateFormat.FULL);
		return dataFormatada.format(data);
	}

	/**
	 * 
	 * DATA LONGA XX DE NOME DO MES DE XXXX
	 */
	public String dataLonga() {
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();

		DateFormat dataFormatada = DateFormat.getDateInstance(DateFormat.LONG);
		return dataFormatada.format(data);
	}

	public String dataLonga(Date data) {
		DateFormat dataFormatada = DateFormat.getDateInstance(DateFormat.LONG);
		return dataFormatada.format(data);
	}

	/**
	 * 
	 * DATA MEDIA XX / XX / XXXX
	 */
	public String dataMedia(Date data) {
		DateFormat dataFormatada = DateFormat
				.getDateInstance(DateFormat.MEDIUM);
		return dataFormatada.format(data);
	}

	public String dataMedia() {
		Date data = new Date();
		DateFormat dataFormatada = DateFormat
				.getDateInstance(DateFormat.MEDIUM);
		return dataFormatada.format(data);
	}

	/**
	 * 
	 * DATA CURTA XX / XX / XX
	 */
	public String dataCurta(Date data) {
		DateFormat dataFormatada = DateFormat.getDateInstance(DateFormat.SHORT);
		return dataFormatada.format(data);
	}

	public String dataCurta() {
		Date data = new Date();
		DateFormat dataFormatada = DateFormat.getDateInstance(DateFormat.SHORT);
		return dataFormatada.format(data);
	}

	/**
	 * 
	 * HORA COMPLETA XX : XX : XX
	 */
	public String horaAtualCompleta() {
		Date data = new Date();
		DateFormat hora = DateFormat.getTimeInstance();

		return hora.format(data);
	}

	/**
	 * 
	 * DATA MEDIA : HORA COMPLETA XX/XX/XXXX XX:XX:XX
	 */
	public String dataAtualHora() {
		Date data = new Date();
		DateFormat hora = DateFormat.getDateTimeInstance();

		return hora.format(data);
	}

	/**
	 * 
	 * DAR PRAZO POR DIA
	 * 
	 */
	public Date contarPrazo(int acrescimo) {

		Calendar dataCalendar = Calendar.getInstance();

		dataCalendar.add(Calendar.DAY_OF_MONTH, acrescimo);
		return dataCalendar.getTime();

	}

	public Date adicionaDias(Date datas, int acrescimo) {

		Calendar data = new GregorianCalendar();

		data.setTime(datas);

		data.add(Calendar.DAY_OF_MONTH, acrescimo);
		return data.getTime();

	}

	/**
	 * 
	 * DAR PRAZO POR SEMANA
	 * 
	 */
	public Date adicionaSemanas(Date datas, int acrescimo) {

		Calendar data = new GregorianCalendar();

		data.setTime(datas);

		data.add(Calendar.WEEK_OF_YEAR, acrescimo);
		return data.getTime();

	}

	/**
	 * 
	 * DAR PRAZO POR MES
	 * 
	 */
	public Date adicionaMes(Date datas, int acrescimo) {

		Calendar data = new GregorianCalendar();

		data.setTime(datas);

		data.add(Calendar.MONTH, acrescimo);
		return data.getTime();

	}

	/**
	 * 
	 * COMPARA DATAS
	 * 
	 */
	public String comparaData(Date data_inicio, Date data_fim) {

		if (data_fim.after(data_inicio))
			return "A data ainda vai chegar";
		else if (data_fim.before(data_inicio))
			return "A data ja passou";
		else
			return "Essa data � hoje";
	}

	public Integer comparaDatas(Date data_inicio, Date data_fim) {

		if (data_fim.after(data_inicio))
			return 1;
		else if (data_fim.before(data_inicio))
			return -1;
		else
			return 0;
	}

	/**
	 * 
	 * QUANTOS DIAS FALTAM - DIFEREN�A ENTRE DATAS
	 * parece idade
	 */ 
	public int diferencaDias(Date data_inicio, Date data_fim) {

		Calendar d1 = new GregorianCalendar();
		Calendar d2 = new GregorianCalendar();

		d1.setTime(data_inicio);
		d2.setTime(data_fim);

		long m1 = d1.getTimeInMillis();
		long m2 = d2.getTimeInMillis();

		int diferenca = (int) ((m2 - m1) / (24 * 60 * 60 * 1000));
		return diferenca;
	}

	/**
	 * 
	 * PEGAR DIA, MES E ANO METODOS RESPECTIVOS
	 * 
	 */
	public static int pegarDia(Date data) {

		Calendar d = new GregorianCalendar();
		d.setTime(data);

		return d.get(Calendar.DAY_OF_MONTH);
	}

	public static int pegarMes(Date data) {
		Calendar d = new GregorianCalendar();
		d.setTime(data);

		return d.get(Calendar.MONTH) + 1;
	}

	public int pegarAno(Date data) {
		Calendar d = new GregorianCalendar();
		d.setTime(data);

		return d.get(Calendar.YEAR);
	}
	
	public String pegarNomeMesAtual(){
		int mes =pegarMes(new Date());
		
		String m = null;
		switch (mes) {
		case 1:	m = "JANEIRO";break;
		case 2:	m = "FEVEIRO";break;
		case 3:	m = "MAR�O";break;
		case 4:	m = "ABRIL";break;
		case 5:	m = "MAIO";break;
		case 6:	m = "JUNHO";break;
		case 7:	m = "JULHO";break;
		case 8:	m = "AGOSTO";break;
		case 9:	m = "SETEMBRO";break;
		case 10:m = "OUTUBRO";break;
		case 11:m = "NOVEMBRO";break;
		case 12:m = "DEZEMBRO";break;

		default: m = "n�o existe";break;
		}
		return m;
	}
	
	public String pegarNomeDiaSemana(Date data) {
		Calendar d = new GregorianCalendar();
		d.setTime(data);
		String dia=null;
		
		int diaSemana = d.get(Calendar.DAY_OF_WEEK);
		switch (diaSemana) {
		case 0:	dia = "Domingo";break;
		case 1:	dia = "Segunda";break;
		case 2:	dia = "Ter�a";break;
		case 3:	dia = "Quarta";break;
		case 4:	dia = "Quinta";break;
		case 5:	dia = "Sexta";break;
		case 6:	dia = "Sabado";break;

		default: dia = "n�o existe";break;
		}
		return dia;
	}
	
	public String pegarTurno(){
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		int hora = c.get(Calendar.HOUR);
		
		if(hora >= 6 && hora <=12)
			return "MANHA";
		else if(hora > 12 && hora <= 18)
			return "TARDE";
		else
			return "NOITE";
	}

	
	/**
	 * SAUDA��O
	 */
	
	public String saudacao(){
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		int hora = c.get(Calendar.HOUR);
		
		if(hora >= 6 && hora <=12)
			return "Bom dia";
		else if(hora > 12 && hora <= 18)
			return "Boa tarde";
		else
			return "Boa noite";
	}

	/**
	 * 
	 * DATA POR PAIS DIA DA SEMANA, XX DE NOME DO MES DE XXXX
	 */
	public String dataPais(Locale pais) {

		Date data = new Date();
		DateFormat dataFormatada = DateFormat.getDateInstance(DateFormat.FULL,
				pais);

		return dataFormatada.format(data);
	}

	public String dataPais(String pais) {

		Locale paises = null;
		if (pais.equals("BRASIL") || pais.equals("Brasil")
				|| pais.equals("brasil")) {
			paises = new Locale("pt", "BR");
		} else if (pais.equals("Estados Unidos") || pais.equals("USA")
				|| pais.equals("US")) {
			paises = Locale.US;
		} else if (pais.equals("ALEMANHA") || pais.equals("Alemanha")
				|| pais.equals("alemanha")) {
			paises = Locale.GERMANY;
		}

		Date data = new Date();
		DateFormat dataFormatada = DateFormat.getDateInstance(DateFormat.FULL,
				paises);

		return pais + " " + dataFormatada.format(data);
	}

	/**
	 * VERIFICA SE A DATA � VALIDA
	 */
	public boolean dataValida(String data) {

		int dia = Integer.parseInt(data.substring(0, 2));

		int mes = Integer.parseInt(data.substring(3, 5));

		int ano = Integer.parseInt(data.substring(6, 10));

		if (ano > 1900) {

			if (mes >= 1 && mes <= 12) {
				// bisexto
				if (ano % 4 == 0) {
					if (mes == 2) {
						if (dia <= 29) {
							
							return true;
						} else{
							System.out.println("fevereiro s� tem ate dia 29 para anos bissexto");
							return false;
						}
					} else {
						// diferente de fevereiro
						if (mes != 2 && mes <= 7) {
							if (mes % 2 != 0) {
								if (mes <= 31)
									return true;
								else{
									System.out.println("Esse mes tem 31 dias");
									return false;
								}
							}
							if (mes % 2 == 0) {

								if (dia <= 30)
									return true;
								else{
									System.out.println("Esse mes tem apenas 30 dias");
									return false;
								}
							}
						} else if (mes != 2 && mes <= 12) {
							if (mes % 2 != 0) {
								if (dia <= 30)
									return true;
								else
									return false;
							}
							if (mes % 2 == 0) {
								if (dia <= 31)
									return true;
								else
									return false;
							}
						} else
							return false;
					}
				} else if (ano % 4 != 0) {
					// nao bissexto
					if (mes == 2) {
						if (dia <= 28) {
							return true;
						} else
							return false;
					} else {
						// diferente de fevereiro
						if (mes != 2 && mes <= 7) {
							if (mes % 2 != 0) {
								if (dia <= 31)
									return true;
								else
									return false;
							}
							if (mes % 2 == 0) {
								if (dia <= 30)
									return true;
								else
									return false;
							}
						} else if (mes != 2 && mes <= 12) {
							if (mes % 2 != 0) {
								if (dia <= 30)
									return true;
								else
									return false;
							}
							if (mes % 2 == 0) {
								if (dia <= 31)
									return true;
								else
									return false;
							}
						} else
							return false;
					}
				} else
					return false;

			} else
				return false;

		} else
			return false;
		return false;
	}
}
