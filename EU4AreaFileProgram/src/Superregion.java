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

    /**
     * generateLocalizationText:
     * Method generates formatted text for localization file
     *
     * @return formatted text block for whole superregion
     */
    public String generateLocalizationText() {
        String loc = "l_english:\n";
        for (int i = 0; i < regions.length; i++) {
            loc += regions[i].generateAreaRegionLocalization();
        }
        loc += " " + formattedName() + ": \"" + name + "\"\n";
        loc += " " + formattedName() + "_name: \"" + name + "\"\n";
        loc += " " + formattedName() + "_adj: \"" + name + "\"\n";

        return loc;
    }

    /**
     * provinceCount:
     * @return the number of provinces in the superregion
     */
    public int provinceCount() {
        int count = 0;
        for (int i = 0; i < regions.length; i++) {
            count += regions[i].provinceCount();
        }

        return count;
    }

    /**
     * getProvinceArray:
     * Method builds an unsorted array of all provinces across superregion
     *
     * @return integer array of provinces
     */
    public int[] getProvinceArray() {
        try {
            int[] provinces = new int[provinceCount()];
            int count = 0;
            for (int i = 0; i < regions.length; i++) {
                for (int j = 0; j < regions[i].getAreas().length; j++) {
                    for (int k = 0; k < regions[i].getAreas()[j].provinceArraySize(); k++) {
                        provinces[count] = regions[i].getAreas()[j].provinceArray()[k];
                        count++;
                    }
                }
            }
            return provinces;
        } catch (Exception e) {
            /**This makes my code more ordered for me than just having the method throw the exception. Basically, i want
               this resolved in writeSuperregion itself so I can abort that method**/
            throw new NumberFormatException();
        }
    }

    /**
     * Quicksort
     */
    public void quicksort(int[] array, int low, int high) {
        if (low < high)
        {
            int pivot = partition(array, low, high);

            quicksort(array, low, pivot - 1);
            quicksort(array, pivot + 1, high);
        }
    }

    /**
     * Partition for quicksort
     */
    public int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++)
        {
            if (array[j] <= pivot)
            {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    /**
     * continentFormattedName:
     * Method formats name for the Continent the superregion belongs to
     *
     * @return formatted name
     */
    public String continentFormattedName() {
        String formatted = String.join("_", continent.toLowerCase().split(" "));
        return formatted.endsWith("_continent") ? formatted : formatted + "_continent";
    }

    /**
     * generateContinentData:
     * Method formats a list of provinces as required by continent.txt
     *
     * @param provinces : array of provinces in continent
     *
     * @return formatted text block
     */
    private String generateContinentData(int[] provinces) {
        String provinceList = continent + " = {\n\t";
        for (int i = 0; i < provinces.length; i++) {
            provinceList += provinces[i];
            if (i + 1 < provinces.length) {
                provinceList += " ";
            }
        }

        provinceList += "\n}";
        return provinceList;
    }

    /**
     * writeSuperregion:
     * Method writes all the data from regions and respective areas arrays into the files, starting with a particular
     * file header. This method will be called as the final step when the script is run, since it is meant to be
     * executed Superregion by Superregion.
     *
     * @param fileheader Header in front of file names to prevent overwrite issues with multiple superregion files
     * present in the program directory (and also to avoid the headache that is formatting/corrections with append)
     */
    public void writeSuperregion(String fileheader) {

        //Setup for continent.txt
        int[] provinces;
        try {
            provinces = getProvinceArray();
        } catch (Exception e) {
            return;
        }
        quicksort(provinces, 0, provinces.length - 1);

        //Declare Filewriter to be used for all files
        FileWriter fileWriter = null;

        //Areas File
        try {
            //Initialize FileWriter for area.txt
            fileWriter = new FileWriter(fileheader + "_area.txt");

            //Generate area.txt data region by region
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

            //Generate region.txt data region by region
            String regionText = "";
            for (int k = 0; k < regions.length; k++) {
                regionText += regions[k].generateRegionText();
            }
            fileWriter.write(regionText);
            fileWriter.flush();

            //Initialize FileWriter for superregion.txt data
            fileWriter = new FileWriter(fileheader + "_superregion.txt");

            //Generate superregion.txt data
            fileWriter.write(generateSuperregionText());
            fileWriter.flush();

            //Initialize FileWriter for continent.txt
            fileWriter = new FileWriter(fileheader + "_continent.txt");

            //Generate continent.txt data
            fileWriter.write(generateContinentData(provinces));
            fileWriter.flush();

            //Initialize FileWriter for Localization file
            fileWriter = new FileWriter(fileheader + "_areas_regions_l_english.txt");

            //Generate Localization
            fileWriter.write(generateLocalizationText());
            fileWriter.flush();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
