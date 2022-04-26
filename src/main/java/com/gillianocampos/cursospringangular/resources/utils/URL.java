package com.gillianocampos.cursospringangular.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	//exemplo da requisição localhost:8080/produtos/nome=computador&categorias=1,3,4
	//metodo para pegar o que vir na requisição a string 1,2,4 e converter para inteiro e colocar numa lista de inteiros
	//pega a string s separa as virgulas converte para inteiro e adiciona na lista para jogar na em ProdutoResource no metodo search
	public static List<Integer> decodeIntList(String s){
		//vetor de string recebe  . o split pega uma string e recorta baseado no que esta entre parentes, no caso ele recorta a virgula
		String[] vet = s.split(",");
		//crio a lista
		List<Integer> list = new ArrayList<>();
		//percorre o vetor
		for(int i=0; i<vet.length; i++) {
			//converte para inteiro e adiciona na lista
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		
		//poderia usar lambda para todo este metodo e ficaria em apenas uma linha
		// return Array.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
	//metodo para tirar espações em branco ex em tv led tv%20led
	//	//exemplo da requisição localhost:8080/produtos/?nome=tv%20led&categorias=1,3,4
	public static String decodeParam(String s) {
		//função do java para desencodar uma string é URLDecoder.decode que recebe a string s como argumento e o padrao UTF-8
		//pode dar exceção se der erro retorna uma String vazia no catch
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
				return "";
		}
	}
}
