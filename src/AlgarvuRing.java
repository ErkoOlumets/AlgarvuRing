/****************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2020/2021 kevadsemester
 *
 * Kodutöö. Ülesanne nr 1a
 *
 * Teema: Algarvuringid
 *
 * Autor: Erko Olumets
 ***************************************/

class AlgarvuRing {
    public static void main(String[] args){
        int i = 311;
        System.out.println("\nKodutöö nr 1a." + " ".repeat(20) + "Programmi väljund");
        System.out.println("=".repeat(51)+ ": \n");
        System.out.println("Antud lähtekoht: " + i);
        System.out.println("Leitud algarvuringid:");
        algarvuRing(i);
        System.out.println("\n"+"=".repeat(51)+ ".");
        System.out.println("Erko Olumets                "+ new java.sql.Timestamp(System.currentTimeMillis()));
  }//main

    /**
     * Algarvuringi leidmine sisendiga a ja leitud algarvuring väljastamine
     *
     * Peamine tsükkel töötab kuni on leitud algarvuring või kahekohalised arvud saavad otsa
     *
     * Teame, et algarvuringiks ei saa olla arv, milles on 0, 2, 4, 5, 6, 8 nii, et optimiseerimiseks vaatame kõige pealt läbi sellised juhud.
     * Kontrollime, kas i on algarv meetodiga kasSobib, kui ei ole siis võtame järgmise arvu
     * Kui i on algarv siis käivitame uue tsükli, mis kontrollib, kas tegu on algarvuringiga kasutades meetodit viimaneEsimeseks
     * Kui üks selle arvu permutatsioonidest ei ole algarv või on suurem kui esialgne arv siis tsükkel katkestatakse
     * ja vaadatakse järgmist arvu
     * Kui kõik permutatsioonid olid algarvud ning ükski neist ei olnud suurem kui esialgne siis väljastatakse see algarvuring
     *
     * Selles tsüklis kasutatakse meetodeid viimaneEsimeseks ja kasSobib, algarvuRing kutsutakse välja peameetodis
     *
     */
    public static void algarvuRing(int a){
        int loendur = 0;
        for (int i = a-1; (loendur < 5) && (i > 10); i--) {
            if (kasSobib(i)) {
                boolean lipp = true;
                int uus = i/10;
                int c = 10;
                while (c<uus){
                    c*=10;
                }

                for (int j = viimaneEsimeseks(i, c); j!=i; j = viimaneEsimeseks(j, c)) {
                    if (!kasSobib(j)|| j>i){
                        lipp = false;
                        break;
                    }
                }
                if (lipp){
                    ++loendur;
                    System.out.println(i);
                }
            }
        }
    }//algarvuRing

    /**
     * Meetod viib arvu viimase numbri esimeseks
     *
     * Töötab alates kahekohalistest arvudest, kuid kuna seda kutsutakse välja meetodis algarvuRing,
     * kus on juba tehtud kontroll kahekohalise arvu jaoks siis see ei mõjuta.
     *
     * Võetud 1. praktikumi materjalidest
     */
    static int viimaneEsimeseks(int h, int a){
        int uus = h/10;
        int algus = h%10;
        return a*algus+uus;
    }//viimaneEsimeseks


    /**
     * Algarvu ja 0, 2, 4, 5, 6, 8 sisalduvuse kontrolli meetod
     *
     * Meetod tagastab false kui arv sisaldab 0, 2, 4, 5, 6, 8 või kui tegu ei ole algarvuga.
     * leitud ja kohandatud: https://stackoverflow.com/questions/2385909/what-would-be-the-fastest-method-to-test-for-primality-in-java
     */
    static boolean kasSobib(long n) {
        if(n%3 == 0) return false;
        String d=String.valueOf(n);
        if (d.contains("0") || d.contains("2") || d.contains("4") || d.contains("5") || d.contains("6") || d.contains("8")) return false;
        long sqrtN = (long)Math.sqrt(n)+1;
        for(long i = 6L; i <= sqrtN; i += 6) {
            if(n%(i-1) == 0 || n%(i+1) == 0) return false;
        }
        return true;
    }//kasSobib

}//klass AlgarvuRing
