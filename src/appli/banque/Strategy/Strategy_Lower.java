package appli.banque.Strategy;
import appli.banque.Compte_Principale;
/**
 * Implementation of Abstract Strategy for a lower strategy.
 * 
 * @author SIBAUD Nicolas & LUTUMBA Jonathan
 */
public class Strategy_Lower extends AbstractStrategy
{
    /**
     * Constructor. 
     * 
     * @param principalAccount given account
     */
    public Strategy_Lower(final Compte_Principale principalAccount) 
    {
        super(principalAccount);
    }

    /**
     * Implementation of action.
     */
    @Override
    public void action()
    {
        cpt.request(cpt.get_Client_set_lower_bound()-cpt.getSolde(), cpt.getEpargne());
    }
    
}
