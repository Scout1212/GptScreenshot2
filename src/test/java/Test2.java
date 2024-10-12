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
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String [] args){
        Test2 test = new Test2();
        test.init();
        test.loop();
    }
}
