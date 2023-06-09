package com.api.cotacaodolar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.cotacaodolar.services.CotacaoService;

@RestController
@RequestMapping("/cotacao")
public class CotacaoController {
	
	@Autowired
	private CotacaoService cotacaoService;
	
	@GetMapping("/{YYYY}/{MM}/{DD}")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "YYYY") String yyyy, @PathVariable(value = "MM") String mm, @PathVariable(value = "DD") String dd) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cotacaoService.buscaESalvaCotacao(yyyy, mm, dd));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }


}
