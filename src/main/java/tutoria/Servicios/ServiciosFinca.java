/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.Servicios;

import tutoria.Modelo.Finca;
import tutoria.Repositorio.FincaRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service
public class ServiciosFinca {
        @Autowired
    private FincaRepositorio metodosCrud;
    
    public List<Finca> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Finca> getFinca(int idFinca){
        return metodosCrud.getFinca(idFinca);
    }
    
    public Finca save(Finca finca){
        if(finca.getId()==null){
            return metodosCrud.save(finca);
        }else{
            Optional<Finca> evt=metodosCrud.getFinca(finca.getId());
            if(evt.isEmpty()){
                return metodosCrud.save(finca);
            }else{
                return finca;
            }
        }
    }
    public Finca update(Finca finca){
        if(finca.getId()!=null){
            Optional<Finca> e=metodosCrud.getFinca(finca.getId());
            if(!e.isEmpty()){
                if(finca.getName()!=null){
                    e.get().setName(finca.getName());
                }
                if(finca.getAddress()!=null){
                    e.get().setAddress(finca.getAddress());
                }
                if(finca.getExtension()!=null){
                    e.get().setExtension(finca.getExtension());
                }
                if(finca.getDescription()!=null){
                    e.get().setDescription(finca.getDescription());
                }
                if(finca.getCategory()!=null){
                    e.get().setCategory(finca.getCategory());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return finca;
            }
        }else{
            return finca;
        }
    }


    public boolean deleteFinca(int fincaId) {
        Boolean aBoolean = getFinca(fincaId).map(finca -> {
            metodosCrud.delete(finca);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    
}
