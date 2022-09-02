package com.rendavis.nycsatscores.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolLocalDataSource {
    private final List<School> mSchools = new ArrayList<>();
    private final Map<String, School> mSchoolMap = new HashMap<>();

    public SchoolLocalDataSource() {
        addSchool(new School(
            "02M260",
            "Clinton School Writers & Artists, M.S. 260",
            "Students who are prepared for college must have an education that encourages them to take risks as they produce and perform. Our college preparatory curriculum develops writers and has built a tight-knit community. Our school develops students who can think analytically and write creatively. Our arts programming builds on our 25 years of experience in visual, performing arts and music on a middle school level. We partner with New Audience and the Whitney Museum as cultural partners. We are a International Baccalaureate (IB) candidate school that offers opportunities to take college courses at neighboring universities.",
            "10 East 15th Street, Manhattan NY 10003 (40.736526, -73.992727)",
            "212-524-4360",
            "www.theclintonschool.net"
        ));
        addSchool(new School(
            "21K728",
            "Liberation Diploma Plus High School",
            "The mission of Liberation Diploma Plus High School, in partnership with CAMBA, is to develop the student academically, socially, and emotionally. We will equip students with the skills needed to evaluate their options so that they can make informed and appropriate choices and create personal goals for success. Our year-round model (trimesters plus summer school) provides students the opportunity to gain credits and attain required graduation competencies at an accelerated rate. Our partners offer all students career preparation and college exposure. Students have the opportunity to earn college credit(s). In addition to fulfilling New York City graduation requirements, students are required to complete a portfolio to receive a high school diploma.",
            "2865 West 19th Street, Brooklyn, NY 11224 (40.576976, -73.985413)",
            "718-946-6812",
            "schools.nyc.gov/schoolportals/21/K728"
        ));
        addSchool(new School(
            "08X282",
            "Women's Academy of Excellence",
            "The Women’s Academy of Excellence is an all-girls public high school, serving grades 9-12. Our mission is to create a community of lifelong learners, to nurture the intellectual curiosity and creativity of young women and to address their developmental needs. The school community cultivates dynamic, participatory learning, enabling students to achieve academic success at many levels, especially in the fields of math, science, and civic responsibility. Our scholars are exposed to a challenging curriculum that encourages them to achieve their goals while being empowered to become young women and leaders. Our Philosophy is GIRLS MATTER!",
            "456 White Plains Road, Bronx NY 10473 (40.815043, -73.85607)",
            "718-542-0740",
            "schools.nyc.gov/SchoolPortals/08/X282"
        ));
    }

    public School getSchool(final String id) {
        return mSchoolMap.get(id);
    }

    public List<School> getAllSchools() {
        return mSchools;
    }

    private void addSchool(School school) {
        mSchools.add(school);
        mSchoolMap.put(school.getId(), school);
    }
}
