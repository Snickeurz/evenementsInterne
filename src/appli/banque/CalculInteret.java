package appli.banque;
/**
 *
 * @author SIBAUD Nicolas & LUTUMBA Jonathan
 */
import appli.context.AppContext;
import java.util.TimerTask;
public class CalculInteret extends TimerTask 
{
    /**
     * Client.
     */
    private final Client c; 
    /**
     * Context application.
     */
    private final AppContext appContext;

    /**
     * Constructor.
     * 
     * @param client given client
     * @param context given context
     */
    public CalculInteret(final Client client, final AppContext context)
    {
        this.c = client;
        this.appContext = context;
    }

    /**
     * Implementation
     */
    @Override
    public void run() 
    {
        System.out.println("*** CalculInteret.run() implem : Tasker ***");
        this.appContext.operationBancaire(c.getCpt(),"tasker");
    }

}
