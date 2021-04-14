/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.modelo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author JLuis
 */
public class GestorRegistroVacunado_JPaxi {
    private ArrayList<RegistroVacunado_JPaxi> lista;

    public GestorRegistroVacunado_JPaxi() 
    {
        lista = new ArrayList<RegistroVacunado_JPaxi>();
    }

    public ArrayList<RegistroVacunado_JPaxi> getLista() {
        return lista;
    }

    public void setLista(ArrayList<RegistroVacunado_JPaxi> lista) {
        this.lista = lista;
    }
     
    public void insertarVacuna(RegistroVacunado_JPaxi item)
    {
      lista.add(item);
    }
    public void modificarVacuna(int pos, RegistroVacunado_JPaxi item)
    {
      lista.set(pos, item);
    }
    public void eliminarVacuna(int pos)
    {
      lista.remove(pos);
    }
   
    public int obtieneId()
    {
      int idaux=0;
      
      for (RegistroVacunado_JPaxi item:lista)
      {
          idaux=item.getId();
      }
      return idaux + 1;
    }
    
    public int ubicarProducto(int id)
    {
        int pos=-1;
        Iterator<RegistroVacunado_JPaxi> it = lista.iterator();
        
        while ( it.hasNext())
        {
            ++pos;
            RegistroVacunado_JPaxi aux = it.next();
            
            if(aux.getId()==id)
            {
                break;
            }
        }
        return pos;
    }
}
