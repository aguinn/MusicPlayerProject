package Logic;

public class AccountLogic {
	public boolean checkEmail(String email){
		boolean check = email.matches("[a-zA-Z0-9+._%-]+@[a-zA-Z0-9+._%-]+.[a-zA-Z]");
		return check;
	}
	public boolean checkPassword(String password){
		boolean check = true;
		if(!password.matches("/[a-zA-Z]/")){
			check = false;
		}
		if(!password.matches("/[0-9]/")){
			check = false;
		}
		if(!password.matches("[^a-zA-Z0-9-]")){
			check = false;
		}
		return check;
	}
}
