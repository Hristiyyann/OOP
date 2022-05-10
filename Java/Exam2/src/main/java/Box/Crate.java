package Box;

public class Crate
{
    private String turshiqType;
    private int jarCount;
    private static int maxJars = 10;

    public Crate(String turshiqType) {
        this.turshiqType = turshiqType;
        this.jarCount = 0;
    }

    public void setTurshiqType(String turshiqType) {this.turshiqType = turshiqType;}
    public void setJarCount(int jarCount) {this.jarCount++;}
    public int getJarCount() {return jarCount;}
    public static int getMaxJars() {return maxJars;}
}
