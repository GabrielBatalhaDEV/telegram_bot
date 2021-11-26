package telegram_bot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import com.google.gson.Gson;

public class WheatherAPI {
	

	
	String url = "https://api.hgbrasil.com/weather?fields=";

	
	public String getTempo() throws Exception {
		
		String tempo = "";
		
		try {
			
		String fields = "only_results,description,humidity,wind_speedy,city,&key=a3299fbb";
		URL url = new URL(this.url + fields );
        HttpURLConnection conexao = (HttpURLConnection)url.openConnection();
        BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
        String jsonEmString = Util.converteJsonEmString(resposta);

        Gson gson = new Gson();
        Tempo previsao = gson.fromJson(jsonEmString, Tempo.class);
        
        conexao.disconnect();
        
        tempo = "S�o Paulo, SP \nTempo: " + previsao.getDescription() + "\nUmidade: " + previsao.getHumidity() +"%\nVel. do vento: " + previsao.getWind_speedy();
 		}
		catch(Exception e) {
			tempo = "Error" + e.getMessage();
		}
		
		return tempo;
		
	}
	
public String getTemperatura() throws Exception {
		
		String temperatura = "";
	
		try {
		
		String fields = "only_results,temp,city,&key=a3299fbb";
		URL url = new URL(this.url + fields );
        HttpURLConnection conexao = (HttpURLConnection)url.openConnection();
        BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
        String jsonEmString = Util.converteJsonEmString(resposta);

        Gson gson = new Gson();
        Temperatura previsao = gson.fromJson(jsonEmString, Temperatura.class);
        
        conexao.disconnect();
        
        temperatura = "S�o Paulo, SP \n \nTemperatura: "+ previsao.getTemp() +"�C";
 		}
		catch(Exception e) {
			temperatura = "Error" + e.getMessage();
		}
		
	
		
		return temperatura;
		
	}


	
}