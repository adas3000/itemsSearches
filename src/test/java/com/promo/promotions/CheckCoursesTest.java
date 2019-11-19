package com.promo.promotions;


import com.promo.promotions.schedule.CheckCourses;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CheckCoursesTest {

    private CheckCourses checkCourses;

    @Test
    public void ifFoundThenOk() throws ParserConfigurationException, SAXException, IOException, JSONException {

        checkCourses = new CheckCourses();
        assertNotNull(checkCourses);


    }


}
