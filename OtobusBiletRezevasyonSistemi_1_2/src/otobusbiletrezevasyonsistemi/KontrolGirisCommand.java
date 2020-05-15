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
public class KontrolGirisCommand {
    Command yer;
    
    public KontrolGirisCommand(){
        
    }

    public void setYer(Command command) {
        yer = command;
    }
    public void butonAction(String kullanici,ActionEvent a){
        yer.giris(kullanici, a);
        System.out.println("KONTROL GİRİS COMMAND");
        
    }
    
    
    
}
