/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appli.main;

import appli.banque.*;

import java.util.Date;
import appli.context.AppContext;
/**
 *
 * @author SIBAUD Nicolas & LUTUMBA Jonathan
 */
public class MainAppli {
    public static void main(String[] args){
         Client c = new Client(1, "NomClient", "PrenomClient", "MaVille", new Date());
         Compte_epargne cpt_epargne = new Compte_epargne(1000, (float) 0.25);
         Compte_Principale accountPrincipal = new Compte_Principale(c, 6000, 8000, 1000, cpt_epargne);
         
         AppContext ctx = new AppContext();
         ctx.addComptes(accountPrincipal);
         ctx.addClient(c);
         
         //Evenement interne
         //cpt_principal.set_Client_set_upper_bound(2550);
         //cpt_principal.setClient_set_lower_bound(3500);
         //cpt_principal.feed(3000);
         accountPrincipal.withdraw(1600);

         // Evenement externe
         ctx.feed(accountPrincipal, 3000);
         ctx.getOperations(1);
         ctx.withdraw(accountPrincipal, 6500);
         ctx.getOperations(1);
    }
}