/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otobusbiletrezevasyonsistemi;

import util.DBConnection;

/**
 *
 * @author hp
 */
public abstract class musteriAbstractController {
    musteriDAO musteriDAO=new musteriDAO();
    DBConnection db=DBConnection.getDBConnection();
}
