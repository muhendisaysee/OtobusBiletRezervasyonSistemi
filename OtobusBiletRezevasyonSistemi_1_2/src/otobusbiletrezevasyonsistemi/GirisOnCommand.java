/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otobusbiletrezevasyonsistemi;

import javafx.event.ActionEvent;

/**
 *
 * @author hp
 */
public class GirisOnCommand implements Command{
    Giris giris = new Giris();
    public GirisOnCommand() {
        
    }
    @Override
    public void giris(String kullanici,ActionEvent a) {
          giris.girisOn(kullanici,a);
    }
    
}
