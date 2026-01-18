//LEGGERE LE ISTRUZIONI NEL FILE ESERCIZIO.md

//Import di Classi Java necessarie al funzionamento del programma
import utils.ImageRGB;

// Classe principale, con metodo main
class Esercizio {

    //Elimina le componenti Verde e Blu, lasciando solo il Rosso
    public static void filtroRosso( int[][] R, int[][] G, int[][] B ) {
       for ( int i=0; i<G.length; ++i ) {
        for ( int j=0; j<G[0].length; ++j ) {
            G[i][j] = 0;
            B[i][j] = 0;
        }
    }
    }
    
    //Bianco e nero: R, G, B impostati alla media dei valori
    public static void filtroBW( int[][] R, int[][] G, int[][] B ) {
        for ( int i=0; i<G.length; ++i ) {
        for ( int j=0; j<G[0].length; ++j ) {
            int media = (R[i][j] + G[i][j] + B[i][j]) / 3;
            R[i][j] = media;
            G[i][j] = media;
            B[i][j] = media;
        }
    }
    }

    //I pixel vicino al centro hanno più luminosità
    public static void filtroCentro(int[][] R, int[][] G, int[][] B) {
        int centroI = G.length / 2;
    int centroJ = G[0].length / 2;
    double distanzaMax = Math.sqrt( centroI*centroI + centroJ*centroJ);
    for ( int i=0; i<G.length; ++i ) {
        for ( int j=0; j<G[0].length; ++j ) {
            double di = Math.abs(i - centroI);
            double dj = Math.abs(j - centroJ);
            double distanza = Math.sqrt(di*di + dj*dj);
            
            
            double distanzaFattore = 1.0 - (distanza/distanzaMax);
            R[i][j] *= distanzaFattore; 
            G[i][j] *= distanzaFattore; 
            B[i][j] *= distanzaFattore; 
        }
    }
    }

    //Effetto glitch (rosso e blu sfasati)
    public static void filtroGlitch(int[][] R, int[][] G, int[][] B) {
        int righe = R.length;
    int colonne = R[0].length;
    int[][] R_orig = new int[righe][colonne];
    int[][] B_orig = new int[righe][colonne];
    
    for ( int i=0; i<R.length; ++i ) {
        for ( int j=0; j<R[0].length; ++j ) {
            R_orig[i][j] = R[i][j];
            B_orig[i][j] = B[i][j];
            R[i][j] = 0;
            B[i][j] = 0;
        }
    }
    }

    //Oscura i pixel che non sono Mare (prevalenza blu)
    public static void filtroMare(int[][] R, int[][] G, int[][] B) {
         for ( int i=0; i<G.length; ++i ) {
        for ( int j=0; j<G[0].length; ++j ) {
            if ( R[i][j] < 127 && G[i][j] < 127 && B[i][j] >= 127 ) {
                ;
            } else {
                R[i][j] = 0; 
                G[i][j] = 0; 
                B[i][j] = 0; 
            }
        }
    }
}

    //Oscura i pixel che non sono Spiaggia (prevalenza giallo)
    public static void filtroSpiaggia(int[][] R, int[][] G, int[][] B) {
        for ( int i=0; i<G.length; ++i ) {
        for ( int j=0; j<G[0].length; ++j ) {
            if ( R[i][j] >= 127 && G[i][j] >= 127 && B[i][j] < 127 ) {
                ;
            } else {
                R[i][j] = 0; 
                G[i][j] = 0; 
                B[i][j] = 0; 
            }
        }
    }
}

    //Oscura i pixel che non sono Vegetazione (prevalenza verde)
    public static void filtroVegetazione(int[][] R, int[][] G, int[][] B) {
        for ( int i=0; i<G.length; ++i ) {
        for ( int j=0; j<G[0].length; ++j ) {
            if ( R[i][j] < 100 && G[i][j] >= 100 && B[i][j] < 100 ) {
                ;
            } else {
                R[i][j] = 0; 
                G[i][j] = 0; 
                B[i][j] = 0; 
            }
        }
    }
    }

    
    public static void main(String args[]) {

        try {
            //Attiva qui uno dei due file immagine da leggere:
            String nomeFileIn = "faro";
            //String nomeFileIn = "foto_aerea";

            //Conversione dell'immagine in matrici parallele R, G, B
            ImageRGB.RGB rgb = ImageRGB.extractRGB("immagini/" + nomeFileIn + ".png");
            int[][] R = rgb.R();
            int[][] G = rgb.G();
            int[][] B = rgb.B();

            //Qui R, G, B contengono i livelli di rosso, verde, blu per ogni pixel.
            //I valori vanno da 0 a 255, da bassa intensità ad alta intensità.
            //Visualizzo la dimensione dell'immagine considerando il rosso (verde e blu hanno la stessa dimensione).
            System.out.println("Dimensione immagine: " + R.length+ "x" + R[0].length );

            //Chiama qui la funzione filtro, passando come parametri le matrici R,G,B da modificare, ad es.:
            //filtroRosso(R, G, B);
            //___

            //Assegna il nome del file di output (immagine con filtro), ad es. "faro_rosso.png":
            String nomeFileOut = "___";

            ImageRGB.writeRGB(R, G, B, nomeFileOut);
 
        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}

//LEGGERE LE ISTRUZIONI NEL FILE ESERCIZIO.md