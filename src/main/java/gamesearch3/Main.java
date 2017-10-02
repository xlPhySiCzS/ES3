package gamesearch3;

public class Main {

	private static Model model;
	
	public static void main(String[] args) throws Exception {

		model = Model.getInstance();
		View view = new View();
		SearchControl searchControl = new SearchControl(view, model);
		model.registerObserver(view); //connection Model -> View
		view.setControllerSearch(searchControl);
		view.receiveUsersMessages();

	}
}