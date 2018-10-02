package co.com.bancolombia.getmovements.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.bancolombia.getmovements.models.RequestService;
import co.com.bancolombia.getmovements.models.ResponseService;
import co.com.bancolombia.getmovements.service.GetMovementsService;

@RestController
public class Controller {
@Autowired
GetMovementsService getMovementsService;
	
	@RequestMapping(value = "getMovements", method = RequestMethod.POST)
	public ResponseService controller(@RequestBody RequestService data){
		 return getMovementsService.getMovements(data);
		
	}
		
}
