package setupAPP;

import processing.core.PImage;
public class Setup {


        // Marges, horitzontal i vertical
        public static float edgeH = 20, edgeV = 20;

        public static float logoW = 80, logoH = 80, logoDistH = 40, logoDistV = 40;

        public static float bannerH = 120, lletresBannerW = 320;


        public static float nomW = 300, nomH = 80;

        public static int yButtonInicial = 190;

        public static int fullScreenX = 1310, fullScreenY = 820, fullScreenW = 40, fullScreenH = 40;

        public static float divHsis = 500/3;

        public static int xPaletaColors = 570;
        public static int sizePaletaColors = 800;

        public static String[][] info = {
                {"Auditori de Manacor", "Manacor, Mallorca", "3.20971", "39.57551", "auditori-de-manacor.jpg"},
                {"Edifici Casasayas", "Palma, Mallorca", "2.64916", "39.5712", "edificiCasasayas.jpeg"},
                {"Centre Nàutic Formentera", "La Savina, Formentera", "1.41603", "38.73206", "clubNauticFormentera.jpg"}
        };
        public static String selectedTextTipografia;
        public static String[][] valuesTextList = {
                {"0", "Tipografia 1"},
                {"1", "LADRILLO"},
                {"2", "CASA"},
                {"3", "PÚBLIC"}};

        public static int wButtonsMap = 400;
        public static int yButtonsMap = 60;
        public static PImage mapaIlles;
        public static int xMap = 955;
        public static int yMap = 600;

        public static int xSecondMiddle = 570;
        public static int ySecondMiddle = 350;

        public static int colour = 0;

        public static float size = 5;
        public static float red;
        public static float green;
        public static float blue;


}
