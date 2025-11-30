package com.motoextreme.motoextreme.services;

import com.motoextreme.motoextreme.models.entities.Categoria;
import com.motoextreme.motoextreme.models.repositories.ICategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private ICategoria repo;


    //listar categorias
    public List<Categoria> listarCategorias(){

        List<Categoria> listasCategorias = repo.findAll();

        if ((listasCategorias != null) && (!listasCategorias.isEmpty())) {
            return listasCategorias;
        } else {
            return null;
        }
    }

    public Categoria crearCategoria(Categoria categoria){

        return repo.save(categoria);
    }
}
