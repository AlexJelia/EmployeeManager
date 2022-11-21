package alex.jelia.empmanager.webapp.util;

import alex.jelia.empmanager.webapp.model.Organization;

public class HtmlHelper {

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
    public static String formatDates(Organization.Position position){
        return DateUtil.format(position.getStartDate()) + " - " + DateUtil.format(position.getEndDate());
    }
}
