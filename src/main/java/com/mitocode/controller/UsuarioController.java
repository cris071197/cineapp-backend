package com.mitocode.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mitocode.model.Cliente;
import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@PostMapping(produces = "application/json", consumes = "application/json")
	private ResponseEntity<Object> registrar(@RequestBody Usuario usuario) {
		usuario.setClave(bcrypt.encode(usuario.getClave()));
		service.registrarTransaccional(usuario);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public Usuario listarPorId(@PathVariable("id") Integer id) {
		return service.listarPorId(id);
	}

	@PutMapping
	public ResponseEntity<Object> cambioclave(@RequestBody Usuario usuario) {
		// TODO Auto-generated method stub
		String clave = usuario.getClave();
		usuario.setClave(bcrypt.encode(clave));
		service.cambioClave(usuario);
		return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
	}

}
