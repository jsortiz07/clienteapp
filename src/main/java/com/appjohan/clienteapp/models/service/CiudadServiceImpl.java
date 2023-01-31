package com.appjohan.clienteapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appjohan.clienteapp.models.entity.Ciudad;
import com.appjohan.clienteapp.models.repository.CiudadRepository;

@Service
public class CiudadServiceImpl implements ICiudadService{

	@Autowired
	private  CiudadRepository ciudadRepository;
	
	@Override
	public List<Ciudad> ListaCiudades() {
		return (List<Ciudad>) ciudadRepository.findAll();
	}

}
