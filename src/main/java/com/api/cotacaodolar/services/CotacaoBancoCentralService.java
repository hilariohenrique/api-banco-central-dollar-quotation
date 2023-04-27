package com.api.cotacaodolar.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.cotacaodolar.exception.CotacaoDolarException;
import com.api.cotacaodolar.vos.CotacaoVO;

@Service
public class CotacaoBancoCentralService {
	
	private static final String VALUE= "value";

	@Value("${api-public}")
	private String api;

	public CotacaoVO getCotacao(String data) throws Exception {
		try {
			URL url = new URL(api.replace("{DATA}", data));
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
			conexao.setRequestMethod("GET");

			BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
			var status = conexao.getResponseCode();
			var mensagemResponse = conexao.getResponseMessage();

			if (status == 200) {
				StringBuilder resposta = new StringBuilder();
				String linha;
				while ((linha = leitor.readLine()) != null) {
					resposta.append(linha);
				}
				leitor.close();
				conexao.disconnect();

				JSONObject json = new JSONObject(resposta.toString());
				var value = String.valueOf(json.getJSONArray(VALUE));

				if (value.equals("[]")) {
					return new CotacaoVO(0.0, 0.0);
				}

				var compra = Double.parseDouble(
						String.valueOf(json.getJSONArray(VALUE).getJSONObject(0).getFloat("cotacaoCompra")));
				var venda = Double.parseDouble(
						String.valueOf(json.getJSONArray(VALUE).getJSONObject(0).getFloat("cotacaoVenda")));

				return new CotacaoVO(compra, venda);
			} else {
				throw new CotacaoDolarException(String.format(
						"Falha ao realizar requisição para API do Banco Central, status diferente do esperado: %d - %s",
						status, mensagemResponse));
			}

		} catch (CotacaoDolarException e) {
			throw new CotacaoDolarException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falha ao realizar a requisição para API do Banco Central.");
		}
	}
}
