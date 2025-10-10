package lib;
public class bank {
    private user user;
    private account account;

    public bank() {
        this.user = new user();
        this.account = new account();




    }

    public boolean actions(){
        String arg = "";
        while (true){
            if (arg.equals("quit")){
                return true;
            }
            else if(arg.equals("login")){
                // login action
            }
            else if(arg.equals("register")){
                // create account action
            }
            else {
                break;
            }
        }
        return false;
    }
}
