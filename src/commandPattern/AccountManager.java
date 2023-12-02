package commandPattern;

import java.sql.Statement;

public class AccountManager
{
    private RegisterCommand registerCommand;
    private InfoRegisterCommand infoRegisterCommand;
    private LogInCommand logInCommand;

    public AccountManager(Statement statement){
        this.registerCommand = new RegisterCommand(statement);
        this.infoRegisterCommand = new InfoRegisterCommand(statement, registerCommand);
        this.logInCommand = new LogInCommand(statement);
    }
    public void createAccount() {
        registerCommand.execute();
        infoRegisterCommand.execute();
    }
    public void logInAccount(){
        logInCommand.execute();
        System.out.println("You are logged in!");
    }
}
