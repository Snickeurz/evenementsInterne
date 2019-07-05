/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appli.banque.Strategy;

import appli.banque.CalculInteret;
import appli.banque.Compte_Principale;
import appli.context.AppContext;
/**
 * Implementation of Abstract Strategy for a strategy of interest.
 * 
 * @author SIBAUD Nicolas & LUTUMBA Jonathan
 */
public class Strategy_interest extends AbstractStrategy
{
    /**
     * Application context.
     */
    private AppContext applicationContext;
    
    /**
     * Constructor.
     * @param principalAccount given account
     * @param applicationContext given context
     */
    public Strategy_interest(final Compte_Principale principalAccount, final AppContext applicationContext) 
    {
        super(principalAccount);
        this.applicationContext = applicationContext;
    }

    /**
     * Implementation of action.
     */
    @Override
    public void action() 
    {
        cpt.getEpargne().interest();
        applicationContext.getAgenda().schedule(new CalculInteret(cpt.getClient(), applicationContext), 1000*60);
    }
}
