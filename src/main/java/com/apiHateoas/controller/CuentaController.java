package com.apiHateoas.controller;

import com.apiHateoas.model.Cuenta;
import com.apiHateoas.service.CuentaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cuentas")
public class CuentaController {
    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    @Operation(summary = "listar personas")
    public ResponseEntity<List<Cuenta>> getAll() {
        List<Cuenta> cuentas = cuentaService.getAll();

        if (cuentas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        for (Cuenta cuenta:cuentas) {
            cuenta.add(linkTo(methodOn(CuentaController.class).getById(cuenta.getId())).withSelfRel());
            cuenta.add(linkTo(methodOn(CuentaController.class).getAll()).withRel(IanaLinkRelations.COLLECTION));
        }

        CollectionModel<Cuenta> modelo = CollectionModel.of(cuentas);
        modelo.add(linkTo(methodOn(CuentaController.class).getAll()).withSelfRel());

        return  ResponseEntity.ok(cuentas);
    }

    @GetMapping("/{cuentaId}")
    public ResponseEntity<Cuenta> getById(@PathVariable Integer cuentaId) {
        try {
            Cuenta cuenta = cuentaService.getById(cuentaId);
            cuenta.add(linkTo(methodOn(CuentaController.class).getById(cuenta.getId())).withSelfRel());
            cuenta.add(linkTo(methodOn(CuentaController.class).getAll()).withRel(IanaLinkRelations.COLLECTION));

            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cuenta> save(@RequestBody Cuenta cuenta){
        Cuenta nuevaCuenta = cuentaService.save(cuenta);

        nuevaCuenta.add(linkTo(methodOn(CuentaController.class).getById(nuevaCuenta.getId())).withSelfRel());
        nuevaCuenta.add(linkTo(methodOn(CuentaController.class).getAll()).withRel(IanaLinkRelations.COLLECTION));

        return ResponseEntity.created(linkTo(methodOn(CuentaController.class).getById(nuevaCuenta.getId())).toUri()).body(nuevaCuenta);
    }

    @PutMapping
    public ResponseEntity<Cuenta> update(@RequestBody Cuenta cuenta){
        Cuenta nuevaCuenta = cuentaService.save(cuenta);
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            cuentaService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}








