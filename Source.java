public class Source {
    static Kret[] krety = new Kret[20];
    public static void main(String[] args) {
        for(int i=0;i<krety.length;i++){
            krety[i] = new Kret("kret " +i);
            krety[i].start();
        }

        System.out.println("Krety");
        Thread killer = new Thread(() -> {
            try {
                for (Kret kret : krety) {
                    Thread.sleep((long) (Math.random()*5000));
                    kret.interrupt();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        killer.start();

    Thread kretCreator = new Thread(new Runnable() {
        @Override
        public void run() {

            }
    }   );
    }
}
