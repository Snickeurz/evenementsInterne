package appli.banque;
import java.util.Date;
import java.util.HashMap;
/**
 *
 * @author SIBAUD Nicolas & LUTUMBA Jonathan
 */
public class Client 
{
    /*
    * Id client.
    */
    private Integer id_client;
    /**
     * Client's lastname.
     */
    private String nom;
    /**
     * Client's firstname.
     */
    private String prenom;
    /**
     * Client's adress.
     */
    private String adresse;
    /**
     * Operations.
     */
    private HashMap<Integer,String> operation;
    /**
     * Transaction.
     */
    private int transaction_number = 0;
    /**
     * Client's birthday.
     */
    private Date birthday;
    /**
     * Client's principal account.
     */
    private Compte_Principale cpt;

    /**
     * Client's constructor.
     * 
     * @param id_client given id
     * @param nom given lastname
     * @param prenom given firstname
     * @param adresse given adress
     * @param birthday given birthday
     */
    public Client(final Integer id_client, final String nom, final String prenom, final String adresse, final Date birthday)
    {
        this.id_client=id_client;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.birthday = birthday;
        // Initialize Map.
        this.operation = new HashMap<>();
    }

    public Integer getId_client() 
    {
        return this.id_client;
    }

    public void setId_client(final Integer id_client)
    {
        this.id_client = id_client;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(final String nom) 
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return this.prenom;
    }

    public void setPrenom(final String prenom)
    {
        this.prenom = prenom;
    }

    public String getAdresse() 
    {
        return this.adresse;
    }

    public void setAdresse(final String adresse)
    {
        this.adresse = adresse;
    }

    public void addOperation(final String operation)
    {
        this.operation.put(transaction_number++,operation);
    }
    
    /**
     * Show operation implementation.
     */
    public void showOperation()
    {
       System.out.println(String.format("Operation for client : %d", id_client));
       operation.forEach((k,v) -> 
       {
            System.out.println(v);
       });
    }

    public Date getBirthday() 
    {
        return this.birthday;
    }

    public void setBirthday(final Date birthday)
    {
        this.birthday = birthday;
    }

    public Compte_Principale getCpt() 
    {
        return this.cpt;
    }

    public void setCpt(final Compte_Principale comptePrincipal) 
    {
        this.cpt = comptePrincipal;
    }
    
    
    
}
