package com.example.demo.Repo;

import java.sql.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entidad.Empleado;
@Repository
public interface MiEntidadRepository extends CrudRepository<Empleado, Integer>{
        
    /*Manera 2 de interactuar con procedure , incia aqui este proceso  */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO genders (id, name) VALUES (:valor1, :valor2)", nativeQuery = true)
    void insertarDatos(@Param("valor1") Integer valor1, @Param("valor2") String valor2);

    /*Manera 3 de interactuar con procedure , incia aqui este proceso  */
    @Modifying
    @Transactional
    @Query(value = "CALL Incertar_GENERO(:valor1, :valor2)", nativeQuery = true)
    void llamarStoredProcedure(@Param("valor1") Integer valor1, @Param("valor2") String valor2);

    @Modifying
    @Transactional
    @Query(value = "CALL AgregarNuevoEmpleado(:nombre, :apellido , :birthdate, :gender, :job)", nativeQuery = true)
    void agregarNuevoEmpleado(
        @Param("nombre") String nombreString,
        @Param("apellido") String apellido,
        @Param("birthdate") Date Fecha,
        @Param("gender") Integer IdGenero,
        @Param("job") Integer IdJob
    );
  

    

}
