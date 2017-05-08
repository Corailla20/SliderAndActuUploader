/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slideruploader;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author Corailla20
 */
public class OracleSQL {
    
    private static final OracleConnector connecteur = OracleConnector.getInstance();
    
    public void flush() throws SQLException{
        connecteur.exec("DELETE FROM vehicule2");
        connecteur.exec("COMMIT WORK");
    }
    
    public void addBanniereInDB(String nom, String path) throws SQLException{
        connecteur.exec("INSERT INTO slider VALUES ('" + nom + "','" + path + "')");
    }
    
    public ArrayList<Banniere> getBannieres() throws SQLException {
        ArrayList<Banniere> liste_bannieres = new ArrayList<>();
        int nb = Integer.parseInt(connecteur.execSelectUniqueString("SELECT COUNT(*) FROM slider"));        
        ArrayList<String> id = connecteur.execSelectColonne("SELECT uuid FROM slider");
        for(int i = 0; i < nb; i++){
            ArrayList<String> tmp = connecteur.execSelectLigne("SELECT * FROM slider WHERE uuid='"+id.get(i)+"'");
            liste_bannieres.add(new Banniere(tmp.get(0), tmp.get(1)));
        }
        return liste_bannieres;
    }
    
    public void supprimerBanniere (Banniere b) throws SQLException {
        connecteur.exec("DELETE FROM slider WHERE uuid ='" +b.getNom() + "'");
        connecteur.exec("COMMIT WORK");
    }
    
    public ArrayList<Actualite> getActualites() throws SQLException {
        ArrayList<Actualite> liste_bannieres = new ArrayList<>();
        int nb = Integer.parseInt(connecteur.execSelectUniqueString("SELECT COUNT(*) FROM actualite"));        
        ArrayList<String> id = connecteur.execSelectColonne("SELECT uuid FROM actualite");
        for(int i = 0; i < nb; i++){
            ArrayList<String> tmp = connecteur.execSelectLigne("SELECT * FROM actualite WHERE uuid='"+id.get(i)+"'");
            liste_bannieres.add(new Actualite(tmp.get(1), tmp.get(2), tmp.get(3)));
        }
        return liste_bannieres;
    } 
    
    
    public void supprimerOffre (OffreEmploi o) throws SQLException {
        connecteur.exec("DELETE FROM offreEmploi WHERE description ='" +o.getContenu()+ "'");
        connecteur.exec("COMMIT WORK");
    }
    
    public void addOffreInDB(String metier, String contrat, String lieu, String tel, String contenu) throws SQLException{
        String uuid = UUID.randomUUID().toString();
        connecteur.exec("INSERT INTO offreEmploi VALUES ('" + uuid + "','" + metier + "','" + contrat + "','" + lieu + "','" + tel + "','" +  contenu + "')");
    }
    
    
    public void modifierOffre(String contenuOffre, OffreEmploi newOffre) throws SQLException{
        connecteur.exec("UPDATE offreEmploi SET metier = '" + newOffre.getMetier()+ "', contrat ='" + newOffre.getContrat()+ "', lieu = '" + newOffre.getLieu() + "', description = '" + newOffre.getContenu()+ "' WHERE description = '" + contenuOffre + "'");
    }
    
    public ArrayList<OffreEmploi> getOffres() throws SQLException {
        ArrayList<OffreEmploi> listeOffreEmplois = new ArrayList<>();
        int nb = Integer.parseInt(connecteur.execSelectUniqueString("SELECT COUNT(*) FROM offreEmploi"));        
        ArrayList<String> id = connecteur.execSelectColonne("SELECT uuid FROM offreEmploi");
        for(int i = 0; i < nb; i++){
            ArrayList<String> tmp = connecteur.execSelectLigne("SELECT * FROM offreEmploi WHERE uuid='"+id.get(i)+"'");
            listeOffreEmplois.add(new OffreEmploi(tmp.get(1), tmp.get(5), tmp.get(2), tmp.get(3), tmp.get(4)));
        }
        return listeOffreEmplois;
    } 
    
    public void supprimerActualite (Actualite a) throws SQLException {
        connecteur.exec("DELETE FROM actualite WHERE titre ='" +a.getTitre()+ "'");
        connecteur.exec("COMMIT WORK");
    }
    
    public void addActualiteInDB(String titre, String contenu, String path) throws SQLException{
        String uuid = UUID.randomUUID().toString();
        connecteur.exec("INSERT INTO actualite VALUES ('" + uuid + "','" + titre + "','" + contenu + "','" + path + "')");
    }
    
    public void modifierActu(String nomActu, Actualite newActu) throws SQLException{
        connecteur.exec("UPDATE actualite SET titre = '" + newActu.getTitre() + "', contenu ='" + newActu.getContenu() + "', path = '" + newActu.getUrl() + "' WHERE titre = '" + nomActu + "'");
    }
    
}
