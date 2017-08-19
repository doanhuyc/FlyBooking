import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel
{
	private JTextField inputDeparture, inputDestination;	
    private JTextField inputNom;
	private JTextArea trace;
    private JButton nouveauVolB, listeVolB, removeVolB,
                   listePassagerB,inscriptionVolB;
    public JComboBox days;
    public JComboBox months;
    public JComboBox years;
    
    
    String[] month = new String[13];
    {for (int i=1;i<month.length;i++){
    	month[i]= Integer.toString(i);
    }
    }
    String[] year = { "2011","2012","2013","2014","2015"}; 
    String [] day = new String[32];
    {for(int i = 1; i < day.length; i++){
    	day[i] = Integer.toString(i);}}
    
    SortedMap<Vol,SortedSet<Passager>> LesVols;
    
    

    

    
    public Main(){
	    	creerObjets();
	    	placerObjets(); 	
	    	ecouterObjets();
	    	LesVols = new TreeMap<Vol,SortedSet<Passager>>();
	    }
	    
	  
    private void creerObjets(){
	        inputDeparture = new JTextField(40);
	        inputDestination = new JTextField(40);
	        inputNom=new JTextField(40);
	        trace = new JTextArea(20,80);
	        nouveauVolB = new JButton("nouveau Vol");
	        removeVolB = new JButton("remove Vol");
	        listeVolB = new JButton("liste Vol");
	        listePassagerB = new JButton("liste Passager");
	        inscriptionVolB = new JButton("inscription");
	        days = new JComboBox(day); 
	        months = new JComboBox(month);
	        years = new JComboBox(year);

	    }

	    private void placerObjets(){
	        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	        
	        JPanel pVol = new JPanel();
	        pVol.add(new JLabel("Departure"));
	        pVol.add(inputDeparture);
	        add(pVol);
	        
	        JPanel pDestination = new JPanel();
	        pDestination.add(new JLabel("Destination"));
	        pDestination.add(inputDestination);
	        add(pDestination);
	        
	        JPanel pDate=new JPanel();
	        pDate.add(new JLabel("Day-Month-Year"));
	        pDate.add(days);
	        pDate.add(months);
	        pDate.add(years);
	        add(pDate);
	        
	        JPanel pPassager=new JPanel();
	        pPassager.add(new JLabel("Nom de Passager"));
	        pPassager.add(inputNom);
	        add(pPassager);
	        
	        JPanel controle = new JPanel();
	        controle.add(nouveauVolB);
	        controle.add(listeVolB);
	        controle.add(removeVolB);
	        controle.add(listePassagerB);
	        controle.add(inscriptionVolB);
	        add(controle);
	        add(new JScrollPane(trace));
	        
	    }
	   
	    // Creer une noveua Vol
	    private void nouveauVol(String Departure,String Destination,Date date){
	        Departure = Departure.trim();
	        Destination = Destination.trim();
	        if(Departure.length()==0){
	        	trace.append("nom de Departure vide\n");
	        	return;
	        }
	        if(Destination.length()==0){
	        	trace.append("nom de Destination vide\n");
	        	return;
	        }
	        Vol c= new Vol(Departure,Destination , date);

	        // ajoute un Departure au début de la liste des Departure
	        if (!LesVols.containsKey(c)){
	            LesVols.put(c,new TreeSet<Passager>());
	            trace.append("Ajoute "+ c.toString()+"\n");
	        } else
	            trace.append("Le Vol "+ c.toString() + " existe deja"+"\n");
	    }
	    
	    
	    private void removeVol(String Departure,String Destination,Date date){
	        Departure = Departure.trim();
	        Destination = Destination.trim();
	        if(Departure.length()==0){
	        	trace.append("nom de Departure vide\n");
	        	return;
	        }
	        if(Destination.length()==0){
	        	trace.append("nom de Destination vide\n");
	        	return;
	        }
	        Vol c= new Vol(Departure,Destination , date);

	        // ajoute un Departure au début de la liste des Departure
	        if (LesVols.containsKey(c)){
	            LesVols.remove(c);
	            trace.append("Removed "+ c.toString()+"\n");
	        } else
	            trace.append("Le Vol "+ c.toString() + " n'existe pas"+"\n");
	    }
	   
	   
	    private void listeVols(){
	        // imprime la liste de tous les cours
	        if(LesVols.isEmpty())trace.append("La Liste est vide"+"\n");
	        else{
	        trace.append("Liste des Vols"+"\n");
	        for(Vol c : LesVols.keySet())
	            trace.append(c.toString()+"\n");
	        }
	    }
	    
	    private void listePassager(Vol a){
	        // imprime la liste triée de tous les inscrits à un cours
	       
	        SortedSet<Passager> Pass = LesVols.get(a);
	        if(a.Departure.length()==0){
	        	trace.append("nom de Departure vide\n");
	        	return;
	        }
	        if(a.Destination.length()==0){
	        	trace.append("nom de Destination vide\n");
	        	return;
	        }
	        if (!LesVols.containsKey(a)){
	        	trace.append("Le Vol "+a.toString()+ " n'existe pas"+"\n");
	        	return;
	        }
	        if(Pass.isEmpty()){   
	        
	        	trace.append("Le Vol "+ a.toString() + " n'a aucun Passager"+"\n");
	        	return;
	        }
	        
	        else {
	        	 trace.append("Liste des Passagers du Vol "+ a.toString()+"\n");
	          
	            trace.append(""+Pass+"\n");
	        }
	    }
	    
	    private void inscriptionVol(Vol vol, String nom){
	        // inscrit un nouvel étudiant à un cours
	        if ( nom.length()==0){
	            trace.append("nom vides"+"\n");
	            return;}
	        if(vol.Departure.length()==0){
	        	trace.append("nom de Departure vide\n");
	        	return;
	        }
	        if(vol.Destination.length()==0){
	        	trace.append("nom de Destination vide\n");
	        	return;
	        }
	        else {
	        	SortedSet<Passager> Pass = LesVols.get(vol);
	        	if (Pass == null){
	                trace.append("Le Vol "+ vol.toString() + " n'existe pas"+"\n");
	        		return;}
	        	 Passager e = new Passager(nom);
	        	 if (!Pass.add(e)){
	                    trace.append(nom +" est deja inscrit au vol " +
	                                       vol.toString()+"\n");
	        	 		return;}
	        	 else {
	        	 trace.append("Inscription de "+ nom 
	                               + " au vol "+ vol.toString()+"\n");
	            	           	               	                
	            }
	        }
	    }
	    private void reserverSeige(Vol v , Passager p,Seige s){
	    	if ( p.nom.length()==0){
	            trace.append("nom vides"+"\n");
	            return;}
	        if(v.Departure.length()==0){
	        	trace.append("nom de Departure vide\n");
	        	return;
	        }
	        if(v.Destination.length()==0){
	        	trace.append("nom de Destination vide\n");
	        	return;
	        }
	        if(s.isReserved){
	        	trace.append("La Seige "+s.toString()+" est deja reserve'"+"\n");
	        	return;
	        }
	        
	    }
	    
	    private void ecouterObjets(){
	        nouveauVolB.addActionListener(new ActionListener(){
	                public void actionPerformed(ActionEvent e){
	                   int y= Integer.parseInt(years.getSelectedItem().toString());
	                   int d= Integer.parseInt(days.getSelectedItem().toString());
	                   int m= Integer.parseInt(months.getSelectedItem().toString());
	              	nouveauVol(inputDeparture.getText(),inputDestination.getText(),new Date(y,m,d));
	                }
	            });
	        removeVolB.addActionListener(new ActionListener(){
	        		public void actionPerformed(ActionEvent e){
	        			int y= Integer.parseInt(years.getSelectedItem().toString());
		                int d= Integer.parseInt(days.getSelectedItem().toString());
		                int m= Integer.parseInt(months.getSelectedItem().toString());
		              	removeVol(inputDeparture.getText(),inputDestination.getText(),new Date(y,m,d));
	        		}
	        });
	        listeVolB.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent e){
	        		listeVols();
	        	}
	        });
	        inscriptionVolB.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent e){
        			int y= Integer.parseInt(years.getSelectedItem().toString());
	                int d= Integer.parseInt(days.getSelectedItem().toString());
	                int m= Integer.parseInt(months.getSelectedItem().toString());
	        		Vol c=new Vol(inputDeparture.getText(),inputDestination.getText(),new Date(y,m,d));
	        		inscriptionVol(c,inputNom.getText());
	        	}
	        });
	        listePassagerB.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent e){
        			int y= Integer.parseInt(years.getSelectedItem().toString());
	                int d= Integer.parseInt(days.getSelectedItem().toString());
	                int m= Integer.parseInt(months.getSelectedItem().toString());
	        		Vol c=new Vol(inputDeparture.getText(),inputDestination.getText(),new Date(y,m,d));
	        		listePassager(c);
	        	}
	        });
	        
	    
	    
	    }
	
	public static void main(String[] args){
        JFrame f = new JFrame();
        f.getContentPane().add(new Main());        
        f.setSize(700,350);
        f.setLocation(100,100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}