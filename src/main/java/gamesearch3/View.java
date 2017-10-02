package gamesearch3;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class View implements Observer{

	
	TelegramBot bot = TelegramBotAdapter.build("418000732:AAFUWXF0g9E3k15VmdjHWQV0-9coU7_6yrM");

	//Object that receives messages
	GetUpdatesResponse updatesResponse;
	//Object that send responses
	SendResponse sendResponse;
	//Object that manage chat actions like "typing action"
	BaseResponse baseResponse;		
	
	int queuesIndex=0;

	ControllerSearch controllersearch; //Strategy Pattern -- connection View -> Controller
	
	boolean choseMessage = false;
	
	View(){}
	
	public void setControllerSearch(ControllerSearch controllersearch){ //Strategy Pattern
		this.controllersearch = controllersearch;
	}
	
	
	public void receiveUsersMessages() {

		//infinity loop
		while (true){
		
			//taking the Queue of Messages
			updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(queuesIndex));
			
			//Queue of messages
			List<Update> updates = updatesResponse.updates();

			//taking each message in the Queue
			for (Update update : updates) {
				
				//updating queue's index
				queuesIndex = update.updateId()+1;
				//model.getUrl(update.message().text())
				if(update.message().text().charAt(0) == '>'){
					this.choseMessage = true;
					callController(update);
					
				}else {
					callController(update);
				}	
			}
		}
	}
	
	public void callController(Update update){
		controllersearch.search(update);
		this.choseMessage = false;
	}
	
	public void update(long chatId, String NameSearch){
		sendResponse = bot.execute(new SendMessage(chatId, NameSearch));
	}
	
	public void sendTypingMessage(Update update){
		baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
	}
	

}