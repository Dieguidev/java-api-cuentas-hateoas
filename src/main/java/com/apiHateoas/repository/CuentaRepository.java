package com.apiHateoas.repository;

import com.apiHateoas.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    @Query("UPDATE Cuenta c SET c.monto=c.monto + ?2 WHERE c.id=?1")
    @Modifying
    void actualizarMonto(Integer id,float monto);


}
