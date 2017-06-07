import java.util.concurrent.Semaphore;

public class Kretowisko {
    private Semaphore sleepingroomSemaphore = new Semaphore(5);
    private Semaphore kitchenSemaphore = new Semaphore(5);
    private Semaphore batchroomSemaphore = new Semaphore(5);
    void enterSleepingRoom(Kret kret) throws InterruptedException {
            sleepingroomSemaphore.acquire();
            System.out.println("enter sleeping room "+kret.name);
    }
    void exitSleepingRoom(){
        sleepingroomSemaphore.release();
        System.out.println("exit sleeping room");
    }
    void enterKitchen(Kret kret){
        System.out.println("enter kitchen "+kret.name);
    }
    void exitKitchen(){
        System.out.println("exit kitchen");
    }
    void enterBathroom(Kret kret){
        System.out.println("enter bathroom "+kret.name);
    }
    void exitBathroom(){
        System.out.println("exit bathroom");
    }
}

