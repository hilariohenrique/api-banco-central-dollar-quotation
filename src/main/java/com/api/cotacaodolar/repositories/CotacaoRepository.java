package com.api.cotacaodolar.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.cotacaodolar.models.CotacaoModel;


@Repository
public interface CotacaoRepository  extends JpaRepository<CotacaoModel, UUID> {
	
	Optional<CotacaoModel> findByDataCotacao(LocalDate dataCotacao);

}
