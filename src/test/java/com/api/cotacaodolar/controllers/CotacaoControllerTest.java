package com.api.cotacaodolar.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import com.api.cotacaodolar.AbstractCotacaoDolarApplicationTests;
import com.api.cotacaodolar.models.CotacaoModel;
import com.api.cotacaodolar.repositories.CotacaoRepository;
import com.api.cotacaodolar.services.CotacaoBancoCentralService;
import com.api.cotacaodolar.util.DateUtil;
import com.api.cotacaodolar.vos.CotacaoVO;

@Sql(scripts = "classpath:/sql/delete.sql")
@Sql(scripts = "classpath:/sql/inserts.sql")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CotacaoControllerTest extends AbstractCotacaoDolarApplicationTests {

	@MockBean
	private CotacaoBancoCentralService cotacaoBancoCentralService;

	@Autowired
	private CotacaoRepository cotacaoRepository;

	@BeforeEach
	public void init() throws Exception {
		super.init();
		CotacaoVO vo01 = new CotacaoVO(4.56D, 4.80D);
		when(this.cotacaoBancoCentralService.getCotacao("04-05-2023")).thenReturn(vo01);

		CotacaoVO vo02 = new CotacaoVO(4.9672D, 4.9678D);
		when(this.cotacaoBancoCentralService.getCotacao("04-18-2023")).thenReturn(vo02);

		CotacaoVO vo03 = new CotacaoVO(0D, 0D);
		when(this.cotacaoBancoCentralService.getCotacao("04-21-2023")).thenReturn(vo03);

		when(this.cotacaoBancoCentralService.getCotacao("04-30-2023")).thenThrow(RuntimeException.class);
	}

	@Test
	@Order(1)
	void testGetCotacaoOk() throws Exception {
		Optional<CotacaoModel> opt = cotacaoRepository.findByDataCotacao(DateUtil.toLocalDate("2023", "04", "05"));
		assertFalse(opt.isPresent());

		mockMvc.perform(get("/cotacao/2023/04/05").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(payloadExtractor).andReturn();

		CotacaoVO vo = payloadExtractor.as(CotacaoVO.class);
		assertEquals(4.56, vo.getCotacaoCompra());
		assertEquals(4.80, vo.getCotacaoVenda());

		opt = cotacaoRepository.findByDataCotacao(DateUtil.toLocalDate("2023", "04", "05"));
		assertTrue(opt.isPresent());
		assertEquals(4.56, opt.get().getCotacaoCompra());
		assertEquals(4.80, opt.get().getCotacaoVenda());
	}

	@Test
	@Order(2)
	void testGetCotacaoBancoOk() throws Exception {
		Optional<CotacaoModel> opt = cotacaoRepository.findByDataCotacao(DateUtil.toLocalDate("2023", "04", "18"));
		assertTrue(opt.isPresent());
		assertEquals(4.9672, opt.get().getCotacaoCompra());
		assertEquals(4.9678, opt.get().getCotacaoVenda());

		mockMvc.perform(get("/cotacao/2023/04/18").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(payloadExtractor).andReturn();

		CotacaoVO vo = payloadExtractor.as(CotacaoVO.class);
		assertEquals(4.9672, vo.getCotacaoCompra());
		assertEquals(4.9678, vo.getCotacaoVenda());
	}

	@Test
	@Order(3)
	void testGetCotacaoBancoUpdateOk() throws Exception {
		Optional<CotacaoModel> opt = cotacaoRepository.findByDataCotacao(DateUtil.toLocalDate("2023", "04", "18"));
		assertTrue(opt.isPresent());
		assertEquals(4.9672, opt.get().getCotacaoCompra());
		assertEquals(4.9678, opt.get().getCotacaoVenda());

		CotacaoVO voMock = new CotacaoVO(5D, 5.7D);
		when(this.cotacaoBancoCentralService.getCotacao("04-18-2023")).thenReturn(voMock);

		mockMvc.perform(get("/cotacao/2023/04/18").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(payloadExtractor).andReturn();

		CotacaoVO vo = payloadExtractor.as(CotacaoVO.class);
		assertEquals(5, vo.getCotacaoCompra());
		assertEquals(5.7, vo.getCotacaoVenda());

		opt = cotacaoRepository.findByDataCotacao(DateUtil.toLocalDate("2023", "04", "18"));
		assertTrue(opt.isPresent());
		assertEquals(5, opt.get().getCotacaoCompra());
		assertEquals(5.7, opt.get().getCotacaoVenda());
	}

	@Test
	@Order(4)
	void testException() throws Exception {
		mockMvc.perform(get("/cotacao/2023/04/30").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	@Order(5)
	void testDataInvalida() throws Exception {
		mockMvc.perform(get("/cotacao/2023/04/31").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
