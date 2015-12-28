package dp.leiba.music.creation;

import java.util.Arrays;

/**
 * Music theory.
 */
public class Theory
{

    public static final int     OCTAVES             = 10;
    public static final int     TONES               = 12;

    public static final double  C0                  = 16.35;
    public static final double  INTERVAL            = Math.pow(2.0, 1.0 / 12);
    public static final double  INTERVAL_PARALLEL   = 3;

    public static final double  INTERVAL_UNISON     = 0;  // Прима
    public static final double  INTERVAL_SECOND     = 2;  // Секунда
    public static final double  INTERVAL_THIRD      = 4;  // Терция
    public static final double  INTERVAL_FORTH      = 5;  // Кварта
    public static final double  INTERVAL_FIFTH      = 7;  // Квинта
    public static final double  INTERVAL_SIXTH      = 9;  // Секста
    public static final double  INTERVAL_SEVENTH    = 11; // Септима
    public static final double  INTERVAL_OCTAVE     = 12; // Октава

    public static final double  INTERVAL_AUG        = 1;
    public static final double  INTERVAL_BIG        = 0;
    public static final double  INTERVAL_SMALL      = -1;
    public static final double  INTERVAL_DIM        = -2;

    private static final String[] names = new String[]{
        "C",  "C#", "D",  "D#",
        "E",  "F",  "F#", "G",
        "G#", "A",  "A#", "B"
    };

    /**
     * Get note frequency.
     *
     * @param note Note.
     *
     * @return Frequency.
     */
    public static double getNoteFreq(int note)
    {
        double freq = C0;

        for (int i = 1; i <= note - 1; i++) {
            freq *= INTERVAL;
        }

        return freq;
    }

    /**
     * Get note name.
     *
     * @param note Note.
     *
     * @return Name.
     */
    public static String getNoteName(int note)
    {
        int octave  = getNoteOctave(note);
        int tone    = note - (octave * TONES);

        return names[tone - 1] + octave;
    }

    /**
     * Get note octave.
     *
     * @param note Note.
     *
     * @return Octave.
     */
    public static int getNoteOctave(int note) {
        return ((note - (note % TONES)) / TONES + ((note % TONES) > 0 ? 1 : 0)) - 1;
    }

    /**
     * Get note parallel.
     *
     * @param tone    Tone.
     * @param isMajor Is major.
     *
     * @return Parallel note.
     */
    public static int getNoteParallel(int tone, boolean isMajor)
    {
        tone += isMajor ? -INTERVAL_PARALLEL : INTERVAL_PARALLEL;
        return tone < 1 ? tone + TONES : tone;
    }

    /**
     * Get harmony for tone in one octave.
     *
     * @param tone    Tone.
     * @param isMajor Is major.
     *
     * @return Harmony.
     */
    public static int[] getHarmony(int tone, boolean isMajor)
    {
        return getHarmony(tone, isMajor, OCTAVES);
    }

    /**
     * Get harmony for tone in one octave.
     *
     * @param tone    Tone.
     * @param isMajor Is major.
     * @param octaves Octaves.
     *
     * @return Harmony.
     */
    public static int[] getHarmony(int tone, boolean isMajor, int octaves)
    {
        int[] harmony = new int[60];

        return harmony;
    }

    /**
     * Get harmony for tone in all octaves.
     *
     * @param note Note.
     *
     * @return Harmony.
     */
    public static int[] getHarmonyOctave(int note, boolean isMajor)
    {
        if (!isMajor) {
            note = getNoteParallel(note, false);
        }

        int[] harmony = new int[] {
            note,
            note + (int) INTERVAL_SECOND,
            note + (int) INTERVAL_THIRD,
            note + (int) INTERVAL_FORTH,
            note + (int) INTERVAL_FIFTH,
            note + (int) INTERVAL_SIXTH,
        };

        if (!isMajor && note > INTERVAL_PARALLEL) {
            harmony[harmony.length - 1] -= TONES;
        }

        Arrays.sort(harmony);
        return harmony;
    }

    /**
     * Get chord major.
     *
     * @param note Note.
     *
     * @return Chord.
     */
    public static int[] getChordMajor(int note)
    {
        return new int[]{
            note,
            note + (int) INTERVAL_THIRD,
            note + (int) INTERVAL_FIFTH
        };
    }

    /**
     * Get chord minor.
     *
     * @param note Note.
     *
     * @return Minor.
     */
    public static int[] getChordMinor(int note)
    {
        return new int[]{
            note,
            note + (int) INTERVAL_FIFTH + (int) INTERVAL_SMALL,
            note + (int) INTERVAL_FIFTH
        };
    }
}
