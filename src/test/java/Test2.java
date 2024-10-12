public class Test2 {
    private InputHandler inputHandler;

    public void init(){
        inputHandler = InputHandler.getInstance();

    }

    public void loop(){
        int i = 1;

        while(i < 10){
            inputHandler.checkInput();
        }
    }

    public static void main(String [] args){
        Test2 test = new Test2();
        test.init();
        test.loop();
    }
}
