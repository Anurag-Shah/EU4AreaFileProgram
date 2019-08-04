public class Region {

    private Area[] areas;       //Array of Areas belonging to this region
    private String name;        //Unformatted name of area
    private String superregion; //Superregion this region belongs to

    //Normal constructor
    public Region(String name) {
        this.name = name;
        this.superregion = "";
    }

    //Constructor when editing region
    public Region(Area[] areas, String name) {
        this(name);
        this.areas = areas;
    }

    public Region(String name, String superregion) {
        this(name);
        this.superregion = superregion;
    }

    String getName() {
        return name;
    }

    Area[] getAreas() {
        return areas;
    }

    String getSuperregion() {
        return superregion;
    }

    void setName(String name) {
        this.name = name;
    }

    void setAreas(Area[] areas) {
        this.areas = areas;
    }

    void setSuperregion(String superregion) {
        this.superregion = superregion;
    }

    /**
     * addArea:
     * Method appends a given area to the end of areas array
     *
     * @param area area to add
     */

    void addArea(Area area) {
        if (areas == null) {
            //If areas array hasn't been initialized do so now
            areas = new Area[1];
            areas[0] = area;
        } else {
            //If areas array has been initialized append to end of array
            Area[] temp = new Area[this.areas.length + 1];
            for (int i = 0; i < areas.length; i++) {
                temp[i] = areas[i];
            }
            temp[areas.length] = area;
            areas = temp;
        }
    }

    /**
     * formattedName:
     * Method formats the Region name to match the formatting in region.txt and superregion.txt
     *
     * @return formatted region name
     */
    String formattedName() {
        String formatted = name.toLowerCase();

        //Formats spaces into underscores
        formatted = String.join("_", formatted.split(" "));

        if (formatted.endsWith("_region")) {
            formatted = formatted.substring(0, formatted.length() - 7);
        }

        //Formats name to end with "_<superregion>_region" if not already
        if (formatted.endsWith(superregion)) {
            formatted += "_region";
        } else if (!formatted.endsWith("_" + superregion + "_region")) {
            formatted += "_" + superregion + "_region";
        }

        //Fixes Superregion having a space or uppercase character
        formatted = String.join("_", formatted.split(" ")).toLowerCase();

        return formatted;
    }

    /**
     * generateRegionText:
     * Method generates the formatted text block as in region.txt for a particular region
     *
     * @return returns the formatted text block
     */
    String generateRegionText() {
        String text = "\n" + formattedName() + " = {\n\tareas = {\n";
        for (int i = 0; i < areas.length; i++) {
            text += "\t\t" + areas[i].formattedName() + "\n";
        }
        text += "\t}\n}\n";

        return text;
    }

    /**
     * generateAreaRegionLocalization:
     * Formats area names and region names for localization file
     *
     * @return formatted text block
     */
    String generateAreaRegionLocalization() {
        String loc = "";
        for (int i = 0; i < areas.length; i++) {
            loc += " " + areas[i].formattedName() + ": \"" + areas[i].getName() + "\"\n";
            loc += " " + areas[i].formattedName() + "_name: \"" + areas[i].getName() + "\"\n";
            loc += " " + areas[i].formattedName() + "_adj: \"" + areas[i].getName() + "\"\n";
        }
        loc += " " + formattedName() + ": \"" + name + "\"\n";
        loc += " " + formattedName() + "_name: \"" + name + "\"\n";
        loc += " " + formattedName() + "_adj: \"" + name + "\"\n";

        return loc;
    }

    /**
     * provinceCount
     * @return total number of provinces in the region
     */
    int provinceCount() {
        int count = 0;
        for (int i = 0; i < areas.length; i++) {
            count += areas[i].provinceArraySize();
        }
        return count;
    }


    /**
     * areaNames:
     * Method builds a string array of all names of Area objects in areas
     *
     * @return String array of area names
     */
    String[] areaNames() {
        String[] names = new String[areas.length];
        for (int i = 0; i < areas.length; i++) {
            names[i] = areas[i].getName();
        }

        return names;
    }

}
