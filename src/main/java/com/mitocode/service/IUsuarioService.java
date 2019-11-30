package com.mitocode.service;

import com.mitocode.model.Usuario;

public interface IUsuarioService{

	
	Usuario registrarTransaccional(Usuario us);

	public Usuario findByUsername(String username);

	public Usuario listarPorId(Integer id);

	public void cambioClave(Usuario usu);

}
