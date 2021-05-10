package org.generation.italy.euris.controller;

import org.generation.italy.euris.dao.IDaoOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operations/")
public class ControllerOperations {
	
	@Autowired
	private IDaoOperations dao;
	
	@GetMapping("sum/{cost1}&{cost2}")
	String sum(@PathVariable String cost1, @PathVariable String cost2) {
		return dao.sum(cost1, cost2);
	}
	
	@GetMapping("sub/{cost1}&{cost2}")
	String sub(@PathVariable String cost1, @PathVariable String cost2) {
		return dao.sub(cost1, cost2);
	}
	
	@GetMapping("mult/{cost1}&{cost2}")
	String mult(@PathVariable String cost1, @PathVariable int cost2) {
		return dao.mult(cost1, cost2);
	}
	
	@GetMapping("div/{cost1}&{cost2}")
	String div(@PathVariable String cost1, @PathVariable int cost2) {
		return dao.div(cost1, cost2);
	}

}
