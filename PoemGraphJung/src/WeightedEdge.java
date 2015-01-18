import org.apache.commons.collections15.Factory;

//adapted from Jung2-Tutorial

public class WeightedEdge {
	double capacity; // should be private
    double weight;   // should be private for good practice
    int id;
    private String name;
    
    public WeightedEdge(double weight, double capacity) {
        this.id = (int) weight; // This is defined in the outer class.
        this.weight = weight;
        this.capacity = capacity;
 }
    // public String toString() { // Always good for debugging
    //     return "E"+id;
    //}
   

    public WeightedEdge(String name) {
        this.name = name;
    }
    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }             
    
    public String toString() {
        return name;
    }
    // Singleton factory for creating Edges...
    public static class MyEdgeFactory implements Factory<WeightedEdge> {
        private static int linkCount = 0;
        private static double defaultWeight;
        private static double defaultCapacity;

        private static MyEdgeFactory instance = new MyEdgeFactory();
        
        private MyEdgeFactory() {            
        }
        
        public static MyEdgeFactory getInstance() {
            return instance;
        }
        
        public WeightedEdge create() {
            String name = "Link" + linkCount++;
            WeightedEdge link = new WeightedEdge(name);
            link.setWeight(defaultWeight);
            link.setCapacity(defaultCapacity);
            return link;
        }    

        public static double getDefaultWeight() {
            return defaultWeight;
        }

        public static void setDefaultWeight(double aDefaultWeight) {
            defaultWeight = aDefaultWeight;
        }

        public static double getDefaultCapacity() {
            return defaultCapacity;
        }

        public static void setDefaultCapacity(double aDefaultCapacity) {
            defaultCapacity = aDefaultCapacity;
        }
        
    }
}
