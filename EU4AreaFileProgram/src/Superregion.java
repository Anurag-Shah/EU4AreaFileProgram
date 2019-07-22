import java.io.FileWriter;

public class Superregion {
    private Region[] regions; //Array of regions belonging to this superregion
    private String continent; //Continent this superregion belongs to
    private String name;      //Unformatted name of area

    //Normal Constructor
    public Superregion(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    //Constructor when editing superregion
    public Superregion(String name, String continent, Region[] regions) {
        this(name, continent);
        this.regions = regions;
    }

    public String getName() {
        return name;
    }

    public Region[] getRegions() {
        return regions;
    }

    public String getContinent() {
        return continent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setRegions(Region[] regions) {
        this.regions = regions;
    }

    /**
     * addArea:
     * Method appends a given region to the end of regions array
     *
     * @param region region to add
     */
    public void addRegion(Region region) {

        region.setSuperregion(name);
        if (regions == null) {
            regions = new Region[1];
            regions[0] = region;
        } else {
            Region[] temp = new Region[this.regions.length + 1];
            for (int i = 0; i < regions.length; i++) {
                temp[i] = regions[i];
            }
            temp[regions.length] = region;
            regions = temp;
        }
    }

    /**
     * formattedName:
     * Method formats the superregion name to match the formatting in superregion.txt
     *
     * @return formatted superregion name
     */
    public String formattedName() {
        String formatted = name.toLowerCase();
        formatted = String.join("_", formatted.split(" "));
        return formatted + (formatted.endsWith("superregion") ? "" : "_superregion");
    }

    /**
     * generateSuperregionText:
     * Method generates the formatted text block as in superregion.txt for a particular superregion
     *
     * @return returns the formatted text block
     */
    public String generateSuperregionText() {
        String text = "\n" + formattedName() + " = {\n\n";
        for (int i = 0; i < regions.length; i++) {
            text += "\t" + regions[i].formattedName() + "\n";
        }
        return text + "}\n";
    }

    public void writeSuperregion(String fileheader) {
        FileWriter fileWriter = null;

        //Areas File
        try {
            //Initialize FileWriter for area.txt
            fileWriter = new FileWriter(fileheader + "_area.txt");

            //Generate area.txt region by region
            String areaText = "";
            for (int i = 0; i < regions.length; i++) {
                for (int j = 0; j < regions[i].getAreas().length; j++) {
                    areaText += regions[i].getAreas()[j].generateAreaText();
                }
            }
            fileWriter.write(areaText);
            fileWriter.flush();

            //Initialize FileWriter for region.txt
            fileWriter = new FileWriter(fileheader + "_region.txt");

            //Generate region.txt region by region
            String regionText = "";
            for (int k = 0; k < regions.length; k++) {
                regionText += regions[k].generateRegionText();
            }
            fileWriter.write(regionText);
            fileWriter.flush();

            //Initialize FileWriter for superregion.txt
            fileWriter = new FileWriter(fileheader + "_superregion.txt");

            //Generate superregion.txt
            fileWriter.write(generateSuperregionText());
            fileWriter.flush();

            //Initialize FileWriter for continent.txt
            fileWriter = new FileWriter(fileheader + "_continent.txt");

            //TODO FIGURE SOMETHING OUT FOR CONTINENT

        } catch (Exception e) {

        }
    }
}
