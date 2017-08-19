public class Passager implements Comparable<Passager>{
    public String nom;


    Passager( String nom){
        if (nom==null)
            throw new NullPointerException();
        this.nom = nom;
    }
    
    public String toString(){
        return nom;
    }

    // la date ne compte pas dans la comparaison...
    // ce sera donc la premiere inscription qui comptera
    public boolean equals(Object that) {
        if(that==this) return true;
        if(!(that instanceof Passager)) return false;
        Passager e = (Passager)that;
        return e.nom.equals(nom);
    }

    public int hashCode() {
        return nom.hashCode();
    }

    // comparaison sur le nom et ensuite le prénom
    public int compareTo(Passager that) {
        if(that==this) return 0;
        if(that==null)
            throw new NullPointerException(this+" comparé avec null");
        int nomCmp = nom.compareTo(that.nom);
        return nomCmp;
    }


 
    // méthodes d'accés
    public String nom(){return nom;}

 }   