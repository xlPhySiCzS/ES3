package gamesearch3;
import java.util.LinkedList;
import java.util.List;

public class Model implements Subject{
	
	private List<Observer> observers = new LinkedList<Observer>();
	
	private static Model uniqueInstance;
	
	private Model(){}
	
	public static Model getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new Model();
		}
		return uniqueInstance;
	}
	
	public void registerObserver(Observer observer){
		observers.add(observer);
	}
	
	public String getNamesearch(String str){
		char a[] = new char[str.length()];
		String str2;
		for(int i=1;i<str.length();i++){
			if(str.charAt(i) == ' '){
				a[i-1] = '+';
			}else{
			a[i-1] = str.charAt(i);
			}
		}
		str2 = new String(a);
		return str2;
	}
	public String getUrl(String NameSearch){
		return "https://www.gamespot.com/search/?indices%5B0%5D=game&indices%5B1%5D=topic&q="+getNamesearch(NameSearch);
		
	}
	public String autoMessage(){
		return "Escreva sua pesquisa usando '>'";
		
	}
	public void sendUrl(long chatId,String url,boolean choseMessage){
		if(choseMessage == true){
			notifyObservers(chatId,url);
		}else{
			notifyObservers(chatId,autoMessage());
		}
	}
	
	public void notifyObservers(long chatId, String NameSearch){
		for(Observer observer:observers){
			observer.update(chatId, NameSearch);
		}
	}		

}
