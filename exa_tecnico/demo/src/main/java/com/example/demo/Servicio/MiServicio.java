package com.example.demo.Servicio;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repo.MiEntidadRepository;
import com.example.demo.entidad.Empleado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class MiServicio {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private MiEntidadRepository repository;

    public Empleado insertarPersona(String nombre, String apellido, Date fecha, Integer idGenero, Integer idJob) {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("addEmploye")
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .setParameter("p_nombre", nombre)
                .registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN)
                .setParameter("p_apellido", apellido)
                .registerStoredProcedureParameter("p_birthdate", Date.class, ParameterMode.IN)
                .setParameter("p_birthdate", fecha)
                .registerStoredProcedureParameter("p_gender", Integer.class, ParameterMode.IN)
                .setParameter("p_gender", idGenero)
                .registerStoredProcedureParameter("p_job", Integer.class, ParameterMode.IN)
                .setParameter("p_job", idJob)
                .registerStoredProcedureParameter("P_ID", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_existe_empleado", Boolean.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_is_adult", Boolean.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_genero_existente", Boolean.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_valida_job", Boolean.class, ParameterMode.OUT);

        storedProcedureQuery.execute();
        Boolean exiteEmpleado = (Boolean) storedProcedureQuery.getOutputParameterValue("p_existe_empleado");
        Boolean esAdulto = (Boolean)storedProcedureQuery.getOutputParameterValue("p_is_adult");
        Boolean existeGenero = (Boolean) storedProcedureQuery.getOutputParameterValue("p_genero_existente");
        Boolean validaJob = (Boolean)  storedProcedureQuery.getOutputParameterValue("p_valida_job");
        
        Integer id = (Integer) storedProcedureQuery.getOutputParameterValue("P_ID");
        Optional<Empleado> empleadOptional = repository.findById(id);
        Empleado empleado;
        if (empleadOptional.isPresent()) {
            empleado = empleadOptional.get();
            return empleado;
        } else {

            empleado = new Empleado();
            empleado.setNAME(
                    "---------La pila de nombres actulamente existe : " + exiteEmpleado+
                    " ------------has ingresado un usuario adulto :"+esAdulto+
                    " ----------existe el genero ingresado : " + existeGenero+
                    " --------- existe el id job :"+validaJob
                    );
            return empleado;
        }

    }

    /*
     * Manera 2 de interactuar con procedure , esta manera necesita implementar con
     * MiEntidadRepository
     */
    public void realizarInsercion(Integer ID, String valor2) {
        repository.insertarDatos(ID, valor2);
    }

    /*
     * Manera 3 de interactuar con procedure , esta manera necesita implmentar con
     * MiEntidadRepository
     */
    public void insertarPorProcedure(Integer id, String nameString) {
        repository.llamarStoredProcedure(id, nameString);
    }

    public String ejecutarStoredProcedure(Integer parametro1) {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("obtener_datos")
                .registerStoredProcedureParameter("parametro1", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("resultado", String.class, ParameterMode.OUT)
                .setParameter("parametro1", parametro1);

        storedProcedureQuery.execute();

        return (String) storedProcedureQuery.getOutputParameterValue("resultado");
    }

}
