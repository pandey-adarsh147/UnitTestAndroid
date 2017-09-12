package me.syncify.unittestandroid;

/**
 * Created by adarshpandey on 9/12/17.
 */

public class CarSelector {
    private final CarBuilderService service;

    public CarSelector(CarBuilderService service) {
        this.service = service;
    }

    public void buyRedFerrari(){
        BuyingCriteria criteria = new BuyingCriteria();
        criteria.setColor("RED");
        criteria.setModel("FERRARI");
        service.buildCar(criteria);
    }

    public void buyPinkCaddilac(){
        BuyingCriteria criteria = new BuyingCriteria();
        criteria.setColor("HOT PINK");
        criteria.setModel("CADDILAC");
        service.buildCar(criteria);
    }

    public interface CarBuilderService {

        void buildCar(BuyingCriteria criteria);

    }

    public class BuyingCriteria {

        private String color;
        private String model;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        // getters and setters

    }
}
