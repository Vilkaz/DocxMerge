package sieve;

import java.util.List;

/**
 * Created by vkukanauskas on 07/03/2016.
 */
public class SieveDTO {
    private int id;
    private int indicator;
    private String location;
    private String disciplin;
    private String name;
    private String number;
    private String pictureURL;
    private String manufactorer;
    private String modelNumber;
    private double previousCosts;
    private int yearOfConstruction;
    private boolean alkal;
    private String alkalUseInstructions;
    private boolean automated;
    private boolean automatisationPosible;
    private boolean storageForMashine;
    private boolean storageForTool;
    private double inventoryValue;
    private List<String> krinkorki;
    private String todo;

    public SieveDTO(int id, int indicator, String location, String disciplin, String name, String number, String pictureURL) {
        this.id = id;
        this.indicator = indicator;
        this.location = location;
        this.disciplin = disciplin;
        this.name = name;
        this.number = number;
        this.pictureURL = pictureURL;
    }

    //region getter and setter

    public int getId() {
        return id;
    }

    public int getIndicator() {
        return indicator;
    }

    public String getLocation() {
        return location;
    }

    public String getDisciplin() {
        return disciplin;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getManufactorer() {
        return manufactorer;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public double getPreviousCosts() {
        return previousCosts;
    }

    public int getYearOfConstruction() {
        return yearOfConstruction;
    }

    public boolean isAlkal() {
        return alkal;
    }

    public String getAlkalUseInstructions() {
        return alkalUseInstructions;
    }

    public boolean isAutomated() {
        return automated;
    }

    public boolean isAutomatisationPosible() {
        return automatisationPosible;
    }

    public boolean isStorageForMashine() {
        return storageForMashine;
    }

    public boolean isStorageForTool() {
        return storageForTool;
    }

    public double getInventoryValue() {
        return inventoryValue;
    }

    public List<String> getKrinkorki() {
        return krinkorki;
    }

    public String getTodo() {
        return todo;
    }


    //endregion getter ans setter

}
