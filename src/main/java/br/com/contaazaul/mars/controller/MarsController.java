package br.com.contaazaul.mars.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.contaazaul.mars.servico.MarteService;

@RestController
@RequestMapping("/rest/mars")
public class MarsController {
	
	@RequestMapping(value="/{comando}", method = RequestMethod.POST, produces = "application/json")
	public String mover(@PathVariable("comando") String comando, HttpServletRequest request, HttpServletResponse response){
		String retorno = marteService.moverRobo(comando);
		if(retorno == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			retorno=response.getStatus()+" Bad Request";
		}
		return retorno;
	}
	
	public MarteService getMarteService() {
		return marteService;
	}

	public void setMarteService(MarteService marteService) {
		this.marteService = marteService;
	}

	@Autowired
	private MarteService marteService; 
	
}
