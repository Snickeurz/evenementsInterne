package appli.banque;
import appli.lib.PropertyChangeSupport;
/**
 * Implementation of compte Epargne.
 * 
 * @author SIBAUD Nicolas & LUTUMBA Jonathan
 */
public class Compte_epargne extends PropertyChangeSupport implements CompteBancaire
{
   /**
    * Compte epargne's solde.
    */
    private float solde;
    /**
     * Interest rate.
     */
    private float interest_rate;
    /**
     * Associated Client.
     */
    private Client client;
    
    /**
     * Constructor.
     * 
     * @param solde
     * @param interest_rate 
     */
    public Compte_epargne(final float solde, final float interest_rate) 
    {
        this.solde = solde;
        this.interest_rate = interest_rate;
    }

    @Override
    public float getSolde()
    {
        return this.solde;
    }

    public void setSolde(final float solde)
    {
        this.solde = solde;
    }

    @Override
    public boolean transfer(final float amount, final CompteBancaire beneficiary)
    {
        if (withdraw(amount))
        {
            return beneficiary.feed(amount);
        }
        return false;
    }

    @Override
    public boolean request(float amount, CompteBancaire source) 
    {
        if (source.withdraw(amount))
        {
            return feed(amount);
        }
        return false;
    }

    @Override
    public boolean feed(final float amount)
    {
        solde += amount;
        client.addOperation("[Compte Epargne] feed : + " + amount);
        //client.showOperation();
        return true;
    }

    @Override
    public boolean withdraw(final float amount) 
    {
        boolean res = (solde - amount) >= 0;
        if ((solde - amount) <= 0) 
        {
            client.addOperation("[Compte Epargne] Withdraw : - "+solde);
            solde = 0;            
        }
        else 
        {
            solde -= amount;
            client.addOperation("[Compte Epargne] Withdraw : - "+amount);
            //client.showOperation();
        }
        return res;
    }

    /**
     * Calcul interest.
     */
    public void interest()
    {
        client.addOperation("[Compte Epargne] Interest :  "+ solde * interest_rate);
        solde *= (1 + interest_rate);
    }

    public Client getClient()
    {
        return this.client;
    }

    public void setClient(final Client client)
    {
        this.client = client;
    }

}
