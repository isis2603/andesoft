/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AndeSoft.rest.mocks;

import AndeSoft.rest.DB.ItinerarioSimuladorDB;
import AndeSoft.rest.dtos.ItinerarioDTO;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author jg.tamura10
 */
/**
 * Mock del recurso Ciudades (Mock del servicio REST)
 */
@Named
@ApplicationScoped
public class ItinerarioLogicMock 
{
    ArrayList itinerarios;
    /**
     * Tiene la lista de los itinerarios de un usuario
     */
    public ItinerarioLogicMock() 
    {
         itinerarios = new ArrayList();  
         
    }
    
    public ArrayList getTodosItinerariosIDPerfil(int id)
    {
        ItinerarioSimuladorDB baseDatos = new ItinerarioSimuladorDB();
        itinerarios = baseDatos.getItinerariosDeUsuario(id);
        return itinerarios;
    }
     public ItinerarioDTO getItinerario(int idP, int idI)
    {
        ItinerarioSimuladorDB baseDatos = new ItinerarioSimuladorDB();
        itinerarios = baseDatos.getItinerariosDeUsuario(idP);
        
        ItinerarioDTO buscado = null;
        
        for(int i=0; i< itinerarios.size(); i++)
        {
            ItinerarioDTO actual =  (ItinerarioDTO) itinerarios.get(i);
            if(actual.darId() == idI)
            {
                buscado = actual;
                break;
            }
        }
        
        return buscado;
    }
     
     public ItinerarioDTO createItinerario(ItinerarioDTO it, int id, int idP)
     {
         ItinerarioSimuladorDB baseDatos = new ItinerarioSimuladorDB();
        itinerarios = baseDatos.getItinerariosDeUsuario(id);
         
         ItinerarioDTO existe= getItinerario(idP, id);
         
         if(existe != null){return null;}
         
         itinerarios.add(it);
         
        baseDatos.setItinerariosDeUsuario(id, itinerarios);
         
         return it;
         
     }
     
     public ItinerarioDTO updateItinerario(int idP, int idI, ItinerarioDTO itN) //throws Exception
     {
         ItinerarioSimuladorDB baseDatos = new ItinerarioSimuladorDB();
        itinerarios = baseDatos.getItinerariosDeUsuario(idP);
         
         for(int i=0;i< itinerarios.size();i++)
         {
             ItinerarioDTO actual = ( ItinerarioDTO)itinerarios.get(i);
             if(actual.darId() == idI)
             {
                 itinerarios.remove(i);
                 itinerarios.add(itN);
                 
                  UsuarioLogicMock.setItinerariosDeUsuario(idP, itinerarios);
                  
                  return itN;
             }
         }
         return null;
         //throw new Exception(" No hay itinerario con ese id");
     }
     public void deleteItinerario(long idP, long idI)
     {

         itinerarios = UsuarioLogicMock.getItinerariosDeUsuario(idP);
         
         for(int i=0;i< itinerarios.size();i++)
         {
             ItinerarioDTO actual = ( ItinerarioDTO)itinerarios.get(i);
             if(actual.darId() == idI)
             {
                 itinerarios.remove(i);
                 
                  UsuarioLogicMock.setItinerariosDeUsuario(idP, itinerarios );
                   
             }
         }
     }
     
     public void setListaEventos(long idPerfil, long idItinerario)
    {
        
    }
    
}
