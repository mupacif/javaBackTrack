import java.util.*;

/**
 * Created by Student on 08-02-17.
 */
public class MasterMind {
    private int chance;
    private List<Integer> combination;
    private List<Integer> baseColor;
    private final static int SIZE=4;
    private boolean finished = false;


    public enum Couleurs
    {
        ROUGE,
        ORANGA,
        JAUNE,
        VERT,
        BLEU,
        VIOLET,
        ROSE,
        BLANC,
        GRIS,
        NOIR,

    }

    public static String[] couleurs() {
        Couleurs[] states = Couleurs.values();
        String[] names = new String[states.length];

        for (int i = 0; i < states.length; i++) {
            names[i] = states[i].name();
        }

        return names;
    }
    public MasterMind()
    {
        this.combination = new ArrayList<>(SIZE);
        this.chance=10;
        this.baseColor = Arrays.asList(new Integer[]{0,1,2,3,4,5,6,7,8,9});
        generateCombination();
    }

    /**
     * Generate a combination of colors
     */
    public void generateCombination()
    {

        Random rnd = new Random();
        int colorIndex = 0;
        for(int i=0;i<SIZE;i++) {
            colorIndex=rnd.nextInt(baseColor.size());
            this.combination.add(colorIndex);

        }
        System.out.println("MasterMind Started");
    }

    /**
     * Test the combiation given if it correspond to the generated combination
     * @param combination
     * @return
     */
    public String testCombination(int[] combination)
    {

        //cache the combination
        List<Integer> tmp = new ArrayList<Integer>();
        for (int index = 0; index < this.combination.size(); index++)
        {
            tmp.add(this.combination.get(index));
        }
        //cache the combination
        List<Integer> tmpUtilisateur = new ArrayList<Integer>();
        for (int index = 0; index < combination.length; index++)
        {
            tmpUtilisateur.add(combination[index]);
        }

        String outBlack = "";
        String outWhite = "";
        //test of black pawns
        for(int i = 0;i<SIZE;i++)
        {

            if(combination[i]==this.combination.get(i))
            {
                //write x
                outBlack += "N";
                //then remove from tmp
                tmp.set(i,-1);
                tmpUtilisateur.set(i,-2);
            }
        }
        //test of white pawns
        for(int i: tmpUtilisateur)
        {
            if(tmp.contains(i)) {
                outWhite += "B";
                tmp.removeAll(Collections.singleton(i));
            }
        }

        String out = outBlack+outWhite;
        this.chance--;
        finished =  out.equals("NNNN");
        return outBlack+outWhite;
    }

    public boolean isFinished()
    {
        return finished;
    }
    /**
     * Return left chances
     * @return chances
     */
    public int getChance()
    {
        return this.chance;
    }
    public static void main(String[] args)
    {
        MasterMind mm = new MasterMind();
        do{
              System.out.println(mm.testCombination(getNumberFromKeyBoard()));
            System.out.println("Left chances "+mm.getChance());


        }while(mm.getChance()>0 && !mm.isFinished());

        if(mm.finished)
            System.out.println("You found the correct combination");
        else {
            System.out.println("You not succeded to find the right combination");
            System.out.println("combination was:"+mm.combination);
                 }


    }
    public static int[] getNumberFromKeyBoard()
    {
        Scanner sc = new Scanner(System.in);
        String outString="";
        int[] out = new int[SIZE];
        //return a four digit string
        System.out.println("Enter a combination of " + SIZE + " colors(numbers)");
        showCouleurs();
       while(sc.hasNext() && !(outString = sc.next()).matches("\\d{"+SIZE+"}")) {
           System.out.println("Enter a combination of " + SIZE + " colors(numbers)");
           showCouleurs();
           sc = new Scanner(System.in);
       }

       //Concert the four digit string into int array
       int i=0;
       for(char c: outString.toCharArray())
       {
         out[i] = Character.getNumericValue(c);
         i++;
       }

        return out;
    }

    public static void showCouleurs()
    {
        String[] couleurs = couleurs();
        for(int i=0;i<couleurs.length;i++)
            System.out.println(i+":"+couleurs[i]);
    }
}
