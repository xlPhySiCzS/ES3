package gamesearch3;

public interface Subject {
	
	public void registerObserver(Observer observer);
	
	public void notifyObservers(long chatId, String NameSearch);

}
