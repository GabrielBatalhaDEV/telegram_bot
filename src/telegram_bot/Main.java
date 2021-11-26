package telegram_bot;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class Main {

	public static void main(String[] args) throws Exception {
		
		TelegramBot bot = new TelegramBot(DadosBot.BOT_TOKEN);


		
		WheatherAPI wheater = new WheatherAPI();
		
		GetUpdatesResponse updatesResponse;
		
		SendResponse sendResponse;

		BaseResponse baseResponse;
		
		DateTime data = new DateTime();

		int m = 0;
		while (true) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

			List<Update> updates = updatesResponse.updates();

			String resposta = "";
			
			for (Update update : updates) {

				m = update.updateId() + 1;
				

				
				

				baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));

				
				switch (update.message().text()){
					case "/help":
						resposta = "/data mostra a data de hoje";
						resposta += "\n/hora mostra a hora atual";
						resposta += "\n/tempo mostra o tempo atual";
						resposta += "\n/temperatura mostra temperatura atual";
						resposta += "\n/sobre fala sobre o bot";
						resposta += "\n/help mostra comando do bot";
						break;
					case "/data":
						resposta = data.getData();
					break;
					case "/hora":
						resposta = data.getHora();
						break;
					case "/tempo":
							 resposta = wheater.getTempo();


							
					break;
					case "/temperatura":
						resposta = wheater.getTemperatura();
						break;
					case "/sobre":
						
						resposta = "Este bot consome uma api de previsão de tempo: HG Weather\n\n"
								+ "https://hgbrasil.com";
						break;
				default:
					resposta = "Olá " + update.message().from().firstName() + ",  Sou o bot Tempo \n\nDigite /help para ver informações sobre os comando";
					break;
				}

				System.out.println("Recebendo mensagem: " + update.message().text());

				

				System.out.println("Resposta de Chat Action Enviada? " + baseResponse.isOk());

				sendResponse = bot.execute(new SendMessage(update.message().chat().id(), resposta ));

				System.out.println("Mensagem Enviada? " + sendResponse.isOk());	
				
			}

		}
	}
}
