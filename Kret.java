public class Kret extends Thread {

    private static Kretowisko kretowisko = new Kretowisko();

    private static boolean randomBoolean() {
        return (int) (Math.random() * 100) % 2 == 0;
    }

    private boolean sleepingRoom;
    private boolean kitchen;
    private boolean bathroom;
    String name;
    int hp;

    Kret(String name) {
        this.name = name;
        this.hp = (int) (Math.random() * 100);
        this.sleepingRoom = randomBoolean();
        this.kitchen = randomBoolean();
        this.bathroom = randomBoolean();
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep((long) (100));
                kretowisko.enterSleepingRoom(this);
                sleep(1000);
                kretowisko.exitSleepingRoom();
            } catch (InterruptedException e) {
                System.out.println("umarł " + name);
                break;
            }
        }
    }

    void addHp(int hp) {
        this.hp = (this.hp + hp > 100) ? 100 : this.hp + hp;
    }

    void subHp(int hp) {
        this.hp -= hp;
        if (hp < 0) interrupt();
    }
}
