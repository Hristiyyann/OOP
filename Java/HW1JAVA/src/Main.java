import java.util.ArrayList;

public class Main
{
    // Тази функци е, за да покажа долу ефектите как променят числата в масива
    public static void print_audio(String audio_name, ArrayList<Float> audio)
    {
        System.out.print("<" + audio_name + "><");
        for(Float number : audio)
        {
            System.out.print(number + ", ");
        }
        System.out.println(">");
    }
    public static void main(String[] args)
    {
        ArrayList<Float> audio = new ArrayList<>();
        audio.add(-5f);
        audio.add(2f);
        audio.add(3f);
        audio.add(-4f);
        audio.add(10f);
        audio.add(12f);
        /*audio.add(7f);
        audio.add(4f);
        audio.add(2f);*/

        Average avg = new Average();
        avg.process(audio);
        System.out.println(avg.toString());

        Max max = new Max();
        max.process(audio);
        System.out.println(max.toString());

        Anomaly anomaly = new Anomaly(3);
        anomaly.process(audio);
        System.out.println(anomaly.toString());

        Mute mute = new Mute();
        mute.process(audio);
        System.out.println(mute.toString());
        print_audio("Mute", mute.getOutput());

        Normalize normalize = new Normalize();
        normalize.process(audio);
        System.out.println(normalize.toString());
        print_audio("Normalize", normalize.getOutput());

        Denoise denoise = new Denoise(3, 2);
        denoise.process(audio);
        System.out.println(denoise.toString());
        print_audio("Denoise", denoise.getOutput());

        //Тук показвам, че горе ефектите не променят нищо по оригиналното аудио и то си остава непроменено
        print_audio("Normal audio", audio);
    }
}
