public class ClassModeling {
    class House{
        private int[] gpsCoord = new int[2];
        private int[] threeDimension = new int[3];

        public House(int coord1, int coord2){
            this.gpsCoord[0] = coord1;
            this.gpsCoord[1] = coord2;
        }
        public int[] getGPSCoord(){
            return gpsCoord;
//            return this.gpsCoord;

        }
        public void setGPSCoord(int coord1, int coord2){
            this.gpsCoord[0] = coord1;
            this.gpsCoord[1] = coord2;
        }
    }

    class Airplane{

    }
    public static void main(String[] args) {

    }
}
