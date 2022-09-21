package printing_shop;

import printing_shop.utility.IApplication;
import printing_shop.utility.IConsoleApplication;

public class PrintShopApplication implements IApplication, IConsoleApplication {

    private final String welcomeMessage;

    public PrintShopApplication(
            String welcomeMessage,
            ICustomerManager customerManager
    ){
        this.welcomeMessage = welcomeMessage;
    }

    public void Run(){
        System.out.println(this.welcomeMessage);

        while(true){

        }
    }

    @Override
    public void PrintIntroduction() {

    }

    @Override
    public void PrintApplicationFunctions() {

    }
}