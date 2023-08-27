package com.apiHateoas.service;

import com.apiHateoas.exception.CuentaNotFoundException;
import com.apiHateoas.model.Cuenta;
import com.apiHateoas.repository.CuentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public List<Cuenta> getAll() {
        return cuentaRepository.findAll();
    }

    public Cuenta getById(Integer id){
        return cuentaRepository.findById(id).get();
    }

    public Cuenta save(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public void delete(Integer id) throws CuentaNotFoundException {
        if (!cuentaRepository.existsById(id)){
            throw new CuentaNotFoundException("Cuenta no encontrada");
        }
        cuentaRepository.deleteById(id);
    }

    public Cuenta depositar(Integer id, float monto){
        cuentaRepository.actualizarMonto(id ,monto);
        return cuentaRepository.findById(id).get();
    }

    public Cuenta retirar(Integer id, float monto){
        cuentaRepository.actualizarMonto(id ,- monto);
        return cuentaRepository.findById(id).get();
    }
}









