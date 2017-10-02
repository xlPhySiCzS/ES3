package gamesearch3;

import com.pengrad.telegrambot.model.Update;

public class SearchControl implements ControllerSearch {
	private Model model;
	private View view;
	public SearchControl(View view, Model model){
		this.view = view;
		this.model = model;
		
	}
	public void search(Update update) {	
		
		model.sendUrl(update.message().chat().id(), model.getUrl(update.message().text()), view.choseMessage);
		
	}

}
