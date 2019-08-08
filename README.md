# EU4 Area File Program:

Welcome to the EU4 Area File Program! This program writes the formatted files for area.txt, region.txt, superregion.txt, continent.txt, and the relevant localization file. Within the GUI that pops up, you can add a list of Regions and a list of Areas in each Region, edit them whenever you make a mistake, and then generate the files with a header to prevent overwriting, one Superregion at a time.

## Requirements:
This program requires only a JRE of 11 or greater (I have not tested it with an older JRE, if you do please let me know if it works and I will edit this accordingly)

## Using the Program:
You can find the latest .jar file for the script in the main repository directory. (Named EU4FileAreaProgram.jar). Download just that file, **not** the entire repository.

Double click this file to run it, and a prompt should pop up asking you to fill in first the Superregion name, then the Continent name. 

After this, you will see 2 lists (currently empty): one for regions, and one for areas. Once you add a region, that list should be populated. You can add an area to a region by selecting that region in the list; once again, after adding at least one area to that region, the area list should also be populated (when you select the respective region, of course). If you make any mistakes, you can always edit them using the edit buttons, and you can quickly see the provinces in an area by looking at the Label under the area list, to make sure you didn't make any mistakes.

If you made too many mistakes, you can clear both lists with the "Clear" button.

Once you are done, hit the finalize button. You will be prompted to entire a file header, do so (this will be a header in front of the file name, for example if you entered "spr" here, the files generated would be spr_area.txt, spr_region.txt, etc. This is to make sure that it does not overwrite any files. Also, it is handy if you find any mistakes after finalizing and want to fix those before closing the program; in fact, for this very reason the finalize button does not exit the window.)

After that, you should see your complete files in the same directory as you placed your .jar file. Copy and paste the contents over to your main area.txt / region.txt / etc. in your Mod folder.

## Notes on using the Program:
The continent file must be added manually to the localization. This was not added through the program to make sure that multiple superregions in the same continent dont print out the continent localization multiple times.

The adjectives in the localization file must be done manually, since it is far out of the scope of this program to have it automated (but feel free to add that as a contribution if you know how to and want to help this program become even more useful!)

## Contributing to the Repository:
To contribute, please fork the repository, make your changes, and send a merge request.
Make sure to include a detailed description of what feature you added, or what issue/bug you fixed (reference issue number if applicable).

Please have a working tested build before you make a merge request, so I can incorporate your code right away.

### Code requirements:
Please add a javadoc comment before every method, and also add line comments wherever you think they are necessary. The easier it is for me to understand your code, the faster I can get it into the program.
Please use camel case for all variables/method names, and use meaningful variable and method names.

### Reporting a bug / problem, or suggesting a new feature
If you find a bug in the program, or you think there is a problem in the code that ought to be addressed, or you feel there is some feature missing in the program that would make it a lot more useful, please open up an issue for it. Write a detailed and relevant issue, with steps to reproduce if it's a bug, or details about the suggested feature if itss a suggestion.

```
Check out Nirn Universalis, our WIP EU4 mod putting the world of Elder Scrolls in EU4. 
This program was in fact created for that mod, to make writing these specific files a 
lot less work. It's an excellent project and if you are at all interested in the 
universe of the Elder Scrolls, I'd highly recommend giving our mod a look!

Our repo: https://github.com/toilercleaner666/nirn
Our subreddit: https://www.reddit.com/r/nirnuniversallis/
Our discord server: https://discord.gg/Spsh9pA
```
