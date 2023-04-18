public class Vehicle {

    private String vehicleBrand;
    private long vehicleYear;
    private long vehicleID;
    private long vehicleMileage;

    public Vehicle(String vehicleBrand, long vehicleYear, long vehicleMileage) {
        this.vehicleBrand = vehicleBrand;
        this.vehicleYear = vehicleYear;
        this.vehicleMileage = vehicleMileage;
    }

    public Vehicle(long vehicleID, long vehicleMileage) {
        this.vehicleID = vehicleID;
        this.vehicleMileage = vehicleMileage;
    }

    public Vehicle(long vehicleID, String vehicleBrand, long vehicleYear, long vehicleMileage) {
        this.vehicleBrand = vehicleBrand;
        this.vehicleYear = vehicleYear;
        this.vehicleMileage = vehicleMileage;
        this.vehicleID = vehicleID;
    }
    public Vehicle(long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public long getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(long vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public long getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public long getVehicleMileage() {
        return vehicleMileage;
    }

    public void setVehicleMileage(long vehicleMileage) {
        this.vehicleMileage = vehicleMileage;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleBrand=" + vehicleBrand +
                " , vehicleYear=" + vehicleYear +
                " , vehicleMileage=" + vehicleMileage +
                " , vehicleID=" + vehicleID +
                '}';
    }
}
