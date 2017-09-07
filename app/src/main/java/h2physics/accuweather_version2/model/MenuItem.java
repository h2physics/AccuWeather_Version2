package h2physics.accuweather_version2.model;

import h2physics.accuweather_version2.constants.MenuFlags;

/**
 * Created by YukiNoHara on 8/30/2017.
 */

public class MenuItem {
    public int icon;
    public String title;
    public MenuFlags flags;

    public MenuItem(int icon, String title, MenuFlags flags) {
        this.icon = icon;
        this.title = title;
        this.flags = flags;
    }
}


