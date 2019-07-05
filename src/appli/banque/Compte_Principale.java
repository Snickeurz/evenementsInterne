package appli.banque;
import appli.lib.PropertyChangeSupport;
/**
 *
 * @author SIBAUD Nicolas & LUTUMBA Jonathan
 */
public class Compte_Principale  extends PropertyChangeSupport implements CompteBancaire
{
    /**
     * Compte Principale's id.
     */
    private static int id = 0;
    /**
     * Compte's id.
     */
    private final int compte_id;
    /**
     * Associated Client.
     */
    private Client client;
    /**
     * Principal account's solde.
     */
    private float solde;
    /**
     * Transfer from main account to epargne
     */
    private float client_set_upper_bound;
    /**
     * Transfer from epargne to main account
     */
    private float client_set_lower_bound;
    /**
     * Epargne account associated.
     */
    private Compte_epargne epargne;
      
    /**
     * Constructor.
     * 
     * @param client given client
     * @param solde given solde
     * @param client_set_upper_bound upper bound
     * @param client_set_lower_bound lower bound
     * @param epargne compte epargn to associate
     */
    public Compte_Principale(final Client client, final float solde, final float client_set_upper_bound, final float client_set_lower_bound, final Compte_epargne epargne) 
    {
        this.client = client;
        this.solde = solde;
        this.client_set_upper_bound = client_set_upper_bound;
        this.client_set_lower_bound = client_set_lower_bound;
        this.epargne = epargne;
        this.epargne.setClient(client);
        this.compte_id = ++id;
        client.setCpt(this);
    }
    
    public Client getClient()
    {
        return this.client;
    }

    public void setClient(final Client client)
    {
        this.client = client;
    }
    
    public int getId()
    {
        return this.compte_id;
    }

    public Compte_epargne getEpargne() 
    {
        return this.epargne;
    }

    public void setEpargne(final Compte_epargne epargne)
    {
        this.epargne = epargne;
    }

    public float get_Client_set_upper_bound() 
    {
        return this.client_set_upper_bound;
    }

    /**
     * Notify the observers after changing the client_set_upper_bound.
     * 
     * @param client_set_upper_bound upper bound
     */
    public void set_Client_set_upper_bound (final float client_set_upper_bound)
    {
        this.client_set_upper_bound = client_set_upper_bound;
        this.firePropertyChange("upper_bound", solde, solde); 
    }

    public float get_Client_set_lower_bound() 
    {
        return client_set_lower_bound;
    }

    /**
     * Notify the observers after changing the client_set_lower_bound.
     * 
     * @param client_set_lower_bound lower bound
     */
    public void setClient_set_lower_bound(final float client_set_lower_bound) 
    {
        this.client_set_lower_bound = client_set_lower_bound;
        this.firePropertyChange("lower_bound", solde, solde); 
    }

    @Override
    public String toString() 
    {
        return "Compte_Principale{"+"solde=" + solde + ", upper_bound=" + client_set_upper_bound + ", lower_bound=" + client_set_lower_bound +'}';
    }
    
    @Override
    public float getSolde() 
    {
        return solde;
    }

    @Override
    public boolean transfer(final float amount, final CompteBancaire beneficiary)
    {
        if(withdraw(amount))
        {
            return beneficiary.feed(amount);
        }
        return false;
    }

    @Override
    public boolean request(float amount, final CompteBancaire source) 
    {

        float source_solde = source.getSolde();
        if(!source.withdraw(amount))
        {
            amount = source_solde;
        }
        return feed(amount);
        
    }

    @Override
    public boolean feed(float amount)
    {
        float oldValue = this.solde;
        this.solde = oldValue + amount;
        client.addOperation("[Principal] Feed : "+amount);
        
        this.firePropertyChange("upper_bound", oldValue, solde); //notify the observers after changing the solde
        return true; //always return true : no limit on feeding main account
    }

    @Override
    public boolean withdraw(final float amount)
    {
        if(amount > getSolde()) return false; // negative balance
        float oldValue = this.solde;
        this.solde = oldValue - amount;
        client.addOperation("[Principal] withdraw : - "+amount);
        //client.showOperation();
        this.firePropertyChange("lower_bound", oldValue, solde); //notify the observers after changing the solde
        return true;
    }

    
    
    
}
