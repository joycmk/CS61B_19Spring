import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {



    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static Double[] CONCERT = new Double[keyboard.length()];

    private static GuitarString[] guitar = new GuitarString[keyboard.length()];


    public static void frequencyHZ (String keyboard) {
        for (int i=0; i < keyboard.length() ; i++) {
            CONCERT[i] = 440 * Math.pow(2,(i-24)/12);
        }
    }

    public static void initialize() {
        frequencyHZ (keyboard);
        for (int i=0; i < keyboard.length() ; i++) {
            guitar[i] = new GuitarString(CONCERT[i]);
        }
    }

    public static void main(String[] args) {
            /* create two guitar strings, for concert A and C */
        initialize();

        while (true) {

                /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                int index = keyboard.indexOf(StdDraw.nextKeyTyped());
                if (index >=0 && index < keyboard.length()) {
                    guitar[index].pluck();
                }
            }

                /* compute the superposition of samples */
            double sample = 0;

            for (int i=0; i < keyboard.length() ; i++) {
                sample = sample + guitar[i].sample();
            }

                /* play the sample on standard audio */
            StdAudio.play(sample);

                /* advance the simulation of each guitar string by one step */
            for (int i=0; i < keyboard.length() ; i++) {
                guitar[i].tic();
            }
        }
    }
}