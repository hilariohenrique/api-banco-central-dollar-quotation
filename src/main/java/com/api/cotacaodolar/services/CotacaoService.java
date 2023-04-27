package com.api.cotacaodolar.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cotacaodolar.models.CotacaoModel;
import com.api.cotacaodolar.repositories.CotacaoRepository;
import com.api.cotacaodolar.util.DateUtil;
import com.api.cotacaodolar.vos.CotacaoVO;

@Service
public class CotacaoService {

	@Autowired
	private CotacaoBancoCentralService cotacaoBancoCentralService;

	@Autowired
	private CotacaoRepository cotacaoRepository;

	public CotacaoVO buscaESalvaCotacao(String yyyy, String mm, String dd) throws Exception {
		LocalDate data = DateUtil.toLocalDate(yyyy, mm, dd);
		Optional<CotacaoModel> opt = cotacaoRepository.findByDataCotacao(data);
		CotacaoVO cotacaoVo = cotacaoBancoCentralService.getCotacao(getDataString(yyyy, mm, dd));

		if (opt.isPresent()) {
			if (!opt.get().getCotacaoCompra().equals(cotacaoVo.getCotacaoCompra())
					|| !opt.get().getCotacaoVenda().equals(cotacaoVo.getCotacaoVenda())) {
				CotacaoModel cotacaoSalva = opt.get();
				cotacaoSalva.setCotacaoCompra(cotacaoVo.getCotacaoCompra());
				cotacaoSalva.setCotacaoVenda(cotacaoVo.getCotacaoVenda());
				cotacaoRepository.save(cotacaoSalva);
				return cotacaoVo;
			}
			return new CotacaoVO(opt.get().getCotacaoCompra(), opt.get().getCotacaoVenda());
		}

		cotacaoRepository.save(
				new CotacaoModel(LocalDateTime.now(), data, cotacaoVo.getCotacaoCompra(), cotacaoVo.getCotacaoVenda()));
		return cotacaoVo;
	}

	private String getDataString(String yyyy, String mm, String dd) {
		StringBuilder sb = new StringBuilder();
		sb.append(mm);
		sb.append("-");
		sb.append(dd);
		sb.append("-");
		sb.append(yyyy);
		return sb.toString();
	}
}
