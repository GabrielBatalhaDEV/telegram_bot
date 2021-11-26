package telegram_bot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
	
	public String getData() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return "Data: " + formatter.format(new Date());
    }

    public String getHora() {
    	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return "Hora: " + formatter.format(new Date());
    }
}