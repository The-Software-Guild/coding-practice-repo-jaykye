package StateCapitals3;

public class Capital {
        private String name;
        private int population;
        private double squareMileage;

        public Capital(String name, int population, double squareMileage){
            this.name = name;
            this.population = population;
            this.squareMileage = squareMileage;
        }

        public String getName(){
            return name;
        }
        public int getPopulation(){
            return population;
        }
        public double getSquareMileage(){
            return squareMileage;
        }
}

