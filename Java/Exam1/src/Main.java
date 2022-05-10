public class Main
{
    public static void main(String[] args)
    {
        Generator gen1 = new Generator("Gen1", 5);
        Generator gen2 = new Generator("Gen2",7);
        Generator gen3 = new Generator("Gen3",10);

        Lamp lamp1 = new Lamp("Lamp1 ",4f);
        Lamp lamp2 = new Lamp("Lamp2",7f);

        Splitter sp1 = new Splitter("Splitter1",10);
        Splitter sp2 = new Splitter("Splitter2",15);

        Timer timer1 = new Timer("Timer1",3);
        Timer timer2 = new Timer("Timer2",2);

        Multiplexer mult1 = new Multiplexer("Multiplexer1", 4);

        gen1.add_connection(null, gen2);
        gen1.add_connection(null, gen3);
        System.out.println(gen3.eval());

        lamp1.add_connection(gen1, null);
        lamp2.add_connection(gen3, null);
        lamp2.add_connection(gen2, null);
        lamp1.eval();

        sp1.add_connection(gen1, null);
        System.out.println(sp1.eval());

        timer1.add_connection(gen1, lamp1);
        System.out.println(timer1.eval());
        System.out.println(timer1.eval());
        System.out.println(timer1.eval());

        mult1.add_connection(gen1, lamp1);
        //mult1.add_connection(gen2, null);
        mult1.eval();
    }
}
