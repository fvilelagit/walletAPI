package com.wallet.controler;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.dto.WalletDTO;
import com.wallet.entity.Wallet;
import com.wallet.response.Response;
import com.wallet.service.WalletService;

@RestController
@RequestMapping ("wallet")
public class WalletController {

	@Autowired
	private WalletService service;
	
	@PostMapping
	public ResponseEntity<Response<WalletDTO>> create(@Valid @RequestBody WalletDTO dto, BindingResult result){
		
		Response<WalletDTO> response = new Response<WalletDTO>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(cadaItemEmResultGetAllErrors -> response.getErrors().add(cadaItemEmResultGetAllErrors.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(response);
		}
		
		Wallet w = service.save(this.convertDtoEntity(dto)); // Relembrar o uso do this
		response.setData(this.convertEntityToDTO(w));
				
		return ResponseEntity.ok().body(response);
	}
	
	private Wallet convertDtoEntity(WalletDTO dto) {
		Wallet w = new Wallet();
		w.setId(dto.getId());
		w.setName(dto.getName());
		w.setValue(dto.getValue());
		
		return w;
	
	}
	
	private WalletDTO convertEntityToDTO(Wallet w) {
		
		WalletDTO dto = new WalletDTO();
		dto.setId(w.getId());
		dto.setName(w.getName());
		dto.setValue(w.getValue());
		
		return dto;
			
	}
}



















