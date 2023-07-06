package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import errors.ErrorExisteixAresta;
import errors.ErrorNoExisteixAresta;
import errors.ErrorV_NoTrobat;
import graf.graf_generic;
import hash.hash;
import hash.nodeH;
import objecte.estacio;

public class Menu {
		


	public static void main(String[] args) throws IOException, ParseException, ErrorV_NoTrobat, ErrorExisteixAresta, ErrorNoExisteixAresta  {
			
		
		double potencia =0;
		JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("icaen.json");
        Object ob = parser.parse(reader);
        JSONArray array = (JSONArray)ob;

	
        hash<String,estacio<Double>,Double> HashEstacio = new hash<String, estacio<Double>,Double>();
        
        
        for(int i = 0; i<array.size(); i++) {
        	
        JSONObject objecte = (JSONObject)array.get(i);
        String key = (String) objecte.get("id_estacio");
        double latitud = Double.parseDouble((String) objecte.get("latitud"));
        double longitud = Double.parseDouble((String) objecte.get("longitud"));
        
        	try {
        	potencia = Double.parseDouble((String) objecte.get("potencia"));
        	}
        	catch(java.lang.NumberFormatException e) {
            potencia = 0;
        	}
        	estacio<Double> e1 = new estacio<Double>(key, latitud,longitud);
        	
        
	        if (!HashEstacio.teK(e1.getId_estacio())){
	        	
	        	e1.setALlista_endolls(potencia);
	        	nodeH<estacio<Double>,Double> nodeh = new nodeH<estacio<Double>,Double>(e1);
	        	HashEstacio.put(key,nodeh);	
	        	

	        }else {
	        	estacio<Double> eaux= HashEstacio.get(key).getV();
	        	eaux.setALlista_endolls(potencia);
	        	nodeH<estacio<Double>,Double> nodeh = new nodeH<estacio<Double>,Double>(eaux);
	        	HashEstacio.put(key,nodeh);
	        	
	        	
	        }
	        //System.out.println( HashEstacio.get(key).getV());
        }  
        
        
        double d = 0;
        graf_generic<String,estacio<Double>,Double> grafFinal = new graf_generic<String,estacio<Double>,Double>();
       //meter doble bucle de iterators para poder "afegir_aresta"
        grafFinal.crearGraf(HashEstacio.retornaTaula());
        Iterator<String> i_est1= HashEstacio.setK().iterator();
        Iterator<String> i_est2= HashEstacio.setK().iterator();
        double distMinima=111111111.0;
        estacio<Double> estAux = null;
        int p1= 0;
       
		while(i_est1.hasNext()) {

			distMinima=1111111111;
			String clau1 = i_est1.next();
			estacio<Double> est1 = HashEstacio.get(clau1).getV();
			i_est2 = HashEstacio.setK().iterator();  
			for (int i=0; i<=p1; i++) {
				i_est2.next();
			}
			
			while(i_est2.hasNext()) {
				
				String clau2 = i_est2.next();
				estacio<Double> est2 = HashEstacio.get(clau2).getV();
				d = HashEstacio.calcularDist(est1.getLatitud(),est1.getLongitud(),est2.getLatitud(), est2.getLongitud());
				
				if(d<distMinima) {
					distMinima=d; // distancia minima que no sea menor a 40 ya que sino sera una aresta normal
					estAux = est2;
				}
				if (d<= 40 && d!=0 ) {  // si es menor a 40 haz aresta
					
					grafFinal.afegirAresta(est1.getId_estacio(), est2.getId_estacio(), d);
				
					
					
				
				} 
			}
			if (distMinima > 40 && est1.getId_estacio()!=estAux.getId_estacio()) {
			grafFinal.afegirAresta(est1.getId_estacio(), estAux.getId_estacio(), distMinima);
			
			//System.out.println(est1.getId_estacio());
			//System.out.println(estAux.getId_estacio());
			//System.out.println(distMinima);
			
			}
			p1++;
			
		}  
		System.out.println(grafFinal.existeixAresta("15167086", "13382168"));
		System.out.println(grafFinal.valorAresta("15167086","13382168"));
		grafFinal.valorAresta("32316853","22276270");
		
		
		ArrayList<String> hola = camiOptim("15167086","19090955" , 50, grafFinal, HashEstacio);
		System.out.println(hola);
		ArrayList<String> hola1 = camiOptim("15167086","19090955" , 20, grafFinal, HashEstacio);
		System.out.println(hola1);
		
		
		
		
		
		ArrayList<String> hola2 = zonesDistMaxNoGarantida("15167086", 20, HashEstacio);
		System.out.println(hola2);
		
		
		
	}
	
	// algorisme 1
	public static ArrayList<String> camiOptim(String id1, String id2, double autonomia, graf_generic<String,estacio<Double>,Double> grafFinal,hash<String,estacio<Double>,Double> HashEstacio) throws ErrorV_NoTrobat, ErrorNoExisteixAresta{
		ArrayList<String> cami = new ArrayList<String>();
		if(HashEstacio.get(id1) != null && HashEstacio.get(id2) != null) {
		
			ArrayList<estacio<Double>> veins = grafFinal.adjacents(id1);
			ArrayList<estacio<Double>> aux = new ArrayList<estacio<Double>>();
			HashMap<String, Double> HashDist = new HashMap<String, Double>();
			HashMap<String, String> HashAnterior = new HashMap<String, String>();
			boolean trobat=false;
			
			Iterator<String> i_est1= HashEstacio.setK().iterator();
			
			
			while(i_est1.hasNext()) {
				String clau1 = i_est1.next();
				aux.add( HashEstacio.get(clau1).getV());
				HashDist.put(clau1, Double.MAX_VALUE );
				
			}
			HashDist.put(id1, 0.0);
			
			double dmin;
			String id_min = null;
			double distAux;
			
			while(!trobat && !aux.isEmpty()) {
				dmin = Double.MAX_VALUE;
				for( estacio<Double> est : aux){
					distAux=HashDist.get(est.getId_estacio());
					if(distAux < dmin) { 
						dmin= distAux;
						id_min= est.getId_estacio();
					}
				}
				if(id_min.equals(id2)) {
					trobat=true;
				}else {
					aux.remove( HashEstacio.get(id_min).getV());
					veins= grafFinal.adjacents(id_min);
					for (estacio<Double> vei : veins) {
						distAux = HashDist.get(id_min)+ grafFinal.valorAresta(id_min, vei.getId_estacio());
						Double distEntreNodes = grafFinal.valorAresta(id_min, vei.getId_estacio());
						if( HashDist.get(vei.getId_estacio()) > distAux  && distEntreNodes <= autonomia && aux.contains(vei)) {
							HashDist.put(vei.getId_estacio(), distAux);
							HashAnterior.put(vei.getId_estacio() , id_min);
						}
					}
				}
			}
			
			do {
				cami.add(id2);
				id2=HashAnterior.get(id2);
			}while(!id2.equals(id1));
			cami.add(id1);
				
			
		}else {
			throw new ErrorV_NoTrobat();
		}

		return cami;
	}
	
	//algorisme 2
	static ArrayList<String> zonesDistMaxNoGarantida(String id_ori,int autonomia, hash<String,estacio<Double>, Double> HashEstacio){
		//INICIALITZEM
        hash<String,estacio<Double>, Double> hash=HashEstacio;
		Iterator<String> estacio1 = HashEstacio.setK().iterator();
		nodeH<estacio<Double>, Double> origen = hash.get(id_ori);
		estacio<Double> aux = origen.getV();
		estacio<Double> aux2;
		estacio<Double> auxl;

        double dist = 0;
        double distl = 0;
        String key1=null;
        ArrayList<estacio<Double>> visitats = new ArrayList<>();
        ArrayList<String> noVisitats = new ArrayList<>();
        

        while(estacio1.hasNext()) {
        	key1=null;
        	key1=estacio1.next();
        	//AFEGIR
        	noVisitats.add(key1);
        }
        
        
		Iterator<String> e1 = noVisitats.iterator();

        
		
        while(e1.hasNext()) {
        	key1=e1.next();
        	//
        	aux2 = hash.get(key1).getV();
    		dist = calcularDist(aux.getLongitud(), aux.getLatitud(), aux2.getLongitud(), aux2.getLatitud()); 
    		if(dist<autonomia) {
    			visitats.add(aux2);  			
    			hash.Esborrar(aux2.getId_estacio());
    		}
    		
    		for(int i=0; i<visitats.size(); i++) {
    			auxl = visitats.get(i);
        		distl = calcularDist(auxl.getLongitud(), auxl.getLatitud(), aux2.getLongitud(), aux2.getLatitud()); 
        		if(distl<autonomia) {
	        		if(!visitats.contains(auxl)) {
	        			visitats.add(aux2);  			
	        			hash.Esborrar(aux2.getId_estacio());
	        		}
        		}
    		}
         }
        
        estacio1 = hash.setK().iterator();
        noVisitats=new ArrayList<>();
        while(estacio1.hasNext()) {
        	key1=null;
        	key1=estacio1.next();
        	noVisitats.add(key1);
        }
		
		return noVisitats;
		
	}

	
	//----------------------------------------------------Calcular dist con la formula ---------------------------------------
		public static double calcularDist(double l1,double long1, double l2, double long2) {
			l1= Math.toRadians(l1);
			long1= Math.toRadians(long1);
			l2= Math.toRadians(l2);
			long2= Math.toRadians(long2);
					
			final double Radi_Terra=6371.01;
			double dist = Radi_Terra * Math.acos(Math.sin(l1) * Math.sin(l2) + Math.cos(l1) * Math.cos(l2) * Math.cos(long1-long2));
			return dist;
		}
		
		
	
}



	 

